package naor.Controller;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public class Control extends Activity implements OnClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);
		
		 // Set up click listeners for all the buttons
		View disconnectButton = this.findViewById(R.id.disconnect_button);
        disconnectButton.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
			case R.id.disconnect_button:
				Intent i = new Intent(this, ControllerActivity.class);
				startActivity(i);
				break;
		}
	}
	
	//// when called to re draw 
		//to do : two colors for  inside Triangle
	
}
