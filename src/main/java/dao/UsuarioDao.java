package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Locale;

import model.Aposta;
import model.Usuario;

public class UsuarioDao{
	
	private Connection conexao;
	private Usuario user;

	public UsuarioDao(Usuario usuario) {
		this.user = usuario;
		this.conexao = new Conexao().getConnection();
	}
	
	public boolean validarUser() {
		//Validar login
		String sql = "select * from usuarios where email = ? and senha = ? ;";
		
		try {
			
			//setando valores na query
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, this.user.getEmail());
			statement.setString(2, this.user.getSenha());
			statement.executeQuery();
			
			//capturando resultado da query
			ResultSet result = statement.getResultSet();
			
			boolean auth = false;
			while(result.next()) {
				//Caso ele estja no BD busca as demais informações do usuario
				this.user.setNome(result.getString("nome"));
				this.user.setId(result.getInt("id"));
				auth = true;
			}
			
			//Irá retirna falso ou true
			return auth;
			
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean buscarUsuario() {
		//Buscar todos os atribustos de um usuário
		String sql = "select * from usuarios where id = ? ;";
		
		try {
			
			//setando valores na query
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, this.user.getId());
			statement.executeQuery();
			
			//capturando resultado da query
			ResultSet result = statement.getResultSet();
			
			
			while(result.next()) {
				//Caso ele estja no BD busca as demais informações do usuario
				this.user.setNome(result.getString("nome"));
				this.user.setSenha(result.getString("senha"));
				this.user.setEmail(result.getString("email"));
				
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}
	
	public boolean buscarUsuarioEmail() {
		//Buscar todos os atribustos de um usuário
		String sql = "select * from usuarios where email = ? ;";
		
		try {
			
			//setando valores na query
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, this.user.getEmail());
			statement.executeQuery();
			
			//capturando resultado da query
			ResultSet result = statement.getResultSet();
			
			
			while(result.next()) {
				//Caso ele esteja no BD busca as demais informações do usuario
				this.user.setId(result.getInt("id"));
				this.user.setNome(result.getString("nome"));
				this.user.setSenha(result.getString("senha"));
				
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}
	
	public ArrayList<Aposta> apostasUsuario(){
		ArrayList<Aposta> list = new ArrayList<Aposta>();
		
		String sql = "select * from apostas where id_usuario = ? ;";
		
		try {
			
			//setando valores na query
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, this.user.getId());
			statement.executeQuery();
			
			//capturando resultado da query
			ResultSet result = statement.getResultSet();
			
			
			while(result.next()) {
				//Buscando apostas associado ao usuário
				Aposta aposta = new Aposta();
				
				aposta.setId(result.getInt("id_aposta"));
				aposta.setValor(result.getDouble("valor"));
				aposta.setSorteados(result.getString("num_sorteados"));
				aposta.setEscolhidos(result.getString("num_escolhidos"));
				aposta.setEscolhidos(result.getString("num_escolhidos"));
				
				//Caputando data e convertendo para string
				//CAPTURANDO UM TIMESTAMP DATA E HORA NO MESMO ATRIBUTO DO BANCO DE DADOS
                Timestamp dataHoraDB  = result.getTimestamp("data");
                SimpleDateFormat formatada = new SimpleDateFormat("dd/MM/yyyy",  new Locale("pt", "BR"));
                
                String horaData = formatada.format(dataHoraDB);
				
				aposta.setData(horaData);
				aposta.setLucroUser(result.getDouble("ganho_usuario"));
				
				list.add(aposta);	
			}
			
		}catch (SQLException e) {
			System.out.println(e);
		}
		
		
		return list;
	}
	
	public void cadastraUser() {
		
		String sql = "insert into usuarios (email,senha,nome) values (?,?,?);";
		
		try {
			
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, this.user.getEmail());
			statement.setString(2, this.user.getSenha());
			statement.setString(3, this.user.getNome());
			statement.execute();
			System.out.println("UsuarioDao.cadastraUser()");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public boolean updateUser() {
		//Buscar todos os atribustos de um usuário
		String sql = "select * from usuarios where id = ? ;";
		
		try {
			
			//setando valores na query
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, this.user.getId());
			statement.executeQuery();
			
			//capturando resultado da query
			ResultSet result = statement.getResultSet();
			
			String email = "";
			while(result.next()) {
				
				email = result.getString("email");
			}
			
			boolean emailEncontrado = this.buscarUsuarioEmail(this.user.getEmail());
			if(email.equals(this.user.getEmail()) || emailEncontrado == false) {
				
				this.update();
				return true;
				
			}
			
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
		
	}
	
	private void update() throws SQLException {
		
		
		String sql = "update usuarios set email = ?, nome = ?, senha = ? where id = ?;";
		
		PreparedStatement statement = conexao.prepareStatement(sql);
		statement.setString(1, this.user.getEmail());
		statement.setString(2, this.user.getNome());
		statement.setString(3, this.user.getSenha());
		statement.setInt(4, this.user.getId());
		statement.execute();
	}
	
	//UTILIZEI O POLIMORFISMO SOBRECARGA
	private boolean buscarUsuarioEmail(String email) {
		//Buscar todos os atribustos de um usuário
		String sql = "select * from usuarios where email = ? ;";
		
		try {
			
			//setando valores na query
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setString(1, email);
			statement.executeQuery();
			
			//capturando resultado da query
			ResultSet result = statement.getResultSet();
			
			
			while(result.next()) {
				
				return true;
			}
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return false;
	}
		
	public void deleteUser() {
		
		String sql = "delete from apostas where id_usuario = ?";
		try {
			
			PreparedStatement statement = conexao.prepareStatement(sql);
			statement.setInt(1, this.user.getId());
			statement.execute();
			
		} catch (SQLException e) {
			System.out.println("Não consegui excluir as apostas do usuario"+e);
		}
		
		String sql2 = "delete from usuarios where id = ?";
		try {
			
			PreparedStatement statement2 = conexao.prepareStatement(sql2);
			statement2.setInt(1, this.user.getId());
			statement2.execute();
			
		} catch (SQLException e) {
			System.out.println("Não consegui excluir o usuario"+e);
		}
	}
	
	
	
	public void closeConexao() throws SQLException {
		this.conexao.close();
	}
	
}
