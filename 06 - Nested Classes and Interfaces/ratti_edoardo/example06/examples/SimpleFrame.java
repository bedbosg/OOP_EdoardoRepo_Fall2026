package example06.examples;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleFrame extends JFrame {
    private final JLabel label = new JLabel("Idle");

    public SimpleFrame() {
        JButton btn = new JButton("Click me");

        btn.addActionListener(new ActionListener() {
                                  @Override
                                  public void actionPerformed(ActionEvent e) {
                                      label.setText("Status: clicked!");
                                  }
                              }
        );

        setLayout(new FlowLayout());
        add(label);
        add(btn);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new SimpleFrame();
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
    }
}
