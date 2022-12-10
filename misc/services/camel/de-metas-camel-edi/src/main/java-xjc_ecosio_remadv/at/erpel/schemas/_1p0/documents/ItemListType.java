//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.09 at 05:19:20 PM CET 
//


package at.erpel.schemas._1p0.documents;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>Java class for ItemListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ItemListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/documents}HeaderDescription" minOccurs="0"/&gt;
 *         &lt;choice&gt;
 *           &lt;element ref="{http://erpel.at/schemas/1p0/documents}ListLineItem" maxOccurs="unbounded"/&gt;
 *           &lt;element ref="{http://erpel.at/schemas/1p0/documents}DeliveryListLineItem" maxOccurs="unbounded"/&gt;
 *           &lt;element ref="{http://erpel.at/schemas/1p0/documents}ForecastListLineItem" maxOccurs="unbounded"/&gt;
 *         &lt;/choice&gt;
 *         &lt;element ref="{http://erpel.at/schemas/1p0/documents}FooterDescription" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ItemListType", propOrder = {
    "headerDescription",
    "listLineItem",
    "deliveryListLineItem",
    "forecastListLineItem",
    "footerDescription"
})
public class ItemListType {

    @XmlElement(name = "HeaderDescription")
    protected String headerDescription;
    @XmlElement(name = "ListLineItem")
    protected List<ListLineItemType> listLineItem;
    @XmlElement(name = "DeliveryListLineItem")
    protected List<DeliveryListLineItemType> deliveryListLineItem;
    @XmlElement(name = "ForecastListLineItem")
    protected List<ForecastListLineItemType> forecastListLineItem;
    @XmlElement(name = "FooterDescription")
    protected String footerDescription;

    /**
     * Free-text description preceding a list line item group.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHeaderDescription() {
        return headerDescription;
    }

    /**
     * Sets the value of the headerDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHeaderDescription(String value) {
        this.headerDescription = value;
    }

    /**
     * Represent a certain list line item.Gets the value of the listLineItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listLineItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListLineItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ListLineItemType }
     * 
     * 
     */
    public List<ListLineItemType> getListLineItem() {
        if (listLineItem == null) {
            listLineItem = new ArrayList<ListLineItemType>();
        }
        return this.listLineItem;
    }

    /**
     * Represents a list line item used in a despatch advice.Gets the value of the deliveryListLineItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the deliveryListLineItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDeliveryListLineItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DeliveryListLineItemType }
     * 
     * 
     */
    public List<DeliveryListLineItemType> getDeliveryListLineItem() {
        if (deliveryListLineItem == null) {
            deliveryListLineItem = new ArrayList<DeliveryListLineItemType>();
        }
        return this.deliveryListLineItem;
    }

    /**
     * Represents a list line item used in a forecast or JIT schedule.Gets the value of the forecastListLineItem property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the forecastListLineItem property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getForecastListLineItem().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ForecastListLineItemType }
     * 
     * 
     */
    public List<ForecastListLineItemType> getForecastListLineItem() {
        if (forecastListLineItem == null) {
            forecastListLineItem = new ArrayList<ForecastListLineItemType>();
        }
        return this.forecastListLineItem;
    }

    /**
     * A free-text description suceeding a list line item group.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFooterDescription() {
        return footerDescription;
    }

    /**
     * Sets the value of the footerDescription property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFooterDescription(String value) {
        this.footerDescription = value;
    }

}
