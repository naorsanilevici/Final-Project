package naor.Controller;

import android.app.Activity;
import android.os.Bundle;

public class GFX extends Activity {

	MyController ourView;
	ControllerSensors gyro;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		//gyro = new ControllerSensors()
		ourView = new MyController(this);
	
		setContentView(ourView);
	}

}
