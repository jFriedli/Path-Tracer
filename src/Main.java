import java.awt.BorderLayout;
import java.util.List;
import java.util.Random;

import javax.swing.JFrame;

import data.Raster;
import data.Scene;
import data.Sphere;
import data.SphereNode;
import javaVectors.Vec2;
import javaVectors.Vec3;

public class Main {
	static boolean BVH = false;
	static boolean BVH_SCENE_TEST = false;

	static boolean APERTURE = false;
	static boolean APERTURE_SCENE_TEST = false;

	static boolean MS = false;
	static boolean BITMAP = false;
	static boolean TEST = false;

	static int ITERATIONS = 2000;

	static Vec3 eye = new Vec3(0, 0, -4);
	static Vec3 lookAt = new Vec3(0, 0, 6);

	static Vec3 up = new Vec3(0, 1, 0);
	static int fov = 36;
	static double fovRadient = fov * (Math.PI / 180.0);

	static Raster rt;

	public static void main(String[] args) {

		Vec3 red = new Vec3(255, 0, 0);
		Vec3 blue = new Vec3(0, 0, 255);
		Vec3 gray = new Vec3(220, 220, 220);
		Vec3 lightGray = new Vec3(240, 240, 220);
		Vec3 yellow = new Vec3(255, 255, 0);
		Vec3 lightCyan = new Vec3(0, 255, 255);
		Vec3 black = new Vec3(0, 0, 0);
		Vec3 white = new Vec3(255, 255, 255);

		String image1 = "C:\\Users\\JonasFriedli\\Desktop\\7.jpg";
		String image2 = "C:\\Users\\JonasFriedli\\Desktop\\papier-peint-two-birds-mc-escher.jpg";

		Scene scene = new Scene();

		if (BITMAP) {
			scene.addSphere(new Sphere(new Vec3(-1001, 0, 0), 1000, gammaCorrection(lightGray), black, true, null));
			scene.addSphere(new Sphere(new Vec3(1001, 0, 0), 1000, gammaCorrection(lightGray), black, true, null));
			scene.addSphere(new Sphere(new Vec3(0, 0, 1001), 1000, gammaCorrection(lightGray), black, true, null));
			scene.addSphere(new Sphere(new Vec3(0, -1001, 0), 1000, gammaCorrection(lightGray), black, false, null));
			scene.addSphere(new Sphere(new Vec3(0, 1001, 0), 1000, gammaCorrection(lightGray), black, false, null));
			scene.addSphere(new Sphere(new Vec3(0.01, -0.5, 0), 0.5, gammaCorrection(yellow), black, false, image1));
			scene.addSphere(new Sphere(new Vec3(-0.01, -0.5, 0), 0.5, gammaCorrection(yellow), black, false, image2));
			scene.addSphere(new Sphere(new Vec3(-10.99, 0, 0), 10, gammaCorrection(red), black, false, null));
			scene.addSphere(new Sphere(new Vec3(-10.99, 1, 0), 10, gammaCorrection(red), black, false, null));
			scene.addSphere(new Sphere(new Vec3(-10.99, -1, 0), 10, gammaCorrection(red), black, false, null));
			scene.addSphere(new Sphere(new Vec3(-10.99, 0.5, 0), 10, gammaCorrection(red), black, false, null));
			scene.addSphere(new Sphere(new Vec3(-10.99, -0.5, 0), 10, gammaCorrection(red), black, false, null));

			scene.addSphere(new Sphere(new Vec3(10.99, 0, 0), 10, gammaCorrection(white), black, false, null));
			scene.addSphere(new Sphere(new Vec3(10.99, 1, 0), 10, gammaCorrection(white), black, false, null));
			scene.addSphere(new Sphere(new Vec3(10.99, -1, 0), 10, gammaCorrection(white), black, false, null));
			scene.addSphere(new Sphere(new Vec3(10.99, 0.5, 0), 10, gammaCorrection(white), black, false, null));
			scene.addSphere(new Sphere(new Vec3(10.99, -0.5, 0), 10, gammaCorrection(white), black, false, null));
			scene.addSphere(new Sphere(new Vec3(0, 10.99, 0), 10, gammaCorrection(white),
					gammaCorrection(white).scale((float) 10), false, null));

		} else if (BVH || BVH_SCENE_TEST) {
			scene.addSphere(new Sphere(new Vec3(0, -10.95, 0), 10, gammaCorrection(white),
					gammaCorrection(white).scale((float) 10), false, null));
			scene.addSphere(new Sphere(new Vec3(0, -1001, 0), 1000, gammaCorrection(lightGray), black, false, null));

			double x = 0;
			double y = 0;

			while (x <= 1) {
				y = 0;

				while (y <= 1) {
					int r = (int) (Math.random() * 255);
					int g = (int) (Math.random() * 255);
					int b = (int) (Math.random() * 255);

					r = (r >= 50) ? r : r + 100;
					g = (g >= 50) ? g : g + 100;
					b = (b >= 50) ? b : b + 100;

					scene.addSphere(
							new Sphere(new Vec3(x, y, 0), 0.1, gammaCorrection(new Vec3(r, g, b)), black, false, null));
					scene.addSphere(new Sphere(new Vec3(-x, y, 0), 0.1, gammaCorrection(new Vec3(r, g, b)), black,
							false, null));
					y += 0.5;
				}
				x += 0.5;
			}
		} else if (APERTURE || APERTURE_SCENE_TEST) {
			scene.addSphere(new Sphere(new Vec3(0, 0, 1001), 1000, gammaCorrection(lightGray), black, false, null));
			scene.addSphere(new Sphere(new Vec3(0, -1001, 0), 1000, gammaCorrection(lightGray), black, false, null));
			scene.addSphere(new Sphere(new Vec3(0, 1001, 0), 1000, gammaCorrection(lightGray), black, false, null));
			scene.addSphere(new Sphere(new Vec3(1, 0, 1), 0.5, gammaCorrection(gray), black, false, null));
			scene.addSphere(new Sphere(new Vec3(0.5, 0, 0.5), 0.5, gammaCorrection(lightCyan), black, false, null));
			scene.addSphere(new Sphere(new Vec3(0, 0, 0), 0.5, gammaCorrection(red), black, false, null));
			scene.addSphere(new Sphere(new Vec3(-0.5, 0, -0.5), 0.5, gammaCorrection(yellow), black, false, null));
			scene.addSphere(new Sphere(new Vec3(-1, 0, -1), 0.5, gammaCorrection(blue), black, false, null));
			scene.addSphere(new Sphere(new Vec3(0, -10.99, 0), 10, gammaCorrection(white),
					gammaCorrection(white).scale((float) 10), false, null));
		} else {

//			CornellBox
			scene.addSphere(new Sphere(new Vec3(-1001, 0, 0), 1000, gammaCorrection(lightGray), black, false, null));
			scene.addSphere(new Sphere(new Vec3(1001, 0, 0), 1000, gammaCorrection(lightGray), black, false, null));
			scene.addSphere(new Sphere(new Vec3(0, 0, 1001), 1000, gammaCorrection(lightGray), black, false, null));
			scene.addSphere(new Sphere(new Vec3(0, -1001, 0), 1000, gammaCorrection(lightGray), black, false, null));
			scene.addSphere(new Sphere(new Vec3(0, 1001, 0), 1000, gammaCorrection(lightGray), black, false, null));
			scene.addSphere(new Sphere(new Vec3(-0.6, 0.7, -0.6), 0.3, gammaCorrection(yellow), black, false, null));
			scene.addSphere(new Sphere(new Vec3(0.3, 0.4, 0.3), 0.6, gammaCorrection(yellow), black, false, null));
			scene.addSphere(new Sphere(new Vec3(0, -10.99, 0), 10, gammaCorrection(white),
					gammaCorrection(white).scale((float) 10), false, null));
		}

		rt = new Raster();

		Vec3[][] pixels = new Vec3[rt.WIDTH][rt.HEIGHT];

		// Box 1 top left
		for (int x = 0; x < rt.WIDTH / 2; x++) {
			for (int y = 0; y < rt.HEIGHT / 2; y++) {
				pixels[x][y] = getPixels(scene, x, y, 1);
			}
		}

		// Box 2 top right
		for (int x = rt.WIDTH / 2; x < rt.WIDTH; x++) {
			for (int y = 0; y < rt.HEIGHT / 2; y++) {
				pixels[x][y] = getPixels(scene, x, y, 2);
			}
		}

		// Box 3 bottom left
		for (int x = 0; x < rt.WIDTH / 2; x++) {
			for (int y = rt.HEIGHT / 2; y < rt.HEIGHT; y++) {
				pixels[x][y] = getPixels(scene, x, y, 3);
			}
		}

		// Box 4 bottom right
		for (int x = rt.WIDTH / 2; x < rt.WIDTH; x++) {
			for (int y = rt.HEIGHT / 2; y < rt.HEIGHT; y++) {
				pixels[x][y] = getPixels(scene, x, y, 4);
			}
		}

		rt.setPixels(pixels);

		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JFrame.setDefaultLookAndFeelDecorated(true);
		f.setResizable(false);
		f.add(rt, BorderLayout.CENTER);
		f.pack();
		f.setVisible(true);
	}

	private static Vec3 getPixels(Scene scene, int x, int y, int box) {
		int iterations = ITERATIONS;
		Vec2 normalizedPixels = getNormalizedCenteredPixelVector(x, y, box);
		Random ran = new Random();
//		Vec3 eyeRayDirection = createEyeRay(eye, lookAt, normalizedPixels);
		Vec3 eyeRayDirection = createEyeRay(eye, lookAt, normalizedPixels.add(
				new Vec2(ran.nextGaussian() * (1f / (2 * rt.WIDTH)), ran.nextGaussian() * (1f / (2 * rt.HEIGHT)))));
		Vec3 color = calcLightFrom(scene, eyeRayDirection, eye);

		for (int i = 1; i < iterations; i++) {

			eyeRayDirection = createEyeRay(eye, lookAt, normalizedPixels.add(
					new Vec2(ran.nextGaussian() * (1f / (2 * rt.WIDTH)), ran.nextGaussian() * (1f / (2 * rt.HEIGHT)))));
			double r = Math.random();
			double omega = Math.random() * 2 * Math.PI;
			double xSample = Math.sqrt(r) * Math.sin(omega);
			double ySample = Math.sqrt(r) * Math.cos(omega);

			Vec3 fVec = lookAt.subtract(eye).normalize();
			Vec3 rVec = up.cross(fVec);
			Vec3 uVec = rVec.cross(fVec);

			Vec3 a = rVec.scale((float) xSample).add(uVec.scale((float) ySample));
			Vec3 eyeNew = eye.add(a);

			Vec3 directionNew = eyeRayDirection.subtract(a.scale((float) ((1.0 / eye.subtract(lookAt).length()))));

			if (APERTURE) {
				color = color.add(calcLightFrom(scene, directionNew, eyeNew));
			} else {
				color = color.add(calcLightFrom(scene, eyeRayDirection, eye));
			}

		}
		color = new Vec3(color.x / iterations, color.y / iterations, color.z / iterations);

		return gammaCorrectionUndo(color);
	}

	private static Vec2 getNormalizedCenteredPixelVector(int x, int y, int box) {
		if (box == 1) {
			float normX = (float) ((1.0 / (rt.HEIGHT / 2)) * ((rt.HEIGHT / 2) - x) * -1);
			float normY = (float) (1.0 / (rt.HEIGHT / 2) * ((rt.HEIGHT / 2) - y));
			return new Vec2(normX, normY);
		} else if (box == 2) {
			float normX = (float) (1.0 / (rt.HEIGHT / 2) * (x - (rt.HEIGHT / 2)));
			float normY = (float) (1.0 / (rt.HEIGHT / 2) * ((rt.HEIGHT / 2) - y));
			return new Vec2(normX, normY);
		} else if (box == 3) {
			float normX = (float) (1.0 / (rt.HEIGHT / 2) * ((rt.HEIGHT / 2) - x) * -1);
			float normY = (float) (1.0 / (rt.HEIGHT / 2) * (y - (rt.HEIGHT / 2)) * -1);
			return new Vec2(normX, normY);
		} else if (box == 4) {
			float normX = (float) (1.0 / (rt.HEIGHT / 2) * (x - (rt.HEIGHT / 2)));
			float normY = (float) (1.0 / (rt.HEIGHT / 2) * ((y - rt.HEIGHT / 2)) * -1);
			return new Vec2(normX, normY);
		} else {
			return null;
		}
	}

	private static Vec3 createEyeRay(Vec3 eye, Vec3 lookAt, Vec2 pixel) {
		Vec3 f = lookAt.subtract(eye).normalize();
		Vec3 r = up.cross(f);
		Vec3 u = r.cross(f);

		r = r.scale(pixel.x).scale((float) Math.tan(fovRadient / 2.0));
		u = u.scale(pixel.y).scale((float) Math.tan(fovRadient / 2.0));

		return f.add(r).add(u).normalize();
	}

	private static Vec3 calcLightFrom(Scene s, Vec3 rayDirection, Vec3 startpoint) {
		double p = 0.2;
		Sphere closestSphere = null;
		double closestLambda = 0;

		if (BVH) {
			s.createSphereTree();

			SphereNode closestSphereNode = getClosestSphere(s, startpoint, rayDirection);

			if (closestSphereNode == null) {
				return new Vec3(0, 0, 0);
			}

			closestSphere = closestSphereNode.getSphere();
			closestLambda = closestSphereNode.getClosestLambda();

		} else {
			closestLambda = Double.POSITIVE_INFINITY;
			double lambda = Double.POSITIVE_INFINITY;

			for (Sphere sphere : s.getSpheres()) {
				double a = 1;
				double b = startpoint.subtract(sphere.getCenter()).scale(2.0f).dot(rayDirection);
				double c = Math.pow(startpoint.subtract(sphere.getCenter()).length(), 2.0)
						- Math.pow(sphere.getRadius(), 2.0);

				double det = Math.sqrt(Math.pow(b, 2.0) - 4.0 * a * c);

				if (det < 0) {
					break;
				} else if (det == 0) {
					lambda = -b / (2.0 * a);
				} else {
					double firstLambda = (-b + det) / 2.0 * a;
					double secondLambda = (-b - det) / 2.0 * a;

					if (firstLambda < 0 && secondLambda >= 0) {
						lambda = secondLambda;
					} else if (secondLambda < 0 && firstLambda >= 0) {
						lambda = firstLambda;
					} else {
						lambda = Math.min(firstLambda, secondLambda);
					}
				}

				if (lambda >= 0 && lambda < closestLambda) {
					closestLambda = lambda;
					closestSphere = sphere;
				}

			}
			if (closestSphere == null) {
				return new Vec3(0, 0, 0);
			}
		}

		if (TEST) {
			return closestSphere.getColor(null);
		}

		Vec3 hp = startpoint.add(rayDirection.scale((float) closestLambda));

		Vec3 n = hp.subtract(closestSphere.getCenter()).normalize();

		// if hitpoint is inside sphere
		while (closestSphere.getCenter().subtract(hp).length() <= closestSphere.getRadius()) {
			hp = hp.add(n.scale(0.001f));
		}

		if (Math.random() < p) {
			return closestSphere.getEmission();
		}

		Vec3 wr = null;

		while (true) {
			float randomX = (float) ((Math.random() < 0.5) ? Math.random() : Math.random() * -1);
			float randomY = (float) ((Math.random() < 0.5) ? Math.random() : Math.random() * -1);
			float randomZ = (float) ((Math.random() < 0.5) ? Math.random() : Math.random() * -1);

			wr = new Vec3(randomX, randomY, randomZ).normalize();
			if (wr.length() < 1) {
				break;
			}
		}

		wr = wr.dot(n) > 0 ? wr : wr.negate();
		wr = wr.normalize();

		return closestSphere.getEmission()
				.add(hadamardProduct(
						calcLightFrom(s, wr, hp)
								.scale((float) (wr.dot(n) * (1 / (1 - p)) * BRDF(rayDirection, wr, n, closestSphere))),
						closestSphere.getColor(hp)));

	}

	private static SphereNode getClosestSphere(Scene s, Vec3 hitpoint, Vec3 ray) {
		return getHitpointSphere(hitpoint, ray, s.getSphereNodes());
	}

	private static SphereNode getHitpointSphere(Vec3 hitpoint, Vec3 ray, List<SphereNode> sphereNodeList) {
		double closestLambda = Double.POSITIVE_INFINITY;
		double lambda = Double.POSITIVE_INFINITY;
		SphereNode closestSphere = null;

		if (sphereNodeList == null || sphereNodeList.isEmpty()) {
			return null;
		}

		for (SphereNode sphere : sphereNodeList) {

			double a = 1;
			double b = hitpoint.subtract(sphere.getCenter()).scale(2.0f).dot(ray);
			double c = Math.pow(hitpoint.subtract(sphere.getCenter()).length(), 2.0)
					- Math.pow(sphere.getRadius(), 2.0);

			double det = Math.sqrt(Math.pow(b, 2.0) - 4.0 * a * c);

			if (det < 0) {
				break;
			} else if (det == 0) {
				lambda = -b / (2.0 * a);
			} else {
				double firstLambda = (-b + det) / 2.0 * a;
				double secondLambda = (-b - det) / 2.0 * a;

				if (firstLambda < 0 && secondLambda >= 0) {
					lambda = secondLambda;
				} else if (secondLambda < 0 && firstLambda >= 0) {
					lambda = firstLambda;
				} else {
					lambda = Math.min(firstLambda, secondLambda);
				}

			}

			if (lambda >= 0 && lambda < closestLambda) {
				closestLambda = lambda;
				closestSphere = sphere;
			}

		}

		if (closestSphere != null) {
			closestSphere.setClosestLambda(closestLambda);
		} else {
			return closestSphere;
		}

		// Correct sphere found, but bounding sphere with no real hit is closer
		if (closestSphere.getChildren() == null || closestSphere.getChildren().isEmpty()) {
			return closestSphere;
		} else if (getHitpointSphere(hitpoint, ray, closestSphere.getChildren()) != null) {
			return getHitpointSphere(hitpoint, ray, closestSphere.getChildren());
		} else {
			sphereNodeList.remove(closestSphere);
			return getHitpointSphere(hitpoint, ray, sphereNodeList);
		}
	}

	private static Vec3 hadamardProduct(Vec3 a, Vec3 b) {
		return new Vec3(a.x * b.x, a.y * b.y, a.z * b.z);
	}

	private static float BRDF(Vec3 wIn, Vec3 randRay, Vec3 n, Sphere sphere) {
		float epsilon = 0.17f;
		Vec3 r = wIn.subtract(n.scale(wIn.dot(n) * 2.0f)).normalize();
		if (sphere.isSpecular() && r.dot(randRay) > 1.0f - epsilon) {
			return 50.0f;
		} else {
			return 1f;
		}
	}

	private static Vec3 gammaCorrection(Vec3 vec) {
		float gamma = (float) 2.2;
		float newX = (float) Math.pow(vec.x / 255, gamma);
		float newY = (float) Math.pow(vec.y / 255, gamma);
		float newZ = (float) Math.pow(vec.z / 255, gamma);
		return new Vec3(newX, newY, newZ);
	}

	private static Vec3 gammaCorrectionUndo(Vec3 vec) {
		float gamma = (float) 2.2;
		float newX = (float) (((Math.pow(vec.x, 1.0 / gamma) * 255) > 255) ? 255
				: (Math.pow(vec.x, 1.0 / gamma) * 255));
		float newY = (float) (((Math.pow(vec.y, 1.0 / gamma) * 255) > 255) ? 255
				: (Math.pow(vec.y, 1.0 / gamma) * 255));
		float newZ = (float) (((Math.pow(vec.z, 1.0 / gamma) * 255) > 255) ? 255
				: (Math.pow(vec.z, 1.0 / gamma) * 255));
		return new Vec3(newX, newY, newZ);
	}
}
