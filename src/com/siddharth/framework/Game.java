package com.siddharth.framework;

import com.siddharth.roboboy.GameScreen;

public interface Game {
	public Audio getAudio();
	
	public FileIO getFileIO();
	
	public Input getInput();
	
	public Graphics getGraphics();
	
	public void setScreen(GameScreen gameScreen);
	
	public Screen getCurrentScreen();
	
	public Screen getInitScreen();
	
}
