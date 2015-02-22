package test;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class JSONStringBuilder {
	OutputStream output;
           
	public JSONStringBuilder(){
		output = null;
	}
	public void createFile(String filename){
		try {
			output =  new BufferedOutputStream( new FileOutputStream(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addJasonString(String s){
		try {
			output.write(s.getBytes());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void closeFile (){
	try {
		output.close();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
}
