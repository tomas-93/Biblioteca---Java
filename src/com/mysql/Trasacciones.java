package com.mysql;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by thomas on 30/11/2014.

public class Trasacciones
{
    public static boolean trsaccionAlumno(Connection conec, int numeroCT,
                                          String nombreYapellido, String nivel,
                                          String carrera)
    {
        boolean bandera = false;
        final String sentencia = "INSERT INTO alumno VALUE("
                +numeroCT+","
                +nombreYapellido+","
                +carrera+","
                +nivel+")";
            try
            {
            conec.setAutoCommit(false);
            PreparedStatement prepararDeclaracion;
            prepararDeclaracion = conec.prepareStatement(sentencia);
            bandera = prepararDeclaracion.execute();
            conec.commit();
        }catch (Exception e)
        {
            if(!bandera)
            {
                JOptionPane.showMessageDialog(null,
                        "Error en la transaccion alumno\n"+e.getLocalizedMessage(),
                        "Error :(",
                        JOptionPane.ERROR_MESSAGE
                );
                conec.rollback();
            }

        }finally
        {
            return bandera;
        }

    }

    public static boolean trsaccionLibro(Connection conec,
                                          String titulo, String editorial,
                                          String autor, String direccion,
                                          String fecha)
    {
        boolean bandera = false;
        final String sentencia = "INSERT INTO libro("+
                "id_libro, titulo, editorial, autor,url, fecha"
                +") VALUE("
                +titulo+","
                +editorial+","
                +autor+","
                +direccion+","
                +fecha+")";
        try
        {
            conec.setAutoCommit(false);
            PreparedStatement prepararDeclaracion;
            prepararDeclaracion = conec.prepareStatement(sentencia);
            bandera = prepararDeclaracion.execute();
            conec.commit();
        }catch (Exception e)
        {
            if(!bandera)
            {
                JOptionPane.showMessageDialog(null,
                        "Error en la transaccion libro\n"+e.getLocalizedMessage(),
                        "Error :(",
                        JOptionPane.ERROR_MESSAGE
                );
                conec.rollback();
            }

        }finally
        {
            return bandera;
        }

    }

    public static boolean trsaccionPrestamo(Connection conec,
                                         int idAlm, int idLibr,
                                         String fecha)
    {
        boolean bandera = false;
        final String sentencia = "INSERT INTO prestamo (id_alumno,id_libro,fecha)" +
                "VALUE("+idAlm+","+idLibr+","+fecha+")";

        try
        {
            conec.setAutoCommit(false);
            PreparedStatement prepararDeclaracion;
            prepararDeclaracion = conec.prepareStatement(sentencia);
            bandera = prepararDeclaracion.execute();
            conec.commit();
        }catch (Exception e)
        {
            if(!bandera)
            {
                JOptionPane.showMessageDialog(null,
                        "Error en la transaccion prestamo\n"+e.getLocalizedMessage(),
                        "Error :(",
                        JOptionPane.ERROR_MESSAGE
                );
                conec.rollback();
            }

        }finally
        {
            return bandera;
        }

    }
    public static boolean trsaccionEmpleado(
            Connection conec,
            String nombre,
            String cargo,
            String passw
    )
    {
        String sql ="INSERT INTO " +
                "empleado(nombre, cargo, pass) " +
                "VALUE("+nombre+","+cargo+","+passw+")";
        boolean bandera = false;
        try
        {
            conec.setAutoCommit(false);
            PreparedStatement prepararSentencia = conec.prepareStatement(sql);
            bandera = prepararSentencia.execute();
            conec.commit();

        }catch(Exception e)
        {
            if(!bandera)
            {
                JOptionPane.showMessageDialog(null,
                        "Error en la transaccion empleado\n"+e.getLocalizedMessage(),
                        "Error :(",
                        JOptionPane.ERROR_MESSAGE
                );
                conec.rollback();
            }

        }finally
        {
            return bandera;
        }

    }
    public static String getConsultaIDLirbo(Connection conec)
    {
        String id = "", sql ="SELECT MAX(id_libro) AS id FROM libro ";
        int resultadoS=0;
        boolean bandera= false;
        try
        {
            conec.setAutoCommit(false);
            PreparedStatement prepararSQL = conec.prepareStatement(sql);
            ResultSet resultado = prepararSQL.executeQuery();
            if(resultado.next())
            {
                if(resultado.getObject("id") == null)
                    id = String.valueOf(++resultadoS);
                else
                {
                    resultadoS = resultado.getInt("id");
                    resultadoS++;
                    id = String.valueOf(resultadoS);
                }
            }
            conec.commit();

        }catch(SQLException e)
        {
            if(bandera == false)
            {
                JOptionPane.showMessageDialog(null,
                        "Error en la Consulta de ID libro 1\n"
                                +e.getLocalizedMessage(),
                        "Error :(",
                        JOptionPane.ERROR_MESSAGE
                );
                conec.rollback();
            }


        }catch(Exception e)
        {
            if(bandera== false)
            {
                JOptionPane.showMessageDialog(null,
                        "Error en la Consulta de ID libro 2\n"+e.getLocalizedMessage()+e.getMessage()+e.getCause(),
                        "Error :(",
                        JOptionPane.ERROR_MESSAGE
                );
                conec.rollback();
            }

        }finally
        {
            return id;
        }
    }
    public static String getConsultaIDEmpleado(Connection conec)
    {
        String id = "", sql ="SELECT MAX(id) AS id FROM empleado ";
        int resultadoS=0;
        boolean bandera= false;
        try
        {
            conec.setAutoCommit(false);
            PreparedStatement prepararSQL = conec.prepareStatement(sql);
            ResultSet resultado = prepararSQL.executeQuery();
            if(resultado.next())
            {
                if(resultado.getObject("id") == null)
                    id = String.valueOf(++resultadoS);
                else
                {
                    resultadoS = resultado.getInt("id");
                    resultadoS++;
                    id = String.valueOf(resultadoS);
                }
            }
            conec.commit();

        }catch(SQLException e)
        {
            if(bandera == false)
            {
                JOptionPane.showMessageDialog(null,
                        "Error en la Consulta de ID empleado 1\n"
                                +e.getLocalizedMessage(),
                        "Error :(",
                        JOptionPane.ERROR_MESSAGE
                );
                conec.rollback();
            }


        }catch(Exception e)
        {
            if(bandera== false)
            {
                JOptionPane.showMessageDialog(null,
                        "Error en la Consulta de ID empleado 2\n"
                                +e.getLocalizedMessage(),
                        "Error :(",
                        JOptionPane.ERROR_MESSAGE
                );
                conec.rollback();
            }

        }finally
        {
            return id;
        }
    }
    public static String getPassDeEmpleado(Connection conec, String passw)
    {
        String sql = "SELECT pass FROM empleado WHERE pass = "+passw,
               respuesta ="";
        try
        {
            conec.setAutoCommit(false);
            PreparedStatement prepararSentencia = conec.prepareStatement(sql);
            ResultSet  respuestaSql = prepararSentencia.executeQuery();
            if(respuestaSql.next())respuesta += respuestaSql.getString("pass");
            else respuesta ="";
            conec.commit();

        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "Error en la Consulta de la contraseña\n"+
                            e.getLocalizedMessage(),
                    "Error :(",
                    JOptionPane.ERROR_MESSAGE
            );
            conec.rollback();
        }finally
        {
            return respuesta;
        }

    }
}*/
