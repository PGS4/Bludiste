package bludiste;

import java.util.ArrayList;

/*import java.awt.Image;
import java.awt.image.ColorModel;
import java.awt.image.ImageObserver;
import java.awt.image.DirectColorModel;
import java.awt.image.IndexColorModel;
import java.awt.image.PixelGrabber;

public class Map implements ImageObserver {
	private Image map = null;
	private int width;
	private int height;
	private Object pixels = null;
	private int numOfColors = 0;
	ColorModel colorModel;

	public Map(Image img) {
		map = img;
	}

	public void grabPixels() throws Exception {
		width = map.getWidth(null);
		height = map.getHeight(null);
		PixelGrabber grabber = new PixelGrabber(map, 0, 0, width, height, false);
		if (grabber.grabPixels()) {
			try {
				grabber.getPixels();
			} catch (Exception e) {
				System.out.println("PixelGrabber fail");
			}

			pixels = grabber.getPixels();// dostane pixely
			colorModel = grabber.getColorModel();// dostane colormodel
			if (colorModel instanceof IndexColorModel) {
				numOfColors = ((IndexColorModel) colorModel).getMapSize();
			}
		}
	}

	public Object getPixels() {
		return pixels;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getNumOfColors() {
		return numOfColors;
	}

	public int getRed(int pixel) {
		return ((DirectColorModel)colorModel).getRed(pixel);
	}

	public int getBlue(int pixel) {
		return ((DirectColorModel)colorModel).getBlue(pixel);
	}

	public int getGreen(int pixel) {
		return ((DirectColorModel)colorModel).getGreen(pixel);
	}

	public void destroy() {
		map = null;
		pixels = null;
	}

	public boolean imageUpdate(Image arg0, int arg1, int arg2, int arg3,
			int arg4, int arg5) {
		// TODO Auto-generated method stub
		return true;
	}
}*/
public class Map{
	ArrayList<String> bricks;
	public Map(ArrayList<String> bricks){
	
	}
}
