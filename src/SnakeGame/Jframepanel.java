package SnakeGame;

import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Random;
import java.util.random.*;
import javax.swing.*;
 

public class Jframepanel extends JPanel implements ActionListener , KeyListener{
	int boardWidth;
	int boardHeight;
	int tileSize = 25;
	
	
	Jframepanel(int boardWidht , int boardHeight) {
		this.boardHeight = boardHeight;
		this.boardWidth = boardWidht;
		setPreferredSize(new Dimension(this.boardHeight , this.boardWidth));
		setBackground(Color.black);
		addKeyListener(this);
		setFocusable(true);
		sneakBody = new ArrayList<Tile>();
		
		sneakHead = new Tile(5,5);
		food = new Tile(10 , 10);
		placeFood();
		placeSneak();
		velocityX = 0;
		velocityY = 0;
		
		
		gameloop = new Timer(100 , this);
		gameloop.start();
		
	}
	Tile sneakHead;
	Tile food;
	
	ArrayList<Tile>	sneakBody;
	
	Random random = new Random();
	
	Timer gameloop;
	int velocityX;
	int velocityY;
	boolean gameover = false;
	
	
	private class Tile
	{
		int x;
		int y;		
		Tile(int x , int y){
			this.x=x;
			this.y=y;
		}		
	}
	
	public boolean collusion(Tile tile1 , Tile tile2) {
		return tile1.x == tile2.x && tile1.y == tile2.y;
	}
	
	public void paintComponent (Graphics g) {
		super.paintComponent(g);
		draw(g);
	}
	public void draw(Graphics g) {
		//food
		g.setColor(Color.red);
		g.fillRect(food.x*tileSize, food.y*tileSize, tileSize, tileSize);
		//snake
		g.setColor(Color.green);
		g.fillRect(sneakHead.x*tileSize, sneakHead.y*tileSize, tileSize, tileSize);
		
		//snakebody 
		for ( int i = 0 ; i < sneakBody.size() ; i++) {
			Tile sneakpart = sneakBody.get(i);
			g.fillRect(sneakpart.x*tileSize, sneakpart.y*tileSize, tileSize, tileSize);
		}
		
		g.setFont(new Font("Arial", Font.PLAIN , 16 ) );
		if (gameover) {
			g.setColor(Color.red);
			g.drawString("Game Over : " + String.valueOf(sneakBody.size()) , tileSize - 16 , tileSize);
		}else {
			g.drawString("Score : "+ String.valueOf(sneakBody.size()), tileSize - 16 , tileSize );
		}
			
	}
	
	public void placeFood() {
		food.x=random.nextInt(boardWidth/tileSize);
		food.y=random.nextInt(boardHeight/tileSize);
		
	}

	public void placeSneak() {
		sneakHead.x=random.nextInt(boardWidth/tileSize);
		sneakHead.y=random.nextInt(boardHeight/tileSize); 
	}
	
	public void move() {
		if (collusion(sneakHead, food)) {
			sneakBody.add(new Tile(food.x, food.y));
			placeFood();
		}
		//sneak body
		for (int i = sneakBody.size() - 1 ; i>=0 ; i-- ) {
			Tile sneakpart = sneakBody.get(i);
			if (i==0 ) {
				sneakpart.x = sneakHead.x;
				sneakpart.y = sneakHead.y;
			}else {
				Tile prevSneakPart =  sneakBody.get(i-1);
				sneakpart.x = prevSneakPart.x;
				sneakpart.y = prevSneakPart.y;
						
			}
		}
		
		//sneakhead
		sneakHead.x += velocityX;
		sneakHead.y += velocityY;
		
		//game over conditions
		for (int i = 0 ; i< sneakBody.size(); i++) {
			Tile sneakpart = sneakBody.get(i);
			//kendine çarpması 
			if(collusion(sneakHead, sneakpart)){
				gameover= true;
			}
		}
		if (sneakHead.x*tileSize<0 || sneakHead.x * tileSize > boardWidth|| sneakHead.y*tileSize < 0 || sneakHead.y*tileSize
				>boardHeight) {
			gameover = true;
		}
		
	
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		move();
		repaint();
		if (gameover) {
			gameloop.stop();
		}
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_UP && velocityY != 1) {
			velocityX = 0;
			velocityY = -1 ;
		}else if(e.getKeyCode() == KeyEvent.VK_DOWN && velocityY != -1) {
			velocityX = 0;
			velocityY = 1;
		}else if (e.getKeyCode() == KeyEvent.VK_RIGHT&& velocityX != -1){
			velocityX = 1;
			velocityY = 0;
		}else if(e.getKeyCode() == KeyEvent.VK_LEFT&& velocityX != 1) {
			velocityX = -1;
			velocityY = 0;
		}
		
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	
	
	
	

}
