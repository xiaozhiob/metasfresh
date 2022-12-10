//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.09 at 05:46:30 PM CET 
//


package de.metas.edi.esb.jaxb.metasfresh;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


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
 *         &lt;element name="Header"&gt;
 *           &lt;complexType&gt;
 *             &lt;complexContent&gt;
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *                 &lt;sequence&gt;
 *                   &lt;element name="SenderID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *                   &lt;element name="InterchangeRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                   &lt;element name="DateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
 *                   &lt;element name="TestIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *                 &lt;/sequence&gt;
 *               &lt;/restriction&gt;
 *             &lt;/complexContent&gt;
 *           &lt;/complexType&gt;
 *         &lt;/element&gt;
 *         &lt;element ref="{}EDI_Imp_C_OLCands" minOccurs="0"/&gt;
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
    "header",
    "ediImpCOLCands"
})
@XmlRootElement(name = "EDI_Message")
public class EDIMessage {

    @XmlElement(name = "Header", required = true)
    protected EDIMessage.Header header;
    @XmlElement(name = "EDI_Imp_C_OLCands")
    protected EDIImpCOLCandsType ediImpCOLCands;

    /**
     * Gets the value of the header property.
     * 
     * @return
     *     possible object is
     *     {@link EDIMessage.Header }
     *     
     */
    public EDIMessage.Header getHeader() {
        return header;
    }

    /**
     * Sets the value of the header property.
     * 
     * @param value
     *     allowed object is
     *     {@link EDIMessage.Header }
     *     
     */
    public void setHeader(EDIMessage.Header value) {
        this.header = value;
    }

    /**
     * Gets the value of the ediImpCOLCands property.
     * 
     * @return
     *     possible object is
     *     {@link EDIImpCOLCandsType }
     *     
     */
    public EDIImpCOLCandsType getEDIImpCOLCands() {
        return ediImpCOLCands;
    }

    /**
     * Sets the value of the ediImpCOLCands property.
     * 
     * @param value
     *     allowed object is
     *     {@link EDIImpCOLCandsType }
     *     
     */
    public void setEDIImpCOLCands(EDIImpCOLCandsType value) {
        this.ediImpCOLCands = value;
    }


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
     *         &lt;element name="SenderID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="RecipientID" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
     *         &lt;element name="InterchangeRef" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
     *         &lt;element name="DateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/&gt;
     *         &lt;element name="TestIndicator" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
        "senderID",
        "recipientID",
        "interchangeRef",
        "dateTime",
        "testIndicator"
    })
    public static class Header {

        @XmlElement(name = "SenderID", required = true)
        protected String senderID;
        @XmlElement(name = "RecipientID", required = true)
        protected String recipientID;
        @XmlElement(name = "InterchangeRef")
        protected String interchangeRef;
        @XmlElement(name = "DateTime")
        @XmlSchemaType(name = "dateTime")
        protected XMLGregorianCalendar dateTime;
        @XmlElement(name = "TestIndicator")
        protected String testIndicator;

        /**
         * Gets the value of the senderID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getSenderID() {
            return senderID;
        }

        /**
         * Sets the value of the senderID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setSenderID(String value) {
            this.senderID = value;
        }

        /**
         * Gets the value of the recipientID property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getRecipientID() {
            return recipientID;
        }

        /**
         * Sets the value of the recipientID property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setRecipientID(String value) {
            this.recipientID = value;
        }

        /**
         * Gets the value of the interchangeRef property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getInterchangeRef() {
            return interchangeRef;
        }

        /**
         * Sets the value of the interchangeRef property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setInterchangeRef(String value) {
            this.interchangeRef = value;
        }

        /**
         * Gets the value of the dateTime property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDateTime() {
            return dateTime;
        }

        /**
         * Sets the value of the dateTime property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDateTime(XMLGregorianCalendar value) {
            this.dateTime = value;
        }

        /**
         * Gets the value of the testIndicator property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getTestIndicator() {
            return testIndicator;
        }

        /**
         * Sets the value of the testIndicator property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setTestIndicator(String value) {
            this.testIndicator = value;
        }

    }

}
