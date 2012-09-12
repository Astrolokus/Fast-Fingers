package com.culebrilla.fastfingers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Play extends Activity implements OnClickListener {
	
	@Override
		public void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			setContentView(R.layout.play);
			
			((Button) this.findViewById(R.id.play_survival)).setOnClickListener(this);
			((Button) this.findViewById(R.id.play_timetrial)).setOnClickListener(this);
        
	}
        
			public void onClick(View button) {
				switch (button.getId()){
				case R.id.play_timetrial:
					this.startActivity(new Intent(this, TimeTrial.class));
					break;	
				case R.id.play_survival:
				this.startActivity(new Intent(this, Survival.class));
				break;
				}
			}
}
