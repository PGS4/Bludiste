package bludiste;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;
        private Timer timer;
        private Player player;
        private ArrayList<Zed> zdi;
        public static  Dimension d;
        private int[][] pos = {{32,32},{64,64},{96,96}};
        Color pozadi = new Color(199, 199, 199);
        private Image map1;

        public Board() {
                addKeyListener(new TAdapter());
                setFocusable(true);
                setBackground(pozadi);
                setDoubleBuffered(true);
                player = new Player();
                initZed();
                d = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
                timer = new Timer(5, this);
                timer.start();
                map1 = Toolkit.getDefaultToolkit().getImage("/map1.gif");
                Map1 mapa = new Map1(map1);
                System.out.println((mapa.getWidth()));
        }

        public void initZed() {
                zdi = new ArrayList<Zed>();
                for (int i = 0; i < pos.length; i++) {
                		zdi.add(new Zed(pos[i][0], pos[i][1]));
                        System.out.println(pos [i] [0] + " a " + pos [i] [1]);
                }
        }

        public void paint(Graphics g) {
                super.paint(g);
                Graphics2D g2d = (Graphics2D)g;
                ;
                g2d.drawImage(player.getImage(), player.getX(), player.getY(), this);
                for (int i = 0; i < zdi.size(); i++) {
                        Zed azed = (Zed) zdi.get(i);
                       
                        g2d.drawImage(azed.getImage(), azed.getX(), azed.getY(), this);
                }
                g2d.drawRect(0, 0, (int) d.getWidth(), (int) d.getHeight());
                Toolkit.getDefaultToolkit().sync();
                g.dispose();
        }

        public void actionPerformed(ActionEvent e) {
        	player.move();
            checkCollissions();
            repaint();
               
        }

        public void checkCollissions() {

                Rectangle r1 = player.getBounds();
                for (int j = 0; j < zdi.size(); j++) {
                        Zed azed = (Zed) zdi.get(j);
                        Rectangle r2 = azed.getBounds();
                        if (r1.intersects(r2)) {
                                if (((player.getX()+(player.getWidth()/2))-8) < (azed.getX()-(azed.getWidth()/2))) {
                                        player.x = player.getX() - 2;
                                        System.out.println("leva");
                                }
                                if ((player.getX()-(player.getWidth()/2))-2 > (azed.getX()+(azed.getWidth()/2))) {
                                        player.x = player.getX() + 2;
                                        System.out.println("prava");
                                }
                                if ((player.getY()+(player.getHeight()/2))-2 < (azed.getY()+(azed.getHeight()/2))) {
                                        player.y = player.getY() - 2;
                                        System.out.println("horni");
                                }
                                if ((player.getY()-(player.getHeight()/2))-2 > (azed.getY()-(azed.getHeight()/2))) {
                                        player.y = player.getY() + 2;
                                        System.out.println("spodni");
                                }
                        }
                }
        }

        private class TAdapter extends KeyAdapter {
                public void keyReleased(KeyEvent e) {
                        player.keyReleased(e);
                }

                public void keyPressed(KeyEvent e) {
                        player.keyPressed(e);
                }
        }
}