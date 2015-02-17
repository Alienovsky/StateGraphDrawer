package com.kamilmade.parser

import com.kamilmade.pogo.Transition
import com.kamilmade.pogo.WorkflowBaseState
import com.kamilmade.pogo.WorkflowState


class StatesParserFromXML {

    String readXMLfromFile(String fileName) {
        // Read the user info parser from the resource
        return getClass().getResource( fileName ).text
    }

    public List<WorkflowBaseState> parseXMLAndReturnWorkflowBaseStates(xml){
        def workflowBaseStates = [];
        def states = new XmlParser().parseText(xml);
        states.BaseSate.each { baseStateNode ->
            WorkflowBaseState workflowBaseState = new WorkflowBaseState();
            workflowBaseState.setName(baseStateNode.'@name');
            baseStateNode.value().each { transitionFromNode ->
                if (transitionFromNode.name() == "Transition") {
                    Transition transition = new Transition();
                    transition.setTarget(transitionFromNode.'@state');
                    transition.setAction(transitionFromNode."@action");
                    transition.setType("suit");
                    workflowBaseState.getTranstions().add(transition);
                }
            }
            workflowBaseStates.add(workflowBaseState);
        }
        return workflowBaseStates;
    }

    public List<WorkflowState> parseXMLAndReturnWorkflowStates(xml){
        def workflowStates = [];
        def states = new XmlParser().parseText(xml);
        states.State.each { state ->
            WorkflowState workflowState = new WorkflowState();
            workflowState.setName(state.'@name');
            state.value().each { transitionNode ->
                if(transitionNode.name()=="DynamicTransition"){
                    transitionNode.value().each { targetStatesNode ->
                        targetStatesNode.value().each { staticStateNode ->
                            Transition transition = new Transition();
                            transition.setTarget(staticStateNode.'@state');
                            transition.setAction(transitionNode.'@action');
                            transition.setType("resolved");
                            workflowState.getTranstions().add(transition);
                        }
                    }
                }
                else if(transitionNode.name()=="Transition"){
                    Transition transition = new Transition();
                    transition.setTarget(transitionNode.'@state');
                    transition.setAction(transitionNode.'@action');
                    transition.setType("suit");
                    workflowState.getTranstions().add(transition);
                }
                else if(transitionNode.name()=="BaseStates"){
                    transitionNode.value().each { baseStateNode ->
                        workflowState.getBaseStateNames().add(baseStateNode.'@name');
                    }
                }
            }
            workflowStates.add(workflowState);
        }
        return workflowStates;
    }
}
