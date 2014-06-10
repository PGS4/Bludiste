package com.labyrint.graphics;

import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

import straightedge.geom.KPoint;
import straightedge.geom.KPolygon;
import straightedge.geom.vision.Occluder;
import straightedge.geom.vision.OccluderImpl;
import straightedge.geom.vision.VisionData;
import straightedge.geom.vision.VisionFinder;

import com.labyrint.entities.Player;
import com.labyrint.entities.Zed;
import com.labyrint.main.Interface;
import com.labyrint.main.Model;

public class View {

	private static Player player;
	private static KPoint eye;
	private int numPoints;
	private float radius;
	private static KPolygon boundaryPolygon;
	private static double smallAmount = 0.0001;
	
	public View(){
		player = Interface.getMovablePlayer();	
		// Make the eye which is like the light-source
		eye = new KPoint(smallAmount,smallAmount);
		// Make the field of vision polygon
		numPoints = 30;
		radius = 500;
		boundaryPolygon = KPolygon.createRegularPolygon(numPoints, radius);
	}

	public static VisionData getFov(){
		// The VisionData just contains the eye and boundary polygon,
		// and also the results of the VisionFinder calculations.
		VisionData visionData = new VisionData(eye, boundaryPolygon);
		VisionFinder visionFinder = new VisionFinder();

		// Make the occluders which block our vision
		ArrayList<Zed> zdi = Model.getZdi();
		ArrayList<Occluder> occluders = new ArrayList<Occluder>();
		for(int i = 0; i<zdi.size(); i++){
			Zed zed = zdi.get(i);
			KPolygon polygon = KPolygon.createRectOblique(zed.getX() + (zed.getWidth()/2), zed.getY() ,zed.getX()+(zed.getWidth()/2), zed.getY() + zed.getHeight(), zed.getWidth()-smallAmount);
			Occluder occluder = new OccluderImpl(polygon);
			occluders.add(occluder);
		}
		
		visionData.eye.setCoords(player.getX() + (player.getWidth()/2) + smallAmount, player.getY() + (player.getHeight()/2) + smallAmount);
		visionData.boundaryPolygon.translateTo(visionData.eye);
		// Calculate what's visible
		visionFinder.calc(visionData, occluders);
		return visionData;
	}
	
	public static Area getOutView() {
		Area outView = new Area();
		Rectangle2D map = new Rectangle(0, 0, 800, 635);
		Area mapArea = new Area(map);
		outView.add(mapArea);
		//outView.subtract(getCliping());
		outView.subtract(new Area(getFov().visiblePolygon));
		return outView;
	}
}
