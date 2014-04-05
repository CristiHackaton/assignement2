package com.model.sell;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for sellList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="sellList">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sell" type="{}sell"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "sellList", propOrder = {
    "sell"
})
public class SellList {

    @XmlElement(required = true)
    protected Sell sell;

    /**
     * Gets the value of the sell property.
     * 
     * @return
     *     possible object is
     *     {@link Sell }
     *     
     */
    public Sell getSell() {
        return sell;
    }

    /**
     * Sets the value of the sell property.
     * 
     * @param value
     *     allowed object is
     *     {@link Sell }
     *     
     */
    public void setSell(Sell value) {
        this.sell = value;
    }

}
