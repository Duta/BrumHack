package DataRequest;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PrintIt {
	PrintWriter pw;
	
	public PrintIt() throws IOException{
		pw= new PrintWriter(new FileWriter("/home/schubocks/questions.csv"));
	}
	
	
	public void printIt(String toPrint) throws Exception{
		pw.println(toPrint);
	}
	
	public void closeIt(){
		pw.close();
	}
}