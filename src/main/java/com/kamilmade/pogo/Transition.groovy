package com.kamilmade.pogo

import groovy.transform.ToString

@ToString
class Transition {
    String target;
    String action;
    String type;

    public String toString(){
        return "[ -- " + action + " --> " + target + " ]";
    }
}
