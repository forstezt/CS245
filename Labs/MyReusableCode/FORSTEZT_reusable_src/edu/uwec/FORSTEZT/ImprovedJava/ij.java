package edu.uwec.FORSTEZT.ImprovedJava;

public class ij {
	
	public static final char QUOTE = '\"';
	public static final char NEWLINE = '\n';
	
	public static String Quoted(String string) {
		return QUOTE + string + QUOTE;
	}
	
	public static boolean NOT(Boolean expression) {
		return !expression;
	}
	
	public static void println(String s) {
		System.out.println(s);
	}
	
	public static void println() {
		System.out.println("");
	}
	
	public static void print(String s) {
		System.out.print(s);
	}
}
