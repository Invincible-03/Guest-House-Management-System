package ui;

import java.awt.*;
import javax.swing.*;

public class RoundedButton extends JButton {

    public RoundedButton(String text) {
        super(text);

        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);

        setForeground(Color.WHITE);

        setFont(new Font("Segoe UI", Font.BOLD, 16));

        setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    protected void paintComponent(Graphics g) {

        Graphics2D g2 = (Graphics2D) g.create();

        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        if(getModel().isPressed())
            g2.setColor(getBackground().darker());
        else if(getModel().isRollover())
            g2.setColor(getBackground().brighter());
        else
            g2.setColor(getBackground());

        g2.fillRoundRect(0,0,getWidth(),getHeight(),30,30);

        super.paintComponent(g);

        g2.dispose();

    }

    @Override
    protected void paintBorder(Graphics g) {
    }
}
