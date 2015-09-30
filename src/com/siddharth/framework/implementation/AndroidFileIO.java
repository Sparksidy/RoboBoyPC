package com.siddharth.framework.implementation;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.os.Environment;
import android.preference.PreferenceManager;

public class AndroidFileIO {
	Context context;
	AssetManager assets;
	String externalStoragePath;
	
	
	
	public AndroidFileIO(Context context){
		this.context = context;
		this.assets = context.getAssets();
		this.externalStoragePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
		
		
	}
	
	public InputStream readAsset(String filename)throws IOException{
		return assets.open(filename);
	}
	
	public InputStream readFile(String filename)throws IOException{
		return new FileInputStream(externalStoragePath+filename);
		
	}
	
	public OutputStream writeFile(String filename)throws IOException{
		return new FileOutputStream(externalStoragePath+filename);
	}
	
	public SharedPreferences getSharedPref(){
		return PreferenceManager.getDefaultSharedPreferences(context);
	}
	
}
