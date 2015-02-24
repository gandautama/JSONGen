package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class JSONStream {
	BufferedWriter fileWriter;
	BufferedReader fileReader;
	boolean isReadEof;
           
	public JSONStream(){
		fileWriter = null;
		fileReader = null;
		isReadEof = false;
	}
	
	public BufferedWriter getWriter(){
		return fileWriter;
	}
	
	public void createOutFile(String filename) throws IOException{
			fileWriter = new BufferedWriter(
                    new FileWriter(filename));
	}
	
	public void openTextFile(String filename) throws FileNotFoundException{
			fileReader = new BufferedReader(
			        new FileReader(filename));
	}
	
	public void addString(String s){
		if (fileWriter == null) return;
		try {
			fileWriter.write(s);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean isEof(){
		return isReadEof;
	}
	
	public String readString(){
		String content = null;
		if (fileReader == null ) return null;
		try {
			content = fileReader.readLine();
			if (content == null) {
				isReadEof = true;
			} else {
				isReadEof = false;
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;
	}
	
	public void closeOutFile() throws IOException {
		if (fileWriter == null) return;
			fileWriter.close();
	}
	
	public void closeInFile() throws IOException{
		if (fileReader == null) return;
			fileReader.close();
	}
}
