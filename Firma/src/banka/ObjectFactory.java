
package banka;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import xml.izvod.TIzvod;
import xml.nalogzaplacanje.TNalogZaPlacanje;
import xml.zahtevzaizvod.TZahtevZaIzvod;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the banka package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Boolean_QNAME = new QName("http://banka", "boolean");
    private final static QName _NalogZaPlacanjeRequest_QNAME = new QName("http://banka", "nalogZaPlacanjeRequest");
    private final static QName _ZahtevZaIzvodRequest_QNAME = new QName("http://banka", "zahtevZaIzvodRequest");
    private final static QName _ZahtevZaIzvodResponse_QNAME = new QName("http://banka", "zahtevZaIzvodResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: banka
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://banka", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TNalogZaPlacanje }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://banka", name = "nalogZaPlacanjeRequest")
    public JAXBElement<TNalogZaPlacanje> createNalogZaPlacanjeRequest(TNalogZaPlacanje value) {
        return new JAXBElement<TNalogZaPlacanje>(_NalogZaPlacanjeRequest_QNAME, TNalogZaPlacanje.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TZahtevZaIzvod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://banka", name = "zahtevZaIzvodRequest")
    public JAXBElement<TZahtevZaIzvod> createZahtevZaIzvodRequest(TZahtevZaIzvod value) {
        return new JAXBElement<TZahtevZaIzvod>(_ZahtevZaIzvodRequest_QNAME, TZahtevZaIzvod.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TIzvod }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://banka", name = "zahtevZaIzvodResponse")
    public JAXBElement<TIzvod> createZahtevZaIzvodResponse(TIzvod value) {
        return new JAXBElement<TIzvod>(_ZahtevZaIzvodResponse_QNAME, TIzvod.class, null, value);
    }

}
