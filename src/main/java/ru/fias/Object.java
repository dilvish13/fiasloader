package ru.fias;

import javax.annotation.Generated;
import javax.xml.bind.annotation.*;
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;

/**
 * вручную созданный POJO для анмаршализации ТОЛЬКО адресных объектов. Стандартный анмаршаллинг по сгенерерованному по WSDL
 * классу не работает, пытается засосать весь документ
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Object")
//@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
public class Object {

    @XmlAttribute(name = "AOGUID", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String aoguid;
    @XmlAttribute(name = "FORMALNAME", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String formalname;
    @XmlAttribute(name = "REGIONCODE", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String regioncode;
    /*@XmlAttribute(name = "AUTOCODE", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String autocode;
    @XmlAttribute(name = "AREACODE", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String areacode;
    @XmlAttribute(name = "CITYCODE", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String citycode;
    @XmlAttribute(name = "CTARCODE", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String ctarcode;
    @XmlAttribute(name = "PLACECODE", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String placecode;
    @XmlAttribute(name = "PLANCODE", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String plancode;
    @XmlAttribute(name = "STREETCODE")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String streetcode;
    @XmlAttribute(name = "EXTRCODE", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String extrcode;
    @XmlAttribute(name = "SEXTCODE", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String sextcode;*/
    @XmlAttribute(name = "OFFNAME")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String offname;
    /*@XmlAttribute(name = "POSTALCODE")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String postalcode;
    @XmlAttribute(name = "IFNSFL")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String ifnsfl;
    @XmlAttribute(name = "TERRIFNSFL")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String terrifnsfl;
    @XmlAttribute(name = "IFNSUL")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String ifnsul;
    @XmlAttribute(name = "TERRIFNSUL")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String terrifnsul;
    @XmlAttribute(name = "OKATO")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String okato;
    @XmlAttribute(name = "OKTMO")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String oktmo;*/
    @XmlAttribute(name = "UPDATEDATE", required = true)
    @XmlSchemaType(name = "date")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar updatedate;
    @XmlAttribute(name = "SHORTNAME", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String shortname;
    @XmlAttribute(name = "AOLEVEL", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigInteger aolevel;
    @XmlAttribute(name = "PARENTGUID")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String parentguid;
    @XmlAttribute(name = "AOID", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String aoid;
    /*@XmlAttribute(name = "PREVID")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String previd;
    @XmlAttribute(name = "NEXTID")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String nextid;
    @XmlAttribute(name = "CODE")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String code;
    @XmlAttribute(name = "PLAINCODE")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String plaincode;*/
    @XmlAttribute(name = "ACTSTATUS", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigInteger actstatus;
    /*@XmlAttribute(name = "CENTSTATUS", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigInteger centstatus;
    @XmlAttribute(name = "OPERSTATUS", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigInteger operstatus;
    @XmlAttribute(name = "CURRSTATUS", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected BigInteger currstatus;
    @XmlAttribute(name = "STARTDATE", required = true)
    @XmlSchemaType(name = "date")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar startdate;
    @XmlAttribute(name = "ENDDATE", required = true)
    @XmlSchemaType(name = "date")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected XMLGregorianCalendar enddate;
    @XmlAttribute(name = "NORMDOC")
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected String normdoc;
    @XmlAttribute(name = "LIVESTATUS", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected byte livestatus;
    @XmlAttribute(name = "DIVTYPE", required = true)
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    protected int divtype;*/

    /**
     * Gets the value of the aoguid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
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
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAOGUID(String value) {
        this.aoguid = value;
    }

    /**
     * Gets the value of the formalname property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getFORMALNAME() {
        return formalname;
    }

    /**
     * Sets the value of the formalname property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setFORMALNAME(String value) {
        this.formalname = value;
    }

    /**
     * Gets the value of the regioncode property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getREGIONCODE() {
        return regioncode;
    }

    /**
     * Sets the value of the regioncode property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setREGIONCODE(String value) {
        this.regioncode = value;
    }

    /*
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getAUTOCODE() {
        return autocode;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAUTOCODE(String value) {
        this.autocode = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getAREACODE() {
        return areacode;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAREACODE(String value) {
        this.areacode = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getCITYCODE() {
        return citycode;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCITYCODE(String value) {
        this.citycode = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getCTARCODE() {
        return ctarcode;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCTARCODE(String value) {
        this.ctarcode = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPLACECODE() {
        return placecode;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPLACECODE(String value) {
        this.placecode = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPLANCODE() {
        return plancode;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPLANCODE(String value) {
        this.plancode = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getSTREETCODE() {
        return streetcode;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSTREETCODE(String value) {
        this.streetcode = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getEXTRCODE() {
        return extrcode;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setEXTRCODE(String value) {
        this.extrcode = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getSEXTCODE() {
        return sextcode;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSEXTCODE(String value) {
        this.sextcode = value;
    } */

    /**
     * Gets the value of the offname property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getOFFNAME() {
        return offname;
    }

    /**
     * Sets the value of the offname property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setOFFNAME(String value) {
        this.offname = value;
    }

    /*@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPOSTALCODE() {
        return postalcode;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPOSTALCODE(String value) {
        this.postalcode = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getIFNSFL() {
        return ifnsfl;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setIFNSFL(String value) {
        this.ifnsfl = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getTERRIFNSFL() {
        return terrifnsfl;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setTERRIFNSFL(String value) {
        this.terrifnsfl = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getIFNSUL() {
        return ifnsul;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setIFNSUL(String value) {
        this.ifnsul = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getTERRIFNSUL() {
        return terrifnsul;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setTERRIFNSUL(String value) {
        this.terrifnsul = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getOKATO() {
        return okato;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setOKATO(String value) {
        this.okato = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getOKTMO() {
        return oktmo;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setOKTMO(String value) {
        this.oktmo = value;
    } */

    /**
     * Gets the value of the updatedate property.
     *
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getUPDATEDATE() {
        return updatedate;
    }

    /**
     * Sets the value of the updatedate property.
     *
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setUPDATEDATE(XMLGregorianCalendar value) {
        this.updatedate = value;
    }

    /**
     * Gets the value of the shortname property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getSHORTNAME() {
        return shortname;
    }

    /**
     * Sets the value of the shortname property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSHORTNAME(String value) {
        this.shortname = value;
    }

    /**
     * Gets the value of the aolevel property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigInteger getAOLEVEL() {
        return aolevel;
    }

    /**
     * Sets the value of the aolevel property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAOLEVEL(BigInteger value) {
        this.aolevel = value;
    }

    /**
     * Gets the value of the parentguid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPARENTGUID() {
        return parentguid;
    }

    /**
     * Sets the value of the parentguid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPARENTGUID(String value) {
        this.parentguid = value;
    }

    /**
     * Gets the value of the aoid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getAOID() {
        return aoid;
    }

    /**
     * Sets the value of the aoid property.
     *
     * @param value
     *     allowed object is
     *     {@link String }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setAOID(String value) {
        this.aoid = value;
    }

    /*@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPREVID() {
        return previd;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPREVID(String value) {
        this.previd = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getNEXTID() {
        return nextid;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setNEXTID(String value) {
        this.nextid = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getCODE() {
        return code;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCODE(String value) {
        this.code = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getPLAINCODE() {
        return plaincode;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setPLAINCODE(String value) {
        this.plaincode = value;
    } */

    /**
     * Gets the value of the actstatus property.
     *
     * @return
     *     possible object is
     *     {@link BigInteger }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigInteger getACTSTATUS() {
        return actstatus;
    }

    /**
     * Sets the value of the actstatus property.
     *
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *
     */
    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setACTSTATUS(BigInteger value) {
        this.actstatus = value;
    }

    /*@Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigInteger getCENTSTATUS() {
        return centstatus;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCENTSTATUS(BigInteger value) {
        this.centstatus = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigInteger getOPERSTATUS() {
        return operstatus;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setOPERSTATUS(BigInteger value) {
        this.operstatus = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public BigInteger getCURRSTATUS() {
        return currstatus;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setCURRSTATUS(BigInteger value) {
        this.currstatus = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getSTARTDATE() {
        return startdate;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setSTARTDATE(XMLGregorianCalendar value) {
        this.startdate = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public XMLGregorianCalendar getENDDATE() {
        return enddate;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setENDDATE(XMLGregorianCalendar value) {
        this.enddate = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public String getNORMDOC() {
        return normdoc;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setNORMDOC(String value) {
        this.normdoc = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public byte getLIVESTATUS() {
        return livestatus;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setLIVESTATUS(byte value) {
        this.livestatus = value;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public int getDIVTYPE() {
        return divtype;
    }

    @Generated(value = "com.sun.tools.internal.xjc.Driver", date = "2018-08-13T02:29:29+07:00", comments = "JAXB RI v2.2.8-b130911.1802")
    public void setDIVTYPE(int value) {
        this.divtype = value;
    } */

}
