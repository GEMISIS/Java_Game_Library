import java.awt.Graphics;

/**
 * The base class for video items. These items can be images,
 * 
 * @author Gerald McAlister
 * 
 */
public class baseVideoClass {

	/**
	 * The type of video item for this object
	 */
	private String type = "";
	/**
	 * The layer for how far back the video item is
	 */
	private int layer = 0;

	/**
	 * Gets the type of video item for this object
	 * 
	 * @return The type of video item
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the type of video item for this object
	 * 
	 * @param type
	 *            The new type of the video item
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the video objects current layer.
	 * 
	 * @return The video objects current layer
	 */
	public int getLayer() {
		return this.layer;
	}

	/**
	 * Sets the current layer of the video object.
	 * 
	 * @param layer
	 *            The video object's new layer
	 */
	public void setLayer(int layer) {
		this.layer = layer;
	}

	/**
	 * The draw method for the video object. It is here for each object to
	 * decide how to use it.
	 * 
	 * @param g
	 *            The Graphics argument. Used to actually draw each object.
	 */
	public void draw(Graphics g) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.type;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object obj) {
		try {
			if (!obj.getClass().equals(baseVideoClass.class)) {
				throw new Exception();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return (obj != null)
				&& ((baseVideoClass) obj).getType().equals(this.type);
	}
}
