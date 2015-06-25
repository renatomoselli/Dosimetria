package com.example.appdosimetriapena;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TipoClienteActivity extends Activity {
	Button btnClienteExiste, btnNovoCliente;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tipo_cliente);
		
		btnClienteExiste = (Button) findViewById(R.id.btnClienteExiste);
		btnClienteExiste.setOnClickListener(btnClienteExisteListener);
		
		btnNovoCliente = (Button) findViewById(R.id.btnNovoCliente);
		btnNovoCliente.setOnClickListener(btnNovoClienteListener);
	}
	
	private OnClickListener btnClienteExisteListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent(TipoClienteActivity.this, SelecionaClienteActivity2.class);
			startActivity(i);
		}
	};
	
private OnClickListener btnNovoClienteListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent(TipoClienteActivity.this, CadastroClienteActivity.class);
			startActivity(i);
		}
	};
}
