package de.game.levels;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;

import de.game.objects.Ball;
import de.game.objects.LevelObject;

public class LevelController {

	public static int LEVELINDEX = 3;

	public static ArrayList<Level> LEVELS = new ArrayList<Level>();

	public LevelController(Ball player) {

		try {

			Level level00_tutorial = new Level("Tutorial", player, new Image("src/de/game/textures/level/Background_Level_0.jpg"));
			level00_tutorial.addDynamicObject(new LevelObject("Object", new Rectangle(0, 0, 100, 100), 300, 300));
			level00_tutorial.addStaticObject(new LevelObject("Kreis", new Circle(0, 0, 20), 300, 800, Color.green));

			Level level01 = new Level("Level 1", player, new Image("src/de/game/textures/level/Level1Slick2D.png"));
			level01.addDynamicObject(new LevelObject("Linkes Rechteck", new Rectangle(0, 0, 40, 350), 100, 500));
			level01.addDynamicObject(new LevelObject("Rechtes Rechteck", new Rectangle(0, 0, 40, 350), 500, 500));

			Level level02 = new Level("Level 2", player, new Image("src/de/game/textures/level/Level2Slick2D.png"));
			level02.addDynamicObject(new LevelObject("Linkes Rechteck", new Rectangle(0, 0, 40, 450), 100, 600));
			level02.addDynamicObject(new LevelObject("Kreis", new Circle(0, 0, 60), 450, 200));
			
			Level level03 = new Level("Level 3", player, new Image("src/de/game/textures/level/Level3Slick2D.png"));
		
			Polygon dreieck1 = new Polygon();
			Polygon dreieck2 = new Polygon();
			Polygon dreieck3 = new Polygon();
			Polygon dreieck4 = new Polygon();
			
			dreieck1.addPoint(100,200);
			dreieck1.addPoint(200,200);
			dreieck1.addPoint(100,300);
			
			dreieck2.addPoint(400,200);
			dreieck2.addPoint(500,200);
			dreieck2.addPoint(500,300);
			
			dreieck3.addPoint(100,700);
			dreieck3.addPoint(100,800);
			dreieck3.addPoint(200,800);
			
			dreieck4.addPoint(500,700);
			dreieck4.addPoint(500,800);
			dreieck4.addPoint(400,800);
			
			level03.addStaticObject(new LevelObject("Oben Links Dreieck", dreieck1, 100, 200, Color.black));
			level03.addDynamicObject(new LevelObject("Oben Rechts Dreieck", dreieck2, 500, 200));
			level03.addDynamicObject(new LevelObject("Unten Links Dreieck", dreieck3, 100, 800));
			level03.addDynamicObject(new LevelObject("Unten Rechts Dreieck", dreieck4, 500, 800));
			
			Level level04 = new Level("Level 4", player, new Image("src/de/game/textures/level/Level4Slick2D.png"));
		

			LEVELS.add(level00_tutorial);
			LEVELS.add(level01);
			LEVELS.add(level02);
			LEVELS.add(level03);
			LEVELS.add(level04);
			
		} catch (Exception e) {

		}
	}
}
