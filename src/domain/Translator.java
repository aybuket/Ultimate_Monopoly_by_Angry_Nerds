package domain;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import domain.game.MonopolyTheGame;
import domain.game.Player;

public class Translator {

	static Translator instance;
	BufferedWriter writer;
	BufferedReader reader;
	public static Translator getInstance(){
		if(instance==null){
			instance= new Translator();
		}
		return instance;
	}
	
	private Translator() {};

	public void saveGame(String filename){

		try {
			writer= new BufferedWriter(new FileWriter(filename+".txt"));

			Player[] players= MonopolyTheGame.getInstance().getPlayers();
			int activePlayer= MonopolyTheGame.getInstance().getActivePlayerIndex();
			writer.write(players.length+"\n");
			writer.write(activePlayer+"\n");
			for(int player=0; player<players.length; player++){
				writer.write(players[player].getLocation()+" ");
				writer.write(players[player].getName()+" ");
				writer.write(players[player].getDirection()+" ");
				writer.write((players[player].isJailed()?1:0)+" ");
				writer.write(players[player].getBalance()+" ");
				for(Integer asset : players[player].getAssets()){
					writer.write(asset+" ");
					writer.write(MonopolyTheGame.getInstance().getBoard().getCurrentSquare(asset).getLevel()+" ");
				System.out.println(MonopolyTheGame.getInstance().getBoard().getCurrentSquare(asset).getLevel()+" zaaaaaa");
				}
				
				writer.newLine();
			}


			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public void loadGame(String filename){

		try {
			reader= new BufferedReader(new FileReader(filename+".txt"));
			String firstLine=reader.readLine();
			int numPlayers= Integer.parseInt(firstLine);
			String secondLine=reader.readLine();
			int activePlayer= Integer.parseInt(secondLine);
			int[] indices=new int[numPlayers];
			int[] directions= new int[numPlayers];
			boolean[] jailed= new boolean[numPlayers];
			String[] names= new String[numPlayers];
			int[] balances= new int[numPlayers];
			ArrayList<Integer>[] assets= new ArrayList[numPlayers];
			ArrayList<Integer>[] assetLevels= new ArrayList[numPlayers];
			for(int player=0; player<numPlayers; player++){
				String info= reader.readLine();

				StringTokenizer token= new StringTokenizer(info);
				indices[player]=Integer.parseInt(token.nextToken());
				//System.out.println(player+ " "+indices[player]);
				names[player]=token.nextToken();
				directions[player]=Integer.parseInt(token.nextToken());
				jailed[player]= Integer.parseInt(token.nextToken())==1;
				balances[player]=Integer.parseInt(token.nextToken());
				assets[player]=new ArrayList<Integer>();
				assetLevels[player]=new ArrayList<Integer>();
				while(token.hasMoreTokens()){
					System.out.println("heey");
					assets[player].add(Integer.parseInt(token.nextToken()));	
					assetLevels[player].add(Integer.parseInt(token.nextToken()));
				}
				//PlayerUI.move(player,indices[player]);

			}
			MonopolyTheGame.getInstance().getBoard().resetBoard();
			MonopolyTheGame.getInstance().createLoadedPlayers(names, indices, directions,jailed, balances, assets,assetLevels);
			MonopolyTheGame.getInstance().setActivePlayerIndex(activePlayer);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
