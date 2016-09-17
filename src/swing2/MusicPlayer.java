package swing2;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

public class MusicPlayer
{

	public static void start(File file)
	{
		try
		{
			Clip play = AudioSystem.getClip();
			play.open(AudioSystem.getAudioInputStream(file));
			FloatControl volume = (FloatControl) play.getControl(FloatControl.Type.MASTER_GAIN);
			volume.setValue(1.0f);
			play.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}