public class NBody{
	static public double readRadius(String inFile){
		In in = new In(inFile);
		int num = in.readInt();
		double radius = in.readDouble();
		return radius;
	}
	static public Planet[] readPlanets(String inFile){
		In in  = new In(inFile);
		int n = in.readInt();
		Planet[] planets = new Planet[n];
		in.readDouble();
		for(int i = 0; i < n; ++i){
			double xxPos = in.readDouble();
			double yyPos = in.readDouble();
			double xxVel = in.readDouble();
			double yyVel = in.readDouble();
			double mass = in.readDouble();
			String imgFileName = in.readString();
			planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
		}
		return planets;
	}
	static public void main(String[] args){
		double T = Double.parseDouble(args[0]);
		double dt = Double.parseDouble(args[1]);
		String filename = args[2];
		double radius = readRadius(filename);
		Planet[] planets = readPlanets(filename);
		int n = planets.length;
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (Planet p: planets) {
			p.draw();
		}
		StdDraw.enableDoubleBuffering();
		int t = 0;
		while (t <= T) {
			double[] xForces = new double[n];
			double[] yForces = new double[n];
			for (int i = 0; i < n; ++i) {
				xForces[i] = planets[i].calcNetForceExertedByX(planets);
				yForces[i] = planets[i].calcNetForceExertedByY(planets);
			}
			for (int i = 0; i < n; ++i) {
				planets[i].update(dt, xForces[i], yForces[i]);		
			}
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (Planet p: planets) {
				p.draw();
			}
			StdDraw.show();
			t += dt;
		}
		StdOut.printf("%d\n", planets.length);
		StdOut.printf("%.2e\n", radius);
		for (int i = 0; i < planets.length; i++) {
    		StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
            planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
            planets[i].yyVel, planets[i].mass, planets[i].imgFileName);   
		}
	}
}