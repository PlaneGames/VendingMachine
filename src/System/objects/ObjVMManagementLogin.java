package System.objects;

import static System.Game.frame;
import static System.Game.panel;
import static System.Game.screen_height;
import static System.Game.screen_width;
import static System.Game.loginTextField;
import static System.Game.mouseX;
import static System.Game.mouseY;
import static System.Game.pageChangeNum;
import static System.Game.mRenderer;

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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTextField;

import com.*;

import System.Game;
import System.objects.coins.ObjCoinPar;

public class ObjVMManagementLogin extends Object {
	
	Matcher match;
	private Font fontTitle;
	private Font fontButton;
	private Font fontTextField 	= new Font("본고딕",Font.BOLD, 12);
	private double buttonAlpha = 0.0;
	
	private String pw = "jaeyoung21+";
	
	public static final String pattern = "^[[A-Za-z]$@$!%*#?&+-_]{8,20}$"; // 특수문자, 숫자
	
	public boolean pwdRegularExpressionChk(String newPwd, String oldPwd) {
		
		boolean chk = false;
		match = Pattern.compile(pattern).matcher(newPwd);
		if(match.find()) {
			chk = true;
		}
		return chk;
	
	}
	
	public ObjVMManagementLogin() {
		Init();
		System.out.println("ObjVMManagement Created.");
		loginTextField.setEnabled(true);
        loginTextField.setVisible(true);
        loginTextField.setFont(fontTextField);
	}
	
	public void finalize() {
		System.out.println("ObjVMManagement Deleted.");
	}

	@Override
	public void Init() {
		
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
	}

	@Override
	public void Update() {
		if (mouseX >= screen_width/2 - 40 && 
			mouseY >= screen_height/2 + 60 - 24 && 
			mouseX <= screen_width/2 + 40 && 
			mouseY <= screen_height/2 + 60 + 24) {
				
			if (buttonAlpha < 1.0) {
				buttonAlpha += 0.05;
			}
				
		} else {
			if (buttonAlpha > 0.5) {
				buttonAlpha -= 0.05;
			}
			if (buttonAlpha < 0.5) {
				buttonAlpha = 0.5;
			}
		}
	}

	@Override
	public void Render() {
		
		drawSetColor(Color.WHITE);
		drawSetAlpha(1.0f);
		mRenderer.setFont(fontTitle);
		drawTextCenter("VENDING MACHINE", 132, 32, fontTitle);
		drawTextCenter("BACK", screen_width - 80, 32, fontTitle);
		
		mRenderer.setFont(fontButton);
		drawTextCenter("Please enter a password", screen_width/2, screen_height/2 - 40, fontButton);
		mRenderer.setFont(fontTextField);
		drawSetAlpha(0.4f);
		drawTextCenter("BETA 비밀번호 : " + String.valueOf(pw), screen_width/2, screen_height/2 - 10, fontTextField);
		drawSetAlpha(buttonAlpha);
		drawTextCenter("ENTER", screen_width/2, screen_height/2 + 60, fontButton);
	}
	
	@Override
	public void mouseReleased() {

	}
	
	@Override
	public void mousePressed() {
		
		if (mouseX >= screen_width - 128 && 
			mouseY >= 0 && 
			mouseX <= screen_width && 
			mouseY <= 48 ) {
			
			loginTextField.setEnabled(false);
	        loginTextField.setVisible(false);
	        frame.setVisible(true);
			pageChangeNum = 1;
					
		}
		
		if (mouseX >= screen_width/2 - 40 && 
			mouseY >= screen_height/2 + 60 - 24 && 
			mouseX <= screen_width/2 + 40 && 
			mouseY <= screen_height/2 + 60 + 24) {

			String n = loginTextField.getText();
			
			if (n.equals(pw)) {
				System.out.println(n);
				loginTextField.setEnabled(false);
		        loginTextField.setVisible(false);
		        frame.setVisible(true);
		        pageChangeNum = 4;
			}
			//if (pwdRegularExpressionChk(n, "hakwon4u+")) {
			//	System.out.println(n);
			//}
		}
	}
	
}
