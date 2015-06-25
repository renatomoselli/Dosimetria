package com.example.appdosimetriapena;



public class PenaBase {
	int id;
	String nome;
	int  meses;

	public PenaBase(int id, String nome, int meses) {
		super();
		this.id = id;
		this.nome = nome;
		this.meses = meses;
	}
	public int getMes() {
		return meses;
	}
	public void setMeses(int meses) {
		this.meses = meses;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "PenaBase [id=" + id + ", nome=" + nome + ", meses=" + meses
				+ "]";
	}


	

}
