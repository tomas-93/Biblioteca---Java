package com.diseño;
import javax.swing.JOptionPane;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import com.mysql.Conexion;
import com.mysql.TransaccionesYConsultas;

import java.sql.Connection;

/**
 * Created by thomas on 22/11/2014.
 */
public class Frame extends JFrame implements ActionListener
{
    private final JButton tablaConsulta = new JButton("Consultas de Alumnos");
    private final JButton formularioPedido = new JButton ("Pedido de Libros");
    private final JButton formularioLibro = new JButton("Formulario de Libros");
    private final JButton formularioAdmi = new JButton ("Administrador");
    private final JButton ingresarJB = new JButton("Ingresar");
    private JPasswordField passJP= new JPasswordField(10);
    private final JLabel etiqJL = new JLabel("Bienvenido, Ingrese su contraseña: ");
    private JTextField userJT = new JTextField(10);
    private JPanel botonera = new JPanel();
    private JPanel froms = new JPanel();
    private final String user = "itesco";
    private final String pass = "12345";
    private final Connection conec = Conexion.getConexion();

    public Frame()
    {
        //Frame
        this.setTitle("Biblioteca");
        this.setSize(700, 410);
        this.setResizable(false);
        this.setBackground(Color.WHITE);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        //Panel Botonera
        this.botonera.setBackground(Color.WHITE);
        Dimension size = new Dimension(675,45);
        this.botonera.setLayout(new FlowLayout());
        this.botonera.setPreferredSize(size);
        Border bor = BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder());
        this.botonera.setBorder(bor);
        //Enable
        this.tablaConsulta.setEnabled(false);
        this.formularioPedido.setEnabled(false);
        this.formularioAdmi.setEnabled(false);
        this.formularioLibro.setEnabled(false);
        //agregar a botonera
        this.botonera.add(this.tablaConsulta);
        this.botonera.add(this.formularioPedido);
        this.botonera.add(this.formularioLibro);
        this.botonera.add(this.formularioAdmi);

        //Panel PanelFormulario
        this.froms.setBackground(Color.WHITE);
        this.froms.setPreferredSize(new Dimension(675, 235));
        Border bord = BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder());
        this .froms.setBorder(bord);
        //agregar PanelFormulario
        ///*

        this.froms.add(this.etiqJL);
        this.froms.add(this.passJP);
        this.froms.add(this.ingresarJB);
        //*/




        //LAYOUT Y AGREGAR
        this.setLayout( new FlowLayout());
        this.add(new AnimacionSuperior());
        this.add(this.botonera);
        this.add(this.froms);
        this.setVisible(true);

        //Eventos
        this.tablaConsulta.addActionListener(this);
        this.formularioPedido.addActionListener(this);
        this.formularioLibro.addActionListener(this);
        this.formularioAdmi.addActionListener(this);
        this.ingresarJB.addActionListener(this);

    }
    @Override
    public void actionPerformed(ActionEvent evt)
    {
        if(evt.getSource() == this.tablaConsulta)
        {
            this.froms.removeAll();
            this.froms.add(new Consultas());
            this.cambioDePanel();
        }else if(evt.getSource() == this.formularioPedido)
        {
            this.froms.removeAll();
            this.froms.add(new PedidoDeLibro());
            this.cambioDePanel();
        }else if(evt.getSource() == this.formularioLibro)
        {
            this.froms.removeAll();
            this.froms.add(new AltaDeLibros());
            this.cambioDePanel();
        }else if(evt.getSource() == this.formularioAdmi)
        {
            this.froms.removeAll();
            this.froms.add(new Administrador());
            this.cambioDePanel();

        }else if(evt.getSource() == this.ingresarJB)
        {
            if(this.pass.equals(this.passJP.getText()) || this.consultaPass())
            {
                this.passJP.setText("");
                JOptionPane.showMessageDialog(
                        null,
                        "Contraseña Correcta Bienvenido",
                        "Bienvenido",
                        JOptionPane.INFORMATION_MESSAGE);
                this.enAlto();
            }//else para la consultas de usuario
        }

    }

    private void cambioDePanel()
    {
        this.froms.updateUI();
        this.froms.repaint();
        this.repaint();
    }
    public static void main(String [] args)
    {
        new Frame();
    }
    private void enAlto()
    {
        this.tablaConsulta.setEnabled(true);
        this.formularioPedido.setEnabled(true);
        this.formularioAdmi.setEnabled(true);
        this.formularioLibro.setEnabled(true);
    }
    private boolean consultaPass()
    {
        if(TransaccionesYConsultas.getPassDeEmpleado(conec, this.passJP.getText()).equals(this.passJP.getText()))
        return true;
        else
        {
            this.passJP.setText("");
            JOptionPane.showMessageDialog(
                    null,
                    "Error verifique su contraseña",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

}
