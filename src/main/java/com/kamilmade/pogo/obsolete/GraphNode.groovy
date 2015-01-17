package com.kamilmade.pogo.obsolete

import groovy.transform.ToString

@ToString
class GraphNode {
    String source;
    String target;
    String type;
    def BaseStates = [];

    public String toString(){
        return "Source: " + source
                + "Target: " + target
                + "Type: " + type
                + "IsBaseState: " + isBaseState
    }

}
