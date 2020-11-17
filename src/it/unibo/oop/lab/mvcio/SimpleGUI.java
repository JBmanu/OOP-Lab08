package it.unibo.oop.lab.mvcio;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {
    private final JFrame frame;

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) It has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextArea with a button "Save" right
     * below (see "ex02.png" for the expected result). SUGGESTION: Use a JPanel with
     * BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The program asks the controller to save the file if the button "Save" gets
     * pressed.
     * 
     * Use "ex02.png" (in the res directory) to verify the expected aspect.
     */

    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUI(final Controller control) {
        this.frame = new JFrame("The graphical interface");
        
        final JPanel panel = new JPanel();
        final JTextArea textArea = new JTextArea();
        final JButton button = new JButton("SAVE");
        
        panel.setLayout(new BorderLayout());
        this.frame.getContentPane().add(panel);

        panel.add(textArea, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        
        button.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                try {
                    control.printStringInCurrentFile(textArea.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
    }
    
   private void display() {
       final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
       final int sw = (int) screen.getWidth();
       final int sh = (int) screen.getHeight();
       
       frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(sw / 2, sh / 2);
       frame.setLocationByPlatform(true);
       frame.setVisible(true);
    }
    
    public static void main(final String... args) {
        new SimpleGUI(new Controller()).display();;
     }

}
