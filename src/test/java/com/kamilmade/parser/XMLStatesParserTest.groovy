package com.kamilmade.parser

import com.kamilmade.pogo.GraphNode
import org.spockframework.util.Assert
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by Alien on 2014-11-11.
 */
class XMLStatesParserTest extends Specification {
    @Shared def parser = new XMLStatesParser();

   def "give list of states after parsing"(){
        when:
        def myList = parser.parseGraphNodeXml(parser.readXML("/statestest.xml"))

        then:
        myList!=null;
        !myList.isEmpty();
    }

    def "give list of states after parsing but 1st element on list should be quote requested state"(){
        setup:
        GraphNode expectedState = new GraphNode();
        expectedState.setIsBaseState(false)
        expectedState.setTarget("Pricing Done")
        expectedState.setType("suit")
        expectedState.setSource("Quote Requested")

        when:
        def myList = parser.parseGraphNodeXml(parser.readXML("/statestest.xml"))
        GraphNode actualState = findExpectedStateOnParsedStateList(myList, expectedState.getSource(), expectedState.getTarget())

        then:
        myList!=null
        !myList.isEmpty()
        Assert.notNull(actualState,"could not find actual base state")
        actualState.getSource().equals(expectedState.getSource())
        actualState.isBaseState.equals(expectedState.isBaseState)
        actualState.getTarget().equals(expectedState.getTarget())
        actualState.getType().equals(expectedState.getType())
    }

    def "give list with states and 2nd element on it should be basestate"(){
        setup:
        GraphNode expectedBaseState = new GraphNode();
        expectedBaseState.setIsBaseState(true)
        expectedBaseState.setTarget("Cancelled")
        expectedBaseState.setType("suit")
        expectedBaseState.setSource("CancellableBaseState")

        when:
        def myList = parser.parseGraphNodeXml(parser.readXML("/statestest.xml"))
        GraphNode actualBaseState = findExpectedStateOnParsedStateList(myList, expectedBaseState.getSource(), expectedBaseState.getTarget())

        then:
        myList!=null
        !myList.isEmpty()
        Assert.notNull(actualBaseState,"could not find actual base state")
        actualBaseState.getSource().equals(expectedBaseState.getSource())
        actualBaseState.isBaseState.equals(expectedBaseState.isBaseState)
        actualBaseState.getTarget().equals(expectedBaseState.getTarget())
        actualBaseState.getType().equals(expectedBaseState.getType())
    }

    def "give list with states and 2nd element on it should be pricing done state with some basestates"(){
        setup:
        GraphNode expectedState = new GraphNode();
        expectedState.setIsBaseState(false)
        expectedState.setTarget("Deal Approved")
        expectedState.setType("suit")
        expectedState.setSource("Pricing Done")

        when:
        def myList = parser.parseGraphNodeXml(parser.readXML("/statestest.xml"))
        GraphNode actualBaseState = findExpectedStateOnParsedStateList(myList, expectedState.getSource(), expectedState.getTarget())

        then:
        myList!=null
        !myList.isEmpty()
        Assert.notNull(actualBaseState,"could not find actual base state")
        actualBaseState.getSource().equals(expectedState.getSource())
        actualBaseState.isBaseState.equals(expectedState.isBaseState)
        actualBaseState.getTarget().equals(expectedState.getTarget())
        actualBaseState.getType().equals(expectedState.getType())
    }

    def "check if pricing done state has cancel action from cancellable base state"(){
        setup:
        GraphNode expectedState = new GraphNode();
        expectedState.setIsBaseState(false)
        expectedState.setTarget("Cancelled")
        expectedState.setType("suit")
        expectedState.setSource("Pricing Done")

        when:
        def myList = parser.parseGraphNodeXml(parser.readXML("/statestest.xml"))
        GraphNode actualBaseState = findExpectedStateOnParsedStateList(myList, expectedState.getSource(), expectedState.getTarget());

        then:
        Assert.notNull(actualBaseState,"could not find actual base state")
        actualBaseState.getSource().equals(expectedState.getSource())
        actualBaseState.isBaseState.equals(expectedState.isBaseState)
        actualBaseState.getTarget().equals(expectedState.getTarget())
        actualBaseState.getType().equals(expectedState.getType())
    }

    private GraphNode findExpectedStateOnParsedStateList(List<GraphNode> myList, String source, String target){
        return  myList.find{element -> ((GraphNode) element).getSource().equals(source) && ((GraphNode) element).getTarget().equals(target)}
    }


}
