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
		distance = 0; 
		
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
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
		int collisionX = tileCollisionX(x, y, (int) nx, (int) ny); 
		int collisionY = tileCollisionY(x, y, (int) nx, (int) ny); 
		
		if (collisionX == -1) 
			nx = Math.abs(nx);  
		else if (collisionX == 1) 
			nx = -Math.abs(nx);  
		
		if (collisionY == -1)
			ny = Math.abs(ny);
		else if (collisionY == 1)
			ny = -Math.abs(ny); 
		
		move(); 
	}

	@Override
	public void render(Screen screen) {
		screen.render((int)x, (int)y, 7 * 32, Colours.get(-1, 311, 510, 544), 0x00, 1);

	}

}
