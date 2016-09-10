/**********************************************************
  * Tahmid Munat || Conference Project || Helper class
  * Indicates the number of chances remaining
  *********************************************************/

import java.io.*;
import javax.imageio.*;
import java.awt.image.*;

public class Lives {
  
  // instance variables
  private BufferedImage lifeBufferImage1, lifeBufferImage2, lifeBufferImage3;
  private File image1, image2;
  private boolean health; // health status
  public  int x; // needs public access for future call in init()
  
  // constructor
  public Lives() {
    this.image1 = new File("lifeAvailable.png");
    this.image2 = new File("lifeGone.png");
    this.x = x;
    this.health = false;
    
    try{
      this.lifeBufferImage2 = ImageIO.read(this.image1);
      this.lifeBufferImage3 = ImageIO.read(this.image2);
    } catch(Exception e) {
      e.printStackTrace();
    }
    
    if (this.lifeBufferImage2 == null) {
      this.lifeBufferImage2.createGraphics();
    }
    if (this.lifeBufferImage3 == null) {
      this.lifeBufferImage3.createGraphics();
    }
    
    this.lifeBufferImage1 = this.lifeBufferImage2;
  }
  
  // get the BufferedImage
  public BufferedImage getLifeBufferImage(){
    return this.lifeBufferImage1;
  }
  
  // set the BufferedImage
  public void setLifeBufferImage(){
    this.lifeBufferImage1 = this.lifeBufferImage3;
  }
  
  // set lives for reset purposes
  public void setLives(){
    this.lifeBufferImage1 = this.lifeBufferImage2;
  }
  
}