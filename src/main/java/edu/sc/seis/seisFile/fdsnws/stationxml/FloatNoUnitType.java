package edu.sc.seis.seisFile.fdsnws.stationxml;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;

import edu.sc.seis.seisFile.fdsnws.StaxUtil;
import edu.sc.seis.seisFile.fdsnws.stationxml.StationXMLException;
import edu.sc.seis.seisFile.fdsnws.stationxml.StationXMLTagNames;

public class FloatNoUnitType {

    /** for subclasses */
    FloatNoUnitType(String tagName) throws XMLStreamException, StationXMLException {
        this.tagName = tagName;
    }

    public FloatNoUnitType(XMLEventReader reader, String tagName) throws XMLStreamException, StationXMLException {
        this(tagName);
        StartElement startE = StaxUtil.expectStartElement(tagName, reader);
        parseAttributes(startE);
        parseValue(reader);
    }

    void parseAttributes(StartElement startE) throws StationXMLException {
        String plusErrorStr = StaxUtil.pullAttributeIfExists(startE, StationXMLTagNames.PLUSERROR);
        String minusErrorStr = StaxUtil.pullAttributeIfExists(startE, StationXMLTagNames.MINUSERROR);
        if (plusErrorStr != null) {
            plusError = Float.parseFloat(plusErrorStr);
        }
        if (minusErrorStr != null) {
            minusError = Float.parseFloat(minusErrorStr);
        }
    }

    void parseValue(final XMLEventReader reader) throws StationXMLException, XMLStreamException {
        value = Float.parseFloat(StaxUtil.pullContiguousText(reader));
    }

    public FloatNoUnitType(float coefficient, Float plusError, Float minusError, String tagName) throws StationXMLException, XMLStreamException {
        this(tagName);
        this.value = coefficient;
        this.plusError = plusError;
        this.minusError = minusError;
    }

    public String getTagName() {
        return tagName;
    }

    public float getValue() {
        return value;
    }

    public Float getPlusError() {
        return plusError;
    }

    public Float getMinusError() {
        return minusError;
    }

    public boolean hasPlusError() {
        return plusError != null;
    }

    public boolean hasMinusError() {
        return minusError != null;
    }

    String tagName;

    float value;

    Float plusError, minusError;
}
