package tests;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class Airport implements Comparable<Airport>{
	private String code; 
	private String country;
	private String city;
	
	public String toString() {
		return this.city;
	}
	
	public Airport(String city, String country, String code) {
		this.code = code;
		this.country = country; 
		this.city = city;
	}
	
	public String getCity() {
		return this.city;
	}
	
	public static void selectionSort(Airport[] apts) {
		int n = apts.length;
		int indexMin;

		for ( int i=0; i < n - 1; i++) {
			indexMin = i;
			for (int j=i+1; j < n; j++) {
				if(apts[j].compareTo(apts[indexMin]) < 0) {
					indexMin = j;
				}
			}
			swap(apts, indexMin, i);
		}
		
	}
	
	public static String findAirportCode(String toFind, Airport[] airports) {
		for(int i=0; i < airports.length; i++) {
			if(airports[i].getCity().equals(toFind)) {
				return airports[i].getCode(); 
			}
		}
		return null;
	}
	
	public static boolean swap(Airport[] apts, int ind1, int ind2) {
		if(ind1 >= apts.length || ind2 >= apts.length) {
			System.out.println("Out of bound swap method");
			return false;
		}
		Airport temp = apts[ind1];
		apts[ind1] = apts[ind2];
		apts[ind2] = temp;
		return true;
	}
	
	public static String BS(String toFind, Airport[] apts){
		int start = 0;
		int end = apts.length - 1;
		while (start <= end) {
			int mid = (start + end) / 2;
			Airport curr = apts[mid];
			int compare = toFind.compareTo(curr.city);
			if (curr.city.equals(toFind)){
				return curr.code;
			} else if (compare < 0) { 
				end = mid - 1;
			} else {
				start = mid + 1;
			}
		}
		
		return null;
	}
	
	private String getCode() {
		return this.code;
	}
	
	
	
	public static void main(String[] args) {
		String s = "hello";
		String h = " World";
		String hello = s + h;
		System.out.println(hello);
		
		
		Airport a = new Airport("Tel-Aviv", "Israel", "TLV");
		Airport b = new Airport("Paris", "France", "CDG");
		Airport c = new Airport("Budapest", "Hungaria", "BDP");
		Airport d = new Airport("Bxl", "Belgium", "ZVM");
		Airport e = new Airport("Argentina City", "Argentina", "ARG");
		
		Airport[] apts = new Airport[5];
		apts[0] = a;
		apts[1] = b;
		apts[2] = c;
		apts[3] = d;
		apts[4] = e;
		
		List<Airport> l = new ArrayList<Airport>();
		l.add(a);
		l.add(b);
		l.add(e);
		
		
//		Arrays.sort(apts);
		Collections.sort(l);
		selectionSort(apts);
		System.out.println(Arrays.toString(apts));
		String cty = apts[2].getCity(); 
		
		System.out.println(findAirportCode(cty, apts));
		System.out.println(BS(cty, apts));
		System.out.println(0.2 + 0.1);
		
	}

	@Override
	public int compareTo(Airport o) {
		return this.getCity().compareTo(o.getCity());
	}

}
