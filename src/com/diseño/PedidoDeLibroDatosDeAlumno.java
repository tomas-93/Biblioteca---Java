package com.diseño;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
/**
 * Created by thomas on 26/11/2014.
 */
public class PedidoDeLibroDatosDeAlumno extends JPanel implements KeyListener {
    private JLabel numeroDeControlJL, nombreJL, carreraJL, nivelJL;
    private JTextField numeroDeControlJT, nombreJT;
    private JComboBox carreraJC,nivelJC;
    private int numeroDeControl;
    private String nombreYapellido;
    private String carrera;
    private String nivel;
    private String mensajeC="";
    public String mensaje = "";


    public PedidoDeLibroDatosDeAlumno() {
        super(new FlowLayout(15, 15, 15));
        this.setPreferredSize(new Dimension(580, 100));

        this.setBackground(Color.WHITE);
        //Border
        Border bor = BorderFactory.createTitledBorder("Datos de Alumno");
        this.setBorder(bor);
        //Numero de Control
        this.numeroDeControlJL = new JLabel("Numero de Control");
        this.numeroDeControlJT = new JTextField(8);
        this.add(this.numeroDeControlJL);
        this.add(this.numeroDeControlJT);
        //Nombre
        this.nombreJL = new JLabel("Nombre");
        this.nombreJT = new JTextField(15);
        this.add(this.nombreJL);
        this.add(this.nombreJT);
        //Separacion
        JPanel sep = new JPanel();
        sep.setPreferredSize(new Dimension(100, 5));
        sep.setBackground(Color.WHITE);
        this.add(sep);
        //Carrera
        String carreras []={"ING. SISTEMAS COMPUTACIONALES",
                            "ING. INFORMATICA",
                            "ING. ADMINISTRACION",
                            "ING. MECATRONICA",
                            "ING. ELECTRONICA",
                            "ING. ELECTRICA",
                            "ING. BIOQUIMICA",
                            "ING. PETROLERA",
                            "ING. GESTION EMPRESARIAL",
                            "ING. MECANICA",
                            "ING. INDUSTRIAL"
                           };
        this.carreraJL = new JLabel("Carrera del Alumno");
        this.carreraJC = new JComboBox(carreras);
        this.add(this.carreraJL);
        this.add(this.carreraJC);
        //Grado y grupo
        String nivel []= {"1 A",
                          "1 B",
                          "2 A",
                          "2 B",
                          "3 A",
                          "3 B",
                          "4 A",
                          "4 B",
                          "5 A",
                          "5 B",
                          "6 A",
                          "6 B",
                          "7 A",
                          "7 B",
                          "8 A",
                          "8 B",
                          "9 A",
                          "9 B"
                          };
        this.nivelJL = new JLabel("Grado y Grupo");
        this.nivelJC = new JComboBox(nivel);
        this.add(this.nivelJL);
        this.add(this.nivelJC);

        //Evento
        this.numeroDeControlJT.addKeyListener(this);
    }
    @Override
    public void keyTyped(KeyEvent evt)
    {

    }
    @Override
    public void keyReleased(KeyEvent evt)
    {
        if(evt.getKeyCode() == evt.VK_ENTER)
        {
            System.out.println("enter");

        }else if(evt.getKeyCode() == evt.VK_BACK_SPACE)
        {
            System.out.println("BORRRA");

        }else if(this.numeroDeControlJT.getText().matches("[0-9]{1,8}"))
        {
            if(this.numeroDeControlJT.getText().length() == 8)
            {
                this.numeroDeControl = Integer.parseInt(this.numeroDeControlJT.getText());
                this.mensajeC +="*¿El numero de control es correcto?\n"+this.numeroDeControl;
            }
        }
        else
        {
            JOptionPane.showMessageDialog(null,
                    "Error, el campo del ID es numerico\nSolo se permiten 8 caracteres",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            this.numeroDeControlJT.setText("");
        }

    }
    @Override
    public void keyPressed(KeyEvent evt)
    {

    }
    public int getNumeroC()
    {
        return this.numeroDeControl;
    }

    public String getNombreYapellido(){ return this.nombreYapellido; }
    public String getCarrera()
    {
        return this.carrera;
    }
    public String getNivel()
    {
        System.out.println(nivel);
        return this.nivel;
    }
    public String getMensaje(){ return this.mensaje; }
    public void setMnesaje()
    {
        this.mensaje="";
        this.mensajeC="";
    }
    public boolean comprobarElmentos()
    {
        this.mensaje+=this.mensajeC+"\n";
        if(comprobarNombreApellido()){
            this.carrera = (String)this.carreraJC.getSelectedItem();
            this.mensaje +="\n*¿La carrera seleccionada es correcta?\n"+this.carrera;
            this.nivel = (String)this.nivelJC.getSelectedItem();
            this.mensaje +="\n*¿Nivel academico es correcto?\n"+this.nivel;
            return true;
        }
        else return false;
    }
    private boolean comprobarNombreApellido()
    {
        this.nombreYapellido = this.nombreJT.getText().toUpperCase();
        if(this.nombreYapellido.matches
                ("[A-Z\u00D1\u00F1\u00E1\u00E9\u00ED\u00F3\u00FA \u00C1\u00C9\u00CD\u00D3\u00DA ]+")
                &&
                this.nombreYapellido.length() <= 50
                )
        {
            this.mensaje +="*¿El nombre es correcto?\n"+this.nombreYapellido;
            return true;
        }else return false;
    }

}
