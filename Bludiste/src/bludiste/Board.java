package bludiste;


import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;


public class Board {
	
	private Player player;
	private Reader in;
	private BufferedReader br;
	private String line;
	private ArrayList<String> lines = new ArrayList<String>();
	// private ArrayList<Enemy> enemies;
	private int levels = 1;
	private Exit exit;
	private ArrayList<Zed> zdi;
	public Board() {
		
		player = new Player();
		levels();
		
	}

	
	// inicializace lvlu
			public void levels() {
				try {
					if (levels < 3) {
						in = new InputStreamReader(this.getClass().getResourceAsStream("/level" + levels + ".txt"));
						br = new BufferedReader(in);
						while((line = br.readLine()) != null){
							lines.add(line);
						}
						//level = (String[]) lines.toArray(new String[lines.size()]);
						System.out.println("vyska: " + lines.size());
						System.out.println("dylka: " + lines.get(1).length());
						initLevel();
						in.close();
					}

				} catch (Exception e) {
					System.err.println("Error: " + e.getMessage());
				}

			}
		// inicializace souøadnic
			public void initLevel() {
				zdi = new ArrayList<Zed>();
				// enemies = new ArrayList<Enemy>();
				for (int y = 0; y < lines.size(); y++) {
					for(int x = 0; x < lines.get(y).length(); x++){
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
						exit = new Exit(x2, y2);
					} 
					 /* else if (level[i].contains("E")){ int x = (i % (800 / 32)) * 32;
					  int y = ((i - (x / 32)) / (800 / 32)) * 32; enemies.add(new
					  Enemy(x, y, "E" + i)); System.out.println(x + " a " + y); }
					 */
					}
				}
			}
			public void checkCollissions() {
				Rectangle r1 = player.getBounds();
				for (int j = 0; j < zdi.size(); j++) {
					Zed azed = (Zed) zdi.get(j);
					Rectangle r2 = azed.getBounds();
					if (r1.intersects(r2)) {
						if ((player.getX() + player.getWidth() - 2) < azed.getX()) {
							player.x = player.getX() - 1;
						}
						if (player.getX() > (azed.getX() + azed.getWidth() - 2)) {
							player.x = player.getX() + 1;
						}
						if ((player.getY() + player.getHeight() - 2) < azed.getY()) {
							player.y = player.getY() - 1;
						}
						if (player.getY() > (azed.getY() + azed.getHeight() - 2)) {
							player.y = player.getY() + 1;
						}
					}
					/*
					 * for (int i = 0; i < enemies.size(); i++){ Enemy enemy = (Enemy)
					 * enemies.get(i); Rectangle r4 = enemy.getBounds(); if
					 * (r4.intersects(r2)){ enemy.dx = -enemy.dx; enemy.dy = -enemy.dy;
					 * if ((enemy.getX() + enemy.getWidth() - 2) < azed.getX()) {
					 * enemy.x = enemy.getX() - 1; } if (enemy.getX() > (azed.getX() +
					 * azed.getWidth() - 2)) { enemy.x = enemy.getX() + 1; } if
					 * ((enemy.getY() + enemy.getHeight() - 2) < azed.getY()) { enemy.y
					 * = enemy.getY() - 1; } if (enemy.getY() > (azed.getY() +
					 * azed.getHeight() - 2)) { enemy.y = enemy.getY() + 1; } } }
					 */
				}

				Rectangle r3 = exit.getBounds();
				if (r1.intersects(r3)) {
					levels += 1;
					setLevels(levels);
					player.x = 40;
					player.y = 40;
					zdi.clear();
					clearLevel();
					levels();
				}
			}
			public Exit getExit(){
				return exit;
			}
			public ArrayList<Zed> getZdi(){
				return zdi;
			}
			public int getLevels(){
				return levels;
			}
			public void setLevels(int levels){
				this.levels = levels;
			}
			public void clearLevel(){
				lines.clear();
			}
			public Player getPlayer(){
				return player;
			}
	//public void actionPerformed(ActionEvent e) {
		
		
		/*
		 * for (int i = 0; i<enemies.size(); i++){ Enemy enemy = (Enemy)
		 * enemies.get(i); enemy.notifyEnemy(); enemy.run(); }
		 */
	//}
	
	
}