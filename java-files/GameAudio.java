/**********************************************************
  * Tahmid Munat || Conference Project || Helper class
  * This program plays the sound in the game
  *********************************************************/

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.*;

public class GameAudio {
  
  /* instance variable */
  public Clip audioClip;
  
  /* constructor */
  public GameAudio() {
    this.audioClip = audioClip;
  }
  
  /* play a given audio file (.wav preferred, doesn't allow .mp3) */
  public void play(String audioFilePath) {
    try {
      File audioFile = new File(audioFilePath);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
      AudioFormat format = audioStream.getFormat();     
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      this.audioClip = (Clip) AudioSystem.getLine(info);
      this.audioClip.open(audioStream);
      try {
        audioClip.start();
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      /* exceptions */
    } catch (UnsupportedAudioFileException e) {
      System.out.println("The specified audio file is not supported.");
      e.printStackTrace();
    } catch (LineUnavailableException e) {
      System.out.println("Audio line for playing back is unavailable.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("Error playing the audio file.");
      e.printStackTrace();
    }
  }
  
  /* non-stop play of a given audio file (.wav preferred, doesn't allow .mp3) */
  public void playWithLoop(String audioFilePath) {
    try {
      File audioFile = new File(audioFilePath);
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
      AudioFormat format = audioStream.getFormat();     
      DataLine.Info info = new DataLine.Info(Clip.class, format);
      this.audioClip = (Clip) AudioSystem.getLine(info);
      this.audioClip.open(audioStream);
      try {
        audioClip.start();
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      this.audioClip.loop(1000); // hardcoded to prevent freezing
      /* exceptions */
    } catch (UnsupportedAudioFileException e) {
      System.out.println("The specified audio file is not supported.");
      e.printStackTrace();
    } catch (LineUnavailableException e) {
      System.out.println("Audio line for playing back is unavailable.");
      e.printStackTrace();
    } catch (IOException e) {
      System.out.println("Error playing the audio file.");
      e.printStackTrace();
    }
  }
  
  public void resume() {
    this.audioClip.start();
  }
  
  public void pause() {
    this.audioClip.stop();
  }
  
  public void close() {
    this.audioClip.close();
  }
  
}