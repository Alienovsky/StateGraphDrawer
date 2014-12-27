package com.kamilmade.pogo

import groovy.transform.ToString

@ToString
class GraphNode {
    String source;
    String target;
    String type;
    boolean isBaseState;
    def BaseStates = [];

    public String toString(){
        return "Source: " + source
                + "Target: " + target
                + "Type: " + type
                + "IsBaseState: " + isBaseState
    }

}
