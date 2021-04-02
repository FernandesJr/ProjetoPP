package model;

import java.util.Random;

import dao.ApostaDao;

public class Aposta {
	
	private int[] numSorteados = new int[5];
	private int[] numEscolhidos;
	private Usuario usuario;
	private double valor;
	private double lucroUser;
	private int acertos = 0;
	private int id;
	//Vem do BD em formato String
	private String sorteados;
	private String escolhidos;
	private String data;
	
	
	
	public Aposta(String[] numEscolhidos, Usuario usuario, double valor) {
		
		this.numEscolhidos = this.converteInt(numEscolhidos);
		this.usuario = usuario;
		this.valor = valor;
	}
	
	public Aposta() {
		
	}

	public int sorteio() {
		
		
		for(int i = 0; i < 5; i++) {
			
			
			int num = this.numAletaorio(); //Gera um número aleatório
			if(i == 0) {
				this.numSorteados[i] = num;
			
			}else {
				for(int aux = 0; aux < i; aux++) {
					//Esse while varre o vetor existente, caso o num sorteado já exista
					//no vetor ele sortei outro numero
					while(num == this.numSorteados[aux]) {
						num = this.numAletaorio();
					}
					this.numSorteados[i] = num;
				}
			}
		}
		
		
		for(int i = 0; i < numSorteados.length; i++ ) {
			System.out.println("nª sorteado:" + numSorteados[i]);
		}
		
		//Vai comparar quantos números o usuário acertou
		int acertos = this.qtAcertos();
		this.lucroAposta(acertos); //Valor do lucro da aposta
		
		//salvar no BANCO DE DADOS e retorna o ID da aposta
		return this.salvarAposta();
	}
	
	public int numAletaorio() {
		
		int num = 0;
		while(num == 0) {
			num = new Random().nextInt(25);
		}
		
		return num;
	}
	
	public int qtAcertos() {
		int acertosl = 0;
		for(int i=0; i < this.numSorteados.length; i++) {
			
			for(int x=0; x < this.numEscolhidos.length; x++) {
				if(this.numSorteados[i] == this.numEscolhidos[x]) {
					acertosl++;
				}
			}
		}
		System.out.println("acertou: " + acertosl);
		this.acertos = acertosl;
		return acertosl;
	}
	
	public int[] converteInt(String[] listaString) {
		
		int[] lista = new int[5];
		
		for(int i = 0; i < lista.length; i++) {
			
			lista[i] = Integer.parseInt(listaString[i]);
			
		}
		return lista;
	}
	
	public void lucroAposta(int acertos){
		
		switch (acertos) {
		case 1:
			this.lucroUser = 0;
			break;
		case 2:
			this.lucroUser = this.valor + (this.valor * 100) / 100; //lucro de 100%
			break;
		case 3:
			this.lucroUser = this.valor + (this.valor * 500) / 100; //lucro de 500%
			break;
		case 4:
			this.lucroUser = this.valor + (this.valor * 1000) / 100; //lucro de 1000%
			break;
		case 5:
			this.lucroUser = this.valor + (this.valor * 2000) / 100; //lucro de 2000%
			break;
		default:
			break;
		}
		System.out.println("Você ganhou!!!!! R$" + this.lucroUser);
	}
	
	public int salvarAposta() {
		
		ApostaDao apostaDao = new ApostaDao(this);
		return apostaDao.salvar();
	}

	public int[] getNumSorteados() {
		return numSorteados;
	}

	public void setNumSorteados(int[] numSorteados) {
		this.numSorteados = numSorteados;
	}

	public double getLucroUser() {
		return lucroUser;
	}

	public void setLucroUser(double lucroUser) {
		this.lucroUser = lucroUser;
	}

	public int getAcertos() {
		return acertos;
	}

	public int[] getNumEscolhidos() {
		return numEscolhidos;
	}

	public void setNumEscolhidos(int[] numEscolhidos) {
		this.numEscolhidos = numEscolhidos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getSorteados() {
		return sorteados;
	}

	public void setSorteados(String sorteados) {
		this.sorteados = sorteados;
	}

	public String getEscolhidos() {
		return escolhidos;
	}

	public void setEscolhidos(String escolhidos) {
		this.escolhidos = escolhidos;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
	
	
	
}
