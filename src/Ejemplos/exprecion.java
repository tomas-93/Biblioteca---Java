package Ejemplos;

/**
 * Created by thomas on 01/12/2014.
 */
public class exprecion
{
    public static void main(String [] args)
    {
        String cadena ="    1 2 2 5 4 7 8    ";
        String cadena1 = "tomas yussef";
        System.out.println(cadena);
        System.out.println(cadena.replaceAll(" ",""));
        System.out.println(cadena1.toUpperCase().matches("[A-Z\\u00D1\\u00F1\\u00E1\\u00E9\\u00ED\\u00F3\\u00FA \\u00C1\\u00C9\\u00CD\\u00D3\\u00DA ]+"));

        char [] a ={'a','b','c'};
        System.out.println(a.toString());
    }
}
