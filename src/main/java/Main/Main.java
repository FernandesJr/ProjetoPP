package Main;



public class Main {

	public static void main(String[] args) {
		
		
		String[] escolhas = {"1","13","14","25","6"};
		String numeros = "";
		for (String string : escolhas) {
			numeros += string + ",";
		}
		
		System.out.println(numeros);
		
		//Aposta a = new Aposta();
		//a.sorteio();
		//int acertos = a.numerosEscolhidos(escolhas);
		//System.out.println("Acertei: " + acertos);

	}

}
