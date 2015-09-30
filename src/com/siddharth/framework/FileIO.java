package com.siddharth.framework;

import java.io.InputStream;
import java.io.OutputStream;

import android.content.SharedPreferences;

public interface FileIO {
	public InputStream readFile(String file)throws Exception;
	
	public OutputStream writeFile(String file)throws Exception;
	
	public InputStream readAsset(String file)throws Exception;
	
	public SharedPreferences getSharedPref();
	
}
