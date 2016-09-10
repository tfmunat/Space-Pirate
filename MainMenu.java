/**********************************************************
  * Tahmid Munat || Conference Project || Helper class
  * The main menu panel
  *********************************************************/

import java.io.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;


public class MainMenu {
  
  // instance variables
  private String play;
  private BufferedImage menuBufferedImage1, menuBufferedImage2, menuBufferedImage3;
  private File image1, image2, image3;
  private int buttonWidth, buttonHeight;
  private Game game;
  public  boolean click;         // needs public access
  public  boolean[] active;      // needs public access
  public  Rectangle[] rectangle; // needs public access
  private BufferedImage[] bufferedImageArray;
  private Point[] point;
  
  // constructor
  public MainMenu(Game game) {
    this.active = new boolean[7];
    this.point = new Point[7];
    this.menuBufferedImage1 = menuBufferedImage1;
    this.menuBufferedImage2 = menuBufferedImage2;
    this.menuBufferedImage3 = menuBufferedImage3;
    this.bufferedImageArray = new BufferedImage[3];
    this.image1 = new File("easy.png");
    this.image2 = new File("normal.png");
    this.image3 = new File("hard.png");
    this.buttonWidth = 150;
    this.buttonHeight = 50;
    this.game = game;
    this.rectangle = new Rectangle[7];
    this.click = click;
    
    // initialize
    for (int i = 0; i < 7; i++) {
      this.active[i] = false;
    }
    
    for (int i = 0; i < 4; i++) {
      this.point[i] = new Point(60 + i * 270, 200);
      this.rectangle[i] = new Rectangle(this.point[i], new Dimension(this.buttonWidth, this.buttonHeight));
    }
    
    for (int i = 0; i < 3; i++) {
      this.point[i + 4] = new Point(60, 281 + 81 * i);
      this.rectangle[i + 4] = new Rectangle(this.point[i+4], new Dimension(this.buttonWidth, this.buttonHeight));
    }
    
    try{
      this.menuBufferedImage1 = ImageIO.read(this.image1);
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    try{
      this.menuBufferedImage2 = ImageIO.read(this.image2);
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    try{
      this.menuBufferedImage3 = ImageIO.read(this.image3);
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    this.bufferedImageArray[0] = menuBufferedImage1;
    this.bufferedImageArray[1] = menuBufferedImage2;
    this.bufferedImageArray[2] = menuBufferedImage3;
      
  }
  
  // draw the main menu
  public void draw(Graphics2D g2d) {
    g2d.drawImage(game.background, 0, 0, null);
    g2d.setFont(game.font);
    /* not needed if using a custom background
    for (int i = 0; i < 4; i++) {
      if (this.active[i]) {
        g2d.drawImage(this.menuBufferedImage, this.point[i].x, this.point[i].y, null);
      }
    }
    */
    if (this.click) {
      g2d.drawImage(this.bufferedImageArray[0], this.point[4].x, this.point[4].y, null);
      g2d.drawImage(this.bufferedImageArray[1], this.point[5].x, this.point[5].y, null);
      g2d.drawImage(this.bufferedImageArray[2], this.point[6].x, this.point[6].y, null);
    }
  }
  
  public Rectangle getRectangleBounds(int i) {
    return this.rectangle[i];
  }
  
}