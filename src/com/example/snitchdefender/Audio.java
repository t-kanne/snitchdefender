package com.example.snitchdefender;

import de.example.helloandroid.R;
import android.content.Context;
import android.media.MediaPlayer;

public class Audio{

	private MediaPlayer mp; 
    Context context;

   //nachfolgende 2 methoden werden in hellandroid.java erzeugt
    public void startSound(){
    	if (mp == null) {
        	mp = MediaPlayer.create(context, R.raw.sound);
        	mp.start();
        }
    }
    

    public void stopSound() {
    	if (mp != null) {
            mp.stop();
            mp.release();
            
       }
    }
    
}