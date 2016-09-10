/**********************************************************
  * Tahmid Munat || Conference Project || Helper class
  * Verifies whether the user really wants to quit
  *********************************************************/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class QuitGameVerification extends JFrame implements ActionListener {
  
  // instance variables
  private JLabel label;
  private JButton yesButton, noButton;
  private JPanel panel1, panel2;
  
  // constructor
  public QuitGameVerification() {
    this.label = new JLabel("Do you wish to quit the game?");
    this.yesButton = new JButton("Yes");
    this.noButton = new JButton("No");
    this.panel1 = new JPanel();
    this.panel2 = new JPanel();
    
    this.yesButton.addActionListener(this);
    this.noButton.addActionListener(this);
    
    this.setSize(350, 100);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.panel1.add(label);
    this.panel2.add(yesButton);
    this.panel2.add(noButton);
    this.add(this.panel1);
    this.add(this.panel2, BorderLayout.SOUTH);
    this.setVisible(true);
  }
  
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == this.noButton) {
      dispose();
    } else {
      // quit the game
      System.exit(0);
    }
  }
  
}