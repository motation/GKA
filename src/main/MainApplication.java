package main;

import gui.StartWindow;
import io.FileGraphReader;

import java.io.IOException;

import javax.swing.JFrame;

public class MainApplication {
	public static void main(String[] args) throws IOException {
		JFrame frame = new StartWindow();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(768,500);
		frame.setVisible(true);
		
//		String pattern = "^([a-zA-Z0-9]+).*?,([a-zA-Z0-9]+)[:]{0,2}.*?$|^([a-zA-Z0-9]+)[:]{0,2}.*?$";
//		if("d:3".matches(pattern)){
//			System.out.println("d:3".replaceAll(pattern, "$3"));
//		}
	}
}
