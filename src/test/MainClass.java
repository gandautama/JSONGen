package test;

/**
 * 
 * @author ganda
 * reference http://www.javabeat.net/java-json-api-jsr-353/
 */
public class MainClass {
	public static void main(String[] args) throws Exception{
		JSONConvert.convert("menado.txt", "outFile.txt");
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
