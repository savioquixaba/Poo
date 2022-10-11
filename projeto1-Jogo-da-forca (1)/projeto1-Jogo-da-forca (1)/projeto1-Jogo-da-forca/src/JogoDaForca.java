import java.io.File;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;


public class JogoDaForca {
		private ArrayList<String> palavras = new ArrayList<>(); // lista de palavras lidas do arquivo
		private ArrayList<String> dicas = new ArrayList<>(); // lista de dicas lidas do arquivo
		private String dica=""; // dica da palavra sorteada
		private String[] letras; // letras da palavra sorteada
		private int acertos = 0; // contador de acertos
		private int penalidade;
		
	
		
    
    public JogoDaForca(String nomearquivo) throws Exception {
    	 try {
 	        Scanner arquivo = new Scanner(new File(nomearquivo));
 	        String palavra, dica;
 	        String[] partes;
 	        while(arquivo.hasNextLine()) {
 	            partes = arquivo.nextLine().split(";");
 	            palavra = partes[0];
 	            dica = partes[1];
 	            this.palavras.add(palavra);
 	            this.dicas.add(dica);	
 	        }	
 	    } catch(Exception e) {
 	        throw new Exception("Arquivo não encontrado");
 	    }
 
}
	public void iniciar() {
		
		Random sorteio = new Random();
			
		   if (palavras.size() != 0) {
			int num_sorteado = sorteio.nextInt(palavras.size());
			String pal_sorteada = palavras.get(num_sorteado);
			
			letras = new String[pal_sorteada.length()];
		
			for (int y = 0; y < pal_sorteada.length(); y++) {
				this.letras[y] = Character.toString(pal_sorteada.charAt(y));
            }
      
      this.dica = dicas.get(num_sorteado);
		   }
		}

	public String getDica() {
			return this.dica;
		}
		
	public int getTamanho() {
			return this.letras.length;
		}
		
	public ArrayList<Integer> getPosicoes(String letra) throws Exception{
		
		
			
			if(letra.length() > 1) {
				throw new Exception("Digite uma letra por vez.");
			}
			
			ArrayList<Integer> posicoes = new ArrayList<>();
			
			for(int e=0; e<letras.length; e++) {
				if(letras[e].equalsIgnoreCase(letra)) {
					letras[e] = "-";
					acertos = acertos +1;
					posicoes.add(e);
				}
			}
			
			if (posicoes.size() < 1) {
				penalidade =  penalidade++;
			}
			
			return posicoes;
		}
		
	public boolean terminou() {
			if(acertos == letras.length || penalidade == 6) {
				return true;
			}else {
				return false;
				
			}
		}
		
	public int getAcertos() {
			return acertos;
		}
		
	public int getPenalidade() {
			return penalidade;
		}
		
	public String getResultado() {
			if (acertos == letras.length) {
				return "voce venceu";
			}else if (penalidade == 6) {
				return "voce morreu enforcado";
			}else
				return "o Jogo ainda não acabou";
				
			}
		}