import java.awt.Component;

import javax.swing.JFrame;

/**
 * A window manager class. It can be used to create JFrame windows to manage a
 * java program.
 * 
 * @author Gerald McAlister
 */
public class windowManager extends JFrame {

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * The constructor for the window manager class
	 * 
	 * @param title
	 *            The title of the window
	 * @param x
	 *            The x position of the window
	 * @param y
	 *            The y position of the window
	 * @param width
	 *            The width of the window
	 * @param height
	 *            The height of the window
	 */
	public windowManager(String title, int x, int y, int width, int height) {
		this.setTitle(title);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(x, y);
		this.setSize(width + 16, height + 38);
		this.setBounds(x, y, width + 16, height + 38);
		this.getLayeredPane().setOpaque(true);
	}

	/**
	 * Adds the desired JPanel to the window frame
	 * 
	 * @param panel
	 *            The desired JPanel
	 */
	public void addPanel(Component panel) {
		this.getLayeredPane().add(panel);
	}

	/**
	 * Adds the desired keyboardSystem to the window frame
	 * 
	 * @param ks
	 *            The desired keyboardSystem
	 */
	public void addKeyboardSystem(keyboardSystem ks) {
		this.addKeyListener(ks);
	}

}
