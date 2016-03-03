package jabi;

import robocode.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

public class JabiBot extends AdvancedRobot {

	boolean peek; // Don't turn if there's a robot there
	double moveAmount; // How much to move

	@Override
	public void run() {
		init();

		// Initialize moveDistance to the maximum possible for this battlefield.
		moveAmount = Math.max(getBattleFieldWidth(), getBattleFieldHeight());
		// Initialize peek to false
		peek = false;

		// turnLeft to face a wall.
		// getHeading() % 90 means the remainder of
		// getHeading() divided by 90.

		System.out.println("getX() = " + getX());
		System.out.println("getY() = " + getY());
		System.out.println("getBattleFieldWidth() = " + getBattleFieldWidth());
		System.out.println("getBattleFieldHeight() = " + getBattleFieldHeight());

		boolean left = getX() < getBattleFieldWidth() / 2;
		boolean top = getY() > getBattleFieldHeight() / 2;

		double fromRight = getBattleFieldWidth() - getX();
		double fromLeft = getBattleFieldWidth() - fromRight;
		System.out.println("fromLeft = " + fromLeft);
		System.out.println("fromRight = " + fromRight);
		double fromTop = getBattleFieldHeight() - getY();
		double fromBottom = getBattleFieldHeight() - fromTop;
		System.out.println("fromTop = " + fromTop);
		System.out.println("fromBottom = " + fromBottom);

		System.out.println("현재 위치 : ");
		System.out.print("왼쪽에서 " + fromLeft);
		System.out.println(" 오른쪽에서 " + fromRight);
		System.out.print("위에서 " + fromTop);
		System.out.println(" 아래에서 " + fromBottom);

		Map map = new HashMap();

		map.put(fromTop - 50, 0);
		map.put(fromRight - 50, 1);
		map.put(fromBottom - 50, 2);
		map.put(fromLeft - 50, 3);
		System.out.println("map = " + map);

		SortedSet set = new TreeSet<>(map.keySet());
		System.out.println("set = " + set);
		Object first = map.get(set.first());
		System.out.println("first = " + first);

		double moveDistance = (double) set.first();
		int moveDirection = (int) map.get(set.first());

		System.out.print("move : ");
		switch (moveDirection) {
			case 0:
				System.out.print("위로 ");
				break;
			case 1:
				System.out.print("오른쪽으로 ");
				break;
			case 2:
				System.out.print("아래로 ");
				break;
			case 3:
				System.out.print("왼쪽으로 ");
				break;
		}

		System.out.println(moveDistance + "만큼 이동");

		double targetHeading = moveDirection * 90;
		double turnDegree = getHeading() - targetHeading;
		if (turnDegree > 180) {
			turnDegree = (360 - turnDegree) * -1;
		}
		if (turnDegree < -180) {
			turnDegree = (360 + turnDegree);
		}
		turnLeft(turnDegree);
		ahead(moveDistance);

		// Turn the gun to turn right 90 degrees.
		peek = true;
		turnGunRight(90);
		turnRight(90);

		// Spin the gun around slowly... forever
		while (true) {

			setTurnRadarLeft(90);
			ahead(10);
			setTurnRadarRight(180);
			ahead(10);
			setTurnRadarLeft(90);
			ahead(10);

		}

	}

	public void init() {
		// Set colors
		setBodyColor(Color.red);
		setGunColor(Color.black);
		setRadarColor(Color.orange);
		setScanColor(Color.yellow);
		setBulletColor(Color.red);
	}

	@Override
	public void onScannedRobot(ScannedRobotEvent event) {

		if (Math.abs(getGunHeading() - event.getHeading()) < 1) {
			fire(1);
		}

		//        double bearing = (getHeading() + event.getBearing());
		//        double gunMove = (getGunHeading() - bearing) % 360;
		//        if (gunMove > 180) {
		//            gunMove = gunMove - 360;
		//        }
		//        if (Math.abs(gunMove) > 3) {
		//            turnGunLeft(gunMove);
		//        } else {
		//        }

	}

	@Override
	public void onHitByBullet(HitByBulletEvent event) {
		System.out.println("JabiBot.onHitByBullet");
		super.onHitByBullet(event);
	}

	@Override
	public void onHitRobot(HitRobotEvent event) {
		System.out.println("JabiBot.onHitRobot");
		super.onHitRobot(event);
	}

	@Override
	public void onBulletHit(BulletHitEvent event) {
		System.out.println("JabiBot.onBulletHit");
		super.onBulletHit(event);
	}

	@Override
	public void onBulletHitBullet(BulletHitBulletEvent event) {
		System.out.println("JabiBot.onBulletHitBullet");
		super.onBulletHitBullet(event);
	}

	@Override
	public void onBulletMissed(BulletMissedEvent event) {
		System.out.println("JabiBot.onBulletMissed");
		super.onBulletMissed(event);
	}

	@Override
	public void onHitWall(HitWallEvent event) {
		System.out.println("JabiBot.onHitWall");
		super.onHitWall(event);
	}
}
