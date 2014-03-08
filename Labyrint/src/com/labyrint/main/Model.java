package com.labyrint.main;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class Model {
	private ArrayList<String> lines = new ArrayList<String>();
	private static ArrayList<Zed> zdi;
	private static int levels = 1;
	private String line;
	private Reader in;
	private BufferedReader br;
	private static Exit exit;
	private static Player player = new Player(33,33);
	public Model(){
		levels();
	}
	// InputStream
	public void levels() {
		try {
			if (levels < 3) {
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
		zdi = new ArrayList<Zed>();
		for (int y = 0; y < lines.size(); y++) {
			for (int x = 0; x < lines.get(y).length(); x++) {
				String brick = String.valueOf(lines.get(y).charAt(x));
				if (brick.contains("#")) {
					int x2 = x * 32;
					int y2 = y * 32;
					zdi.add(new Zed(x2, y2));
					System.out.println(x2 + " a " + y2);
				} else if (brick.contains("!")) {
					int x2 = x * 32;
					int y2 = y * 32;
					System.out.println(x2 + " a " + y2);
					System.out.println(brick);
					exit = new Exit(x2,y2);
				}

			}
		}
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
	public void setNewLevel() {
		// TODO Auto-generated method stub
		levels += 1;
		player.setX(40);
		player.setY(40);
		zdi.clear();
		lines.clear();
		levels();
	}
}
