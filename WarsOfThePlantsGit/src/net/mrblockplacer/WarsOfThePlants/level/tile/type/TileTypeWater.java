package net.mrblockplacer.WarsOfThePlants.level.tile.type;

import net.mrblockplacer.WarsOfThePlants.level.tile.Tile;
import net.mrblockplacer.WarsOfThePlants.render.Screen;
import net.mrblockplacer.WarsOfThePlants.render.Sprite;

public class TileTypeWater extends Tile {

	public TileTypeWater(Sprite sprite) {
		super(sprite);
	}

	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
