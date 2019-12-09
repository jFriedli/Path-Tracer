package data;

import java.util.List;

import javaVectors.Vec3;

public class SphereNode {
	SphereNode parent;
	Sphere sphere;
	List<SphereNode> children;
	Vec3 center;
	double radius;
	double closestLambda;
	
	public SphereNode(Vec3 center, double radius) {
		this(null, null, center, radius, 0);
	}
	
	public SphereNode(Sphere sphere, Vec3 center, double radius) {
		this(sphere, null, center, radius, 0);
	}
	
	public SphereNode(Sphere sphere, List<SphereNode> children, Vec3 center, double radius, double closestLambda) {
		super();
		this.sphere = sphere;
		this.children = children;
		this.center = center;
		this.radius = radius;
		this.closestLambda = closestLambda;
	}

	public List<SphereNode> getChildren() {
		return children;
	}
	
	public void setChildren(List<SphereNode> children) {
		this.children = children;
	}
	
	public double getClosestLambda() {
		return closestLambda;
	}
	
	public void setClosestLambda(double closestLambda) {
		this.closestLambda = closestLambda;
	}

	public Sphere getSphere() {
		return sphere;
	}

	public void setSphere(Sphere sphere) {
		this.sphere = sphere;
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
	
	public double getVolume() {
		return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
	}

	public SphereNode getParent() {
		return parent;
	}

	public void setParent(SphereNode parent) {
		this.parent = parent;
	}
}
