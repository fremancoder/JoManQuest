package be.joman.jomanquest.controller;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Freddy on 22-7-2017.
 */
public class MusicController {

    private Clip audioClip;

    private AudioInputStream audioStream;

    public MusicController(){
    }

    public void play(){
        try {
            File audioFile = new File("E:\\tmp\\MarimbaBoy.wav");
            audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            audioClip = (Clip) AudioSystem.getLine(info);
            audioClip.open(audioStream);
            audioClip.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public void stop(){
        audioClip.stop();
    }

}
