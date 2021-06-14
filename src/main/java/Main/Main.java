package Main;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.Conexao;
import model.Aposta;
import model.Usuario;

public class Main {

	public static void main(String[] args) {
		
		/*
		String[] escolhas = {"1","13","14","25","6"};
		String numeros = "";
		for (String string : escolhas) {
			numeros += string + ",";
		}
		
		System.out.println(numeros);
		*/
		//Aposta a = new Aposta();
		//a.sorteio();
		//int acertos = a.numerosEscolhidos(escolhas);
		//System.out.println("Acertei: " + acertos);
		//Conexao com = new Conexao();
		//com.getConnection();
		
		Usuario user = new Usuario(2);
		try {
			ArrayList<Aposta> list = user.buscarApostas();
			for(Aposta aposta: list) {
				System.out.println(aposta.getEscolhidos());
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		

	}

}
