package supersql.codegenerator.Responsive;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.Augmenter;

import supersql.codegenerator.Fraction;
import supersql.common.GlobalEnv;


public class Screenshot {
	
	public static void CaptureScreenshot(WebDriver driver, JavascriptExecutor js){
		TakesScreenshot ts = (TakesScreenshot) new Augmenter().augment(driver);
        
	      //画面サイズで必要なものを取得
	        int innerH = Integer.parseInt(String.valueOf(js.executeScript("return window.innerHeight")));
	        int innerW =Integer.parseInt(String.valueOf(js.executeScript("return window.innerWidth")));
	        int scrollH = Integer.parseInt(String.valueOf(js.executeScript("return document.documentElement.scrollHeight")));
	        
	      //イメージを扱うための準備
	        BufferedImage img = new BufferedImage(innerW, scrollH, BufferedImage.TYPE_INT_ARGB);
	        Graphics g = img.getGraphics();
	        
	        try {
		      //スクロールを行うかの判定
		        if(innerH>scrollH){
		            BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
		            g.drawImage(imageParts, 0, 0, null);
		        } else {
		            int scrollableH = scrollH;
		            int i = 0;
		
		            //スクロールしながらなんどもイメージを結合していく
		            while(scrollableH>innerH){
		                BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
		                g.drawImage(imageParts, 0, innerH*i, null);
		                scrollableH=scrollableH - innerH;
		                i++;
		                js.executeScript("window.scrollTo(0,"+innerH*i+")");
		            }
		
		            //一番下まで行ったときは、下から埋めるように貼り付け
		            BufferedImage imageParts = ImageIO.read(ts.getScreenshotAs(OutputType.FILE));
		            g.drawImage(imageParts, 0, scrollH - innerH, null);
		        }
		
		        ImageIO.write(img, "png", new File("/Users/ryosuke/Desktop/screenshot"+ System.currentTimeMillis() +".png"));
	        } catch (WebDriverException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			} catch (IOException e) {
				// TODO 自動生成された catch ブロック
				e.printStackTrace();
			}
	}
}
