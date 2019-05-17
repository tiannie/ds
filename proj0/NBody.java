public class NBody {
	public static double readRadius(String fileName) {
		In in = new In(fileName);
		int item_number = in.readInt();
		double radius = in.readDouble();
		return radius;
	}

	public static Body[] readBodies(String fileName) {
		In in = new In(fileName);
		int item_number = in.readInt();
		double radius = in.readDouble();
		Body[] bodies = new Body[item_number];
		for (int idx = 0; idx < item_number; ++idx) {
			bodies[idx] = new Body(in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readDouble(), in.readString());
		}
		return bodies;
	}

	public static void main(String[] args) {
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];

		Body[] bodies = NBody.readBodies(filename);
		double radius = NBody.readRadius(filename);

		StdDraw.enableDoubleBuffering();

		StdDraw.setScale(-radius, radius);

		double[] xForces = new double[bodies.length];
		double[] yForces = new double[bodies.length];

		for (double time = 0; time < T; time += dt) {
			for (int idx = 0; idx < bodies.length; idx++) {
				xForces[idx] = bodies[idx].calcNetForceExertedByX(bodies);
				yForces[idx] = bodies[idx].calcNetForceExertedByY(bodies);
			}
			for (int idx = 0; idx < bodies.length; idx++) {
				bodies[idx].update(dt, xForces[idx], yForces[idx]);
			}

			StdDraw.picture(0, 0, "images/starfield.jpg", radius * 2, radius * 2);
			for (Body b : bodies) {
				b.draw();
			}
			StdDraw.show();
			StdDraw.pause(1);
		}
		StdOut.printf("%d\n", bodies.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < bodies.length; i++) {
			StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
				bodies[i].xxPos, bodies[i].yyPos, bodies[i].xxVel, bodies[i].yyVel,
				bodies[i].mass, bodies[i].imgFileName);
		}
	}
}