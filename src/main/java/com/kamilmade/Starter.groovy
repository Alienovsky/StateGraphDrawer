package com.kamilmade

import com.kamilmade.parser.StatesParserFromXML
import com.kamilmade.pogo.MyNode
import com.kamilmade.service.NodeCreator

class Starter {
    public static void main() {
        /* // 1. Create an instance
        def parser = new XMLStatesParser();

        // 2. read the parser
        def xml = parser.readXML('/states.xml');
        def myList = parser.parseGraphNodeXml(xml)

        String json = JsonOutput.toJson(myList);
       // println JsonOutput.prettyPrint(json);*/
        def parser = new StatesParserFromXML();
        def xml = parser.readXMLfromFile('/states.xml');
        def baseStates = parser.parseXMLAndReturnWorkflowBaseStates(xml);
        def states = parser.parseXMLAndReturnWorkflowStates(xml);
        NodeCreator nodeCreator = new NodeCreator();
        List<MyNode> lista;
        lista = nodeCreator.createNodesFromWorkflowStatesAndWorkflowBaseStates(states,baseStates);
        lista.forEach{it -> println(it.toString())}
        /*
         try {
             FileWriter fileWriter = new FileWriter("/test.json");
             fileWriter.write(JsonOutput.prettyPrint(json));
             fileWriter.flush();
             fileWriter.close();

         } catch (IOException e) {
             e.printStackTrace();
         }*/
    }


}
