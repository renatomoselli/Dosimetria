package com.example.appdosimetriapena;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DescricaoCliente extends Activity {
	TextView nome, idade, genero, pena;
	long pessoaId;
	PessoaDAO pessoaDAO;
	Pessoa pessoa;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_descricao_cliente);
		
		nome = (TextView) findViewById(R.id.textView1);
		idade = (TextView) findViewById(R.id.textView2);
		genero = (TextView) findViewById(R.id.textView3);
		pena = (TextView) findViewById(R.id.textView4);
		limpar();
		
 		pessoaId = getIntent().getLongExtra("pessoaId", 0);
		pessoaDAO = new PessoaDAO(this);
		pessoa = pessoaDAO.getById(pessoaId);
		carregaTextView();
		Button btn1 = (Button) findViewById(R.id.btn_OK);
		btn1.setOnClickListener(onClickBackListener);
		
		
		
	}
	public OnClickListener onClickBackListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			DescricaoCliente.this.finish();
		}

	};
	
	private void carregaTextView(){
	
		nome.setText("Nome = " + pessoa.getNome());
		idade.setText("Descrição = " + pessoa.getIdade());
		//txtV3.setText("Descrição = " + pessoa.getSexo());
		if(pessoa.getSexo() == 0){
			genero.setText("Genero = Masculino");
		}else{
			genero.setText("Genero = Feminino");
		}
		pena.setText("Pena = Pena de aproximadamente de" + pessoa.getPena() + " meses");
	}
	
	private void limpar(){
		nome.setText("");
		idade.setText("");
		genero.setText("");
		pena.setText("");
	}
	
	@Override
	protected void onDestroy(){
		super.onDestroy();
		pessoaDAO.close();
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent i){
		if(requestCode == 29 && resultCode == RESULT_OK){
			carregaTextView();
		}
	}
}

