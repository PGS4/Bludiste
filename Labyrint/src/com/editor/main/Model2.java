package com.editor.main;

import java.util.ArrayList;
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
		for (int y = 0; y < (640 / 32)-1; y++) {
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
			String path;
			if(file.getName().contains(".tmp")){
				path=file.getPath();
			}else{
				path = file.getPath() + ".tmp";
			}
			fos = new FileOutputStream(path);
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
	
	@SuppressWarnings({ "unchecked", "resource" })
	public boolean openFile(File file) {
		FileInputStream fis;
		try{
			fis = new FileInputStream(file);
			ObjectInputStream ois = new ObjectInputStream(fis);
			map = (Hashtable<List<Integer>, String>) ois.readObject();
			ois.close();
			fis.close();
			return true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.err.println(e.getLocalizedMessage());
			return false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public void exportMap(File file) {
		String line;
		ArrayList<String> lines = new ArrayList<String>();
		List<Integer> souradnice;
		for(int y=0; y<(640/32)-1; y++){
			line ="";
			for(int x=0; x<(800/32); x++){
				souradnice = Arrays.asList(x,y);
				line += map.get(souradnice);
			}
			lines.add(line);
		}
		PrintWriter pw;
		try{
			String filePath;
			if(file.getName().contains(".txt")){
				filePath = file.getPath();
			}else{
				filePath = file.getPath() + ".txt"; 
			}
			pw = new PrintWriter(filePath);
			for(String str: lines){
				pw.println(str);;
			}
			pw.close();
		}catch(IOException e){
			
		}
	}
	
	public static Hashtable<List<Integer>, String> getMap() {
		return map;
	}

	public static void putToMap(List<Integer> souradnice, String value) {
		map.put(souradnice, value);
	}

}
