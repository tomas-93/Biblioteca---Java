package com.diseño;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.sql.Connection;
import com.mysql.Conexion;
import com.mysql.TransaccionesYConsultas;




/**
 * Created by thomas on 26/11/2014.
 */
public class PedidoDeLibroDatosDePrestamo extends JPanel
{
    private JLabel idPedidoJL,fechaJL,horaJL;
    private JTextField idPedidoJT,fechaJT,horaJT;
    private String idpedido;
    private int id;
    private final Connection conec  = Conexion.getConexion();

    public PedidoDeLibroDatosDePrestamo()
    {
        super(new FlowLayout(15,15,15));
        this.setBackground(Color.WHITE);
        this.setPreferredSize(new Dimension(580, 100));
        Border bor = BorderFactory.createTitledBorder("Infomarcion de Prestamo");
        this.setBorder(bor);
        //PEDIDO
        this.idPedidoJL = new JLabel("ID de Pedido");
        this.idPedidoJT = new JTextField (8);
        this.idPedidoJT.setEditable(false);
        this.id=Integer.parseInt(this.consulta());
        this.idPedidoJT.setText(this.consulta());
        this.add(this.idPedidoJL);
        this.add(this.idPedidoJT);
        //fecha
        this.fechaJL = new JLabel("Fecha Actual");
        this.fechaJT = new JTextField(10);
        this.fechaJT.setEditable(false);
        this.fechaJT.setText(fechaActual());
        this.add(this.fechaJL);
        this.add(fechaJT);
        //Separacion
        JPanel sep = new JPanel();
        sep.setPreferredSize(new Dimension(150,5));
        sep.setBackground(Color.WHITE);
        this.add(sep);

        //hora
        this.horaJL = new JLabel("Hora Actual");
        this.horaJT = new JTextField(10);
        this.horaJT.setEditable(false);
        this.horaJT.setText(horaActual());
        this.add(horaJL);
        this.add(horaJT);

        //llamada del metodo
        //this.idPedidoJT.setText(String.valueOf(getDbID()));
        this.idpedido = this.idPedidoJT.getText();
    }
    private String fechaActual()
    {
        SimpleDateFormat  formato  = new SimpleDateFormat("dd/mm/yy");
        return formato.format(new Date());
    }
    private String horaActual()
    {
        SimpleDateFormat  formato  = new SimpleDateFormat("hh:mm:ss");
        return formato.format(new Date());
    }
    public int getID()
    {
        return this.id;
    }
    public String getTime()
    {
        return this.horaActual()+" - "+this.fechaActual();
    }
    private String consulta()
    {
        return TransaccionesYConsultas.getIdPrestamo(this.conec);
    }
}
