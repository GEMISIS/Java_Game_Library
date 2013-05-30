import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * A class used to handle the input for the computer keyboard
 * 
 * @author Gerald McAlister
 */
public class keyboardSystem implements KeyListener {

	/**
	 * The keys from the keyboard's value
	 */
	private boolean[] keys = new boolean[525];
	/**
	 * The key state for the previous keyboard state
	 */
	private boolean[] keysOld = new boolean[525];
	/**
	 * The key state for the current keyboard state
	 */
	private boolean[] keysCurrent = new boolean[525];

	/**
	 * The constructor for the keyboard system
	 */
	public keyboardSystem() {
	}

	/**
	 * Tells whether the key is just now being pressed
	 * 
	 * @param key
	 *            The desired key
	 * @return The boolean value of the key just being pressed
	 */
	public boolean isKeyPressed(int key) {
		return ((!keysOld[key]) && (keysCurrent[key]));
	}

	/**
	 * Gets the the key held state of the desired keyboard key
	 * 
	 * @param key
	 *            The desired key
	 * @return The boolean value of the key being held
	 */
	public boolean isKeyHeld(int key) {
		return ((keysOld[key]) && (keysCurrent[key]));
	}

	/**
	 * Gets the the key released state of the desired keyboard key
	 * 
	 * @param key
	 *            The desired key
	 * @return The boolean value of the key being released
	 */
	public boolean isKeyReleased(int key) {
		return ((!keysOld[key]) && (!keysCurrent[key]));
	}

	/**
	 * Tells whether the key was just released
	 * 
	 * @param key
	 *            The desired key
	 * @return The boolean value of the key just being released
	 */
	public boolean isKeyJustReleased(int key) {
		return ((keysOld[key]) && (!keysCurrent[key]));
	}

	/**
	 * Updates the keyboard's keys states
	 */
	public void update() {
		keysOld = keysCurrent.clone();
		keysCurrent = keys.clone();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent e) {
	}

}
