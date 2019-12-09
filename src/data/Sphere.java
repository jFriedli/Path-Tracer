package data;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import javaVectors.Vec3;

public class Sphere {

	Vec3 center;
	double radius;
	Vec3 color;
	Vec3 emission;
	boolean specular;
	BufferedImage img;
	
	public Sphere(Vec3 center, double radius, Vec3 color, Vec3 emission, boolean specular, String imagePath) {
		super();
		this.center = center;
		this.radius = radius;
		this.color = color;
		this.emission = emission;
		this.specular = specular;
		
		if(imagePath != null && !"".equals(imagePath)) {
			try {
			    img = ImageIO.read(new File(imagePath));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	public Vec3 getCenter() {
		return center;
	}

	public void setCenter(Vec3 center) {
		this.center = center;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public Vec3 getColor(Vec3 hitpoint) {
		if(hasImage()) {
			Vec3 cord = new Vec3(hitpoint.x() - center.x(), hitpoint.y() - center.y(), hitpoint.z() - center.z()).normalize();
			int s = (int) ((cord.x() * img.getWidth() >= 0) ? cord.x() * img.getWidth() : cord.x() * img.getWidth() * -1);
			int t = (int) ((cord.y() * img.getHeight() >= 0) ? cord.y() * img.getHeight() : cord.y() * img.getHeight() * -1);
			s = s > img.getWidth() - 1 ? img.getWidth() - 1 : s;
			t = t > img.getHeight() - 1 ? img.getHeight() - 1 : t;
			Color color = new Color(img.getRGB(s, t));
			return new Vec3(color.getRed(), color.getGreen(), color.getBlue());
		}
			
		return color;
	}

	public void setColor(Vec3 color) {
		this.color = color;
	}

	public Vec3 getEmission() {
		return emission;
	}

	public void setEmission(Vec3 emission) {
		this.emission = emission;
	}

	public boolean isSpecular() {
		return specular;
	}

	public void setSpecular(boolean specular) {
		this.specular = specular;
	}
	
	public double getVolume() {
		return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
	}
	
	private boolean hasImage() {
		return img != null;
	}
}
