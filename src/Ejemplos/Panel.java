package Ejemplos;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by thomas on 29/11/2014.
 */
public class Panel extends JFrame implements ActionListener
{
    private JPanel pane;
    private JLabel etiq;
    private JButton clic;
    private int i = 0;
    public Panel() {
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(100,100);
        this.setLayout(new GridLayout(2,1));

        pane = new JPanel();
        etiq = new JLabel("Indice "+i);
        pane.add(etiq);

        clic = new JButton(("CLIC"));
        this.add(clic);
        this.add(pane);

        clic.addActionListener(this);
        this.setVisible(true);



    }


    @Override
    public void actionPerformed(ActionEvent avt)
    {
        pane.removeAll();
        i++;
        etiq.setText("Indicie"+i);
        pane.add(etiq);
        //pane.updateUI();
        pane.repaint();
        //this.repaint();

    }
    public static void main(String [] args)
    {
        new Panel();
    }
}
