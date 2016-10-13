/**********************************************************
  * Tahmid Munat || Conference Project || Helper class
  * The first loading simulation
  *********************************************************/

import java.io.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;

public class StartScreen {
  
  // instance variables
  private Game game;
  
  // constructor
  public StartScreen(Game game) {
    this.game = game;
  }
  
  public void draw1(Graphics2D g2d) {
    g2d.drawImage(game.startscreen1, 0, 0, null);
  }
  
  public void draw2(Graphics2D g2d) {
    g2d.drawImage(game.startscreen2, 0, 0, null);
  }
  
}