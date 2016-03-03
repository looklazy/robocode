package robosample;

import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;

import java.awt.*;

public class ChloeBot extends Robot {

	@Override
	public void run() {
		setColors(Color.pink, Color.yellow, Color.cyan, Color.white, Color.orange);

		while (true) {
			ahead(50);
			turnLeft(10);
			turnGunRight(360);
		}
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent e) {
		fire(2);
	}

	@Override
	public void onHitRobot(HitRobotEvent e) {
		turnLeft(10 - e.getBearing());
	}

	@Override
	public void onHitWall(HitWallEvent event) {
		back(50);
	}
}
