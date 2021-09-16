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
import static System.Game.coin;
import static System.Game.tempCoin;
import static System.Game.coinList;
import static System.Game.coinNumber;
import static System.Game.totalMoney;
import static System.Game.getCoinNum;
import static System.Game.loginTextField;
import static System.Game.drinkNum;
import static System.Game.frame;
import static System.Game.removeCoinNum;
import static System.Game.editDrinkTextField;
import static System.Game.editDrinkTextField2;
import static System.Game.editDrinkTextField3;
import static System.Game.editDrinkTextField4;
import static System.Game.editDrinkTextField5;

import static System.Game.editPriceTextField;
import static System.Game.editPriceTextField2;
import static System.Game.editPriceTextField3;
import static System.Game.editPriceTextField4;
import static System.Game.editPriceTextField5;

import static System.Game.drinkName;
import static System.Game.drinkPrice;

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
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.ImageIcon;
import com.*;

import System.Game;
import System.objects.coins.ObjCoinPar;

public class ObjVMManagement extends Object {
	
	private Image sprVMIcon;
	private Image sprCheckBox;
	private Image sprCheckBoxCheck;
	private Image sprLineButton;
	
	private double mainTitleAlpha = 0.0;
	private double ASCButtonAlpha = 0.0;
	private double RCButtonAlpha = 0.0;
	
	private double fillInAlpha = 0.0;
	private double [] checkBoxAlpha = {0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3, 0.3};
	private double [] checkBoxCheckAlpha = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
	private boolean [] checkBoxCheck = {false, false, false, false, false, false, false, false, false, false};
	
	// Can Add Selected Commodity.
	private boolean checkCanASC;
	
	// Can Reset Change
	private boolean checkCanRC;
	
	private Font fontTitle;
	private Font fontButton;
	private Font fontTitle2 	= new Font("º»°íµñ",Font.TRUETYPE_FONT, 22);
	private Font fontMiniTitle 	= new Font("º»°íµñ",Font.BOLD, 12);
	private Font fontLore		= new Font("º»°íµñ",Font.BOLD, 17);
	GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
	
	private int easeTime;
	private int timeLine;
	
	private int moneyNum1000;
	private int moneyNum500;
	private int moneyNum100;
	private int moneyNum50;
	private int moneyNum10;
	
	private boolean editMode = false;
	
	String drinkT, drinkT2, drinkT3, drinkT4, drinkT5;
	int priceT, priceT2, priceT3, priceT4, priceT5;
	
	/*
	public void printLabel(String str, int x, int y, double alpha) {
		drawSetColor(Color.WHITE);
		drawSetAlpha(alpha);
		mRenderer.setFont(fontLore);
		drawText(str, x, y);
		drawSetColor(Color.WHITE);
		drawSetAlpha(1.0);
	}*/
	
	public ObjVMManagement() {
		Init();
		System.out.println("ObjVMManagement Created.");
	}
	
	public void finalize() {
		System.out.println("ObjVMManagement Deleted.");
	}

	@Override
	public void Init() {
		
		//fontButton = new Font("º»°íµñ",Font.BOLD, 10);
		InputStream stream = ClassLoader.getSystemClassLoader().getResourceAsStream("RecipekoreaFONT.ttf");

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
		
		InputStream stream2 = ClassLoader.getSystemClassLoader().getResourceAsStream("RecipekoreaFONT.ttf");
		try {
			fontButton = Font.createFont(Font.TRUETYPE_FONT, stream2).deriveFont(13f);
		} catch (FontFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		sprVMIcon = new ImageIcon(Game.class.getResource("/sprVendingMachine.png")).getImage();
		sprVMIcon = imageSetSize(sprVMIcon, sprVMIcon.getWidth(this)/24, sprVMIcon.getHeight(this)/24);
		
		sprCheckBox 		= new ImageIcon(Game.class.getResource("/sprCheckBox.png")).getImage();
		sprCheckBoxCheck 	= new ImageIcon(Game.class.getResource("/sprCheckBoxCheck.png")).getImage();
		
		sprLineButton 		= new ImageIcon(Game.class.getResource("/sprLineButton.png")).getImage();
		
		easeTime = 0;
		timeLine = 0;
		
		checkCanASC = false;
		
		moneyNum1000 = getCoinNum(1000);
		moneyNum500 = getCoinNum(500);
		moneyNum100 = getCoinNum(100);
		moneyNum50 = getCoinNum(50);
		moneyNum10 = getCoinNum(10);
		
	}

	@Override
	public void Update() {
		
		if (mainTitleAlpha < 1.0)
			mainTitleAlpha += 0.01;
		
		if (fillInAlpha < 1.0)
			fillInAlpha += 0.01;
		
		if (timeLine == 0) {

			if (easeTime < 60) {
				easeTime++;
			} else {
				easeTime = 0;
				timeLine = 1;
			}
			
		} else if (timeLine == 1) {
			
		}
		
		int xOffSet = 0;
		int yOffSet = 0;
		checkCanASC = false;
		
		for (int i = 0; i < 5; i ++) {
			switch(i) {
				case 0: xOffSet = 0; yOffSet = 0; break;
				case 1: xOffSet = 1; yOffSet = 0; break;
				case 2: xOffSet = 2; yOffSet = 0; break;
				case 3: xOffSet = 0; yOffSet = 1; break;
				case 4: xOffSet = 1; yOffSet = 1; break;
			}
			
			if (mouseX >= screen_width/2 - 267 + (180 * xOffSet) && 
				mouseY >= screen_height/2 - 148 + (60 * yOffSet) && 
				mouseX <= screen_width/2 - 267 + 140 + (180 * xOffSet) && 
				mouseY <= screen_height/2 - 148 + 50 + (60 * yOffSet)) {
				if (checkBoxAlpha[i] < 0.9)
					checkBoxAlpha[i] += 0.1;
			} else {
				if (checkBoxAlpha[i] > 0.3)
					checkBoxAlpha[i] -= 0.05;
			}
			
			if (checkBoxCheck[i]) {
				checkCanASC = true;
				if (checkBoxCheckAlpha[i] < 0.9)
					checkBoxCheckAlpha[i] += 0.25;
			} else {
				if (checkBoxCheckAlpha[i] > 0.0)
					checkBoxCheckAlpha[i] -= 0.25;
				if (checkBoxCheckAlpha[i] < 0.0)
					checkBoxCheckAlpha[i] = 0.0;
			}
		}
		if (checkCanASC) {
			if (ASCButtonAlpha < 0.9)
				ASCButtonAlpha += 0.1;
		} else {
			if (ASCButtonAlpha > 0.3)
				ASCButtonAlpha -= 0.1;
			if (ASCButtonAlpha < 0.3)
				ASCButtonAlpha = 0.3;
		}
		
		checkCanRC = false;
		for (int i = 5; i < 10; i ++) {
			switch(i - 5) {
				case 0: xOffSet = 0; yOffSet = 0; break;
				case 1: xOffSet = 1; yOffSet = 0; break;
				case 2: xOffSet = 2; yOffSet = 0; break;
				case 3: xOffSet = 0; yOffSet = 1; break;
				case 4: xOffSet = 1; yOffSet = 1; break;
			}
			
			if (mouseX >= screen_width/2 - 267 + (180 * xOffSet) && 
				mouseY >= screen_height/2 + 24 + (60 * yOffSet) && 
				mouseX <= screen_width/2 - 267 + 140 + (180 * xOffSet) && 
				mouseY <= screen_height/2 + 24 + 50 + (60 * yOffSet)) {
				if (checkBoxAlpha[i] < 0.9)
					checkBoxAlpha[i] += 0.1;
			} else {
				if (checkBoxAlpha[i] > 0.3)
					checkBoxAlpha[i] -= 0.05;
			}
			
			if (checkBoxCheck[i]) {
				checkCanRC = true;
				if (checkBoxCheckAlpha[i] < 0.9)
					checkBoxCheckAlpha[i] += 0.25;
			} else {
				if (checkBoxCheckAlpha[i] > 0.0)
					checkBoxCheckAlpha[i] -= 0.25;
				if (checkBoxCheckAlpha[i] < 0.0)
					checkBoxCheckAlpha[i] = 0.0;
			}
		}
		
		if (checkCanRC) {
			if (RCButtonAlpha < 0.9)
				RCButtonAlpha += 0.1;
		} else {
			if (RCButtonAlpha > 0.3)
				RCButtonAlpha -= 0.1;
			if (RCButtonAlpha < 0.3)
				RCButtonAlpha = 0.3;
		}
		
	}

	@Override
	public void Render() {
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(1.0f);
		mRenderer.setFont(fontTitle);
		drawTextCenter("VENDING MACHINE", 132, 32, fontTitle);
		
		drawTextCenter("BACK", screen_width - 80, 32, fontTitle);
		
		drawSetColor(new Color(34, 34, 48));
		drawSetAlpha(mainTitleAlpha);
		drawFill(screen_width/2 - 330, screen_height/2 - 300 + 15, 890, 600);
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(mainTitleAlpha * 0.6);
		mRenderer.setFont(fontButton);
		//VENDING MACHINE
		drawTextCenter("VENDING MACHINE", screen_width/2 - 485, screen_height/2 - 230, fontButton);
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(mainTitleAlpha * 0.6);
		mRenderer.setFont(fontButton);
		drawTextCenter("INCOME STATISTIC", screen_width/2 - 485, screen_height/2 - 170, fontButton);
	
		drawSetColor(Color.WHITE);
		drawSetAlpha(fillInAlpha * 0.9);
		mRenderer.setFont(fontTitle2);
		drawTextCenter("VENDING MACHINE", screen_width/2 - 150, screen_height/2 - 234, fontTitle2);
		
		drawSetAlpha(fillInAlpha);
		drawSprite(sprVMIcon, screen_width/2 - 280, screen_height/2 - 246);
		
		drawSetColor(new Color(87, 101, 116));
		drawSetAlpha(fillInAlpha);
		mRenderer.setFont(fontMiniTitle);
		drawText("DRINKS", screen_width/2 - 280, screen_height/2 - 174);
		drawSetAlpha(fillInAlpha * 0.2);
		mRenderer.drawLine(screen_width/2 - 280, screen_height/2 - 164, screen_width/2 + 510, screen_height/2 - 164);
		mRenderer.drawLine(screen_width/2 - 280, screen_height/2 - 163, screen_width/2 + 510, screen_height/2 - 163);
		
		drawSetAlpha(fillInAlpha * 0.8);
		drawSetColor(new Color(87, 101, 116));
		mRenderer.setFont(fontMiniTitle);
		drawText("TOTAL LEFT", screen_width/2 - 180, screen_height/2 - 174);
		drawSetColor(Color.WHITE);
		
		if (!editMode)
			drawText("EDIT", screen_width/2 + 424, screen_height/2 - 174);
		else
			drawText("EDIT END", screen_width/2 + 424, screen_height/2 - 174);
		
		drawSetColor(new Color(83, 160, 255));
		drawSetAlpha(fillInAlpha * 0.8);
		int total;
		
		total = ((drinkNum[0]) + (drinkNum[1]) + (drinkNum[2]) + (drinkNum[3]) + (drinkNum[4]));
		drawText(String.valueOf(total), screen_width/2 - 104, screen_height/2 - 174);
		
		// Draw Check Boxes.
		
		int xOffSet = 0;
		int yOffSet = 0;
		for (int i = 0; i < 5; i ++) {
			switch(i) {
				case 0: xOffSet = 0; yOffSet = 0; break;
				case 1: xOffSet = 1; yOffSet = 0; break;
				case 2: xOffSet = 2; yOffSet = 0; break;
				case 3: xOffSet = 0; yOffSet = 1; break;
				case 4: xOffSet = 1; yOffSet = 1; break;
			}
			drawSetAlpha(checkBoxAlpha[i]);
			drawSprite(sprCheckBox, screen_width/2 - 267 + (180*xOffSet), screen_height/2 - 137 + (60*yOffSet));
			drawSetAlpha(checkBoxCheckAlpha[i]);
			drawSprite(sprCheckBoxCheck, screen_width/2 - 266 + (180*xOffSet), screen_height/2 - 137 + (60*yOffSet));
		}
		
		// Draw Drinks Name.
		drawSetColor(Color.WHITE);
		drawSetAlpha(fillInAlpha * 0.8);
		mRenderer.setFont(fontLore);
		drawText(drinkName[0], screen_width/2 - 236, screen_height/2 - 130);
		drawText(drinkName[1], screen_width/2 - 236 + 180, screen_height/2 - 130);
		drawText(drinkName[2], screen_width/2 - 236 + 180*2, screen_height/2 - 130);
		drawText(drinkName[3], screen_width/2 - 236, screen_height/2 - 70);
		drawText(drinkName[4], screen_width/2 - 236 + 180, screen_height/2 - 70);
		drawSetAlpha(fillInAlpha * 0.3);
		mRenderer.setFont(fontMiniTitle);
		drawText("Price ", screen_width/2 - 236, screen_height/2 - 112);
		drawText("Price ", screen_width/2 - 236 + 180, screen_height/2 - 112);
		drawText("Price ", screen_width/2 - 236 + 180 * 2, screen_height/2 - 112);
		drawText("Price ", screen_width/2 - 236, screen_height/2 - 52);
		drawText("Price ", screen_width/2 - 236 + 180, screen_height/2 - 52);
		drawSetColor(new Color(255, 159, 68));
		drawSetAlpha(fillInAlpha * 0.6);
		drawText(String.valueOf(drinkPrice[0]), screen_width/2 - 200, screen_height/2 - 112);
		drawText(String.valueOf(drinkPrice[1]), screen_width/2 - 200 + 180, screen_height/2 - 112);
		drawText(String.valueOf(drinkPrice[2]), screen_width/2 - 200 + 180 * 2, screen_height/2 - 112);
		drawText(String.valueOf(drinkPrice[3]), screen_width/2 - 200, screen_height/2 - 52);
		drawText(String.valueOf(drinkPrice[4]), screen_width/2 - 200 + 180, screen_height/2 - 52);
		drawSetColor(Color.WHITE);
		drawSetAlpha(fillInAlpha * 0.3);
		drawText(" ¤ý Left ", screen_width/2 - 180, screen_height/2 - 112);
		drawText(" ¤ý Left ", screen_width/2 - 180 + 180, screen_height/2 - 112);
		drawText(" ¤ý Left ", screen_width/2 - 180 + 180 * 2, screen_height/2 - 112);
		drawText(" ¤ý Left ", screen_width/2 - 180, screen_height/2 - 52);
		drawText(" ¤ý Left ", screen_width/2 - 180 + 180, screen_height/2 - 52);
		drawSetColor(new Color(83, 160, 255));
		drawSetAlpha(fillInAlpha * 0.7);
		drawText(String.valueOf(drinkNum[0]), screen_width/2 - 134, screen_height/2 - 112);
		drawText(String.valueOf(drinkNum[1]), screen_width/2 - 134 + 180, screen_height/2 - 112);
		drawText(String.valueOf(drinkNum[2]), screen_width/2 - 134 + 180 * 2, screen_height/2 - 112);
		drawText(String.valueOf(drinkNum[3]), screen_width/2 - 134, screen_height/2 - 52);
		drawText(String.valueOf(drinkNum[4]), screen_width/2 - 134 + 180, screen_height/2 - 52);
		
		// Draw Add selected product inventory.
		drawSetColor(Color.WHITE);
		drawSetAlpha(fillInAlpha * 0.1);
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(ASCButtonAlpha);
		mRenderer.setFont(fontMiniTitle);
		drawText("Add Selected Commodity", screen_width/2 + 308, screen_height/2 - 124);
		drawSetColor(Color.WHITE);
		drawSprite(sprLineButton, screen_width/2 + 290, screen_height/2 - 140);

		drawSetColor(new Color(87, 101, 116));
		drawSetAlpha(fillInAlpha);
		mRenderer.setFont(fontMiniTitle);
		drawText("REVENUE", screen_width/2 - 280, screen_height/2);
		drawSetAlpha(0.2f);
		mRenderer.drawLine(screen_width/2 - 280, screen_height/2 + 10, screen_width/2 + 510, screen_height/2 + 10);
		mRenderer.drawLine(screen_width/2 - 280, screen_height/2 + 9, screen_width/2 + 510, screen_height/2 + 9);
		
		drawSetAlpha(fillInAlpha * 0.8);
		drawSetColor(new Color(87, 101, 116));
		mRenderer.setFont(fontMiniTitle);
		drawText("TOTAL", screen_width/2 - 180, screen_height/2);
		drawSetColor(new Color(255, 159, 68));
		drawSetAlpha(fillInAlpha * 0.65);
		
		total = ((moneyNum1000*1000) + (moneyNum500*500) + (moneyNum100*100) + (moneyNum50*50) + (moneyNum10*10));
		
		drawText(String.valueOf(total) + " WON", screen_width/2 - 136, screen_height/2);
		
		for (int i = 5; i < 10; i ++) {
			switch(i - 5) {
				case 0: xOffSet = 0; yOffSet = 0; break;
				case 1: xOffSet = 1; yOffSet = 0; break;
				case 2: xOffSet = 2; yOffSet = 0; break;
				case 3: xOffSet = 0; yOffSet = 1; break;
				case 4: xOffSet = 1; yOffSet = 1; break;
			}
			drawSetAlpha(checkBoxAlpha[i]);
			drawSprite(sprCheckBox, screen_width/2 - 267 + (180*xOffSet), screen_height/2 + 35 + (60*yOffSet));
			drawSetAlpha(checkBoxCheckAlpha[i]);
			drawSprite(sprCheckBoxCheck, screen_width/2 - 266 + (180*xOffSet), screen_height/2 + 35 + (60*yOffSet));
		}
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(fillInAlpha * 0.8);
		mRenderer.setFont(fontLore);
		drawText("1000 WON", screen_width/2 - 236, screen_height/2 + 42);
		drawSetAlpha(fillInAlpha * 0.3);
		mRenderer.setFont(fontMiniTitle);
		drawText("Left ", screen_width/2 - 236, screen_height/2 + 60);
		drawText(String.valueOf(moneyNum1000), screen_width/2 - 209, screen_height/2 + 60);
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(fillInAlpha * 0.8);
		mRenderer.setFont(fontLore);
		drawText("500 WON", screen_width/2 - 236 + 180, screen_height/2 + 42);
		drawSetAlpha(fillInAlpha * 0.3);
		mRenderer.setFont(fontMiniTitle);
		drawText("Left ", screen_width/2 - 236 + 180, screen_height/2 + 60);
		drawText(String.valueOf(moneyNum500), screen_width/2 - 209 + 180, screen_height/2 + 60);
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(fillInAlpha * 0.8);
		mRenderer.setFont(fontLore);
		drawText("100 WON", screen_width/2 - 236 + 180 * 2, screen_height/2 + 42);
		drawSetAlpha(fillInAlpha * 0.3);
		mRenderer.setFont(fontMiniTitle);
		drawText("Left ", screen_width/2 - 236 + 180 * 2, screen_height/2 + 60);
		drawText(String.valueOf(moneyNum100), screen_width/2 - 209 + 180 * 2, screen_height/2 + 60);
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(fillInAlpha * 0.8);
		mRenderer.setFont(fontLore);
		drawText("50 WON", screen_width/2 - 236, screen_height/2 + 42 + 60);
		drawSetAlpha(fillInAlpha * 0.3);
		mRenderer.setFont(fontMiniTitle);
		drawText("Left ", screen_width/2 - 236, screen_height/2 + 60 + 60);
		drawText(String.valueOf(moneyNum50), screen_width/2 - 209, screen_height/2 + 60 + 60);
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(fillInAlpha * 0.8);
		mRenderer.setFont(fontLore);
		drawText("10 WON", screen_width/2 - 236 + 180, screen_height/2 + 42 + 60);
		drawSetAlpha(fillInAlpha * 0.3);
		mRenderer.setFont(fontMiniTitle);
		drawText("Left ", screen_width/2 - 236 + 180, screen_height/2 + 60 + 60);
		drawText(String.valueOf(moneyNum10), screen_width/2 - 209 + 180, screen_height/2 + 60 + 60);
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(RCButtonAlpha);
		mRenderer.setFont(fontMiniTitle);
		drawText("Change Reset", screen_width/2 + 338, screen_height/2 + 48);
		drawSetColor(Color.WHITE);
		drawSprite(sprLineButton, screen_width/2 + 290, screen_height/2 + 32);
	}
	
	@Override
	public void mouseCheck() {

	}

	@Override
	public void mousePressed() {
		
		// Back Button
		if (mouseX >= screen_width - 128 && 
			mouseY >= 0 && 
			mouseX <= screen_width && 
			mouseY <= 48 ) {
			
			editMode = false;
			editDrinkTextField.setEnabled(editMode);
			editDrinkTextField.setVisible(editMode);
			editDrinkTextField2.setEnabled(editMode);
			editDrinkTextField2.setVisible(editMode);
			editDrinkTextField3.setEnabled(editMode);
			editDrinkTextField3.setVisible(editMode);
			editDrinkTextField4.setEnabled(editMode);
			editDrinkTextField4.setVisible(editMode);
			editDrinkTextField5.setEnabled(editMode);
			editDrinkTextField5.setVisible(editMode);
			
			editPriceTextField.setEnabled(editMode);
			editPriceTextField.setVisible(editMode);
			editPriceTextField2.setEnabled(editMode);
			editPriceTextField2.setVisible(editMode);
			editPriceTextField3.setEnabled(editMode);
			editPriceTextField3.setVisible(editMode);
			editPriceTextField4.setEnabled(editMode);
			editPriceTextField4.setVisible(editMode);
			editPriceTextField5.setEnabled(editMode);
			editPriceTextField5.setVisible(editMode);
	        frame.setVisible(true);
			pageChangeNum = 1;
				
		}
		
//screen_width/2 + 424, screen_height/2 - 174
		if (mouseX >= screen_width/2 + 416 && 
			mouseY >= screen_height/2 - 190 && 
			mouseX <= screen_width/2 + 416 + 60 && 
			mouseY <= screen_height/2 - 160 ) {
			
			
			if (!editMode) {
				
				editMode = true;
				
				// Drink Edit
				editDrinkTextField.setEnabled(editMode);
				editDrinkTextField.setVisible(editMode);
				editDrinkTextField.setText(drinkName[0]);
				drinkT = editDrinkTextField.getText();
				
				editDrinkTextField2.setEnabled(editMode);
				editDrinkTextField2.setVisible(editMode);
				editDrinkTextField2.setText(drinkName[1]);
				drinkT2 = editDrinkTextField2.getText();
				
				editDrinkTextField3.setEnabled(editMode);
				editDrinkTextField3.setVisible(editMode);
				editDrinkTextField3.setText(drinkName[2]);
				drinkT3 = editDrinkTextField3.getText();
				
				editDrinkTextField4.setEnabled(editMode);
				editDrinkTextField4.setVisible(editMode);
				editDrinkTextField4.setText(drinkName[3]);
				drinkT4 = editDrinkTextField4.getText();
				
				editDrinkTextField5.setEnabled(editMode);
				editDrinkTextField5.setVisible(editMode);
				editDrinkTextField5.setText(drinkName[4]);
				drinkT5 = editDrinkTextField5.getText();
				
				
				// Price Edit
				editPriceTextField.setEnabled(editMode);
				editPriceTextField.setVisible(editMode);
				editPriceTextField.setText(String.valueOf(drinkPrice[0]));
				priceT = Integer.valueOf(editPriceTextField.getText());
				
				editPriceTextField2.setEnabled(editMode);
				editPriceTextField2.setVisible(editMode);
				editPriceTextField2.setText(String.valueOf(drinkPrice[1]));
				priceT2 = Integer.valueOf(editPriceTextField2.getText());
				
				editPriceTextField3.setEnabled(editMode);
				editPriceTextField3.setVisible(editMode);
				editPriceTextField3.setText(String.valueOf(drinkPrice[2]));
				priceT3 = Integer.valueOf(editPriceTextField3.getText());
				
				editPriceTextField4.setEnabled(editMode);
				editPriceTextField4.setVisible(editMode);
				editPriceTextField4.setText(String.valueOf(drinkPrice[3]));
				priceT4 = Integer.valueOf(editPriceTextField4.getText());
				
				editPriceTextField5.setEnabled(editMode);
				editPriceTextField5.setVisible(editMode);
				editPriceTextField5.setText(String.valueOf(drinkPrice[4]));
				priceT5 = Integer.valueOf(editPriceTextField5.getText());
				
				drinkName[0] = drinkT;
				drinkName[1] = drinkT2;
				drinkName[2] = drinkT3;
				drinkName[3] = drinkT4;
				drinkName[4] = drinkT5;
				
				drinkPrice[0] = priceT;
				drinkPrice[1] = priceT2;
				drinkPrice[2] = priceT3;
				drinkPrice[3] = priceT4;
				drinkPrice[4] = priceT5;
				
		        frame.setVisible(true);
		        
			} else {
				
				// Get Drinks Name
				drinkT = editDrinkTextField.getText();
				drinkT2 = editDrinkTextField2.getText();
				drinkT3 = editDrinkTextField3.getText();
				drinkT4 = editDrinkTextField4.getText();
				drinkT5 = editDrinkTextField5.getText();

				// Get Drinks Price
				priceT = Integer.valueOf(editPriceTextField.getText());
				priceT2 = Integer.valueOf(editPriceTextField2.getText());
				priceT3 = Integer.valueOf(editPriceTextField3.getText());
				priceT4 = Integer.valueOf(editPriceTextField4.getText());
				priceT5 = Integer.valueOf(editPriceTextField5.getText());
				
				drinkName[0] = drinkT;
				drinkName[1] = drinkT2;
				drinkName[2] = drinkT3;
				drinkName[3] = drinkT4;
				drinkName[4] = drinkT5;
				
				drinkPrice[0] = priceT;
				drinkPrice[1] = priceT2;
				drinkPrice[2] = priceT3;
				drinkPrice[3] = priceT4;
				drinkPrice[4] = priceT5;
				
				editMode = false;
				editDrinkTextField.setEnabled(editMode);
				editDrinkTextField.setVisible(editMode);
				editDrinkTextField2.setEnabled(editMode);
				editDrinkTextField2.setVisible(editMode);
				editDrinkTextField3.setEnabled(editMode);
				editDrinkTextField3.setVisible(editMode);
				editDrinkTextField4.setEnabled(editMode);
				editDrinkTextField4.setVisible(editMode);
				editDrinkTextField5.setEnabled(editMode);
				editDrinkTextField5.setVisible(editMode);
				
				editPriceTextField.setEnabled(editMode);
				editPriceTextField.setVisible(editMode);
				editPriceTextField2.setEnabled(editMode);
				editPriceTextField2.setVisible(editMode);
				editPriceTextField3.setEnabled(editMode);
				editPriceTextField3.setVisible(editMode);
				editPriceTextField4.setEnabled(editMode);
				editPriceTextField4.setVisible(editMode);
				editPriceTextField5.setEnabled(editMode);
				editPriceTextField5.setVisible(editMode);
		        frame.setVisible(true);
		        
			}
					
		}
		
		//screen_width/2 + 290, screen_height/2 - 140
		if (mouseX >= screen_width/2 + 290 && 
			mouseY >= screen_height/2 - 140 && 
			mouseX <=screen_width/2 + 290 + 180 && 
			mouseY <= screen_height/2 - 140+ 24) {
			
			if (checkCanASC) {
				System.out.println("ASC");
				for (int i = 0; i < 5; i ++) {
					if (checkBoxCheck[i] == true)
						drinkNum[i] = 3;
				}
			}
			
		} else if (mouseX >= screen_width/2 + 290 && 
			mouseY >= screen_height/2 + 32 && 
			mouseX <=screen_width/2 + 290 + 180 && 
			mouseY <= screen_height/2 + 32 + 24) {
			
			if (checkCanRC) {
				System.out.println("RC");
				for (int i = 5; i < 10; i ++) {
					if (checkBoxCheck[i] == true) {
						int n = 0;
						if (i == 5) {
							n = getCoinNum(1000);
							if (getCoinNum(1000) > 0) {
								removeCoinNum(1000, n);
							}
						} else if (i == 6) {
							n = getCoinNum(500);
							if (getCoinNum(500) > 5) {
								removeCoinNum(500, n - 5);
							} else if (getCoinNum(500) != 5) {
								for (int j = 0; j < 5 - n; j ++) {
									coin = new ObjCoinPar(500);
									coinList.add(coinNumber, coin);
									coinNumber++;
								}
							}
						} else if (i == 7) {
							n = getCoinNum(100);
							if (getCoinNum(100) > 5) {
								removeCoinNum(100, n - 5);
							} else if (getCoinNum(100) != 5) {
								for (int j = 0; j < 5 - n; j ++) {
									coin = new ObjCoinPar(100);
									coinList.add(coinNumber, coin);
									coinNumber++;
								}
							}
						} else if (i == 8) {
							n = getCoinNum(50);
							if (getCoinNum(50) > 5) {
								removeCoinNum(50, n - 5);
							} else if (getCoinNum(50) != 5) {
								for (int j = 0; j < 5 - n; j ++) {
									coin = new ObjCoinPar(50);
									coinList.add(coinNumber, coin);
									coinNumber++;
								}
							}
						} else if (i == 9) {
							n = getCoinNum(10);
							if (getCoinNum(10) > 5) {
								removeCoinNum(10, n - 5);
							} else if (getCoinNum(10) != 5) {
								for (int j = 0; j < 5 - n; j ++) {
									coin = new ObjCoinPar(10);
									coinList.add(coinNumber, coin);
									coinNumber++;
								}
							}
						}
					}
				}
				moneyNum1000 = getCoinNum(1000);
				moneyNum500 = getCoinNum(500);
				moneyNum100 = getCoinNum(100);
				moneyNum50 = getCoinNum(50);
				moneyNum10 = getCoinNum(10);
			}
				
		}
		
		int xOffSet = 0;
		int yOffSet = 0;
		for (int i = 0; i < 5; i ++) {
			switch(i) {
				case 0: xOffSet = 0; yOffSet = 0; break;
				case 1: xOffSet = 1; yOffSet = 0; break;
				case 2: xOffSet = 2; yOffSet = 0; break;
				case 3: xOffSet = 0; yOffSet = 1; break;
				case 4: xOffSet = 1; yOffSet = 1; break;
			}

			if (mouseX >= screen_width/2 - 267 + (180 * xOffSet) && 
				mouseY >= screen_height/2 - 148 + (60 * yOffSet) && 
				mouseX <= screen_width/2 - 267 + 140 + (180 * xOffSet) && 
				mouseY <= screen_height/2 - 148 + 50 + (60 * yOffSet)) {
				if (!checkBoxCheck[i])
					checkBoxCheck[i] = true;
				else
					checkBoxCheck[i] = false;
			}
		}
		
		for (int i = 5; i < 10; i ++) {
			switch(i - 5) {
				case 0: xOffSet = 0; yOffSet = 0; break;
				case 1: xOffSet = 1; yOffSet = 0; break;
				case 2: xOffSet = 2; yOffSet = 0; break;
				case 3: xOffSet = 0; yOffSet = 1; break;
				case 4: xOffSet = 1; yOffSet = 1; break;
			}

			if (mouseX >= screen_width/2 - 267 + (180 * xOffSet) && 
				mouseY >= screen_height/2 + 24 + (60 * yOffSet) && 
				mouseX <= screen_width/2 - 267 + 140 + (180 * xOffSet) && 
				mouseY <= screen_height/2 + 24 + 50 + (60 * yOffSet)) {
				if (!checkBoxCheck[i])
					checkBoxCheck[i] = true;
				else
					checkBoxCheck[i] = false;
			}
		}

	}
	
	@Override
	public void mouseReleased() {

	}
	
}
