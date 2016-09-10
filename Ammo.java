/*******************************************************
  * Tahmid Munat || Conference Project || Helper class
  * The bullets coming out from the player's vehicle
  ******************************************************/

import java.io.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.image.*;


public class Ammo {
  
  // instance variables
  private BufferedImage ammoBufferImage;
  private File image1, image2;     // bullet image files
  public  int x, y;                // position (needs public access)
  public  double sin, cos, theta;  // position adjustment (needs public access)
  public  boolean shotsFired;      // whether bullet was fired or not (needs public access)
  
  // first constructor for player bullets
  public Ammo (int x, int y , double theta){
    this.ammoBufferImage = ammoBufferImage;
    this.image1 = new File("firstAmmo.png");
    this.shotsFired = false;
    this.x = x;
    this.y = y;
    this.sin = sin;
    this.cos = cos;
    this.theta = theta;
    
    // load the ammo image
    try{
      this.ammoBufferImage = ImageIO.read(this.image1);
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    if (this.ammoBufferImage == null) {
      this.ammoBufferImage.createGraphics();
    }
  }
  
  // second constructor for enemy bullets
  public Ammo (int i){
    this.ammoBufferImage = ammoBufferImage;
    this.image2 = new File("secondAmmo.png");
    this.shotsFired = false;
    this.x = x;
    this.y = y;    
    this.sin = sin;
    this.cos = cos;
    this.theta = theta;
    
    // load the ammo image
    try{
      this.ammoBufferImage = ImageIO.read(this.image2);
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    if (this.ammoBufferImage == null) {
      this.ammoBufferImage.createGraphics();
    }
  }
  
  // get the BufferedImage
  public BufferedImage getAmmoBufferImage() {
    return this.ammoBufferImage;
  }
  
  // return a rectangle with the BufferedImage
  public Rectangle getRectangleBounds() {
    return new Rectangle(this.x, this.y, this.ammoBufferImage.getWidth(), this.ammoBufferImage.getHeight());
  }
  
}