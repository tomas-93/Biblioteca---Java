package com.diseño;
import javax.swing.JPanel;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;

/**
 * Created by thomas on 22/11/2014.
 */
public class AnimacionSuperior extends JPanel
{
    private JPanel p1 = new JPanel();
    private JPanel p2 = new JPanel();

    public AnimacionSuperior()
    {
        this.setBackground(Color.WHITE);
        Dimension size = new Dimension(625,80);
        this.setPreferredSize(size);
        Border bor = BorderFactory.createCompoundBorder(
                BorderFactory.createRaisedBevelBorder(),
                BorderFactory.createLoweredBevelBorder());
        this.setBorder(bor);
    }

}
