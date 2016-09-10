/**********************************************************
  * Tahmid Munat || Conference Project || Helper class
  * Detects mouse movements to adjust position
  *********************************************************/

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;


public class MouseInputDetector extends MouseAdapter implements MouseMotionListener {
  
  // instance variable
  private int x, y;
  public  Point move, click; // need public access
  private Game game;
  
  // constructor
  public MouseInputDetector(Game game) {
    this.game = game;
    this.x = 0;
    this.y = 0;
    this.move = new Point(0, 0);
    this.click = new Point(0, 0);
    this.game.addMouseMotionListener(this);
    this.game.addMouseListener(this);
  }
  
  // get the value of x
  public int getX() {
    return this.x;
  }
  
  // get the value of y
  public int getY() {
    return this.y;
  }
  
  // update processes
  @Override
  public void mouseMoved(MouseEvent e) {
    // rotate player sprite based on mouse movement
    this.move = e.getPoint();
    this.x = e.getX();
    this.y = e.getY();
  }
  
  @Override
  public void mouseDragged(MouseEvent e) {
    // skip (nothing will happen for a dragges mouse event)
  }
  
  @Override
  public void mouseClicked(MouseEvent e) {
    // check how the player sprite will fire ammo
    if (!this.game.play) {
      if (SwingUtilities.isLeftMouseButton(e)) {
        this.click = e.getPoint();
      }
    } else {
      // single fire for left mouse click
      if (SwingUtilities.isLeftMouseButton(e)) {
        this.game.addAmmo(1);
      }
      // multiple fire for right mouse click
      if (SwingUtilities.isRightMouseButton(e)) {
        this.game.addAmmo(2);
      }
    }
  }
  
}