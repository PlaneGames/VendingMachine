package System.objects.coins;

import static System.Game.mRenderer;
import static System.Game.mouseX;
import static System.Game.mouseY;
import static System.Game.screen_height;
import static System.Game.screen_width;
import static System.objects.ObjVMMgr.enteredMoney;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;

import System.Game;
import System.objects.Object;

public class ObjCoinPar extends Object {
		
	public int value;
	
	public ObjCoinPar(int v) {
		value = v;
		Init();
	}
	
	public void finalize() {
	}
	
	@Override
	public void Init() {
	}

	@Override
	public void Update() {
	}

	@Override
	public void Render() {
	}
	
	@Override
	public void mouseCheck() {
	}
	
	@Override
	public void mousePressed() {
	}
	
	@Override
	public void mouseReleased() {
	}
	
}
