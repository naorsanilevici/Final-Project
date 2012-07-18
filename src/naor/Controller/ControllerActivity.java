package naor.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class ControllerActivity extends Activity implements OnClickListener {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
     // Set up click listeners for all the buttons
        View connectButton = this.findViewById(R.id.connect_button);
        connectButton.setOnClickListener(this);
        View aboutButton = this.findViewById(R.id.about_button);
        aboutButton.setOnClickListener(this);
        View exitButton = this.findViewById(R.id.exit_button);
        exitButton.setOnClickListener(this);
    }

	@Override
	public void onClick(View v) 
	{
		switch (v.getId()) 
		{
			case R.id.about_button:
				Intent i = new Intent(this, About.class);
				startActivity(i);
				break;
			case R.id.connect_button:
				Intent j = new Intent(this,ControllerSensors.class);
				startActivity(j);
				//System.out.println("push  the butten");
				break;
			case R.id.exit_button:
				finish();
				break;
			// More buttons go here (if any) ...
		}
	}
}