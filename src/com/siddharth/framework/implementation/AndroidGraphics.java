package com.siddharth.framework.implementation;

import java.io.InputStream;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Rect;
import android.graphics.RectF;

import com.siddharth.framework.Graphics;
import com.siddharth.framework.Image;

public class AndroidGraphics implements Graphics {
	
	AssetManager assets;
	Paint paint;
	Canvas canvas;
	Bitmap frameBuffer;
	
	Rect srcRect = new Rect();
	RectF dstRect = new RectF();
	
	public AndroidGraphics(AssetManager assets,Bitmap frameBuffer){
		this.assets= assets;
		this.frameBuffer = frameBuffer;
		this.paint = new Paint();
		this.canvas = new Canvas(frameBuffer);
	}
	
	@Override
	public Image newImage(String filename, ImageFormat format) {
		// TODO Auto-generated method stub
		
		//Set the configuration based on Image format
		Config config = null;
		if(format == ImageFormat.RGB565){
			config = Config.RGB_565;
		}else if(format == ImageFormat.ARGB4444){
			config = Config.ARGB_4444;
		}else {
			config = Config.ARGB_8888;
		}
		
		
		Options options  = new Options();
		options.inPreferredConfig = config;
		
		//Read the file and create a bitmap by decoding the inputstream
		InputStream in=null;
		Bitmap bitmap = null;
		try{
			in = assets.open(filename);
			bitmap = BitmapFactory.decodeStream(in);
			if(bitmap==null){
				throw new RuntimeException("Couldn't load bitmap from asset");
				
			}
			
		}catch(Exception e){
			throw new RuntimeException("Couldn't load bitmap from asset");
	
		}finally{
			if(in != null){
				try{
					in.close();
				}catch(Exception e){
					
				}
			}
		}
		
		//Get the config from the bitmap and assign it to format
		if(bitmap.getConfig() == Config.RGB_565){
			format = ImageFormat.RGB565;
		}else if(bitmap.getConfig()==Config.ARGB_4444){
			format = ImageFormat.ARGB4444;
		}else{
			format = ImageFormat.ARGB8888;
		}
		
		return new AndroidImage(bitmap,format);
		
	}
	
	
	@Override
	public void clearScreen(int color) {
		// TODO Auto-generated method stub
		canvas.drawRGB((color & 0xff0000) >> 16, (color & 0xff00) >> 8,
                (color & 0xff));

	}
	
	@Override
	public void drawLine(int x1, int y1, int x2, int y2, int color) {
		// TODO Auto-generated method stub
		paint.setColor(color);
		canvas.drawLine(x1, y1, x2, y2, paint);
	
	}
	
	@Override
	public void drawRect(int x, int y, int width, int height, int color) {
		// TODO Auto-generated method stub
		paint.setColor(color);
		paint.setStyle(Style.FILL);
		canvas.drawRect(x,y,x+width-1,y+height-1,paint);
	}
	
	@Override
	public void drawARGB(int i, int j, int k, int l) {
		
		// TODO Auto-generated method stub
		paint.setStyle(Style.FILL);
		canvas.drawARGB(i, j, k, l);
		
	}
	
	@Override
	public void drawString(String text, int x, int y, Paint paint) {
		// TODO Auto-generated method stub
		canvas.drawText(text, x, y, paint);
	}
	
	@Override
	public void drawImage(Image image, int x, int y, int srcX, int srcY,
			int srcWidth, int srcHeight) {
		// TODO Auto-generated method stub
		srcRect.left = srcX;
		srcRect.top = srcY;
		srcRect.right = srcX + srcWidth;
		srcRect.bottom = srcY + srcHeight;
		
		dstRect.left = x;
		dstRect.top= y;
		dstRect.right = x+srcWidth;
		dstRect.bottom = y+srcHeight;
		
		canvas.drawBitmap(((AndroidImage)Image).bitmap, srcRect, dstRect,null);
	}
	
	@Override
	public void drawImage(Image Image, int x, int y) {
		// TODO Auto-generated method stub
		canvas.drawBitmap(((AndroidImage)Image).bitmap,x,y,null);
	}
	
	public void drawScaledImage(Image Image, int x, int y, int width, int height, int srcX, int srcY, int srcWidth, int srcHeight){
	       
	       
	     	srcRect.left = srcX;
	        srcRect.top = srcY;
	        srcRect.right = srcX + srcWidth;
	        srcRect.bottom = srcY + srcHeight;
	       
	       
	        dstRect.left = x;
	        dstRect.top = y;
	        dstRect.right = x + width;
	        dstRect.bottom = y + height;
	       
	   
	       
	        canvas.drawBitmap(((AndroidImage) Image).bitmap, srcRect, dstRect, null);
	       
	    }
	
	@Override
	public int getWidth() {
		// TODO Auto-generated method stub
		return frameBuffer.getWidth();
	}
	@Override
	public int getHeight() {
		// TODO Auto-generated method stub
		return frameBuffer.getHeight();
	}
	
}
