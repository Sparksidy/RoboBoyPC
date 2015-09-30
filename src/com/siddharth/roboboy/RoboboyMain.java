package com.siddharth.roboboy;

import com.siddharth.framework.Screen;
import com.siddharth.framework.implementation.AndroidGame;

public class RoboboyMain extends AndroidGame {

	@Override
	public Screen getInitScreen() {
		// TODO Auto-generated method stub
		return new LoadingScreen(this);
	}

	@Override
	public void setScreen(GameScreen gameScreen) {
		// TODO Auto-generated method stub
		
	}
	
	
}
