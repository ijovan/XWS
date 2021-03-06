//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.5.1 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.06.26 at 05:02:02 PM CEST 
//


package xml.banka;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import sessionbeans.common.Identifiable;
import xml.mt102.Placanje;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="oznakaBanke" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="obracunskiRacun" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="swiftKod" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="placanja" type="{http://xml/mt102}Placanje" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *       &lt;attribute name="id" type="{http://www.w3.org/2001/XMLSchema}long" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "oznakaBanke",
    "obracunskiRacun",
    "swiftKod",
    "placanja"
})
@XmlRootElement(name = "Banka")
public class Banka extends Identifiable {

    @XmlElement(required = true)
    protected String oznakaBanke;
    @XmlElement(required = true)
    protected String obracunskiRacun;
    @XmlElement(required = true)
    protected String swiftKod;
    @XmlElement(required = true)
    protected List<Placanje> placanja;
    @XmlAttribute(name = "id")
    protected Long id;

    public Banka() {
		super();
	}

	public Banka(String oznakaBanke, String obracunskiRacun, String swiftKod) {
		super();
		this.oznakaBanke = oznakaBanke;
		this.obracunskiRacun = obracunskiRacun;
		this.swiftKod = swiftKod;
	}

	/**
     * Gets the value of the oznakaBanke property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOznakaBanke() {
        return oznakaBanke;
    }

    /**
     * Sets the value of the oznakaBanke property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOznakaBanke(String value) {
        this.oznakaBanke = value;
    }

    /**
     * Gets the value of the obracunskiRacun property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObracunskiRacun() {
        return obracunskiRacun;
    }

    /**
     * Sets the value of the obracunskiRacun property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObracunskiRacun(String value) {
        this.obracunskiRacun = value;
    }

    /**
     * Gets the value of the swiftKod property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSwiftKod() {
        return swiftKod;
    }

    /**
     * Sets the value of the swiftKod property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSwiftKod(String value) {
        this.swiftKod = value;
    }

    /**
     * Gets the value of the placanja property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the placanja property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPlacanja().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Placanje }
     * 
     * 
     */
    public List<Placanje> getPlacanja() {
        if (placanja == null) {
            placanja = new ArrayList<Placanje>();
        }
        return this.placanja;
    }

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setId(Long value) {
        this.id = value;
    }

}
