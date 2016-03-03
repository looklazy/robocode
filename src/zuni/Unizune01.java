package zuni;

import java.awt.Color;
import java.awt.geom.Point2D;
 
import robocode.*;
import robocode.util.Utils;

public class Unizune01 extends AdvancedRobot
{
	private Enemy target;
	private final double PI = Math.PI;
	private int direction = 1;
	private double firePower = 1.0;

	public void run() {
		setAdjustGunForRobotTurn(true);
		setAdjustRadarForGunTurn(true);
		
		target = new Enemy();
		target.distance = 100000;
		setColors(Color.red, Color.blue, Color.green);

		turnRadarRightRadians(2*PI);
		while(true) {
			doMovement();
			doGun();
			doScanner();
			fire(firePower);
			execute();
		}
	}

	void doMovement() {
		if (getTime() % 20 == 0)  {
			direction *= -1;
			setAhead(direction*300);
		}
		setTurnRightRadians(target.bearing + (PI/2));
	}
	
	void doScanner() {
		double radarOffset;
		if (getTime() - target.ctime > 4) {
			radarOffset = 360;
		} else {	
			radarOffset = getRadarHeadingRadians() - absbearing(getX(), getY(), target.x, target.y);
			
			if (radarOffset < 0)
				radarOffset -= PI/8;
			else
				radarOffset += PI/8; 
		}

		setTurnRadarLeftRadians(NormaliseBearing(radarOffset));
	}
	
	
	double NormaliseBearing(double ang) {
		if (ang > PI) {
			ang -= 2*PI;
		}
		if (ang < -PI) {
			ang += 2*PI;
		}
		return ang;
	}
	
	double NormaliseHeading(double ang) {
		if (ang > 2*PI) {
			ang -= 2*PI;
		}
		if (ang < 0) {
			ang += 2*PI;
		}
		return ang;
	}
	
	public double getrange( double x1 ,double y1, double x2,double y2 ) {
		double xo = x2-x1;
		double yo = y2-y1;
		return Math.sqrt( xo*xo + yo*yo );
	}

	public double absbearing( double x1,double y1, double x2,double y2 ) {
		double xo = x2-x1;
		double yo = y2-y1;
		double h = getrange( x1,y1, x2,y2 );
		if( xo > 0 && yo > 0 ) {
			return Math.asin( xo / h );
		}
		if( xo > 0 && yo < 0 ) {
			return Math.PI - Math.asin( xo / h );
		}
		if( xo < 0 && yo < 0 ) {
			return Math.PI + Math.asin( -xo / h );
		}
		if( xo < 0 && yo > 0 ) {
			return 2.0 * Math.PI - Math.asin( -xo / h );
		}
		return 0;
	}

	public void doGun() {
		long time = getTime() + (int)(target.distance/(20-(3*firePower)));
		
		double gunOffset = getGunHeadingRadians() - absbearing(getX(), getY(), target.x, target.y);
		setTurnGunLeftRadians(NormaliseBearing(gunOffset));
	}

	public void onScannedRobot(ScannedRobotEvent e) {

		if ((e.getDistance() < target.distance) || (target.name == e.getName())) {

			double absbearing_rad = (getHeadingRadians()+e.getBearingRadians())%(2*PI);

			target.name = e.getName();
			target.x = getX() + Math.sin(absbearing_rad) * e.getDistance();
			target.y = getY() + Math.cos(absbearing_rad) * e.getDistance();
			target.bearing = e.getBearingRadians();
			target.head = e.getHeadingRadians();
			target.ctime = getTime();
			target.speed = e.getVelocity();
			target.distance = e.getDistance();
		}
	}

	public void onRobotDeath(RobotDeathEvent e) {
		if (e.getName() == target.name) {
			target.distance = 10000;
		}	
	}

}

class Enemy {
	public String name;
	public double bearing;
	public double head;
	public long ctime;
	public double speed;
	public double x, y;
	public double distance;
}
