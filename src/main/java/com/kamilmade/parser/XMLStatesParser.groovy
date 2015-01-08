package com.kamilmade.parser

import com.kamilmade.pogo.GraphNode


public class XMLStatesParser {
   public String readXML(String fileName) {
        // Read the user info parser from the resource
        //println fileName
        return getClass().getResource( fileName ).text
    }
    public List parseGraphNodeXml(xml){
        def graphNodeList = [];
        def states = new XmlParser().parseText(xml);
        states.State.each { state ->
            GraphNode graphNode = new GraphNode()
            graphNode.setIsBaseState(false)
            state.value().each { transition ->
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
                else if(transition.name()=="Transition"){
                    graphNode.setSource(transition.parent().'@name')
                    graphNode.setTarget(transition.'@state')
                    graphNode.setType("suit")
                }
                else if(transition.name()=="BaseStates"){
                    transition.value().each { baseState ->
                        graphNode.BaseStates.add(baseState.'@name')
                    }
                }
            }
            graphNodeList.add(graphNode)
        }
        states.BaseSate.each { baseState ->
            GraphNode graphNode = new GraphNode()
            graphNode.setIsBaseState(true)
            baseState.value().each { transition ->
                if(transition.name()=="Transition"){
                    graphNode.setSource(transition.parent().'@name')
                    graphNode.setTarget(transition.'@state')
                    graphNode.setType("suit")
                }
            }
            graphNodeList.add(graphNode)
        }
        return graphNodeList;
    }

    public List<GraphNode> checkAllNodesAndGenerateProperStatesFromTheirBaseStates(List<GraphNode> graphNodeList){
        def filteredList;
        graphNodeList.each{ node ->
            if(node.BaseStates.isEmpty){

            }
        }
    }




/*    String converToJson(obj){
        String json = JsonOutput.toJson(obj)
        return JsonOutput.prettyPrint(json);
    }*/
}
