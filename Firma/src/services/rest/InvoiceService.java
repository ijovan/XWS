package services.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.xml.bind.JAXBException;

import org.apache.log4j.Logger;

import sessionbeans.transaction.FakturaDaoLocal;
import xml.faktura.Faktura;
import xml.faktura.Faktura.StavkaFakture;


@Path("/partneri")
public class InvoiceService {

	private static Logger log = Logger.getLogger(Faktura.class);
	private String[] p= {"12","13","14"};	

	@EJB
	private FakturaDaoLocal fakturaDao;

	//	1. POST <url_kupca>/partneri/<id_dobavljaca>/fakture/
	//	a) Omogu�ava slanje fakture servisu Firme poslovnog partnera za isporu�enu robu i usluge. 
	//	b) Parametar <url_kupca> predstavlja adresu servisa poslovnog partnera koji prima fakuturu.
	//	c) Parametar <id_dobavljaca> predstavlja PIB poslovnog partnera koji izdaje fakturu.
	//	d) Ukoliko dati dobavljac jeste poslovni partner kupca, a faktura je validna i prihva�ena, 
	//	odgovor je HTTP 201 Created, sa zaglavljem Content-Location: 
	//  /partneri/<id_dobavljaca>/fakture/<id_fakture>koje ukazuje na URL novokreirane fakture.
	//	e) U slu�aju da dobavlja� nije poslovni partner kupca, odgovor je HTTP 403 Forbidden.
	//	f) U slu�aju neispravne fakture, odgovor je HTTP 400 Bad Request.		
	@POST
	@Path("/{id}/fakture/") 
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendInvoice(@PathParam("id") String id, Faktura tf) throws JAXBException, IOException{
		System.out.println("USAO U 1");
		ResponseBuilder rb;
		//provera da li je poslovni parner kompanije
		if(isPartner(id)){
			//provera da li je faktura ispravna
			if(!fakturaDao.validateInvoice(tf)){
				Faktura save= fakturaDao.persist(tf);
				rb = Response.created(URI.create("/partneri/"+id+"/fakture/"+save.getId()));
			}else{
				rb = Response.status(Status.BAD_REQUEST);
			}
		}else{
			rb = Response.status(Status.FORBIDDEN);
		}
		return rb.build();
	}


	//	2. GET <url_kupca>/partneri/<id_dobavljaca>/fakture
	//	a) Omogu�ava pribavljanje svih faktura koje je izdao dati dobavlja� datom kupcu.
	//	b) Ukoliko dati dobavljac jeste poslovni partner kupca, odgovor je 200 OK sa kolekcijom 
	//	faktura u telu HTTP odgovora.
	//	c) U suprotnom slu�aju odgovor je HTTP 404 Not Found.
	@GET
	@Path("/{id}/fakture")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllInvoice(@PathParam("id") String id) throws IOException, JAXBException{
		System.out.println("USAO U 2");
		ResponseBuilder rb;
		if(isPartner(id)){
			List<Faktura> povratna = fakturaDao.getInvoicesForPartner(Long.parseLong(id));
			
			if (!povratna.isEmpty())
			{
				rb = Response.ok(povratna);
			}
			else
			{
				rb = Response.status(Status.NOT_FOUND);
				log.info("Nema faktura za trazeni zahtev.");
			}
		}else{
			rb = Response.status(Status.FORBIDDEN);
		}
		return rb.build();
	}

	//	3. GET <url_kupca>/partneri/<id_dobavljaca>/fakture/<id_fakture>
	//	a) Omogu�ava pribavljanje date fakture datog dobavlja�a. 
	//	b) Parametar <id_fakture> predstavlja identifikator fakture.
	//	c) Ukoliko dati dobavlja� jeste poslovni partner kupca i postoji data faktura, odgovor je HTTP 
	//	200 OK sa konkretnom fakturom u telu HTTP odgovora.
	//	d) U suprotnom slu�aju odgovor je HTTP 404 Not Found. 
	@GET
	@Path("/{id}/fakture/{id_i}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findInvoiceById(@PathParam("id") String id, @PathParam("id_i") long idi) throws JAXBException, IOException{
		System.out.println("USAO U 3");
		ResponseBuilder rb = null;
		if(isPartner(id)){
			List<Faktura> supplierInvoices = fakturaDao.getInvoicesForPartner(Long.parseLong(id));
			rb = Response.status(Status.NOT_FOUND);//stavim da nije pronadjen u slucaju prazne liste
			for (Faktura tf : supplierInvoices){
				if(tf.getId() == idi) {
					rb = Response.ok(tf);
					break; //kad nadje kraj da ga ne pregazi
				}
			}
		}else{
			rb = Response.status(Status.FORBIDDEN);
		}

		return rb.build();    
	}

	//	4. GET <url_kupca>/partneri/<id_dobavljaca>/fakture/<id_fakture>/stavke
	//	a) Omogu�ava pribavljanje svih stavki date fakture datog dobavlja�a. 
	//	b) Ukoliko dati dobavlja� jeste poslovni partner kupca i postoji data faktura, odgovor je HTTP 
	//	200 OK, sa kolekcijom stavki u telu HTTP odgovora.
	//	c) U suprotnom slu�aju odgovor je HTTP 404 Not Found.
	@GET
	@Path("/{id}/fakture/{id_i}/stavke")
	@Produces(MediaType.APPLICATION_XML)
	public Response getInvoiceItems(@PathParam("id") String id, @PathParam("id_i") long idi) throws JAXBException, IOException{
		System.out.println("USAO U 4");
		ResponseBuilder rb;
		if(isPartner(id)){
			List<Faktura.StavkaFakture> povratna = fakturaDao.getInvoiceItemsForInvoice(idi, Long.parseLong(id));
			if( povratna != null){
				rb = Response.ok(povratna);
			}else{
				rb = Response.status(Status.NOT_FOUND);
			}
		}else{
			rb = Response.status(Status.FORBIDDEN);
		}
		return rb.build();
	}

	//	5. POST <url_kupca>/partneri/<id_dobavljaca>/fakture/<id_fakture>/stavke
	//	a) Omogu�ava dodavanje nove stavke u okviru postoje�e fakture datog dobavlja�a. 
	//	b) Ukoliko dati dobavljac jeste poslovni partner kupca, a data faktura postoji, odgovor je 
	//	HTTP 201 Created, sa zaglavljem Content-Location: 
	//	/partneri/<id_dobavljaca>/fakture/<id_fakture>/stavke/<red_br> koje ukazuje na URL 
	//	novododate stavke.
	//	c) U slu�aju da dobavlja� nije poslovni partner kupca, odgovor je HTTP 403 Forbidden.
	//	d) U slu�aju nepostoje�e fakture, odgovor je HTTP 404 Not Found.
	//	e) U slu�aju neispravne stavke, odgovor je HTTP 400 Bad Request.	
	@POST
	@Path("/{id}/fakture/{id_i}/stavke")
	@Produces(MediaType.APPLICATION_XML)
	public Response createInvoiceItem(@PathParam("id") String id, @PathParam("id_i") long idi, StavkaFakture sf) throws URISyntaxException{
		System.out.println("USAO U 5");
		ResponseBuilder rb=null;
		Faktura result = null;		
		if(isPartner(id)){
			if (sf.isValid())
			{
				try {
					result = fakturaDao.createInvoiceItem(idi, sf);
				} catch (IOException | JAXBException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				if(result == null){
					rb = Response.status(Status.NOT_FOUND);
				}
				else
				{
					rb = Response.created(new URI("/partneri/"+id+"/fakture/"+idi+"/stavke/"+sf.getRedniBroj()));
					//.type("application/xml").entity(newInvoiceItem).build();		
				}
			}
			else
			{
				rb= Response.status(Status.BAD_REQUEST);					
			}
		}
		else
		{
			rb = Response.status(Status.FORBIDDEN);
		}
		return rb.build();
	}

	//	6. GET <url_kupca>/partneri/<id_dobavljaca>/fakture/<id_fakture>/stavke/<red_br>
	//	a) Omogu�ava pribavljanje pojedina�ne stavke date fakture datog dobavlja�a. 
	//	b) Parametar <red_br> predstavlja redni broj stavke u okviru fakture.
	//	c) Ukoliko dati dobavlja� jeste poslovni partner kupca, a data stavka u okviru date fakture 
	//	postoji, odgovor je HTTP 200 OK sa konkretnom stavkom u telu HTTP odgovora.
	//	d) U suprotnom slu�aju odgovor je HTTP 404 Not Found.
	@GET
	@Path("/{id}/fakture/{id_i}/stavke/{r_br}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getInvoiceItemByNo(@PathParam("id") String id, @PathParam("id_i") long idi,@PathParam("r_br") long rbr){
		System.out.println("USAO U 6");
		ResponseBuilder rb =null;
		StavkaFakture res = null;
		try {
			if(isPartner(id)){
				res = fakturaDao.findItemInInvoice(idi, rbr);
				if(res != null){
					rb = Response.ok().type("application/xml").entity(res); //proveri ti za svaki slucaj
				}else{
					rb = Response.status(Status.NOT_FOUND);
				}
			}else{
				rb = Response.status(Status.FORBIDDEN);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return rb.build();
	}

	//	7. PUT <url_kupca>/partneri/<id_dobavljaca>/fakture/<id_fakture>/stavke/<red_br>
	//	a) Omogu�ava izmenu postoje�e stavke u okviru date fakture datog dobavlja�a. 
	//	b) Ukoliko dati dobavljac jeste poslovni partner kupca, a data stavka u okviru date fakture 
	//	postoji, odgovor je HTTP 200 OK.
	//	c) U slu�aju da dobavlja� nije poslovni partner kupca, odgovor je HTTP 403 Forbidden.
	//	d) U slu�aju nepostoje�e fakture ili stavke, odgovor je HTTP 404 Not Found.
	//	e) U slu�aju neispravne stavke, odgovor je HTTP 400 Bad Request.
	@PUT
	@Path("/{id}/fakture/{id_i}/stavke/{r_br}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateInvoiceItem(@PathParam("id") String id, @PathParam("id_i") long idi,@PathParam("r_br") long rbr, StavkaFakture sf){
		System.out.println("USAO U 7");
		ResponseBuilder rb =null;
		Faktura res = null;
		try {
			if(isPartner(id)){
				if (sf.isValid())
				{
					if (fakturaDao.findById(idi) != null)
					{
						res = fakturaDao.updateInvoiceItem(idi, sf);
						if (res != null)
						{
							rb = Response.status(Status.OK);
						}
						else
						{
							rb = Response.status(Status.NOT_FOUND);
						}
					}
					else
					{
						rb = Response.status(Status.NOT_FOUND);
					}
				}
				else
				{
					rb = Response.status(Status.BAD_REQUEST);
				}
			}else{
				rb = Response.status(Status.FORBIDDEN);
			}			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return rb.build();
	}

	//	8. DELETE <url_kupca>/partneri/<id_dobavljaca>/fakture/<id_fakture>/stavke/<red_br>
	//	a) Omogu�ava brisanje pojedina�ne stavke date fakture datog dobavlja�a. 
	//	b) Ukoliko dati dobavlja� jeste poslovni partner kupca, a data stavka u okviru date fakture 
	//	postoji, odgovor je HTTP 204 No Content.
	//	c) U slu�aju da dobavlja� nije poslovni partner kupca, odgovor je HTTP 403 Forbidden.
	//	d) U suprotnom slu�aju (faktura ili stavka ne postoji) odgovor je HTTP 404 Not Found.
	@DELETE
	@Path("/{id}/fakture/{id_i}/stavke/{r_br}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteInvoiceItem(@PathParam("id") String id, @PathParam("id_i") long idi,@PathParam("r_br") long rbr){
		System.out.println("USAO U 8");
		ResponseBuilder rb=null;
		Faktura res ;
		try{
			if(isPartner(id)){
				res = fakturaDao.findById(idi);
				if (res != null)
				{
					res = fakturaDao.removeItemFromInvoice(idi, rbr);
					if (res != null)
					{
						rb = Response.status(Status.NO_CONTENT);
					}
					else
					{
						rb = Response.status(Status.NOT_FOUND);
					}
				}
				else
				{
					rb = Response.status(Status.NOT_FOUND);
				}
			}else{
				rb = Response.status(Status.FORBIDDEN);
			}
		}catch (IOException e) {
			e.printStackTrace();
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return rb.build();
	}

	private boolean isPartner(String firmId)
	{
		for (int i=0; i < p.length; i++)
		{
			if (firmId.equals(p[i]))
				return true;
		}

		return false;
	}

}
