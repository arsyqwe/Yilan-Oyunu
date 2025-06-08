package SnakeGame;

import java.awt.Frame;

import javax.swing.*;
public class Snakegame {

	public static void main(String[] args) {
		int boardWidth = 600;
		int boardHeight = boardWidth;
		
		JFrame frame = new JFrame("Yılan Oyunu");
		frame.setVisible(true);
		frame.setSize(boardHeight,boardWidth);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		Jframepanel yılan = new Jframepanel(boardWidth , boardHeight);
		frame.add(yılan);
		frame.pack();
		yılan.requestFocus();
		 
			

	}

}
