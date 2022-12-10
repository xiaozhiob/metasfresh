//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.09 at 05:38:20 PM CET 
//


package de.metas.vertical.pharma.vendor.gateway.msv3.schema.v2;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RetoureGrund.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="RetoureGrund"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Bestellfehler"/&gt;
 *     &lt;enumeration value="BerechnetNichtGeliefert"/&gt;
 *     &lt;enumeration value="Beschaedigt"/&gt;
 *     &lt;enumeration value="VerfallZuKurz"/&gt;
 *     &lt;enumeration value="ZuvielGeliefert"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "RetoureGrund")
@XmlEnum
public enum RetoureGrund {

    @XmlEnumValue("Bestellfehler")
    BESTELLFEHLER("Bestellfehler"),
    @XmlEnumValue("BerechnetNichtGeliefert")
    BERECHNET_NICHT_GELIEFERT("BerechnetNichtGeliefert"),
    @XmlEnumValue("Beschaedigt")
    BESCHAEDIGT("Beschaedigt"),
    @XmlEnumValue("VerfallZuKurz")
    VERFALL_ZU_KURZ("VerfallZuKurz"),
    @XmlEnumValue("ZuvielGeliefert")
    ZUVIEL_GELIEFERT("ZuvielGeliefert");
    private final String value;

    RetoureGrund(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static RetoureGrund fromValue(String v) {
        for (RetoureGrund c: RetoureGrund.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
