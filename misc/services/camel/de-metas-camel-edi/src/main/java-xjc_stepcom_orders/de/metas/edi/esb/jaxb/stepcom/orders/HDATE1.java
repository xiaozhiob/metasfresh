//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.03 at 02:02:15 PM CET 
//


package de.metas.edi.esb.jaxb.stepcom.orders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for HDATE1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="HDATE1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DOCUMENTID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DATEQUAL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DATEFROM" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="DATETO" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DAYS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "HDATE1", propOrder = {
    "documentid",
    "datequal",
    "datefrom",
    "dateto",
    "days"
})
public class HDATE1 {

    @XmlElement(name = "DOCUMENTID", required = true)
    protected String documentid;
    @XmlElement(name = "DATEQUAL", required = true)
    protected String datequal;
    @XmlElement(name = "DATEFROM", required = true)
    protected String datefrom;
    @XmlElement(name = "DATETO")
    protected String dateto;
    @XmlElement(name = "DAYS")
    protected String days;

    /**
     * Gets the value of the documentid property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDOCUMENTID() {
        return documentid;
    }

    /**
     * Sets the value of the documentid property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDOCUMENTID(String value) {
        this.documentid = value;
    }

    /**
     * Gets the value of the datequal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDATEQUAL() {
        return datequal;
    }

    /**
     * Sets the value of the datequal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDATEQUAL(String value) {
        this.datequal = value;
    }

    /**
     * Gets the value of the datefrom property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDATEFROM() {
        return datefrom;
    }

    /**
     * Sets the value of the datefrom property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDATEFROM(String value) {
        this.datefrom = value;
    }

    /**
     * Gets the value of the dateto property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDATETO() {
        return dateto;
    }

    /**
     * Sets the value of the dateto property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDATETO(String value) {
        this.dateto = value;
    }

    /**
     * Gets the value of the days property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDAYS() {
        return days;
    }

    /**
     * Sets the value of the days property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDAYS(String value) {
        this.days = value;
    }

}
