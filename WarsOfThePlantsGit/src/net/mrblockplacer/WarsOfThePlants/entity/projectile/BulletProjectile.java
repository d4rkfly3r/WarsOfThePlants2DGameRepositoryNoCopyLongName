package net.mrblockplacer.WarsOfThePlants.entity.projectile;

import net.mrblockplacer.WarsOfThePlants.graphics.Screen;
import net.mrblockplacer.WarsOfThePlants.graphics.Sprite;

public class BulletProjectile extends Projectile {

	public BulletProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damageDealt = 20;
		rateOfFire = 20;
		sprite = Sprite.bullet1;
		nx = Math.cos(angle) * speed;
		ny = Math.sin(angle) * speed;
	}

	public void update() {
		move();
	}

	protected void move() {
		x += nx;
		y += ny;
		if (calculateDist() >= range) {
			remove();
		}
	}

	private double calculateDist() {
		double dist = 0;
		double a = (xOrg - x) * (xOrg - x);
		double b = (yOrg - y) * (yOrg - y);
		dist = Math.sqrt(a + b);
		return dist;
	}

	public void render(Screen screen) {
		screen.renderProjectile((int) x - 8, (int) y - 3, this);
	}

}
