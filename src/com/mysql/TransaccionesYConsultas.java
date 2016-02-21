package com.mysql;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

/**
 * Created by thomas on 30/11/2014.
 */
public class TransaccionesYConsultas
{
    public static boolean trsaccionAlumno(Connection conec, int numeroCT,
                                          String nombreYapellido, String carrera,
                                          String nivel )
    {
        boolean bandera = false;
        final String sentencia = "INSERT INTO alumno VALUE("
                +numeroCT+",'"
                +nombreYapellido+"','"
                +carrera+"','"
                +nivel+"')";
        System.out.println(sentencia);
            try
            {
            conec.setAutoCommit(false);
            PreparedStatement prepararDeclaracion;
            prepararDeclaracion = conec.prepareStatement(sentencia);
            prepararDeclaracion.execute();
            bandera =true;
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
                                          int id,
                                          String titulo, String editorial,
                                          String autor, String direccion,
                                          String fecha)
    {
        boolean bandera = false;
        final String sentencia = "INSERT INTO libro("+
                "id_libro, titulo, editorial, autor,url, fecha"
                +") VALUE("+id+",'"
                +titulo+"','"
                +editorial+"','"
                +autor+"','"
                +direccion+"','"
                +fecha+"')";
        try
        {
            conec.setAutoCommit(false);
            PreparedStatement prepararDeclaracion;
            prepararDeclaracion = conec.prepareStatement(sentencia);
            prepararDeclaracion.execute();
            bandera = true;
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

    public static boolean trsaccionPrestamo(Connection conec,int idPrest,
                                         int idAlm, int idLibr,
                                         String fecha)
    {
        boolean bandera = false;
        final String sentencia = "INSERT INTO prestamo (id_prestamo, id_alumno,id_libro,fecha)" +
                "VALUE("+idPrest+","+idAlm+","+idLibr+",'"+fecha+"')";

        try
        {
            conec.setAutoCommit(false);
            PreparedStatement prepararDeclaracion;
            prepararDeclaracion = conec.prepareStatement(sentencia);
            prepararDeclaracion.execute();
            bandera = true;
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
            int id,
            String nombre,
            String cargo,
            String passw
    )
    {
        String sql ="INSERT INTO empleado(idempleado ,nombre, cargo, pass) " +
                "VALUE("+id+",'"+nombre+"','"+cargo+"','"+passw+"')";
        boolean bandera = false;
        try
        {
            conec.setAutoCommit(false);
            PreparedStatement prepararSentencia = conec.prepareStatement(sql);
            prepararSentencia.execute();
            bandera = true;
            conec.commit();

        }catch(Exception e)
        {
            if(bandera == false)
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
        String id = "", sql ="SELECT MAX(idempleado) AS id FROM empleado ";
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
    public static String getIdPrestamo(Connection conec)
    {
        String id ="", sql = "SELECT MAX(id_prestamo) AS id FROM prestamo";
        try
        {
            conec.setAutoCommit(false);
            PreparedStatement prepararSentencia = conec.prepareStatement(sql);
            ResultSet resultadoSql = prepararSentencia.executeQuery();
            System.out.println("se jecuto");
            if(resultadoSql.next())
                if(resultadoSql.getObject("id") == null)
                    id = "1";
                else id = resultadoSql.getString("id");
            conec.commit();


        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "Error en la Consulta de id prestamo\n"+
                            e.getLocalizedMessage(),
                    "Error :(",
                    JOptionPane.ERROR_MESSAGE);
            conec.rollback();

        }finally
        {
            return id;
        }

    }

    public static ArrayList<String[]> getCosultaTablaEmpleado(
            Connection conec)
    {
        String sql = "SELECT * FROM empleado";
        String [] filas = new String[3];
        int i =0;
        ArrayList<String[]> tablasEmpleado = new ArrayList<String[]>();
        try
        {
            conec.setAutoCommit(false);
            PreparedStatement prepararSentencia = conec.prepareStatement(sql);
            ResultSet resultadoSql = prepararSentencia.executeQuery();

            while(resultadoSql.next())
            {
                filas[0] = resultadoSql.getString("idempleado");
                filas[1] = resultadoSql.getString("nombre");
                filas[2] = resultadoSql.getString("cargo");
                tablasEmpleado.add(filas);
                filas = new String[3];
            }
            conec.commit();

        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "Error en la Consulta de tabla empleado\n"+
                            e.getLocalizedMessage(),
                    "Error :(",
                    JOptionPane.ERROR_MESSAGE);
            conec.rollback();

        }finally
        {
            return tablasEmpleado;
        }

    }
    public static String [] getTituloYEditorial(Connection conec, int n)
    {
        String [] respuesta = new String [2];
        String sql = "SELECT titulo, autor FROM libro WHERE id_libro ="+n;

        try
        {
            conec.setAutoCommit(false);
            PreparedStatement sentencia = conec.prepareStatement(sql);
            ResultSet resultado = sentencia.executeQuery();
            if(resultado.next())
            {
                respuesta[0] = resultado.getString("titulo");
                respuesta[1] = resultado.getString("autor");
            }
            conec.commit();

        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "Error en la Consulta de editorial y libro\n"+
                            e.getLocalizedMessage(),
                    "Error :(",
                    JOptionPane.ERROR_MESSAGE);
            conec.rollback();

        }finally
        {
            return respuesta ;
        }

    }

    public static ArrayList<String[]> getConsultaTablaLibro(
            Connection conec)
    {
        String sql = "SELECT * FROM libro";
        String [] respuesta  = new String[6];
        ArrayList <String []> filas = new ArrayList<String[]>();

        try
        {
            conec.setAutoCommit(false);
            PreparedStatement sentencia = conec.prepareStatement (sql);
            ResultSet sqlResp = sentencia.executeQuery();
            while(sqlResp.next())
            {
                respuesta [0] = sqlResp.getString("id_libro");
                respuesta [1] = sqlResp.getString("titulo");
                respuesta [2] = sqlResp.getString("editorial");
                respuesta [3] = sqlResp.getString("autor");
                respuesta [4] = sqlResp.getString("url");
                respuesta [5] = sqlResp.getString("fecha");
                filas.add(respuesta);
                respuesta = new String[6];
            }
            conec.commit();

        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "Error en la Consulta de la tabla del linbro\n"+
                            e.getLocalizedMessage(),
                    "Error :(",
                    JOptionPane.ERROR_MESSAGE);
            conec.rollback();

        }finally
        {
            return filas;
        }

    }

    public static ArrayList<String []> getConsultaTablaPedido(
            Connection conec)
    {
        String sql = "select  id_prestamo, prestamo.fecha,titulo,autor,alumno.id_alumno ,nombre, carrera " +
                "from prestamo " +
                "inner join alumno " +
                "inner join libro " +
                "on prestamo.id_alumno = alumno.id_alumno and prestamo.id_libro = libro.id_libro order by id_prestamo";
        String [] respuesta = new String [7];
        ArrayList <String[]> filas = new ArrayList<String []>();
        try
        {
            conec.setAutoCommit(false);
            PreparedStatement sentencia = conec.prepareStatement(sql);
            ResultSet respuestaSql = sentencia.executeQuery();
            while(respuestaSql.next())
            {
                respuesta[0] = respuestaSql.getString("id_prestamo");
                respuesta[1] = respuestaSql.getString("fecha");
                respuesta[2] = respuestaSql.getString("titulo");
                respuesta[3] = respuestaSql.getString("autor");
                respuesta[4] = respuestaSql.getString("id_alumno");
                respuesta[5] = respuestaSql.getString("nombre");
                respuesta[6] = respuestaSql.getString("carrera");
                filas.add(respuesta);
                respuesta = new String[7];
            }
            conec.commit();

        }catch (Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "Error en la Consulta de la tabla pedido\n"+
                            e.getLocalizedMessage(),
                    "Error :(",
                    JOptionPane.ERROR_MESSAGE);
            conec.rollback();

        }finally
        {
            return filas;
        }

    }

    public static boolean eliminarPedido(Connection conec, Object referencia, Object referencia2)
    {
        String sql1 = "DELETE FROM itesco.prestamo WHERE id_prestamo = " +String.valueOf(referencia),
                sql2 ="DELETE FROM itesco.alumno WHERE id_alumno = "+String.valueOf(referencia2) ;
        boolean bandera= false;
        try
        {
            conec.setAutoCommit(false);
            PreparedStatement sentencia = conec.prepareStatement(sql1);
            sentencia.execute();
            sentencia = conec.prepareStatement(sql2);
            sentencia.execute();
            bandera = true;
            conec.commit();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "Error en la eliminacion de la tabla\n"+
                            e.getLocalizedMessage(),
                    "Error :(",
                    JOptionPane.ERROR_MESSAGE);
            conec.rollback();
        }finally {
            return bandera;
        }

    }

    public static boolean eliminarTablasLibro(Connection conec, Object referencia)
    {
        String sql1 = "DELETE FROM itesco.libro WHERE id_libro = " +String.valueOf(referencia);
        boolean bandera= false;
        try
        {
            conec.setAutoCommit(false);
            PreparedStatement sentencia = conec.prepareStatement(sql1);
            sentencia.execute();
            bandera = true;
            conec.commit();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "Error en la eliminacion de la tabla\n"+
                            e.getLocalizedMessage(),
                    "Error :(",
                    JOptionPane.ERROR_MESSAGE);
            conec.rollback();
        }finally {
            return bandera;
        }
    }
    public static boolean eliminarTablaAdm(Connection conec, Object referencia)
    {
        String sql1 = "DELETE FROM itesco.empleado WHERE idempleado = " +String.valueOf(referencia);
        boolean bandera= false;
        try
        {
            conec.setAutoCommit(false);
            PreparedStatement sentencia = conec.prepareStatement(sql1);
            sentencia.execute();
            bandera = true;
            conec.commit();
        }catch(Exception e)
        {
            JOptionPane.showMessageDialog(null,
                    "Error en la eliminacion de la tabla\n"+
                            e.getLocalizedMessage(),
                    "Error :(",
                    JOptionPane.ERROR_MESSAGE);
            conec.rollback();
        }finally {
            return bandera;
        }
    }





}
