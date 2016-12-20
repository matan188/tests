package tests;

import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class Recursions {
	
	public static void permutations(final String str) {
		int n = str.length();
		boolean[] used = new boolean[n];
		StringBuilder out = new StringBuilder(str.length());
		Set<String> memo = new TreeSet<String>();
		permHelp(str, out, used, memo);
	}
	
	private static void permHelp(final String str, StringBuilder out, boolean[] used, Set<String> memo) {
		for (int i=0; i < used.length; i++) {
			if (used[i]) {
				continue;
			}
			out.append(str.charAt(i));
			used[i] = true;
			if(out.length() == used.length){
				String test = new String(out);
				if (!memo.contains(test)) {
					System.out.print(test + " ");
					memo.add(test);
				}
			}else {
				permHelp(str, out, used, memo);
			}
			out.setLength(out.length()-1);
			used[i] = false;
		}
	}
	
	public static void combinations(final String str) {
		StringBuffer out = new StringBuffer(str.length());
		HashSet<String> memo = new HashSet<String>();
		combHelp(str, out, 0, memo);
	}
	
	private static void combHelp(final String str, StringBuffer out, int start, HashSet<String> memo) {
		for (int i=start; i < str.length()-1; i++) {
			out.append(str.charAt(i));
			String test = new String(out);
			if (!memo.contains(test)) {
				System.out.println(out);
				memo.add(test);
			}
			
			combHelp(str, out, i+1, memo);
			out.setLength(out.length()-1);
		}
		
		
		out.append(str.charAt(str.length()-1));
		String s = new String(out);
		if (!memo.contains(s)) {
			System.out.println(out);
			memo.add(s);
		}
		
		out.setLength(out.length()-1);
	}
	
	public static void main(String[] args) {
		combinations("aabc");
	}

}
