package it.unibo.oop.lab.mvcio2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.*;

import it.unibo.oop.lab.mvcio.Controller;
/**
 * A very simple program using a graphical interface.
 * 
 */
public final class SimpleGUIWithFileChooser {
    private JFrame frame;
    
    public SimpleGUIWithFileChooser(final Controller control) {
        this.frame = new JFrame("The graphical interface");
        final JPanel canvasAll = new JPanel();
        canvasAll.setLayout(new BorderLayout());
        this.frame.getContentPane().add(canvasAll);
        
        final JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        
        final JTextArea filePath = new JTextArea(control.getCurrentFilePath());
        panel.add(filePath, BorderLayout.CENTER);
        filePath.setBackground(Color.LIGHT_GRAY); 
        filePath.setEditable(false);
        
        final JButton buttonBrowse = new JButton("Browse");
        panel.add(buttonBrowse, BorderLayout.EAST);
        
        final JButton buttonSave = new JButton("SAVE");
        canvasAll.add(buttonSave, BorderLayout.SOUTH);
        
        final JTextArea textArea = new JTextArea();
        canvasAll.add(textArea, BorderLayout.CENTER);

        canvasAll.add(panel, BorderLayout.NORTH);
                
        buttonSave.addActionListener(new ActionListener() {
            public void actionPerformed(final ActionEvent event) {
                try {
                    control.printStringInCurrentFile(textArea.getText());
                } catch (IOException e) {
                    JOptionPane.showMessageDialog(null, e.getMessage(), "An error occurred", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        buttonBrowse.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JFileChooser browserFile = new JFileChooser();
                browserFile.setSelectedFile(control.getCurrentFile());
                final int result = browserFile.showSaveDialog(frame);
                switch(result) {
                    case JFileChooser.APPROVE_OPTION:
                        final File newFile = browserFile.getSelectedFile();
                        control.setCurrentFile(newFile);
                        filePath.setText(newFile.getPath()); break;
                    case JFileChooser.CANCEL_OPTION: break;
                    default:
                        JOptionPane.showMessageDialog(frame, result, "Meh!", JOptionPane.ERROR_MESSAGE);         
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
        new SimpleGUIWithFileChooser(new Controller()).display();;
     }

    /*
     * TODO: Starting from the application in mvcio:
     * 
     * 1) Add a JTextField and a button "Browse..." on the upper part of the
     * graphical interface.
     * Suggestion: use a second JPanel with a second BorderLayout, put the panel
     * in the North of the main panel, put the text field in the center of the
     * new panel and put the button in the line_end of the new panel.
     * 
     * 2) The JTextField should be non modifiable. And, should display the
     * current selected file.
     * 
     * 3) On press, the button should open a JFileChooser. The program should
     * use the method showSaveDialog() to display the file chooser, and if the
     * result is equal to JFileChooser.APPROVE_OPTION the program should set as
     * new file in the Controller the file chosen. If CANCEL_OPTION is returned,
     * then the program should do nothing. Otherwise, a message dialog should be
     * shown telling the user that an error has occurred (use
     * JOptionPane.showMessageDialog()).
     * 
     * 4) When in the controller a new File is set, also the graphical interface
     * must reflect such change. Suggestion: do not force the controller to
     * update the UI: in this example the UI knows when should be updated, so
     * try to keep things separated.
     */

}
