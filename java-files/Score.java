/**********************************************************
  * Tahmid Munat || Conference Project || Helper class
  * The final result panel to show score
  *********************************************************/

import java.awt.*;
import javax.imageio.*;

public class Score {
  
  // instance variables
  private Game game;
  private int buttonWidth, buttonHeight;
  private Integer score;
  public  boolean click, victory, paint; // needs public access
  public  boolean[] active;              // needs public access
  public  Rectangle[] rectangle;         // needs public access
  private Point[] point;
  
  // constructor
  public Score(Game game) {
    this.game = game;
    this.buttonWidth = 150;
    this.buttonHeight = 50;
    this.rectangle = new Rectangle[2];
    this.score = 0;
    this.click = click;;
    this.victory = false;
    this.paint = false;
    this.active = new boolean[2];
    this.point = new Point[2];
    
    // initialize
    for (int i = 0; i < 2; i++) {
      this.active[i] = false;
    }
    
    for (int i = 0; i < 2; i++) {
      this.point[i] = new Point(465, 200 + 81*i);
      this.rectangle[i] = new Rectangle(this.point[i], new Dimension(this.buttonWidth, this.buttonHeight));
    }
  }
  
  public void draw(Graphics2D g2d) {
    if(victory) {
      g2d.drawImage(game.victory, 0, 0, null);
      g2d.setColor(new Color(210, 165, 0));
      g2d.setFont(game.font);
      // random score for now
      if(!this.paint) {
        if (game.seconds > 50) {
          this.score = 5000 + (int) (Math.random() * ((10000 - 5000) + 1));
        } else {
          this.score = 2000 + (int) (Math.random() * ((5000 - 2000) + 1));
        }
      }
      g2d.drawString(this.score.toString(), 570, 157);
    } else {
      g2d.drawImage(game.defeat, 0, 0, null);
    }
  }
  
  public Rectangle getRectangleBounds(int i) {
    return rectangle[i];
  }
  
}