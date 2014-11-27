package com.kamilmade

import com.kamilmade.parser.XMLStatesParser
import groovy.json.JsonOutput

class Starter {
    public static void main() {
        // 1. Create an instance
        def parser = new XMLStatesParser();

        // 2. read the parser
        def xml = parser.readXML('/states.xml');
        def myList = parser.parseGraphNodeXml(xml)

        String json = JsonOutput.toJson(myList);
        println JsonOutput.prettyPrint(json);

        try {
            FileWriter fileWriter = new FileWriter("/test.json");
            fileWriter.write(JsonOutput.prettyPrint(json));
            fileWriter.flush();
            fileWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
