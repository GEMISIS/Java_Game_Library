import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.net.URL;

/**
 * A system for implementing images. This can be used to load images and
 * retrieve certain properties for them.
 * 
 * @author Gerald McAlister
 */
public class imgClass extends baseVideoClass {

	/**
	 * The Image that the imgClass will use.
	 */
	private Image img;
	/**
	 * Tells whether the image is active
	 */
	private boolean active = false;
	/**
	 * The x position of the image.
	 */
	private int x = 0;
	/**
	 * The y position of the image.
	 */
	private int y = 0;
	/**
	 * The y position of the image's frame, relative to the image
	 */
	private int frameX = 0;
	/**
	 * The y position of the image's frame, relative to the image
	 */
	private int frameY = 0;
	/**
	 * The width of the image's frame
	 */
	private int frameWidth = 0;
	/**
	 * The height of the image's frame
	 */
	private int frameHeight = 0;
	/**
	 * The bounds of the image
	 */
	private Rectangle bounds = new Rectangle();
	/**
	 * The frame number of the images current frame
	 */
	private int frame = 0;
	/**
	 * The rotation of the image relative to the center of the image
	 */
	private double rotation = 0;
	/**
	 * How much to scale the image by (should be 1 if no scale)
	 */
	private double scale = 1;
	/**
	 * Tells whether the animations are on or not
	 */
	private boolean animationsOn = false;
	/**
	 * The starting frame for animations
	 */
	private int startFrame = 0;
	/**
	 * The ending frame for animations
	 */
	private int endFrame = 0;
	/**
	 * The counter to use for displaying animations
	 */
	private int counter = 0;
	/**
	 * The frames per second for animations
	 */
	private int framesPerSecond = 1;
	/**
	 * The horizontal flip value
	 */
	private boolean hFlip = false;
	/**
	 * The vertical flip value
	 */
	private boolean vFlip = false;
	/**
	 * The pixel grabber used to get and set pixels from the image
	 */
	private PixelGrabber pg = null;
	/**
	 * The pixel data from the image
	 */
	private int[] pixelData = null;
	/**
	 * A temporary BufferedImage used for replacing pixels on the image
	 */
	private BufferedImage tempBImg;
	/**
	 * Used to draw the image with rotation, translation, etc. implemented
	 */
	private Graphics2D tempGraphics;
	/**
	 * The location of the image
	 */
	private String location;

	/**
	 * Gets the location of the image file
	 * 
	 * @return The location of the image file
	 */
	public String getLocation() {
		return location;
	}

	/**
	 * Gets the location of the image file
	 * 
	 * @param location
	 *            The location of the image file
	 */
	public void setLocation(String location) {
		this.location = location;
	}

	/**
	 * Creates a new instance of imgClass for use
	 * 
	 * @param imgLocation
	 *            The location of the image
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 * @param frameWidth
	 *            The width of the image's frame
	 * @param frameHeight
	 *            The height of the image's frame
	 * @param frame
	 *            The frame number
	 */
	public imgClass(String imgLocation, int x, int y, int frameWidth,
			int frameHeight, int frame) {
		img = Toolkit.getDefaultToolkit().getImage(
				getClass().getResource(imgLocation));
		this.x = x;
		this.y = y;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		bounds = new Rectangle(x, y, frameWidth, frameHeight);
		this.frame = frame;
		this.updateFrame();
		active = true;
		location = imgLocation;
	}

	/**
	 * Creates a new instance of imgClass for use
	 * 
	 * @param imgLocation
	 *            The location of the image
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 */
	public imgClass(String imgLocation, int x, int y) {
		img = Toolkit.getDefaultToolkit().getImage(imgLocation);
		this.x = x;
		this.y = y;
		while ((this.frameWidth = img.getWidth(null)) == -1)
			;
		this.frameHeight = img.getHeight(null);
		bounds = new Rectangle(x, y, frameWidth, frameHeight);
		this.frame = 0;
		this.updateFrame();
		active = true;
		location = imgLocation;
	}

	/**
	 * Creates a new instance of imgClass for use
	 * 
	 * @param img
	 *            The image to use
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 * @param frameWidth
	 *            The width of the image's frame
	 * @param frameHeight
	 *            The height of the image's frame
	 * @param frame
	 *            The frame number
	 */
	public imgClass(Image img, int x, int y, int frameWidth, int frameHeight,
			int frame) {
		this.img = img;
		this.x = x;
		this.y = y;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		bounds = new Rectangle(x, y, frameWidth, frameHeight);
		this.frame = frame;
		this.updateFrame();
		active = true;
	}

	/**
	 * Creates a new instance of imgClass for use
	 * 
	 * @param img
	 *            The image to use.
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 */
	public imgClass(Image img, int x, int y) {
		this.img = img;
		this.x = x;
		this.y = y;
		while ((this.frameWidth = img.getWidth(null)) == -1)
			;
		this.frameHeight = img.getHeight(null);
		bounds = new Rectangle(x, y, frameWidth, frameHeight);
		this.frame = 0;
		this.updateFrame();
		active = true;
	}

	/**
	 * Creates a new instance of imgClass for use
	 * 
	 * @param imgLocation
	 *            The location of the image
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 * @param rotation
	 *            The rotation of the image to use
	 * @param frameWidth
	 *            The width of the image's frame
	 * @param frameHeight
	 *            The height of the image's frame
	 * @param frame
	 *            The frame number
	 */
	public imgClass(String imgLocation, int x, int y, double rotation,
			int frameWidth, int frameHeight, int frame) {
		img = Toolkit.getDefaultToolkit().getImage(imgLocation);
		this.x = x;
		this.y = y;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		bounds = new Rectangle(x, y, frameWidth, frameHeight);
		this.frame = frame;
		this.updateFrame();
		this.rotation = rotation;
		location = imgLocation;
	}

	/**
	 * Creates a new instance of imgClass for use
	 * 
	 * @param imgLocation
	 *            The location of the image
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 * @param rotation
	 *            The rotation of the image to use
	 */
	public imgClass(String imgLocation, int x, int y, double rotation) {
		img = Toolkit.getDefaultToolkit().getImage(imgLocation);
		this.x = x;
		this.y = y;
		while ((this.frameWidth = img.getWidth(null)) == -1)
			;
		this.frameHeight = img.getHeight(null);
		bounds = new Rectangle(x, y, frameWidth, frameHeight);
		this.updateFrame();
		this.rotation = rotation;
		location = imgLocation;
	}

	/**
	 * Creates a new instance of imgClass for use
	 * 
	 * @param imgLocation
	 *            The location of the image
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 * @param frameWidth
	 *            The width of the image's frame
	 * @param frameHeight
	 *            The height of the image's frame
	 * @param frame
	 *            The frame number
	 */
	public imgClass(URL imgLocation, int x, int y, int frameWidth,
			int frameHeight, int frame) {
		img = Toolkit.getDefaultToolkit().getImage(imgLocation);
		this.x = x;
		this.y = y;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		bounds = new Rectangle(x, y, frameWidth, frameHeight);
		this.frame = frame;
		this.updateFrame();
		active = true;
		location = imgLocation.getFile();
	}

	/**
	 * Creates a new instance of imgClass for use
	 * 
	 * @param imgLocation
	 *            The location of the image
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 * @param rotation
	 *            The rotation of the image to use
	 * @param frameWidth
	 *            The width of the image's frame
	 * @param frameHeight
	 *            The height of the image's frame
	 * @param frame
	 *            The frame number
	 */
	public imgClass(URL imgLocation, int x, int y, double rotation,
			int frameWidth, int frameHeight, int frame) {
		img = Toolkit.getDefaultToolkit().getImage(imgLocation);
		this.x = x;
		this.y = y;
		this.frameWidth = frameWidth;
		this.frameHeight = frameHeight;
		bounds = new Rectangle(x, y, frameWidth, frameHeight);
		this.frame = frame;
		this.updateFrame();
		this.rotation = rotation;
		active = true;
		location = imgLocation.getFile();
	}

	/**
	 * Creates a new instance of imgClass for use
	 * 
	 * @param imgLocation
	 *            The location of the image
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 * @param rotation
	 *            The rotation of the image to use
	 */
	public imgClass(URL imgLocation, int x, int y) {
		img = Toolkit.getDefaultToolkit().getImage(imgLocation);
		this.x = x;
		this.y = y;
		while ((this.frameWidth = img.getWidth(null)) == -1)
			;
		this.frameHeight = img.getHeight(null);
		bounds = new Rectangle(x, y, frameWidth, frameHeight);
		this.updateFrame();
		location = imgLocation.getFile();
	}

	/**
	 * Creates a new instance of imgClass for use
	 * 
	 * @param imgLocation
	 *            The location of the image
	 * @param x
	 *            The x position of the image
	 * @param y
	 *            The y position of the image
	 * @param rotation
	 *            The rotation of the image to use
	 */
	public imgClass(URL imgLocation, int x, int y, double rotation) {
		img = Toolkit.getDefaultToolkit().getImage(imgLocation);
		this.x = x;
		this.y = y;
		while ((this.frameWidth = img.getWidth(null)) == -1)
			;
		this.frameHeight = img.getHeight(null);
		bounds = new Rectangle(x, y, frameWidth, frameHeight);
		this.updateFrame();
		this.rotation = rotation;
		location = imgLocation.getFile();
	}

	/**
	 * Gets the image that the imgClass class is using
	 * 
	 * @return The image that the imgClass class is using
	 */
	public Image getImg() {
		return img;
	}

	/**
	 * Sets the image that the imgClass class is using
	 * 
	 * @param img
	 *            The new image to use
	 */
	public void setImg(Image img) {
		pixelData = null;
		this.img = img;
	}

	/**
	 * Tells whether the image is active
	 * 
	 * @return The active status of the image
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Gets the x position of the image
	 * 
	 * @return The x position of the image
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Sets whether the image is active or not.
	 * 
	 * @param active
	 *            The new value for whether the image is active or not.
	 */
	public void setActive(boolean active) {
		this.active = active;
	}

	/**
	 * Sets the x position of the image
	 * 
	 * @param x
	 *            The new x position to assign
	 */
	public void setX(int x) {
		bounds.x = x;
		this.x = x;
	}

	/**
	 * Gets the y position of the image
	 * 
	 * @return The y position of the image
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Sets the y position of the image
	 * 
	 * @param y
	 *            The new y position of the image
	 */
	public void setY(int y) {
		bounds.y = y;
		this.y = y;
	}

	/**
	 * Gets the image's rotation
	 * 
	 * @return The image's rotation
	 */
	public double getRotation() {
		return this.rotation;
	}

	/**
	 * Sets the image's rotation
	 * 
	 * @param rotation
	 *            The image's new rotation
	 */
	public void setRotation(double rotation) {
		this.rotation = rotation;
	}

	/**
	 * Gets how much to scale the image by
	 * 
	 * @return How much to scale the image by
	 */
	public double getScale() {
		return this.scale;
	}

	/**
	 * Sets how much to scale the image by
	 * 
	 * @param scale
	 *            The new scale value
	 */
	public void setScale(double scale) {
		this.scale = scale;
	}

	/**
	 * Get's the image's current frame
	 * 
	 * @return The image's current frame
	 */
	public int getFrame() {
		return this.frame;
	}

	/**
	 * Sets the image's current frame
	 * 
	 * @param frame
	 *            The image's new frame
	 */
	public void setFrame(int frame) {
		this.frame = frame;
	}

	/**
	 * Gets the horizontal flip value
	 * 
	 * @return The horizontal flip value
	 */
	public boolean getHorizontalFlip() {
		return hFlip;
	}

	/**
	 * Sets the horizontal flip to the desired value
	 * 
	 * @param enabled
	 *            The new value for the horizontal flip
	 */
	public void setHorizontalFlip(boolean enabled) {
		hFlip = enabled;
	}

	/**
	 * Gets the vertical flip value
	 * 
	 * @return The vertical flip value
	 */
	public boolean getVerticalFlip() {
		return vFlip;
	}

	/**
	 * Sets the vertical flip to the desired value
	 * 
	 * @param enabled
	 *            The new value for the vertical flip
	 */
	public void setVerticalFlip(boolean enabled) {
		vFlip = enabled;
	}

	/**
	 * Gets the boundaries of the image.
	 * 
	 * @return The boundaries of the image
	 */
	public Rectangle getBounds() {
		return new Rectangle(bounds.x, bounds.y, (int) (bounds.width * scale),
				(int) (bounds.height * scale));
	}

	/**
	 * Draws the image
	 * 
	 * @param g
	 *            The Graphics variable to use
	 */
	public void draw(Graphics g) {
		if (img != null && this.active) {
			if (animationsOn) {
				if (counter >= 60 / framesPerSecond) {
					if (frame >= endFrame) {
						frame = startFrame;
					} else {
						frame += 1;
					}
					counter = 0;
				} else {
					counter += 1;
				}
			}
			updateFrame();
			tempGraphics = (Graphics2D) g;
			tempGraphics.translate(0, 0);
			tempGraphics.rotate(rotation * Math.PI / 180, frameWidth / 2,
					frameHeight / 2);
			int posX = x;
			int posY = y;
			int posW = x + (int) (frameWidth * scale);
			int posH = y + (int) (frameHeight * scale);
			if (hFlip) {
				posX = x + (int) (frameWidth * scale);
				posW = x;
			}
			if (vFlip) {
				posY = y + (int) (frameHeight * scale);
				posH = y;
			}
			tempGraphics.drawImage(img, posX, posY, posW, posH, frameX, frameY,
					frameX + frameWidth, frameY + frameHeight, null);
			tempGraphics.rotate(-rotation, frameWidth / 2, frameHeight / 2);
		}
	}

	/**
	 * Destroys the image
	 */
	public void destroy() {
		img = null;
		pixelData = null;
		active = false;
		// Runtime.getRuntime().freeMemory();
	}

	/**
	 * Updates the image's frame
	 */
	public void updateFrame() {
		int currentFrame = 0;
		for (int y = 0; y < this.img.getHeight(null); y += this.frameHeight) {
			for (int x = 0; x < this.img.getWidth(null); x += this.frameWidth) {
				if (currentFrame == this.frame) {
					this.frameX = x;
					this.frameY = y;
					return;
				} else {
					currentFrame += 1;
				}
			}
		}
		this.frameX = 0;
		this.frameY = 0;
	}

	/**
	 * Starts the animation for the image with the specified parameters
	 * 
	 * @param startingFrame
	 *            The starting animation frame
	 * @param endingFrame
	 *            The ending animation frame
	 * @param fps
	 *            The frames per second to use
	 */
	public void startAnimations(int startingFrame, int endingFrame, int fps) {
		this.startFrame = startingFrame;
		this.endFrame = endingFrame;
		this.framesPerSecond = fps;
		this.animationsOn = true;
	}

	/**
	 * Stops the animation for the image
	 */
	public void stopAnimations() {
		this.animationsOn = false;
	}

	/**
	 * Gets the color of the pixel at the desired position
	 * 
	 * @param x
	 *            The x position
	 * @param y
	 *            The y position
	 * @return The color of the pixel
	 */
	public Color getPixel(int x, int y) {
		if (pixelData == null) {

			pixelData = new int[frameWidth * frameHeight];
			pg = new PixelGrabber(img, frameX, frameY, frameWidth, frameHeight,
					pixelData, 0, frameWidth);
			try {
				pg.grabPixels();
			} catch (InterruptedException e) {
			}
		}
		int alpha = (pixelData[x + (frameWidth * y)] >> 24) & 0xff;
		int red = (pixelData[x + (frameWidth * y)] >> 16) & 0xff;
		int green = (pixelData[x + (frameWidth * y)] >> 8) & 0xff;
		int blue = (pixelData[x + (frameWidth * y)]) & 0xff;
		return new Color(red, green, blue, alpha);
	}

	/**
	 * Sets the color of the pixel at the desired position
	 * 
	 * @param x
	 *            The x position
	 * @param y
	 *            The y position
	 * @param color
	 *            The new color for the pixel
	 */
	public void setPixel(int x, int y, Color color) {
		if (img != null) {
			tempBImg = new BufferedImage(frameWidth, frameHeight,
					BufferedImage.TYPE_INT_ARGB);
			tempGraphics = tempBImg.createGraphics();
			tempGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			tempGraphics.drawImage(img, 0, 0, frameWidth, frameHeight, frameX,
					frameY, frameWidth, frameHeight, null);
			tempGraphics.dispose();
			tempBImg.setRGB(x, y, color.getRGB());
			img = tempBImg;
			pixelData = null;
		}
	}

	/**
	 * Sets an area of the image with the specified data.
	 * 
	 * @param x
	 *            The x position to start at.
	 * @param y
	 *            The y position to start at.
	 * @param width
	 *            The width of the area.
	 * @param height
	 *            The height of the area.
	 * @param data
	 *            The data for the image area.
	 */
	public void setPixelData(int x, int y, int width, int height, int[] data) {
		if (img != null) {
			tempBImg = new BufferedImage(frameWidth, frameHeight,
					BufferedImage.TYPE_INT_ARGB);
			tempGraphics = tempBImg.createGraphics();
			tempGraphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
					RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			tempGraphics.drawImage(img, 0, 0, frameWidth, frameHeight, frameX,
					frameY, frameWidth, frameHeight, null);
			tempGraphics.dispose();
			tempBImg.setRGB(x, y, width, height, data, 0, width);
			img = tempBImg;
			pixelData = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see baseVideoClass#toString()
	 */
	public String toString() {
		return this.location;
	}

	public boolean equals(Object obj) {
		try {
			if (!obj.getClass().equals(imgClass.class)) {
				throw new Exception();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (obj != null)
				&& ((imgClass) obj).getLocation().equals(this.location);
	}
}
