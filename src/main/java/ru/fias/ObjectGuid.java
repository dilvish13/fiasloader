package ru.fias;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Объекты ФИАС с AOGuid и AOID
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement(name = "Object")
public class ObjectGuid {

    @XmlAttribute(name = "AOGUID", required = true)
    protected String aoguid;
    @XmlAttribute(name = "AOID", required = true)
    protected String aoid;

    /**
     * Gets the value of the aoguid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
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
    public void setAOGUID(String value) {
        this.aoguid = value;
    }

    /**
     * Gets the value of the aoid property.
     *
     * @return
     *     possible object is
     *     {@link String }
     *
     */
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
    public void setAOID(String value) {
        this.aoid = value;
    }

}
