package ru.fias;

import java.math.BigInteger;
import javax.annotation.Generated;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "AddressObjectType")
public class Socr {

    @XmlAttribute(name = "LEVEL", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigInteger socrLevel;
    @XmlAttribute(name = "SOCRNAME", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String name;
    @XmlAttribute(name = "SCNAME", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String code;
    @XmlAttribute(name = "KOD_T_ST")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String kodTSt;

    /**
     * Gets the value of the level property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigInteger getLEVEL() {
        return socrLevel;
    }

    /**
     * Sets the value of the level property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setLEVEL(BigInteger value) {
        this.socrLevel = value;
    }

    /**
     * Gets the value of the SOCRNAME property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getSOCRNAME() {
        return name;
    }

    /**
     * Sets the value of the SOCRNAME property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSOCRNAME(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the SCNAME property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getSCNAME() {
        return code;
    }

    /**
     * Sets the value of the SCNAME property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSCNAME(String value) {
        this.code = value;
    }

    /**
     * Gets the value of the KOD_T_ST property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getKOD_T_ST() {
        return kodTSt;
    }

    /**
     * Sets the value of the KOD_T_ST property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setKOD_T_ST(String value) {
        this.kodTSt = value;
    }

}
