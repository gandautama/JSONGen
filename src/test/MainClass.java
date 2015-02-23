package test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;
/**
 * 
 * @author ganda
 * reference http://www.javabeat.net/java-json-api-jsr-353/
 */
public class MainClass {
	public static void main(String[] args) throws Exception{
		JSONStringBuilder jsb=new JSONStringBuilder();

		jsb.openTextFile("menado.txt");
		jsb.createOutFile("outFile.txt");
			
		System.out.println("converting from menado.txt to outfile.txt");
		
		//testCopy
		while (!jsb.isEof()) {
			StringWriter strWriter = new StringWriter();
	        Map<String, Object> properties = new HashMap<String, Object>(1);
	        properties.put(JsonGenerator.PRETTY_PRINTING, true);
	        JsonGeneratorFactory jgf = Json.createGeneratorFactory(properties);
	        JsonGenerator jg = jgf.createGenerator(strWriter);
			
			String myStr = jsb.readString();
			if (myStr == null)
				break;
			if (myStr.startsWith("[")) {
				int lastIdx = myStr.indexOf(']');
				if (lastIdx == -1)
					throw new Exception("] not found in line please fix");
				String valueType = myStr.substring(1, lastIdx);
				jg.writeStartObject().write("name", valueType).writeEnd();
				jg.flush();
				jsb.addString(strWriter.toString());
				System.out.println(strWriter.toString());
			}
			;
			strWriter.close();
			strWriter=null;
		}
		
		jsb.closeInFile();
		jsb.closeOutFile();
		System.out.println("done");
	}
}

//.write("name", lastIdx)              //    "name":"Jane Doe",
//.writeStartObject("address")         //    "address":{
//    .write("type", 1)                //        "type":1,
//    .write("street", "1 A Street")   //        "street":"1 A Street",
//    .writeNull("city")               //        "city":null,
//    .write("verified", false)        //        "verified":false
//.writeEnd()                          //    },
//.writeStartArray("phone-numbers")    //    "phone-numbers":[
//    .writeStartObject()              //        {
//        .write("number", "555-1111") //            "number":"555-1111",
//        .write("extension", "123")   //            "extension":"123"
//    .writeEnd()                      //        },
//    .writeStartObject()              //        {
//        .write("number", "555-2222") //            "number":"555-2222",
//        .writeNull("extension")      //            "extension":null
//    .writeEnd()                      //        }
//.writeEnd()                          //    ]
//.writeEnd()                              // }
