package System.objects.drinks;

import static System.Game.drinkNum;
import static System.Game.mRenderer;
import static System.Game.mouseX;
import static System.Game.mouseY;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;

import System.Game;

public class ObjDrinkWater extends ObjDrinkPar {
	 
	public ObjDrinkWater() {
		spriteIndex = new ImageIcon(Game.class.getResource("/sprWaterIcon.png")).getImage();
		Init();
		System.out.println(name + " Created.");
	}
	
	@Override
	public void finalize() {
		System.out.println(name + " Deleted.");
	}
	
	@Override
	public void Init() {
		drinkCode		= 0;
		leftNum			= drinkNum[0];
		name 			= "WATER";
		xOffSet			= 0;
		yOffSet			= 0;
		price			= 450;
	}
	
}
