package ui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class RoundedTextField extends JTextField {

    public RoundedTextField() {

        setOpaque(false);

        setBorder(new EmptyBorder(10,15,10,15));

        setFont(new Font("Segoe UI",Font.PLAIN,15));

    }

    @Override
    protected void paintComponent(Graphics g){

        Graphics2D g2=(Graphics2D)g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Color.WHITE);

        g2.fillRoundRect(0,0,getWidth(),getHeight(),20,20);

        super.paintComponent(g);

        g2.dispose();

    }

    @Override
    protected void paintBorder(Graphics g){

        Graphics2D g2=(Graphics2D)g.create();

        g2.setColor(new Color(200,200,200));

        g2.drawRoundRect(0,0,getWidth()-1,getHeight()-1,20,20);

        g2.dispose();

    }

}