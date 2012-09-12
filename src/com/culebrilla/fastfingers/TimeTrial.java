package com.culebrilla.fastfingers;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;

public class TimeTrial extends Activity implements OnClickListener {

	ArrayList<ImageButton> imagenes;
	int current_image,highscores[];
	Random generator;
	int puntuacion,i;
	Activity activity;
	Handler mhandler;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.board);
               // TODO: mandar los resultados a highscores 
        generator = new Random();
        activity=this;
        
        puntuacion = 0;
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
        
        // TODO: bases de datos, dialog
        
        current_image = 1;
        imagenes.get(current_image).setImageDrawable(this.getResources().getDrawable(R.drawable.board_true));
        
        mhandler = new Handler (){
        	public void handleMessage(Message msg) {
                AlertDialog.Builder alertbox = new AlertDialog.Builder(activity);
                alertbox.setMessage("Congratulations,you get"+ Integer.valueOf(puntuacion) + "points.");
                alertbox.setNeutralButton("Save", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                		Intent i = new Intent(activity, Highscores.class);
                		i.putExtra("PTCION", puntuacion);
                		activity.startActivity(i);
                		activity.finish(); 
                    }
                });
    
                alertbox.show();
            }
        };
        
        TimerTask timerTask = new TimerTask() {
        	public void run(){
        		mhandler.sendEmptyMessage(0);
        		cancel();
        	}
        };
        
        Timer timer = new Timer();
        timer.schedule(timerTask, 30000, 200000);
    }

	public void onClick(View pulsado) {
		
		if(((ImageButton)this.findViewById(pulsado.getId()))==imagenes.get(current_image)) {
			imagenes.get(current_image).setImageDrawable(this.getResources().getDrawable(R.drawable.board_false));
			current_image = generator.nextInt(12);
			imagenes.get(current_image).setImageDrawable(this.getResources().getDrawable(R.drawable.board_true));
			puntuacion ++;
		}
	}
}
