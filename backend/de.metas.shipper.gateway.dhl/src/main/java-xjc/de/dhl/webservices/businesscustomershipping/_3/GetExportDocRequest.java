//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.09 at 05:46:45 PM CET 
//


package de.dhl.webservices.businesscustomershipping._3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://dhl.de/webservices/businesscustomershipping/3.0}Version"/&gt;
 *         &lt;element ref="{http://dhl.de/webservice/cisbase}shipmentNumber" maxOccurs="30"/&gt;
 *         &lt;element name="exportDocResponseType" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *               &lt;enumeration value="URL"/&gt;
 *               &lt;enumeration value="B64"/&gt;
 *               &lt;enumeration value="ZPL2"/&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="groupProfileName" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="labelFormat" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="labelFormatRetoure" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="combinedPrinting" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *         &lt;element name="feederSystem" minOccurs="0"&gt;
 *           &lt;simpleType&gt;
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *             &lt;/restriction&gt;
 *           &lt;/simpleType&gt;
 *         &lt;/element&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "version",
    "shipmentNumber",
    "exportDocResponseType",
    "groupProfileName",
    "labelFormat",
    "labelFormatRetoure",
    "combinedPrinting",
    "feederSystem"
})
@XmlRootElement(name = "GetExportDocRequest")
public class GetExportDocRequest {

    @XmlElement(name = "Version", namespace = "http://dhl.de/webservices/businesscustomershipping/3.0", required = true)
    protected Version version;
    @XmlElement(namespace = "http://dhl.de/webservice/cisbase", required = true)
    protected List<String> shipmentNumber;
    protected String exportDocResponseType;
    protected String groupProfileName;
    protected String labelFormat;
    protected String labelFormatRetoure;
    protected String combinedPrinting;
    protected String feederSystem;

    /**
     * The version of the webservice implementation for which the
     * 							requesting client is developed.
     * 
     * @return
     *     possible object is
     *     {@link Version }
     *     
     */
    public Version getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link Version }
     *     
     */
    public void setVersion(Version value) {
        this.version = value;
    }

    /**
     * To request export documents, ShipmentNumber. ShipmentNumber
     * 							is required. This parent element inherits from ShipmentNumberType,
     * 							therefore all following subelements are valid according to schema,
     * 							however the web service accepts shipmentNumber only.Gets the value of the shipmentNumber property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the shipmentNumber property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getShipmentNumber().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getShipmentNumber() {
        if (shipmentNumber == null) {
            shipmentNumber = new ArrayList<String>();
        }
        return this.shipmentNumber;
    }

    /**
     * Gets the value of the exportDocResponseType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExportDocResponseType() {
        return exportDocResponseType;
    }

    /**
     * Sets the value of the exportDocResponseType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExportDocResponseType(String value) {
        this.exportDocResponseType = value;
    }

    /**
     * Gets the value of the groupProfileName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupProfileName() {
        return groupProfileName;
    }

    /**
     * Sets the value of the groupProfileName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupProfileName(String value) {
        this.groupProfileName = value;
    }

    /**
     * Gets the value of the labelFormat property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelFormat() {
        return labelFormat;
    }

    /**
     * Sets the value of the labelFormat property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelFormat(String value) {
        this.labelFormat = value;
    }

    /**
     * Gets the value of the labelFormatRetoure property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLabelFormatRetoure() {
        return labelFormatRetoure;
    }

    /**
     * Sets the value of the labelFormatRetoure property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLabelFormatRetoure(String value) {
        this.labelFormatRetoure = value;
    }

    /**
     * Gets the value of the combinedPrinting property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCombinedPrinting() {
        return combinedPrinting;
    }

    /**
     * Sets the value of the combinedPrinting property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCombinedPrinting(String value) {
        this.combinedPrinting = value;
    }

    /**
     * Gets the value of the feederSystem property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFeederSystem() {
        return feederSystem;
    }

    /**
     * Sets the value of the feederSystem property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFeederSystem(String value) {
        this.feederSystem = value;
    }

}
