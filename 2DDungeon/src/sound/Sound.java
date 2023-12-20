package sound;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound implements Runnable {
    private String filePath;

    public Sound(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            File audioFile = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(audioFile);
            AudioFormat format = audioStream.getFormat();
            DataLine.Info info = new DataLine.Info(Clip.class, format);
            Clip clip = (Clip) AudioSystem.getLine(info);

            clip.open(audioStream);
            clip.start();

            // Wait for the clip to finish playing before closing resources
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
                    clip.close();
                    try {
                        audioStream.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });

        } catch (UnsupportedAudioFileException | LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public void playConcurrently() {
        Thread audioThread = new Thread(this);
        audioThread.start();
    }
}
