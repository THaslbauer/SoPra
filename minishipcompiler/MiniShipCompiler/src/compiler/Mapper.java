package compiler;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Mapper {

	private HashMap<String,Integer> map;
	private String deletedString;
	
	public Mapper(){
		map = new HashMap<String,Integer>();
	}
	
	public HashMap<String,Integer> getMap(){
		return map;
	}
	
	public String getDeletedString(){
		return deletedString;
	}
	
	public HashMap<String,Integer> mapAndDeleteTheLabels(String s){
		String adjusted = s.replaceAll("(?m)^[ \t]*\r?\n", "");
		//delete the own comments
		String[] noCommentspls = adjusted.split("\n");
		StringBuilder noCommentBuilder = new StringBuilder();
		for (String str: noCommentspls){
			if (str.startsWith("//")){
				continue;
			}else{
				noCommentBuilder.append(str);
				noCommentBuilder.append("\n");
			}
		}
		String adjustedNoComments = noCommentBuilder.toString();
		String[] resArray = adjustedNoComments.split("\n");
		int counter = 0;
		for (String t : resArray){
				if (t.contains(":")){
					String[] shortArray = t.split(":");
					String res = shortArray[0];
					map.put(res, counter);
					
					//delete the labels
					resArray[counter] = shortArray[1];
				}else{
					
				}
			counter++;
		}
		//make a String from the StringArray
		StringBuilder builder = new StringBuilder();
		for (String str :resArray){
			builder.append(str);
			builder.append("\n");
			
		}
		deletedString = builder.toString();
		return map;
	}
}
