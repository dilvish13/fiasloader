package ru.fias;

import javax.annotation.Generated;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "House")
//@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class House {

    @XmlAttribute(name = "POSTALCODE")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String postalcode;
    @XmlAttribute(name = "HOUSENUM")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String housenum;
    @XmlAttribute(name = "BUILDNUM")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String buildnum;
    @XmlAttribute(name = "STRSTATUS")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigInteger strstatus;
    @XmlAttribute(name = "HOUSEID", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String houseid;
    @XmlAttribute(name = "HOUSEGUID", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String houseguid;
    @XmlAttribute(name = "AOGUID", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String aoguid;

    /**
     * Gets the value of the postalcode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPOSTALCODE() {
        return postalcode;
    }

    /**
     * Sets the value of the postalcode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPOSTALCODE(String value) {
        this.postalcode = value;
    }

    /**
     * Gets the value of the housenum property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getHOUSENUM() {
        return housenum;
    }

    /**
     * Sets the value of the housenum property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setHOUSENUM(String value) {
        this.housenum = value;
    }

    /**
     * Gets the value of the buildnum property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getBUILDNUM() {
        return buildnum;
    }

    /**
     * Sets the value of the buildnum property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setBUILDNUM(String value) {
        this.buildnum = value;
    }

    /**
     * Gets the value of the strstatus property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigInteger getSTRSTATUS() {
        return strstatus;
    }

    /**
     * Sets the value of the strstatus property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSTRSTATUS(BigInteger value) {
        this.strstatus = value;
    }

    /**
     * Gets the value of the houseid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getHOUSEID() {
        return houseid;
    }

    /**
     * Sets the value of the houseid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setHOUSEID(String value) {
        this.houseid = value;
    }

    /**
     * Gets the value of the houseguid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getHOUSEGUID() {
        return houseguid;
    }

    /**
     * Sets the value of the houseguid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setHOUSEGUID(String value) {
        this.houseguid = value;
    }

    /**
     * Gets the value of the aoguid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getAOGUID() {
        return aoguid;
    }

    /**
     * Sets the value of the aoguid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:56+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAOGUID(String value) {
        this.aoguid = value;
    }

}
