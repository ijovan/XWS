
package centralnabanka;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

import xml.mt102.MT102;
import xml.mt103.MT103;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the centralnabanka package. 
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

    private final static QName _Mt102Request_QNAME = new QName("http://centralnabanka", "mt102Request");
    private final static QName _Boolean_QNAME = new QName("http://centralnabanka", "boolean");
    private final static QName _Mt103Request_QNAME = new QName("http://centralnabanka", "mt103Request");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: centralnabanka
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TMT102 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://centralnabanka", name = "mt102Request")
    public JAXBElement<MT102> createMt102Request(MT102 value) {
        return new JAXBElement<MT102>(_Mt102Request_QNAME, MT102 .class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://centralnabanka", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link MT103 }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://centralnabanka", name = "mt103Request")
    public JAXBElement<MT103> createMt103Request(MT103 value) {
        return new JAXBElement<MT103>(_Mt103Request_QNAME, MT103 .class, null, value);
    }

}
