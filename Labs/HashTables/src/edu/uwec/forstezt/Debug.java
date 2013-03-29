package edu.uwec.forstezt;

public class Debug {

	private static boolean debugging_is_on;
	
	public static void println(String msg){
		if (debugging_is_on) {
		System.out.println("DEBUGGING:  " + msg);
		}
	}
	
	public static void turnOnMessages(){
		System.out.println("DEBUGGING:  Debugging messages are turned ON.\n");
		debugging_is_on = true;
	}
	
	public static void turnOffMessages() {
		System.out.println("DEBUGGING:  Debugging messages are turned OFF.\n");
		debugging_is_on = false;
	}
}
