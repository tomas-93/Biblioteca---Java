package com.diseño;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.Border;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.sql.Connection;
import com.mysql.TransaccionesYConsultas;
import com.mysql.Conexion;

/**
 * Created by thomas on 22/11/2014.
 */
public class Consultas extends JPanel implements ActionListener
{
    private final String [] nombreColumnasPedidos =
            {"Numero de pedido","Fecha del pedido","Titulo","Autor del libro","Numero de Control","Nombre y Apellidos","Carrera",};
    private final String [] nombreColumnasLibros =
            {"ID del Libro","Titulo","Editorial","Autor","Direccion","Fecha de creacion"};
    private final String [] nombreColumnasAdmi =
            {"ID", "Nombre","Cargo"};
    private JLabel consultaJL;
    private JComboBox entradaJC;
    private JButton buscarJB;
    private JTable tablaJT;
    private DefaultTableModel modelo;
    private JMenuItem item1;
    static JPopupMenu pop;
    private int size;
    private final Connection conec = Conexion.getConexion();

    public Consultas()
    {
        //Herencia Panel
        this.setLayout(new FlowLayout(15));
        this.setPreferredSize(new Dimension(625, 220));
        this.setBackground(Color.WHITE);
        Border borde = BorderFactory.createTitledBorder("Consultas");
        this.setBorder(borde);

        //Primera Fila JPanel
        JPanel primeraFila = new JPanel(new FlowLayout(15,15,15));
        primeraFila.setBackground(Color.WHITE);
        String [] entradaArray = {"Pedido de Libros","Libros","Administrador"};

        this.consultaJL = new JLabel("Consultas");
        this.entradaJC = new JComboBox(entradaArray);
        this.entradaJC.setEditable(true);
        this.buscarJB = new JButton("Consultar");

        primeraFila.add(this.consultaJL);
        primeraFila.add(this.entradaJC);
        primeraFila.add(this.buscarJB);

        //Agregar panel primera fila
        this.add(primeraFila);

        //Tabla
        String [] defecto = {"  ","  ","  ","   "};
        Object[][] defectox = {
                {"  ","  ","  ","   "},
                {"  ","  ","  ","   "},
                {"  ","  ","  ","   "},
                {"  ","  ","  ","   "},
                {"  ","  ","  ","   "},
                {"  ","  ","  ","   "},
                {"  ","  ","  ","   "},
                {"  ","  ","  ","   "}
        };
        this.modelo = new DefaultTableModel(defectox, defecto)
        {
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        this.tablaJT = new JTable(modelo);
        this.tablaJT.setPreferredScrollableViewportSize(new Dimension(587,100));
        this.tablaJT.setFillsViewportHeight(true);
        this.tablaJT.getTableHeader().setReorderingAllowed(false);


        JScrollPane barra = new JScrollPane(this.tablaJT);
        this.add(barra);

        //POPMENU
        item1= new JMenuItem("Eliminar Fila");
        item1.addActionListener(this);

        pop= new JPopupMenu();

        MouseListener popListener = new PopupListener();

        pop.add(item1);

        this.tablaJT.addMouseListener(popListener);

        //eventos
        this.entradaJC.addActionListener(this);
        this.buscarJB.addActionListener(this);

        this.size=0;

    }
    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getSource() == this.entradaJC)
            this.consulta(this.entradaJC.getSelectedItem());
        else if(e.getSource() == this.buscarJB)
            this.consulta(this.entradaJC.getSelectedItem());
        else if(e.getSource() == this.item1)
        {
            this.eliminar();
        }
    }
    private void consulta(Object obj)
    {
        //"Pedido de Libros","Libros Fisico","Libros Digitales","Administrador"
        if(obj.equals("Pedido de Libros"))
        {
            this.size=1;
            this.agregarTabla(
                    TransaccionesYConsultas.
                            getConsultaTablaPedido(this.conec),
                    this.nombreColumnasPedidos);

        }else if(obj.equals("Libros"))
        {
            this.size=2;
            this.agregarTabla(
                    TransaccionesYConsultas.
                            getConsultaTablaLibro(this.conec),
                    this.nombreColumnasLibros);

        }else if(obj.equals("Administrador"))
        {
            this.size=3;
            this.agregarTabla(
                    TransaccionesYConsultas.
                            getCosultaTablaEmpleado(this.conec),
                    this.nombreColumnasAdmi);
        }else{}
    }
    private void agregarTabla(ArrayList<String[]> lista,String[] array)
    {
        DefaultTableModel sub =new DefaultTableModel(){
        public boolean isCellEditable(int rowIndex, int vColIndex) {
            return false;
        }};
        sub.setColumnIdentifiers(array);
        for(int i = 0; i < lista.size(); i++)
        {
            sub.addRow(lista.get(i));
        }
        this.tablaJT.setModel(sub);


    }
    private void eliminar()
    {
        int i = this.tablaJT.getSelectedRow();
        if (this.size == 1) {
            if (TransaccionesYConsultas.eliminarPedido(this.conec,
                    this.tablaJT.getValueAt(i, 0),
                    this.tablaJT.getValueAt(i, 4)))
            {
                JOptionPane.showMessageDialog(null, "Se elimino la fila", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                this.size=1;
                this.agregarTabla(
                        TransaccionesYConsultas.
                                getConsultaTablaPedido(this.conec),
                        this.nombreColumnasPedidos);
            }
        } else if (this.size == 2) {
            if (TransaccionesYConsultas.eliminarTablasLibro(this.conec, this.tablaJT.getValueAt(i, 0)))
            {
                JOptionPane.showMessageDialog(null, "Se elimino la fila", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                this.size=2;
                this.agregarTabla(
                        TransaccionesYConsultas.
                                getConsultaTablaLibro(this.conec),
                        this.nombreColumnasLibros);
            }
        } else if (this.size == 3) {
            if (TransaccionesYConsultas.eliminarTablaAdm(this.conec, this.tablaJT.getValueAt(i, 0)))
            {
                JOptionPane.showMessageDialog(null, "Se elimino la fila", "Confirmacion", JOptionPane.INFORMATION_MESSAGE);
                this.agregarTabla(
                        TransaccionesYConsultas.
                                getCosultaTablaEmpleado(this.conec),
                        this.nombreColumnasAdmi);
            }
        }
        this.tablaJT.repaint();
    }

}


class PopupListener extends MouseAdapter{

    public void mousePressed(MouseEvent e) {
        maybeShowPopup(e);
    }

    public void mouseReleased(MouseEvent e) {
        maybeShowPopup(e);
    }

    private void maybeShowPopup(MouseEvent e) {
        if (e.isPopupTrigger()) {
            Consultas.pop.show(e.getComponent(),
                    e.getX(), e.getY());
        }

    }


}
