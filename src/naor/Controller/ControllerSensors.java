package naor.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;

public class ControllerSensors extends Activity implements SensorEventListener
{
	private static final String TAG = "ControlerSensors";
	MyController ourView;
	private SensorManager sm;
	Sensor gyro;
	NXTtalker talker ;
	NXTRemoteControl remote ;
	String MACaDdress="empty";
	boolean First = true ; 
	private NXTtalker mNXTtalker;
	private final Handler mHandler = new Handler();
	private BluetoothAdapter mBluetoothAdapter;
	private int speed = 200 ,Lmod =1,Rmod=1;
	
	
@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ourView = new MyController(this);
		setContentView(ourView);
		if(First)
		{
			Intent i = new Intent(this, DeviceListActivity.class);
			startActivityForResult(i, 7);
			First = false;
		}
		
		Log.e(TAG, "after deviceList");
		/*
		Bundle extras = getIntent().getExtras();
		Log.e(TAG, "after Bundle");
		String value = "empty";
		if(extras !=null) 
		{
			
			 value = extras.getString("EXTRA_DEVICE_ADDRESS");
		}
		*/
		Log.e(TAG, "MAC address: "+MACaDdress);
		sm = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		Log.e(TAG, "got sm");
		// System.out.println("out");
		 if(sm.getSensorList(Sensor.TYPE_GYROSCOPE)!=null)
			{
			 	//System.out.println("in");
				gyro = sm.getSensorList(Sensor.TYPE_GYROSCOPE).get(0);
				sm.registerListener(this, gyro, SensorManager.SENSOR_DELAY_GAME) ;
				System.out.println(gyro.getName());
			}
		 //expermant
		 ourView.setArrow_D(true);
		 ourView.setArrow_U(false);
		 ourView.setArrow_L(true);
		 ourView.setArrow_R(false);
		 
		 mNXTtalker = new NXTtalker(mHandler);
		
	}
	

	////if false arrow black else green
	

	@Override
	public void onSensorChanged(SensorEvent event) 
	{
		// TODO Auto-generated method stub
		//ourView.setArrow_L(true);
		if (event.accuracy == SensorManager.SENSOR_STATUS_UNRELIABLE)
		{
			return;
		}
		/*if(event.values[2]>10)
		{
			System.out.println(gyro.getName());
		}*/
		//System.out.println(event.values[2]+"VVVVVVVVVVVVVVVVVVVV");
		ourView.setXtxt(Float.toString(event.values[2]));
		ourView.setYtxt(Float.toString(event.values[1]));
		ourView.setZtxt(Float.toString(event.values[0]));
		// Responsible for left and right arrows
		
		///to stop 
		 
		if(	(	event.values[2]>(-20) && event.values[2]<20 	)	&&	
			(  event.values[1]>(-20) && event.values[1 ]<20 )	   )
		{
			//speed = 0 ;
			byte l = (byte) (0);
            byte r = (byte) (0);
            mNXTtalker.motors(l, r, false, false);
            Log.e(TAG, "robot stop");
		}
		
		////////
		if(event.values[1]<-20)
		{
			ourView.setArrow_R(true);
			ourView.setArrow_L(false);
			byte l = (byte) (speed*Lmod);
            byte r = (byte) ((speed*Rmod)/2);
            mNXTtalker.motors(l, r, true, true);
			
		}
		else if(event.values[1]>20)
		{
			ourView.setArrow_L(true);
			ourView.setArrow_R(false);
			byte l = (byte) ((speed*Lmod)/2);
            byte r = (byte) (speed*Rmod);
            mNXTtalker.motors(l, r, true, true);
		}
		else
		{
			ourView.setArrow_L(false);
			ourView.setArrow_R(false);
		}
		// Responsible for up and down arrows 
		if(event.values[2]<-20)
		{
			//speed = (int) (event.values[2]*(-100));
			ourView.setArrow_U(true);
			ourView.setArrow_D(false);
			byte l = (byte) (speed*Lmod);
            byte r = (byte) (speed*Rmod);
            mNXTtalker.motors(l, r, true, true);
			
		}
		else if(event.values[2]>20)
		{
			//speed = (int) (event.values[2]*(100));
			ourView.setArrow_D(true);
			ourView.setArrow_U(false);
			byte l = (byte) (speed*Lmod*(-1));
            byte r = (byte) (speed*Rmod*(-1));
            mNXTtalker.motors(l, r, true, true);
		}
		else if(event.values[2]>40)
		{
			//speed = (int) (event.values[2]*(100));
			ourView.setArrow_D(true);
			ourView.setArrow_U(false);
			byte l = (byte) (speed*Lmod*(-2));
            byte r = (byte) (speed*Rmod*(-2));
            mNXTtalker.motors(l, r, true, true);
		}
		else if(event.values[2]>60)
		{
			//speed = (int) (event.values[2]*(100));
			ourView.setArrow_D(true);
			ourView.setArrow_U(false);
			byte l = (byte) (speed*Lmod*(-3));
            byte r = (byte) (speed*Rmod*(-3));
            mNXTtalker.motors(l, r, true, true);
		}
		else
		{
			ourView.setArrow_D(false);
			ourView.setArrow_U(false);
		}
		
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
		
	}
	public void noSensoer()
	{
		System.out.println("no gyro is Available");
	}
	 @Override
	protected void onResume() 
	{
		super.onResume();
			/*register the sensor listener to listen to the gyroscope sensor, use the 
			 * callbacks defined in this class, and gather the sensor information as  
			 * quick as possible*/
		sm.registerListener(this, sm.getDefaultSensor(Sensor.TYPE_ORIENTATION),SensorManager.SENSOR_DELAY_FASTEST);
	}
	protected void onStop() 
	{
			//unregister the sensor listener
		sm.unregisterListener(this);
		super.onStop();
	}
	/*
	private String getMacAddress(Intent i)
	{
		String address = " address naor";
		//Intent i = new Intent(this, DeviceListActivity.class);
		System.out.println(address);
		//startActivity(i);
		//address = extras.getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
		//if(i.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS) != null)
			//address = i.getExtras().getString(DeviceListActivity.EXTRA_DEVICE_ADDRESS);
		Log.e(TAG, address);
		return address;
	
	}*/
	
	 protected void onActivityResult(int requestCode, int resultCode, Intent data)
	 {
		 Bundle extra=data.getExtras();
		 MACaDdress = extra.getString("device_address");
		 Log.e(TAG, "macAddress 2 : "+MACaDdress);
		 connectToNXT(MACaDdress);
		 
	 }
	 private void connectToNXT(String MacAddress)
	 {
		 mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		 Log.e(TAG, "macAddress 3 : "+MACaDdress + "in connect to ");
		 if (BluetoothAdapter.checkBluetoothAddress(MacAddress))
		 {
			 BluetoothDevice device = mBluetoothAdapter.getRemoteDevice(MacAddress);
			 mNXTtalker.connect(device);
		 }
		 Log.e(TAG, "macAddress 4 : "+MACaDdress + "is not valid ");
	 
	 }
	 
}