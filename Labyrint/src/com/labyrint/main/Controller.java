package com.labyrint.main;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

import com.labyrint.entities.Enemy;
import com.labyrint.entities.Exit;
import com.labyrint.entities.Player;
import com.labyrint.entities.Zed;

public class Controller implements Runnable {
	private Model model;
	private Player player;
	private int levels = 0;
	private ArrayList<Zed> zdi;
	private Zed zed;
	private Rectangle r1, r2, r3, r4;
	private Exit exit;
	private boolean vydim;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<String> lines = new ArrayList<String>();
	private int enemyPositions[][] = new int[20][4];
	private static ArrayList<int[]> enemyPos = new ArrayList<int[]>();
	private Interface grafika;
	
	public Controller() {
		model = new Model();
		grafika = new Interface();
	}

	public void checkCollisions() {
		player = Interface.getMovablePlayer();
		enemyPos = Model.getEnemyPositions();
		enemies = Model.getEnemies();
		if (levels != Model.getLevels()) {
			levels = Model.getLevels();
		}
		zdi = Model.getZdi();
		exit = Model.getExit();
		lines = Model.getLines();
		if (levels < 4) {
			r1 = player.getBounds();
			for (int i = 0; i < zdi.size(); i++) {
				zed = zdi.get(i);
				r2 = zed.getBounds();
				if (r1.intersects(r2)) {
					if ((player.getX() + player.getWidth() - 2) < zed.getX()) {
						player.setX(player.getX() - 1);
					}
					if (player.getX() > (zed.getX() + zed.getWidth() - 2)) {
						player.setX(player.getX() + 1);
					}
					if ((player.getY() + player.getHeight() - 2) < zed.getY()) {
						player.setY(player.getY() - 1);
					}
					if (player.getY() > (zed.getY() + zed.getHeight() - 2)) {
						player.setY(player.getY() + 1);
					}
				}
			}
			for (int i = 0; i < enemies.size(); i++) {
				Enemy enemy = enemies.get(i);
				r4 = enemy.getBounds();
				// enemyPositions = enemyPos.get(i);
				// pokud je na waypointech
				if ((!String.valueOf(
						lines.get(enemy.getBlockY()).charAt(enemy.getBlockX()))
						.contains(" ") && !String.valueOf(
						lines.get(enemy.getBlockY2())
								.charAt(enemy.getBlockX2())).contains(" "))
						&& (enemy.getBehaviour().contentEquals("normal") || enemy
								.getBehaviour().contentEquals("guard"))) {
					ArrayList<Integer> options = new ArrayList<Integer>();
					if (enemy.getDx() == 1) {
						if (enemyPositions[i][0] != enemy.getBlockX()) {
							enemyPositions[i][0] = enemy.getBlockX();
							enemyPositions[i][2] = enemy.getBlockX2();
							if (!String.valueOf(
									lines.get(enemy.getBlockY()).charAt(
											enemy.getBlockX2() + 1)).contains(
									"#")) {
								options.add(0);
							}
							if (!String.valueOf(
									lines.get(enemy.getBlockY() + 1).charAt(
											enemy.getBlockX2())).contains("#")) {
								options.add(2);
							}
							if (!String.valueOf(
									lines.get(enemy.getBlockY2() - 1).charAt(
											enemy.getBlockX2())).contains("#")) {
								options.add(3);
							}

							enemy.choosePath(options);
						}
					} else if (enemy.getDx() == -1) {
						if (enemyPositions[i][2] != enemy.getBlockX2()) {
							enemyPositions[i][0] = enemy.getBlockX();
							enemyPositions[i][2] = enemy.getBlockX2();
							if (!String.valueOf(
									lines.get(enemy.getBlockY()).charAt(
											enemy.getBlockX() - 1)).contains(
									"#")) {
								options.add(1);
							}
							if (!String.valueOf(
									lines.get(enemy.getBlockY2() + 1).charAt(
											enemy.getBlockX())).contains("#")) {
								options.add(2);
							}
							if (!String.valueOf(
									lines.get(enemy.getBlockY() - 1).charAt(
											enemy.getBlockX())).contains("#")) {
								options.add(3);
							}
							enemy.choosePath(options);
						}
					} else if (enemy.getDy() == 1) {
						if (enemyPositions[i][1] != enemy.getBlockY()) {
							enemyPositions[i][1] = enemy.getBlockY();
							enemyPositions[i][3] = enemy.getBlockY2();
							if (!String.valueOf(
									lines.get(enemy.getBlockY2()).charAt(
											enemy.getBlockX() - 1)).contains(
									"#")) {
								options.add(1);
							}
							if (!String.valueOf(
									lines.get(enemy.getBlockY2() + 1).charAt(
											enemy.getBlockX())).contains("#")) {
								options.add(2);
							}
							if (!String.valueOf(
									lines.get(enemy.getBlockY2()).charAt(
											enemy.getBlockX() + 1)).contains(
									"#")) {
								options.add(0);
							}
							enemy.choosePath(options);
						}
					} else if (enemy.getDy() == -1) {
						if (enemyPositions[i][3] != enemy.getBlockY2()) {
							enemyPositions[i][3] = enemy.getBlockY2();
							enemyPositions[i][1] = enemy.getBlockY();
							if (!String.valueOf(
									lines.get(enemy.getBlockY()).charAt(
											enemy.getBlockX() - 1)).contains(
									"#")) {
								options.add(1);
							}
							if (!String.valueOf(
									lines.get(enemy.getBlockY() - 1).charAt(
											enemy.getBlockX())).contains("#")) {
								options.add(3);
							}
							if (!String.valueOf(
									lines.get(enemy.getBlockY()).charAt(
											enemy.getBlockX() + 1)).contains(
									"#")) {
								options.add(0);
							}
							enemy.choosePath(options);
						}
					}
					if (options.size() > 3) {
						System.out.println("Enemak " + i
								+ " ma chybu v direkcich");
					}
					options.clear();
				} else if (String.valueOf(
						lines.get(enemy.getBlockY()).charAt(enemy.getBlockX()))
						.contains(" ")
						|| String.valueOf(
								lines.get(enemy.getBlockY2()).charAt(
										enemy.getBlockX2())).contains(" ")) {
					enemy.setDx(-enemy.getDx());
					enemy.setDy(-enemy.getDy());
				}

				/*
				 * else if (!enemy.getBehaviour().contentEquals("stopped")) {
				 * System.out.println("Jedu podle zdi!"); // pokud je zed vpravo
				 * if (enemy.getDx() == 1) { if (String.valueOf(
				 * lines.get(enemy.getBlockY()).charAt( enemy.getBlockX() +
				 * 1)).contains("#") || String.valueOf(
				 * lines.get(enemy.getBlockY2()).charAt( enemy.getBlockX() + 1))
				 * .contains("#")) { enemy.changeDirection(0);
				 * enemy.setBehaviour("normal"); } } // pokud je zed vlevo if
				 * (enemy.getDx() == -1) { if (String.valueOf(
				 * lines.get(enemy.getBlockY()).charAt( enemy.getBlockX2() -
				 * 1)).contains("#") || String.valueOf(
				 * lines.get(enemy.getBlockY2()).charAt( enemy.getBlockX2() -
				 * 1)) .contains("#")) { enemy.changeDirection(1);
				 * enemy.setBehaviour("normal"); } } // pokud je zed nahore if
				 * (enemy.getDy() == -1) { if (String.valueOf(
				 * lines.get(enemy.getBlockY2() - 1).charAt(
				 * enemy.getBlockX())).contains("#") || String.valueOf(
				 * lines.get(enemy.getBlockY2() - 1)
				 * .charAt(enemy.getBlockX2())) .contains("#")) {
				 * enemy.changeDirection(3); enemy.setBehaviour("normal"); } }
				 * // pokud je zed dole if (enemy.getDy() == 1) { if
				 * (String.valueOf( lines.get(enemy.getBlockY() + 1).charAt(
				 * enemy.getBlockX())).contains("#") || String.valueOf(
				 * lines.get(enemy.getBlockY() + 1) .charAt(enemy.getBlockX2()))
				 * .contains("#")) { enemy.changeDirection(2);
				 * enemy.setBehaviour("normal"); } } }
				 */
				// vydi hrace vlevo
				if ((!String.valueOf(
						lines.get(player.getBlockY())
								.charAt(player.getBlockX())).contains(" "))
						&& (!String.valueOf(
								lines.get(player.getBlockY2()).charAt(
										player.getBlockX2())).contains(" "))) {
					if (player.getBlockX() < enemy.getBlockX()
							&& (player.getBlockY() == enemy.getBlockY()
									|| player.getBlockY2() == enemy
											.getBlockY2()
									|| player.getBlockY() == enemy.getBlockY2() || player
									.getBlockY2() == enemy.getBlockY())) {
						vydim = true;
						for (int blocks = 1; blocks < (enemy.getBlockX2() - player
								.getBlockX()); blocks++) {
							if (!((enemy.getBlockX2() - blocks) < 1)
									&& !((enemy.getBlockX2() - blocks) < 1)) {
								if (!String.valueOf(
										lines.get(enemy.getBlockY()).charAt(
												enemy.getBlockX2() - blocks))
										.contains("#")
										&& !String.valueOf(
												lines.get(enemy.getBlockY2())
														.charAt(enemy
																.getBlockX2()
																- blocks))
												.contains("#")) {
								} else {
									vydim = false;
								}
							}
						}
						if (vydim == true) {
							if (String.valueOf(
									lines.get(enemy.getBlockY2()).charAt(
											enemy.getBlockX() - 1)).contains(
									"#")) {
								enemy.setDx(0);
								enemy.setDy(-1);
							} else if (String.valueOf(
									lines.get(enemy.getBlockY()).charAt(
											enemy.getBlockX() - 1)).contains(
									"#")) {
								enemy.setDx(0);
								enemy.setDy(1);
							} else {
								enemy.setDx(-1);
								enemy.setDy(0);
							}
						}
					}
					// vydi hrace v pravo
					if (player.getBlockX() > enemy.getBlockX()
							&& (player.getBlockY() == enemy.getBlockY()
									|| player.getBlockY2() == enemy
											.getBlockY2()
									|| player.getBlockY() == enemy.getBlockY2() || player
									.getBlockY2() == enemy.getBlockY())) {
						vydim = true;
						for (int blocks = 1; blocks < (player.getBlockX2() - enemy
								.getBlockX()); blocks++) {
							if (!((enemy.getBlockX() + blocks) > lines.get(1)
									.length() - 1)
									&& !((enemy.getBlockX() + blocks) > lines
											.get(1).length() - 1)) {
								if (!String.valueOf(
										lines.get(enemy.getBlockY()).charAt(
												enemy.getBlockX() + blocks))
										.contains("#")
										&& !String.valueOf(
												lines.get(enemy.getBlockY2())
														.charAt(enemy
																.getBlockX()
																+ blocks))
												.contains("#")) {
								} else {
									vydim = false;
								}
							}
						}
						if (vydim == true) {
							if (String.valueOf(
									lines.get(enemy.getBlockY2()).charAt(
											enemy.getBlockX2() + 1)).contains(
									"#")) {
								enemy.setDx(0);
								enemy.setDy(-1);
							} else if (String.valueOf(
									lines.get(enemy.getBlockY()).charAt(
											enemy.getBlockX2() + 1)).contains(
									"#")) {
								enemy.setDx(0);
								enemy.setDy(1);
							} else {
								enemy.setDx(1);
								enemy.setDy(0);
							}
						}
					}
					// vydi hrace nahore
					if (player.getBlockY() < enemy.getBlockY()
							&& (player.getBlockX() == enemy.getBlockX()
									|| player.getBlockX2() == enemy
											.getBlockX2()
									|| player.getBlockX() == enemy.getBlockX2() || player
									.getBlockX2() == enemy.getBlockX())) {
						vydim = true;
						for (int blocks = 1; blocks < (enemy.getBlockY2() - player
								.getBlockY()); blocks++) {
							if ((!(enemy.getBlockY2() - blocks < 1))
									&& (!(enemy.getBlockY2() - blocks < 1))) {
								if (!String.valueOf(
										lines.get(enemy.getBlockY2() - blocks)
												.charAt(enemy.getBlockX()))
										.contains("#")
										&& !String.valueOf(
												lines.get(
														enemy.getBlockY2()
																- blocks)
														.charAt(enemy
																.getBlockX2()))
												.contains("#")) {
								} else {
									vydim = false;
								}
							}
						}
						if (vydim == true) {
							if (String.valueOf(
									lines.get(enemy.getBlockY2() - 1).charAt(
											enemy.getBlockX())).contains("#")) {
								enemy.setDx(1);
								enemy.setDy(0);
							} else if (String.valueOf(
									lines.get(enemy.getBlockY2() - 1).charAt(
											enemy.getBlockX2())).contains("#")) {
								enemy.setDx(-1);
								enemy.setDy(0);
							} else {
								enemy.setDx(0);
								enemy.setDy(-1);
							}
						}
					}
					// vydi hrace dole
					if (player.getBlockY() > enemy.getBlockY()
							&& (player.getBlockX() == enemy.getBlockX()
									|| player.getBlockX2() == enemy
											.getBlockX2()
									|| player.getBlockX() == enemy.getBlockX2() || player
									.getBlockX2() == enemy.getBlockX())) {
						vydim = true;
						for (int blocks = 1; blocks < (player.getBlockY2() - enemy
								.getBlockY()); blocks++) {
							if ((!(enemy.getBlockY2() + blocks > lines.size() - 1))
									&& (!(enemy.getBlockY2() + blocks > lines
											.size() - 1))) {
								if (!String.valueOf(
										lines.get(enemy.getBlockY() + blocks)
												.charAt(enemy.getBlockX()))
										.contains("#")
										&& !String.valueOf(
												lines.get(
														enemy.getBlockY()
																+ blocks)
														.charAt(enemy
																.getBlockX2()))
												.contains("#")) {
								} else {
									vydim = false;
								}
							}
						}
						if (vydim == true) {
							if (String.valueOf(
									lines.get(enemy.getBlockY() + 1).charAt(
											enemy.getBlockX())).contains("#")) {
								enemy.setDx(1);
								enemy.setDy(0);
							} else if (String.valueOf(
									lines.get(enemy.getBlockY() + 1).charAt(
											enemy.getBlockX2())).contains("#")) {
								enemy.setDx(-1);
								enemy.setDy(0);
							} else {
								enemy.setDx(0);
								enemy.setDy(1);
							}
						}
					}
				}

				if (r4.intersects(r1)) {
					model.playerDead();
					enemy.setBehaviour("normal");
				}

			}
			r3 = exit.getBounds();
			if (r1.intersects(r3)) {
				model.setNewLevel();
				enemyPos = Model.getEnemyPositions();
				grafika.repaint();
			}
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		Timer timer = new Timer(9, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (Interface.getStart() == true && Interface.getPause() == false) {
					checkCollisions();
					player.move();
				}
			}
		});
		timer.start();

	}
}
