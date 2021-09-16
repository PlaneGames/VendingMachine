package System.objects.drinks;

import static System.Game.drinkNum;
import static System.Game.mRenderer;
import static System.Game.mouseX;
import static System.Game.mouseY;
import static System.Game.screen_height;
import static System.Game.screen_width;
import static System.objects.ObjVMMgr.enteredMoney;
import static System.Game.coin;
import static System.Game.tempCoin;
import static System.Game.coinList;
import static System.Game.coinNumber;
import static System.Game.drinkName;
import static System.Game.drinkPrice;
import static System.Game.totalMoney;
import static System.objects.ObjVMMgr.enteredMoneyNum1000;
import static System.objects.ObjVMMgr.enteredMoneyNum500;
import static System.objects.ObjVMMgr.enteredMoneyNum100;
import static System.objects.ObjVMMgr.enteredMoneyNum50;
import static System.objects.ObjVMMgr.enteredMoneyNum10;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import System.Game;
import System.objects.Object;
import System.objects.coins.ObjCoinPar;

public class ObjDrinkPar extends Object {
	
	// Drink Info.
	public int drinkCode;
	public String name;
	public Image spriteIndex;
	public double x;
	public double y;
	public Image sprDrinksShadow = new ImageIcon(Game.class.getResource("/sprDrinksShadow.png")).getImage();;
	public double imageAlpha;
	public double nameAlpha;
	public int leftNum;
	public int price;
	public int drinkIndex = 0;
	public boolean checkMouseIsOn;
	
	public int xOffSet;
	public int yOffSet;
	
	// Render Var.
	public Color colorCanBuy 	= new Color(17, 166, 190);
	public Color colorCantBuy 	= new Color(233, 78, 76);
	public int easeTime;
	public Font fontName = new Font("º»°íµñ",Font.BOLD, 14);
	public Font fontSoldout = new Font("º»°íµñ",Font.BOLD, 18);
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
	// Func.
	double Lerp(double A, double B, double d) { return A * (1 - d) + B * d; }
		
	public ObjDrinkPar() {
		
		x = 100;
		y = screen_height/2 - 100;
		Init();

	}
	
	public void finalize() {

	}
	
	@Override
	public void Init() {

		imageAlpha 		= 0.0f;
		nameAlpha 		= 0.0f;
		leftNum			= 0;
		
		easeTime 		= 0;
		checkMouseIsOn 	= false;
		
		name			= drinkName[drinkCode];
		price			= drinkPrice[drinkCode];

	}

	@Override
	public void Update() {
		
		name			= drinkName[drinkCode];
		price			= drinkPrice[drinkCode];
		
		if ((mouseX >= x && mouseY >= screen_height/2 - 104 + (yOffSet * 190) && mouseX <= x + spriteIndex.getWidth(this) && mouseY <= screen_height/2 - 44 + spriteIndex.getHeight(this) + (yOffSet * 190)))
			checkMouseIsOn = true;
		else
			checkMouseIsOn = false;
		
		if (enteredMoney >= price && drinkNum[drinkCode] > 0) {
			if (checkMouseIsOn) {
				
				if (nameAlpha < 1.00) {
					nameAlpha += 0.1;
				}
				if (imageAlpha < 1.00) {
					imageAlpha += 0.1;
				}
				y = Lerp(y, screen_height/2 - 110 + (yOffSet * 190), 0.2d);
	
			} else {
				
				if (nameAlpha > 0.5) {
					nameAlpha -= 0.1;
				}
				if (nameAlpha < 0.5) {
					nameAlpha = 0.5;
				}
				if (imageAlpha > 0.80) {
					imageAlpha -= 0.1;
				}
				y = Lerp(y, screen_height/2 - 86 + (yOffSet * 190), 0.2d);
	
			}
		} else {
			
			if (nameAlpha > 0.5) {
				nameAlpha -= 0.1;
			}
			if (nameAlpha < 0.5) {
				nameAlpha = 0.5;
			}
			if (imageAlpha > 0.80) {
				imageAlpha -= 0.1;
			}
			y = Lerp(y, screen_height/2 - 86 + (yOffSet * 190), 0.2d);
			
		}
		
		if (easeTime < 60) {
			
			easeTime++;
			imageAlpha = easeInSine(easeTime, 0, 0.8, 60);
			x = (int) Math.round(easeInSine(easeTime, -100 + screen_width/2 + 5 + (xOffSet * 118), 96, 60));

		}

	}

	@Override
	public void Render() {
		
		drawSetAlpha(1.0f);
		drawSprite(sprDrinksShadow, (int) (x), (int) (screen_height/2 + 8 + (yOffSet * 190)));
		
		if (drinkNum[drinkCode] != 0) 
			drawSetAlpha(imageAlpha);
		else if (drinkNum[drinkCode] == 0) 
			drawSetAlpha(imageAlpha * 0.4);
		
		drawSprite(spriteIndex, (int) (x - 5), (int) (Math.round(y)));
		
		drawSetColor(Color.WHITE);
		
		if (drinkNum[drinkCode] != 0) 
			drawSetAlpha(nameAlpha);
		else if (drinkNum[drinkCode] == 0) 
			drawSetAlpha(nameAlpha * 0.4);
		
		mRenderer.setFont(fontName);
		drawTextCenter(name, (int) (x + 30), (int) (screen_height/2 + 64 + (yOffSet * 190)), fontName);
		
		if (enteredMoney >= price && drinkNum[drinkCode] > 0)
			drawSetColor(colorCanBuy);
		else
			drawSetColor(colorCantBuy);
		drawTextCenter(String.valueOf(price), (int) (x + 30), (int) (screen_height/2 + 90 + (yOffSet * 190)), fontName);

		if (drinkNum[drinkCode] == 0) {
			drawSetAlpha(0.9f);
			drawSetColor(new Color(16, 17, 30));
			drawTextCenter("SOLD OUT!", (int) (x + 32), (int) (screen_height/2 - 14 + (yOffSet * 190)), fontSoldout);
			drawSetColor(colorCantBuy);
			drawTextCenter("SOLD OUT!", (int) (x + 30), (int) (screen_height/2 - 16 + (yOffSet * 190)), fontSoldout);
		}
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(1.0f);

	}

	@Override
	public void mouseCheck() {

	}
	
	@Override
	public void mousePressed() {
		
		if (checkMouseIsOn) {
			
			if (drinkNum[drinkCode] > 0) {
				if (enteredMoney >= price) {
					
					enteredMoney -= price;
					drinkNum[drinkCode] -= 1;
					
				}
			}
			
		}
		
	}
	
	@Override
	public void mouseReleased() {

	}
	
}
