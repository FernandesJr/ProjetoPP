package dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
	
	//default do curso -> com.mysql.jdbc.Driver | Atualizado -> com.mysql.cj.jdbc.Driver
	private String drive = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/loteria?useTimezone=true&serverTimezone=UTC";
	private String user = "root";
	private String passoword = "";
	
	Connection conexao = null;
	
	public Connection getConnection(){
		
		try {
			Class.forName(drive);
			conexao = DriverManager.getConnection(url, user, passoword);
			System.out.println("conectado");
			
		} catch (Exception e) {
			System.out.println("ERRO na tentativa de conex�o: " + e);
		}
		return conexao;
	}
}
