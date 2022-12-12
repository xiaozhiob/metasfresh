//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.12 at 08:47:19 PM EET 
//


package at.erpel.schemas._1p0.documents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Transport transit information.
 * 
 * <p>Java class for TransportDetailType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TransportDetailType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TransportStageCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TransportModeCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TransportMeansCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TransportMeansIdentification" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CarrierIdentifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TransportDetailType", propOrder = {
    "transportStageCode",
    "transportModeCode",
    "transportMeansCode",
    "transportMeansIdentification",
    "carrierIdentifier"
})
public class TransportDetailType {

    @XmlElement(name = "TransportStageCode")
    protected String transportStageCode;
    @XmlElement(name = "TransportModeCode")
    protected String transportModeCode;
    @XmlElement(name = "TransportMeansCode")
    protected String transportMeansCode;
    @XmlElement(name = "TransportMeansIdentification")
    protected String transportMeansIdentification;
    @XmlElement(name = "CarrierIdentifier")
    protected String carrierIdentifier;

    /**
     * Gets the value of the transportStageCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportStageCode() {
        return transportStageCode;
    }

    /**
     * Sets the value of the transportStageCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportStageCode(String value) {
        this.transportStageCode = value;
    }

    /**
     * Gets the value of the transportModeCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportModeCode() {
        return transportModeCode;
    }

    /**
     * Sets the value of the transportModeCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportModeCode(String value) {
        this.transportModeCode = value;
    }

    /**
     * Gets the value of the transportMeansCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportMeansCode() {
        return transportMeansCode;
    }

    /**
     * Sets the value of the transportMeansCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportMeansCode(String value) {
        this.transportMeansCode = value;
    }

    /**
     * Gets the value of the transportMeansIdentification property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTransportMeansIdentification() {
        return transportMeansIdentification;
    }

    /**
     * Sets the value of the transportMeansIdentification property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTransportMeansIdentification(String value) {
        this.transportMeansIdentification = value;
    }

    /**
     * Gets the value of the carrierIdentifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCarrierIdentifier() {
        return carrierIdentifier;
    }

    /**
     * Sets the value of the carrierIdentifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCarrierIdentifier(String value) {
        this.carrierIdentifier = value;
    }

}
