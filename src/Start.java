import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Start {
	
	private static HashMap<String, String> words = new HashMap<String, String>();
	private static String[] reserveRegexChar = {"+", "^"};
	
	public static void loadWordList() throws IOException {
		BufferedReader bufferedReader =  new BufferedReader(new FileReader("privateWords.txt"));
		String line = bufferedReader.readLine();
		while(line != null) {
			String word = line.trim();
			String[] wordAndDescription = word.split(",");
			words.put(wordAndDescription[0], wordAndDescription[1]);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
	}
	
	public static boolean isReserveRegexChar(String caracter) {
		for (String string : reserveRegexChar) {
			if(string.equals(caracter)) {
				return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		try {
			loadWordList();
			String content = new String(Files.readAllBytes(Paths.get("jquery.txt")));
            String newContent = content
            						.replaceAll("(?s:/\\*.*?\\*/)|//.*", "")
            						.replaceAll("/\\*[^*]*(?:\\*(?!/)[^*]*)*\\*/|//.*", "");
            						//.replaceAll("\\s+", "");
            
            FileWriter arquivo = new FileWriter("saida.txt");
            try {
            	arquivo.append(newContent);
            	arquivo.flush();
			} catch (IOException e) {}
            
            BufferedReader bufferedReader =  new BufferedReader(new FileReader("saida.txt"));
            FileWriter arquivo2 = new FileWriter("saida2.lex");
    		
            String line = bufferedReader.readLine();
            arquivo2.append("TOKEN|TIPO\n");
            while(line != null) {
            	String newLine = line;
            	words.forEach((key,value) -> {
            		if(newLine.contains(key)) {
            			try {
            				arquivo2.append("<"+key+",> | "+value+"\n");
            			} catch (IOException e) {
            				e.printStackTrace();
            			}  
            		}
            	});
            	line = bufferedReader.readLine();
            }
            bufferedReader.close();
            arquivo2.flush();
            
            PrintWriter gravadorArquivo = new PrintWriter(arquivo);
            PrintWriter gravadorArquivo1 = new PrintWriter(arquivo2);
            gravadorArquivo.close();
            gravadorArquivo1.close();
		}catch(IOException e) { System.out.println(e.getMessage());}
		
	}

}
