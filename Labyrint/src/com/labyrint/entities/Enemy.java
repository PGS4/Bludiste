package com.labyrint.entities;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.Timer;

import com.labyrint.main.Interface;
import com.labyrint.main.Model;

public class Enemy extends MovableObject implements ActionListener {
	private String behaviour = "normal";
	private int lastDx = dx, lastDy = dy;
	private boolean stop=false;
	
	
	/*
	 * private Timer behaviourStop = new
	 * Timer((int)(Math.floor(Math.random()*2+3)*1000), new ActionListener(){
	 * 
	 * @Override public void actionPerformed(ActionEvent arg0) { // TODO
	 * Auto-generated method stub if(behaviour.contentEquals("normal")){
	 * behaviour = "stopped"; lastDx = dx; lastDy = dy; startNormalTimer(); } }
	 * 
	 * }); private Timer behaviourNormal = new
	 * Timer((int)(Math.floor(Math.random()*18+9)*1000), new ActionListener(){
	 * 
	 * @Override public void actionPerformed(ActionEvent e) { // TODO
	 * Auto-generated method stub if(behaviour.contentEquals("stopped")){
	 * behaviour = "normal"; dx = -lastDx; dy = -lastDy; move();
	 * startStopTimer(); } }
	 * 
	 * }); Timer chase = new Timer(8,this);
	 */
	private Timer normal = new Timer(12, this);

	public Enemy(int x, int y, String name) {
		super(x, y);
		ImageIcon ii = new ImageIcon(this.getClass().getResource("/enemy.png"));
		image = ii.getImage();
		init();
		normal.start();
		// behaviourStop.start();
	}

	/*
	 * private void startStopTimer(){ behaviourStop.start();
	 * behaviourNormal.stop(); } private void startNormalTimer(){
	 * behaviourNormal.start(); behaviourStop.stop(); }
	 */
	public void init() {
		Random rand = new Random();
		ArrayList<Integer> options = new ArrayList<Integer>();
		ArrayList<String> lines = Model.getLines();
		if (!String.valueOf(lines.get(getBlockY()).charAt(getBlockX2() + 1))
				.contains("#")) {
			options.add(0);
		}
		if (!String.valueOf(lines.get(getBlockY()).charAt(getBlockX() - 1))
				.contains("#")) {
			options.add(0);
		}
		if (!String.valueOf(lines.get(getBlockY() + 1).charAt(getBlockX2()))
				.contains("#")) {
			options.add(2);
		}
		if (!String.valueOf(lines.get(getBlockY2() - 1).charAt(getBlockX2()))
				.contains("#")) {
			options.add(3);
		}
		direction = options.get(rand.nextInt(options.size()));
		if (direction == 0) {
			dx = 1;
			dy = 0;
		}
		if (direction == 1) {
			dx = -1;
			dy = 0;
		}
		if (direction == 2) {
			dx = 0;
			dy = 1;
		}
		if (direction == 3) {
			dx = 0;
			dy = -1;
		}
		options.clear();
	}

	public int getLastDx() {
		return lastDx;
	}

	public int getLastDy() {
		return lastDy;
	}

	public void choosePath(ArrayList<Integer> options) {
		if (behaviour.contentEquals("normal")) {
			Random rand = new Random();
			if (options.size() != 0) {
				direction = options.get(rand.nextInt(options.size()));
				if (direction == 0) {
					dx = 1;
					dy = 0;
				}
				if (direction == 1) {
					dx = -1;
					dy = 0;
				}
				if (direction == 2) {
					dx = 0;
					dy = 1;
				}
				if (direction == 3) {
					dx = 0;
					dy = -1;
				}
			} else {
				dx = -dx;
				dy = -dy;
			}
		}
	}

	public String getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(String behaviour) {
		this.behaviour = behaviour;
	}

	/*
	 * private void setChaseMode(){ normal.stop(); chase.start(); } private void
	 * setNormalMode(){ chase.stop(); normal.start(); }
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		/*
		 * if(behaviour.contentEquals("stopped")){ dx = 0; dy = 0; }
		 */
		move();
		if(Interface.getPause()==true){
			stop=true;
			dx=0;
			dy=0;	
		}else{
			if(stop==true){
				init();
				stop=false;
			}
		}
		/*
		 * if (behaviour.contentEquals("chase")){ setChaseMode(); }
		 * if(behaviour.contentEquals("normal")){ setNormalMode(); }
		 */
	}
}
