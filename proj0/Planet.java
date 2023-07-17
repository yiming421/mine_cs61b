public class Planet {
	public double xxPos;
	public double yyPos;
	public double xxVel;
	public double yyVel;
	public double mass;
	public String imgFileName;
	private static final double G = 6.67e-11;
	public Planet(double xP, double yP, double xV,
				  double yV, double m, String img){
		xxPos = xP;
		yyPos = yP;
		xxVel = xV;
		yyVel = yV;
		mass = m;
		imgFileName = img;
	}
	public Planet(Planet p){
		xxPos = p.xxPos;
		yyPos = p.yyPos;
		xxVel = p.xxVel;
		yyVel = p.yyVel;
		mass = p.mass;
		imgFileName = p.imgFileName;
	}
	public double calcDistance(Planet p){
		double dx = xxPos - p.xxPos;
		double dy = yyPos - p.yyPos;
		return Math.sqrt(dx * dx + dy * dy);
	}
	public double calcForceExertedBy(Planet p){
		return (G * mass * p.mass) / (calcDistance(p) * calcDistance(p));
	}
	public double calcForceExertedByX(Planet p){
		double dx = p.xxPos - xxPos;
		return calcForceExertedBy(p) * dx / calcDistance(p);
	}
	public double calcForceExertedByY(Planet p){
		double dy = p.yyPos - yyPos;
		return calcForceExertedBy(p) * dy / calcDistance(p);
	}
	public double calcNetForceExertedByX(Planet[] allP){
		double ret = 0;
		for(Planet p: allP){
			if(!equals(p)){
				ret += calcForceExertedByX(p);
			}
		}
		return ret;
	}
	public double calcNetForceExertedByY(Planet[] allP){
		double ret = 0;
		for(Planet p: allP){
			if(!equals(p)){
				ret += calcForceExertedByY(p);
			}
		}
		return ret;
	}
	public void update(double dt, double fX, double fY){
		double aX = fX / mass;
		double aY = fY / mass;
		xxVel += dt * aX;
		yyVel += dt * aY;
		xxPos += dt * xxVel;
		yyPos += dt * yyVel;
	}
	public void draw(){
		String imgPath = "images/" + imgFileName;
		StdDraw.picture(xxPos, yyPos, imgPath);
	}
}