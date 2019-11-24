package Quiz;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.util.Log;

public class SoundEffects {
    //*******
    // * ERROR STRING MESSAGES *
    private static final String SOUND_ERROR = "SOUND ERROR:";
    private static final String SOUND_INFO = "SOUND INFO:";
    private static final String SUCCESS_SOUND_FILE_WORKS = "SOUND FILE WORKS";
    private static final String SUCCESS_SOUND_FILE_ERROR = "When Playing -> SOUND File";
    // * Class Instance Variables *
    private SoundPool soundPool;
    private Context context;
    private int successSound;
    //*******
    /***Constructor↓***/
   public SoundEffects(Context context, int maxStream) {
        this.context = context;
        AudioAttributes audioAttribute = new AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_GAME).setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION).build();
        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttribute).setMaxStreams(maxStream).build();
    }


    /***SET METHODS↓***/
    public void setSound(int soundFile) {
        this.successSound = soundPool.load(context, soundFile, 1);
    }

    /***GET METHODS↓***/
    private int getSound() {
        return (successSound);
    }

    /***↓Sound Effect METHODS↓***/
    public void playSound() {
        if (getSound() != 0) {
            soundPool.play(getSound(), 1, 1, 0, 0, 1);
            Log.i(SOUND_INFO, SUCCESS_SOUND_FILE_WORKS);
        } else {
            Log.e(SOUND_ERROR, SUCCESS_SOUND_FILE_ERROR);
        }
    }

    public void stopSound() {
        soundPool.stop(getSound());
    }

    public void disposeSound() {
        soundPool.release();
    }


}// END OF CLASS
