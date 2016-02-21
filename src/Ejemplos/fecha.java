package Ejemplos;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
 * Created by thomas on 26/11/2014.
 */
public class fecha
{
    public static void main(String [] margs)
    {
        Date fecha = new Date();
        SimpleDateFormat formatoF = new SimpleDateFormat("dd/mm/yy");
        SimpleDateFormat formato = new SimpleDateFormat("hh:mm:ss");
        System.out.println(formatoF.format(fecha));
        System.out.println(formato.format(fecha));

    }
}
