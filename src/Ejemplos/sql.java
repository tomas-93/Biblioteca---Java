package Ejemplos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Created by thomas on 05/12/2014.
 */
public class sql {
    public static void main(String [] argas)
    {
        try
        {
            //Connection conec = com.mysql.Conexion.getConexion();

            String n = "12345";
            String pass = "_thomas";
            String user = "root";
            String dri = "jdbc:mysql://localhost/itesco";
            Class.forName("com.mysql.jdbc.Driver");
            Connection conec = DriverManager.getConnection(dri,user,pass);

            PreparedStatement sql = conec.prepareStatement(
                    "select pass from empleado where pass = "+n);
            ResultSet respuesta = sql.executeQuery();
            if(respuesta.next())System.out.println(respuesta.getString("pass"));

            //System.out.println(respuesta.getString("id"));
            /*Prubea del id  de la funcion MAX() para verificar si la tabla tiene campos ingresados

            int n =0;
            if(respuesta.next()){
                if(respuesta.getObject("id") == null)
                {
                    n = respuesta.getInt("id")+1;
                    System.out.println(n);
                }
                else System.out.println(++n);

            }else{
                System.out.println("Error");
            }*/



        }catch(Exception e)
        {
            System.out.println("Error\n"+e.getLocalizedMessage());

        }


    }
}
