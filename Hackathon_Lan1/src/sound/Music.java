package sound;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by VinhNguyenDinh on 03/17/2016.
 */
public class Music {
    public static final void music (String s)
    {
        InputStream in = null;
        try {
            String str = String.format("Sounds/%s.wav", s);
            in = new FileInputStream(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        AudioStream as = null;
        try {
            as = new AudioStream(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioPlayer.player.start(as);
    }
}
