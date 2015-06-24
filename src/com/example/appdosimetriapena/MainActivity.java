package com.example.appdosimetriapena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {
	Button btnConsultaPena, btnConsultaCliente;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnConsultaPena = (Button) findViewById(R.id.btnConsultaPena);
		btnConsultaPena.setOnClickListener(btnConsultaPenaListener);
		
		btnConsultaCliente = (Button) findViewById(R.id.btnConsultaCliente);
		btnConsultaCliente.setOnClickListener(btnConsultaClienteListener);
	}
	
	private OnClickListener btnConsultaPenaListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, SelecionaClienteActivity.class);
			startActivity(i);
		}
	};
	
	private OnClickListener btnConsultaClienteListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent(MainActivity.this, SelecionaClienteActivity.class);
			startActivity(i);
		}
	};
}
