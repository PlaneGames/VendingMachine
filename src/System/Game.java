package System;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Iterator;

import static System.Game.drinkName;
import static System.Game.drinkPrice;
import static System.Game.screen_height;
import static System.Game.screen_width;

import java.awt.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.image.*;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

import System.DrawFunc;
import System.objects.coins.ObjCoinPar;
import System.pages.Page;
import System.pages.PageMainMenu;
import System.pages.PageVMManagement;
import System.pages.PageVMManagementLogin;
import System.pages.PageVMMgr;

public class Game extends Canvas implements Runnable, MouseListener, MouseMotionListener {

	//DrawFunc drawFunc = new DrawFunc();
	private static String NAME 	= "Game";
	private boolean isRunning 	= false;

	// Screen Info
	public static int titlebar_width 	= 1280;
	public static int titlebar_height 	= 30;
	public static int screen_width 		= 1280;
	public static int screen_height 	= 720;
	
	public static int mousePressedIs	= 0;
	public static int mouseX;
	public static int mouseY;
	
	// Main Graphics
	public static AlphaComposite ac;
	public static Graphics mRenderer;
	public static Graphics2D mRenderer2D;
	
	public static Color bgColor1 = new Color(47, 47, 47);
	public static Color bgColor2 = new Color(20, 20, 22);
	public static GradientPaint bgGradient = new GradientPaint(0, 0, bgColor1, 1280, 720, bgColor2, true);
	
	// Main Frame / Panel
	public static JFrame frame;
	public static JPanel panel;
	
	// Mouse Listener
	public static PointerInfo deviceMouse = MouseInfo.getPointerInfo();
	public MouseEvent mouseEvent;
	
	// Frame Rate
	public static int fps = 0;
	
	// Text Fields
	public static JTextField loginTextField = new JTextField(20);
	
	public static JTextField editDrinkTextField  = new JTextField(20);
	public static JTextField editDrinkTextField2 = new JTextField(20);
	public static JTextField editDrinkTextField3 = new JTextField(20);
	public static JTextField editDrinkTextField4 = new JTextField(20);
	public static JTextField editDrinkTextField5 = new JTextField(20);
	
	public static JTextField editPriceTextField  = new JTextField(20);
	public static JTextField editPriceTextField2 = new JTextField(20);
	public static JTextField editPriceTextField3 = new JTextField(20);
	public static JTextField editPriceTextField4 = new JTextField(20);
	public static JTextField editPriceTextField5 = new JTextField(20);
	
	// Page Setting
	public static Page pageInfo;
	public static int pageChangeNum = 0;
	public static boolean pageChangeIs = false;
	
	public Image imageSetSize(Image img, int xscale, int yscale) {
		Image scaleImg = img.getScaledInstance(xscale, yscale, Image.SCALE_SMOOTH);
		return scaleImg;
	}
	
	// Drink Setting
	public static int[] drinkNum = new int[5];
	public static String[] drinkName = new String[5];
	
	public static int[] drinkPrice = new int[5];
	
	// Coin Setting
	public static int totalMoney;
	public static int coinNumber;
	public static ArrayList<ObjCoinPar> coinList;
	public static ObjCoinPar tempCoin;
	public static ObjCoinPar coin;

	// Coin Functions.
	// 코인의 개수 구하기
	public static int getCoinNum(int value) {
		
		ArrayList<Integer> moneyList = new ArrayList<Integer>();
		int coinNum 	= 0;
		int i;
		for (i = 0; i < coinNumber; i ++) {

			tempCoin = (ObjCoinPar)coinList.get(i);
			if (tempCoin.value == value) {
				moneyList.add(coinNum, i);
				coinNum ++;
			}

		}
		return coinNum;
		
	}
	// 해당 코인을 개수만큼 제거하기
	public static void removeCoinNum(int value, int num) {
		
		int repeat = 0;
		for(Iterator<ObjCoinPar> it = coinList.iterator() ; it.hasNext() && num > 0 ; ) {
			
			ObjCoinPar temp = it.next();
			if(temp.value == value) {
				it.remove();
				coinNumber--;
				repeat ++;
				if (repeat == num) {
					System.out.println("Remove " + value + " WON " + repeat + " TIME");
					break;
				}
			}
			
		}
		
	}
	
	public Game() {

        // Frame Setting.
        frame = new JFrame(NAME);
        frame.setUndecorated(true);
        panel = new JPanel();
        
        // Edit Login Text Field Setting.
        loginTextField.setBounds(screen_width/2 - 90, screen_height/2 + titlebar_height - 12, 180, 24);
        loginTextField.setBackground(new Color(34, 34, 48));
        loginTextField.setForeground(Color.WHITE);
        loginTextField.setCaretColor(Color.WHITE);
        loginTextField.setEnabled(false);
        loginTextField.setVisible(false);
        
        // Edit Drink Text Field Setting.
        editDrinkTextField.setBounds(screen_width/2 - 236, screen_height/2 - 148, 120, 22);
        editDrinkTextField.setBackground(new Color(34, 34, 48));
        editDrinkTextField.setForeground(Color.WHITE);
        editDrinkTextField.setCaretColor(Color.WHITE);
        editDrinkTextField.setEnabled(false);
        editDrinkTextField.setVisible(false);
        
        editDrinkTextField2.setBounds(screen_width/2 - 236 + 180, screen_height/2 - 148, 120, 22);
        editDrinkTextField2.setBackground(new Color(34, 34, 48));
        editDrinkTextField2.setForeground(Color.WHITE);
        editDrinkTextField2.setCaretColor(Color.WHITE);
        editDrinkTextField2.setEnabled(false);
        editDrinkTextField2.setVisible(false);
        
        editDrinkTextField3.setBounds(screen_width/2 - 236 + 180 * 2, screen_height/2 - 148, 120, 22);
        editDrinkTextField3.setBackground(new Color(34, 34, 48));
        editDrinkTextField3.setForeground(Color.WHITE);
        editDrinkTextField3.setCaretColor(Color.WHITE);
        editDrinkTextField3.setEnabled(false);
        editDrinkTextField3.setVisible(false);
        
        editDrinkTextField4.setBounds(screen_width/2 - 236, screen_height/2 - 148 + 60, 120, 22);
        editDrinkTextField4.setBackground(new Color(34, 34, 48));
        editDrinkTextField4.setForeground(Color.WHITE);
        editDrinkTextField4.setCaretColor(Color.WHITE);
        editDrinkTextField4.setEnabled(false);
        editDrinkTextField4.setVisible(false);
        
        editDrinkTextField5.setBounds(screen_width/2 - 236 + 180, screen_height/2 - 148 + 60, 120, 22);
        editDrinkTextField5.setBackground(new Color(34, 34, 48));
        editDrinkTextField5.setForeground(Color.WHITE);
        editDrinkTextField5.setCaretColor(Color.WHITE);
        editDrinkTextField5.setEnabled(false);
        editDrinkTextField5.setVisible(false);
        
     // Edit Drink Text Field Setting.
        editPriceTextField.setBounds(screen_width/2 - 200, screen_height/2 - 128, 32, 22);
        editPriceTextField.setBackground(new Color(34, 34, 48));
        editPriceTextField.setForeground(Color.WHITE);
        editPriceTextField.setCaretColor(Color.WHITE);
        editPriceTextField.setEnabled(false);
        editPriceTextField.setVisible(false);
        
        editPriceTextField2.setBounds(screen_width/2 - 200 + 180, screen_height/2 - 128, 32, 22);
        editPriceTextField2.setBackground(new Color(34, 34, 48));
        editPriceTextField2.setForeground(Color.WHITE);
        editPriceTextField2.setCaretColor(Color.WHITE);
        editPriceTextField2.setEnabled(false);
        editPriceTextField2.setVisible(false);
        
        editPriceTextField3.setBounds(screen_width/2 - 200 + 180 * 2, screen_height/2 - 128, 32, 22);
        editPriceTextField3.setBackground(new Color(34, 34, 48));
        editPriceTextField3.setForeground(Color.WHITE);
        editPriceTextField3.setCaretColor(Color.WHITE);
        editPriceTextField3.setEnabled(false);
        editPriceTextField3.setVisible(false);
        
        editPriceTextField4.setBounds(screen_width/2 - 200, screen_height/2 - 128 + 60, 32, 22);
        editPriceTextField4.setBackground(new Color(34, 34, 48));
        editPriceTextField4.setForeground(Color.WHITE);
        editPriceTextField4.setCaretColor(Color.WHITE);
        editPriceTextField4.setEnabled(false);
        editPriceTextField4.setVisible(false);
        
        editPriceTextField5.setBounds(screen_width/2 - 200 + 180, screen_height/2 - 128 + 60, 32, 22);
        editPriceTextField5.setBackground(new Color(34, 34, 48));
        editPriceTextField5.setForeground(Color.WHITE);
        editPriceTextField5.setCaretColor(Color.WHITE);
        editPriceTextField5.setEnabled(false);
        editPriceTextField5.setVisible(false);
        
        //jaeyoung21+
        
        frame.add(loginTextField);
        frame.add(editDrinkTextField);
        frame.add(editDrinkTextField2);
        frame.add(editDrinkTextField3);
        frame.add(editDrinkTextField4);
        frame.add(editDrinkTextField5);
        frame.add(editPriceTextField);
        frame.add(editPriceTextField2);
        frame.add(editPriceTextField3);
        frame.add(editPriceTextField4);
        frame.add(editPriceTextField5);
        
        panel.setPreferredSize(new Dimension(screen_width, screen_height + titlebar_height));
        panel.setLayout(new BorderLayout());
        panel.add(this, BorderLayout.CENTER);
        
        frame.add(panel);
        frame.revalidate();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();

        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        // PageInit
        pageInfo = new PageMainMenu(); 
		
		// MouseListener Update.
		addMouseListener(this);
        addMouseMotionListener(this);
        
        int i;
        // Drink Setting.
        for (i = 0; i < 5; i ++) {
        	drinkNum[i] = 3;
        }
        
        drinkName[0] = "Water";
        drinkName[1] = "Coffee";
        drinkName[2] = "Sports Drink";
        drinkName[3] = "Royal Coffee";
        drinkName[4] = "Soda";
        
        drinkPrice[0] = 450;
        drinkPrice[1] = 500;
        drinkPrice[2] = 550;
        drinkPrice[3] = 700;
        drinkPrice[4] = 750;
        
        // Coin Setting.
        coinList = new ArrayList<ObjCoinPar>();
		
		for (i = 0; i < 5; i ++) {
			coin = new ObjCoinPar(500);
			coinList.add(coinNumber, coin);
			coinNumber++;
			coin = new ObjCoinPar(100);
			coinList.add(coinNumber, coin);
			coinNumber++;
			coin = new ObjCoinPar(50);
			coinList.add(coinNumber, coin);
			coinNumber++;
			coin = new ObjCoinPar(10);
			coinList.add(coinNumber, coin);
			coinNumber++;
		}

	}

	public synchronized void start() {

		// 독립적인 쓰레드 구현
		new Thread(this).start();
		isRunning = true;

	}

	@Override
	public void run() {
		long lastTime 		= System.nanoTime();
		double nsPerTick 	= 1000000000D / 60D;
		long lastTimer 		= System.currentTimeMillis();

		int frames = 0;
		int ticks = 0;

		double delta = 0;

		while (isRunning) {

			long now 				= System.nanoTime();
			delta 					+= (now - lastTime) / nsPerTick;
			lastTime 				= now;
			boolean shouldRender 	= false;

			while (delta >= 1) {
				
				ticks++;
				delta -= 1;
				shouldRender = true;
				
				// TODO ///////////////////////////////////////////////////////////////////////////////////////////////

				// Window Drag.
				deviceMouse = MouseInfo.getPointerInfo();
				if (mousePressedIs == 1) {
					frame.setLocation(deviceMouse.getLocation().x - mouseX, deviceMouse.getLocation().y - mouseY);
				}
				
				// Buffer
				loginTextField.setForeground(Color.WHITE);
		        loginTextField.setCaretColor(Color.WHITE);
		        editDrinkTextField.setForeground(Color.WHITE);
		        editDrinkTextField.setCaretColor(Color.WHITE);
		        editDrinkTextField2.setForeground(Color.WHITE);
		        editDrinkTextField2.setCaretColor(Color.WHITE);
		        editDrinkTextField3.setForeground(Color.WHITE);
		        editDrinkTextField3.setCaretColor(Color.WHITE);
		        editDrinkTextField4.setForeground(Color.WHITE);
		        editDrinkTextField4.setCaretColor(Color.WHITE);
		        editDrinkTextField5.setForeground(Color.WHITE);
		        editDrinkTextField5.setCaretColor(Color.WHITE);
		        
		        editPriceTextField.setForeground(Color.WHITE);
		        editPriceTextField.setCaretColor(Color.WHITE);
		        editPriceTextField2.setForeground(Color.WHITE);
		        editPriceTextField2.setCaretColor(Color.WHITE);
		        editPriceTextField3.setForeground(Color.WHITE);
		        editPriceTextField3.setCaretColor(Color.WHITE);
		        editPriceTextField4.setForeground(Color.WHITE);
		        editPriceTextField4.setCaretColor(Color.WHITE);
		        editPriceTextField5.setForeground(Color.WHITE);
		        editPriceTextField5.setCaretColor(Color.WHITE);
		        
				// Page Change Check.
		        if (pageChangeNum > 0) {
		        	
		        	switch(pageChangeNum) {
		        	
			        	case 1:
			        		System.out.println("<<  Page Change Start  >>");
			        		pageInfo = null;
			        		System.gc();
			        		pageInfo = new PageMainMenu();
			        		System.out.println("<<  Page Changed  >>");
			        	break;
			        	
			        	case 2:
			        		System.out.println("<<  Page Change Start  >>");
			        		pageInfo = null;
			        		System.gc();
			        		pageInfo = new PageVMMgr();
			        		System.out.println("<<  Page Changed  >>");
			        	break;
			        	
			        	case 3:
			        		System.out.println("<<  Page Change Start  >>");
			        		pageInfo = null;
			        		System.gc();
			        		pageInfo = new PageVMManagementLogin();
			        		System.out.println("<<  Page Changed  >>");
			        	break;
			        	
			        	case 4:
			        		System.out.println("<<  Page Change Start  >>");
			        		pageInfo = null;
			        		System.gc();
			        		pageInfo = new PageVMManagement();
			        		System.out.println("<<  Page Changed  >>");
			        	break;
			        	
			        	default:
			        		break;

		        	}
		        	pageChangeNum = 0;
		        	
		        }
		        
		        // Page Update.
				pageInfo.Update();
				if (mousePressedIs == 1)
					pageInfo.mouseCheck();
				
				///////////////////////////////////////////////////////////////////////////////////////////////////////
			
			}

			try {

				Thread.sleep(2);

			} catch (InterruptedException e) {
				
				// TODO Auto-generated catch block.
				e.printStackTrace();

			}

			if (shouldRender) {
				
				frames++;
				render();
				
			}
			
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				
				lastTimer += 1000;
				fps = frames;
				ticks = 0;
				frames = 0;
				
			}

		}
	}

	public void render() {

		// Setting Triple Buffering
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		// Renderer Setting
		mRenderer 		= bs.getDrawGraphics();
		mRenderer2D 	= (Graphics2D)mRenderer;
		panel.paint(mRenderer);
		
		// Draw Main Background Fill.
		mRenderer2D.setPaint(new Color(25, 24, 38));
		mRenderer2D.fillRect(0, 0, getWidth(), getHeight());
		//mRenderer2D.setPaint(new Color(20, 20, 20));
		//mRenderer2D.fillRect(0, 0, titlebar_width, titlebar_height);

		// Anti-Aliasing
		mRenderer2D.setRenderingHint(
		        RenderingHints.KEY_TEXT_ANTIALIASING,
		        RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		// Basic Color - white
		mRenderer2D.setPaint(Color.white);
		// Show Frame Rate For Render
		mRenderer.drawString(String.valueOf(fps), 1250, 16);
		// PageRender
		pageInfo.Render();

		// Dispose Renderer
	    mRenderer2D.dispose();
		mRenderer.dispose();

		bs.show();

	}

	public static void main(String args[]) {
		new Game().start();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseEvent = e;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePressedIs = 1;
		pageInfo.mousePressed();

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		mousePressedIs = 0;
		pageInfo.mouseReleased();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX = e.getX();
		mouseY = e.getY();
	}

}