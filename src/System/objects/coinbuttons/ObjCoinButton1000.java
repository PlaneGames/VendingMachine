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

public class ObjCoinButton1000 extends ObjCoinButtonPar {
	 
	public ObjCoinButton1000() {
		spriteIndex = new ImageIcon(Game.class.getResource("/sprBill.png")).getImage();
		sprShadow = new ImageIcon(Game.class.getResource("/sprBillShadow.png")).getImage();
		Init();
		System.out.println(name + " Created.");
	}
	
	@Override
	public void finalize() {
		System.out.println(name + " Deleted.");
	}
	
	@Override
	public void Init() {
		value			= 1000;
		name 			= "1000 WON";
		xOffSet			= 2;
		yOffSet			= 0;
	}
	
	@Override
	public void mouseCheck() {
		
	}

	@Override
	public void mouseReleased() {

	}
	
}
