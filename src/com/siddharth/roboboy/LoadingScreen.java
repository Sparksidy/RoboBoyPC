package com.siddharth.roboboy;

import com.siddharth.framework.Game;
import com.siddharth.framework.Graphics;
import com.siddharth.framework.Screen;
import com.siddharth.framework.Graphics.ImageFormat;

public class LoadingScreen extends Screen {
	
	public LoadingScreen(Game game){
		super(game);
	}
	
	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		Graphics g = game.getGraphics();
		Assets.menu = g.newImage("menu.jpg", ImageFormat.RGB565);
		Assets.click = game.getAudio().createSound("explode.ogg");
		
		//game.setScreen(new MainMenuScreen(game));
	}
	
	@Override
	public void paint(float deltaTime) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void backButton() {
		// TODO Auto-generated method stub
		
	}
	
	
}
