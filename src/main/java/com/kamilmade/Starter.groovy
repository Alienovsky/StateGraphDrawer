package com.kamilmade

import com.kamilmade.parser2.StatesParserFromXML
import com.kamilmade.pogo.Transition
import com.kamilmade.pogo.WorkflowBaseState
import com.kamilmade.pogo.WorkflowState

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
        baseStates.each { baseState ->
            ((WorkflowBaseState)baseState).getTranstions().each { transition ->
                println(((WorkflowBaseState)baseState).getName() + " : " + ((Transition)transition))
            }
        }
        def states = parser.parseXMLAndReturnWorkflowStates(xml);
        states.each { state ->
            ((WorkflowState)state).getTranstions().each { transition ->
                println(((WorkflowState)state).getName() + " : " + ((Transition)transition))
            }
        }

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
