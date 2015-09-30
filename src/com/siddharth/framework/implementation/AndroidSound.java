package com.siddharth.framework.implementation;

import android.media.SoundPool;

import com.siddharth.framework.Sound;

public class AndroidSound implements Sound {
	SoundPool soundPool;
	int soundId;
	
	public AndroidSound(SoundPool soundPool,int soundId){
		this.soundPool = soundPool;
		this.soundId = soundId;
	}
	
	@Override
	public void play(float volume) {
		// TODO Auto-generated method stub
		soundPool.play(soundId, volume, volume, 0, 0, 1);
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		soundPool.unload(soundId);
	}
}
