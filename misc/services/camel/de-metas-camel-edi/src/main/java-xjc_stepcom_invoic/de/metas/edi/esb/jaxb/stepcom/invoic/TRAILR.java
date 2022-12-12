//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 08:47:18 PM EET 
//


package de.metas.edi.esb.jaxb.stepcom.invoic;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for TRAILR complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TRAILR"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DOCUMENTID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CONTROLQUAL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="CONTROLVALUE" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="MEASUREMENTUNIT" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TAMOU1" type="{}TAMOU1" maxOccurs="5"/&gt;
 *         &lt;element name="TTAXI1" type="{}TTAXI1" maxOccurs="5"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRAILR", propOrder = {
    "documentid",
    "controlqual",
    "controlvalue",
    "measurementunit",
    "tamou1",
    "ttaxi1"
})
public class TRAILR {

    @XmlElement(name = "DOCUMENTID", required = true)
    protected String documentid;
    @XmlElement(name = "CONTROLQUAL", required = true)
    protected String controlqual;
    @XmlElement(name = "CONTROLVALUE", required = true)
    protected String controlvalue;
    @XmlElement(name = "MEASUREMENTUNIT")
    protected String measurementunit;
    @XmlElement(name = "TAMOU1", required = true)
    protected List<TAMOU1> tamou1;
    @XmlElement(name = "TTAXI1", required = true)
    protected List<TTAXI1> ttaxi1;

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
     * Gets the value of the controlqual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONTROLQUAL() {
        return controlqual;
    }

    /**
     * Sets the value of the controlqual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONTROLQUAL(String value) {
        this.controlqual = value;
    }

    /**
     * Gets the value of the controlvalue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCONTROLVALUE() {
        return controlvalue;
    }

    /**
     * Sets the value of the controlvalue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCONTROLVALUE(String value) {
        this.controlvalue = value;
    }

    /**
     * Gets the value of the measurementunit property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMEASUREMENTUNIT() {
        return measurementunit;
    }

    /**
     * Sets the value of the measurementunit property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMEASUREMENTUNIT(String value) {
        this.measurementunit = value;
    }

    /**
     * Gets the value of the tamou1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the tamou1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTAMOU1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TAMOU1 }
     * 
     * 
     */
    public List<TAMOU1> getTAMOU1() {
        if (tamou1 == null) {
            tamou1 = new ArrayList<TAMOU1>();
        }
        return this.tamou1;
    }

    /**
     * Gets the value of the ttaxi1 property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the ttaxi1 property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getTTAXI1().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TTAXI1 }
     * 
     * 
     */
    public List<TTAXI1> getTTAXI1() {
        if (ttaxi1 == null) {
            ttaxi1 = new ArrayList<TTAXI1>();
        }
        return this.ttaxi1;
    }

}
