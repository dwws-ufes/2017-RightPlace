package br.ufes.inf.nemo.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.ResourceFactory;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.vocabulary.RDFS;

import br.ufes.inf.nemo.marvin.core.domain.Place;
import br.ufes.inf.nemo.marvin.core.persistence.PlaceDAO;

@WebServlet(urlPatterns = "/data/places")
public class PlaceRdfServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final Logger logger = Logger.getLogger(PlaceRdfServlet.class.getCanonicalName());

	private static final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

	@EJB
	private PlaceDAO placeDAO;

	public PlaceRdfServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/xml");

		List<Place> placeList = placeDAO.retrieveAll();

		Model model = ModelFactory.createDefaultModel();

		String myNS = "http://localhost:8080/Rightplace-1/data/places/";
		String grNS = "http://purl.org/obo/goodrelations/v1#";

		model.setNsPrefix("gr", grNS);
		
		Resource grLocation = ResourceFactory.createResource(grNS + "Location");
		Property grdescription = ResourceFactory.createProperty(grNS +
				"description");
				
				for (Place place : placeList) {

					model.createResource(myNS + place.getId())
							.addProperty(RDF.type, grLocation)
						 	.addProperty(grdescription, place.getDescription())
							.addProperty(RDFS.label, place.getName()) ;
						}
		try (PrintWriter out = response.getWriter()) {
			model.write(out, "RDF/XML");
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}