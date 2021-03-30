package model;

import java.sql.SQLException;

import dao.UsuarioDao;

public class Usuario {
	
	private int id;
	private String email;
	private String senha;
	private String nome;
	
	
	public Usuario(int id, String email, String senha) {
		
		this.id = id;
		this.email = email;
		this.senha = senha;
	}

	public Usuario(String email, String senha) {
		
		this.email = email;
		this.senha = senha;
	}
	
	public Usuario(int id) {
		
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean validarUser() throws SQLException {
		
		UsuarioDao userDao = new UsuarioDao(this);
		boolean auth = userDao.validarUser();
		userDao.closeConexao();
		return auth;
	}
	
	public boolean buscarUser() throws SQLException {
		UsuarioDao userDao = new UsuarioDao(this);
		boolean auth = userDao.buscarUsuario();
		userDao.closeConexao();
		return auth;
	}
	
	
}
