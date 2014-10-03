package main;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

import compiler.Mapper;
import compiler.Replacer;
import reader.OurTacticReader;

public class Main {

	public static void main(String[] args) throws IOException {
		
		OurTacticReader reader = new OurTacticReader(args[0]);
		String ourString = reader.readToString();
		Mapper mapper = new Mapper();
		mapper.mapAndDeleteTheLabels(ourString);
		Replacer rep = new Replacer(mapper.getDeletedString(),mapper.getMap());
		String res = rep.replace();
		
		OutputStream outputStream = new FileOutputStream (args[1]);
		Writer writer = new OutputStreamWriter(outputStream);
		writer.write(res);
		writer.close();
	}

}
