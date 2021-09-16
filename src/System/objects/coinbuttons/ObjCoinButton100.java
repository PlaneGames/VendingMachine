package System.objects.coinbuttons;

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

public class ObjCoinButton100 extends ObjCoinButtonPar {
	 
	public ObjCoinButton100() {
		spriteIndex = new ImageIcon(Game.class.getResource("/sprCoin100.png")).getImage();
		sprShadow = new ImageIcon(Game.class.getResource("/sprCoin100Shadow.png")).getImage();
		Init();
		System.out.println(name + " Created.");
	}
	
	@Override
	public void finalize() {
		System.out.println(name + " Deleted.");
	}
	
	@Override
	public void Init() {
		value			= 100;
		name 			= "100 WON";
		xOffSet			= 1;
		yOffSet			= 0;
	}
	
	@Override
	public void mouseCheck() {
		
	}
	
	@Override
	public void mouseReleased() {

	}
	
}
