import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.swing.JPanel;

/**
 * A class for creating a system that will automatically manage video display
 * items that are created with it. This is the 2D version
 * 
 * @author Gerald McAlister
 */
public class videoSystem2D extends JPanel {

	/**
	 * The serial version UID
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * The list of video items in the video system
	 */
	private SortedMap<Integer, baseVideoClass> videoItemsList;
	/**
	 * A temporary list for organizing the video items list
	 */
	private ArrayList<baseVideoClass> tempList;

	/**
	 * The constructor for the video system class
	 */
	public videoSystem2D(int width, int height) {
		videoItemsList = new TreeMap<Integer, baseVideoClass>();
		this.setBounds(0, 0, width, height);
		this.setSize(width, height);
	}

	public Dimension getPreferredSize() {
		return new Dimension(this.getSize().width, this.getSize().height);
	}

	/**
	 * Creates an image with the specified index and parameters
	 * 
	 * @param index
	 *            The index of the image
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 * @param fWidth
	 *            The width of the image frame
	 * @param fHeight
	 *            The height of the image frame
	 * @param location
	 *            The image's location
	 */
	public void createImage(int index, int layer, int x, int y, String location) {
		videoItemsList.put(index, new imgClass(location, x, y));
		videoItemsList.get(index).setType("Image");
		getImage(index).setLayer(layer);
	}

	/**
	 * Creates an image with the specified index and parameters
	 * 
	 * @param index
	 *            The index of the image
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 * @param fWidth
	 *            The width of the image frame
	 * @param fHeight
	 *            The height of the image frame
	 * @param location
	 *            The image's location
	 */
	public void createImage(int index, int layer, int x, int y, int fWidth,
			int fHeight, String location) {
		videoItemsList.put(index, new imgClass(location, x, y, fWidth, fHeight,
				0));
		videoItemsList.get(index).setType("Image");
		getImage(index).setLayer(layer);
	}

	/**
	 * Creates an image with the specified index and parameters
	 * 
	 * @param index
	 *            The index of the image
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 * @param fWidth
	 *            The width of the image frame
	 * @param fHeight
	 *            The height of the image frame
	 * @param img
	 *            The image to use
	 */
	public void createImage(int index, int layer, int x, int y, int fWidth,
			int fHeight, Image img) {
		videoItemsList.put(index, new imgClass(img, x, y, fWidth, fHeight, 0));
		videoItemsList.get(index).setType("Image");
		getImage(index).setLayer(layer);
	}

	/**
	 * Creates an image with the specified index and parameters
	 * 
	 * @param index
	 *            The index of the image
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 * @param fWidth
	 *            The width of the image frame
	 * @param fHeight
	 *            The height of the image frame
	 * @param location
	 *            The image's location
	 */
	public void createImage(int index, int layer, int x, int y, int fWidth,
			int fHeight, URL location) {
		videoItemsList.put(index, new imgClass(location, x, y, fWidth, fHeight,
				0));
		videoItemsList.get(index).setType("Image");
		getImage(index).setLayer(layer);
	}

	/**
	 * Draws text on the current window
	 * 
	 * @param index
	 *            The index of the desired text slot
	 * @param layer
	 *            The layer of the text
	 * @param x
	 *            The x position of the text
	 * @param y
	 *            The y position of the text
	 * @param text
	 *            The text to display
	 */
	public void drawText(int index, int layer, int x, int y, Object text) {
		videoItemsList.put(index, new textClass(layer, x, y, text));
		videoItemsList.get(index).setType("Text");
		getText(index).setLayer(layer);
	}

	/**
	 * Gets the image at the specified index
	 * 
	 * @param index
	 *            The index of the desired image
	 * @return The image at the specified index
	 */
	public imgClass getImage(int index) {
		try {
			if (videoItemsList.get(index).getType().equals("Image")) {
				return (imgClass) videoItemsList.get(index);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * Gets the text at the specified index
	 * 
	 * @param index
	 *            The index of the desired text
	 * @return The text at the specified index
	 */
	public textClass getText(int index) {
		try {
			if (videoItemsList.get(index).getType().equals("Text")) {
				return (textClass) videoItemsList.get(index);
			}
		} catch (Exception e) {
			return null;
		}
		return null;
	}

	/**
	 * Clears everything from this video class
	 */
	public void clear() {
		if (videoItemsList != null) {
			videoItemsList.clear();
		}
		if (tempList != null) {
			tempList.clear();
		}
	}

	/**
	 * Determines if the two items have a collision
	 * 
	 * @param index1
	 *            The index of the first video item
	 * @param index2
	 *            The index of the second video item
	 * @return Returns true if there is a collision, otherwise it's false
	 */
	public boolean collision(int index1, int index2) {
		if (getImage(index1) == null || getImage(index2) == null) {
			return false;
		}
		if (getImage(index1).getBounds().intersects(
				getImage(index2).getBounds())) {
			return (getImage(index1).isActive() && getImage(index2).isActive());
		}
		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for (int layer = 0; layer < 8; layer += 1) {
			Iterator<?> it = videoItemsList.entrySet().iterator();
			while (it.hasNext()) {
				Object obj = it.next();
				@SuppressWarnings("unchecked")
				Map.Entry<Integer, baseVideoClass> entry = (Entry<Integer, baseVideoClass>) obj;
				if (entry.getValue().getLayer() == layer) {
					entry.getValue().draw(g);
				}
			}
		}
	}
}
