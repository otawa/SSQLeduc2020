package supersql.codegenerator.Responsive;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import supersql.codegenerator.Fraction;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Env;
import supersql.common.GlobalEnv;


public class Driver {

	public static WebDriver driver_lg;
	public static WebDriver driver_md;
	public static WebDriver driver_sm;
	public static WebDriver driver_xs;
	
	private static final String driverPath = GlobalEnv.getworkingDir()+"/webdriver/geckodriver";
//	private static final String driverPath = GlobalEnv.getworkingDir()+"/webdriver/chromedriver";
	
	public static LinkedHashMap<String,LinkedHashMap> fixMap = new LinkedHashMap<String,LinkedHashMap>();
	
	
	public static void setupDriver() {
		System.setProperty("webdriver.gecko.driver", driverPath);
//		WebDriver driver = new FirefoxDriver();
//		JavascriptExecutor js = (JavascriptExecutor) driver;
		
		//Setup Webdriver for 4 display size
		driver_lg = new FirefoxDriver();
		driver_lg.manage().window().setSize(new Dimension(1200,3000));
		
		driver_md = new FirefoxDriver();
		driver_md.manage().window().setSize(new Dimension(992,3000));
		
		driver_sm = new FirefoxDriver();
		driver_sm.manage().window().setSize(new Dimension(768,3000));
		
		driver_xs = new FirefoxDriver();
		driver_xs.manage().window().setSize(new Dimension(400,3000));

		//Get page of URL
		String fn = new Mobile_HTML5Env().getFileName4()+".html";
		fn = Responsive.getResponsiveURL()+fn;
		driver_lg.get(fn);
		driver_md.get(fn);
		driver_sm.get(fn);
		driver_xs.get(fn);
		
	}
	
	public static void quitDriver(){
        driver_lg.quit();
        driver_md.quit();
        driver_sm.quit();
        driver_xs.quit();
    }
	
	public static void G1Fix(String TFEid, Fraction original_size, WebDriver driver_lg, WebDriver driver_md, WebDriver driver_sm, WebDriver driver_xs, LinkedHashMap fixMap){
		
		int original_division = original_size.getDenominator();
		
		//Get G1 Element x4
        WebElement element_lg = driver_lg.findElement(By.className(TFEid));
        List<WebElement> elements_lg = driver_lg.findElements(By.className(TFEid));
        
        WebElement element_md = driver_md.findElement(By.className(TFEid));
        List<WebElement> elements_md = driver_md.findElements(By.className(TFEid));
        
        WebElement element_sm = driver_sm.findElement(By.className(TFEid));
        List<WebElement> elements_sm = driver_sm.findElements(By.className(TFEid));
        
        WebElement element_xs = driver_xs.findElement(By.className(TFEid));
        List<WebElement> elements_xs = driver_xs.findElements(By.className(TFEid));
        
        //Get width of each display size
        LinkedHashMap<String,Integer> G1widthMap = new LinkedHashMap<String,Integer>();
        G1widthMap.put("lg", element_lg.getSize().width);
        G1widthMap.put("md", element_md.getSize().width);
        G1widthMap.put("sm", element_sm.getSize().width);
        G1widthMap.put("xs", element_xs.getSize().width);
        
        
        LinkedHashMap<String,Fraction> G1fixMap = new LinkedHashMap<String,Fraction>();
        //Calculate Best
        for(Map.Entry<String, Integer> e : G1widthMap.entrySet()) {
        	double minDiff=5000;
            int best = 0;
            
        	String size = e.getKey();
        	int eachwidth = e.getValue();
        	
        	for(int i=0; i<original_division; i++){
                double width = Math.floor( (eachwidth * original_division ) / ( original_division - i ) );
                if (Math.abs(width-G1widthMap.get("lg")) < minDiff){
                	minDiff = Math.abs(width-G1widthMap.get("lg"));
                	best = original_division - i;
                }
            }
        	
        	G1fixMap.put(size, new Fraction("1/"+best));
        	
            double fixWidth = 100.0/best;
            BigDecimal bd =new BigDecimal(fixWidth);
            BigDecimal bd4 = bd.setScale(3, BigDecimal.ROUND_DOWN);

            switch (e.getKey()){
            case "md":
            	for(WebElement each : elements_md){
            		((JavascriptExecutor)driver_md).executeScript("arguments[0].setAttribute('style', 'width:"+bd4+"%')",each);
                }
            case "sm":
            	for(WebElement each : elements_sm){
            		((JavascriptExecutor)driver_sm).executeScript("arguments[0].setAttribute('style', 'width:"+bd4+"%')",each);
                }
            case "xs":
            	for(WebElement each : elements_xs){
            		((JavascriptExecutor)driver_xs).executeScript("arguments[0].setAttribute('style', 'width:"+bd4+"%')",each);
                }
            }
        }
        fixMap.put(TFEid, G1fixMap);
    	System.out.println(fixMap);
	}
	
	public static LinkedHashMap putinmap(String key, LinkedHashMap map){
		LinkedHashMap<String, LinkedHashMap> returnMap = new LinkedHashMap<String, LinkedHashMap>();
		returnMap.put(key, map);
		return returnMap;
	}
	
	public static LinkedHashMap putsize(String key, Fraction size){
		LinkedHashMap<String, Fraction> returnMap = new LinkedHashMap<String, Fraction>();
		returnMap.put(key, size);
		return returnMap;
	}
}
