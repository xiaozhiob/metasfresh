//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.09 at 05:38:20 PM CET 
//


package de.metas.vertical.pharma.vendor.gateway.msv3.schema.v2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for VertragsdatenHauptbestellzeit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VertragsdatenHauptbestellzeit"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Tag" type="{urn:msv3:v2}VertragsdatenTag"/&gt;
 *         &lt;element name="Zeitpunkt" type="{urn:msv3:v2}VertragsdatenZeit"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VertragsdatenHauptbestellzeit", propOrder = {
    "tag",
    "zeitpunkt"
})
public class VertragsdatenHauptbestellzeit {

    @XmlElement(name = "Tag", required = true)
    @XmlSchemaType(name = "string")
    protected VertragsdatenTag tag;
    @XmlElement(name = "Zeitpunkt", required = true)
    @XmlSchemaType(name = "time")
    protected XMLGregorianCalendar zeitpunkt;

    /**
     * Gets the value of the tag property.
     * 
     * @return
     *     possible object is
     *     {@link VertragsdatenTag }
     *     
     */
    public VertragsdatenTag getTag() {
        return tag;
    }

    /**
     * Sets the value of the tag property.
     * 
     * @param value
     *     allowed object is
     *     {@link VertragsdatenTag }
     *     
     */
    public void setTag(VertragsdatenTag value) {
        this.tag = value;
    }

    /**
     * Gets the value of the zeitpunkt property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getZeitpunkt() {
        return zeitpunkt;
    }

    /**
     * Sets the value of the zeitpunkt property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setZeitpunkt(XMLGregorianCalendar value) {
        this.zeitpunkt = value;
    }

}
