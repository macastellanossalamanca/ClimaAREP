package edu.escuelaing.arep.ClimaApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

public class Enlace {
	
	/**
	 * Reads an response from the server
	 * 
	 * @param as_site url to read
	 * @return A string with the body response 
	 */
	public static String readURL(String as_site) {
		String ls_resData;
		ls_resData = null;
		try {
			URL lu_siteURL;

			lu_siteURL = new URL(as_site);

			if (lu_siteURL != null) {
				URLConnection luc_urlConnection;

				luc_urlConnection = lu_siteURL.openConnection();

				if (luc_urlConnection != null) {
					InputStreamReader lis_input;

					lis_input = new InputStreamReader(luc_urlConnection.getInputStream());

					if (lis_input != null) {
						BufferedReader lbr_reader;

						lbr_reader = new BufferedReader(lis_input);

						if (lbr_reader != null) {
							String ls_line;

							ls_line = null;

							ls_resData = "";

							while ((ls_line = lbr_reader.readLine()) != null)
								ls_resData += ls_line;

						}
					}

				}
			}
			ls_resData = getWeather(ls_resData);
		} catch (IOException x) {
			ls_resData = "";
			x.printStackTrace();
		}
		
		return ls_resData;
	}
	
	public static String getWeather(String a) {
		JSONObject jsonObject = new JSONObject(a);
		
		
		return jsonObject.get("weather").toString();
	}
}
