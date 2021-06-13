package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Aposta;

public class ApostaDao {
	
	
	private Aposta aposta;
	private Connection conexao;

	public ApostaDao(Aposta aposta) {
		this.aposta = aposta;
		this.conexao = new Conexao().getConnection();
	}
	
	public int salvar(){
		
		//Como vem em vetor vou converter para Array para salva nesse formato.
		//ArrayList escolhidos = this.converteVetor(aposta.getNumEscolhidos());
		//ArrayList sorteados = this.converteVetor(aposta.getNumSorteados());
		
		String sorteados = this.converteVetor(aposta.getNumSorteados());
		String escolhidos = this.converteVetor(aposta.getNumEscolhidos());
		
		String sql = "insert into apostas (valor,num_sorteados,num_escolhidos,data,ganho_usuario,id_usuario)"
				+ " values (?, ?, ?, current_timestamp(), ?,?);";
		
		try {
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setDouble(1, this.aposta.getValor());
			statement.setString(2, sorteados);
			statement.setString(3, escolhidos);
			statement.setDouble(4, this.aposta.getLucroUser());
			statement.setInt(5, this.aposta.getUsuario().getId());
			
			statement.execute();
			
			System.out.println("salvei a aposta DAO");
			
			return this.idAposta();
			
		} catch (SQLException e) {
			System.out.println("Não consegui salva a aposta no BD: "+e);
		}
		
		return 0;
	}
	
	
	public String converteVetor(int[] vetor) {
		String armazena = "";
		
		for(int i : vetor) {
			armazena += i + ",";
		}
		return armazena;
	}
	
	public int idAposta() {
		
		int id = 0;
		
		String sql = "select id_aposta from apostas;";
		
		try {
			
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.executeQuery();
			
			ResultSet result = statement.getResultSet();
			
			while(result.next()) {
				id = result.getInt("id_aposta");
			}
			
		} catch (SQLException e) {
			
			System.out.println("Não consegui busca id da aposta" +e);
		}
		
		return id;
	}
	
	
}
