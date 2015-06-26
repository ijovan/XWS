
package banka;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import xml.mt102.MT102;
import xml.mt103.MT103;
import xml.mt900.TMT900;
import xml.mt910.TMT910;


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

    private final static QName _Mt103Request_QNAME = new QName("http://banka", "mt103Request");
    private final static QName _Mt900Request_QNAME = new QName("http://banka", "mt900Request");
    private final static QName _Boolean_QNAME = new QName("http://banka", "boolean");
    private final static QName _Mt910Request_QNAME = new QName("http://banka", "mt910Request");
    private final static QName _Mt102Request_QNAME = new QName("http://banka", "mt102Request");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: banka
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MT103 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://banka", name = "mt103Request")
    public JAXBElement<MT103> createMt103Request(MT103 value) {
        return new JAXBElement<MT103>(_Mt103Request_QNAME, MT103 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TMT900 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://banka", name = "mt900Request")
    public JAXBElement<TMT900> createMt900Request(TMT900 value) {
        return new JAXBElement<TMT900>(_Mt900Request_QNAME, TMT900 .class, null, value);
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
     * Create an instance of {@link JAXBElement }{@code <}{@link TMT910 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://banka", name = "mt910Request")
    public JAXBElement<TMT910> createMt910Request(TMT910 value) {
        return new JAXBElement<TMT910>(_Mt910Request_QNAME, TMT910 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MT102 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://banka", name = "mt102Request")
    public JAXBElement<MT102> createMt102Request(MT102 value) {
        return new JAXBElement<MT102>(_Mt102Request_QNAME, MT102 .class, null, value);
    }

}
