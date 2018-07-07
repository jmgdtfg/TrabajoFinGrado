package rest.service;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

import jackson.JacksonUtils;
import pipeline.Pipeline;
import pipeline.Response;


@Path("/")
public class WebService {

    public static void main(String[] args) {        
        final String baseUri = "http://localhost:7084/";
        final Map<String, String> initParams = new HashMap<String, String>();

        // Register the package that contains your javax.ws.rs-annotated beans here
        initParams.put("com.sun.jersey.config.property.packages","rest.service");

        System.out.println("Starting grizzly...");
        try {
            @SuppressWarnings("unused")
			SelectorThread threadSelector =
                    GrizzlyWebContainerFactory.create(baseUri, initParams);
            System.out.println(String.format("Jersey started with WADL "
                    + "available at %sapplication.wadl.", baseUri));
           
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Función que devuelve los elementos de entrada
     * @return
     */
    @GET
    @Path("/getInputs")
    @Produces(MediaType.TEXT_PLAIN)
    public String inputs() {
    	AvailableElementsData elements = new AvailableElementsData();
    	Map<String, String> map = elements.getInputs_();
    	StringBuilder response = new StringBuilder();
    	for (Map.Entry<String, String> entry : map.entrySet()){
    	    response.append(entry.getKey());
    	    response.append(" | ");
    	    response.append(entry.getValue());
    	    response.append("\n");
    	}
        return response.toString();
    }
    /**
     * Función que devuelve los elementos de procesamiento
     * @return
     */
    @GET
    @Path("/getProcessing")
    @Produces(MediaType.TEXT_PLAIN)
    public String processing() {
    	AvailableElementsData elements = new AvailableElementsData();
    	Map<String, String> map = elements.getProcess_();
    	StringBuilder response = new StringBuilder();
    	for (Map.Entry<String, String> entry : map.entrySet()){
    	    response.append(entry.getKey());
    	    response.append(" | ");
    	    response.append(entry.getValue());
    	    response.append("\n");
    	}
        return response.toString();
    }
    
    /**
     * Función que devuelve los elementos de salida
     * @return
     */
    @GET
    @Path("/getOutputs")
    @Produces(MediaType.TEXT_PLAIN)
    public String outputs() {
    	AvailableElementsData elements = new AvailableElementsData();
    	Map<String, String> map = elements.getOutputs_();
    	StringBuilder response = new StringBuilder();
    	for (Map.Entry<String, String> entry : map.entrySet()){
    	    response.append(entry.getKey());
    	    response.append(" | ");
    	    response.append(entry.getValue());
    	    response.append("\n");
    	}
        return response.toString();
    }
    
    /**
     * Función que envía una petición en formato JSON
     * @param json
     * @return
     */
    @POST
    @Path("/sendRequest")
    @Produces(MediaType.TEXT_PLAIN)
    public String request(String json) {
    	
    	Response response = new Response();
    	JacksonUtils.deserialize(json);
    	Pipeline pipeline = JacksonUtils.deserialize(json);
    	response = pipeline.execute();
        return response.getResult_();

    }
}
