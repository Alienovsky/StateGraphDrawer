package com.kamilmade.service

import com.kamilmade.pogo.MyNode
import com.kamilmade.pogo.Transition
import com.kamilmade.pogo.WorkflowBaseState
import com.kamilmade.pogo.WorkflowState
/**
 * Creates nodes needed to generate JSON from which will be drawn graph
 */
class NodeCreator {

    public List<MyNode> createNodesFromWorkflowStatesAndWorkflowBaseStates(List<WorkflowState> workflowStates, List<WorkflowBaseState> workflowBaseStates){
        List<MyNode> listaNode = new ArrayList<>();
        for(WorkflowState ws : workflowStates){
            List<Transition> listaTrans = ws.getTranstions();
            for(String wbs : ws.getBaseStateNames()){
                listaTrans.addAll(getTransitions(wbs,workflowBaseStates));
            }
            for(Transition transition : listaTrans){
                MyNode node = new MyNode();
                node.setSource(ws.getName());
                node.setTarget(transition.getTarget());
                node.setAction(transition.getAction())
                node.setType(transition.getType());
                listaNode.add(node);
            }
        }
        return listaNode;
    }

    private List<Transition> getTransitions(String baseStateName, List<WorkflowBaseState> baseStates){
        WorkflowBaseState workflowBaseState = baseStates.find { it -> it.name = baseStateName};
        return workflowBaseState.transtions;
    }
}
