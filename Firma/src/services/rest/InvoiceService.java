package services.rest;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
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
import xml.faktura.TFaktura;
import xml.faktura.TFaktura.StavkaFakture;


@Path("/partneri")
public class InvoiceService {

	private static Logger log = Logger.getLogger(TFaktura.class);
	private String[] p= {"boris","pera","Cverdelj"};	

	@EJB
	private FakturaDaoLocal fakturaDao;

	//	1. POST <url_kupca>/partneri/<id_dobavljaca>/fakture/
	//	a) Omoguæava slanje fakture servisu Firme poslovnog partnera za isporuèenu robu i usluge. 
	//	b) Parametar <url_kupca> predstavlja adresu servisa poslovnog partnera koji prima fakuturu.
	//	c) Parametar <id_dobavljaca> predstavlja PIB poslovnog partnera koji izdaje fakturu.
	//	d) Ukoliko dati dobavljac jeste poslovni partner kupca, a faktura je validna i prihvaæena, 
	//	odgovor je HTTP 201 Created, sa zaglavljem Content-Location: 
	//  /partneri/<id_dobavljaca>/fakture/<id_fakture>koje ukazuje na URL novokreirane fakture.
	//	e) U sluèaju da dobavljaè nije poslovni partner kupca, odgovor je HTTP 403 Forbidden.
	//	f) U sluèaju neispravne fakture, odgovor je HTTP 400 Bad Request.		
	@POST
	@Path("/{id}/fakture/") //id dobavljaca??
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Response sendInvoice(@PathParam("id") String id, TFaktura tf) throws JAXBException, IOException{
		ResponseBuilder rb;
		//provera da li je poslovni parner kompanije
		if(id.equalsIgnoreCase(p[0]) || id.equalsIgnoreCase(p[1]) ||id.equalsIgnoreCase(p[2])){
			//provera da li je faktura ispravna
			if(!fakturaDao.validateInvoice(tf)){
				TFaktura save= fakturaDao.persist(tf);
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
	//	a) Omoguæava pribavljanje svih faktura koje je izdao dati dobavljaè datom kupcu.
	//	b) Ukoliko dati dobavljac jeste poslovni partner kupca, odgovor je 200 OK sa kolekcijom 
	//	faktura u telu HTTP odgovora.
	//	c) U suprotnom sluèaju odgovor je HTTP 404 Not Found.
	@GET
	@Path("/{id}/fakture")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllInvoice(@PathParam("id") String id) throws IOException, JAXBException{
		ResponseBuilder rb;
		if(id.equalsIgnoreCase(p[0]) || id.equalsIgnoreCase(p[1]) ||id.equalsIgnoreCase(p[2])){
			rb = Response.ok(fakturaDao.findAll()); //promeniti da ga gleda po IDju!!!!!
		}else{
			rb = Response.status(Status.FORBIDDEN);
		}
		return rb.build();
	}

	//	3. GET <url_kupca>/partneri/<id_dobavljaca>/fakture/<id_fakture>
	//	a) Omoguæava pribavljanje date fakture datog dobavljaèa. 
	//	b) Parametar <id_fakture> predstavlja identifikator fakture.
	//	c) Ukoliko dati dobavljaè jeste poslovni partner kupca i postoji data faktura, odgovor je HTTP 
	//	200 OK sa konkretnom fakturom u telu HTTP odgovora.
	//	d) U suprotnom sluèaju odgovor je HTTP 404 Not Found. 
	@GET
	@Path("/{id}/fakture/{id_i}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findInvoiceById(@PathParam("id") String id, @PathParam("id_i") long idi) throws JAXBException, IOException{
		ResponseBuilder rb;
		if(id.equalsIgnoreCase(p[0]) || id.equalsIgnoreCase(p[1]) ||id.equalsIgnoreCase(p[2])){
			if(fakturaDao.findById(idi) != null){
				rb = Response.ok(fakturaDao.findById(idi));
			}else{
				rb = Response.status(Status.NOT_FOUND);
			}
		}else{
			rb = Response.status(Status.FORBIDDEN);
		}

		return rb.build();
	}

	//	4. GET <url_kupca>/partneri/<id_dobavljaca>/fakture/<id_fakture>/stavke
	//	a) Omoguæava pribavljanje svih stavki date fakture datog dobavljaèa. 
	//	b) Ukoliko dati dobavljaè jeste poslovni partner kupca i postoji data faktura, odgovor je HTTP 
	//	200 OK, sa kolekcijom stavki u telu HTTP odgovora.
	//	c) U suprotnom sluèaju odgovor je HTTP 404 Not Found.
	@GET
	@Path("/{id}/fakture/{id_i}/stavke")
	@Produces(MediaType.APPLICATION_XML)
	public Response getInvoiceItems(@PathParam("id") String id, @PathParam("id_i") long idi) throws JAXBException, IOException{
		ResponseBuilder rb;
		if(id.equalsIgnoreCase(p[0]) || id.equalsIgnoreCase(p[1]) ||id.equalsIgnoreCase(p[2])){
			if(fakturaDao.findById(idi) != null){
				rb = Response.ok(fakturaDao.findAll());//PROMENI U findAllItems kada se napravi!!!!
			}else{
				rb = Response.status(Status.NOT_FOUND);
			}
		}else{
			rb = Response.status(Status.FORBIDDEN);
		}
		return rb.build();
	}

	//	5. POST <url_kupca>/partneri/<id_dobavljaca>/fakture/<id_fakture>/stavke
	//	a) Omoguæava dodavanje nove stavke u okviru postojeæe fakture datog dobavljaèa. 
	//	b) Ukoliko dati dobavljac jeste poslovni partner kupca, a data faktura postoji, odgovor je 
	//	HTTP 201 Created, sa zaglavljem Content-Location: 
	//	/partneri/<id_dobavljaca>/fakture/<id_fakture>/stavke/<red_br> koje ukazuje na URL 
	//	novododate stavke.
	//	c) U sluèaju da dobavljaè nije poslovni partner kupca, odgovor je HTTP 403 Forbidden.
	//	d) U sluèaju nepostojeæe fakture, odgovor je HTTP 404 Not Found.
	//	e) U sluèaju neispravne stavke, odgovor je HTTP 400 Bad Request.	
	@POST
	@Path("/{id}/fakture/{id_i}/stavke")
	@Produces(MediaType.APPLICATION_XML)
	public Response createInvoiceItem(@PathParam("id") String id, @PathParam("id_i") long idi, StavkaFakture sf) throws URISyntaxException{
		ResponseBuilder rb=null;
		String result = "";		
		//NISAM SIGURAN NI U STA OVDE
		try{
			if(id.equalsIgnoreCase(p[0]) || id.equalsIgnoreCase(p[1]) ||id.equalsIgnoreCase(p[2])){
				result =fakturaDao.findById(idi).toString(); //fakturaDao.addInvoiceItem(id_fakture, newInvoiceItem);
				if(result.equalsIgnoreCase("404")){
					rb = Response.status(Status.NOT_FOUND);
				}else if(result.equalsIgnoreCase("403")){
					rb = Response.status(403);
				}else if(result.equalsIgnoreCase("201")){
					rb = Response.created(new URI("/partneri/"+id+"/fakture/"+idi+"/stavke/"+sf.getRedniBroj()));//.type("application/xml").entity(newInvoiceItem).build();
				}else{
					rb= Response.status(Status.BAD_REQUEST);
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

	//	6. GET <url_kupca>/partneri/<id_dobavljaca>/fakture/<id_fakture>/stavke/<red_br>
	//	a) Omoguæava pribavljanje pojedinaène stavke date fakture datog dobavljaèa. 
	//	b) Parametar <red_br> predstavlja redni broj stavke u okviru fakture.
	//	c) Ukoliko dati dobavljaè jeste poslovni partner kupca, a data stavka u okviru date fakture 
	//	postoji, odgovor je HTTP 200 OK sa konkretnom stavkom u telu HTTP odgovora.
	//	d) U suprotnom sluèaju odgovor je HTTP 404 Not Found.
	@GET
	@Path("/{id}/fakture/{id_i}/stavke/{r_br}")
	@Produces(MediaType.APPLICATION_XML)
	public Response getInvoiceItemByNo(@PathParam("id") String id, @PathParam("id_i") long idi,@PathParam("r_br") long rbr){
		ResponseBuilder rb =null;
		StavkaFakture res = null;
		try {
			if(id.equalsIgnoreCase(p[0]) || id.equalsIgnoreCase(p[1]) ||id.equalsIgnoreCase(p[2])){
				res = fakturaDao.findItemInInvoice(idi, rbr);//.getInvoiceItemByIdFromInvoice(id_fakture, redni_broj);
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
	//	a) Omoguæava izmenu postojeæe stavke u okviru date fakture datog dobavljaèa. 
	//	b) Ukoliko dati dobavljac jeste poslovni partner kupca, a data stavka u okviru date fakture 
	//	postoji, odgovor je HTTP 200 OK.
	//	c) U sluèaju da dobavljaè nije poslovni partner kupca, odgovor je HTTP 403 Forbidden.
	//	d) U sluèaju nepostojeæe fakture ili stavke, odgovor je HTTP 404 Not Found.
	//	e) U sluèaju neispravne stavke, odgovor je HTTP 400 Bad Request.
	@PUT
	@Path("/{id}/fakture/{id_i}/stavke/{r_br}")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateInvoiceItem(@PathParam("id") String id, @PathParam("id_i") long idi,@PathParam("r_br") long rbr, StavkaFakture sf){
		ResponseBuilder rb =null;
		String res = null;
		try {
			if(id.equalsIgnoreCase(p[0]) || id.equalsIgnoreCase(p[1]) ||id.equalsIgnoreCase(p[2])){
				res = fakturaDao.findItemInInvoice(idi, rbr).toString();//.modifyInvoiceItemFromInvoice(sf, id_fakture, redni_broj);
				if(res.equalsIgnoreCase("200")){
					rb = Response.ok().type("application/xml").entity(sf); //proveri ti za svaki slucaj
				}else if(res.equalsIgnoreCase("404")){
					rb = Response.status(Status.NOT_FOUND);
				}else if(res.equalsIgnoreCase("403")){
					rb = Response.status(Status.FORBIDDEN);
				}else if(res.equalsIgnoreCase("400")){
					rb= Response.status(Status.BAD_REQUEST);
				}else{
					rb = Response.status(Status.INTERNAL_SERVER_ERROR);//to da znam da sam zeznuo
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
	//	a) Omoguæava brisanje pojedinaène stavke date fakture datog dobavljaèa. 
	//	b) Ukoliko dati dobavljaè jeste poslovni partner kupca, a data stavka u okviru date fakture 
	//	postoji, odgovor je HTTP 204 No Content.
	//	c) U sluèaju da dobavljaè nije poslovni partner kupca, odgovor je HTTP 403 Forbidden.
	//	d) U suprotnom sluèaju (faktura ili stavka ne postoji) odgovor je HTTP 404 Not Found.
	@DELETE
	@Path("/{id}/fakture/{id_i}/stavke/{r_br}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteInvoiceItem(@PathParam("id") String id, @PathParam("id_i") long idi,@PathParam("r_br") long rbr){
		ResponseBuilder rb=null;
		String res = "";
		try{
			if(id.equalsIgnoreCase(p[0]) || id.equalsIgnoreCase(p[1]) ||id.equalsIgnoreCase(p[2])){
				res = fakturaDao.findById(idi).toString();//.removeInvoiceItemByIdFromInvoice(id_fakture, redni_broj);
				if(res.equalsIgnoreCase("204")){
					rb = Response.status(Status.NO_CONTENT); 
				}else if(res.equalsIgnoreCase("404")){
					rb = Response.status(Status.NOT_FOUND);
				}else if(res.equalsIgnoreCase("403")){
					rb = Response.status(Status.FORBIDDEN);
				}else{
					rb = Response.status(Status.INTERNAL_SERVER_ERROR);//to da znam da sam zeznuo
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

}
