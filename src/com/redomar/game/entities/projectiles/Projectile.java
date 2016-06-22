package com.redomar.game.entities.projectiles;

import java.util.Random;

import com.redomar.game.entities.Entity;
import com.redomar.game.level.LevelHandler;
import com.redomar.game.level.tiles.Tile;

public abstract class Projectile extends Entity{

	protected final double xOrigin, yOrigin;
	protected double angle;
	protected double nx, ny;
	protected double speed, range, damage, distance;
	protected Random life = new Random();
	
	private boolean removed = false;
	
	public Projectile(LevelHandler level, int x, int y, double dir) {
		super(level);
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	protected abstract void move();
	
	public boolean tileCollision(double xa, double ya, int nx, int ny){
		int xMin = 0;
		int xMax = 7;
		int yMin = 0;
		int yMax = 7;
		
		//Top side
		for (int x = xMin; x < xMax; x++) {
			if (isSolid((int) xa, (int) ya, x, yMin, nx, ny)) {
				return true;
			}
		}
		
		//Bottom side
		for (int x = xMin; x < xMax; x++) {
			if (isSolid((int) xa, (int) ya, x, yMax, nx, ny)) {
				return true;
			}
		}
		
		//Left side
		for (int y = yMin; y < yMax; y++) {
			if (isSolid((int) xa, (int) ya, xMin, y, nx, ny)) {
				return true;
			}
		}
		
		//Right side
		for (int y = yMin; y < yMax; y++) {
			if (isSolid((int) xa, (int) ya, xMax, y, nx, ny)) {
				return true;
			}
		}

		return false;
	}
	
	
	//Returns one of "left", "right", "top", "bottom" or "false"
	public String tileCollisionSide(double xa, double ya, int nx, int ny){
		int xMin = 0;
		int xMax = 7;
		int yMin = 0;
		int yMax = 7;
		
		//Top side
		for (int x = xMin; x < xMax; x++) {
			if (isSolid((int) xa, (int) ya, x, yMin, nx, ny)) {
				return "top";
			}
		}
		
		//Bottom side
		for (int x = xMin; x < xMax; x++) {
			if (isSolid((int) xa, (int) ya, x, yMax, nx, ny)) {
				return "bottom";
			}
		}
		
		//Left side
		for (int y = yMin; y < yMax; y++) {
			if (isSolid((int) xa, (int) ya, xMin, y, nx, ny)) {
				return "left";
			}
		}
		
		//Right side
		for (int y = yMin; y < yMax; y++) {
			if (isSolid((int) xa, (int) ya, xMax, y, nx, ny)) {
				return "right";
			}
		}

		return "false";
	}
	
	private boolean isSolid(int xa, int ya, int x, int y, int nx, int ny) {
		if (level == null) {
			return false;
		}

		Tile lastTile = level.getTile((nx + x) >> 3,
				(ny + y) >> 3);
		Tile newtTile = level.getTile((nx + x + xa) >> 3, (ny
				+ y + ya) >> 3);

		if (!lastTile.equals(newtTile) && newtTile.isSolid()) {
			return true;
		}

		return false;
	}

	public void remove(){
		setRemoved(true);
	}

	public boolean isRemoved() {
		return removed;
	}

	public void setRemoved(boolean removed) {
		this.removed = removed;
	}
	
}
