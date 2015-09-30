package com.siddharth.framework.implementation;

import java.util.List;

import android.content.Context;
import android.os.Build.VERSION;
import android.view.View;

import com.siddharth.framework.Input;

public class AndroidInput implements Input {
	TouchHandler touchHandler;
	
	public AndroidInput(Context context,View view,float scaleX,float scaleY){
		if(Integer.parseInt(VERSION.SDK)<5){
			touchHandler = new SingleTouchHandler(view,scaleX,scaleY);
		}else{
			touchHandler = new MultiTouchHandler(view,scaleX,scaleY);
		}
		
	}
	@Override
	public boolean isTouchDown(int pointer) {
		// TODO Auto-generated method stub
		return touchHandler.isTouchDown(pointer);
	}
	
	@Override
	public int getTouchX(int pointer) {
		// TODO Auto-generated method stub
		return touchHandler.getTouchX(pointer);
	}
	
	@Override
	public int getTouchY(int pointer) {
		// TODO Auto-generated method stub
		return touchHandler.getTouchY(pointer);
	}
	
	@Override
	public List<TouchEvent> getTouchEvents() {
		// TODO Auto-generated method stub
		return touchHandler.getTouchEvents();
	}
}
