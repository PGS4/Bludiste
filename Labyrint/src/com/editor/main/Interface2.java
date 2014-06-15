package com.editor.main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.*;
import java.io.File;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;

public class Interface2 extends JPanel implements ActionListener, MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1212756613752347186L;
	private JMenu menu;
	private JMenuBar menuBar;
	private JMenuItem menuItem;
	private Timer main;
	private Hashtable<List<Integer>, String> map = Model2.getMap();
	private int mouseBtn = 0;
	private Enumeration<?> objectsInMap;
	private Model2 model;
	private String value = "";
	private int exit = 0;
	private int enemy = 0;
	private JFileChooser fc;
	private FileFilter filter;
	private Color background = new Color(0.7f, 0.7f, 0.7f);
	private Timer dragger = new Timer(20, new ActionListener() {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			if (getCursorBlockY() >= 1 && getCursorBlockX() >= 1
					&& getCursorBlockY() < (640 / 32) - 2
					&& getCursorBlockX() < (800 / 32) - 1) {
				if (!(getCursorBlockY() == 1 && getCursorBlockX() == 1)) {
					map = Model2.getMap();
					List<Integer> souradnice = Arrays.asList(getCursorBlockX(),
							getCursorBlockY());
					if (mouseBtn == MouseEvent.BUTTON1) {
						if (map.get(souradnice).equals(" ")) {
							if (value.equals("!")) {
								if (exit < 1) {
									Model2.putToMap(souradnice, value);
									exit += 1;
								}
							} else if (value.equals("E")) {
								if (enemy < 20) {
									Model2.putToMap(souradnice, value);
									enemy += 1;
								}
							} else {
								Model2.putToMap(souradnice, value);
							}
						}
					}
					if (mouseBtn == MouseEvent.BUTTON3) {
						if (map.get(souradnice).equals("!")) {
							exit -= 1;
						}
						if (map.get(souradnice).equals("E")) {
							enemy -= 1;
						}
						Model2.putToMap(souradnice, " ");
					}
				}
			}
		}

	});

	public Interface2() {
		model = new Model2();
		main = new Timer(25, this);
		main.start();
		addMouseListener(this);
		setBackground(background);
		fc = new JFileChooser();
		fc.setAcceptAllFileFilterUsed(false);
	}

	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		for (int i = 0; i < 635 / 32; i++) {
			g2d.drawLine(0, i * 32, 800, i * 32);
		}
		for (int i = 0; i < 800 / 32; i++) {
			g2d.drawLine(i * 32, 0, i * 32, 635);
		}
		objectsInMap = map.keys();
		while (objectsInMap.hasMoreElements()) {
			@SuppressWarnings("unchecked")
			List<Integer> souradnice = (List<Integer>) objectsInMap
					.nextElement();
			int x = souradnice.get(0) * 32;
			int y = souradnice.get(1) * 32;
			ImageIcon ii;
			Image itemImage = null;
			if (map.get(souradnice).equals("#")) {
				ii = new ImageIcon(this.getClass().getResource("/zed.png"));
				itemImage = ii.getImage();
			}
			if (map.get(souradnice).equals("E")) {
				ii = new ImageIcon(this.getClass().getResource("/enemy.png"));
				itemImage = ii.getImage();
			}
			if (map.get(souradnice).equals("!")) {
				ii = new ImageIcon(this.getClass().getResource("/exit.png"));
				itemImage = ii.getImage();
			}
			if (map.get(souradnice).equals(".")) {
				ii = new ImageIcon(this.getClass().getResource("/life.png"));
				itemImage = ii.getImage();
			}
			g2d.drawImage(itemImage, x, y, null);
		}
		ImageIcon ii = new ImageIcon(this.getClass().getResource("/star.png"));
		Image itemImage = ii.getImage();
		g2d.drawImage(itemImage, 35, 35, null);
		Font info = new Font("Times New Roman", Font.BOLD, 20);
		Color bad = new Color(255, 0, 0);
		Color ok = new Color(255, 255, 255);
		g2d.setFont(info);
		if (value.equals("!")) {
			if (exit < 1) {
				g2d.setColor(ok);
			} else {
				g2d.setColor(bad);
			}
			g2d.drawString("Poèet exitù na mapì: " + exit + "/1", 10, 15);
		}
		if (value.equals("E")) {
			if (enemy < 20) {
				g2d.setColor(ok);
			} else {
				g2d.setColor(bad);
			}
			g2d.drawString("Poèet nepøátel na mapì: " + enemy + "/20", 10, 15);
		}
	}

	public JMenuBar addMenu() {
		menu = new JMenu("Menu");
		menuBar = new JMenuBar();
		menu.setMnemonic(KeyEvent.VK_ALT);
		menuBar.add(menu);
		menuBar.setName("Menu");
		menuItem = new JMenuItem("Nový", KeyEvent.VK_T);
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menuItem = new JMenuItem("Nahrát");
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menuItem = new JMenuItem("Uložit");
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menuItem = new JMenuItem("Exportovat");
		menu.add(menuItem);
		menuItem.addActionListener(this);
		menuItem = new JMenuItem("Konec");
		menuItem.addActionListener(this);
		menu.add(menuItem);
		return menuBar;
	}

	public int getCursorX() {
		int x = (int) (MouseInfo.getPointerInfo().getLocation().getX() - getLocationOnScreen()
				.getX());
		return x;
	}

	public int getCursorY() {
		int y = (int) (MouseInfo.getPointerInfo().getLocation().getY() - getLocationOnScreen()
				.getY());
		return y;
	}

	public int getCursorBlockX() {
		int x = getCursorX();
		int cursorBlockX = ((x) - ((x) % 32)) / 32;
		return cursorBlockX;
	}

	public int getCursorBlockY() {
		int y = getCursorY();
		int blockY = ((y) - ((y) % 32)) / 32;
		return blockY;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		repaint();
		try {
			if (e.getActionCommand().equals("Konec")) {
				System.exit(0);
			}
			if (e.getActionCommand().equals("Nový")) {
				exit=0;
				enemy=0;
				model.newMap();
			}
			if (e.getActionCommand().equals("Uložit")) {
				fc.removeChoosableFileFilter(filter);
				fc.validate();
				filter = new FileFilter() {

					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return "Java object file (.tmp)";
					}

					@Override
					public boolean accept(File file) {
						// TODO Auto-generated method stub
						if (file.isDirectory()) {
							return true;
						}
						if (file.getName().contains(".tmp")) {
							return true;
						} else {
							return false;
						}
					}
				};
				fc.setFileFilter(filter);
				int returnVal = fc.showSaveDialog(Interface2.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					model.saveFile(file);
				}
			}
			if (e.getActionCommand().equals("Nahrát")) {
				fc.removeChoosableFileFilter(filter);
				fc.validate();
				filter = new FileFilter() {

					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return "Java object file (.tmp)";
					}

					@Override
					public boolean accept(File file) {
						// TODO Auto-generated method stub
						if (file.isDirectory()) {
							return true;
						}
						if (file.getName().contains(".tmp")) {
							return true;
						} else {
							return false;
						}
					}
				};
				fc.setFileFilter(filter);
				int returnVal = fc.showOpenDialog(Interface2.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					if (model.openFile(file)) {
						map = Model2.getMap();
						objectsInMap = map.keys();
						exit=0;
						enemy=0;
						while(objectsInMap.hasMoreElements()){
							@SuppressWarnings("unchecked")
							List<Integer> souradnice = (List<Integer>) objectsInMap
									.nextElement();
							if(map.get(souradnice).equals("!")){
								exit+=1;
							}
							if(map.get(souradnice).equals("E")){
								enemy+=1;
							}
						}
					} else {
						JOptionPane.showMessageDialog(null,
								"Error: File not found!", "Error",
								JOptionPane.ERROR_MESSAGE);
					}
				}

			}
			if (e.getActionCommand().equals("Exportovat")) {
				fc.removeChoosableFileFilter(filter);
				fc.validate();
				filter = new FileFilter() {

					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return "Text file (.txt)";
					}

					@Override
					public boolean accept(File file) {
						// TODO Auto-generated method stub
						if (file.isDirectory()) {
							return true;
						}
						if (file.getName().contains(".txt")) {
							return true;
						} else {
							return false;
						}
					}
				};
				fc.setFileFilter(filter);
				int returnVal = fc.showSaveDialog(Interface2.this);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					File file = fc.getSelectedFile();
					model.exportMap(file);
				}
			}
		} catch (Exception ex) {

		}
		value = ItemsWindow.getSelectedValue();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseBtn = e.getButton();
		dragger.start();
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		dragger.stop();
	}
}
