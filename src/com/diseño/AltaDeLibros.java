package com.diseño;
import com.mysql.Conexion;
import com.mysql.TransaccionesYConsultas;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.FlowLayout;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.io.File;

/**
 * Created by thomas on 26/11/2014.
 */
public class AltaDeLibros extends JPanel implements ActionListener{
    private JLabel idLibroJL, tituloJL,editorialJL,
            autorJL, fechadeCreacionJL,libroDigitalJL;
    private JTextField idLibroJT, tituloJT, editorialJT,
            autorJT, fechadeCreacionJT;
    private JComboBox libroDigitalJC;
    private JButton altaJB;
    private final Connection conec = Conexion.getConexion();
    private String mensaje,titulo,editorial,autor,url,fecha;
    private boolean alvetencia;
    private int id;


    public AltaDeLibros()
    {
        this.crearUI();
        //eventos
        this.altaJB.addActionListener(this);
        this.libroDigitalJC.addActionListener(this);
        // variable
        this.mensaje = "¿Los elementos ingresados son correctos?\n";
        this.titulo ="";
        this.editorial ="";
        this.autor = "";
        this.url ="";
        this.fecha ="";

    }
    @Override
    public void actionPerformed(ActionEvent avt)
    {
        if(avt.getSource() == this.libroDigitalJC)
        {
            this.libroDigitalJC = (JComboBox)avt.getSource();
            if(this.libroDigitalJC.getSelectedItem().equals("Libro Digital"))
            {
                try
                {
                    this.url = ventanaBusqueda();
                    alvertenciaURL();
                }catch(NullPointerException e)
                {
                    alvertenciaURL();
                }
            }
            else
            {
                this.alvetencia=true;
                this.url ="Libro Fisico";
            }
        }else if(avt.getSource() == this.altaJB)
        {
            if(alvertenciaURL())
                transaccion();
        }
    }
    private String getIdDB()
    {
        return TransaccionesYConsultas.getConsultaIDLirbo(this.conec);
    }
    private boolean comprobarTitulo()
    {

        this.titulo = this.tituloJT.getText().toUpperCase().trim();
        String exp ="[A-Za-z\u00D1\u00F1\u00E1\u00E9\u00ED\u00F3\u00FA \u00C1\u00C9\u00CD\u00D3\u00DA]+";
        if(this.titulo.matches(exp)
                &&
                this.titulo.length() <= 30
                )
        {
            this.mensaje += "*Nombre del titulo:\n"
                    +this.titulo+"\n";
            return true;
        }  else return false;

    }
    private boolean comprobarEditorial()
    {
        this.editorial = this.editorialJT.getText().toUpperCase().trim();
        String exp ="[A-Za-z0-9\u00D1\u00F1\u00E1\u00E9\u00ED\u00F3\u00FA \u00C1\u00C9\u00CD\u00D3\u00DA]+";
        if(this.editorial.matches(exp)
                &&
                this.editorial.length() <= 30
                )
        {
            this.mensaje += "*Nombre de la editorial:\n"
                    +this.editorial+"\n";
            return true;
        }  else return false;
    }
    private boolean comprobarAutor()
    {
        this.autor = this.autorJT.getText().toUpperCase().trim();
        String exp ="[A-Za-z\u00D1\u00F1\u00E1\u00E9\u00ED\u00F3\u00FA \u00C1\u00C9\u00CD\u00D3\u00DA]+";
        if(this.autor.matches(exp)
                &&
                this.autor.length() <= 50
                )
        {
            this.mensaje += "*Nombre del autor\n"
                    +this.autor+"\n";
            return true;
        }  else return false;

    }
    private boolean comprobarFecha()
    {
        this.fecha = this.fechadeCreacionJT.getText().toUpperCase().trim();
        String exp ="[A-Za-z0-9\u00D1\u00F1\u00E1\u00E9\u00ED\u00F3\u00FA \u00C1\u00C9\u00CD\u00D3\u00DA]+";
        if(this.fecha.matches(exp)
                &&
                this.fecha.length() <= 50
                )
        {
            this.mensaje += "*Fecha:\n"
                    +this.fecha;
            return true;
        }  else return false;

    }
    private void transaccion()
    {
        if(comprobarTitulo()
                && comprobarAutor()
                && comprobarEditorial()
                && comprobarFecha())
        {
            //transaccion
            if(JOptionPane.showConfirmDialog(null,
                    this.mensaje,
                    "Alvertencia",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == 0)
            {
                if(TransaccionesYConsultas.trsaccionLibro(
                        this.conec,
                        this.id,
                        this.titulo,
                        this.editorial,
                        this.autor,
                        this.url,
                        this.fecha))
                    JOptionPane.showMessageDialog(
                        null,
                        "Transaccion Realizada",
                        "Correcto",
                        JOptionPane.INFORMATION_MESSAGE);
                this.refrescar();
            }
        }else
        {
            JOptionPane.showMessageDialog(null,"Error verifique los campos",
                    "Error",JOptionPane.ERROR_MESSAGE);
            this.refrescar();
        }

    }
    private String ventanaBusqueda()
    {
        File ruta;
        int opcion;
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Seleccione Ruta");
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);
        opcion = jfc.showOpenDialog(null);
        if(opcion == JFileChooser.APPROVE_OPTION)
        {
            this.alvetencia = true;
            ruta = jfc.getSelectedFile();
        }else
        {
            this.alvetencia = false;
            ruta = null;
        }
        return ruta.getAbsolutePath();
    }
    private boolean alvertenciaURL()
    {
        if(!this.alvetencia)
        {
            JOptionPane.showMessageDialog(null,"No ha seleccionado ruta",
                    "Error",JOptionPane.ERROR_MESSAGE);
            return false;
        }else return true;

    }
    private void crearUI()
    {
        //Panel
        this.setLayout(new FlowLayout(15, 30, 15));
        this.setPreferredSize(new Dimension(625, 200));
        Border borde = BorderFactory.createTitledBorder("Alta de Libros");
        this.setBorder(borde);
        this.setBackground(Color.WHITE);


        //variables ID
        this.idLibroJL = new JLabel("ID de Libro");
        this.idLibroJT = new JTextField(8);
        this.idLibroJT.setEnabled(false);
        this.id= Integer.parseInt(getIdDB());
        this.idLibroJT.setText(getIdDB());
        this.add(this.idLibroJL);
        this.add(this.idLibroJT);

        //variables TITULO
        this.tituloJL = new JLabel("Tiutlo del Libro");
        this.tituloJT = new JTextField(10);
        this.add(this.tituloJL);
        this.add(this.tituloJT);
        //MARGEN
        JPanel margen1 = new JPanel();
        margen1.setPreferredSize(new Dimension(50,5));
        margen1.setBackground(Color.WHITE);
        this.add(margen1);

        //variable Autor
        this.autorJL = new JLabel("Autor de Libro");
        this.autorJT = new JTextField(10);
        this.add(this.autorJL);
        this.add(this.autorJT);
        //variable Editorial
        this.editorialJL = new JLabel ("Editorial del Libro");
        this.editorialJT = new JTextField(10);
        this.add(this.editorialJL);
        this.add(this.editorialJT);

        //varaible fecha de creacion
        this.fechadeCreacionJL = new JLabel("Fecha de Creacion");
        this.fechadeCreacionJT = new JTextField(10);
        this.add(this.fechadeCreacionJL);
        this.add(this.fechadeCreacionJT);

        //variable tipo de libro

        //label
        this.libroDigitalJL  = new JLabel("Tipo de Libro");
        this.add(this.libroDigitalJL);

        //combobox
        String [] item = {"Libro Digital", "Libro Fisico"};
        this.libroDigitalJC = new JComboBox(item);
        this.add(this.libroDigitalJC);
        //margen 2
        JPanel margen2 = new JPanel();
        margen2.setPreferredSize(new Dimension(355,5));
        margen2.setBackground(Color.WHITE);
        this.add(margen2);
        //Boton
        this.altaJB = new JButton("Alta del Libro");
        this.add(this.altaJB);
    }
    private void refrescar()
    {
        this.removeAll();
        this.crearUI();
        this.updateUI();
        this.repaint();
        this.repaint();
    }



}
