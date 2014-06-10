package com.editor.main;

import java.util.Arrays;
import java.util.Hashtable;
import java.util.List;
import java.io.*;

public class Model2 {

	private static Hashtable<List<Integer>, String> map = new Hashtable<List<Integer>, String>();

	public Model2() {

	}

	public void newMap() {
		map.clear();
		for (int y = 0; y < 640 / 32; y++) {
			for (int x = 0; x < 800 / 32; x++) {
				if (y == 0 || y == (640 / 32) - 2 || x == 0
						|| x == (800 / 32) - 1) {
					List<Integer> souradnice = Arrays.asList(x, y);
					map.put(souradnice, "#");
				} else {
					List<Integer> souradnice = Arrays.asList(x, y);
					map.put(souradnice, " ");
				}
			}
		}
	}

	public void saveFile(File file) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(file + ".tmp");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(map);
			oos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unchecked")
	public void openFile(File file) {
		FileInputStream fis;
		try{
			fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			map = (Hashtable<List<Integer>, String>) ois.readObject();
			ois.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Hashtable<List<Integer>, String> getMap() {
		return map;
	}

	public static void putToMap(List<Integer> souradnice, String value) {
		map.put(souradnice, value);
	}

}
