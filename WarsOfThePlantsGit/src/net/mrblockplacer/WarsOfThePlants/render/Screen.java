package net.mrblockplacer.WarsOfThePlants.render;

import java.util.Random;

import net.mrblockplacer.WarsOfThePlants.entity.projectiles.Projectile;
import net.mrblockplacer.WarsOfThePlants.level.tile.Tile;

public class Screen {

	public int width;
	public int height;
	public int[] pixels;

	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;

	public int xOffset, yOffset;

	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // 4096

	private Random random = new Random();

	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; // 50400

		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	public void renderSheet(int xp, int yp, SpriteSheets sheet, boolean stuck) {
		if (stuck) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sheet.HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.WIDTH; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];
			}
		}
	}

	public void renderSprite(int xp, int yp, Sprite sprite, boolean stuck) {
		if (stuck) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height)
					continue;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}

	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;

		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;

			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}

	}

	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
		Sprite sprite = p.getSprite();
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;

			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int color = sprite.pixels[x + y * sprite.SIZE];
				if (color != 0xffff00ff)
					pixels[xa + ya * width] = color;
			}
		}

	}

	public void renderPlayer(int xp, int yp, Sprite sprite, int flip) {
		xp -= xOffset;
		yp -= yOffset;

		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			int ys = y;
			if ((flip == 2) || (flip == 3))
				ys = 31 - y;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1 || flip == 3)
					xs = 31 - x;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height)
					break;
				if (xa < 0)
					xa = 0;
				int color = sprite.pixels[xs + ys * 32];
				if (color != 0xffff00ff)
					pixels[xa + ya * width] = color;
			}
		}
	}

	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
