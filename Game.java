/**********************************************************
  * Tahmid Munat || Conference Project || Main class
  * The main program
  *********************************************************/

  /* Important: 
   * The mainAudio and startUp strings must point to the local directory where the files are stored
   * mainAudio will not play on a Mac because Mac puts a 1 megabyte size limitation.
   * The strings should be uncommented to play the audio*/

import java.io.*;
import java.awt.*;
import javax.swing.*;
import javax.imageio.*;
import java.awt.image.*;

public class Game extends JPanel implements Runnable {
  
  // instance variables
  public  JFrame frame;                       // holds the game
  private StartScreen screen;                 // first loading screens
  private KeyboardInputDetector keys;
  private MouseInputDetector mouse;
  private ControlWindow window;         // game window handler
  private Thread thread;                // to run the game and play audio
  public  Font font;
  private Score score;                  // final score by the player
  private double theta;                 // for angular calculation
  private long diff, start;
  private MainMenu mainMenu;
  private File backgroundFile, backgroundFile2, victoryFile, defeatFile, startscreen1File, startscreen2File;
  public  boolean inited, up, down, left, right, gameOver, mainMenuScreen, scoreScreen, startScreen1, startScreen2;
  public  BufferedImage background, background2, victory, defeat, startscreen1, startscreen2; // background images
  public  volatile boolean play;
  public  int turbo, seconds;
  private int totalEnemies, enemySpeed, bulletSpeed;
  private int numberOflives, remainingLives, totalStaticEnemies;
  private int enemyBulletNumber, bulletNumber, enemyCount, bulletTravelTime;
  private HeroSprite hero;
  private Lives[] hearts;
  private EvilSprites[] evil;
  private Ammo[] ammos;
  private Ammo[] enemyAmmos;
  private EvilSprites[] staticEnemy;
  
  // constructor
  public Game() {
    
    // frame
    this.frame = new JFrame("Space Pirates");
    this.font = new Font(Font.MONOSPACED, Font.BOLD, 32);
    
    // loading screen
    this.screen = screen;
    
    // boolean
    this.inited = false;
    this.up = false;
    this.down = false;
    this.right = false;
    this.left = false;
    this.play = play;
    this.gameOver = gameOver;
    this.mainMenuScreen = mainMenuScreen;
    this.scoreScreen = scoreScreen;
    this.startScreen1 = startScreen1;
    this.startScreen2 = startScreen2;
    
    // related to the game
    this.turbo = 4;
    this.totalEnemies = 12;
    this.enemySpeed = 3;
    this.bulletSpeed = 20;
    this.numberOflives = 6;
    this.totalStaticEnemies = 5;
    this.enemyBulletNumber = 15;
    this.bulletNumber = 25;
    this.bulletTravelTime = 1;
    this.seconds = 90;
    this.start = System.currentTimeMillis();
    this.remainingLives = numberOflives;
    this.enemyCount = this.totalEnemies + this.totalStaticEnemies;
    
    // sprites
    this.hero = hero;
    this.evil = evil;
    this.hearts = hearts;
    this.ammos = ammos;
    this.enemyAmmos = enemyAmmos;
    this.staticEnemy = staticEnemy;
    
    // others
    this.window = window;
    this.keys = keys;
    this.mouse = mouse;
    this.thread = thread;
    this.score = score;
    this.theta = theta;
    this.diff = diff;
    this.background = background;     // buffered image
    this.startscreen1 = startscreen1; // buffered image
    this.startscreen2 = startscreen2; // buffered image
    
    // set window
    this.setSize(1080, 660);
    this.frame.setSize(1080, 680);
    this.frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
    this.frame.setResizable(false);
    this.setFocusable(true);
    this.frame.setLocationRelativeTo(null);
    this.setBackground(Color.BLACK);
    this.frame.add(this);
    
    // add background images
    this.backgroundFile = new File("background.jpg");
    this.backgroundFile2 = new File("background2.jpg");
    this.victoryFile = new File("victory.png");
    this.defeatFile = new File("defeat.png");
    this.startscreen1File = new File("startscreen1.png");
    this.startscreen2File = new File("startscreen2.png");
    
    try {
      this.background = (ImageIO.read(this.backgroundFile)).getSubimage(0, 0, 1080, 660);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    try {
      this.background2 = (ImageIO.read(this.backgroundFile2)).getSubimage(0, 0, 1080, 660);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    try {
      this.victory = (ImageIO.read(this.victoryFile)).getSubimage(0, 0, 1080, 660);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    try {
      this.defeat = (ImageIO.read(this.defeatFile)).getSubimage(0, 0, 1080, 660);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    try {
      this.startscreen1 = (ImageIO.read(this.startscreen1File)).getSubimage(0, 0, 1080, 660);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    try {
      this.startscreen2 = (ImageIO.read(this.startscreen2File)).getSubimage(0, 0, 1080, 660);
    } catch (IOException e) {
      e.printStackTrace();
    }
    
    this.frame.setVisible(true);
  }
  
  // initialization
  public void init() {
    this.window = new ControlWindow(this);
    this.mouse = new MouseInputDetector(this);
    this.keys = new KeyboardInputDetector(this);
    
    this.play = false;
    this.gameOver = true;
    this.mainMenuScreen = false; // to not start the main menu screen
    this.scoreScreen = false; // to not start the score screen
    this.startScreen1 = true; // to start the first loading screen
    this.startScreen2 = false; // to not start the second loading screen
    this.score = new Score(this);
    this.mainMenu = new MainMenu(this);
    this.screen = new StartScreen(this);
    
    this.hero = new HeroSprite();
    
    this.remainingLives = this.numberOflives;
    this.hearts = new Lives[this.numberOflives];
    for (int i = 0; i < this.numberOflives; i++) {
      this.hearts[i] = new Lives();
      this.hearts[i].x = 40 * i;
    }
    
    this.evil = new EvilSprites[this.totalEnemies];
    for (int i = 0; i < this.totalEnemies; i++) {
      this.evil[i] = new EvilSprites();
    }
    
    this.staticEnemy = new EvilSprites[this.totalStaticEnemies];
    for (int i = 0; i < this.totalStaticEnemies; i++) {
      this.staticEnemy[i] = new EvilSprites(2);
    }
    
    this.thread = new Thread(this);
    
    this.ammos = new Ammo[this.bulletNumber];
    for (int i = 0; i < this.bulletNumber; i++) {
      this.ammos[i] = new Ammo(210, 200, 0);
    }
    
    this.enemyAmmos = new Ammo[this.enemyBulletNumber];
    for (int i = 0; i < this.enemyBulletNumber; i++) {
      this.enemyAmmos[i] = new Ammo(5);
    }
    
    this.inited = true;
  }
  
  // for thread execution purposes
  /* Important: The mainAudio and startUp strings must point to the local directory where the files are stored
   * mainAudio will not play on a mac because mac puts a 1 megabyte size limitation.
   * The strings are currently commented out */
  public void run() {
    this.init();
    GameAudio audio = new GameAudio();
    String mainAudio = "your\\local\\directory\\mainAudio.wav";
    String startUp = "your\\local\\directory\\startup.wav";
    audio.play(startUp);
    try {
      // show the first loading screen, the second loading screen and main menu; in that order
      Thread.sleep(4000);
      this.startScreen1 = false;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.startScreen2 = true;
    try {
      // show the first loading screen, the second loading screen and main menu; in that order
      Thread.sleep(4000);
      this.startScreen2 = false;
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    this.mainMenuScreen = true;
    audio.close();
    
    while (true) {
      audio.playWithLoop(mainAudio);
      this.main();
      
      while (!this.gameOver) {
        this.play();
      }
      
      this.score();
      audio.close();
    }
  }
  
  // draw all the components
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    if (this.inited) {
      if (this.startScreen1) {
        this.screen.draw1(g2d); // first loading image
      } else if (this.startScreen2) {
        this.screen.draw2(g2d); // second loading image
      } else if (this.mainMenuScreen) {
        this.mainMenu.draw(g2d);
      } else if (!this.gameOver) {
        g2d.drawImage(this.background2, 0, 0, null); // BackGround image
        
        for (int i = 0; i < this.numberOflives; i++) {
          g2d.drawImage(this.hearts[i].getLifeBufferImage(), this.hearts[i].x, 620, null);
        }
        
        // Calculate The angle
        // studied algorithm
        this.theta = Math.atan2(this.mouse.getY() - (this.hero.y + 40), this.mouse.getX()
                             - (this.hero.x + 50)) + Math.PI / 2;
        
        for (int i = 0; i < this.bulletNumber; i++) {
          if (this.ammos[i].shotsFired) {
            g2d.drawImage(this.ammos[i].getAmmoBufferImage(), this.ammos[i].x,
                          this.ammos[i].y, null);
          }
        }
        
        for (int i = 0; i < this.enemyBulletNumber; i++) {
          if (this.enemyAmmos[i].shotsFired) {
            g2d.drawImage(this.enemyAmmos[i].getAmmoBufferImage(), this.enemyAmmos[i].x,
                          this.enemyAmmos[i].y, null);
          }
        }
        
        this.hero.draw(g2d, this.theta);
        
        for (int i = 0; i < this.totalEnemies; i++) {
          if (this.evil[i].isAlive) {
            g2d.drawImage(this.evil[i].getEvilSpriteBufferImage(), this.evil[i].x,
                          this.evil[i].y, null);
          }
        }
        
        for (int i = 0; i < this.totalStaticEnemies; i++) {
          if (this.staticEnemy[i].isAlive) {
            g2d.drawImage(this.staticEnemy[i].getEvilSpriteBufferImage(),
                          this.staticEnemy[i].x, this.staticEnemy[i].y, null);
          }
        }
        
      } else if (this.scoreScreen) {
        this.score.draw(g2d);
      }
    }
    revalidate();
    repaint(); 
  }
  
  // fps handler
  public void sleep(int i) {
    if (i > 0) {
      this.diff = System.currentTimeMillis() - this.start;
      long targetDelay = 1000 / i;
      if (this.diff < targetDelay) {
        try {
          Thread.sleep(targetDelay - this.diff);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
      this.start = System.currentTimeMillis();
    }
  }
  
  // studied algorithm
  public void addAmmo(int num) {
    if (num == 1) {
      for (int i = 0; i < this.bulletNumber; i++) {
        if (!this.ammos[i].shotsFired) {
          if (i % 2 == 0) {
            this.ammos[i].x = this.hero.x + 70;
          } else {
            this.ammos[i].x = this.hero.x + 30;
            this.ammos[i].y = this.hero.y + 40;
            this.ammos[i].theta = this.theta;
            this.ammos[i].sin = Math.sin(this.ammos[i].theta - Math.PI / 2);
            this.ammos[i].cos = Math.cos(this.ammos[i].theta - Math.PI / 2);
            this.ammos[i].shotsFired = true;
            break;
          }
        }
      }
    }
    
    if (num == 2) {
      for (int i = 0; i < this.bulletNumber; i++) {
        if (!this.ammos[i].shotsFired) {
          this.ammos[i].shotsFired = true;
          this.ammos[i].x = this.hero.x + 40;
          this.ammos[i].y = this.hero.y + 40;
          this.ammos[i].theta = Math.random() * Math.PI * 2;
          this.ammos[i].sin = Math.sin(this.ammos[i].theta - Math.PI / 2);
          this.ammos[i].cos = Math.cos(this.ammos[i].theta - Math.PI / 2);
          continue;
        }
      }
    }
  }
  
  // studied algorithm
  public void addEnemyAmmo(EvilSprites evil) {
    for (int i = 0; i < this.enemyBulletNumber; i++) {
      if (!this.ammos[i].shotsFired) {
        this.enemyAmmos[i].x = evil.x + 25;
        this.enemyAmmos[i].y = evil.y + 25;
        this.enemyAmmos[i].theta = Math.atan2((this.hero.y +40) - (evil.y), (this.hero.x+50) - (evil.x));
        this.enemyAmmos[i].sin = Math.sin(this.enemyAmmos[i].theta) ;
        this.enemyAmmos[i].cos = Math.cos(this.enemyAmmos[i].theta );
        this.enemyAmmos[i].shotsFired = true;
        break;
      }
    }
  }
  
  public void play() {
    while (this.play) {
      if (this.hero.isAlive) {
        if (this.up && this.hero.y > 0) {
          this.hero.setY(this.hero.y - this.turbo);
        }
        if (this.down && this.hero.y < 580) {
          this.hero.setY(this.hero.y + this.turbo);
        }
        if (this.right && this.hero.x < 980) {
          this.hero.setX(this.hero.x + this.turbo);
        }
        if (this.left && this.hero.x > 0) {
          this.hero.setX(this.hero.x - this.turbo);
        }
        
        for (int i = 0; i < this.totalEnemies; i++) {
          this.evil[i].theta = Math.atan2(this.evil[i].y - (this.hero.y + 40),
                                     this.evil[i].x - (this.hero.x + 50));
          this.evil[i].y = this.evil[i].y - (int) (this.enemySpeed * Math.sin(this.evil[i].theta));
          
          this.evil[i].x = this.evil[i].x - (int) (this.enemySpeed * Math.cos(this.evil[i].theta));
          
          if (this.evil[i].isAlive
                && this.remainingLives != 0
                && this.evil[i].getRectangleBounds().intersects(this.hero.getRectangleBounds())) {
            this.evil[i].isAlive = false;
            this.enemyCount--;
            this.hearts[--this.remainingLives].setLifeBufferImage(); // crashes if [remainingHearts--]
          }
        }
        
        for (int i = 0; i < this.totalStaticEnemies; i++) {
          
          if (this.staticEnemy[i].isAlive
                && this.remainingLives != 0
                && this.staticEnemy[i].getRectangleBounds().intersects(this.hero.getRectangleBounds())) {
            this.staticEnemy[i].isAlive = false;
            this.enemyCount--;
            this.hearts[--this.remainingLives].setLifeBufferImage(); // crashes if [remainingHearts--]
          }
        }
        
        for (int i = 0; i < this.bulletNumber; i++) {
          if (this.ammos[i].shotsFired) {
            for (int j = 0; j < this.totalEnemies; j++) {
              if (this.evil[j].isAlive) {
                if (this.evil[j].getRectangleBounds().intersects(this.ammos[i].getRectangleBounds())) {
                  this.evil[j].isAlive = false;
                  this.enemyCount--;
                  this.ammos[i].shotsFired = false;
                }
              }
            }
            for (int k = 0; k < this.totalStaticEnemies; k++) {
              if (this.staticEnemy[k].isAlive) {
                if (this.staticEnemy[k].getRectangleBounds().intersects(this.ammos[i].getRectangleBounds())) {
                  this.staticEnemy[k].isAlive = false;
                  this.enemyCount--;
                  this.ammos[i].shotsFired = false;
                }
              }
            }
            
            if (this.ammos[i].x < -100 || this.ammos[i].x > 1180
                  || this.ammos[i].y < -100 || this.ammos[i].y > 760) {
              this.ammos[i].shotsFired = false;
            } else {
              this.ammos[i].y = this.ammos[i].y + (int) (this.bulletSpeed * this.ammos[i].sin);
              this.ammos[i].x = this.ammos[i].x + (int) (this.bulletSpeed * this.ammos[i].cos);
            }
          }
        }
        
        for (int i = 0; i < this.enemyBulletNumber; i++) {
          if (this.enemyAmmos[i].shotsFired) {
            if (this.hero.getRectangleBounds().intersects(this.enemyAmmos[i].getRectangleBounds())) {
              this.hearts[--this.remainingLives].setLifeBufferImage(); // crashes if [remainingHearts--]
              this.enemyAmmos[i].shotsFired = false;
            }
            
            if (this.enemyAmmos[i].x < -200 || this.enemyAmmos[i].x > 1300
                  || this.enemyAmmos[i].y < -200 || this.enemyAmmos[i].y > 1000) {
              this.enemyAmmos[i].shotsFired = false;
            } else {
              this.enemyAmmos[i].y = this.enemyAmmos[i].y + (int) (this.bulletSpeed * this.enemyAmmos[i].sin);
              this.enemyAmmos[i].x = this.enemyAmmos[i].x + (int) (this.bulletSpeed * this.enemyAmmos[i].cos);
            }
          }
        }
        if(this.bulletTravelTime % this.seconds == 0) {
          int rand = (int)(Math.random()*this.totalStaticEnemies);
          if(this.staticEnemy[rand].isAlive) {
            addEnemyAmmo(this.staticEnemy[rand]);
          }
        }
        
        if (this.remainingLives == 0) {
          this.score.victory = false;
          this.play = false;
          this.gameOver = true;
          this.scoreScreen = true;
        }
        if (this.enemyCount == 0) {
          this.score.victory = true;
          this.play = false;
          this.gameOver = true;
          this.scoreScreen = true;
        }
        repaint();
        sleep(60);
        this.bulletTravelTime++;
      }
    }
  }
  
  // detects what the player chooses and acts accordingly
  public void main() {
    while (this.mainMenuScreen) {
      for (int i = 0; i < 7; i++) {
        if (this.mainMenu.rectangle[i].contains(this.mouse.move)) {
          this.mainMenu.active[i] = true;
        } else {
          this.mainMenu.active[i] = false;
        }
      }
      
      if (this.mainMenu.rectangle[0].contains(this.mouse.click)) {
        this.mainMenu.click = true;
      } else {
        if (!(this.mainMenu.rectangle[4].contains(this.mouse.click)
                || this.mainMenu.rectangle[5].contains(this.mouse.click) || this.mainMenu.rectangle[6]
                .contains(this.mouse.click))) {
          this.mainMenu.click = false;
        }
      }
      
      if (this.mainMenu.rectangle[3].contains(this.mouse.click)) {
        // exit is selected
        System.exit(0);
      }
      if (this.mainMenu.rectangle[2].contains(this.mouse.click)) {
        // about is selected
        this.mouse.click.x = 0;
        this.mouse.click.y = 0;
        new About();
      }
      if (this.mainMenu.rectangle[1].contains(this.mouse.click)) {
        // help is selected
        this.mouse.click.x = 0;
        this.mouse.click.y = 0;
        new Help();
      }
      
      // easy mode
      if (this.mainMenu.click && mainMenu.rectangle[4].contains(this.mouse.click)) {
        this.mainMenu.click = false;
        this.totalEnemies = 3;
        this.numberOflives = 6;
        this.remainingLives = 6;
        this.totalStaticEnemies = 1;
        this.seconds = 90;
        this.enemyCount = this.totalEnemies + this.totalStaticEnemies;
        this.reset();
        this.mainMenuScreen = false;
        this.play = true;
        this.gameOver = false;
      }
      // normal mode
      if (this.mainMenu.click && mainMenu.rectangle[5].contains(this.mouse.click)) {
        this.mainMenu.click = false;
        this.totalEnemies = 4;
        this.numberOflives = 4;
        this.remainingLives = 4;
        this.totalStaticEnemies = 2;
        this.seconds = 70;
        this.enemyCount = this.totalEnemies + this.totalStaticEnemies;
        this.reset();
        this.mainMenuScreen = false;
        this.play = true;
        this.gameOver = false;
      }
      // hard mode
      if (this.mainMenu.click && mainMenu.rectangle[6].contains(this.mouse.click)) {
        this.mainMenu.click = false;
        this.totalEnemies = 5;
        this.numberOflives = 2;
        this.remainingLives = 2;
        this.totalStaticEnemies = 3;
        this.seconds = 60;
        this.enemyCount = this.totalEnemies + this.totalStaticEnemies;
        this.reset();
        this.mainMenuScreen = false;
        this.play = true;
        this.gameOver = false;
      }
      revalidate();
      repaint();
      sleep(60);
    }
  }
  
  // player score screen
  public void score() {
    this.score.paint = false;
    this.mouse.click.x = 0;
    this.mouse.click.y = 0;
    while (this.scoreScreen) {
      for (int i = 0; i < 2; i++) {
        if (this.score.rectangle[i].contains(this.mouse.move))
          this.score.active[i] = true;
        else
          this.score.active[i] = false;
      }
      if (this.score.rectangle[1].contains(this.mouse.click))
        System.exit(0);
      if (this.score.rectangle[0].contains(this.mouse.click)) {
        this.mouse.click.x = 0;
        this.mouse.click.y = 0;
        this.scoreScreen = false;
        this.mainMenuScreen = true;
      }
      revalidate();
      repaint();
      sleep(60);
      this.score.paint = true;
      
    }
  }
  
  public void reset() {
    for (int i = 0; i < this.numberOflives; i++) {
      this.hearts[i].setLives();
    }
    
    for (int i = 0; i < this.totalEnemies; i++) {
      this.evil[i].isAlive = true;
      this.evil[i].setLocation();
    }
    
    for (int i = 0; i < this.totalStaticEnemies; i++) {
      this.staticEnemy[i].isAlive = true;
      this.staticEnemy[i].setLocation();
    }
    
    for (int i = 0; i < this.bulletNumber; i++) {
      this.ammos[i].shotsFired = false;
    }
    this.hero.x = 510;
    this.hero.y = 300;
    
  }
  
  // main function
  public static void main(String[] args) {
    new Thread(new Game()).start();
  }
}
