//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 08:47:18 PM EET 
//


package de.metas.edi.esb.jaxb.stepcom.desadv;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for DRFAD1 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DRFAD1"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="DOCUMENTID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="LINENUMBER" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="ADDRESSQUAL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="REFERENCEQUAL" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="REFERENCE" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="REFERENCEDATE1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="REFERENCEDATE2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DRFAD1", propOrder = {
    "documentid",
    "linenumber",
    "addressqual",
    "referencequal",
    "reference",
    "referencedate1",
    "referencedate2"
})
public class DRFAD1 {

    @XmlElement(name = "DOCUMENTID", required = true)
    protected String documentid;
    @XmlElement(name = "LINENUMBER", required = true)
    protected String linenumber;
    @XmlElement(name = "ADDRESSQUAL", required = true)
    protected String addressqual;
    @XmlElement(name = "REFERENCEQUAL", required = true)
    protected String referencequal;
    @XmlElement(name = "REFERENCE")
    protected String reference;
    @XmlElement(name = "REFERENCEDATE1")
    protected String referencedate1;
    @XmlElement(name = "REFERENCEDATE2")
    protected String referencedate2;

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
     * Gets the value of the linenumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLINENUMBER() {
        return linenumber;
    }

    /**
     * Sets the value of the linenumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLINENUMBER(String value) {
        this.linenumber = value;
    }

    /**
     * Gets the value of the addressqual property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getADDRESSQUAL() {
        return addressqual;
    }

    /**
     * Sets the value of the addressqual property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setADDRESSQUAL(String value) {
        this.addressqual = value;
    }

    /**
     * Gets the value of the referencequal property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFERENCEQUAL() {
        return referencequal;
    }

    /**
     * Sets the value of the referencequal property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFERENCEQUAL(String value) {
        this.referencequal = value;
    }

    /**
     * Gets the value of the reference property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFERENCE() {
        return reference;
    }

    /**
     * Sets the value of the reference property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFERENCE(String value) {
        this.reference = value;
    }

    /**
     * Gets the value of the referencedate1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFERENCEDATE1() {
        return referencedate1;
    }

    /**
     * Sets the value of the referencedate1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFERENCEDATE1(String value) {
        this.referencedate1 = value;
    }

    /**
     * Gets the value of the referencedate2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getREFERENCEDATE2() {
        return referencedate2;
    }

    /**
     * Sets the value of the referencedate2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setREFERENCEDATE2(String value) {
        this.referencedate2 = value;
    }

}
