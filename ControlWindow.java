/**********************************************************
  * Tahmid Munat || Conference Project || Helper class
  * Handles quitgame functionality while in game
  *********************************************************/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ControlWindow extends WindowAdapter {
  
  // instance variable
  private Game game;
  
  // constructor
  public ControlWindow(Game game){
    this.game = game;
    this.game.frame.addWindowListener(this);
  }
  
  // invoked when the user attempts to close the window from the window's system menu
  public void windowClosing(WindowEvent e) {
    if(e.getSource() == game.frame) {
      new QuitGameVerification(this);
      game.play = false;
    } else if(e.getSource() instanceof QuitGameVerification) {
      ((QuitGameVerification)(e.getSource())).dispose();
      if(!game.mainMenuScreen && !game.scoreScreen) {
        game.play = true;
      }
    }
    
  }
  
  private class QuitGameVerification extends JFrame implements ActionListener {
    
    // instance variables
    private JLabel label;
    private JButton yesButton, noButton;
    private JPanel panel1, panel2;
    
    // constructor
    public QuitGameVerification(ControlWindow cw){
      this.label = new JLabel("Do you wish to quit the game?");
      this.yesButton = new JButton("Yes");
      this.noButton = new JButton("No");
      this.panel1 = new JPanel();
      this.panel2 = new JPanel();
      
      this.yesButton.addActionListener(this);
      this.noButton.addActionListener(this);
      
      this.setSize(350, 100);
      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
      this.setResizable(false);
      this.setLocationRelativeTo(null);
      this.addWindowListener(cw);
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
        if(!game.mainMenuScreen && !game.scoreScreen) {
          game.play = true;
        }
      } else {
        // quit the game
        System.exit(0);
      }
    }
    
  }
}