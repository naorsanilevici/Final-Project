package naor.Controller;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;

public class MyController extends View {
	
	Paint paint = new Paint();
	String Xtext,Ytext,Ztext ;
	
	private Bitmap arrow_R_B;
	private Bitmap arrow_L_B;
	private Bitmap arrow_U_B;
	private Bitmap arrow_D_B;
	
	private Bitmap arrow_R_G;
	private Bitmap arrow_L_G;
	private Bitmap arrow_U_G;
	private Bitmap arrow_D_G;
	
	ControllerSensors gyro ;
	
	protected Boolean arrow_L;
	protected Boolean arrow_R;
	protected Boolean arrow_D;
	protected Boolean arrow_U;
	
	//size of arrows
	int arrowWidth;
	int arrowHeight;
	
	public MyController(Context context )//,ControllerSensors gyro) 
	{
		super(context);
	
		arrow_L=false;
		arrow_R=false;
		arrow_U=false;
		arrow_D=false;
		//this.gyro = gyro;
		gyro = new ControllerSensors();
		/*
		try
		{
			gyro = new ControllerSensors();
			System.out.println("here");}
		catch (Exception e) {
			// TODO: handle exception
			System.out.println("not define gyro");
		}*/
		//System.out.println("open consractur");
		//black arrows
		arrow_R_B = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_right_black_128);
		arrow_L_B = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_left_black_128);
		arrow_U_B = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_up_black_128);
		arrow_D_B = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_down_black_128);
		//green arrows
		arrow_R_G = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_right_128);
		arrow_L_G = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_left_128);
		arrow_U_G = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_up_128);
		arrow_D_G = BitmapFactory.decodeResource(getResources(), R.drawable.arrow_down_128);
		//size of arrows
		arrowWidth = arrow_D_G.getWidth() ;
		arrowHeight = arrow_D_G.getHeight();
		
		Xtext= "defultX";
		Ytext= "defultY";
		Ztext= "defultZ";
	}

	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		
		canvas.drawColor(Color.WHITE);
		//whatColorOfArrows();
		paint.setColor(Color.WHITE);
		paint.setStyle(Style.FILL);
		canvas.drawPaint(paint);
		paint.setColor(Color.BLACK);
		paint.setTextSize(20);
		canvas.drawText(Xtext, 300, 175, paint);
		canvas.drawText(Ytext, 300, 200, paint);
		canvas.drawText(Ztext, 300, 225, paint);
		
		if(arrow_L)
		{
			canvas.drawBitmap(arrow_L_G,0
					,(canvas.getHeight()/2)-arrowWidth, null);
		}
		else
		{
			canvas.drawBitmap(arrow_L_B,0
		               ,(canvas.getHeight()/2)-arrowWidth, null);	
		}
               
		
		
		if(arrow_R)
		{
			canvas.drawBitmap(arrow_R_G,(canvas.getWidth()-arrowHeight)
              ,(canvas.getHeight()/2)-arrowWidth, null);
		}
		else
		{
			canvas.drawBitmap(arrow_R_B,canvas.getWidth()-arrowHeight
               ,(canvas.getHeight()/2)-arrowWidth, null);
		}
		if(arrow_U)
		{
			canvas.drawBitmap(arrow_U_G,(canvas.getWidth()/2)-(arrowHeight/2)
	                ,0, null);
		}
		else
		{
			canvas.drawBitmap(arrow_U_B,(canvas.getWidth()/2)-(arrowHeight/2)
	                ,0, null);
		}
		
		
		if(arrow_D)
		{
			canvas.drawBitmap(arrow_D_G,(canvas.getWidth()/2-arrowWidth/2)
                ,canvas.getHeight()-(arrowWidth+70), null);
		}
		else
		{
			canvas.drawBitmap(arrow_D_B,(canvas.getWidth()/2-arrowWidth/2)
                ,canvas.getHeight()-(arrowWidth+75), null);
		}
		invalidate();
		
	}
	protected void whatColorOfArrows()
	{
		//in the mid time until read from gyroscope
		
		/*
		arrow_L=true;
		arrow_R=true;
		arrow_U=true;
		arrow_D=true;
			*/
	}
	public void setArrow_L(Boolean bool) 
	{
		arrow_L=bool;
	}
	public void setArrow_U(Boolean bool) 
	{
		arrow_U=bool;
	}
	public void setArrow_D(Boolean bool) 
	{
		arrow_D=bool;
	}
	public void setArrow_R(Boolean bool) 
	{
		arrow_R=bool;
	}
	
	public void setXtxt(String text)
	{
		
		Xtext= "X axe: " +text ;
	}
	public void setYtxt(String text)
	{
		
		Ytext= "Y axe: " +text ;
	}
	public void setZtxt(String text)
	{
		
		Ztext= "Z axe: " +text ;
	}
}
