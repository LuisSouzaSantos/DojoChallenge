import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;

public class Start {
	
	private static HashMap<String, String> words = new HashMap<String, String>();
	
	public static void loadWordList() throws IOException {
		BufferedReader bufferedReader =  new BufferedReader(new FileReader("privateWords.txt"));
		String line = bufferedReader.readLine();
		while(line != null) {
			String word = line.trim();
			words.put(word, word);
			line = bufferedReader.readLine();
		}
		bufferedReader.close();
	}
	
	
	public static void main(String[] args) {
		
		try {
			loadWordList();
			String content = new String(Files.readAllBytes(Paths.get("jquery.txt")));
            String newContent = content
            						.replaceAll("(?s:/\\*.*?\\*/)|//.*", "")
            						.replaceAll("/\\*[^*]*(?:\\*(?!/)[^*]*)*\\*/|//.*", "")
            						.replaceAll("\\s+", "");
           
            String newDoNewContent = "";
            
            FileWriter arquivo = new FileWriter("saida.txt");
            FileWriter arquivo2 = new FileWriter("saida2.txt");

            words.forEach((key,value) -> {
            	newContent.replaceAll(value, "");
            	try {
					arquivo.append("<"+value+", >");
					arquivo.flush();
				} catch (IOException e) {
					e.printStackTrace();
				}      	
            });
            
            try {
				arquivo2.append(newContent);
				arquivo2.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            PrintWriter gravadorArquivo = new PrintWriter(arquivo);
            PrintWriter gravadorArquivo1 = new PrintWriter(arquivo2);
            gravadorArquivo.close();
            gravadorArquivo1.close();
		}catch(IOException e) { System.out.println(e.getMessage());}
		
	}

}
