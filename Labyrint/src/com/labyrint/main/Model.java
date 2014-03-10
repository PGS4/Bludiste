package com.labyrint.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

import com.labyrint.entities.Enemy;
import com.labyrint.entities.Exit;
import com.labyrint.entities.Player;
import com.labyrint.entities.Zed;

public class Model {
	private static ArrayList<String> lines = new ArrayList<String>();
	private static ArrayList<Zed> zdi = new ArrayList<Zed>();;
	private static int levels = 1;
	private String line;
	private Reader in;
	private BufferedReader br;
	private static Exit exit;
	private static Player player = new Player(33,33);
	private static int lives = 4;
	private static ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	public Model(){
	}
	// InputStream
	public void levels() {
		try {
			if (levels < 4) {
				in = new InputStreamReader(this.getClass().getResourceAsStream(
						"/level" + levels + ".txt"));
				br = new BufferedReader(in);
				while ((line = br.readLine()) != null) {
					lines.add(line);
				}
				initLevel();
				in.close();
			}

		} catch (Exception e) {
			System.err.println("Error: " + e.getMessage());
		}

	}

	public void initLevel() {
		for (int y = 0; y < lines.size(); y++) {
			for (int x = 0; x < lines.get(y).length(); x++) {
				String brick = String.valueOf(lines.get(y).charAt(x));
				if (brick.contains("#")) {
					int x2 = x * 32;
					int y2 = y * 32;
					zdi.add(new Zed(x2, y2));
				} else if (brick.contains("!")) {
					int x2 = x * 32;
					int y2 = y * 32;
					exit = new Exit(x2,y2);
				} else if (brick.contains("E")) {
					int x2 = x*32;
					int y2 = y*32;
					enemies.add(new Enemy(x2,y2, "Enemy" + x + y));
				}

			}
		}
	}
	public void playerDead(){
		lives -= 1;
		player.setX(33);
		player.setY(33);
		if(lives == 0){
			endGame();
		}
	}
	public static int getLives(){
		return lives;
	}
	public static ArrayList<String> getLines(){
		return lines;
	}
	public static ArrayList<Enemy> getEnemies(){
		return enemies;
	}
	public static int getLevels(){
		return levels;
	}
	public static void setLevels(int level){
		levels=level;
	}
	public static Player getPlayer(){
		return player;
	}
	public static Exit getExit(){
		return exit;
	}
	public static ArrayList<Zed> getZdi(){
		return zdi;
	}
	public void endGame(){
		zdi.clear();
		enemies.clear();
		lines.clear();
	}
	public void setNewLevel() {
		levels += 1;
		player.setX(40);
		player.setY(40);
		zdi.clear();
		enemies.clear();
		lines.clear();
		levels();
	}
}
