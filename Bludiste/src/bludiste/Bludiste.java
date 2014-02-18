package bludiste;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

public class Bludiste extends JFrame {
        /**
         * 
         */
        private static final long serialVersionUID = 1L;

        public Bludiste() {
                add(new Interface());
                Dimension dim = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
                System.out.println(dim.getHeight());
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				setSize(800,608);
                setVisible(true);
                setResizable(false);
                setLocationRelativeTo(null);
                setTitle("Labyrint");
        }

        public static void main(String[] args){
                new Bludiste();
        }
}