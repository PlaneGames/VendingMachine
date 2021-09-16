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

public class ObjDrinkRoyalCoffee extends ObjDrinkPar {
	 
	public ObjDrinkRoyalCoffee() {
		spriteIndex = new ImageIcon(Game.class.getResource("/sprCoffeeIcon.png")).getImage();
		Init();
		System.out.println(name + " Created.");
	}
	
	@Override
	public void finalize() {
		System.out.println(name + " Deleted.");
	}
	
	@Override
	public void Init() {
		drinkCode		= 3;
		leftNum			= drinkNum[3];
		name 			= "ROYAL COFFEE";
		xOffSet			= 0;
		yOffSet			= 1;
		price			= 700;
	}
	
}
