package test;

import java.io.FileNotFoundException;
import java.io.IOException;

import javax.json.stream.*;

public class MainClass {
	public static void main(String[] args){
		JSONStringBuilder jsb=new JSONStringBuilder();
		try {
			jsb.openTextFile("menado.txt");
			jsb.createOutFile("outFile.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("copying files from");
		
		//testCopy
		while (!jsb.isEof()) {
			String myStr = jsb.readString();
			if (myStr==null) break ;
			if  (myStr.startsWith("[")){
				
			};
			
			System.out.println(myStr);
			jsb.addString(myStr);
		}
		jsb.closeInFile();
		jsb.closeOutFile();
		System.out.println("done");
	}
}
