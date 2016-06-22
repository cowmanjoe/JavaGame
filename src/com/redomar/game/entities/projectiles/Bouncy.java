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
		if (tileCollisionSide(x, y, (int) nx, (int) ny) == "left") 
			nx = Math.abs(nx);  
		else if (tileCollisionSide(x, y, (int) nx, (int) ny) == "right") 
			nx = -Math.abs(nx);  
		
		if (tileCollisionSide(x, y, (int) nx, (int) ny) == "top")
			ny = Math.abs(ny);
		else if (tileCollisionSide(x, y, (int) nx, (int) ny) == "bottom")
			ny = -Math.abs(ny); 
		
		move(); 
	}

	@Override
	public void render(Screen screen) {
		screen.render((int)x, (int)y, 7 * 32, Colours.get(-1, 311, 510, 544), 0x00, 1);

	}

}
