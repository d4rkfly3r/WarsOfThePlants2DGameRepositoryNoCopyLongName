package net.mrblockplacer.WarsOfThePlants.entity.movingobjects;

import net.mrblockplacer.WarsOfThePlants.effects.Particle;
import net.mrblockplacer.WarsOfThePlants.entity.Entity;
import net.mrblockplacer.WarsOfThePlants.entity.projectiles.BulletProjectile;
import net.mrblockplacer.WarsOfThePlants.entity.projectiles.Projectile;
import net.mrblockplacer.WarsOfThePlants.level.tile.Tile;
import net.mrblockplacer.WarsOfThePlants.render.Sprite;
import net.mrblockplacer.WarsOfThePlants.sound.Audio;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;
	protected int waterSound = 0;
	public boolean hasPhaseSuit = false;
	protected int fireCounter = 0;
	public int health = 6;
	protected int speed = 1;
	int counter = 10;

	public void move(int xa, int ya, Mob mob) {

		if (xa > 0)
			dir = 1;
		if (xa < 0)
			dir = 3;
		if (ya > 0)
			dir = 2;
		if (ya < 0)
			dir = 0;

		if ((!collision(xa * mob.getSpeed(), ya * mob.getSpeed()) || hasPhaseSuit) && waterSound > 20) {
			if (water(xa * mob.getSpeed(), ya * mob.getSpeed())) {
				Audio.playSound(Audio.SOUND_WATER_1);
				waterSound = 0;
			}
		} else {
			waterSound++;
		}

		int a = 0;
		if (!collision(0, ya * mob.getSpeed()) || hasPhaseSuit) {
			y += ya * mob.getSpeed();
			a = 1;
		}

		if (!collision(xa * mob.getSpeed(), 0) || hasPhaseSuit) {
			x += xa * mob.getSpeed();
			a = 1;
		}
		if (a == 1) {
			Particle pa = new Particle(x, y, 100);
			level.add(pa);
		}
		if (mob instanceof Player) {
			// JOptionPane.showMessageDialog(null, "HI");
			// if (((Player) mob).id == 1) {
			// MainClass.network.sendText("HI");
			// if (MainClass.network.client != null) {
			// MainClass.network.sendText("HI");
			// counter = 0;
			// } else {
			// counter++;
			// }

			// }
		}
	}

	protected int getSpeed() {
		return 1;
	}

	protected void shoot(int x, int y, double dir) {
		Projectile p = new BulletProjectile(x, y, dir);
		p.init(level);
		level.add(p);
		Audio.playSound(Audio.SOUND_BOUNCE);
	}

	public void update() {
	}

	public void render() {
	}

	protected boolean collision(int xa, int ya) {
		boolean test = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt).solid())
				test = true;
		}
		return test;
	}

	protected boolean water(int xa, int ya) {
		boolean test = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 14 - 8) / 16;
			int yt = ((y + ya) + c / 2 * 12 + 3) / 16;
			if (level.getTile(xt, yt) == Tile.spawn_water1)
				test = true;
			if (level.getTile(xt, yt) == Tile.spawn_water2)
				test = true;
			if (level.getTile(xt, yt) == Tile.spawn_water3)
				test = true;
			if (level.getTile(xt, yt) == Tile.spawn_water4)
				test = true;
		}
		return test;
	}
}
