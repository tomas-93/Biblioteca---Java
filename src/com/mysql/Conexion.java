package com.mysql;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 * Created by thomas on 30/11/2014.
 */
public class Conexion
{
    public static Connection getConexion()
    {
        final String USER ="root";
        final String PASSWORD ="1234";
        final String SERVIDOR ="jdbc:mysql://localhost:3306/itesco";
        Connection conec = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            conec = DriverManager.getConnection(SERVIDOR,USER,PASSWORD);

        }catch (ClassNotFoundException e)
        {
            JOptionPane.showMessageDialog(null, e, "Error1 en la Conexi?n con la BD "
                    +e.getMessage(), JOptionPane.ERROR_MESSAGE);
            conec=null;
        }catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error2 en la Conexi?n con la BD "
                    +ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conec=null;
        }
        catch(Exception ex)
        {
            JOptionPane.showMessageDialog(null, ex, "Error3 en la Conexi?n con la BD "
                    +ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conec=null;
        }finally
        {
            return conec;
        }
    }
}
