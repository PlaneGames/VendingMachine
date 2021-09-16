package System.objects.coinbuttons;

import static System.Game.mouseX;
import static System.Game.mouseY;
import static System.Game.screen_height;
import static System.Game.screen_width;
import static System.objects.ObjVMMgr.enteredMoney;
import static System.Game.coin;
import static System.Game.tempCoin;
import static System.Game.coinList;
import static System.Game.coinNumber;
import static System.Game.totalMoney;
import static System.objects.ObjVMMgr.enteredMoneyNum1000;
import static System.objects.ObjVMMgr.enteredMoneyNum500;
import static System.objects.ObjVMMgr.enteredMoneyNum100;
import static System.objects.ObjVMMgr.enteredMoneyNum50;
import static System.objects.ObjVMMgr.enteredMoneyNum10;

import java.awt.Color;
import java.awt.Image;
import System.objects.Object;
import System.objects.coins.ObjCoinPar;

public class ObjCoinButtonPar extends Object {
	
	// Coin Info.
	public double x;
	public double y;
	
	public int value;
	
	public String name;
	public Image spriteIndex;
	public Image sprShadow;
	public double imageAlpha;
	
	public int easeTime;
	public boolean checkMouseIsOn;
	public boolean canEnter;
	
	public int xOffSet;
	public int yOffSet;
	
	public double loreBoardAlpha;
	
	// Func.
	double Lerp(double A, double B, double d) {
		return A * (1 - d) + B * d;
	}
	
	public ObjCoinButtonPar() {
		
		Init();
		imageAlpha 		= 0.0f;
		x 				= screen_width/2 + 160;
		y 				= screen_height/2 - 240 + (yOffSet * 70);
		easeTime 		= 0;
		checkMouseIsOn 	= false;
		canEnter		= true;
		loreBoardAlpha 	= 0.0f;
		
	}
	
	public void finalize() {
		
	}
	
	@Override
	public void Init() {
		
	}

	@Override
	public void Update() {

		if (mouseX >= x && mouseY >= screen_height/2 - 250 + (yOffSet * 70) && mouseX <= x + spriteIndex.getWidth(this) && mouseY <= screen_height/2 - 240 + (yOffSet * 70) + spriteIndex.getHeight(this))
			checkMouseIsOn = true;
		else
			checkMouseIsOn = false;
		
		canEnter = ((enteredMoney + value <= 5000) && ((value == 1000 && enteredMoneyNum1000 < 3) || (value != 1000)));
		
		if (canEnter) {
			
			if (checkMouseIsOn) {
				if (imageAlpha < 1.0f) {
					imageAlpha += 0.1f;
				}
				if (loreBoardAlpha < 1.0f) {
					loreBoardAlpha += 0.1f;
				}
				y = Lerp(y, screen_height/2 - 250 + (yOffSet * 70), 0.2d);
			} else {
				if (imageAlpha > 0.7f) {
					imageAlpha -= 0.1f;
				}
				if (loreBoardAlpha > 0.0f) {
					loreBoardAlpha -= 0.1f;
				}
				if (loreBoardAlpha < 0.0f) {
					loreBoardAlpha = 0.0f;
				}
				y = Lerp(y, screen_height/2 - 240 + (yOffSet * 70), 0.2d);
			}
		
		} else {
			
			if (imageAlpha > 0.2f) {
				imageAlpha -= 0.1f;
			}
			if (loreBoardAlpha > 0.0f) {
				loreBoardAlpha -= 0.1f;
			}
			if (loreBoardAlpha < 0.0f) {
				loreBoardAlpha = 0.0f;
			}
			y = Lerp(y, screen_height/2 - 240 + (yOffSet * 70), 0.2d);
			
		}
		
		if (easeTime < 60) {
			easeTime++;
			imageAlpha = easeInSine(easeTime, 0, 0.8, 60);
			x = screen_width/2 + 160 + (xOffSet * 70);
		}

	}

	@Override
	public void Render() {

		drawSetAlpha(1.0f);
		drawSprite(sprShadow, (int) (x), (int) (screen_height/2 - 236) + (yOffSet * 70));
		
		drawSetAlpha(imageAlpha);
		drawSprite(spriteIndex, (int) (Math.round(x)), (int) (Math.round(y)));
		
		drawSetAlpha(loreBoardAlpha);
		drawSetColor(Color.WHITE);
		drawText(name, (int) (x - 8), (int) (int) (screen_height/2 - 200) + (yOffSet * 70) + 4);

		drawSetColor(Color.WHITE);
		drawSetAlpha(1.0f);

	}
	
	@Override
	public void mouseCheck() {

	}
	
	@Override
	public void mousePressed() {
		
		// Create Money Instance.
		if (checkMouseIsOn) {

			canEnter = ((enteredMoney + value <= 5000) && ((value == 1000 && enteredMoneyNum1000 < 3) || (value != 1000)));
			
			if (canEnter) {

				// Check Button Value.
				switch (value) {
				
				case 1000:
					enteredMoneyNum1000 ++;
					enteredMoney += 1000;
					System.out.println("1000 WON ENTERED!");
					break;
				case 500:
					enteredMoneyNum500 ++;
					enteredMoney += 500;
					System.out.println("500 WON ENTERED!");
					break;
				case 100:
					enteredMoneyNum100 ++;
					enteredMoney += 100;
					System.out.println("100 WON ENTERED!");
					break;
				case 50:
					enteredMoneyNum50 ++;
					enteredMoney += 50;
					System.out.println("50 WON ENTERED!");
					break;
				case 10:
					enteredMoneyNum10 ++;
					enteredMoney += 10;
					System.out.println("10 WON ENTERED!");
					break;
					
				}

				// Coin Create.
				System.out.println("Entered Index : " + coinNumber);
				coin = new ObjCoinPar(value);
				coinList.add(coinNumber, coin);
				coinNumber++;
				
				/*
				enteredMoney = 0;
				for (int i = 0; i < coinNumber; i ++) {
					tempCoin = (ObjCoinPar)coinList.get(i);
					enteredMoney += tempCoin.value;
				}*/
				
			}
			
		}

	}
	
	@Override
	public void mouseReleased() {

	}
	
}
