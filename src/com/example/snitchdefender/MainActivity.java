package com.example.snitchdefender;

import de.example.helloandroid.R;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements SensorEventListener {
	private SensorManager sensorManager;
	
	private MediaPlayer mp; 
	
	ImageView imageLogo1,imageLine1;
	ImageButton imageButton1;
	TextView xValue,yValue,zValue; 
	TextView max_view_x,max_view_y,max_view_z; 
	float xmax,ymax,zmax;
	float xmax_abs,ymax_abs,zmax_abs;
	float x_compare,y_compare,z_compare;
	boolean sound_X_Check = false;
	float limitValue = 3;
	float limitValueZ = 9;

	
	@Override
	public void onCreate(Bundle savedInstanceState){
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_main);
		
		imageLine1 = (ImageView) findViewById(R.id.imageLine1);
		imageLogo1 = (ImageView) findViewById(R.id.imageLogo1);
		
		addButtonListener();
		
		xValue=(TextView)findViewById(R.id.xcoor);
		yValue=(TextView)findViewById(R.id.ycoor); 
		zValue=(TextView)findViewById(R.id.zcoor); 
		max_view_x = (TextView)findViewById(R.id.max_x_text);
		max_view_y = (TextView)findViewById(R.id.max_y_text);
		max_view_z = (TextView)findViewById(R.id.max_z_text);
		
		sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
		sensorManager.registerListener(this, 
				sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
				SensorManager.SENSOR_DELAY_NORMAL);
		}
	
	
	public void addButtonListener() {
		 
		imageButton1 = (ImageButton) findViewById(R.id.imageButton1);
		imageButton1.setOnClickListener(new OnClickListener() {
 
			public void onClick(View v) {
				stopSound();
			 //  Toast.makeText(HelloAndroid.this,
			 //	"klick klick bla bla", Toast.LENGTH_SHORT).show();
			}
		});
	}
	
	

	public void onAccuracyChanged(Sensor sensor,int accuracy){}
	
	public void onSensorChanged(SensorEvent event){
		
		if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
			
			float x = event.values[0];
			float y = event.values[1];
			float z = event.values[2];
			
			//Werte anzeigen in Activity (ersten 3 TextViews)
			xValue.setText("X: "+ x);
			yValue.setText("Y: "+ y);
			zValue.setText("Z: "+ z);
			
			//Betrag ermitteln
			xmax = Math.abs(x);
			ymax = Math.abs(y);
			zmax = Math.abs(z);

				if(xmax > x_compare){
					max_view_x.setText("max-x: " + xmax);
					x_compare = xmax;
				}
				
				if(ymax > y_compare){
					max_view_y.setText("max-y: " + ymax);
					y_compare = ymax;
				}
				
				if(zmax > z_compare){
					max_view_z.setText("max-z: " + zmax);
					z_compare = zmax;
				}	
			}
		
		if(zmax < limitValueZ){
			sound_X_Check = true;
		}
			if(sound_X_Check == true){
				startSound();
			}	
	}
	
	
	
	public void startSound(){
		//Audio audio = new Audio();
    	if (mp == null) {
        	mp = MediaPlayer.create(MainActivity.this, R.raw.sound);
        		mp.start();	
        }
    }
    

    public void stopSound() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
			sound_X_Check = false;
			
       }
    }
	
	
	
	
}