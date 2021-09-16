package System;

import static System.Game.ac;
import static System.Game.mRenderer;
import static System.Game.mRenderer2D;
import static System.Game.mousePressedIs;
import static System.Game.mouseX;
import static System.Game.mouseY;
import static System.Game.screen_height;
import static System.Game.screen_width;

import java.awt.AlphaComposite;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

public class DrawFunc extends Canvas {
	
	// Ease
	public double easeInSine(int t, double s, double c, int d) {
		return c * Math.sin((double)t / d * (Math.PI / 2)) + s;
	}

	// Graphic
	public Image imageSetSize(Image img, int xscale, int yscale) {
		Image scaleImg = img.getScaledInstance(xscale, yscale, Image.SCALE_SMOOTH);
		return scaleImg;
	}
	
	public void printGrid(int gridXSize, int gridYSize) {
		int i, j;
		for (i = 0; i <= getWidth()/gridXSize; i ++)
			mRenderer.drawLine(i*gridXSize, 0, i*gridXSize, screen_height);
		for (j = 0; j <= getHeight()/gridYSize; j ++)
			mRenderer.drawLine(0, j*gridYSize, screen_width, j*gridYSize);
	}
	
	public void drawSetAlpha(double alpha) {
		ac = AlphaComposite.getInstance(AlphaComposite.SRC_OVER, (float) Math.min(alpha, 1f));
        mRenderer2D.setComposite(ac);
	}
	
	public void drawSetColor(Color color) {
		mRenderer.setColor(color);
	}
	
	public void drawText(String string, int x, int y) {
		mRenderer.drawString(string, x, y);
	}

	public void drawSprite(Image img, int x, int y) {
		int imgW = img.getWidth(this);
		int imgH = img.getHeight(this);
		mRenderer.drawImage(img, x, y, this);
	}

	public void drawSpriteExt(Image img, int x, int y, int xscale, int yscale) {
		Image scaleImg = img.getScaledInstance(xscale, yscale, Image.SCALE_SMOOTH);
		mRenderer.drawImage(scaleImg, x, y, this);
	}

	public void drawFill(int x, int y, int w, int h) {
		mRenderer.fillRect(x, y, w, h);
	}

	public void drawTextCenter(String s, int x, int y, Font font) {
		Rectangle r = new Rectangle(x, y, 0, 0);
	    FontRenderContext frc = new FontRenderContext(null, true, true);
	    Rectangle2D r2D = font.getStringBounds(s, frc);

	    int rWidth = (int) Math.round(r2D.getWidth());
	    int rHeight = (int) Math.round(r2D.getHeight());
	    int rX = (int) Math.round(r2D.getX());
	    int rY = (int) Math.round(r2D.getY());
	    int a = (r.width / 2) - (rWidth / 2) - rX;
	    int b = (r.height / 2) - (rHeight / 2) - rY;

	    mRenderer.setFont(font);
	    mRenderer.drawString(s, r.x + a, r.y + b);
	}
	
}