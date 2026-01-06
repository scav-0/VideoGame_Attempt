package main;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//clicking x actually exits it
		window.setResizable(false);
		window.setTitle("Chickens 5");
		
		GamePanel gamePanel = new GamePanel();
		window.add(gamePanel);
		
		window.pack();//Cause this window to be sized to fir the preferred size and layout of its subcomponents
		
		window.setLocationRelativeTo(null);//game window will be in the centre of the screen
		window.setVisible(true);
		
		gamePanel.setUpGame();
		gamePanel.startGameThread();

	}

}
