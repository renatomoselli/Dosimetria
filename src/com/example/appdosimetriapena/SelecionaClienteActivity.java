package com.example.appdosimetriapena;

import java.util.List;







import android.support.v7.app.ActionBarActivity;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

public class SelecionaClienteActivity extends Activity {
	Button btnBuscar;
	Long pessoaId;
	List<Pessoa> pessoas;
	ListView pessoasListView;
	PessoaDAO pessoaDAO;
	Pessoa pessoa;
	private ActionMode currentActionMode;
	int currentPosition;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_seleciona_cliente);
		pessoasListView = (ListView) findViewById(R.id.listView1);
		pessoaDAO = new PessoaDAO(this);
		pessoaId = getIntent().getLongExtra("pessoaId", 0);
		pessoa = pessoaDAO.getById(pessoaId);
		pessoasListView.setOnItemClickListener(onItemClickListener);
		//pessoasListView.setOnItemLongClickListener(onItemLongClickListener);
		updateLV();
		
		btnBuscar = (Button) findViewById(R.id.btnBuscar);
		btnBuscar.setOnClickListener(btnBuscarListener);
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		pessoaDAO.close();
		
	}
	
	public void updateLV(){
		pessoas = pessoaDAO.getAll();
		ArrayAdapter<Pessoa> pessoaAdapter = new ArrayAdapter<Pessoa>(this, android.R.layout.simple_list_item_1, pessoas);
		pessoasListView.setAdapter(pessoaAdapter);
	}

	private OnItemClickListener onItemClickListener = new AdapterView.OnItemClickListener() {

		@SuppressLint("NewApi")
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			if(currentActionMode != null){
				currentActionMode.finish();
				currentActionMode = null;
			}
			Intent i = new Intent(SelecionaClienteActivity.this, DescricaoCliente.class);
			i.putExtra("pessoaId", pessoas.get(position).getId());
			startActivityForResult(i, 21);
			
		}
		
	};
	
	private OnClickListener btnBuscarListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent i = new Intent(SelecionaClienteActivity.this, TipoCrimeActivity.class);
			startActivity(i);
		}
	};
}
