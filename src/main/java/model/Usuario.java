package model;

import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.ArrayList;

import dao.UsuarioDao;

public class Usuario {
	
	private int id;
	private String email;
	private String senha;
	private String nome;
	//Uso apenas para mostrar no Dashboard
	private int qtApostas = 0;
	private int porcentGanhos = 0;
	private String totGanhos = "";
	private String dayGanhos = "";
	
	
	public Usuario(){
		
	}
	
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
	
	public int getQtApostas() {
		return qtApostas;
	}

	public void setQtApostas(int qtApostas) {
		this.qtApostas = qtApostas;
	}

	public String getTotGanhos() {
		return totGanhos;
	}

	public void setTotGanhos(String totGanhos) {
		this.totGanhos = totGanhos;
	}

	public int getPorcentGanhos() {
		return porcentGanhos;
	}

	public void setPorcentGanhos(int porcentGanhos) {
		this.porcentGanhos = porcentGanhos;
	}

	public String getDayGanhos() {
		return dayGanhos;
	}

	public void setDayGanhos(String dayGanhos) {
		this.dayGanhos = dayGanhos;
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
	
	public boolean buscarUserSessao() throws SQLException {
		UsuarioDao userDao = new UsuarioDao(this);
		boolean auth = userDao.buscarUsuarioEmail();
		userDao.closeConexao();
		return auth;
	}
	
	public ArrayList<Aposta> buscarApostas() throws SQLException{
		UsuarioDao userDao = new UsuarioDao(this);
		ArrayList<Aposta> apostas = userDao.apostasUsuario();
		userDao.closeConexao();
		return apostas;
	}
	
	public void cadastarUsuario() throws SQLException {
		UsuarioDao userDao = new UsuarioDao(this);
		userDao.cadastraUser();
		userDao.closeConexao();
	}
	
	public boolean updateUsuario()  throws SQLException{
		UsuarioDao userDao = new UsuarioDao(this);
		boolean atualizado = userDao.updateUser();
		userDao.closeConexao();
		return atualizado;
	}
	
	public void excluirUsuario() throws SQLException {
		UsuarioDao userDao = new UsuarioDao(this);
		userDao.deleteUser();
		userDao.closeConexao();
	}
	
	//Calcular os valores que iram prencher o dashboard do usuário
	public void calcularDash() throws SQLException {
		
		ArrayList<Aposta> apostas = this.buscarApostas();
		
		LocalDate hoje = LocalDate.now();
		
		int contAcertos = 0;
		int totGanhos = 0;
		int totGanhosDay = 0;
		for (Aposta aposta : apostas) {
			//Contando quantas apostas o usuário já fez
			
			this.qtApostas += 1;
			
			//Calculando o total ganhado
			totGanhos += aposta.getLucroUser();
			
			if(aposta.getLucroUser() != 0) {
				contAcertos++;
			}
			//Verificando ganhos do dia atual
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			TemporalAccessor parse = formatter.parse(aposta.getData());
			LocalDate dataApost = LocalDate.from(parse);
			if(hoje.equals(dataApost)) {
				totGanhosDay += aposta.getLucroUser();
			}
			
		}
		//Calculando a porcentagem de ganho
		if(contAcertos != 0) {
			this.porcentGanhos = (contAcertos * 100) / this.qtApostas;
		}
		
		//Formatando os valores em moeda
		DecimalFormat moeda = new DecimalFormat("###,###.00 ");
		if(totGanhos != 0) {
			this.totGanhos = moeda.format(totGanhos);
		}
		
		
		//Formatando os valores em moeda
		if(totGanhosDay != 0) {
			this.dayGanhos = moeda.format(totGanhosDay);
		}

	}
	
}
