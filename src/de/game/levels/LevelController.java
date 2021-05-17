package de.game.levels;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.geom.Circle;
import org.newdawn.slick.geom.Rectangle;

import de.game.objects.Ball;
import de.game.objects.LevelObject;

public class LevelController {

	public static int LEVELINDEX = 0;

	public static ArrayList<Level> LEVELS = new ArrayList<Level>();

	public LevelController(Ball player) {

		try {

			Level level00_tutorial = new Level("Tutorial", player, new Image("src/de/game/textures/level/Background_Level_0.jpg"));
			level00_tutorial.addDynamicObject(new LevelObject("Object", new Rectangle(300, 300, 100, 100), 300, 300));
			level00_tutorial.addStaticObject(new LevelObject("Kreis", new Circle(300, 800, 20), 300, 800, Color.green));

			Level level01 = new Level("Level 1", player, new Image("src/de/game/textures/level/Level1Slick2D.png"));
			level01.addDynamicObject(new LevelObject("Linkes Rechteck", new Rectangle(150, 300, 100, 400), 300, 300));

			Level level02 = new Level("Level 2", player, new Image("src/de/game/textures/level/Level2Slick2D.png"));

			LEVELS.add(level00_tutorial);
			LEVELS.add(level01);
			LEVELS.add(level02);
			
		} catch (Exception e) {

		}
	}
}