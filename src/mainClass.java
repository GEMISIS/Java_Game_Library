import java.awt.Color;
import java.awt.event.KeyEvent;
import java.net.MalformedURLException;

public class mainClass {

	private static windowManager mainWindow;
	private static videoSystem2D video;
	private static keyboardSystem keyboard;

	/**
	 * @param args
	 * @throws MalformedURLException
	 */
	public static void main(String[] args) throws MalformedURLException {

		mainWindow = new windowManager("Demo", 100, 100, 640, 480);

		video = new videoSystem2D(mainWindow.getWidth(), mainWindow.getHeight());

		keyboard = new keyboardSystem();

		video.createImage(0, 0, 0, 50, 658, 512, "images/circle.png");
		video.getImage(0).setScale(0.25);

		video.drawText(2, 2, 0, 0, "Hello World!");
		video.getText(2).setColor(Color.black);

		mainWindow.getLayeredPane().add(video, 0);
		mainWindow.addKeyboardSystem(keyboard);
		mainWindow.setVisible(true);

		System.out.println("Done!");

		int x = 0;

		while (true) {
			if (keyboard.isKeyPressed(KeyEvent.VK_ESCAPE)) {
				System.exit(0);
			}
			if (keyboard.isKeyHeld(KeyEvent.VK_RIGHT)) {
				x += 1;
				video.getImage(0).setX(x);
			}
			if (keyboard.isKeyHeld(KeyEvent.VK_LEFT)) {
				x -= 1;
				video.getImage(0).setX(x);
			}
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			keyboard.update();
			video.updateUI();
		}
	}
}
