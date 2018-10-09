package br.com.udemycurso.resources.utils;

import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

public class URL {

	
	
	public static String decodeString(String s) {
		try {
			return URLDecoder.decode(s , "UTF-8");
		} catch (Exception e) {
			return "";
			
		}
		
		
	}
	
	public static List<Long> decodeLongList(String s){
		
		String[] vet = s.split(",");
		
		List<Long> longs= new ArrayList<>();
		
		for(int i = 0 ; i < vet.length ; i++) {
			
			longs.add(Long.parseLong(vet[i]));
			
		}
			
		return longs;
	}
}
