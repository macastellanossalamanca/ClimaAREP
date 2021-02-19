package edu.escuelaing.arep.ClimaApp;

import java.util.HashMap;

public class Cache {
	
	private HashMap<String,String> info;
	
	public Cache (){
		
		info = new HashMap<>();
	}
	
	public void add(String nombre, String data) {
		
		info.put(nombre, data);
	}
	
	public String getByName(String nombre) {
		
		if (info.containsKey(nombre)){
			return info.get(nombre);
		}
		return null;
	}

}
