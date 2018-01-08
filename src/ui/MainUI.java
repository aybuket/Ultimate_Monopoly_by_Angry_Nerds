package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class MainUI extends JPanel implements VariablesUI{


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				createOpeningFrame();
				opening();
				ButtonsUI.getInstance().actionListeners();
				playGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						openingFrame.dispose();
						createFrame();
						controllerInstance.createPlayers();
						mainFrame.add(game);
						game.startGame();
					}
				});
				exitGame.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						mainFrame.dispose();
					}
				});
			}
		});
	}
	
	public static void registerPlayer() {
		registerPlayers.setVisible(true);
		registerPlayers.setBackground(new Color(204,232,255));
		registerPlayers.setSize(600, 400);
		registerPlayers.setResizable(false);
		
		
	}

	public static void createOpeningFrame() {
		openingFrame.setLocation(200, 60);
		openingFrame.setVisible(true);
		openingFrame.setBackground(new Color(204,232,255));
		openingFrame.setSize(1265, 950);
		openingFrame.setResizable(false);
		openingFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void createFrame() {
		mainFrame.setVisible(true);
		mainFrame.setBackground(background);
		mainFrame.setSize(new Dimension(1410,950));
		mainFrame.setResizable(false);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainFrame.setLocation(150, 60);
	}
	public static void opening() {
		openingFrame.setContentPane(new JLabel(new ImageIcon("images/angrynerds.png")));
		openingFrame.add(play);
		play.setBackground(new Color(204,232,255));
		play.setLocation(500,305);
		play.setSize(170,100);
		play.setVisible(true);
		play.add(playGame);
		play.add(loadGame);
		play.add(exit);
	}
}