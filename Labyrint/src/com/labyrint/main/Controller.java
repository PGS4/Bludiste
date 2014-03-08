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
	private Interface grafika;
	private Player player;
	private int levels;
	private ArrayList<Zed> zdi;
	private Zed zed;
	private Rectangle r1, r2, r3, r4;
	private Exit exit;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<String> lines = new ArrayList<String>();

	public Controller() {
		model = new Model();
		grafika = new Interface();
	}

	public void checkCollisions() {
		player = Interface.getMovablePlayer();
		levels = Model.getLevels();
		enemies = Model.getEnemies();
		zdi = Model.getZdi();
		exit = Model.getExit();
		lines = Model.getLines();
		if (levels < 3) {
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
			for (int i = 0; i < enemies.size(); i++){
				Enemy enemy = enemies.get(i);
				r4 = enemy.getBounds();
				if(enemy.getDx() == 1){
					if(String.valueOf(lines.get(enemy.getBlockY()).charAt(enemy.getBlockX()+1)).contains("#") || String.valueOf(lines.get(enemy.getBlockY2()).charAt(enemy.getBlockX()+1)).contains("#")){
						enemy.changeDirection(0);
					}
				}
				if(enemy.getDx() == -1){
					if(String.valueOf(lines.get(enemy.getBlockY()).charAt(enemy.getBlockX2()-1)).contains("#") || String.valueOf(lines.get(enemy.getBlockY2()).charAt(enemy.getBlockX2()-1)).contains("#")){
						enemy.changeDirection(1);
					}
				}
				if(enemy.getDy() == -1){
					if(String.valueOf(lines.get(enemy.getBlockY2()-1).charAt(enemy.getBlockX())).contains("#") || String.valueOf(lines.get(enemy.getBlockY2()-1).charAt(enemy.getBlockX2())).contains("#")){
						enemy.changeDirection(3);
					}
				}
				if(enemy.getDy() == 1){
					if(String.valueOf(lines.get(enemy.getBlockY()+1).charAt(enemy.getBlockX())).contains("#") || String.valueOf(lines.get(enemy.getBlockY()+1).charAt(enemy.getBlockX2())).contains("#")){
						enemy.changeDirection(2);
					}
				}
				if (Math.abs(player.getX()-enemy.getX())<64 && Math.abs(player.getY()-enemy.getY())<64){
					//System.out.println("JE BLIZKO!! \n" + Math.abs(player.getX()-enemy.getX()) + " a " + Math.abs(player.getY()-enemy.getY()));
					if(player.getBlockX()<enemy.getBlockX() && (player.getBlockY()==enemy.getBlockY() || player.getBlockY2() == enemy.getBlockY())){
						if(String.valueOf(lines.get(enemy.getBlockY()).charAt(enemy.getBlockX2()-1)).contains("#") || String.valueOf(lines.get(enemy.getBlockY2()).charAt(enemy.getBlockX2()-1)).contains("#")){
							System.out.println("Je vlevo ale nemuzu tam :(");
						}else{
						enemy.setDx(-1);
						enemy.setDy(0);
						}
					}
					if(player.getBlockX()>enemy.getBlockX() && (player.getBlockY()==enemy.getBlockY() || player.getBlockY2() == enemy.getBlockY())){
						if(String.valueOf(lines.get(enemy.getBlockY()).charAt(enemy.getBlockX()+1)).contains("#") || String.valueOf(lines.get(enemy.getBlockY2()).charAt(enemy.getBlockX()+1)).contains("#")){
							System.out.println("Je vpravo ale nemuzu tam :(");
						}else{
						enemy.setDx(1);
						enemy.setDy(0);
						}
					}
					if(player.getBlockY()<enemy.getBlockY() && (player.getBlockX()==enemy.getBlockX() || player.getBlockX2() == enemy.getBlockX())){
						if(String.valueOf(lines.get(enemy.getBlockY2()-1).charAt(enemy.getBlockX())).contains("#") || String.valueOf(lines.get(enemy.getBlockY2()-1).charAt(enemy.getBlockX2())).contains("#")){
							System.out.println("Je nahore ale nemuzu tam :(");
						}else{
						enemy.setDx(0);
						enemy.setDy(-1);
						}
					}
					if(player.getBlockY()>enemy.getBlockY() && (player.getBlockX()==enemy.getBlockX() || player.getBlockX2() == enemy.getBlockX())){
						if(String.valueOf(lines.get(enemy.getBlockY()+1).charAt(enemy.getBlockX())).contains("#") || String.valueOf(lines.get(enemy.getBlockY()+1).charAt(enemy.getBlockX2())).contains("#")){
							System.out.println("Je dole ale nemuzu tam :(");
						}else{
						enemy.setDx(0);
						enemy.setDy(1);
						}
					}
				}
				if(r4.intersects(r1)){
					model.playerDead();
					}
			}
			
			r3 = exit.getBounds();
			if (r1.intersects(r3)) {
				model.setNewLevel();
				grafika.repaint();
			}
		}
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		Timer timer = new Timer(5, new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				checkCollisions();
				player.move();
			}
		});
		timer.start();
	}
}
