/**********************************************************
  * Tahmid Munat || Conference Project || Helper class
  * Detects keyboard movements to adjust position
  *********************************************************/

import java.awt.event.*;


public class KeyboardInputDetector implements KeyListener {
  
  // instance variable
  private Game game;
  
  // constructor
  public KeyboardInputDetector(Game game) {
    this.game = game;
    this.game.addKeyListener(this);
  }
  
  // get up, down, left, right key press events
  public void keyPressed(KeyEvent e) {
    if(e.getKeyCode() == KeyEvent.VK_W) game.up = true;
    if(e.getKeyCode() == KeyEvent.VK_S) game.down = true;
    if(e.getKeyCode() == KeyEvent.VK_D) game.right = true;
    if(e.getKeyCode() == KeyEvent.VK_A) game.left = true;
    if(e.getKeyCode() == KeyEvent.VK_CONTROL) game.turbo = 10;
  }
  
  public void keyReleased(KeyEvent e) {  
    if(e.getKeyCode() == KeyEvent.VK_W) game.up = false;
    if(e.getKeyCode() == KeyEvent.VK_S) game.down = false;
    if(e.getKeyCode() == KeyEvent.VK_D) game.right = false;
    if(e.getKeyCode() == KeyEvent.VK_A) game.left = false;
    if(e.getKeyCode() == KeyEvent.VK_CONTROL) game.turbo = 4;
  }
  @Override
  public void keyTyped(KeyEvent e) {
    // skip
  } 
  
}