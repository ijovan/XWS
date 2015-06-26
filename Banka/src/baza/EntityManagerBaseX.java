package baza;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import org.w3c.dom.Node;

import baza.basex.rest.Result;
import baza.basex.rest.Results;

public class EntityManagerBaseX<T, ID extends Serializable> {

	/*
	 * Izbaciti u XML/properties konfiguraciju
	 */
	public static final String REST_URL = "http://localhost:8984/rest/";

	public static final String BASEX_CONTEXT_PATH = "baza.basex.rest";
	
	private String schemaName;
	
	private String contextPath;
	
	private Marshaller marshaller;
	
	private Unmarshaller unmarshaller, basex_unmarshaller;
	
	private JAXBContext context, basex_context;
	
	private URL url;
	
	private HttpURLConnection conn;
	
	public EntityManagerBaseX(String schemaName, String contextPath) throws JAXBException {
		setSchemaName(schemaName);
		setContextPath(contextPath);
		
		context = JAXBContext.newInstance(contextPath);
		marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		unmarshaller = context.createUnmarshaller();
		
		basex_context = JAXBContext.newInstance(BASEX_CONTEXT_PATH);
		basex_unmarshaller = basex_context.createUnmarshaller();
	}
	
	@SuppressWarnings("unchecked")
	public T find(ID resourceId) throws IOException, JAXBException {
		T entity = null;
		
		url = new URL(REST_URL + schemaName + "/" + resourceId);
		conn = (HttpURLConnection) url.openConnection();
		
		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();

		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		
		if (responseCode == HttpURLConnection.HTTP_OK) 
			return (T) unmarshaller.unmarshal(conn.getInputStream());
		
		conn.disconnect();
		return entity;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> findAll() throws IOException, JAXBException {
		Results wrappedResults = null;
		List<T> results = new ArrayList<T>();
		
		StringBuilder builder = new StringBuilder(REST_URL);
		builder.append(schemaName);
		builder.append("?query=collection('invoice')");
		builder.append("&wrap=yes");

		url = new URL(builder.substring(0));
		conn = (HttpURLConnection) url.openConnection();
		//Autentifikacija u konekciji nisam siguran ali valjda je admin:admin
		
		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		conn.setRequestProperty ("Authorization", basicAuth);
		conn.connect();
		//
		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();

		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		
		if (responseCode == HttpURLConnection.HTTP_OK) {
			wrappedResults = (Results) basex_unmarshaller.unmarshal(conn.getInputStream());
			for (Result result : wrappedResults.getResult())
				results.add((T) unmarshaller.unmarshal((Node)result.getAny()));
		}
		
		conn.disconnect();
		return results;
	}
	
	/*
	 * Takes both, XQuery and XUpdate statements.
	 */
	public InputStream executeQuery(String xQuery, boolean wrap) throws IOException {
		InputStream result = null;
		String wrapString = wrap ? "yes" : "no";
		String wrappedQuery = "<query xmlns='http://basex.org/rest'>" + 
				"<text><![CDATA[%s]]></text>" + 
				"<parameter name='wrap' value='" + wrapString + "'/>" +
			"</query>";
		wrappedQuery = String.format(wrappedQuery, xQuery);

		url = new URL(REST_URL + schemaName);
		conn = (HttpURLConnection) url.openConnection();
		
		conn.setDoOutput(true);
		conn.setRequestMethod(RequestMethod.POST);
		conn.setRequestProperty("Content-Type", "application/query+xml");
		//Autentifikacija u konekciji nisam siguran ali valjda je admin:admin
		
				String userpass = "admin:admin";
				String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
				conn.setRequestProperty ("Authorization", basicAuth);
				conn.connect();
				//
		/*
		 * Generate HTTP POST body.
		 */
		System.out.println(wrappedQuery);
		OutputStream out = conn.getOutputStream();
		out.write(wrappedQuery.getBytes("UTF-8"));
		out.close();

		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();

		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		
		if (responseCode == HttpURLConnection.HTTP_OK)
			result = conn.getInputStream();
		//Boris
		conn.disconnect();
		//
		return result;
	}
	
	public void persist(T entity, Long id) throws JAXBException, IOException {

		Long resourceId = id;
		
		url = new URL(REST_URL + schemaName + "/" + resourceId);
		System.out.println(url);
		conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(RequestMethod.PUT);
		//Autentifikacija u konekciji nisam siguran ali valjda je admin:admin
		
				String userpass = "admin:admin";
				String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
				conn.setRequestProperty ("Authorization", basicAuth);
				conn.connect();
		//
		marshaller.marshal(entity, conn.getOutputStream());
		
		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();
		
		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		
		conn.disconnect();
	}
	
	public void delete(ID resourceId) throws IOException {
		url = new URL(REST_URL + schemaName + "/" + resourceId);
		conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod(RequestMethod.DELETE);
		//Autentifikacija u konekciji nisam siguran ali valjda je admin:admin
		
				String userpass = "admin:admin";
				String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
				conn.setRequestProperty ("Authorization", basicAuth);
				conn.connect();
				//
		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();
		
		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		
		conn.disconnect();
	}
	
	public void update(T entity, ID resourceId) throws IOException, JAXBException {
		url = new URL(REST_URL + schemaName + "/" + resourceId);
		conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(RequestMethod.PUT);
		//Autentifikacija u konekciji nisam siguran ali valjda je admin:admin
		
				String userpass = "admin:admin";
				String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
				conn.setRequestProperty ("Authorization", basicAuth);
				conn.connect();
				//
		marshaller.marshal(entity, conn.getOutputStream());
		
		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();
		
		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		
		conn.disconnect();
	}

	/**
	 * Implements some sort of identity strategy, since it isn't natively supported by XMLDB.
	 * @return the next id value.
	 * @throws IOException
	 */
	public Long getIdentity() throws IOException {

		String xQuery = "max(//@id)";
		InputStream input = executeQuery(xQuery, false);
		BufferedReader br = new BufferedReader(new InputStreamReader(input));
		
		String line = br.readLine();
		if (line != null)
			return Long.valueOf(line) + 1L;
		return 1L;
	}
	
	public static int createSchema(String schemaName) throws Exception {
		System.out.println("=== PUT: create a new database: " + schemaName + " ===");
		URL url = new URL(REST_URL + schemaName);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setRequestMethod(RequestMethod.PUT);
		String userpass = "admin:admin";
		String basicAuth = "Basic " + javax.xml.bind.DatatypeConverter.printBase64Binary(userpass.getBytes());
		conn.setRequestProperty ("Authorization", basicAuth);
		conn.connect();
		int responseCode = printResponse(conn);
		conn.disconnect();
		return responseCode;
	}
	
	public static int printResponse(HttpURLConnection conn) throws Exception {
		int responseCode = conn.getResponseCode();
		String message = conn.getResponseMessage();
		System.out.println("\n* HTTP response: " + responseCode + " (" + message + ')');
		return responseCode;
	}
	
	/*
	 * Get/set methods
	 */
	
	public void setSchemaName(String schemaName) {
		this.schemaName = schemaName;
	}
	
	public String getSchemaName() {
		return schemaName;
	}

	public String getContextPath() {
		return contextPath;
	}

	public void setContextPath(String contextPath) {
		this.contextPath = contextPath;
	}

	public Marshaller getMarshaller() {
		return marshaller;
	}

	public void setMarshaller(Marshaller marshaller) {
		this.marshaller = marshaller;
	}

	public Unmarshaller getUnmarshaller() {
		return unmarshaller;
	}

	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.unmarshaller = unmarshaller;
	}

	public JAXBContext getContext() {
		return context;
	}

	public void setContext(JAXBContext context) {
		this.context = context;
	}
	
}
