//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2023.01.03 at 02:02:16 PM CET 
//


package at.erpel.schemas._1p0.documents.extensions.edifact;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for INVOICListLineItemExtensionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="INVOICListLineItemExtensionType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="CustomsTariffNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/documents/extensions/edifact}ConsumerUnitEAN" minOccurs="0"/&gt;
 *         &lt;element name="ConsumerUnitAmount" type="{http://erpel.at/schemas/1p0/documents/extensions/edifact}UnitType" minOccurs="0"/&gt;
 *         &lt;element name="DeliveredQuantity" type="{http://erpel.at/schemas/1p0/documents/extensions/edifact}UnitType" minOccurs="0"/&gt;
 *         &lt;element name="QuantityInHigherLevelAssortmentUnit" type="{http://erpel.at/schemas/1p0/documents/extensions/edifact}UnitType" minOccurs="0"/&gt;
 *         &lt;element name="ReturnableContainer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="AssortmentUnit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="TypeOfPackaging" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/documents/extensions/edifact}AdditionalChargesAndReductions" minOccurs="0"/&gt;
 *         &lt;element name="ParentLineItemNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UnitGrossPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="UnitNetPrice" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="TotalAmountOfAllowancesAndCharges" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="ConsumerUnit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="InvoiceUnit" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="TaxAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="TaxableAmount" type="{http://www.w3.org/2001/XMLSchema}decimal" minOccurs="0"/&gt;
 *         &lt;element name="OrderedByPartyGLN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AcceptingPartyGLN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ContractNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="THM" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="NonReturnableContainer" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/&gt;
 *         &lt;element name="SpecialConditionCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "INVOICListLineItemExtensionType", propOrder = {
    "customsTariffNumber",
    "consumerUnitEAN",
    "consumerUnitAmount",
    "deliveredQuantity",
    "quantityInHigherLevelAssortmentUnit",
    "returnableContainer",
    "assortmentUnit",
    "typeOfPackaging",
    "additionalChargesAndReductions",
    "parentLineItemNumber",
    "unitGrossPrice",
    "unitNetPrice",
    "totalAmountOfAllowancesAndCharges",
    "consumerUnit",
    "invoiceUnit",
    "taxAmount",
    "taxableAmount",
    "orderedByPartyGLN",
    "acceptingPartyGLN",
    "contractNumber",
    "thm",
    "nonReturnableContainer",
    "specialConditionCode"
})
public class INVOICListLineItemExtensionType {

    @XmlElement(name = "CustomsTariffNumber")
    protected String customsTariffNumber;
    @XmlElement(name = "ConsumerUnitEAN")
    protected String consumerUnitEAN;
    @XmlElement(name = "ConsumerUnitAmount")
    protected UnitType consumerUnitAmount;
    @XmlElement(name = "DeliveredQuantity")
    protected UnitType deliveredQuantity;
    @XmlElement(name = "QuantityInHigherLevelAssortmentUnit")
    protected UnitType quantityInHigherLevelAssortmentUnit;
    @XmlElement(name = "ReturnableContainer")
    protected Boolean returnableContainer;
    @XmlElement(name = "AssortmentUnit")
    protected Boolean assortmentUnit;
    @XmlElement(name = "TypeOfPackaging")
    protected String typeOfPackaging;
    @XmlElement(name = "AdditionalChargesAndReductions")
    protected AdditionalChargesAndReductionsType additionalChargesAndReductions;
    @XmlElement(name = "ParentLineItemNumber")
    protected String parentLineItemNumber;
    @XmlElement(name = "UnitGrossPrice")
    protected BigDecimal unitGrossPrice;
    @XmlElement(name = "UnitNetPrice")
    protected BigDecimal unitNetPrice;
    @XmlElement(name = "TotalAmountOfAllowancesAndCharges")
    protected BigDecimal totalAmountOfAllowancesAndCharges;
    @XmlElement(name = "ConsumerUnit")
    protected Boolean consumerUnit;
    @XmlElement(name = "InvoiceUnit")
    protected Boolean invoiceUnit;
    @XmlElement(name = "TaxAmount")
    protected BigDecimal taxAmount;
    @XmlElement(name = "TaxableAmount")
    protected BigDecimal taxableAmount;
    @XmlElement(name = "OrderedByPartyGLN")
    protected String orderedByPartyGLN;
    @XmlElement(name = "AcceptingPartyGLN")
    protected String acceptingPartyGLN;
    @XmlElement(name = "ContractNumber")
    protected String contractNumber;
    @XmlElement(name = "THM")
    protected Boolean thm;
    @XmlElement(name = "NonReturnableContainer")
    protected Boolean nonReturnableContainer;
    @XmlElement(name = "SpecialConditionCode")
    protected String specialConditionCode;

    /**
     * Gets the value of the customsTariffNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustomsTariffNumber() {
        return customsTariffNumber;
    }

    /**
     * Sets the value of the customsTariffNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustomsTariffNumber(String value) {
        this.customsTariffNumber = value;
    }

    /**
     * The GTIN (EAN) of the consumer unit.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getConsumerUnitEAN() {
        return consumerUnitEAN;
    }

    /**
     * Sets the value of the consumerUnitEAN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setConsumerUnitEAN(String value) {
        this.consumerUnitEAN = value;
    }

    /**
     * Gets the value of the consumerUnitAmount property.
     * 
     * @return
     *     possible object is
     *     {@link UnitType }
     *     
     */
    public UnitType getConsumerUnitAmount() {
        return consumerUnitAmount;
    }

    /**
     * Sets the value of the consumerUnitAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitType }
     *     
     */
    public void setConsumerUnitAmount(UnitType value) {
        this.consumerUnitAmount = value;
    }

    /**
     * Gets the value of the deliveredQuantity property.
     * 
     * @return
     *     possible object is
     *     {@link UnitType }
     *     
     */
    public UnitType getDeliveredQuantity() {
        return deliveredQuantity;
    }

    /**
     * Sets the value of the deliveredQuantity property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitType }
     *     
     */
    public void setDeliveredQuantity(UnitType value) {
        this.deliveredQuantity = value;
    }

    /**
     * Gets the value of the quantityInHigherLevelAssortmentUnit property.
     * 
     * @return
     *     possible object is
     *     {@link UnitType }
     *     
     */
    public UnitType getQuantityInHigherLevelAssortmentUnit() {
        return quantityInHigherLevelAssortmentUnit;
    }

    /**
     * Sets the value of the quantityInHigherLevelAssortmentUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link UnitType }
     *     
     */
    public void setQuantityInHigherLevelAssortmentUnit(UnitType value) {
        this.quantityInHigherLevelAssortmentUnit = value;
    }

    /**
     * Gets the value of the returnableContainer property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isReturnableContainer() {
        return returnableContainer;
    }

    /**
     * Sets the value of the returnableContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnableContainer(Boolean value) {
        this.returnableContainer = value;
    }

    /**
     * Gets the value of the assortmentUnit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isAssortmentUnit() {
        return assortmentUnit;
    }

    /**
     * Sets the value of the assortmentUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setAssortmentUnit(Boolean value) {
        this.assortmentUnit = value;
    }

    /**
     * Gets the value of the typeOfPackaging property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTypeOfPackaging() {
        return typeOfPackaging;
    }

    /**
     * Sets the value of the typeOfPackaging property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTypeOfPackaging(String value) {
        this.typeOfPackaging = value;
    }

    /**
     * Details about additional surcharges and reductions.
     * 
     * @return
     *     possible object is
     *     {@link AdditionalChargesAndReductionsType }
     *     
     */
    public AdditionalChargesAndReductionsType getAdditionalChargesAndReductions() {
        return additionalChargesAndReductions;
    }

    /**
     * Sets the value of the additionalChargesAndReductions property.
     * 
     * @param value
     *     allowed object is
     *     {@link AdditionalChargesAndReductionsType }
     *     
     */
    public void setAdditionalChargesAndReductions(AdditionalChargesAndReductionsType value) {
        this.additionalChargesAndReductions = value;
    }

    /**
     * Gets the value of the parentLineItemNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentLineItemNumber() {
        return parentLineItemNumber;
    }

    /**
     * Sets the value of the parentLineItemNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentLineItemNumber(String value) {
        this.parentLineItemNumber = value;
    }

    /**
     * Gets the value of the unitGrossPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUnitGrossPrice() {
        return unitGrossPrice;
    }

    /**
     * Sets the value of the unitGrossPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUnitGrossPrice(BigDecimal value) {
        this.unitGrossPrice = value;
    }

    /**
     * Gets the value of the unitNetPrice property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUnitNetPrice() {
        return unitNetPrice;
    }

    /**
     * Sets the value of the unitNetPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUnitNetPrice(BigDecimal value) {
        this.unitNetPrice = value;
    }

    /**
     * Gets the value of the totalAmountOfAllowancesAndCharges property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTotalAmountOfAllowancesAndCharges() {
        return totalAmountOfAllowancesAndCharges;
    }

    /**
     * Sets the value of the totalAmountOfAllowancesAndCharges property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTotalAmountOfAllowancesAndCharges(BigDecimal value) {
        this.totalAmountOfAllowancesAndCharges = value;
    }

    /**
     * Gets the value of the consumerUnit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isConsumerUnit() {
        return consumerUnit;
    }

    /**
     * Sets the value of the consumerUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setConsumerUnit(Boolean value) {
        this.consumerUnit = value;
    }

    /**
     * Gets the value of the invoiceUnit property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isInvoiceUnit() {
        return invoiceUnit;
    }

    /**
     * Sets the value of the invoiceUnit property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInvoiceUnit(Boolean value) {
        this.invoiceUnit = value;
    }

    /**
     * Gets the value of the taxAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxAmount() {
        return taxAmount;
    }

    /**
     * Sets the value of the taxAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxAmount(BigDecimal value) {
        this.taxAmount = value;
    }

    /**
     * Gets the value of the taxableAmount property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getTaxableAmount() {
        return taxableAmount;
    }

    /**
     * Sets the value of the taxableAmount property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setTaxableAmount(BigDecimal value) {
        this.taxableAmount = value;
    }

    /**
     * Gets the value of the orderedByPartyGLN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderedByPartyGLN() {
        return orderedByPartyGLN;
    }

    /**
     * Sets the value of the orderedByPartyGLN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderedByPartyGLN(String value) {
        this.orderedByPartyGLN = value;
    }

    /**
     * Gets the value of the acceptingPartyGLN property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAcceptingPartyGLN() {
        return acceptingPartyGLN;
    }

    /**
     * Sets the value of the acceptingPartyGLN property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAcceptingPartyGLN(String value) {
        this.acceptingPartyGLN = value;
    }

    /**
     * Gets the value of the contractNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     * Sets the value of the contractNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractNumber(String value) {
        this.contractNumber = value;
    }

    /**
     * Gets the value of the thm property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isTHM() {
        return thm;
    }

    /**
     * Sets the value of the thm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setTHM(Boolean value) {
        this.thm = value;
    }

    /**
     * Gets the value of the nonReturnableContainer property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isNonReturnableContainer() {
        return nonReturnableContainer;
    }

    /**
     * Sets the value of the nonReturnableContainer property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setNonReturnableContainer(Boolean value) {
        this.nonReturnableContainer = value;
    }

    /**
     * Gets the value of the specialConditionCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSpecialConditionCode() {
        return specialConditionCode;
    }

    /**
     * Sets the value of the specialConditionCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSpecialConditionCode(String value) {
        this.specialConditionCode = value;
    }

}
