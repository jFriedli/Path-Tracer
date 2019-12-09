package data;

import java.awt.*;
import java.awt.image.*;
import javax.swing.*;

import javaVectors.Vec3;

@SuppressWarnings("serial")
public class Raster extends JPanel {

	public final int WIDTH = 500;
	public final int HEIGHT = 500;

	Vec3[][] pixels;

	public Raster() {
		this(null);
	}

	public Raster(Vec3[][] pixels) {
		this.pixels = pixels;
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
	}

	@Override
	public void paintComponent(Graphics g) {
		final BufferedImage image;
		int[] iArray = { 0, 0, 0, 255 };

		image = (BufferedImage) createImage(WIDTH, HEIGHT);
		WritableRaster raster = image.getRaster();
		for (int y = 0; y < HEIGHT; y++) {
			for (int x = 0; x < WIDTH; x++) {
				iArray[0] = (int) pixels[x][y].x;
				iArray[1] = (int) pixels[x][y].y;
				iArray[2] = (int) pixels[x][y].z;
				raster.setPixel(x, y, iArray);
			}
		}
		
		g.drawImage(image, 0, 0, getWidth() , getHeight() , null);
	}

	public Vec3[][] getPixels() {
		return pixels;
	}

	public void setPixels(Vec3[][] pixels2) {
		this.pixels = pixels2;
	}

}