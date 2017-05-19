import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class cifraVingenere {

    public static void leitor(String path, String palavraChave) throws IOException {
        BufferedReader buffRead = new BufferedReader(new FileReader(path));
        String linha = "";
        while (true) {
        	linha = buffRead.readLine();
            if (linha != null) {
            	System.out.println("Mensagem a ser codificada: " + linha);
 
            } else
                break;
            
            codificador(linha, palavraChave);
        }
        buffRead.close();
    }
 
    public static void escritor(String path) throws IOException {
        BufferedWriter buffWrite = new BufferedWriter(new FileWriter(path));
        String linha = "";
    	System.out.println("Escrevendo no arquivo a mensagem para ser codificada, digite fim para sair.");
    	Scanner in = new Scanner(System.in);
        while(true){
        	
        	System.out.println("Escreva algo: ");
        	linha = in.nextLine();
        	if (linha.equals("fim")){
        		buffWrite.close();
        		in.close();
        		break;
        	}else{        		
        		buffWrite.append(linha);
        		buffWrite.newLine();
        	}
        }
    }
    
    public static void codificador (String linha, String palavraChave) throws IOException{
    	BufferedWriter buffWrite = new BufferedWriter(new FileWriter("cifrado.txt", true)); //
    	String codificado = "";
    	char letra;
    	int aux=0;
    	for(int i=0; i<linha.length(); i++){
    		letra = linha.charAt(i);
    		if (aux == palavraChave.length())
    			aux = 0;
    		if (letra>='a' && letra<='z'){
    			int letraPalavra = palavraChave.charAt(aux) - 97;
    			int xic = (letra-97);
    			int e = (letraPalavra + xic)%26;
    	        char s = (char) (e+97);
    			codificado = codificado + s;
    			aux++;
    		}else
    			codificado = codificado + letra;
    		
    	}
    	
    	buffWrite.append(codificado);
    	buffWrite.newLine();
    	buffWrite.close();
    	System.out.println("Mensagem codificada: " + codificado);

    }
    
    public static void main(String args[]) throws IOException {
        String path = "mensagem.txt";
        String palavraChave = "";
        Scanner in = new Scanner(System.in);
        System.out.println("Digite a palavra chave para codificar a mensagem: ");
        palavraChave = in.nextLine();
        cifraVingenere.escritor(path);
        cifraVingenere.leitor(path,palavraChave);
        System.out.println("Codificação feita com sucesso!");
        in.close();
    }

}
