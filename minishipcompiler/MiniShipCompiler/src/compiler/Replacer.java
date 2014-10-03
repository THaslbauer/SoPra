package compiler;

import java.util.HashMap;

public class Replacer {

	private String s;
	private HashMap<String,Integer> map;
	
	public Replacer(String s, HashMap<String,Integer> map){
		this.s = s;
		this.map = map;
	}
	
	public String replace(){
		String[] newStrArray = s.split("\n");
		int counter = 0;
		for(String key: map.keySet()){
			for(String row : newStrArray){
				String[] noComments = row.split(";");
				String[] word = noComments[0].split(" ");
					if(key.equals(word[word.length-1])){
					//replace
					word[word.length-1] = map.get(key).toString();
					String zusammenBabString = "";
					
					StringBuilder builder = new StringBuilder();
					for (String str :word){
						builder.append(str+" ");
						
					}
					zusammenBabString = builder.toString();
					newStrArray[counter]=zusammenBabString;
				}
					
					counter++;
			}
			counter = 0;
			//s = s.replace(key, map.get(key).toString());
		}
		String result = "";
		for (String str : newStrArray){
			result += str+"\n";
		}
		return result;
	}
}
