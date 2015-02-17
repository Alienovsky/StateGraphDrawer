package com.kamilmade.pogo

import groovy.transform.ToString

@ToString
abstract class State {
    def String name;
    List<Transition> transtions = [];
}
