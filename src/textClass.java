import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

/**
 * A system for implementing text. This can be used to draw text on the screen
 * and retrieve certain properties of the text
 * 
 * @author Gerald McAlister
 * 
 */
public class textClass extends baseVideoClass {

	/**
	 * The previous color of the graphics component. Used so that the color does
	 * not change when the text color changes
	 */
	private Color oldColor = Color.white;

	/**
	 * The x position of the text
	 */
	private int x = 0;
	/**
	 * The y position of the text
	 */
	private int y = 0;
	/**
	 * The text to display
	 */
	private Object text = "";
	/**
	 * The color of the text
	 */
	private Color color = Color.white;
	/**
	 * The size of the font
	 */
	private int size = 12;
	/**
	 * The style of the font
	 */
	private int style = Font.PLAIN;
	/**
	 * The name of the font
	 */
	private String name = Font.SANS_SERIF;
	/**
	 * Tells whether the text is active. This determines whether or not to
	 * display the text
	 */
	private boolean active = false;

	/**
	 * The constructor for the text class
	 * 
	 * @param layer
	 *            The layer to draw the text on
	 * @param x
	 *            The x position for the text
	 * @param y
	 *            The y position for the text
	 * @param text
	 *            The text to display
	 */
	public textClass(int layer, int x, int y, Object text) {
		this.x = x;
		this.y = y;
		this.text = text;
		this.setLayer(layer);
		active = true;
	}

	/**
	 * Destroys the text object
	 */
	public void destroy() {
		text = "";
		active = false;
	}

	/**
	 * Tells whether the text is active
	 * 
	 * @return The active status of the text
	 */
	public boolean isActive() {
		return active;
	}

	/**
	 * Sets the x position of the text
	 * 
	 * @param x
	 *            The new x position to use
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Gets the x position of the text
	 * 
	 * @return The current x position of the text
	 */
	public int getX() {
		return this.x;
	}

	/**
	 * Sets the y position of the text
	 * 
	 * @param y
	 *            The new y position to use
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the y position of the text
	 * 
	 * @return The current y position of the text
	 */
	public int getY() {
		return this.y;
	}

	/**
	 * Sets the text to display for this textClass object
	 * 
	 * @param text
	 *            The text to use
	 */
	public void setText(Object text) {
		this.text = text;
	}

	/**
	 * Gets the current text as an object
	 * 
	 * @return The current text as an object
	 */
	public Object getText() {
		return this.text;
	}

	/**
	 * Sets the color of the text
	 * 
	 * @param color
	 *            The new color to use for the text
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Gets the color of the text
	 * 
	 * @return The color of the text
	 */
	public Color getColor() {
		return this.color;
	}

	/**
	 * Sets the point size of the text
	 * 
	 * @param size
	 *            The point size of the text
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * Gets the point size of the text
	 * 
	 * @param size
	 *            The point size of the text
	 */
	public int getSize() {
		return this.size;
	}

	/**
	 * Sets the style of the text
	 * 
	 * @param size
	 *            The style of the text
	 */
	public void setStyle(int style) {
		this.style = style;
	}

	/**
	 * Gets the style of the text
	 * 
	 * @param size
	 *            The style of the text
	 */
	public int getStyle() {
		return this.style;
	}

	/**
	 * Sets the name of the text
	 * 
	 * @param size
	 *            The name of the text
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the name of the text
	 * 
	 * @param size
	 *            The name of the text
	 */
	public String getName() {
		return this.name;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see baseVideoClass#draw(java.awt.Graphics)
	 */
	public void draw(Graphics g) {
		if (this.active) {
			oldColor = g.getColor();
			g.setColor(color);
			g.setFont(new Font(name, style, size));
			g.drawString(text.toString(), x, y + size);
			g.setColor(oldColor);
		}
	}
}
