//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.3.0 
// See <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2022.12.09 at 05:38:01 PM CET 
//


package de.metas.vertical.pharma.vendor.gateway.msv3.schema.v1;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for VerfuegbarkeitRueckmeldungTyp.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="VerfuegbarkeitRueckmeldungTyp"&gt;
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string"&gt;
 *     &lt;enumeration value="Normal"/&gt;
 *     &lt;enumeration value="Verbund"/&gt;
 *     &lt;enumeration value="Nachlieferung"/&gt;
 *     &lt;enumeration value="Dispo"/&gt;
 *     &lt;enumeration value="NichtLieferbar"/&gt;
 *   &lt;/restriction&gt;
 * &lt;/simpleType&gt;
 * </pre>
 * 
 */
@XmlType(name = "VerfuegbarkeitRueckmeldungTyp")
@XmlEnum
public enum VerfuegbarkeitRueckmeldungTyp {

    @XmlEnumValue("Normal")
    NORMAL("Normal"),
    @XmlEnumValue("Verbund")
    VERBUND("Verbund"),
    @XmlEnumValue("Nachlieferung")
    NACHLIEFERUNG("Nachlieferung"),
    @XmlEnumValue("Dispo")
    DISPO("Dispo"),
    @XmlEnumValue("NichtLieferbar")
    NICHT_LIEFERBAR("NichtLieferbar");
    private final String value;

    VerfuegbarkeitRueckmeldungTyp(String v) {
        value = v;
    }

    public String value() {
        return value;
    }

    public static VerfuegbarkeitRueckmeldungTyp fromValue(String v) {
        for (VerfuegbarkeitRueckmeldungTyp c: VerfuegbarkeitRueckmeldungTyp.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

}
