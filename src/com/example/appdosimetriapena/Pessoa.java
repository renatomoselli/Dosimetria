package com.example.appdosimetriapena;

import java.io.Serializable;

public class Pessoa implements Serializable{
	private static final long serialVersionUID = 1L;
	private long id;
	private String nome;//, horarioRegistro;
	private int sexo, idade;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
//	public String getHorarioRegistro() {
//		return horarioRegistro;
//	}
//	public void setHorarioRegistro(String horarioRegistro) {
//		this.horarioRegistro = horarioRegistro;
//	}
	public int getSexo(){
		return sexo;
	}
	public void setSexo(int sexo) {
		this.sexo = sexo;
	}
	public int getIdade() {
		return idade;
	}
	public void setIdade(int idade) {
		this.idade = idade;
	}
	
	
	@Override
	public String toString() {
		return "Cliente [nome=" + nome + "]";
	}
	
	
}
