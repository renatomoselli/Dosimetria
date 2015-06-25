package com.example.appdosimetriapena;

import java.sql.Date;
import java.text.SimpleDateFormat;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
	
	
public class CadastroClienteActivity extends Activity {
	Button btnSalvar;
	EditText editNome, editIdade;
	RadioButton radioM, radioF;
	PessoaDAO pessoaDAO;
	Pessoa pessoa;
	String nome;
	int idade, sexo;
	long id;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cadastro_cliente);
		
		editNome = (EditText) findViewById(R.id.editNome);
		editIdade = (EditText) findViewById(R.id.editIdade);
		radioM = (RadioButton) findViewById(R.id.rdMasculino);
		radioF = (RadioButton)findViewById(R.id.rdFeminino);
		pessoa = new Pessoa();
		pessoaDAO = new PessoaDAO(this);
		pessoa = pessoaDAO.getById(id);
		
		btnSalvar = (Button) findViewById(R.id.btnSalvar);
		btnSalvar.setOnClickListener(btnSalvarListener);
	}
	
	private OnClickListener btnSalvarListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			String aux;
			aux = editIdade.getText().toString();
			nome = editNome.getText().toString();
			idade = Integer.parseInt(aux);
			if(radioM.isChecked()){
				sexo = 0;
			}else if(radioF.isChecked()){
				sexo = 1;
			}
//			String horarioAtual = SimpleDateFormat.getDateTimeInstance().format(new Date(System.currentTimeMillis()));
			
			pessoa.setNome(nome);
			pessoa.setIdade(idade);
			pessoa.setSexo(sexo);
//			pessoa.setHorarioRegistro();

			pessoaDAO.update(pessoa);
			
			CadastroClienteActivity.this.finish();
		}
	};
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		pessoaDAO.close();
	}
}
