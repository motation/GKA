package main;

import gui.StartWindow;

import java.io.IOException;

import javax.swing.JFrame;

public class MainApplication {
	public static void main(String[] args) throws IOException {
		JFrame frame = new StartWindow();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(768,500);
		frame.setVisible(true);
	}
}
