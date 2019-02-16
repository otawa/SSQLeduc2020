package supersql.codegenerator.Responsive;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map.Entry;

import supersql.codegenerator.CodeGenerator;
import supersql.codegenerator.DecorateList;
import supersql.codegenerator.Fraction;
import supersql.codegenerator.Sass;
import supersql.codegenerator.Mobile_HTML5.Mobile_HTML5Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;
import supersql.parser.Start_Parse;

//added by goto 20161217
public class Responsive {
	
	public final static String OPTION_NAME = "rurl";
	private static String option = "";
	
	private final static String MODIFIER = "responsive";
	private static boolean responsive = false;
	private static boolean reExec = false;		//CG再実行時: true
	
	public static LinkedHashMap<String, LinkedHashMap> fixMap = new LinkedHashMap<>();
	
	public Responsive() {

	}
	
	
	// setOption()
	public static void setOption(String val) {
		option = val;
	}
	// getOption()
	public static String getOption() {
		return option;
	}
	// getResponsiveURL()
	public static String getResponsiveURL() {
		return GlobalEnv.getResponsiveURL();
	}
	
	// check()
	public static boolean check(DecorateList decos) {
		if(decos.containsKey(MODIFIER)){
			responsive = true;
			return true;
		}
		return false;
	}
	
	// isResponsive()
	public static boolean isResponsive() {
		return responsive;
	}
	
	
	// process() : process1, process2 の実行
	public static boolean process(CodeGenerator codegenerator, Start_Parse parser, ExtList extList) {
		if(!isResponsive())	return false;
		if(getResponsiveURL().isEmpty()){
			Log.err("No responsive option specified. (e.g. -"+OPTION_NAME+" URL)\n");
			return false;
		}
		
		LinkedHashMap<String, LinkedHashMap> fixMap = process1(Sass.HTMLCheckMap);
		process2(fixMap, codegenerator, parser, extList);
		return true;
	}
	
	// process1() : TFE全体に@{responsive}がついていた場合に、HTMLなどが生成された後Responsiveの処理に投げる
	private static LinkedHashMap<String, LinkedHashMap> process1(LinkedHashMap SassHTMLCheckMap) {
		//TODO HTMLなどをチェックするメソッドの呼び出し
		LinkedHashMap<Integer, LinkedHashMap>HTMLCheckMap = SassHTMLCheckMap;
		
		int countC1 = 0;
		int countG1 = 0;
		int C1elementSum = 0;
		int C1elementProduct = 1;
		int C1patternSum = 0;
		int C1executionSum = 0;
		for(Entry<Integer, LinkedHashMap> e : HTMLCheckMap.entrySet()) {
			LinkedHashMap<String, LinkedHashMap> C1G1Map_A = e.getValue();
			if(C1G1Map_A.containsKey("C1")){
				countC1++;
				int C1Size = C1G1Map_A.get("C1").size();
				
				C1elementSum += C1Size;
				C1elementProduct *= C1Size;
				C1patternSum += Fix_C1.getAllC1Pattern(C1Size).size();
				C1executionSum += (Fix_C1.getAllC1Pattern(C1Size).size() * C1Size);
			}else if(C1G1Map_A.containsKey("G1")){
				countG1++;
			}
		}
		
		Log.info("G1 Count: ");
		Log.info(countG1);
		Log.info("C1 Count: ");
		Log.info(countC1);
		Log.info("C1elementSum: ");
		Log.info(C1elementSum);
		Log.info("C1patternSum: ");
		Log.info(C1patternSum);
		Log.info("C1executionSum: ");
		Log.info(C1executionSum);
		
		
		//forevaluation
		long driver_start = System.nanoTime();
		
		Driver.setupDriver();
		//forevaluation
		long driver_end = System.nanoTime();
		Log.info("Driver Time:(ms)");
		Log.info((driver_end - driver_start) / 1000000f );
		
		//キーのリストを作る
		List<Integer> keylist = new ArrayList<Integer>(HTMLCheckMap.keySet());

		//Comparator でキーを降順ソート
//		Collections.sort(keylist, Comparator.reverseOrder());
				
		//forevaluation
		long fix_start = System.nanoTime();
		//for (Integer key : keylist) {
		for (Integer key : keylist) {
			LinkedHashMap<String, LinkedHashMap> C1G1Map_B = HTMLCheckMap.get(key);
			if(C1G1Map_B.containsKey("C1")){
				C1G1Map_B.get("C1");
				Fix_C1.C1Fix(C1G1Map_B.get("C1"));
			}else if(C1G1Map_B.containsKey("G1")){
				C1G1Map_B.get("G1");
				Fix_G1.G1Fix(C1G1Map_B.get("G1"));
			}
		}
		//forevaluation
		long fix_end = System.nanoTime();
		Log.info("Fix Time:(ms)");
		Log.info((fix_end - fix_start) / 1000000f);
		
		long driver_quit_start = System.nanoTime();
		Driver.quitDriver();
		long driver_quit_end = System.nanoTime();
		Log.info("Driver Quit Time:" + (driver_quit_end - driver_quit_start) / 1000000f + "ms");
		
		LinkedHashMap<String, LinkedHashMap> fixMap = new LinkedHashMap<>();
		fixMap = Driver.fixMap;
		Log.info(fixMap+"\n");
		
		int fixCount = 0;
		for(Entry<String, LinkedHashMap> e : fixMap.entrySet()) {
			LinkedHashMap<String, Fraction> sizeMap = e.getValue();
			Fraction onebefore = new Fraction("0/1");
			Log.info(e.getKey());
			for(Entry<String, Fraction> f : sizeMap.entrySet()) {
				if(f.getKey().equals("lg")){
					onebefore = f.getValue();
					Log.info(f.getKey() + "=" + f.getValue());
				}else{
					if( !f.getValue().toString().equals(onebefore.toString()) ){
						fixCount++;
						onebefore = f.getValue();
						Log.info(f.getKey() + "=" + f.getValue());
					};
				}
			}
		}
		Log.info("\nfixCount= "+fixCount);
		
		
		return fixMap;
	}
	
	// process2() : 修正案がResponsive側から返ってきたときに、Code Generatorを再実行する
	private static boolean process2(LinkedHashMap<String, LinkedHashMap> fixMap, CodeGenerator codegenerator, Start_Parse parser, ExtList extList) {
		Mobile_HTML5Manager.initAllValiables();
		
		Responsive.fixMap = fixMap;
		Responsive.reExec = true;
		codegenerator.generateCode(parser, extList);	//再実行
		Responsive.reExec = false;
		return true;
	}
	// isReExec()
	public static boolean isReExec() {
		return reExec;
	}
	
	// checkBootstrapModifiers()
	public static DecorateList checkBootstrapModifiers(String classid, DecorateList decos) {
		if(isReExec()){
			try {
				//rewrite bootstrap modifiers
				LinkedHashMap<String, String> fMap = fixMap.get(classid);
				for (String key : fMap.keySet()) {
					String value = fMap.get(key);
					decos.put(key, value);
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}
		}
		return decos;
	}

}
