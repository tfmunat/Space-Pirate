/**********************************************************
  * Tahmid Munat || Conference Project || Helper class
  * General Information pane
  *********************************************************/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class About extends JFrame implements ActionListener{
  
  // instance variables
  private JPanel panel1, panel2, panel3;
  private JButton okButton;
  private JTextArea textArea; // holds the words
  private ImageIcon image;    // about icon
  private JLabel label;       // to set the icon
  
  // constructor
  public About(){
    this.label = new JLabel();
    this.textArea = new JTextArea(20 , 30);
    this.okButton = new JButton("OK");
    this.panel1 = new JPanel();
    this.panel2 = new JPanel();
    this.panel3 = new JPanel();
    this.image = new ImageIcon("about.png");
    
    this.textArea.setText(" Greetings! \n \n This is a java game project for the Data Structures class at \n Sarah Lawrence College. "+ 
                 "This was developed by Tahmid Munat \n in Spring 2016 under Professor James Marshall. " +
                 "\n \n For more information, visit: http://www.slc.edu");
    this.textArea.setEditable(false);
    this.label.setIcon(this.image);
    this.okButton.addActionListener(this);
    
    this.setResizable(false);
    this.setSize(450,360);
    this.setTitle("About");
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