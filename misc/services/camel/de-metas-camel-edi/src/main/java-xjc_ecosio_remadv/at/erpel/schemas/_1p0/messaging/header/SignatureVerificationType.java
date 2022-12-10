//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.09 at 05:19:20 PM CET 
//


package at.erpel.schemas._1p0.messaging.header;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SignatureVerificationType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SignatureVerificationType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/messaging/header}SignatureVerificationResult" minOccurs="0"/&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/messaging/header}SignatureVerificationOmittedErrorCode" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *       &lt;attribute ref="{http://erpel.at/schemas/1p0/messaging/header}SignatureVerified use="required""/&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SignatureVerificationType", propOrder = {
    "signatureVerificationResult",
    "signatureVerificationOmittedErrorCode"
})
public class SignatureVerificationType {

    @XmlElement(name = "SignatureVerificationResult")
    protected SignatureVerificationResultType signatureVerificationResult;
    @XmlElement(name = "SignatureVerificationOmittedErrorCode")
    protected String signatureVerificationOmittedErrorCode;
    @XmlAttribute(name = "SignatureVerified", namespace = "http://erpel.at/schemas/1p0/messaging/header", required = true)
    protected boolean signatureVerified;

    /**
     * Gets the value of the signatureVerificationResult property.
     * 
     * @return
     *     possible object is
     *     {@link SignatureVerificationResultType }
     *     
     */
    public SignatureVerificationResultType getSignatureVerificationResult() {
        return signatureVerificationResult;
    }

    /**
     * Sets the value of the signatureVerificationResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link SignatureVerificationResultType }
     *     
     */
    public void setSignatureVerificationResult(SignatureVerificationResultType value) {
        this.signatureVerificationResult = value;
    }

    /**
     * Gets the value of the signatureVerificationOmittedErrorCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSignatureVerificationOmittedErrorCode() {
        return signatureVerificationOmittedErrorCode;
    }

    /**
     * Sets the value of the signatureVerificationOmittedErrorCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSignatureVerificationOmittedErrorCode(String value) {
        this.signatureVerificationOmittedErrorCode = value;
    }

    /**
     * Gets the value of the signatureVerified property.
     * 
     */
    public boolean isSignatureVerified() {
        return signatureVerified;
    }

    /**
     * Sets the value of the signatureVerified property.
     * 
     */
    public void setSignatureVerified(boolean value) {
        this.signatureVerified = value;
    }

}
