package Ejemplos;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import java.io.*;
import java.nio.channels.FileChannel;
/**
 * Created by thomas on 06/12/2014.
 */
public class filechois
{
    public static void main(String [] args)
    {
        File ruta;
        int opcion;
        JFileChooser jfc = new JFileChooser();
        jfc.setDialogTitle("Guardar Archivo");
        jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        jfc.setAcceptAllFileFilterUsed(false);
        opcion = jfc.showOpenDialog(null);
        if(opcion == JFileChooser.APPROVE_OPTION)
        {
            ruta = jfc.getSelectedFile();//getCurrentDirectory();
            System.out.println(ruta.getAbsolutePath());

        }else
        {
            ruta = null;
        }
        File sourceFile=ruta.getAbsoluteFile();
        File destFile = new File("..\\b\\"+ruta.getName());
        FileChannel origen = null;
        FileChannel destino = null;
        try {
            origen = new FileInputStream(sourceFile).getChannel();
            destino = new FileOutputStream(destFile).getChannel();
            long count = 0;
            long size = origen.size();
            while((count += destino.transferFrom(origen, count, size-count))<size);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

        }


    }
}
