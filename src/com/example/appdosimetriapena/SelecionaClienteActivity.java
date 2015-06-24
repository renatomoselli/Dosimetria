package com.example.appdosimetriapena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SelecionaClienteActivity extends Activity {
	Button btnBuscar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seleciona_cliente);
		
		btnBuscar = (Button) findViewById(R.id.btnBuscar);
		btnBuscar.setOnClickListener(btnBuscarListener);
	}
	
	private OnClickListener btnBuscarListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent(SelecionaClienteActivity.this, TipoCrimeActivity.class);
			startActivity(i);
		}
	};
}
