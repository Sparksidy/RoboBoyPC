package com.siddharth.framework.implementation;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.view.Window;
import android.view.WindowManager;

import com.siddharth.framework.Audio;
import com.siddharth.framework.FileIO;
import com.siddharth.framework.Game;
import com.siddharth.framework.Graphics;
import com.siddharth.framework.Input;
import com.siddharth.framework.Screen;

public abstract class AndroidGame extends Activity implements Game {
	AndroidFastRenderView renderView;
	FileIO fileIO;
	Graphics graphics;
	Input input;
	Screen screen;
	Audio audio;
	WakeLock wakeLock;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		//Removing the app title bar and setting to full screen mode
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		//Aligning the orientation according to devices And creation of bitmaps
		boolean isPortrait = getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT;
		int frameBufferWidth = isPortrait ? 800 : 1280;
		int frameBufferHeight = isPortrait ? 1280: 800;
		Bitmap frameBuffer = Bitmap.createBitmap(frameBufferWidth,frameBufferHeight,Config.RGB_565);
		
		
		float scaleX = (float)frameBufferWidth/getWindowManager().getDefaultDisplay().getWidth();
		float scaleY = (float)frameBufferHeight/getWindowManager().getDefaultDisplay().getHeight();
		
		renderView = new AndroidFastRenderView(this,frameBuffer);
		graphics = new AndroidGraphics(getAssets(),frameBuffer);
		fileIO =  new AndroidFileIO(this);
		audio = new AndroidAudio(this);
		input = new AndroidInput(this,renderView,scaleX,scaleY);
		screen = getInitScreen();
		setContentView(renderView);
		
		PowerManager powerManager = (PowerManager)getSystemService(Context.POWER_SERVICE);
		wakeLock = powerManager.newWakeLock(PowerManager.FULL_WAKE_LOCK, "My Game");
	
		
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		wakeLock.acquire();
		screen.resume();
		renderView.resume();
		
	}
	
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		wakeLock.release();
		renderView.pause();
		screen.pause();
		
		//If the activity is pausing or completely finishing
		if(isFinishing()){
			screen.dispose();
		}
	}
	
	public Input getInput(){
		return input;
		
	}
	
	public FileIO getFileIO(){
		return fileIO;
	}
	
	public Graphics getGraphics(){
		return graphics;
	}
	
	public Audio getAudio(){
		return audio;
	}
	
	@Override
	public void setScreen(Screen screen) {
		// TODO Auto-generated method stub
		if(screen == null){
			throw new IllegalArgumentException("Screen must not be null");
		}
		this.screen.pause();
		this.screen.dispose();
		screen.resume();
		screen.update(0);
		this.screen = screen;
	}
	
	public Screen getCurrentScreen(){
		return screen;
		
	}
	
	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		getCurrentScreen().backButton();
	}
	
}
