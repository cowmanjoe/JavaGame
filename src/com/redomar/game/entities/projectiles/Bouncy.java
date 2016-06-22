package com.redomar.game.entities.projectiles;

import com.redomar.game.gfx.Colours;
import com.redomar.game.gfx.Screen;
import com.redomar.game.level.LevelHandler;

public class Bouncy extends Projectile {

	public Bouncy(LevelHandler level, int x, int y, double dir) {
		super(level, x, y, dir);
		range = 1000;
		damage = 100; 
		speed = 1;
	}
	
	
	@Override
	protected void move() {
		x += nx; 
		y += ny; 
		
		this.distance += Math.sqrt(nx * nx + ny * ny);
		if (this.distance > range) remove(); 

	}

	@Override
	public void tick() {
		if (tileCollisionSide(x, y, (int) nx, (int) ny) == "left" ||
				tileCollisionSide(x, y, (int) nx, (int) ny) == "right") 
			nx = -nx;  
		else if (tileCollisionSide(x, y, (int) nx, (int) ny) == "top" ||
				tileCollisionSide(x, y, (int) nx, (int) ny) == "bottom")
			ny = -ny; 
	

	}

	@Override
	public void render(Screen screen) {
		screen.render((int)x, (int)y, 7 * 32, Colours.get(-1, 311, 510, 544), 0x00, 1);

	}

}
