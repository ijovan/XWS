
package xml.mt103;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;
import xml.globals.TFirma;


/**
 * <p>Java class for TMT103 complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TMT103">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="50"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SWIFT_Duznika" type="{http://xml/globals}TSwiftCode"/>
 *         &lt;element name="ObrRacunBankeDuznika" type="{http://xml/globals}TOznakaRacuna"/>
 *         &lt;element name="SWIFT_Poverioca" type="{http://xml/globals}TSwiftCode"/>
 *         &lt;element name="ObrRacunBankePoverioca" type="{http://xml/globals}TOznakaRacuna"/>
 *         &lt;element name="Duznik-Nalogodavac" type="{http://xml/globals}TFirma"/>
 *         &lt;element name="SvrhaPlacanja">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *               &lt;maxLength value="255"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Primalac-Poverilac" type="{http://xml/globals}TFirma"/>
 *         &lt;element name="DatumNaloga" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="DatumValute" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="Iznos">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *               &lt;totalDigits value="15"/>
 *               &lt;fractionDigits value="2"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="sifraValute" type="{http://xml/globals}TOznakaValute"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TMT103", propOrder = {
    "id",
    "swiftDuznika",
    "obrRacunBankeDuznika",
    "swiftPoverioca",
    "obrRacunBankePoverioca",
    "duznikNalogodavac",
    "svrhaPlacanja",
    "primalacPoverilac",
    "datumNaloga",
    "datumValute",
    "iznos",
    "sifraValute"
})
public class TMT103 {

    @XmlElement(name = "ID", required = true)
    protected String id;
    @XmlElement(name = "SWIFT_Duznika", required = true)
    protected String swiftDuznika;
    @XmlElement(name = "ObrRacunBankeDuznika", required = true)
    protected String obrRacunBankeDuznika;
    @XmlElement(name = "SWIFT_Poverioca", required = true)
    protected String swiftPoverioca;
    @XmlElement(name = "ObrRacunBankePoverioca", required = true)
    protected String obrRacunBankePoverioca;
    @XmlElement(name = "Duznik-Nalogodavac", required = true)
    protected TFirma duznikNalogodavac;
    @XmlElement(name = "SvrhaPlacanja", required = true)
    protected String svrhaPlacanja;
    @XmlElement(name = "Primalac-Poverilac", required = true)
    protected TFirma primalacPoverilac;
    @XmlElement(name = "DatumNaloga", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumNaloga;
    @XmlElement(name = "DatumValute", required = true)
    @XmlSchemaType(name = "date")
    protected XMLGregorianCalendar datumValute;
    @XmlElement(name = "Iznos", required = true)
    protected BigDecimal iznos;
    @XmlElement(required = true)
    protected String sifraValute;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the swiftDuznika property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSWIFTDuznika() {
        return swiftDuznika;
    }

    /**
     * Sets the value of the swiftDuznika property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSWIFTDuznika(String value) {
        this.swiftDuznika = value;
    }

    /**
     * Gets the value of the obrRacunBankeDuznika property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObrRacunBankeDuznika() {
        return obrRacunBankeDuznika;
    }

    /**
     * Sets the value of the obrRacunBankeDuznika property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObrRacunBankeDuznika(String value) {
        this.obrRacunBankeDuznika = value;
    }

    /**
     * Gets the value of the swiftPoverioca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSWIFTPoverioca() {
        return swiftPoverioca;
    }

    /**
     * Sets the value of the swiftPoverioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSWIFTPoverioca(String value) {
        this.swiftPoverioca = value;
    }

    /**
     * Gets the value of the obrRacunBankePoverioca property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getObrRacunBankePoverioca() {
        return obrRacunBankePoverioca;
    }

    /**
     * Sets the value of the obrRacunBankePoverioca property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setObrRacunBankePoverioca(String value) {
        this.obrRacunBankePoverioca = value;
    }

    /**
     * Gets the value of the duznikNalogodavac property.
     * 
     * @return
     *     possible object is
     *     {@link TFirma }
     *     
     */
    public TFirma getDuznikNalogodavac() {
        return duznikNalogodavac;
    }

    /**
     * Sets the value of the duznikNalogodavac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFirma }
     *     
     */
    public void setDuznikNalogodavac(TFirma value) {
        this.duznikNalogodavac = value;
    }

    /**
     * Gets the value of the svrhaPlacanja property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSvrhaPlacanja() {
        return svrhaPlacanja;
    }

    /**
     * Sets the value of the svrhaPlacanja property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSvrhaPlacanja(String value) {
        this.svrhaPlacanja = value;
    }

    /**
     * Gets the value of the primalacPoverilac property.
     * 
     * @return
     *     possible object is
     *     {@link TFirma }
     *     
     */
    public TFirma getPrimalacPoverilac() {
        return primalacPoverilac;
    }

    /**
     * Sets the value of the primalacPoverilac property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFirma }
     *     
     */
    public void setPrimalacPoverilac(TFirma value) {
        this.primalacPoverilac = value;
    }

    /**
     * Gets the value of the datumNaloga property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumNaloga() {
        return datumNaloga;
    }

    /**
     * Sets the value of the datumNaloga property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumNaloga(XMLGregorianCalendar value) {
        this.datumNaloga = value;
    }

    /**
     * Gets the value of the datumValute property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDatumValute() {
        return datumValute;
    }

    /**
     * Sets the value of the datumValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDatumValute(XMLGregorianCalendar value) {
        this.datumValute = value;
    }

    /**
     * Gets the value of the iznos property.
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getIznos() {
        return iznos;
    }

    /**
     * Sets the value of the iznos property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setIznos(BigDecimal value) {
        this.iznos = value;
    }

    /**
     * Gets the value of the sifraValute property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSifraValute() {
        return sifraValute;
    }

    /**
     * Sets the value of the sifraValute property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSifraValute(String value) {
        this.sifraValute = value;
    }

}