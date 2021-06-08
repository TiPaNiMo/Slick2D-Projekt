package de.game.levels;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Polygon;
import org.newdawn.slick.geom.Rectangle;

import de.game.objects.Ball;
import de.game.objects.LevelObject;
import de.game.objects.TextObject;

public class LevelController {

	public static int LEVELINDEX = 0;

	public static ArrayList<Level> LEVELS = new ArrayList<Level>();

	public LevelController(Ball player) {

		try {

			Level level00_tutorial = new Level("Tutorial", player, new Image("resource/textures/level/Background_Level_0.jpg"));
			level00_tutorial.addDynamicObject(new LevelObject("Object", new Rectangle(0, 0, 100, 100), 300, 300));
			level00_tutorial.addTextObject(new TextObject("Press LEFT mouse key to place the ball", 150, 100, Color.white));
			level00_tutorial.addTextObject(new TextObject("Release LEFT mouse key to shoot the ball", 138, 130, Color.white));
			level00_tutorial.addTextObject(new TextObject("Move the cursor for aiming", 190, 180, Color.white));
			
			
			Level level01 = new Level("Level 1", player, new Image("resource/textures/level/Level1Slick2D.png"));
			level01.addDynamicObject(new LevelObject("Linkes Rechteck", new Rectangle(0, 0, 40, 350), 100, 500));
			level01.addDynamicObject(new LevelObject("Rechtes Rechteck", new Rectangle(0, 0, 40, 350), 500, 500));

			Level level02 = new Level("Level 2", player, new Image("resource/textures/level/Level2Slick2D.png"));
			level02.addDynamicObject(new LevelObject("Linkes Rechteck", new Rectangle(0, 0, 40, 450), 100, 600));
			level02.addDynamicObject(new LevelObject("Quadrat", new Rectangle(0, 0, 120, 120), 450, 200));
			
			Level level03 = new Level("Level 3", player, new Image("resource/textures/level/Level3Slick2D.png"));
		
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
			
			Level level04 = new Level("Level 4", player, new Image("resource/textures/level/Level4Slick2D.png"));
			level04.addDynamicObject(new LevelObject("Quadrat links", new Rectangle(0, 0, 100, 100), 150, 500));
			level04.addDynamicObject(new LevelObject("Quadrat rechts unten", new Rectangle(0, 0, 100, 100), 450, 700));
			level04.addDynamicObject(new LevelObject("Quadrat rechts oben", new Rectangle(0, 0, 100, 100), 450, 300));
			
			Level level05 = new Level("Level 5", player, new Image("resource/textures/level/Background_Level_0.jpg"));
			level05.addDynamicObject(new LevelObject("Rotierendes Rechteck", new Rectangle(0, 0, 300, 40), 300, 700, 0)); //Rotieren funktioniert nicht
			level05.addDynamicObject(new LevelObject("Quadrat", new Rectangle(0, 0, 50, 50), 300, 200));
			
			Level level06 = new Level("Level 6", player, new Image("resource/textures/level/Level1Slick2D.png"));
			level06.addDynamicObject(new LevelObject("Rechteck", new Rectangle(0, 0, 20, 150), 100, 600));
			level06.addStaticObject(new LevelObject("Rechteck", new Rectangle(0, 0, 50, 300), 300, 200, Color.black));
			level06.addStaticObject(new LevelObject("Rechteck", new Rectangle(0, 0, 50, 300), 300, 600, Color.black));
			level06.addDynamicObject(new LevelObject("Dreieck", dreieck2, 500, 250));

<<<<<<< Upstream, based on origin/LevelBuilding
			Level level07 = new Level("Level 7", player, new Image("resource/textures/level/Level2Slick2D.png"));
=======
			//Winkel falsch
			Level level07 = new Level("Level 7", player, new Image("src/de/game/textures/level/Level2Slick2D.png"));
>>>>>>> ee9f31f winkel falsch
			level07.addDynamicObject(new LevelObject("Rechteck oben links", new Rectangle(0, 0, 150, 40), 150, 300, -0.7f));
			level07.addDynamicObject(new LevelObject("Rechteck unten links", new Rectangle(0, 0, 150, 40), 150, 600, 0.7f));
			level07.addDynamicObject(new LevelObject("Rechteck oben rechts", new Rectangle(0, 0, 150, 40), 450, 300, 0.7f));
			level07.addDynamicObject(new LevelObject("Rechteck unten rechts", new Rectangle(0, 0, 150, 40), 450, 600, -0.7f));
			
			Level level08 = new Level("Level 8", player, new Image("resource/textures/level/Level3Slick2D.png"));
			level08.addDynamicObject(new LevelObject("Rechteck oben links", new Rectangle(0, 0, 150, 40), 150, 300, 0.8f));
			level08.addDynamicObject(new LevelObject("Rechteck unten links", new Rectangle(0, 0, 150, 40), 150, 600, -0.8f));
			level08.addDynamicObject(new LevelObject("Rechteck oben rechts", new Rectangle(0, 0, 150, 40), 450, 300, -0.8f));
			level08.addDynamicObject(new LevelObject("Rechteck unten rechts", new Rectangle(0, 0, 150, 40), 450, 600, 0.8f));
			
			Level level09 = new Level("Level 9", player, new Image("resource/textures/level/Level4Slick2D.png"));
			level09.addDynamicObject(new LevelObject("Rechteck", new Rectangle(0, 0, 150, 40), 300, 150));
			level09.addDynamicObject(new LevelObject("Rechteck", new Rectangle(0, 0, 150, 40), 300, 350));
			level09.addDynamicObject(new LevelObject("Rechteck", new Rectangle(0, 0, 150, 40), 300, 650));
			level09.addDynamicObject(new LevelObject("Rechteck", new Rectangle(0, 0, 150, 40), 300, 850));
			
			Level level10 = new Level("Level 10", player, new Image("resource/textures/level/Background_Level_0.jpg"));
			
			Polygon poly = new Polygon();
			
			poly.addPoint(200, 150);
			poly.addPoint(400, 150);
			poly.addPoint(325, 250);
			poly.addPoint(275, 250);
			
			level10.addStaticObject(new LevelObject("Polygon", poly, 300, 150, Color.black));
			level10.addStaticObject(new LevelObject("Quadrat", new Rectangle(0, 0, 50, 50), 300, 800, Color.black));
			level10.addDynamicObject(new LevelObject("Quadrat links", new Rectangle(0, 0, 50, 50), 150, 800));
			level10.addDynamicObject(new LevelObject("Quadrat rechts", new Rectangle(0, 0, 50, 50), 450, 800));
		

			LEVELS.add(level00_tutorial);
			LEVELS.add(level01);
			LEVELS.add(level02);
			LEVELS.add(level03);
			LEVELS.add(level04);
			LEVELS.add(level05);
			LEVELS.add(level06);
			LEVELS.add(level07);
			LEVELS.add(level08);
			LEVELS.add(level09);
			LEVELS.add(level10);
			
		} catch (Exception e) {

		}
	}
}
