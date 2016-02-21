package com.diseño;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.awt.event.FocusListener;
import java.awt.event.FocusEvent;

import com.mysql.TransaccionesYConsultas;
import com.mysql.Conexion;
import java.sql.Connection;
/**
 * Created by thomas on 26/11/2014.
 */
public class Administrador extends JPanel
        implements ActionListener,KeyListener, FocusListener
{
    private JLabel paswordAdmJL,passwordUserJL , idJL,nombreJL, cargoJL;
    private JTextField  idJT, nombreJT, cargoJT;
    private JPasswordField passwordAministradorJP,passwordUserJP;
    private JButton altaJB;
    private String nombre;
    private String cargo;
    private String passw;
    private String mensaje;
    private boolean bandera;
    private int id;
    private Connection conec = Conexion.getConexion();

    public Administrador()
    {
        this.crearUI();
        //evento
        this.altaJB.addActionListener(this);
        this.passwordAministradorJP.addKeyListener(this);
        this.passwordUserJP.addFocusListener(this);

        //propiedades
        this.nombre = "";
        this.cargo = "";
        this.passw ="";
        this.mensaje = "Compruebe los siguientes campos\n";
        this.bandera = false;
    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(this.comprobarNombre() && this.comprobarCargo())
        {
            this.transaccion();
            this.crearUI();
        }else
        {
            JOptionPane.showMessageDialog(null,
                    "Compruebe los campos de Nombre y Cargo\n",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            this.crearUI();
        }
    }
    @Override
    public void keyTyped(KeyEvent e)
    {    }
    @Override
    public void keyReleased(KeyEvent e)
    {
        String cad = this.passwordAministradorJP.getText();
        if(e.getKeyCode() == e.VK_ENTER)
        {
            System.out.println("enter");

        }else if(e.getKeyCode() == e.VK_BACK_SPACE)
        {
            System.out.println("BORRRA");

        }else if(cad.matches("[1-5]{1,5}"))
        {
            if(cad.equals("12345"))
            {
                JOptionPane.showMessageDialog(null,"Contraseña Correcta");
                this.passwordAministradorJP.setText("");
                enAlto(true);
            }
        }else
        {
            JOptionPane.showMessageDialog(null,"Error en contraseña");
            this.passwordAministradorJP.setText("");
        }


    }
    @Override
    public void keyPressed(KeyEvent e)
    {    }
    @Override
    public void focusGained(FocusEvent e)
    {    }
    @Override
    public void focusLost(FocusEvent e)
    {
        if(e.getSource() == this.passwordUserJP)
        {
            this.bandera = this.comprobarPass();
        }
    }
    private void enAlto(boolean t)
    {
        this.id =Integer.parseInt(consulta());
        this.idJT.setText(consulta());
        this.passwordUserJP.setEditable(t);
        this.nombreJT.setEditable(t);
        this.cargoJT.setEditable(t);
    }
    private boolean comprobarPass()
    {
        this.passw = this.passwordUserJP.getText();
        if(this.passw.matches("[0-9]{8}"))
        {
            return true;
        }else
        {
            JOptionPane.showMessageDialog(null, "La contraseña solo permite " +
                    "\ndigitos [0-9]. No se permiten espacios ni palabras acentuadas ",
                    "Error",JOptionPane.ERROR_MESSAGE);
            this.passwordUserJP.setText("");
            return false;
        }
    }
    private boolean comprobarNombre()
    {
        String exp = "[A-Z\u00D1\u00F1\u00E1\u00E9\u00ED\u00F3\u00FA \u00C1\u00C9\u00CD\u00D3\u00DA]{1,50}";
        this.nombre = this.nombreJT.getText().toUpperCase().trim();
        if (this.nombre.matches(exp))
        {
            this.mensaje += "Nombre:\n*" + this.nombre;
            return true;
        } else return false;
    }
    private boolean comprobarCargo()
    {
        String exp = "[A-Z\u00D1\u00F1\u00E1\u00E9\u00ED\u00F3\u00FA \u00C1\u00C9\u00CD\u00D3\u00DA]{1,30}";
        this.cargo = this.cargoJT.getText().toUpperCase().trim();
        if (this.cargo.matches(exp))
        {
            this.mensaje += "\nCargo:\n*" + this.cargo;
            return true;
        } else return false;
    }
    private void transaccion()
    {
        if(JOptionPane.showConfirmDialog(
                null,
                this.mensaje,
                "Confirma",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE
        ) == 0 && this.bandera)
            if(TransaccionesYConsultas.trsaccionEmpleado(
                    this.conec,
                    this.id,
                    this.nombre,
                    this.cargo,
                    this.passw))
                JOptionPane.showMessageDialog(
                    null,
                    "Transaccion Realizada",
                        "Correcto",
                        JOptionPane.INFORMATION_MESSAGE);

        else this.mensaje ="Compruebe los siguientes campos\n";
    }
    private String consulta()
    {
        return TransaccionesYConsultas.getConsultaIDEmpleado(this.conec);
    }
    private void refrescar()
    {
        this.removeAll();
        this.crearUI();
        this.updateUI();
        this.repaint();
    }
    private void crearUI()
    {
        this.setLayout(new FlowLayout(15,15,15));
        //JPanel
        this.setPreferredSize(new Dimension(625, 200));
        Border borde = BorderFactory.createTitledBorder("Administrador");
        this.setBorder(borde);
        this.setBackground(Color.WHITE);

        //Jlabel administrador y Jpassword
        this.paswordAdmJL = new JLabel("Ingrese Password de Administrador");
        this.passwordAministradorJP = new JPasswordField(10);
        this.passwordAministradorJP.setToolTipText(
                "Preciones enter al terminar de ingresar la contraseña");
        this.add(this.paswordAdmJL);
        this.add(this.passwordAministradorJP);
        //id etiqueta y textfield
        this.idJL = new JLabel("ID ");
        this.idJT = new JTextField(10);
        this.idJT.setEditable(false);
        this.add(this.idJL);
        this.add(this.idJT);
        //CONTRASEÑA jlabel jpassword
        this.passwordUserJL = new JLabel("Ingrese Contraseña");
        this.passwordUserJP = new JPasswordField(10);
        this.passwordUserJP.setEditable(false);
        this.passwordUserJP.setToolTipText("La contraseña solo permite caracteres " +
                "de la [A-Z], [a-z] y \ndigitos [0-9]. No se permiten espacios ni palabras acentuadas ");
        this.add(this.passwordUserJL);
        this.add(this.passwordUserJP);
        //nombre jlabel y jtextfield
        this.nombreJL = new JLabel("Ingresar nombre");
        this.nombreJT = new JTextField(10);
        this.nombreJT.setEditable(false);
        this.add(this.nombreJL);
        this.add(this.nombreJT);
        //margen
        JPanel  pane1 = new JPanel();
        pane1.setPreferredSize(new Dimension(90,5));
        pane1.setBackground(Color.WHITE);
        this.add(pane1);
        // cargo jlabel y jtextfield
        this.cargoJL = new JLabel("Cargo");
        this.cargoJT = new JTextField(10);
        this.cargoJT.setEditable(false);
        this.add(this.cargoJL);
        this.add(this.cargoJT);
        //margen
        JPanel  pane = new JPanel();
        pane.setPreferredSize(new Dimension(225,5));
        pane.setBackground(Color.WHITE);
        this.add(pane);
        //boton
        altaJB = new JButton("Alta de Usuario");
        this.add(altaJB);

    }


}
