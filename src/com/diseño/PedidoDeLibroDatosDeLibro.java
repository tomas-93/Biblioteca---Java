package com.diseño;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import com.mysql.TransaccionesYConsultas;
import com.mysql.Conexion;




/**
 * Created by thomas on 26/11/2014.
 */
public class PedidoDeLibroDatosDeLibro extends JPanel implements KeyListener
{
    private JLabel idLibroJL,tituloJL,autorJL;
    private JTextField idLibroJT,tituloJT,autorJT;
    private int id;
    private boolean bandera;
    private final Connection conec = Conexion.getConexion();


    public PedidoDeLibroDatosDeLibro()
    {
        super(new FlowLayout(15,15,15));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(580, 100));
        Border bor = BorderFactory.createTitledBorder("Infomarcion del Libro");
        this.setBorder(bor);
        //id
        this.idLibroJL = new JLabel("ID Libro");
        this.idLibroJT = new JTextField (8);
        this.add(this.idLibroJL);
        this.add(this.idLibroJT);
        //titulo
        this.tituloJL = new JLabel("Titulo del Libro");
        this.tituloJT = new JTextField (15);
        this.tituloJT.setEditable(false);
        this.add(this.tituloJL);
        this.add(this.tituloJT);
        //Separacion
        JPanel sep = new JPanel();
        sep.setPreferredSize(new Dimension(150,5));
        sep.setBackground(Color.WHITE);
        this.add(sep);
        //autor;
        this.autorJL = new JLabel ("Autor del Libro");
        this.autorJT = new JTextField(15);
        this.autorJT.setEditable(false);
        this.add(this.autorJL);
        this.add(this.autorJT);


        //eventos
        this.idLibroJT.addKeyListener(this);



    }

    @Override
    public void keyPressed(KeyEvent evt)
    {}
    @Override
    public void keyReleased(KeyEvent evt)
    {
        if(evt.getKeyCode() == evt.VK_ENTER)
        {
            System.out.println("enter");
            this.comprobarID();

        }else if(evt.getKeyCode() == evt.VK_BACK_SPACE)
        {
        }else if(this.idLibroJT.getText().length() == 8)this.comprobarID();
        else if(this.idLibroJT.getText().length() > 8){
            this.bandera = false;
            JOptionPane.showMessageDialog(null,
                    "Error, el campo del ID es numerico\nSolo se permiten 8 caracteres",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            this.idLibroJT.setText("");
        }


    }
    @Override
    public void keyTyped(KeyEvent evt)
    {}
    public int getId()
    {
        return this.id;
    }
    public boolean getBandera()
    {
        return this.bandera;
    }

    private void comprobarID()
    {
        if(this.idLibroJT.getText().matches("[0-9]{1,8}"))
        {
            this.id = Integer.parseInt(this.idLibroJT.getText());
            this.bandera = true;
            this.consulta();
        }
    }
    private void consulta()
    {
        String [] respuesta = TransaccionesYConsultas.
                getTituloYEditorial(this.conec,this.id);
        this.tituloJT.setText(respuesta[0]);
        this.autorJT.setText(respuesta[1]);

    }


}
