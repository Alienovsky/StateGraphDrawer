package com.kamilmade.parser

import com.kamilmade.parser2.obsolete.XMLStatesParser
import spock.lang.Shared
import spock.lang.Specification

/**
 * Created by Alien on 2014-11-11.
 */
class XMLStatesParserTest extends Specification {
    @Shared def parser = new XMLStatesParser();

  /*  def "give list of states after parsing"(){
        setup:
        GraphNode quoteRequested = new GraphNode();
        quoteRequested.setSource(null);
        quoteRequested.setTarget("Pricing Done");
        quoteRequested.setType("suit");
        when:
        def myList = parser.parseGraphNodeXml(parser.readXML("/statestest.xml"))
        then:
        myList!=null;
        !myList.isEmpty();
        myList.contains(quoteRequested);

    }*/
}
