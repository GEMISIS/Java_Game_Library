import java.applet.Applet;
import java.applet.AudioClip;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A simple audio class. Can be used to load sounds and play/stop them.
 * 
 * @author Gerald McAlister
 * 
 */
public class audioClass {

	/**
	 * The actual audio clip. Used to control the sound options
	 */
	private AudioClip soundClip;
	private String filePath;

	/**
	 * The default constructor for this class
	 */
	public audioClass() {
		filePath = "";
	}

	/**
	 * The constructor for this class. Loads the desired sound file.
	 * 
	 * @param file
	 *            The file to load for the sound
	 */
	public audioClass(String file) {
		load(file);
	}

	/**
	 * The constructor for this class. Loads the desired sound file.
	 * 
	 * @param file
	 *            The file to load for the sound
	 */
	public audioClass(URL file) {
		load(file);
	}

	/**
	 * Loads a sound to play and sets the file path location
	 * 
	 * @param location
	 *            The location of the sound file
	 * @return Returns true if successful, false otherwise.
	 */
	public boolean load(String location) {
		try {
			soundClip = Applet.newAudioClip(new URL(location));
			filePath = location;
			return true;
		} catch (MalformedURLException e) {
			return false;
		}
	}

	/**
	 * Loads a sound to play and sets the file location.
	 * 
	 * @param location
	 *            The sound file to load
	 * @return Returns true if successful, false otherwise.
	 */
	public boolean load(URL location) {
		if (location.getFile() != "") {
			soundClip = Applet.newAudioClip(location);
			filePath = location.getPath();
			return true;
		}
		return false;
	}

	/**
	 * Plays the audio clip.
	 */
	public void play() {
		soundClip.play();
	}

	/**
	 * Stops playing the audio clip.
	 */
	public void stop() {
		soundClip.stop();
	}

	/**
	 * Makes the audio clip loop.
	 */
	public void loop() {
		soundClip.loop();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return filePath;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		try {
			if (!obj.getClass().equals(audioClass.class)) {
				throw new Exception();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (obj != null)
				&& ((audioClass) obj).toString().equals(this.filePath)
				&& ((audioClass) obj).soundClip.equals(this.soundClip);
	}

}
