/**********************************************************
  * Tahmid Munat || Conference Project || Helper class
  * Game directions pane
  *********************************************************/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class Help extends JFrame implements ActionListener{
  
  
  // instance variables
  private JPanel panel1, panel2, panel3;
  private JButton okButton;
  private JTextArea textArea; // holds the words
  private ImageIcon image;    // help icon
  private JLabel label;       // to set the icon
  
  
  // constructor
  public Help(){
    this.label = new JLabel();
    this.textArea = new JTextArea(20 , 30);
    this.okButton = new JButton("OK");
    this.panel1 = new JPanel();
    this.panel2 = new JPanel();
    this.panel3 = new JPanel();
    this.image = new ImageIcon("help.png");
    
    this.textArea.setText("Usage: \n \t W:  move up \n \t S:  move down \n \t A:  move left \n \t D:  move right \n \n" +
                 " Left mouse button: fire bullets.\n\n Right mouse button: power up");   
    this.textArea.setEditable(false);
    this.label.setIcon(this.image);
    this.okButton.addActionListener(this);
       
    this.setResizable(false);
    this.setSize(450,375);
    this.setTitle("Help");
    this.textArea.setBackground(getBackground());
    this.panel1.add(this.textArea);
    this.panel2.add(this.label);
    this.panel3.add(this.okButton);
    this.add(this.panel1);
    this.add(this.panel2, BorderLayout.NORTH);
    this.add(this.panel3, BorderLayout.SOUTH);
    this.setLocationRelativeTo(null);
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.setVisible(true);
  }
  @Override
  public void actionPerformed(ActionEvent e) {
    dispose();
  }
  
}