package mju;
import robocode.*;
import robocode.Robot;

import java.awt.*;
//import java.awt.Color;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * Teseus_proto_2 - a robot by (your name here)
 */
public class MjuBot extends Robot
{
	/**
	 * run: Teseus_proto_2's default behavior
	 */
	double moveAmount; // How much to move
	public void run() {
		// Initialization of the robot should be put here
		// Set colors
		setBodyColor(Color.red);
		setGunColor(Color.black);
		setRadarColor(Color.orange);
		setBulletColor(Color.cyan);
		setScanColor(Color.cyan);
		// After trying out your robot, try uncommenting the import at the top,
		// and the next line:

		// setColors(Color.red,Color.blue,Color.green); // body,gun,radar
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		// Robot main loop
		while(true) {
			// Replace the next 4 lines with any behavior you would like
			ahead(moveAmount);
			turnGunRight(360);
			back(moveAmount);
			turnGunLeft(360);
		}
	}

	/**
	 * onScannedRobot: What to do when you see another robot
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		// Replace the next line with any behavior you would like
		fire(2);
	}

	/**
	 * onHitByBullet: What to do when you're hit by a bullet
	 */
	public void onHitByBullet(HitByBulletEvent e) {
		// Replace the next line with any behavior you would like
		back(100);
	}

	/**
	 * onHitWall: What to do when you hit a wall
	 */
	public void onHitWall(HitWallEvent e) {
		// Replace the next line with any behavior you would like
		back(20);
	}
}
