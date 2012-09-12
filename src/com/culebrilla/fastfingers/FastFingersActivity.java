package com.culebrilla.fastfingers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class FastFingersActivity extends Activity implements OnClickListener {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ((Button) this.findViewById(R.id.main_start)).setOnClickListener(this);
        ((Button) this.findViewById(R.id.main_highscores)).setOnClickListener(this);
        //((Button) this.findViewById(R.id.main_options)).setOnClickListener(this);
        ((Button) this.findViewById(R.id.main_exit)).setOnClickListener(this);

	}

	public void onClick(View button) {
		switch (button.getId()){
		case R.id.main_start:
			this.startActivity(new Intent(this, Play.class));
			break;
		case R.id.main_highscores:
			this.startActivity(new Intent(this, Highscores.class));
		//case R.id.main_options:
			// TODO: Menu opciones.
			//break;
		case R.id.main_exit:
			this.finish();
			break;
		}
	}
}