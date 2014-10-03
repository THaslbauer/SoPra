package reader;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class OurTacticReader {
	
	private InputStreamReader reader;

	public OurTacticReader(String s){
		InputStream input;
		try {
			input = new FileInputStream(s);
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("there is no inputstring!");
		}
		reader = new InputStreamReader(input);
	}
	
	public String readToString() throws IOException{
		//Read the document
				String document = "";
				
				int data = reader.read();
				while(data != -1){
					char theChar = (char) data;
					document += theChar;
					data = reader.read();
				}
				return document;
	}
	
}
