package com.kamilmade.parser

import spock.lang.Shared
import spock.lang.Specification


class XMLStatesParserFilteringNodesTest extends Specification {
    @Shared def parser = new XMLStatesParser();

    def "check if list has been returned / not null"(){
        given:
        def myList = parser.parseGraphNodeXml(parser.readXML("/statestest.xml"))
        when:
        def filteredList = parser.checkAllNodesAndGenerateProperStatesFromTheirBaseStates(myList)
        then:
        filteredList!=null
    }

    def "check if list has any states generated from states with baseStates"(){
        given:
        def myList = parser.parseGraphNodeXml(parser.readXML("/statestest.xml"))
        when:
        def filteredList = parser.checkAllNodesAndGenerateProperStatesFromTheirBaseStates(myList)
        then:
        filteredList!=null
        !filteredList.isEmpty()
    }
}
