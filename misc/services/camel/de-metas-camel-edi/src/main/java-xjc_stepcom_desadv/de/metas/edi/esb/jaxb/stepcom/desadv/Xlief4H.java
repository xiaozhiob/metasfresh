//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.03 at 02:02:15 PM CET 
//


package de.metas.edi.esb.jaxb.stepcom.desadv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Xlief4h complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Xlief4h"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="HEADER" type="{}HEADER_Xlief"/&gt;
 *         &lt;element name="TRAILR" type="{}TRAILR"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Xlief4h", propOrder = {
    "header",
    "trailr"
})
public class Xlief4H {

    @XmlElement(name = "HEADER", required = true)
    protected HEADERXlief header;
    @XmlElement(name = "TRAILR", required = true)
    protected TRAILR trailr;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link HEADERXlief }
     *     
     */
    public HEADERXlief getHEADER() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link HEADERXlief }
     *     
     */
    public void setHEADER(HEADERXlief value) {
        this.header = value;
    }

    /**
     * Gets the value of the trailr property.
     * 
     * @return
     *     possible object is
     *     {@link TRAILR }
     *     
     */
    public TRAILR getTRAILR() {
        return trailr;
    }

    /**
     * Sets the value of the trailr property.
     * 
     * @param value
     *     allowed object is
     *     {@link TRAILR }
     *     
     */
    public void setTRAILR(TRAILR value) {
        this.trailr = value;
    }

}
