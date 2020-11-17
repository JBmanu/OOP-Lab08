package it.unibo.oop.lab.mvc;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUI {

    private final JFrame frame = new JFrame();
    private final Controller controller;

    /*
     * Once the Controller is done, implement this class in such a way that:
     * 
     * 1) I has a main method that starts the graphical application
     * 
     * 2) In its constructor, sets up the whole view
     * 
     * 3) The graphical interface consists of a JTextField in the upper part of the frame, 
     * a JTextArea in the center and two buttons below it: "Print", and "Show history". 
     * SUGGESTION: Use a JPanel with BorderLayout
     * 
     * 4) By default, if the graphical interface is closed the program must exit
     * (call setDefaultCloseOperation)
     * 
     * 5) The behavior of the program is that, if "Print" is pressed, the
     * controller is asked to show the string contained in the text field on standard output. 
     * If "show history" is pressed instead, the GUI must show all the prints that
     * have been done to this moment in the text area.
     * 
     */

    /**
     * builds a new {@link SimpleGUI}.
     */
    public SimpleGUI(final Controller controller) {
        this.controller = controller;
        
        JPanel containAll = new JPanel();
        containAll.setLayout(new BorderLayout());
        frame.setContentPane(containAll);
        
        JPanel containJButton = new JPanel();
        containJButton.setLayout(new FlowLayout());  
        JPanel containText = new JPanel();
        containText.setLayout(new BoxLayout(containText, BoxLayout.X_AXIS));
        
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        JTextField textField = new JTextField();
        textField.setBackground(Color.lightGray);
        JButton printButton = new JButton("Print");
        JButton historyButton = new JButton("Show history");
        
        containText.add(textArea);
        containJButton.add(printButton);
        containJButton.add(historyButton);
        
        containAll.add(textField, BorderLayout.NORTH);
        containAll.add(containText, BorderLayout.CENTER);
        containAll.add(containJButton, BorderLayout.SOUTH);
        
        printButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SimpleGUI.this.controller.setNextStringToPrint(textField.getText());
                SimpleGUI.this.controller.printCurrentString();
                textField.setText("");
            }
        });
        
        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final StringBuilder text = new StringBuilder();
                final List<String> history = SimpleGUI.this.controller.getPrintedStringsHistory();
                history.forEach(value -> text.append(value + " "));
                
                if (!history.isEmpty()) {
                    text.deleteCharAt(text.length() - 1);
                }
                textArea.setText(text.toString());
            }           
        });
    }
    
    private void display() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        final Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        final int sw = (int) screen.getWidth();
        final int sh = (int) screen.getHeight();
        frame.setSize(sw / 2, sh / 2);
        frame.setLocationByPlatform(true);
        frame.setVisible(true);
    }
    
    public static void main(final String[] args) {
        new SimpleGUI(new SimpleController()).display();
    }

}
