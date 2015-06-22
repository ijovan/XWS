
package xml.faktura;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

import sessionbeans.common.Identifiable;


/**
 * <p>Java class for TFaktura complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="TFaktura">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Zaglavlje">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ID_poruke">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;length value="50"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Naziv_dobavljaca">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Adresa_dobavljaca">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="255"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="PIB_dobavljaca">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;length value="11"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Naziv_kupca">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="55"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Adresa_kupca">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="55"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="PIB_kupca">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;length value="11"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Broj_racuna">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *                         &lt;pattern value="[0-9]{6}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Datum_racuna" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                   &lt;element name="Vrednost_robe">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;totalDigits value="17"/>
 *                         &lt;fractionDigits value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Vrednost_usluga">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;totalDigits value="17"/>
 *                         &lt;fractionDigits value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Ukupno_roba_i_usluga">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;totalDigits value="17"/>
 *                         &lt;fractionDigits value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Ukupan_rabat">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;totalDigits value="17"/>
 *                         &lt;fractionDigits value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Ukupan_porez">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;totalDigits value="17"/>
 *                         &lt;fractionDigits value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Oznaka_valute">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;length value="3"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Iznos_za_uplatu">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;totalDigits value="17"/>
 *                         &lt;fractionDigits value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Uplata_na_racun">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="18"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Datum_valute" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Stavka_fakture" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Redni_broj">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
 *                         &lt;totalDigits value="3"/>
 *                         &lt;pattern value="[0-9]{3}"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Naziv_robe_ili_usluge">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;maxLength value="120"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Kolicina">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;totalDigits value="12"/>
 *                         &lt;fractionDigits value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Jedinica_mere">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *                         &lt;length value="6"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Jedinicna_cena">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;totalDigits value="12"/>
 *                         &lt;fractionDigits value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Vrednost">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;totalDigits value="12"/>
 *                         &lt;fractionDigits value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Procenat_rabata">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;totalDigits value="7"/>
 *                         &lt;fractionDigits value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Iznos_rabata">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;totalDigits value="14"/>
 *                         &lt;fractionDigits value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Umanjeno_za_rabat">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;totalDigits value="14"/>
 *                         &lt;fractionDigits value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                   &lt;element name="Ukupan_porez">
 *                     &lt;simpleType>
 *                       &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
 *                         &lt;totalDigits value="14"/>
 *                         &lt;fractionDigits value="2"/>
 *                       &lt;/restriction>
 *                     &lt;/simpleType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TFaktura", propOrder = {
    "zaglavlje",
    "stavkaFakture"
})
public class TFaktura extends Identifiable{

    @XmlElement(name = "Zaglavlje", required = true)
    protected TFaktura.Zaglavlje zaglavlje;
    @XmlElement(name = "Stavka_fakture", required = true)
    protected List<TFaktura.StavkaFakture> stavkaFakture;

    /**
     * Gets the value of the zaglavlje property.
     * 
     * @return
     *     possible object is
     *     {@link TFaktura.Zaglavlje }
     *     
     */
    public TFaktura.Zaglavlje getZaglavlje() {
        return zaglavlje;
    }

    /**
     * Sets the value of the zaglavlje property.
     * 
     * @param value
     *     allowed object is
     *     {@link TFaktura.Zaglavlje }
     *     
     */
    public void setZaglavlje(TFaktura.Zaglavlje value) {
        this.zaglavlje = value;
    }

    /**
     * Gets the value of the stavkaFakture property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the stavkaFakture property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStavkaFakture().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link TFaktura.StavkaFakture }
     * 
     * 
     */
    public List<TFaktura.StavkaFakture> getStavkaFakture() {
        if (stavkaFakture == null) {
            stavkaFakture = new ArrayList<TFaktura.StavkaFakture>();
        }
        return this.stavkaFakture;
    }


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
     *         &lt;element name="Redni_broj">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
     *               &lt;totalDigits value="3"/>
     *               &lt;pattern value="[0-9]{3}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Naziv_robe_ili_usluge">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="120"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Kolicina">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;totalDigits value="12"/>
     *               &lt;fractionDigits value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Jedinica_mere">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;length value="6"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Jedinicna_cena">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;totalDigits value="12"/>
     *               &lt;fractionDigits value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Vrednost">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;totalDigits value="12"/>
     *               &lt;fractionDigits value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Procenat_rabata">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;totalDigits value="7"/>
     *               &lt;fractionDigits value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Iznos_rabata">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;totalDigits value="14"/>
     *               &lt;fractionDigits value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Umanjeno_za_rabat">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;totalDigits value="14"/>
     *               &lt;fractionDigits value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Ukupan_porez">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;totalDigits value="14"/>
     *               &lt;fractionDigits value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "redniBroj",
        "nazivRobeIliUsluge",
        "kolicina",
        "jedinicaMere",
        "jedinicnaCena",
        "vrednost",
        "procenatRabata",
        "iznosRabata",
        "umanjenoZaRabat",
        "ukupanPorez"
    })
    public static class StavkaFakture {

        @XmlElement(name = "Redni_broj", required = true)
        protected BigInteger redniBroj;
        @XmlElement(name = "Naziv_robe_ili_usluge", required = true)
        protected String nazivRobeIliUsluge;
        @XmlElement(name = "Kolicina", required = true)
        protected BigDecimal kolicina;
        @XmlElement(name = "Jedinica_mere", required = true)
        protected String jedinicaMere;
        @XmlElement(name = "Jedinicna_cena", required = true)
        protected BigDecimal jedinicnaCena;
        @XmlElement(name = "Vrednost", required = true)
        protected BigDecimal vrednost;
        @XmlElement(name = "Procenat_rabata", required = true)
        protected BigDecimal procenatRabata;
        @XmlElement(name = "Iznos_rabata", required = true)
        protected BigDecimal iznosRabata;
        @XmlElement(name = "Umanjeno_za_rabat", required = true)
        protected BigDecimal umanjenoZaRabat;
        @XmlElement(name = "Ukupan_porez", required = true)
        protected BigDecimal ukupanPorez;

        /**
         * Gets the value of the redniBroj property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getRedniBroj() {
            return redniBroj;
        }

        /**
         * Sets the value of the redniBroj property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setRedniBroj(BigInteger value) {
            this.redniBroj = value;
        }

        /**
         * Gets the value of the nazivRobeIliUsluge property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNazivRobeIliUsluge() {
            return nazivRobeIliUsluge;
        }

        /**
         * Sets the value of the nazivRobeIliUsluge property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNazivRobeIliUsluge(String value) {
            this.nazivRobeIliUsluge = value;
        }

        /**
         * Gets the value of the kolicina property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getKolicina() {
            return kolicina;
        }

        /**
         * Sets the value of the kolicina property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setKolicina(BigDecimal value) {
            this.kolicina = value;
        }

        /**
         * Gets the value of the jedinicaMere property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getJedinicaMere() {
            return jedinicaMere;
        }

        /**
         * Sets the value of the jedinicaMere property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setJedinicaMere(String value) {
            this.jedinicaMere = value;
        }

        /**
         * Gets the value of the jedinicnaCena property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getJedinicnaCena() {
            return jedinicnaCena;
        }

        /**
         * Sets the value of the jedinicnaCena property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setJedinicnaCena(BigDecimal value) {
            this.jedinicnaCena = value;
        }

        /**
         * Gets the value of the vrednost property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getVrednost() {
            return vrednost;
        }

        /**
         * Sets the value of the vrednost property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setVrednost(BigDecimal value) {
            this.vrednost = value;
        }

        /**
         * Gets the value of the procenatRabata property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getProcenatRabata() {
            return procenatRabata;
        }

        /**
         * Sets the value of the procenatRabata property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setProcenatRabata(BigDecimal value) {
            this.procenatRabata = value;
        }

        /**
         * Gets the value of the iznosRabata property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getIznosRabata() {
            return iznosRabata;
        }

        /**
         * Sets the value of the iznosRabata property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setIznosRabata(BigDecimal value) {
            this.iznosRabata = value;
        }

        /**
         * Gets the value of the umanjenoZaRabat property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getUmanjenoZaRabat() {
            return umanjenoZaRabat;
        }

        /**
         * Sets the value of the umanjenoZaRabat property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setUmanjenoZaRabat(BigDecimal value) {
            this.umanjenoZaRabat = value;
        }

        /**
         * Gets the value of the ukupanPorez property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getUkupanPorez() {
            return ukupanPorez;
        }

        /**
         * Sets the value of the ukupanPorez property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setUkupanPorez(BigDecimal value) {
            this.ukupanPorez = value;
        }

    }


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
     *         &lt;element name="ID_poruke">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;length value="50"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Naziv_dobavljaca">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Adresa_dobavljaca">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="255"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="PIB_dobavljaca">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;length value="11"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Naziv_kupca">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="55"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Adresa_kupca">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="55"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="PIB_kupca">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;length value="11"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Broj_racuna">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}positiveInteger">
     *               &lt;pattern value="[0-9]{6}"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Datum_racuna" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *         &lt;element name="Vrednost_robe">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;totalDigits value="17"/>
     *               &lt;fractionDigits value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Vrednost_usluga">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;totalDigits value="17"/>
     *               &lt;fractionDigits value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Ukupno_roba_i_usluga">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;totalDigits value="17"/>
     *               &lt;fractionDigits value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Ukupan_rabat">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;totalDigits value="17"/>
     *               &lt;fractionDigits value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Ukupan_porez">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;totalDigits value="17"/>
     *               &lt;fractionDigits value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Oznaka_valute">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;length value="3"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Iznos_za_uplatu">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}decimal">
     *               &lt;totalDigits value="17"/>
     *               &lt;fractionDigits value="2"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Uplata_na_racun">
     *           &lt;simpleType>
     *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
     *               &lt;maxLength value="18"/>
     *             &lt;/restriction>
     *           &lt;/simpleType>
     *         &lt;/element>
     *         &lt;element name="Datum_valute" type="{http://www.w3.org/2001/XMLSchema}date"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "idPoruke",
        "nazivDobavljaca",
        "adresaDobavljaca",
        "pibDobavljaca",
        "nazivKupca",
        "adresaKupca",
        "pibKupca",
        "brojRacuna",
        "datumRacuna",
        "vrednostRobe",
        "vrednostUsluga",
        "ukupnoRobaIUsluga",
        "ukupanRabat",
        "ukupanPorez",
        "oznakaValute",
        "iznosZaUplatu",
        "uplataNaRacun",
        "datumValute"
    })
    public static class Zaglavlje {

        @XmlElement(name = "ID_poruke", required = true)
        protected String idPoruke;
        @XmlElement(name = "Naziv_dobavljaca", required = true)
        protected String nazivDobavljaca;
        @XmlElement(name = "Adresa_dobavljaca", required = true)
        protected String adresaDobavljaca;
        @XmlElement(name = "PIB_dobavljaca", required = true)
        protected String pibDobavljaca;
        @XmlElement(name = "Naziv_kupca", required = true)
        protected String nazivKupca;
        @XmlElement(name = "Adresa_kupca", required = true)
        protected String adresaKupca;
        @XmlElement(name = "PIB_kupca", required = true)
        protected String pibKupca;
        @XmlElement(name = "Broj_racuna", required = true)
        protected BigInteger brojRacuna;
        @XmlElement(name = "Datum_racuna", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar datumRacuna;
        @XmlElement(name = "Vrednost_robe", required = true)
        protected BigDecimal vrednostRobe;
        @XmlElement(name = "Vrednost_usluga", required = true)
        protected BigDecimal vrednostUsluga;
        @XmlElement(name = "Ukupno_roba_i_usluga", required = true)
        protected BigDecimal ukupnoRobaIUsluga;
        @XmlElement(name = "Ukupan_rabat", required = true)
        protected BigDecimal ukupanRabat;
        @XmlElement(name = "Ukupan_porez", required = true)
        protected BigDecimal ukupanPorez;
        @XmlElement(name = "Oznaka_valute", required = true)
        protected String oznakaValute;
        @XmlElement(name = "Iznos_za_uplatu", required = true)
        protected BigDecimal iznosZaUplatu;
        @XmlElement(name = "Uplata_na_racun", required = true)
        protected String uplataNaRacun;
        @XmlElement(name = "Datum_valute", required = true)
        @XmlSchemaType(name = "date")
        protected XMLGregorianCalendar datumValute;

        /**
         * Gets the value of the idPoruke property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getIDPoruke() {
            return idPoruke;
        }

        /**
         * Sets the value of the idPoruke property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setIDPoruke(String value) {
            this.idPoruke = value;
        }

        /**
         * Gets the value of the nazivDobavljaca property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNazivDobavljaca() {
            return nazivDobavljaca;
        }

        /**
         * Sets the value of the nazivDobavljaca property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNazivDobavljaca(String value) {
            this.nazivDobavljaca = value;
        }

        /**
         * Gets the value of the adresaDobavljaca property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdresaDobavljaca() {
            return adresaDobavljaca;
        }

        /**
         * Sets the value of the adresaDobavljaca property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdresaDobavljaca(String value) {
            this.adresaDobavljaca = value;
        }

        /**
         * Gets the value of the pibDobavljaca property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPIBDobavljaca() {
            return pibDobavljaca;
        }

        /**
         * Sets the value of the pibDobavljaca property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPIBDobavljaca(String value) {
            this.pibDobavljaca = value;
        }

        /**
         * Gets the value of the nazivKupca property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getNazivKupca() {
            return nazivKupca;
        }

        /**
         * Sets the value of the nazivKupca property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setNazivKupca(String value) {
            this.nazivKupca = value;
        }

        /**
         * Gets the value of the adresaKupca property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getAdresaKupca() {
            return adresaKupca;
        }

        /**
         * Sets the value of the adresaKupca property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setAdresaKupca(String value) {
            this.adresaKupca = value;
        }

        /**
         * Gets the value of the pibKupca property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getPIBKupca() {
            return pibKupca;
        }

        /**
         * Sets the value of the pibKupca property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setPIBKupca(String value) {
            this.pibKupca = value;
        }

        /**
         * Gets the value of the brojRacuna property.
         * 
         * @return
         *     possible object is
         *     {@link BigInteger }
         *     
         */
        public BigInteger getBrojRacuna() {
            return brojRacuna;
        }

        /**
         * Sets the value of the brojRacuna property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigInteger }
         *     
         */
        public void setBrojRacuna(BigInteger value) {
            this.brojRacuna = value;
        }

        /**
         * Gets the value of the datumRacuna property.
         * 
         * @return
         *     possible object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public XMLGregorianCalendar getDatumRacuna() {
            return datumRacuna;
        }

        /**
         * Sets the value of the datumRacuna property.
         * 
         * @param value
         *     allowed object is
         *     {@link XMLGregorianCalendar }
         *     
         */
        public void setDatumRacuna(XMLGregorianCalendar value) {
            this.datumRacuna = value;
        }

        /**
         * Gets the value of the vrednostRobe property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getVrednostRobe() {
            return vrednostRobe;
        }

        /**
         * Sets the value of the vrednostRobe property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setVrednostRobe(BigDecimal value) {
            this.vrednostRobe = value;
        }

        /**
         * Gets the value of the vrednostUsluga property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getVrednostUsluga() {
            return vrednostUsluga;
        }

        /**
         * Sets the value of the vrednostUsluga property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setVrednostUsluga(BigDecimal value) {
            this.vrednostUsluga = value;
        }

        /**
         * Gets the value of the ukupnoRobaIUsluga property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getUkupnoRobaIUsluga() {
            return ukupnoRobaIUsluga;
        }

        /**
         * Sets the value of the ukupnoRobaIUsluga property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setUkupnoRobaIUsluga(BigDecimal value) {
            this.ukupnoRobaIUsluga = value;
        }

        /**
         * Gets the value of the ukupanRabat property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getUkupanRabat() {
            return ukupanRabat;
        }

        /**
         * Sets the value of the ukupanRabat property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setUkupanRabat(BigDecimal value) {
            this.ukupanRabat = value;
        }

        /**
         * Gets the value of the ukupanPorez property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getUkupanPorez() {
            return ukupanPorez;
        }

        /**
         * Sets the value of the ukupanPorez property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setUkupanPorez(BigDecimal value) {
            this.ukupanPorez = value;
        }

        /**
         * Gets the value of the oznakaValute property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getOznakaValute() {
            return oznakaValute;
        }

        /**
         * Sets the value of the oznakaValute property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setOznakaValute(String value) {
            this.oznakaValute = value;
        }

        /**
         * Gets the value of the iznosZaUplatu property.
         * 
         * @return
         *     possible object is
         *     {@link BigDecimal }
         *     
         */
        public BigDecimal getIznosZaUplatu() {
            return iznosZaUplatu;
        }

        /**
         * Sets the value of the iznosZaUplatu property.
         * 
         * @param value
         *     allowed object is
         *     {@link BigDecimal }
         *     
         */
        public void setIznosZaUplatu(BigDecimal value) {
            this.iznosZaUplatu = value;
        }

        /**
         * Gets the value of the uplataNaRacun property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getUplataNaRacun() {
            return uplataNaRacun;
        }

        /**
         * Sets the value of the uplataNaRacun property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setUplataNaRacun(String value) {
            this.uplataNaRacun = value;
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

    }


	@Override
	public Long getId() {
		return Long.parseLong(zaglavlje.idPoruke);
	}

	@Override
	public void setId(Long value) {
		zaglavlje.idPoruke = value.toString();
		
	}

}
