package data;

import java.util.ArrayList;
import java.util.List;

import javaVectors.Vec3;

public class Scene {

	List<Sphere> spheres;
	List<SphereNode> sphereNodes;

	public Scene() {
		spheres = new ArrayList<Sphere>();
	}

	public Scene(List<Sphere> spheres) {
		this.spheres = spheres;
	}

	public void createSphereTree() {
		sphereNodes = getListAsSphereNodes(spheres);
		SphereNode boundingSphere = null;

		while (sphereNodes.size() != 1) {
			List<SphereNode> spheresToBeBounded = new ArrayList<>();

			for (int i = 0; i < 2; i++) {
				SphereNode minVol = getMinVolumeSphere();
				if (minVol != null) {
					spheresToBeBounded.add(minVol);
				}
			}

			boundingSphere = getBoundingSphere(spheresToBeBounded);
			for(SphereNode node : spheresToBeBounded) {
				node.setParent(boundingSphere);
			}
			spheresToBeBounded.addAll(getAllSpheresInBounds(boundingSphere));
			boundingSphere.setChildren(spheresToBeBounded);
			sphereNodes.removeAll(spheresToBeBounded);
			sphereNodes.add(boundingSphere);
		}
	}

	private SphereNode getMinVolumeSphere() {
		SphereNode minVolSphere = null;
		for (SphereNode sphere : sphereNodes) {
			if (minVolSphere == null || minVolSphere.getVolume() > sphere.getVolume()) {
				minVolSphere = sphere;
			}
		}
		sphereNodes.remove(minVolSphere);
		return minVolSphere;
	}

	private SphereNode getBoundingSphere(List<SphereNode> spheresToBeBounded) {
		double radius0 = spheresToBeBounded.get(0).getRadius();
		double radius1 = spheresToBeBounded.get(1).getRadius();

		Vec3 center0 = spheresToBeBounded.get(0).getCenter();
		Vec3 center1 = spheresToBeBounded.get(1).getCenter();
		
		if((center0.subtract(center1)).dot(center0.subtract(center1)) <= ((radius0 + radius1)*(radius0+radius1))) {

		}
		
		double radius = (center1.subtract(center0).length() + radius0 + radius1) / 2.0;
		Vec3 center = center0.add((center1.subtract(center0).normalize().scale((float) (radius - radius0))));
		return new SphereNode(center, radius);
	}

	private List<SphereNode> getAllSpheresInBounds(SphereNode boundingSphere) {
		List<SphereNode> spheresInBounds = new ArrayList<>();
		for (SphereNode sphere : sphereNodes) {
			double distanceBetweenCenters = sphere.getCenter().subtract(boundingSphere.getCenter()).length();
			if (distanceBetweenCenters + sphere.getRadius() < boundingSphere.getRadius()) {
				spheresInBounds.add(sphere);
			}
		}
		return spheresInBounds;
	}

	private static List<SphereNode> getListAsSphereNodes(List<Sphere> spheres) {
		List<SphereNode> sphereNodes = new ArrayList<>();
		for (Sphere sphere : spheres) {
			sphereNodes.add(new SphereNode(sphere, sphere.getCenter(), sphere.getRadius()));
		}
		return sphereNodes;
	}
	
	public void addSphere(Sphere s) {
		spheres.add(s);
	}

	public List<Sphere> getSpheres() {
		return spheres;
	}

	public List<SphereNode> getSphereNodes() {
		return sphereNodes;
	}

	public void setSphereNodes(List<SphereNode> sphereNodes) {
		this.sphereNodes = sphereNodes;
	}

}
