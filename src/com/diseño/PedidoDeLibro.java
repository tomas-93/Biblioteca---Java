package com.diseño;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.mysql.Conexion;
import com.mysql.TransaccionesYConsultas;


/**
 * Created by thomas on 26/11/2014.
 */
public class PedidoDeLibro extends JPanel implements ActionListener
{
    private final JButton botonJB = new JButton("Alta de Pedido") ;
    private PedidoDeLibroDatosDeAlumno alumno;
    private PedidoDeLibroDatosDeLibro libro;
    private PedidoDeLibroDatosDePrestamo prestamo;

    public PedidoDeLibro()
    {
        this.crearUI();
        //Eventos
        this.botonJB.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent avt)
    {
        if(alumno.comprobarElmentos())
        {
            if(JOptionPane.showConfirmDialog(null,
                this.alumno.getMensaje(),
                "Comprobar",
                JOptionPane. OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
               ) == 0 && this.libro.getBandera())
            {
                /*
                Trasaccion
                 */

                if(TransaccionesYConsultas.trsaccionAlumno(
                        Conexion.getConexion(),
                        this.alumno.getNumeroC(),
                        this.alumno.getNombreYapellido(),
                        this.alumno.getCarrera(),
                        this.alumno.getNivel()
                ))
                    if(TransaccionesYConsultas.trsaccionPrestamo(

                        Conexion.getConexion(),
                        this.prestamo.getID(),
                        this.alumno.getNumeroC(),
                        this.libro.getId(),
                        this.prestamo.getTime()
                    ))JOptionPane.showMessageDialog(
                            null,
                            "Transaccion Realizada",
                            "Correcto",
                            JOptionPane.INFORMATION_MESSAGE);
                this.alumno.setMnesaje();
                this.refrescar();

            }else this.alumno.setMnesaje();
        }else
        {
            this.alumno.setMnesaje();
            JOptionPane.showMessageDialog(null,
                    "Faltan Campos por llenar",
                    "Error",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }
    private void refrescar()
    {
        this.removeAll();
        this.crearUI();
        this.updateUI();
        this.repaint();
        this.repaint();
    }
    private void crearUI()
    {
        //Border
        Border bor = BorderFactory.createTitledBorder("Pedidos de Libros");
        this.setBorder(bor);
        this.setBackground(Color.WHITE);
        //instacias
        this.alumno  = new PedidoDeLibroDatosDeAlumno();
        this.libro = new PedidoDeLibroDatosDeLibro();
        this.prestamo = new PedidoDeLibroDatosDePrestamo();
        //sub panel contendor
        JPanel subpane = new JPanel(new GridLayout(4,1));
        subpane.add(this.alumno);
        subpane.add(this.libro);
        subpane.add(this.prestamo);

        //panel del boton
        JPanel b = new JPanel();
        b.setBackground(Color.WHITE);
        b.setPreferredSize(new Dimension(100,100));
        b.add(this.botonJB);
        subpane.add(b);

        //Panel del Scroll
        JScrollPane barra = new JScrollPane(subpane);

        //agregar panel
        barra.setPreferredSize(new Dimension(640, 190));
        this.add(barra);

    }

}
