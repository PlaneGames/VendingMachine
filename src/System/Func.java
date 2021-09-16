package System;
/*
package System;
import System.DrawFunc;

import java.awt.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.image.*;
import java.awt.Toolkit;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;

import javax.swing.*;

public class Game extends Canvas implements Runnable {
	
	DrawFunc drawFunc = new DrawFunc();
	
	private static int WIDTH 	= 1280;
	private static int HEIGHT 	= 720;
	private static String NAME 	= "Game";
	private boolean isRunning 	= false;
	
	Image background = new ImageIcon(Game.class.getResource("/sprTUDIcon.png")).getImage();

	public static AlphaComposite ac;
	public static Graphics mRenderer;
	public static Graphics2D mRenderer2D;

	private JFrame frame;
	private JPanel panel;
	
	private Color bg_color = new Color(40, 40, 40);
	
	public Image imageSetSize(Image img, int xscale, int yscale) {
		Image scaleImg = img.getScaledInstance(xscale, yscale, Image.SCALE_SMOOTH);
		return scaleImg;
	}

	public Game() {
		
        background = imageSetSize(background, 64, 64);

        // Frame Setting.
        
        frame = new JFrame(NAME);
        panel = new JPanel();

        panel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        panel.setLayout(new BorderLayout());
        panel.add(this,BorderLayout.CENTER);
        
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
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
			}

			try {
				
				Thread.sleep(2);
				
			} catch (InterruptedException e) {
				
				// TODO Auto-generated catch block
				e.printStackTrace();
		
			}

			if (shouldRender) {
				frames++;
				render();
			}
			if (System.currentTimeMillis() - lastTimer >= 1000) {
				lastTimer += 1000;
				System.out.println(ticks + " , " + frames);
				ticks = 0;
				frames = 0;
			}
			
		}
	}

	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3); // 트리플 버퍼링
			return;
		}
		
		// Renderer Setting
		mRenderer 		= bs.getDrawGraphics();
		mRenderer2D 	= (Graphics2D)mRenderer;
		panel.paint(mRenderer);

		// Main Background Fill.
		mRenderer.setColor(bg_color);
		mRenderer.fillRect(0, 0, getWidth(), getHeight());
		 
		mRenderer.drawImage(background, WIDTH/2 - 32, HEIGHT/2 - 32, this);

		drawFunc.drawSetColor(mRenderer, Color.WHITE);
		drawFunc.drawSetAlpha(mRenderer2D, ac, 0.2);
        
		drawFunc.printGrid(mRenderer, 80, 80);

        ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f);
        mRenderer2D.setComposite(ac);
        
        mRenderer.drawImage(background, 0, 0, null);
        
        ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.1f);
        mRenderer2D.setComposite(ac);
        
		Color c = new Color(0.0f, 1.0f, 0.0f, 0.7f);
		
		mRenderer.setColor(c);
	    mRenderer.drawString(Integer.toString(getWidth()), 16, 16);
	    mRenderer.fillRect(100, 100, 100, 100);
	    mRenderer.drawLine(100, 0, 100, HEIGHT);
	    //mRenderer.drawString(String.valueOf(getWidth()), 28, 28);
	    
	    mRenderer2D.dispose();
		mRenderer.dispose();
		bs.show();

	}

	public static void main(String args[]) {
		new Game().start();
	}

}
*/