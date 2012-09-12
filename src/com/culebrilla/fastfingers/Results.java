package com.culebrilla.fastfingers;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class Results extends Activity {
	
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.result);
	        
	        
	       // int p = savedInstanceState.;
	        Bundle bundle = getIntent().getExtras();
	        		
	        TextView puntuacion = (TextView) this.findViewById(R.id.result_puntuacion);
	        puntuacion.setText(String.valueOf(bundle.getInt("PTCION")));
	        
	 }

}
