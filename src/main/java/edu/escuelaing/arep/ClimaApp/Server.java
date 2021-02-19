  
package edu.escuelaing.arep.ClimaApp;

import static spark.Spark.*;

import java.util.HashMap;
import java.util.Map;


import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.velocity.VelocityTemplateEngine;

/**
 * Class in charge of starting the service.
 *
 */
public class Server {
	
	private static Cache cache;
	/**
	 * Main class that starts the main thread of the service.
	 * 
	 * @param args String array
	 */
	public static void main(String[] args) {
		cache = new Cache();
		port(getPort());
		get("/find", (req, res) -> {
			res.type("application/json");
			String ls_res;
			ls_res = "";
			ls_res = resultData(req, res);
			return ls_res;

		});
	}
	
	/**
	 * Specifies the port on which the service will run.
	 * 
	 * @return The port where the service will be run.
	 */
	public static int getPort() {    
		if (System.getenv("PORT") != null)
		{            
			return Integer.parseInt(System.getenv("PORT"));      
		} 
		return 4567; 
	}
	
	/**
	 * Method in charge of generating the values of the mean and the standard deviation.
	 * 
	 * @param am_model Data that will be passed to the template.
	 * @param ar_req Request received by the server
	 * @param ar_res Server response.
	 * @return Returns if it was possible to calculate the data with the information entered.
	 */
	private static String resultData(Request ar_req, Response ar_res){
		String ls_values;
		String ls_res;
		String ls_aux;
		boolean lb_resp;
		
		ls_values = ar_req.queryParams("lugar");
		if (ls_values == null)
			return "";
		ls_res = cache.getByName(ls_values);
		if(ls_res==null) {
			
			ls_res = Enlace.readURL("http://api.openweathermap.org/data/2.5/weather?q="+ls_values+"&appid=7241c511099db33048201d1e4cc20198");
			cache.add(ls_values, ls_res);
		}
		
		
		return ls_res;
	}
	
	
	
}