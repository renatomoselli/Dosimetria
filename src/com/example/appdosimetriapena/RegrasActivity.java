package com.example.appdosimetriapena;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class RegrasActivity extends Activity {
	long pessoaId;
	PessoaDAO pessoaDAO;
	Pessoa pessoa;
	RadioButton radio1, radio2, radio3, radio4, radioFurto, radioAssalto;
	Button btnCalcular;
	String auxCrime;
	int auxPena;
	double penaFinal;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_regras);

		radio1 = (RadioButton) findViewById(R.id.radio1);
		radio2 = (RadioButton) findViewById(R.id.radio2);
		radio3 = (RadioButton) findViewById(R.id.radio3);
		radio4 = (RadioButton) findViewById(R.id.radio4);
		radioFurto = (RadioButton) findViewById(R.id.radioFurto);
		radioAssalto = (RadioButton) findViewById(R.id.radioAssalto);

		btnCalcular = (Button) findViewById(R.id.button1);

		btnCalcular.setOnClickListener(calcular);

	}
	
	private OnClickListener calcular = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			selecionaCrime();
			pegarPenaBase();
			
		}
	};

	private void pegarPenaBase() {
		String resourceURL = "http://10.0.2.2:3000/pena/" + auxCrime;
		AsyncHttpClient httpClient = new AsyncHttpClient();

		httpClient.get(resourceURL, new JsonHttpResponseHandler() {
			@Override
			public void onSuccess(int statusCode, Header[] headers,
					JSONArray response) {
				// String aux = edt.getText().toString();
				// int idAux = Integer.parseInt(aux);
				
				for (int i = 0; i < response.length(); i++) {
					try {
						JSONObject obj = response.getJSONObject(i);
						PenaBase m = new PenaBase(obj.getInt("id"), obj
								.getString("nome"), obj.getInt("mes"));
						if (auxCrime.equals(m.getNome())) {
							auxPena = m.getMes();
						}
						// categoria.add(m);
					} catch (JSONException e) {
						e.printStackTrace();
					}
				}
			}
		});
		
		if(radio1.isChecked()){
			double atenuante = auxPena/3;
			penaFinal = auxPena - atenuante;
		}
		
		if(radio3.isChecked()){
			double agravante = penaFinal/3;
			penaFinal = penaFinal + agravante;
		}
		
		//pessoa = new Pessoa();
		
 		pessoaId = getIntent().getLongExtra("pessoaId", 0);
		pessoaDAO = new PessoaDAO(this);
		pessoa = pessoaDAO.getById(pessoaId);
		String ateraPena = "" +penaFinal;
		int altPena = Integer.parseInt(ateraPena);
		pessoa.setPena(altPena);
		
		pessoaDAO.update(pessoa);
		
		Toast.makeText(this, "Calculo realizado com sucesso!", Toast.LENGTH_LONG).show();
		
		Intent i = new Intent(RegrasActivity.this, MainActivity.class);
		startActivity(i);
		
	}

	private void selecionaCrime() {
		if (radioFurto.isChecked()) {
			auxCrime = "furto";
		} else {
			auxCrime = "assalto";
		}
	}
}
