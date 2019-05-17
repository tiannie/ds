public class Body {
	private static final double grav_cons = 6.67e-11;
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;

	public Body(double xP, double yP, double xV, double yV, double m, String img) {
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Body(Body b) {
		xxPos = b.xxPos;
		yyPos = b.yyPos;
		xxVel = b.xxVel;
		yyVel = b.yyVel;
		mass = b.mass;
		imgFileName = b.imgFileName;
	}

	public double calcDistance(Body b) {
		double dx = xxPos - b.xxPos;
		double dy = yyPos - b.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}

	public double calcForceExertedBy(Body b) {
		double dist = calcDistance(b);
		return grav_cons * (mass * b.mass) / (dist * dist);
	}

	public double calcForceExertedByX(Body b) {
		double force = calcForceExertedBy(b);
		return force * (b.xxPos - xxPos) / calcDistance(b);
	}
	public double calcForceExertedByY(Body b) {
		double force = calcForceExertedBy(b);
		return force * (b.yyPos - yyPos) / calcDistance(b);
	}

	public double calcNetForceExertedByX(Body[] bodies) {
		double force = 0;
		for (Body b : bodies) {
			if (this.equals(b)) {
				continue;
			}
			force += calcForceExertedByX(b);
		}
		return force;
	}
	public double calcNetForceExertedByY(Body[] bodies) {
		double force = 0;
		for (Body b : bodies) {
			if (this.equals(b)) {
				continue;
			}
			force += calcForceExertedByY(b);
		}
		return force;
	}

	public void update(double dt, double fX, double fY) {
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel += aX * dt;
		yyVel += aY * dt;
		double moveX = xxVel * dt;
		double moveY = yyVel * dt;
		xxPos += moveX;
		yyPos += moveY;
	}

	public void draw() {
		StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
	}
}