package com.culebrilla.fastfingers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class Survival extends Activity implements OnClickListener{
	
	ArrayList<ImageButton> imagenes;
	int current_image;
	int vidas = 10;
	Random generator;
	Activity activity;
	long tiempo, tiempof;
	Handler handler;
	int clicks,periodo;
	long dtiempo;
	Timer timer;
	TimerTask timerTask;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);
        
        generator = new Random();
        tiempo = new Date().getTime();
        clicks = 0;
        activity = this;
        periodo = (1000 - 1000*log10(clicks+1)/5);
        // TODO: un bucle de velocidad. variable del bucle

        
        imagenes = new ArrayList<ImageButton>();
        imagenes.add((ImageButton) this.findViewById(R.id.imageButton1));
        imagenes.add((ImageButton) this.findViewById(R.id.imageButton10));
        imagenes.add((ImageButton) this.findViewById(R.id.imageButton11));
        imagenes.add((ImageButton) this.findViewById(R.id.imageButton12));
        imagenes.add((ImageButton) this.findViewById(R.id.imageButton2));
        imagenes.add((ImageButton) this.findViewById(R.id.imageButton3));
        imagenes.add((ImageButton) this.findViewById(R.id.imageButton4));
        imagenes.add((ImageButton) this.findViewById(R.id.imageButton5));
        imagenes.add((ImageButton) this.findViewById(R.id.imageButton6));
        imagenes.add((ImageButton) this.findViewById(R.id.imageButton7));
        imagenes.add((ImageButton) this.findViewById(R.id.imageButton8));
        imagenes.add((ImageButton) this.findViewById(R.id.imageButton9));
        for(ImageButton b : imagenes)
        	b.setOnClickListener(this);
        
        current_image = 1;
        imagenes.get(current_image).setImageDrawable(this.getResources().getDrawable(R.drawable.board_true));

        //hacer que la foto cambie cada cierto tiempo
        handler = new Handler() {
        	public void handleMessage (Message msg){
        		vidas--;
                if(vidas <= 0) {
        			tiempof = new Date().getTime();
        			dtiempo = (tiempof-tiempo)/1000;
        			Toast.makeText(activity, "has durado " + dtiempo, Toast.LENGTH_SHORT).show();
        	       	activity.finish(); 
        	       	// TODO: hacer que termine tambien el handler
        	     }
    			imagenes.get(current_image).setImageDrawable(activity.getResources().getDrawable(R.drawable.board_false));
    			current_image = generator.nextInt(12);
    			imagenes.get(current_image).setImageDrawable(activity.getResources().getDrawable(R.drawable.board_true));
    			timer.cancel();
    			timer.purge();
    			if (vidas > 0) {
        			timer = new Timer ();
    				timerTask = new TimerTask () {
    				@Override
    				public void run() {
    					handler.sendEmptyMessage(0);
    				}
    	        };
    			timer.schedule(timerTask, periodo);
    			}
        	}
        };
        
        timerTask = new TimerTask () {
			@Override
			public void run() {
				handler.sendEmptyMessage(0);
			}
        };
        
        timer = new Timer ();
        timer.schedule(timerTask, periodo);
    }


	private int log10(int i) {
		// TODO Auto-generated method stub
		return 0;
	}


	public void onClick(View pulsado) {
		timer.cancel();
		timer.purge();
		timer = new Timer ();
		timerTask = new TimerTask () {
			@Override
			public void run() {
				handler.sendEmptyMessage(0);
			}
        };
		timer.schedule(timerTask, periodo);
		if(((ImageButton)this.findViewById(pulsado.getId()))==imagenes.get(current_image)) {
			vidas++;
			clicks++;
			imagenes.get(current_image).setImageDrawable(this.getResources().getDrawable(R.drawable.board_false));
			current_image = generator.nextInt(12);
			imagenes.get(current_image).setImageDrawable(this.getResources().getDrawable(R.drawable.board_true));
		}
		else {
			imagenes.get(current_image).setImageDrawable(this.getResources().getDrawable(R.drawable.board_false));
			current_image = generator.nextInt(11)+1;
			imagenes.get(current_image).setImageDrawable(this.getResources().getDrawable(R.drawable.board_true));
			vidas--;
		}
		 if(vidas <= 0) {
			tiempof = new Date().getTime();
			dtiempo = (tiempof-tiempo)/1000;
			Toast.makeText(this, "has durado " + dtiempo, Toast.LENGTH_SHORT).show();
	       	this.finish();        	
	     }
	}
}