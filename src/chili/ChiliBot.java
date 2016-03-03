package chili;

import robocode.*;

import static robocode.util.Utils.normalRelativeAngleDegrees;

public class ChiliBot extends Robot {
	private double bearingFromGun;
	/**
	 * TrackFire's run method
	 */
	public void run() {
		// Loop forever
		while (true) {
			turnGunRight(10); // Scans automatically
		}
	}

	/**
	 * onScannedRobot:  We have a target.  Go get it.
	 */
	public void onScannedRobot(ScannedRobotEvent e) {
		bearingFromGun = getBearingFromGun(e);

		// If it's close enough, fire!
		if (Math.abs(bearingFromGun) <= 3) {
			turnGunRight(bearingFromGun);
			// We check gun heat here, because calling fire()
			// uses a turn, which could cause us to lose track
			// of the other robot.
			if (getGunHeat() == 0) {
				fire(Math.min(3 - Math.abs(bearingFromGun), getEnergy() - .1));
			}
		} // otherwise just set the gun to turn.
		// Note:  This will have no effect until we call scan()
		else {
			turnGunRight(bearingFromGun);
		}
		// Generates another scan event if we see a robot.
		// We only need to call this if the gun (and therefore radar)
		// are not turning.  Otherwise, scan is called automatically.
		if (bearingFromGun == 0) {
			scan();
		}
	}

	private double getBearingFromGun(ScannedRobotEvent e) {
		// Calculate exact location of the robot
		double absoluteBearing = getHeading() + e.getBearing();
		return normalRelativeAngleDegrees(absoluteBearing - getGunHeading());
	}

	public void onBulletMissed(BulletMissedEvent event) {
		ahead(50);
		if (bearingFromGun == 0) {
			scan();
		}
	}

	public void onHitByBullet(HitByBulletEvent event) {
		back(50);
		if (bearingFromGun == 0) {
			scan();
		}
	}

	public void onWin(WinEvent e) {
		// Victory dance
		turnRight(36000);
	}
}
