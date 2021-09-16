package System.objects;

import static System.Game.deviceMouse;
import static System.Game.mRenderer;
import static System.Game.mousePressedIs;
import static System.Game.mouseX;
import static System.Game.mouseY;
import static System.Game.pageChangeNum;
import static System.Game.panel;
import static System.Game.screen_height;
import static System.Game.screen_width;
import static System.Game.frame;


import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class ObjMainMenu extends Object {
	
	private Image sprVM;

	private Font fontTitle2;
	private Font fontTitle;
	private Font fontButton;
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private Font customFont = new Font("º»°íµñ",Font.PLAIN, 18);
	
	private int timeLine;
	private int easeTime;
	private int easeTime2;
	
	private double titleY;
	private double titleAlpha;
	
	private double buttonY;
	private double buttonAlpha;
	private double button2Y;
	private double button2Alpha;
	
	public ObjMainMenu() {
		Init();
		System.out.println("ObjMainMenu Created.");
	}
	
	public void finalize() {
		System.out.println("ObjMainMenu Deleted.");
	}

	@Override
	public void Init() {
		
		easeTime 	= 0;
		titleY 		= 0;
		titleAlpha  = 0;
		buttonY 	= 0;
		buttonAlpha = 0;
		button2Y 	= 0;
		button2Alpha = 0;
		
		// Custom Font Setting.
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("RecipekoreaFONT.ttf");
		try {
			fontTitle = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(48f);
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		InputStream stream2 = ClassLoader.getSystemClassLoader().getResourceAsStream("RecipekoreaFONT.ttf");
		try {
			fontTitle2 = Font.createFont(Font.TRUETYPE_FONT, stream2).deriveFont(20f);
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		fontButton = customFont;
		
		sprVM = new ImageIcon(getClass().getResource("/sprVendingMachine.png")).getImage();
		//313 x 555 pixel size.
		//sprVM = imageSetSize(sprVM, 64, 64);
		
	}

	@Override
	public void Update() {
		
		if (timeLine == 0) {
			
			titleY 		= easeInSine(easeTime, 0, 120, 60);
			titleAlpha 	= easeInSine(easeTime, 0, 1, 60);
			
			if (easeTime < 60)
				easeTime++;
			else {
				timeLine = 1;
				easeTime = 0;
			}
			
		} else if (timeLine == 1) {
			
			buttonY 		= easeInSine(easeTime, 0, -50, 45);
			buttonAlpha 	= easeInSine(easeTime, 0, 0.5, 45);
			
			if (easeTime < 45)
				easeTime++;
			
		}
		
		if (timeLine == 1 && easeTime > 25) {
			
			button2Y 		= easeInSine(easeTime2, 0, -50, 45);
			button2Alpha 	= easeInSine(easeTime2, 0, 0.5, 45);
			
			if (easeTime2 < 45)
				easeTime2++;
			else
				timeLine = 2;
			
		}
		if (timeLine == 2) {
			if (mouseX >= screen_width/2 - 128 && mouseY >= screen_height/2 + 32 && mouseX <= screen_width/2 + 128 && mouseY <= screen_height/2 + 72) {
				if (buttonAlpha < 1.0)
					buttonAlpha += 0.05;
				if (button2Alpha > 0.5)
					button2Alpha -= 0.05;
			} else if (mouseX >= screen_width/2 - 128 && mouseY >= screen_height/2 + 80 && mouseX <= screen_width/2 + 128 && mouseY <= screen_height/2 + 122) {
				if (button2Alpha < 1.0)
					button2Alpha += 0.05;
				if (buttonAlpha > 0.5)
					buttonAlpha -= 0.05;
			} else {
				if (buttonAlpha > 0.5)
					buttonAlpha -= 0.05;
				if (button2Alpha > 0.5)
					button2Alpha -= 0.05;
			}
		}
		
	}

	@Override
	public void Render() {

		drawSetAlpha(0.1);
		drawSprite(sprVM, screen_width/2 - 156, screen_height/2 - 260);
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(titleAlpha);
		mRenderer.setFont(fontTitle);

		drawTextCenter("VENDING MACHINE", screen_width/2, (int) (screen_height/2 + titleY - 144), fontTitle);
		
		mRenderer.setFont(fontTitle2);
		
		drawTextCenter("EXIT", screen_width - 80, 32, fontTitle2);
		
		drawSetAlpha(buttonAlpha);
		mRenderer.setFont(fontButton);
		drawTextCenter("Getting Start", screen_width/2, (int) (screen_height/2 + buttonY + 102), fontButton);

		drawSetAlpha(button2Alpha);
		drawTextCenter("Manager Page", screen_width/2, (int) (screen_height/2 + button2Y + 150), fontButton);

	}
	
	@Override
	public void mouseCheck() {
		
	}
	
	@Override
	public void mousePressed() {
		
		if (mouseX >= screen_width - 128 && 
			mouseY >= 0 && 
			mouseX <= screen_width && 
			mouseY <= 48 ) {
					
			System.exit(0);
					
		}
		
		if (mouseX >= screen_width/2 - 128 && mouseY >= screen_height/2 + 32 && mouseX <= screen_width/2 + 128 && mouseY <= screen_height/2 + 72)
			pageChangeNum = 2;
		else if (mouseX >= screen_width/2 - 128 && mouseY >= screen_height/2 + 80 && mouseX <= screen_width/2 + 128 && mouseY <= screen_height/2 + 120)
			pageChangeNum = 3;
	}
	
	@Override
	public void mouseReleased() {

	}
	
}
