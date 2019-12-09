package data;

import javaVectors.Vec3;

public class CornellBox {
	
	Vec3 eye;
	Vec3 lookAt;
	int fov;
	Scene scene;
	
	public CornellBox(Vec3 eye, Vec3 lookAt, int fov, Scene scene) {
		super();
		this.eye = eye;
		this.lookAt = lookAt;
		this.fov = fov;
		this.scene = scene;
	}
	
}
