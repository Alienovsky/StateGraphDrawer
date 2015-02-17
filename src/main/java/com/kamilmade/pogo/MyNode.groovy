package com.kamilmade.pogo

import groovy.transform.ToString

@ToString
class MyNode {
    String source;
    String target;
    String action;
    String type;

    public String toString(){
        return "[" + source + " -- " + action + " --> " + target + " ]";
    }
}
