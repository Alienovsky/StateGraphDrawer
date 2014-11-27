package com.kamilmade.parser

import com.kamilmade.pogo.GraphNode


public class XMLStatesParser {
    String readXML(String fileName) {
        // Read the user info parser from the resource
        println fileName
        return getClass().getResource( fileName ).text
    }

    List parseGraphNodeXml2(xml){
        def slurperXml = new XmlSlurper().parseText(xml);
        def graphNodeList = [];
        def graphNodeListTemp = [];
        def xmlParser = new XmlParser().parseText(xml);
        //TODO: need to do internal loop for many transitions in one state
        slurperXml.State.each { usrNode ->
            if (usrNode.DynamicTransition != null) {
                usrNode.DynamicTransition.TargetStates.StaticState.each { targetState ->
                    GraphNode graphNodeTemp = new GraphNode()
                    graphNodeTemp.setSource(usrNode.@name.text())
                    graphNodeTemp.setTarget(targetState.@state.text())
                    graphNodeTemp.setType("resolved")
                    graphNodeListTemp.add(graphNodeTemp)
                }
                graphNodeList.addAll(graphNodeListTemp)
            }
            if(graphNodeListTemp.isEmpty()){
           // else{
                GraphNode graphNode = new GraphNode()
                graphNode.setSource(usrNode.@name.text())
                graphNode.setTarget(usrNode.Transition.@state.text())
                graphNode.setType("suit")
                graphNodeList.add(graphNode);
            }
        }
        return graphNodeList;
    }



    List parseGraphNodeXml(xml){
        def graphNodeList = [];
        def states = new XmlParser().parseText(xml);
        states.State.each { state ->
            state.value().each {transition ->
                if(transition.name()=="DynamicTransition"){
                    def graphNodeListTemp = [];
                    transition.value().each { targetStates ->
                       targetStates.value().each { staticState ->
                           GraphNode graphNodeTemp = new GraphNode()
                           graphNodeTemp.setSource(transition.parent().'@name')
                           graphNodeTemp.setTarget(staticState.'@state')
                           graphNodeTemp.setType("resolved")
                           graphNodeListTemp.add(graphNodeTemp)  
                       }
                    }
                    graphNodeList.addAll(graphNodeListTemp)
                }
                if(transition.name()=="Transition"){
                    GraphNode graphNodeTemp = new GraphNode()
                    graphNodeTemp.setSource(transition.parent().'@name')
                    graphNodeTemp.setTarget(transition.'@state')
                    graphNodeTemp.setType("suit")
                    graphNodeList.add(graphNodeTemp);
                }
            }
        }

        return graphNodeList;
    }


/*    String converToJson(obj){
        String json = JsonOutput.toJson(obj)
        return JsonOutput.prettyPrint(json);
    }*/
}
