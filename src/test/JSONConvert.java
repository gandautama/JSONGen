package test;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.json.Json;
import javax.json.stream.JsonGenerator;
import javax.json.stream.JsonGeneratorFactory;

public class JSONConvert {
	public static void convert(String infile, String outfile) throws Exception {
		JSONStream jstream = new JSONStream();
		jstream.openTextFile(infile);
		jstream.createOutFile(outfile);
		System.out.println("converting from "+infile+" to "+outfile);
		// testCopy
		while (!jstream.isEof()) {
			StringWriter strWriter = new StringWriter();
			Map<String, Object> properties = new HashMap<String, Object>(1);
			properties.put(JsonGenerator.PRETTY_PRINTING, true);
			JsonGeneratorFactory jgf = Json.createGeneratorFactory(properties);
			JsonGenerator jg = jgf.createGenerator(strWriter);

			String myStr = jstream.readString();
			if (myStr == null)
				break;
			if (myStr.startsWith("[")) {
				int lastIdx = myStr.indexOf(']');
				if (lastIdx == -1)
					throw new Exception("] not found in line please fix");
				String mainStr = myStr.substring(1, lastIdx);
				String headerStr = jstream.readString();
				String headersArr[] = headerStr.split("\t");
				
				jg.writeStartObject().writeStartArray(mainStr);
				
				boolean isContainBracket=false;
				
				do {
					isContainBracket=false;
					myStr = jstream.readString();
					
					
					if (myStr==null||myStr.contains("[")|| myStr.contains("]")){
						isContainBracket =true;
						break;
					}
					String contentsArr[] = myStr.split("\t");
					if (contentsArr.length<1) continue;
					jg.writeStartObject();
					
					for(int  contentsIdx=0 ; contentsIdx <contentsArr.length ; contentsIdx++){
						jg.write(headersArr[contentsIdx],contentsArr[contentsIdx]) ;
					}
					jg.writeEnd();
					
				} while (!isContainBracket);
				
				jg.writeEnd();
				
				jg.writeEnd();
				
				jg.flush();
				jstream.addString(strWriter.toString());
				System.out.println(strWriter.toString());
			}
			strWriter.close();
		}
		jstream.closeInFile();
		jstream.closeOutFile();
		System.out.println("done");
	}
}


//.write("name", lastIdx)              //    "name":"Jane Doe",
//.writeStartObject("address")         //    "address":{
//  .write("type", 1)                //        "type":1,
//  .write("street", "1 A Street")   //        "street":"1 A Street",
//  .writeNull("city")               //        "city":null,
//  .write("verified", false)        //        "verified":false
//.writeEnd()                          //    },
//.writeStartArray("phone-numbers")    //    "phone-numbers":[
//  .writeStartObject()              //        {
//      .write("number", "555-1111") //            "number":"555-1111",
//      .write("extension", "123")   //            "extension":"123"
//  .writeEnd()                      //        },
//  .writeStartObject()              //        {
//      .write("number", "555-2222") //            "number":"555-2222",
//      .writeNull("extension")      //            "extension":null
//  .writeEnd()                      //        }
//.writeEnd()                          //    ]
//.writeEnd()                              // }