package com.culebrilla.fastfingers;

import android.app.Activity;
import android.os.Bundle;

public class Highscores extends Activity{
	
	int highscore[];
	int i,j;

	// TODO : mirar sql
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.main);
	        
	        Bundle bundle = getIntent().getExtras();
    		
	        for (int i=0 ; i < 5; i++ ) {
	        	if (highscore[i] < bundle.getInt("PTCION")) {
	        		for (int j=4; j > i; j--)
	        			highscore [j] = highscore[j-1];
	        		highscore[i] = bundle.getInt("PTUCION");
	        		break;	        		
	        	}
	        }
	        
	 }

}
