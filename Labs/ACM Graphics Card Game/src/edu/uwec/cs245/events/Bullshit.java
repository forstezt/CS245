package edu.uwec.cs245.events;

import java.awt.Color;

import acm.graphics.*;
import acm.program.*;

public class Bullshit extends GraphicsProgram{
	
	public void run() {
		GRect rect = new GRect(250, 400, 50, 70);
		rect.setFilled(true);
		rect.setColor(Color.BLUE);
		add(rect);
		
	}
	
	public static void main(String[] args) {
		new Bullshit().start(args);
	}
	
}
