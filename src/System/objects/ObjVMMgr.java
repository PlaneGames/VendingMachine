package System.objects;

import static System.Game.mRenderer;
import static System.Game.mouseX;
import static System.Game.mouseY;
import static System.Game.screen_height;
import static System.Game.screen_width;
import static System.Game.coin;
import static System.Game.tempCoin;
import static System.Game.coinList;
import static System.Game.coinNumber;
import static System.Game.totalMoney;
import static System.Game.getCoinNum;
import static System.Game.removeCoinNum;
import static System.Game.pageChangeNum;
import static System.Game.pageInfo;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;

import System.Game;
import System.objects.coinbuttons.ObjCoinButton10;
import System.objects.coinbuttons.ObjCoinButton100;
import System.objects.coinbuttons.ObjCoinButton1000;
import System.objects.coinbuttons.ObjCoinButton50;
import System.objects.coinbuttons.ObjCoinButton500;
import System.objects.coins.ObjCoinPar;
import System.objects.drinks.ObjDrinkCoffee;
import System.objects.drinks.ObjDrinkRoyalCoffee;
import System.objects.drinks.ObjDrinkSoda;
import System.objects.drinks.ObjDrinkSports;
import System.objects.drinks.ObjDrinkWater;

public class ObjVMMgr extends Object {
	
	private Image sprVM;
	
	private Font fontTitle;
	private Font fontButton;
	private Font fontEnteredMoney;
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	private Font customFont = new Font("º»°íµñ",Font.BOLD, 14);
	
	public static int enteredMoney;
	
	public int timeLine;
	public int easeTime;
	
	private double titleY;
	private double titleAlpha;
	
	private double sprVMX;

	public static int enteredMoneyNum1000;
	public static int enteredMoneyNum500;
	public static int enteredMoneyNum100;
	public static int enteredMoneyNum50;
	public static int enteredMoneyNum10;
	
	public ObjVMMgr() {
		Init();
		System.out.println("ObjVMMgr Created.");
	}
	
	//public void finalize() {
	//	System.out.println("ObjVMMgr Deleted.");
	//}

	@Override
	public void Init() {
		
		enteredMoney = 0;
		fontEnteredMoney = new Font("º»°íµñ",Font.BOLD, 10);
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("RecipekoreaFONT.ttf");
		timeLine	= 0;
		easeTime 	= 0;

		// Custom Font Setting.
		try {
			fontTitle = Font.createFont(Font.TRUETYPE_FONT, stream).deriveFont(20f);
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		fontButton = customFont;

		sprVM = new ImageIcon(Game.class.getResource("/sprVendingMachine.png")).getImage();
		
		// Coin Setting
		
		enteredMoneyNum1000 = 0;
		enteredMoneyNum500	= 0;
		enteredMoneyNum100	= 0;
		enteredMoneyNum50	= 0;
		enteredMoneyNum10	= 0;

	}

	@Override
	public void Update() {
		
		if (timeLine == 0) {
			
			if (easeTime < 30)
				easeTime++;
			else {
				timeLine = 1;
				easeTime = 0;
			}
			
		} else if (timeLine == 1) {
			
			sprVMX 	= Math.round(easeInSine(easeTime, 0, -244, 120));
			titleAlpha 	= easeInSine(easeTime, 0, 1, 120);
			
			if (easeTime < 120)
				
				easeTime++;
			
			else {
				
				// Create Coin Buttons
				pageInfo.instanceCreate(new ObjCoinButton1000());
				pageInfo.instanceCreate(new ObjCoinButton500());
				pageInfo.instanceCreate(new ObjCoinButton100());
				pageInfo.instanceCreate(new ObjCoinButton50());
				pageInfo.instanceCreate(new ObjCoinButton10());

				// Create Drinks
				pageInfo.instanceCreate(new ObjDrinkWater());
				pageInfo.instanceCreate(new ObjDrinkCoffee());
				pageInfo.instanceCreate(new ObjDrinkSports());
				pageInfo.instanceCreate(new ObjDrinkRoyalCoffee());
				pageInfo.instanceCreate(new ObjDrinkSoda());
				
				timeLine = 2;
				easeTime = 0;

			}
			
		}
		
	}

	@Override
	public void Render() {
		
		drawSetAlpha(titleAlpha);
		drawSprite(sprVM, screen_width/2 - 156 + (int)sprVMX, screen_height/2 - 260);

		drawSetColor(new Color(245, 154, 68));
		drawTextCenter(String.valueOf(enteredMoney), screen_width/2 + 97 + (int)sprVMX, screen_height/2 - 100, fontEnteredMoney);

		drawSetColor(Color.WHITE);
		drawSetAlpha(titleAlpha);
		mRenderer.setFont(fontTitle);
		drawTextCenter("VENDING MACHINE", 132, 32, fontTitle);
		drawTextCenter("BACK", screen_width - 80, 32, fontTitle);
		
		/*
		drawSetAlpha(titleAlpha/5);
		mRenderer.setFont(fontButton);
		drawTextCenter("LEFT MONEY", screen_width - 96, 58, fontButton);
		drawSetColor(Color.GREEN);
		drawSetAlpha(titleAlpha/1.1);
		drawTextCenter(String.valueOf(totalMoney), screen_width - 96, 32, fontTitle);
		*/
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(titleAlpha/5);
		mRenderer.setFont(fontButton);
		drawTextCenter("ENTERED MONEY", screen_width/2 + 48, screen_height/2 - 200, fontButton);
		drawSetColor(new Color(245, 154, 68));
		drawSetAlpha(titleAlpha/1.1);
		drawTextCenter(String.valueOf(enteredMoney), screen_width/2 + 48, screen_height/2 - 226, fontTitle);
		
		drawSetAlpha(titleAlpha/1.5);
		drawSetColor(new Color(245, 154, 68));
		drawFill(screen_width/2 + 48 - 59, screen_height/2 - 170, 118, 26);
		
		drawSetAlpha(titleAlpha/1.0);
		drawSetColor(new Color(16, 17, 30));
		drawTextCenter("GET CHANGE", screen_width/2 + 48, screen_height/2 - 157, fontButton);
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(titleAlpha/5);
		drawTextCenter("ENTER MONEY", screen_width/2 + 250, screen_height/2 - 270, fontButton);
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(titleAlpha);
		
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
			
			pageChangeNum = 1;
			
		}
		
		if (mouseX >= screen_width/2 - 11 && mouseY >= screen_height/2 - 170 && mouseX <= screen_width/2 - 11 + 118 && mouseY <= screen_height/2 - 170 + 26) {

			int changeMoneyNum500	= 0;
			int changeMoneyNum100	= 0;
			int changeMoneyNum50	= 0;
			int changeMoneyNum10	= 0;
			
			changeMoneyNum500 	= enteredMoney / 500;
			changeMoneyNum100 	= (enteredMoney % 500) / 100;
			changeMoneyNum50 	= ((enteredMoney % 500) % 100) / 50;
			changeMoneyNum10 	= (((enteredMoney % 500) % 100) % 50) / 10;
			
			System.out.println("¹ÝÈ¯ÇÒ 500¿ø °¹¼ö : " + changeMoneyNum500);
			System.out.println("¹ÝÈ¯ÇÒ 100¿ø °¹¼ö : " + changeMoneyNum100);
			System.out.println("¹ÝÈ¯ÇÒ 50¿ø °¹¼ö  : " + changeMoneyNum50);
			System.out.println("¹ÝÈ¯ÇÒ 10¿ø °¹¼ö  : " + changeMoneyNum10);
			
			enteredMoney -= 500 * changeMoneyNum500;
			removeCoinNum(500, changeMoneyNum500);
			
			enteredMoney -= 100 * changeMoneyNum100;
			removeCoinNum(100, changeMoneyNum100);
			
			enteredMoney -= 50 * changeMoneyNum50;
			removeCoinNum(100, changeMoneyNum50);
			
			enteredMoney -= 10 * changeMoneyNum10;
			removeCoinNum(100, changeMoneyNum10);
			
			System.out.println("LEFT 1000 WON NUMBER : " + getCoinNum(1000));
			System.out.println("LEFT 500 WON NUMBER : " + getCoinNum(500));
			System.out.println("LEFT 100 WON NUMBER : " + getCoinNum(100));
			System.out.println("LEFT 50 WON NUMBER  : " + getCoinNum(50));
			System.out.println("LEFT 10 WON NUMBER  : " + getCoinNum(10));
			
			enteredMoneyNum1000 = 0;
			enteredMoneyNum500 = 0;
			enteredMoneyNum100 = 0;
			enteredMoneyNum50 = 0;
			enteredMoneyNum10 = 0;
			
			/* PLAN B ////////////////////////////////////////////////////////////////////
						ArrayList<Integer> coin1000List = new ArrayList<Integer>();
			ArrayList<Integer> coin500List = new ArrayList<Integer>();
			ArrayList<Integer> coin100List = new ArrayList<Integer>();
			ArrayList<Integer> coin50List = new ArrayList<Integer>(); 
			ArrayList<Integer> coin10List = new ArrayList<Integer>();
			
			int coinNum1000 	= 0;
			int coinNum500 		= 0;
			int coinNum100 		= 0;
			int coinNum50 		= 0;
			int coinNum10 		= 0;
			int tempCoinIndex 	= 0;
			
			//int changeMoneyNum1000  = 0;
			int changeMoneyNum500	= 0;
			int changeMoneyNum100	= 0;
			int changeMoneyNum50	= 0;
			int changeMoneyNum10	= 0;
			int i					= 0;
			
			//changeMoneyNum1000 = enteredMoney % 1000;
			changeMoneyNum500 	= enteredMoney / 500;
			changeMoneyNum100 	= (enteredMoney % 500) / 100;
			changeMoneyNum50 	= ((enteredMoney % 500) % 100) / 50;
			changeMoneyNum10 	= (((enteredMoney % 500) % 100) % 50) / 10;
			
			System.out.println("¹ÝÈ¯ÇÒ 500¿ø °¹¼ö : " + changeMoneyNum500);
			System.out.println("¹ÝÈ¯ÇÒ 100¿ø °¹¼ö : " + changeMoneyNum100);
			System.out.println("¹ÝÈ¯ÇÒ 50¿ø °¹¼ö : " + changeMoneyNum50);
			System.out.println("¹ÝÈ¯ÇÒ 10¿ø °¹¼ö : " + changeMoneyNum10);
			
			for (i = 0; i < coinNumber; i ++) {
	
				tempCoin = (ObjCoinPar)coinList.get(i);
				
				if (tempCoin.value == 1000) {
					coin1000List.add(coinNum1000, i);
					coinNum1000 ++;
				} else if (tempCoin.value == 500) {
					coin500List.add(coinNum500, i);
					coinNum500 ++;
				} else if (tempCoin.value == 100) {
					coin100List.add(coinNum100, i);
					coinNum100 ++;
				} else if (tempCoin.value == 50) {
					coin50List.add(coinNum50, i);
					coinNum50 ++;
					//System.out.println("50¿ø ÀÎµ¦½º : " + i);
				} else if (tempCoin.value == 10) {
					coin10List.add(coinNum10, i);
					coinNum10 ++;
				}
	
			}
			
			for (i = 0; i < changeMoneyNum500; i ++) {
				
				enteredMoney 		-= 500;
				coinNum500 			-= 1;
				tempCoinIndex 		= coin500List.get(coinNum500);
				
				coin500List.remove(coinNum500);
				coinList.remove(tempCoinIndex);
				coinNumber--;
				
			}
			for (i = 0; i < changeMoneyNum100; i ++) {
				
				enteredMoney 		-= 100;
				coinNum100 			-= 1;
				tempCoinIndex 		= coin100List.get(coinNum100);
				System.out.println("Remove 100 Index : " + tempCoinIndex);
				coin100List.remove(coinNum100);
				coinList.remove(tempCoinIndex);
				coinNumber--;
				
			}
			for (i = 0; i < changeMoneyNum50; i ++) {
				
				enteredMoney 		-= 50;
				coinNum50 			-= 1;
				tempCoinIndex 		= coin50List.get(coinNum50);
				System.out.println("Remove 50 Index : " + tempCoinIndex);
				coin50List.remove(coinNum50);
				coinList.remove(tempCoinIndex);
				coinNumber--;
				
			}
			for (i = 0; i < changeMoneyNum10; i ++) {
				
				enteredMoney 		-= 10;
				coinNum10 			-= 1;
				tempCoinIndex 		= coin10List.get(coinNum10);
				System.out.println("Remove 10 Index : " + tempCoinIndex);
				coin10List.remove(coinNum10);
				coinList.remove(tempCoinIndex);
				coinNumber--;
				
			}
			*/ /////////////////////////////////////////////////////////////////////////////
					
		}
		
	}
	
	@Override
	public void mouseReleased() {

	}
	
}
