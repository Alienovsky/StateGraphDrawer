package com.kamilmade

import com.kamilmade.parser.StatesParserFromXML
import com.kamilmade.pogo.MyNode
import com.kamilmade.service.NodeCreator
import groovy.json.JsonOutput

class Starter {
    public static void main() {

       // println JsonOutput.prettyPrint(json);*/
        def parser = new StatesParserFromXML();
        def xml = parser.readXMLfromFile('/states.xml');
        def baseStates = parser.parseXMLAndReturnWorkflowBaseStates(xml);
        def states = parser.parseXMLAndReturnWorkflowStates(xml);
        NodeCreator nodeCreator = new NodeCreator();
        List<MyNode> lista;
        lista = nodeCreator.createNodesFromWorkflowStatesAndWorkflowBaseStates(states,baseStates);
        lista.forEach{it -> println(it.toString())}
        String json = JsonOutput.toJson(lista);

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
