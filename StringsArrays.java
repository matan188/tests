package tests;

import java.awt.image.ReplicateScaleFilter;

public class StringsArrays {
	
	public static boolean anagram(String s, String t) {
		if (s.length() != t.length()) return false;
		int[] letters = new int[256];
		int num_unique_chars = 0;
		int num_completed_t = 0;
		char[] s_array = s.toCharArray();
		for (char c : s_array) {
			if (letters[c] == 0) ++num_unique_chars;
			++letters[c];
		}
		
		for (int i=0; i < t.length(); ++i) {
			int c = t.charAt(i);
			if (letters[c] == 0) {
				return false;
			}
			--letters[c];
			if (letters[c] == 0){
				++num_completed_t;
				if (num_completed_t == num_unique_chars) {
					return i == t.length() - 1;
				}
			}
		}
		return false;
	}
	
	public static char[]  replaceSpaces(char[] str) {
		int spacesNum = 0;
		for (char c : str) {
			if (c==' ') ++spacesNum;
		}
		int newLength = str.length + spacesNum*2;
		char[]  out = new char[newLength];
		for (int i=str.length-1; i >= 0; i--) {
			if (str[i] == ' ') {
				out[--newLength] = '0';
				out[--newLength] = '2';
				out[--newLength] = '%';
			} else {
				out[--newLength] = str[i];
			}
		}
		return out;
	}
	
	public static void rotateMatrix(int[][] matrix) {
		if (matrix.length == 0 || matrix.length!=matrix[0].length) return;
		
	}
	
	public static void combinations(String str) {
		StringBuilder sb = new StringBuilder();
		combHelp(str, 0, sb);
	}
	
	public static void combHelp(String str, int ind, StringBuilder sb) {
		for (int i= ind; i < str.length(); i++) {
			sb.append(str.charAt(i));
			
			System.out.println(sb);
			if (i < str.length()) {
				combHelp (str, i+1, sb);
			}
			sb.setLength(sb.length()-1);
		}
	}
	
	public static void main(String[] args) {
		String s = "Good morning lads";
		char[] arr = null;
		
		combinations("abc");
	}
}
