package mir;

import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;
import java.util.Random;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

public class Mir extends Robot {
	int direction = 1;
	Random rand = new Random();

	public void run() {

		setBodyColor(Color.green);
		setGunColor(Color.cyan);
		setRadarColor(Color.cyan);
		setScanColor(Color.cyan);
		setBulletColor(Color.cyan);

		while (true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(150 * direction);
			turnGunLeft(40);
			turnLeft(20 * seletctVector());
			turnGunLeft(40);
			ahead(150 * direction);
			turnGunLeft(40);
			turnLeft(20 * seletctVector());
			turnGunLeft(40);
		}
	}

	private int seletctVector() {
		if (rand.nextBoolean()) {
			return 1;
		}
		return -1;
	}

	public void onScannedRobot(ScannedRobotEvent e) {
		fire(3);
	}

	public void onHitWall(HitWallEvent e) {
		direction *= -1;
	}
}
