package supersql.codegenerator.PDF;

import java.util.Hashtable;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import supersql.codegenerator.ITFE;
import supersql.extendclass.ExtList;

//PDFに関する基本的な変数とメソッドを持つクラス
public class PDFOptimizer {
	
	private int i, j;
	
	private PDFEnv pdf_env;
	
	private ExtList flatList = new ExtList();
	private Hashtable maxWidth = new Hashtable();
	private Hashtable maxHeight = new Hashtable();
	
	//------------------------
	private Hashtable maxOriginalWidth = new Hashtable();
	private Hashtable maxOriginalHeight = new Hashtable();
	
	
	
	//縦反復の高さ、横反復の幅を調節する時に使用
	private ExtList matchListH = new ExtList();
	private ExtList matchListV = new ExtList();

	
	private ExtList resultList = new ExtList();
	
	
	private ITFE tfe_info;
	private ExtList flatTFE = new ExtList();
	
	
	//コンストラクタ
	public PDFOptimizer(PDFEnv pdf_env) {
		this.pdf_env = pdf_env;
	}
	
	
	
	//追加10.24　サイズを再調整するメソッド　
	//リストをフラットにしてから各兄弟の最大幅を取得し、
	//リストの折り畳みがあった場合は折り畳みの再構成を行ったりする
	//最終的にサイズを完璧に調整し、各PDFValueにセット
	public int adjust(PDFValue result){		
		
		//debug("Before Sort Label");
		
		sortLabelListH();
		sortLabelListV();

		//debug("After Sort Label");

		
		List2Flat(result);

		adjustWidth();
		adjustHeight();
		
		
		int layoutChange;
		
		//--- G1のFOLDとG2のFOLD、どっちの方が深い位置にあるか調べる ---//
		int from = 0;
		int index = 0;
		int depthH = 0;
		int depthV = 0;
		for(i=flatList.size()-1; i>-1; i--){
			PDFValue check = (PDFValue)flatList.get(i);
			if(check.foldFLAG_H){
				while(index != -1){
					index = check.labelH.indexOf("-", from);
					from = index;
					depthH++;
				}
			}
			else if(check.foldFLAG_V){
				while(index != -1){
					index = check.labelV.indexOf("-", from);
					from = index;
					depthV++;
				}
			}
		}
		//--------------------------------------------------------------//
		
		//深かった方だけ最大サイズをセット
		//  次のAdjustで浅いかった方が無視されないように、
		//  restoreFOLDメソッド内で浅かった方にfalseを代入
		//FOLDの再構成がない時、両方に最大サイズをセット
		if(depthH > depthV){
			set_maxWidth(result);
			restoreFOLD("H");
			layoutChange = 1;
		}
		else if(depthH < depthV){
			set_maxHeight(result);
			restoreFOLD("V");
			layoutChange = 1;
		}
		else{
			set_maxWidth(result);
			set_maxHeight(result);
			
			//折り畳みの再構成は済んでいる
			//その構成に従ってオリジナルサイズのラベルも振られている
			sortLabelListOH();
			sortLabelListOV();
			initializeOriginalAdjust();
			adjustOriginalWidth();
			adjustOriginalHeight();
			set_maxOriginalWidth(result);
			set_maxOriginalHeight(result);
			
			layoutChange = 0;
		}

		
		//変更があったら１を返し、なかったら0を返す
		return layoutChange;
	}
	
	
	//public int optimizeSTART(TFE tfe_info, ExtList data_info, PDFValue result){
	public int optimizeSTART(ExtList data_info, PDFValue result){
		boolean change = false;
		int layoutChange;
		
		float widthAll;
		float heightAll;
		float Dexcess;
		
		if(result.type.equals("G1")&&result.fold > pdf_env.widthPaper - pdf_env.paddingPaper_H*2){
			System.out.println("ERROR: folding length is over the Paper width");
			System.exit(0);
		}
		else if(result.type.equals("G2")&&result.fold > pdf_env.heightPaper - pdf_env.paddingPaper_V*2){
			System.out.println("ERROR: folding length is over the Paper height");
			System.exit(0);
		}
		
		//最上位が横反復で高さを調節する
			//縦反復で折り畳まれていた場合もこの処理だが、効率的でない
			//foldが紙の高さを超えていたらエラーにするだけ
		else if( result.type.equals("G1") ){		
			
			//-- 折り畳んでいてもいなくても一番大きな行の高さがページ内に入りきるかを調べる --//
			//移動
	/*
			float tmpHeight;
			float maxRowHeight = 0;
			for(i=0; i<result.rowNum+1; i++){
				tmpHeight = Float.parseFloat((String)result.rowHeights.get(i));
				if(maxRowHeight < tmpHeight)
					maxRowHeight = tmpHeight;
			}
			heightAll = maxRowHeight;	
	*/
			
			heightAll = Float.parseFloat((String)maxHeight.get("0"));			
			if(heightAll > pdf_env.heightPaper - pdf_env.paddingPaper_V*2){
				System.out.println("Optimizing Height");
				Dexcess = heightAll - pdf_env.heightPaper + pdf_env.paddingPaper_V*2;
				change = ((PDFTFE)tfe_info).optimizeH(Dexcess, result);
			}
			else
				System.out.println("===> Not need to optimize");
			
		}
		
		//最上位が縦反復で幅を調節する
			//横反復で折り畳まれていた場合もこの処理だが、効率的でない
			//foldが紙の幅を超えていたらエラーにするだけ
		else if( result.type.equals("G2") ){
			
			//--- 折り畳んでいてもいなくても一番大きな列の幅がページ内に入りきるかを調べる ---//
			//移動
	/*		
	 		float tmpWidth;
			float maxColumnWidth = 0;
			for(i=0; i<result.columnNum+1; i++){
				tmpWidth = Float.parseFloat((String)result.columnWidths.get(i));
				if(maxColumnWidth < tmpWidth)
					maxColumnWidth = tmpWidth;
			}
			widthAll = maxColumnWidth;
	*/
			widthAll = Float.parseFloat((String)maxWidth.get("0"));
			if(widthAll > pdf_env.widthPaper - pdf_env.paddingPaper_H*2){
				System.out.println("Optimizing Width: widthAll = "+widthAll+"  widthPaper = "+(pdf_env.widthPaper - pdf_env.paddingPaper_H*2));
				Dexcess = widthAll - pdf_env.widthPaper + pdf_env.paddingPaper_H*2;
				change = ((PDFTFE)tfe_info).optimizeW(Dexcess, result);
			}
			else
				System.out.println("===> Not need to optimize");
			
		}
/*		
		if(layoutChange){
			tfe_info.work(data_info);
			PDFValue newResult = ((PDFTFE)tfe_info).getInstance();
			
			//変数の初期化
			initializeAdjust();	
			
			((PDFTFE)tfe_info).setLabel(newResult);
			
			adjust(newResult);
		}
*/		
		
		if(change)
			layoutChange = 2;
		else
			layoutChange = -1;
		
		//なにも変更がなかったら-1を、あったら2を返す
		return layoutChange;
	}
	
	
	public void divideResult(PDFValue result){
		PDFValue instance;
		
		ExtList AllPage = new ExtList();


		//--------- レイアウト変更したはずなのに、縦・横両方オーバーしている場合 ----------//
		if(result.box_height > pdf_env.heightPaper && result.box_width > pdf_env.widthPaper){
			System.out.println("ERROR: LayoutChange is not completion with success");
			System.exit(0);
		}
		
		//----------- 縦にオーバーしている場合 (G2とG1で折り畳んだ場合の2通り) ------------//
		else if(result.box_height > pdf_env.heightPaper){
			
			//-------- G2の場合 -------------------------------------------------//
			if(result.type.equals("G2")){
				//分割後の1ページ目のG2にあたるboxをつくる//
				PDFValue Page = new PDFValue("G2");
				Page.columnNum = result.columnNum;
				Page.columns = result.columns;
			
				float tmpHeight = 0;
				
				for(i=0; i<result.inList.size(); i++){
					instance = (PDFValue)result.inList.get(i);
				
					if(instance.box_height > pdf_env.heightPaper - pdf_env.paddingPaper_V*2){
						//ハイパーリンク使わなきゃ・・・
						System.out.println(instance.box_height);
						for(j=0; j<4; j++)
						System.out.println(((PDFValue)instance.inList.get(j)).box_height);//------------------------
						System.out.println("ERROR: one instance is over the Paper heihgt");
						System.exit(0);
					}
				
					tmpHeight += instance.box_height;
					if(tmpHeight > (pdf_env.heightPaper - pdf_env.paddingPaper_V*2)){
						AllPage.add(Page);
						
						//次ページのG2にあたるboxをつくる//
						Page = new PDFValue("G2");
						Page.columnNum = result.columnNum;
						Page.columns = result.columns;
						
						tmpHeight = instance.box_height;
					}
					
					Page.inList.add(instance);				
				}
				
				AllPage.add(Page);
			}
			
			//-------- G1で折り畳んだ場合 ---------------------------------------//
			else{
				//分割後の1ページ目のG1にあたるboxをつくる//
				PDFValue Page = new PDFValue("G1");
				
				float rowHeight;
				float tmpHeight = 0;
				int fromRow = 0;
				int endRow = 0;
				int pageInstanceNum = 0;
				int instanceIndex = 0;
				
				for(i=0; i<result.rowNum+1; i++){
					rowHeight = Float.parseFloat((String)result.rowHeights.get(i));
					
					if(rowHeight > pdf_env.heightPaper - pdf_env.paddingPaper_V*2){
						//ハイパーリンク使わなきゃ・・・
						System.out.println("ERROR: one row is over the Paper heihgt");
						System.exit(0);
					}
					
					tmpHeight += rowHeight;
					if(tmpHeight > (pdf_env.heightPaper - pdf_env.paddingPaper_V*2)){
						
						//----------------------------------//
						//			怪しい					//
						Page.box_width = 0;
						//Page.box_width = result.box_width;
						Page.box_height = 0;
						Page.rows = result.rows.ExtsubList(fromRow, endRow);
						Page.rowHeights = result.rowHeights.ExtsubList(fromRow, endRow);
						Page.rowNum = endRow-1 - fromRow;

						pageInstanceNum = Integer.parseInt((String)result.rows.get(endRow-1));
						for(j=0;j<=Page.rowNum;j++){
							Page.rows.set(j,Integer.toString(Integer.parseInt((String)Page.rows.get(j))-instanceIndex));
						}
						for(j=instanceIndex; j<pageInstanceNum; j++){
							instance = (PDFValue)result.inList.get(j);
							Page.inList.add(instance);
						}
						instanceIndex = pageInstanceNum;
						pageInstanceNum = 0;
						//----------------------------------//
						
						AllPage.add(Page);
						Page = new PDFValue("G1");
						
						fromRow = endRow;
						tmpHeight = rowHeight;
					}
					
					endRow++;
				}
				//----------------------------------//
				//			怪しい					//
				Page.box_width = result.box_width;
				Page.rows = result.rows.ExtsubList(fromRow, endRow);
				Page.rowHeights = result.rowHeights.ExtsubList(fromRow, endRow);
				Page.rowNum = endRow-1 - fromRow;

				pageInstanceNum = Integer.parseInt((String)result.rows.get(endRow-1));
				for(j=0;j<=Page.rowNum;j++){
					Page.rows.set(j,Integer.toString(Integer.parseInt((String)Page.rows.get(j))-instanceIndex));
				}
				for(j=instanceIndex; j<pageInstanceNum; j++){
					instance = (PDFValue)result.inList.get(j);
					Page.inList.add(instance);
				}
				//----------------------------------//
				
				AllPage.add(Page);
			}
		}
		
		//----------- 横にオーバーしている場合 (G1とG2で折り畳んだ場合の2通り) ------------//
		else if(result.box_width > pdf_env.widthPaper){
			
			//-------- G1の場合 -------------------------------------------------//
			if(result.type.equals("G1")){
				//分割後の1ページ目のG1にあたるboxをつくる//
				PDFValue Page = new PDFValue("G1");
				Page.rowNum = result.rowNum;
				Page.rows = result.rows;
			
				float tmpWidth = 0;
				
				for(i=0; i<result.inList.size(); i++){
					instance = (PDFValue)result.inList.get(i);
				
					if(instance.box_width > pdf_env.widthPaper - pdf_env.paddingPaper_H*2){
						//ハイパーリンク使わなきゃ・・・
						System.out.println("ERROR: one instance is over the Paper width");
						System.exit(0);
					}
				
					tmpWidth += instance.box_width;
					if(tmpWidth > (pdf_env.widthPaper - pdf_env.paddingPaper_H*2)){
						AllPage.add(Page);
						
						//次ページのG1にあたるboxをつくる//
						Page = new PDFValue("G1");
						Page.rowNum = result.rowNum;
						Page.rows = result.rows;
						
						tmpWidth = instance.box_width;
					}
					
					Page.inList.add(instance);				
				}
				
				AllPage.add(Page);
			}
			
			//-------- G2で折り畳んだ場合 ---------------------------------------//
			else{
				//分割後の1ページ目のG2にあたるboxをつくる//
				PDFValue Page = new PDFValue("G2");
				
				float columnWidth;
				float tmpWidth = 0;
				int fromColumn = 0;
				int endColumn = 0;
				int pageInstanceNum = 0;
				int instanceIndex = 0;
				
				for(i=0; i<result.columnNum+1; i++){
					columnWidth = Float.parseFloat((String)result.columnWidths.get(i));
					
					if(columnWidth > pdf_env.widthPaper - pdf_env.paddingPaper_H*2){
						//ハイパーリンク使わなきゃ・・・
						System.out.println("ERROR: one column is over the Paper width "+Float.toString(columnWidth)+"  "+ (pdf_env.widthPaper - pdf_env.paddingPaper_H*2));
						System.exit(0);
					}
					
					tmpWidth += columnWidth;
					if(tmpWidth > (pdf_env.widthPaper - pdf_env.paddingPaper_H*2)){
						
						//----------------------------------//
						//			怪しい					//
						Page.box_height = 0;
						//Page.box_height = result.box_height;
						Page.box_width = 0;
						Page.columns = result.columns.ExtsubList(fromColumn, endColumn);
						Page.columnWidths = result.columnWidths.ExtsubList(fromColumn, endColumn);
						Page.columnNum = endColumn-1 - fromColumn;
						
						pageInstanceNum = Integer.parseInt((String)result.columns.get(endColumn-1));
						for(j=0;j<=Page.columnNum;j++){
							Page.columns.set(j,Integer.toString(Integer.parseInt((String)Page.columns.get(j))-instanceIndex));
						}
						for(j=instanceIndex; j<pageInstanceNum; j++){
							instance = (PDFValue)result.inList.get(j);
							Page.inList.add(instance);
						}
						instanceIndex = pageInstanceNum;
						pageInstanceNum = 0;
						//----------------------------------//
						
						AllPage.add(Page);
						Page = new PDFValue("G2");
						
						fromColumn = endColumn;
						tmpWidth = columnWidth;
					}
					
					endColumn++;
				}
				//----------------------------------//
				//			怪しい					//
				Page.box_height = result.box_height;
				Page.columns = result.columns.ExtsubList(fromColumn, endColumn);
				Page.columnWidths = result.columnWidths.ExtsubList(fromColumn, endColumn);
				Page.columnNum = endColumn-1 - fromColumn;
				
				pageInstanceNum = Integer.parseInt((String)result.columns.get(endColumn-1));
				for(j=0;j<=Page.columnNum;j++){
					Page.columns.set(j,Integer.toString(Integer.parseInt((String)Page.columns.get(j))-instanceIndex));
				}
				for(j=instanceIndex; j<pageInstanceNum; j++){
					instance = (PDFValue)result.inList.get(j);
					Page.inList.add(instance);
				}
				//----------------------------------//
				
				AllPage.add(Page);
			}
		}

		//---------------------- まったく1ページで済んでしまう場合 -----------------------//
		else{
			
			if(result.type.equals("G1")){
				//ページのG1にあたるboxを新しくつくる//
				PDFValue Page = new PDFValue("G1");
				Page.rowNum = result.rowNum;
				Page.rows = result.rows;
				Page.rowHeights = result.rowHeights;
				Page.box_width = result.box_width;
				Page.box_height = result.box_height;
				
				for(i=0; i<result.inList.size(); i++){
					instance = (PDFValue)result.inList.get(i);
					
					Page.inList.add(instance);
				}
				
				AllPage.add(Page);
			}
			
			if(result.type.equals("G2")){
				//ページのG2にあたるboxを新しくつくる//
				PDFValue Page = new PDFValue("G2");
				Page.columnNum = result.columnNum;
				Page.columns = result.columns;
				Page.box_width = result.box_width;
				Page.box_height = result.box_height;
				
				for(i=0; i<result.inList.size(); i++){
					instance = (PDFValue)result.inList.get(i);
					
					Page.inList.add(instance);
				}
				
				AllPage.add(Page);
			}
		}
		
		//--------------------------------------------------------------------------------//		
		
		setResultList(AllPage);
	}
	
	
	public void initializeLabel(){
		pdf_env.labelH = 0;
		pdf_env.labelV = 0;
		pdf_env.labelmaxH = 0;
		pdf_env.labelmaxV = 0;
		pdf_env.labelSuffixH = "null";
		pdf_env.labelSuffixV = "null";
		pdf_env.labelListH.clear();
		pdf_env.labelListV.clear();
		
		pdf_env.labelO = 0;
		pdf_env.labelmaxO = 0;
		pdf_env.labelListOH.clear();
		pdf_env.labelListOV.clear();
	}
	
	
	public void initializeAdjust(){
		flatList.clear();
		
		maxWidth.clear();// = new Hashtable();
		for(i=0;i<pdf_env.labelListH.size();i++)
			maxWidth.put(pdf_env.labelListH.get(i), "0");
		
		maxHeight.clear();// = new Hashtable();
		for(i=0;i<pdf_env.labelListV.size();i++)
			maxHeight.put(pdf_env.labelListV.get(i), "0");
		
		matchListH.clear();
		matchListV.clear();
	}
	
	
	public void initializeOriginalAdjust(){		
		maxOriginalWidth.clear();// = new Hashtable();
		for(i=0;i<pdf_env.labelListOH.size();i++)
			maxOriginalWidth.put(pdf_env.labelListOH.get(i), "0");
		
		maxOriginalHeight.clear();// = new Hashtable();
		for(i=0;i<pdf_env.labelListOV.size();i++)
			maxOriginalHeight.put(pdf_env.labelListOV.get(i), "0");
		
		matchListH.clear();
		matchListV.clear();
	}
	
	
	public void sortLabelListH(){
		int i, j;
		String temp;
		
	    //バブルソートによる並び換え　同じだったら次のSuffixを比較
	    for(i=0; i<pdf_env.labelListH.size()-1; i++){
	    	
	    	for(j=pdf_env.labelListH.size()-1; j>i; j--){
	    		String bubble = (String)pdf_env.labelListH.get(j);
	    		String water = (String)pdf_env.labelListH.get(j-1);
	    		
    			StringTokenizer tokenALLBubble = new StringTokenizer(bubble, "-", false);
    			StringTokenizer tokenALLWater = new StringTokenizer(water, "-", false);
    			
	    		while(tokenALLBubble.hasMoreTokens() && tokenALLWater.hasMoreTokens()){
	    			String tokenBubble = tokenALLBubble.nextToken();
	    			String tokenWater = tokenALLWater.nextToken();
	    			if(Integer.parseInt(tokenBubble) < Integer.parseInt(tokenWater)){
	    				temp = (String)pdf_env.labelListH.get(j);
		    			pdf_env.labelListH.set(j, pdf_env.labelListH.get(j-1));
		    			pdf_env.labelListH.set(j-1, temp);
		    			break;
	    			}
	    			if(Integer.parseInt(tokenBubble) > Integer.parseInt(tokenWater))
	    				break;
	    		}
	        }
	    }
	}
	
	
	public void sortLabelListV(){
		int i, j;
		String temp;
		
		//バブルソートによる並び換え　同じだったら次のSuffixを比較
	    for(i=0; i<pdf_env.labelListV.size()-1; i++){
	    	
	    	for(j=pdf_env.labelListV.size()-1; j>i; j--){
	    		String bubble = (String)pdf_env.labelListV.get(j);
	    		String water = (String)pdf_env.labelListV.get(j-1);
	    		
    			StringTokenizer tokenALLBubble = new StringTokenizer(bubble, "-", false);
    			StringTokenizer tokenALLWater = new StringTokenizer(water, "-", false);
    			
	    		while(tokenALLBubble.hasMoreTokens() && tokenALLWater.hasMoreTokens()){
	    			String tokenBubble = tokenALLBubble.nextToken();
	    			String tokenWater = tokenALLWater.nextToken();
	    			if(Integer.parseInt(tokenBubble) < Integer.parseInt(tokenWater)){
	    				temp = (String)pdf_env.labelListV.get(j);
		    			pdf_env.labelListV.set(j, pdf_env.labelListV.get(j-1));
		    			pdf_env.labelListV.set(j-1, temp);
		    			break;
	    			}
	    			if(Integer.parseInt(tokenBubble) > Integer.parseInt(tokenWater))
	    				break;
	    		}
	        }
	    }
	}
	
	
	public void sortLabelListOH(){
		int i, j;
		String temp;
		
	    //バブルソートによる並び換え　同じだったら次のSuffixを比較
	    for(i=0; i<pdf_env.labelListOH.size()-1; i++){
	    	
	    	for(j=pdf_env.labelListOH.size()-1; j>i; j--){
	    		String bubble = (String)pdf_env.labelListOH.get(j);
	    		String water = (String)pdf_env.labelListOH.get(j-1);
	    		
    			StringTokenizer tokenALLBubble = new StringTokenizer(bubble, "-", false);
    			StringTokenizer tokenALLWater = new StringTokenizer(water, "-", false);
    			
	    		while(tokenALLBubble.hasMoreTokens() && tokenALLWater.hasMoreTokens()){
	    			String tokenBubble = tokenALLBubble.nextToken();
	    			String tokenWater = tokenALLWater.nextToken();
	    			if(Integer.parseInt(tokenBubble) < Integer.parseInt(tokenWater)){
	    				temp = (String)pdf_env.labelListOH.get(j);
		    			pdf_env.labelListOH.set(j, pdf_env.labelListOH.get(j-1));
		    			pdf_env.labelListOH.set(j-1, temp);
		    			break;
	    			}
	    			if(Integer.parseInt(tokenBubble) > Integer.parseInt(tokenWater))
	    				break;
	    		}
	        }
	    }
	}
	
	
	public void sortLabelListOV(){
		int i, j;
		String temp;
		
		//バブルソートによる並び換え　同じだったら次のSuffixを比較
	    for(i=0; i<pdf_env.labelListOV.size()-1; i++){
	    	
	    	for(j=pdf_env.labelListOV.size()-1; j>i; j--){
	    		String bubble = (String)pdf_env.labelListOV.get(j);
	    		String water = (String)pdf_env.labelListOV.get(j-1);
	    		
    			StringTokenizer tokenALLBubble = new StringTokenizer(bubble, "-", false);
    			StringTokenizer tokenALLWater = new StringTokenizer(water, "-", false);
    			
	    		while(tokenALLBubble.hasMoreTokens() && tokenALLWater.hasMoreTokens()){
	    			String tokenBubble = tokenALLBubble.nextToken();
	    			String tokenWater = tokenALLWater.nextToken();
	    			if(Integer.parseInt(tokenBubble) < Integer.parseInt(tokenWater)){
	    				temp = (String)pdf_env.labelListOV.get(j);
		    			pdf_env.labelListOV.set(j, pdf_env.labelListOV.get(j-1));
		    			pdf_env.labelListOV.set(j-1, temp);
		    			break;
	    			}
	    			if(Integer.parseInt(tokenBubble) > Integer.parseInt(tokenWater))
	    				break;
	    		}
	        }
	    }
	}
	
	
/*
	public void TFE2Flat(TFE tfe){
		int local;
		flatTFE.add(tfe);
		
		if(tfe instanceof Grouper){
			TFE2Flat( ((Grouper)tfe).tfe );
		}
		else if(tfe instanceof Connector){
			for(local=0; local<((Connector)tfe).tfeitems; local++){
				tfe = (TFE)((Connector)tfe).tfes.get(local);
				TFE2Flat(tfe);
			}
		}
	}
*/	
	
	//追加10.24　再帰的にリスト構造を開いてフラット化
	public void List2Flat(PDFValue instance){
		int local;
		flatList.add(instance);
		for(local=0; local<instance.inList.size(); local++){
			//flatList.add(instance.inList.get(local));
			List2Flat((PDFValue)instance.inList.get(local));
		}
	}
	
	
	public void adjustWidth(){
		String label;
		float tmpWidth;
		
		
//		maxWidth = new Hashtable();
//		for(i=0;i<pdf_env.labelListH.size();i++)
//			maxWidth.put(pdf_env.labelListH.get(i), "0");
		
		escape:
		
		//for(i=0; i<pdf_env.levelListH.size(); i++){		//順に走査
		for(i=pdf_env.labelListH.size()-1; i>-1; i--){		//逆から走査
			label = (String)pdf_env.labelListH.get(i);
			//for(j=0; j<flatList.size(); j++){			//処理した順に取り出す
			for(j=flatList.size()-1; j>-1; j--){		//処理と逆順に取り出す
				PDFValue check = (PDFValue)flatList.get(j);
				
				//if(check.labelH.equals("0"))
				//if(label.equals("0"))
				//System.out.println("tttttttttt "+check.data+" "+check.labelH+" "+label+" "+check.box_width);

				
				if( check.labelH.equals(label) ){//&& !check.widthFLAG ){
					
					////////////////ちょっと強引だけどここがキモ
					//リストの上下に来るC2はこれではダメ
					if(check.type.equals("Att")||check.type.equals("Func")){
//						check.widthFLAG = true;
						
						tmpWidth = Float.parseFloat((String)maxWidth.get(label));
						if( tmpWidth < check.box_width)
							maxWidth.put(label, Float.toString(check.box_width));
					}
					
					else if(check.type.equals("G1")){
						
						//再格納　中が属性ならいらないはず Funcもかも
						if(check.fold != 0 && !check.foldFLAG_H
							&& ( !((PDFValue)check.inList.get(0)).type.equals("Att") &&
								 !((PDFValue)check.inList.get(0)).type.equals("Func") ) //&& !check.widthFLAG){
						  ){
								check.foldFLAG_H = true;
								break escape;
						}
						check.foldFLAG_H = false;
//						check.widthFLAG = true;
						
						int local;
						String labelPrefix;
						String labelSuffix;
						String tmpLabel;
						//さらにクラス変数にmatchListHを定義
						
						//------------------------- 折り畳みに対応する4変数 -------------------------//
						//rowNum   :折り畳みが何回発生したかを格納し、初めは最後の行を指し、         //                       
						//          徐々にカウントダウン                                             //
						//foldWidth:どの行が最大幅かを取得し、maxWidthに格納                         //
						//matchNum :子要素に該当するものを探して当てはまった個数                     //
						//preNum   :各行の要素数が2,1だった場合、各行の要素数が格納されたrowsには    //
						//          2,3と入っているので、後ろから処理する場合3個マッチではなくて     //
						//          1個マッチにしなくてはならない　そこで1つ前の行までの要素数を引く //
						//          その１つ前までの要素数を格納                                     //
						int rowNum = check.rowNum;
						float foldWidth = 0;
						int matchNum = 0;
						int preNum = 0;
						//---------------------------------------------------------------------------//
						
						if(label.equals("0"))
							labelPrefix = Integer.toString(Integer.parseInt(label) + 1);
						else{
							labelPrefix = label.substring(0, label.indexOf("-"));
							labelSuffix = label.substring(label.indexOf("-"));
							labelPrefix = Integer.toString(Integer.parseInt(labelPrefix) + 1);
							labelPrefix = labelPrefix + labelSuffix;
						}
						
						Pattern pattern = Pattern.compile(labelPrefix+"-"+"[0-9]*");
						Matcher matcher = pattern.matcher("");
						boolean match;
						
						check.box_width = 0;
						//for(local=0; local<pdf_env.labelListH.size(); local++){	//順に走査
						for(local=pdf_env.labelListH.size()-1; local>-1; local--){	//逆に走査
							tmpLabel = (String)pdf_env.labelListH.get(local);
							matcher = pattern.matcher(tmpLabel);
							match = matcher.matches();
							if(match){
								if(!matchListH.contains(tmpLabel)){
									//System.out.println("子　"+tmpLabel);
									matchListH.add(tmpLabel);
									tmpWidth = Float.parseFloat((String)maxWidth.get(tmpLabel));
									check.box_width += tmpWidth;
									matchNum++;
								}
							}

//							int test = (Integer.parseInt((String)check.rows.get(rowNum)))-preNum;
//							System.out.println("test2 "+matchNum+" "+test);
							
							if(rowNum!=0)
								preNum = Integer.parseInt((String)check.rows.get(rowNum-1));
							if(matchNum == Integer.parseInt((String)check.rows.get(rowNum))-preNum){
								if(foldWidth < check.box_width){
									foldWidth = check.box_width;
								}
								check.box_width = 0;
								rowNum--;
								if(rowNum < 0)
									break;
								matchNum=0;////////////////////////////////////////////////////////////////////////////////////////////////
							}
							
						}

						
						//もしfoldが指定されていたらその長さをmaxHeightにする///////////////////
						//ここをコメントアウトすれば、最大幅が採用される////////////////////////
						if(check.fold != 0)
							foldWidth = check.fold;
						
						tmpWidth = Float.parseFloat((String)maxWidth.get(label));
						if(tmpWidth < foldWidth)
							maxWidth.put(label, Float.toString(foldWidth));
					}
					
					else if(check.type.equals("G2")){
//						check.widthFLAG = true;
						
						if(check.columnNum == 0){
							tmpWidth = Float.parseFloat((String)maxWidth.get(((PDFValue)check.inList.get(0)).labelH));
							check.box_width = tmpWidth;
						}
						
						else{
							int local;
							String labelPrefix;
							String labelSuffix;
							String tmpLevel;
							
							if(label.equals("0"))
								labelPrefix = Integer.toString(Integer.parseInt(label) + 1);
							else{
								labelPrefix = label.substring(0, label.indexOf("-"));
								labelSuffix = label.substring(label.indexOf("-"));
								labelPrefix = Integer.toString(Integer.parseInt(labelPrefix) + 1);
								labelPrefix = labelPrefix + labelSuffix;
							}
							
							Pattern pattern = Pattern.compile(labelPrefix+"-"+"[0-9]*");
							Matcher matcher = pattern.matcher("");
							boolean match;
							
							check.box_width = 0;
							for(local=0; local<pdf_env.labelListH.size(); local++){
								tmpLevel = (String)pdf_env.labelListH.get(local);
								matcher = pattern.matcher(tmpLevel);
								match = matcher.matches();
								if(match){
									tmpWidth = Float.parseFloat((String)maxWidth.get(tmpLevel));
									check.box_width += tmpWidth;
								}
							}
						}
						
						tmpWidth = Float.parseFloat((String)maxWidth.get(label));
						if(tmpWidth < check.box_width)
							maxWidth.put(label, Float.toString(check.box_width));
					}
					
					else if(check.type.equals("C1")){
//						check.widthFLAG = true;
						
						int local;
						PDFValue[] test = new PDFValue[check.inList.size()];
						for(local=0; local<check.inList.size(); local++){
							test[local] = (PDFValue)check.inList.get(local);
						}
						check.box_width = 0;
						for(local=0; local<check.inList.size(); local++){
							tmpWidth = Float.parseFloat((String)maxWidth.get(test[local].labelH));
							check.box_width += tmpWidth;	
						}
						tmpWidth = Float.parseFloat((String)maxWidth.get(label));
						if(tmpWidth < check.box_width)
							maxWidth.put(label, Float.toString(check.box_width));
					}
					
					else if(check.type.equals("C2")){
//						check.widthFLAG = true;
						
						int local;
						
						check.box_width = 0;
						for(local=0; local<check.inList.size(); local++){
							check.box_width = Float.parseFloat((String)maxWidth.get(((PDFValue)check.inList.get(local)).labelH));
							tmpWidth = Float.parseFloat((String)maxWidth.get(label));
							if(tmpWidth < check.box_width)
								maxWidth.put(label, Float.toString(check.box_width));
						}
						/*
						int local;
						float tmpWidth2;
						PDFValue test;
						for(local=0; local<check.inList.size(); local++){
							test = (PDFValue)check.inList.get(local);
							tmpWidth = Float.parseFloat((String)maxWidth.get(label));
							tmpWidth2 = Float.parseFloat((String)maxWidth.get(test.labelH));
							if(tmpWidth < tmpWidth2)
								maxWidth.put(label, Float.toString(tmpWidth2));
						}
						for(local=0; local<check.inList.size(); local++){
							test = (PDFValue)check.inList.get(local);
							tmpWidth = Float.parseFloat((String)maxWidth.get(label));
							tmpWidth2 = Float.parseFloat((String)maxWidth.get(test.labelH));
							if(tmpWidth > tmpWidth2)
								maxWidth.put(test.labelH, Float.toString(tmpWidth));
						}
						*/
					}
					
				}
			}
		}
	}

	
	public void adjustHeight(){
		String label;
		float tmpHeight;
		
		
//		maxHeight = new Hashtable();
//		for(i=0;i<pdf_env.labelListV.size();i++)
//			maxHeight.put(pdf_env.labelListV.get(i), "0");
		
		escape:
		
		//for(i=0; i<pdf_env.levelListV.size(); i++){		//順に走査
		for(i=pdf_env.labelListV.size()-1; i>-1; i--){		//逆から走査
			label = (String)pdf_env.labelListV.get(i);
			//for(j=0; j<flatList.size(); j++){			//処理した順に取り出す
			for(j=flatList.size()-1; j>-1; j--){		//処理と逆順に取り出す
				PDFValue check = (PDFValue)flatList.get(j);
				
				if( check.labelV.equals(label) ){//&& !check.heightFLAG ){
					
					////////////////ちょっと強引だけどここがキモ
					//リストの上下に来るC2はこれではダメ
					if(check.type.equals("Att")||check.type.equals("Func")){
//						check.heightFLAG = true;
						
						tmpHeight = Float.parseFloat((String)maxHeight.get(label));
						if( tmpHeight < check.box_height)
							maxHeight.put(label, Float.toString(check.box_height));
					}
					
					else if(check.type.equals("G1")){
//						check.heightFLAG = true;
						
						if(check.rowNum == 0){
							System.out.println(maxHeight.size()+" "+check.inList.size()+" "+check.type);//-------------
							tmpHeight = Float.parseFloat((String)maxHeight.get(((PDFValue)check.inList.get(0)).labelV));
							check.box_height = tmpHeight;
						}
						
						else{
							int local;
							String labelPrefix;
							String labelSuffix;
							String tmpLabel;
							
							if(label.equals("0"))
								labelPrefix = Integer.toString(Integer.parseInt(label) + 1);
							else{
								labelPrefix = label.substring(0, label.indexOf("-"));
								labelSuffix = label.substring(label.indexOf("-"));
								labelPrefix = Integer.toString(Integer.parseInt(labelPrefix) + 1);	
								labelPrefix = labelPrefix + labelSuffix;
							}
							
							Pattern pattern = Pattern.compile(labelPrefix+"-"+"[0-9]*");
							Matcher matcher = pattern.matcher("");
							boolean match;
							
							check.box_height = 0;
							for(local=0; local<pdf_env.labelListV.size(); local++){
								tmpLabel = (String)pdf_env.labelListV.get(local);
								matcher = pattern.matcher(tmpLabel);
								match = matcher.matches();
								if(match){
									tmpHeight = Float.parseFloat((String)maxHeight.get(tmpLabel));
									check.box_height += tmpHeight;
								}
							}
						}
						
						tmpHeight = Float.parseFloat((String)maxHeight.get(label));
						if(tmpHeight < check.box_height)
							maxHeight.put(label, Float.toString(check.box_height));
					}
					
					else if(check.type.equals("G2")){
						
						System.out.println(maxHeight.size()+" "+check.inList.size()+" "+check.type);//---------
						
						//再格納　中が属性ならいらないはず Funcもかも
						if(check.fold != 0  && !check.foldFLAG_V
							&& ( !((PDFValue)check.inList.get(0)).type.equals("Att") &&
								 !((PDFValue)check.inList.get(0)).type.equals("Func") ) //&& !check.heightFLAG
						  ){
								check.foldFLAG_V = true;
								break escape;
						}
						
						check.foldFLAG_V = false;
//						check.heightFLAG = true;
						
						int local;
						String labelPrefix;
						String labelSuffix;
						String tmpLabel;
						//さらにクラス変数にmatchListVを定義
						
						//------------------------- 折り畳みに対応する4変数 --------------------------//
						//rowNum    :折り畳みが何回発生したかを格納し、初めは最後の列を指し、         //                       
						//           徐々にカウントダウン                                             //
						//foldHeight:どの列が最大幅かを取得し、maxHeightに格納                        //
						//matchNum  :子要素に該当するものを探して当てはまった個数                     //
						//preNum    :各列の要素数が2,1だった場合、各列の要素数が格納されたcolumnsには //
						//           2,3と入っているので、後ろから処理する場合3個マッチではなくて     //
						//           1個マッチにしなくてはならない　そこで1つ前の列までの要素数を引く //
						//           その１つ前までの要素数を格納                                     //
						int columnNum = check.columnNum;			
						float foldHeight = 0;
						int matchNum = 0;
						int preNum = 0;
						//----------------------------------------------------------------------------//
						
						if(label.equals("0"))
							labelPrefix = Integer.toString(Integer.parseInt(label) + 1);
						else{
							labelPrefix = label.substring(0, label.indexOf("-"));
							labelSuffix = label.substring(label.indexOf("-"));
							labelPrefix = Integer.toString(Integer.parseInt(labelPrefix) + 1);
							labelPrefix = labelPrefix + labelSuffix;
						}
						
						Pattern pattern = Pattern.compile(labelPrefix+"-"+"[0-9]*");
						Matcher matcher = pattern.matcher("");
						boolean match;
						
						check.box_height = 0;
						//for(local=0; local<pdf_env.labelListV.size(); local++){	//順に走査
						for(local=pdf_env.labelListV.size()-1; local>-1; local--){	//逆に走査
							tmpLabel = (String)pdf_env.labelListV.get(local);
							matcher = pattern.matcher(tmpLabel);
							match = matcher.matches();
							if(match){
								if(!matchListV.contains(tmpLabel)){
									matchListV.add(tmpLabel);
									tmpHeight = Float.parseFloat((String)maxHeight.get(tmpLabel));
									check.box_height += tmpHeight;
									matchNum++;
								}
							}
							if(columnNum!=0)
								preNum = Integer.parseInt((String)check.columns.get(columnNum-1));
							if(matchNum == Integer.parseInt((String)check.columns.get(columnNum))-preNum){
								if(foldHeight < check.box_height)
									foldHeight = check.box_height;
								check.box_height = 0;
								columnNum--;
								if(columnNum < 0)
									break;
							}
							
						}
							
						//もしfoldが指定されていたらその長さをmaxHeightにする//////////////////////
						//ここをコメントアウトすれば、最大高さが採用される/////////////////////////
						if(check.fold != 0)
							foldHeight = check.fold;
							
						tmpHeight = Float.parseFloat((String)maxHeight.get(label));
						if(tmpHeight < foldHeight)
							maxHeight.put(label, Float.toString(foldHeight));
					}
					
					else if(check.type.equals("C1")){
//						check.heightFLAG = true;
						
						int local;
						
						check.box_height = 0;
						for(local=0; local<check.inList.size(); local++){
							System.out.println(maxHeight.size()+" "+check.inList.size()+" "+check.type);//-------------
							check.box_height = Float.parseFloat((String)maxHeight.get(((PDFValue)check.inList.get(local)).labelV));
							tmpHeight = Float.parseFloat((String)maxHeight.get(label));
							if(tmpHeight < check.box_height)
								maxHeight.put(label, Float.toString(check.box_height));
						}
						/*
						 int local;
						 float tmpHeight2;
						 PDFValue test;
						 for(local=0; local<check.inList.size(); local++){
						 	test = (PDFValue)check.inList.get(local);
						 	tmpHeight = Float.parseFloat((String)maxHeight.get(label));
						 	tmpHeight2 = Float.parseFloat((String)maxHeight.get(test.labelV));
						 	if(tmpHeight < tmpHeight2)
						 		maxHeight.put(label, Float.toString(tmpHeight2));
						 }
						 for(local=0; local<check.inList.size(); local++){
						 	test = (PDFValue)check.inList.get(local);
						 	tmpHeight = Float.parseFloat((String)maxHeight.get(label));
						 	tmpHeight2 = Float.parseFloat((String)maxHeight.get(test.labelV));
						 	if(tmpHeight > tmpHeight2)
						 		maxHeight.put(test.labelV, Float.toString(tmpHeight));
						 }
						*/
					}
						
					else if(check.type.equals("C2")){
//						check.heightFLAG = true;
						
						int local;
						PDFValue[] test = new PDFValue[check.inList.size()];
						for(local=0; local<check.inList.size(); local++){
							test[local] = (PDFValue)check.inList.get(local);
						}
						check.box_height = 0;
						for(local=0; local<check.inList.size(); local++){
							tmpHeight = Float.parseFloat((String)maxHeight.get(test[local].labelV));
							check.box_height += tmpHeight;	
						}
						tmpHeight = Float.parseFloat((String)maxHeight.get(label));
						if(tmpHeight < check.box_width)
							maxHeight.put(label, Float.toString(check.box_height));
					}
					
				}
			}
		}
	}
	
	
	public void adjustOriginalWidth(){
		String label;
		float tmpWidth;

		
		for(i=pdf_env.labelListOH.size()-1; i>-1; i--){		//逆から走査
			label = (String)pdf_env.labelListOH.get(i);
			
			for(j=flatList.size()-1; j>-1; j--){		//処理と逆順に取り出す
				PDFValue check = (PDFValue)flatList.get(j);

				if( check.labelOH.equals(label) ){
					
					if(check.type.equals("Att")||check.type.equals("Func")){
						
						tmpWidth = Float.parseFloat((String)maxOriginalWidth.get(label));
						if( tmpWidth < check.originalWidth)
							maxOriginalWidth.put(label, Float.toString(check.originalWidth));
						
					}
					
					else if(check.type.equals("G1")){
						
						int local;
						String labelPrefix;
						String labelSuffix;
						String tmpLabel;

						int rowNum = check.rowNum;
						float foldWidth = 0;
						int matchNum = 0;
						int preNum = 0;
						
						if(label.equals("0"))
							labelPrefix = Integer.toString(Integer.parseInt(label) + 1);
						else{
							labelPrefix = label.substring(0, label.indexOf("-"));
							labelSuffix = label.substring(label.indexOf("-"));
							labelPrefix = Integer.toString(Integer.parseInt(labelPrefix) + 1);
							labelPrefix = labelPrefix + labelSuffix;
						}
						
						Pattern pattern = Pattern.compile(labelPrefix+"-"+"[0-9]*");
						Matcher matcher = pattern.matcher("");
						boolean match;
						
						check.originalWidth = 0;
						for(local=pdf_env.labelListOH.size()-1; local>-1; local--){	//逆に走査
							tmpLabel = (String)pdf_env.labelListOH.get(local);
							matcher = pattern.matcher(tmpLabel);
							match = matcher.matches();
							if(match){
								if(!matchListH.contains(tmpLabel)){
									matchListH.add(tmpLabel);
									tmpWidth = Float.parseFloat((String)maxOriginalWidth.get(tmpLabel));
									check.originalWidth += tmpWidth;
									matchNum++;
								}
							}

							
							if(rowNum!=0)
								preNum = Integer.parseInt((String)check.rows.get(rowNum-1));
							if(matchNum == Integer.parseInt((String)check.rows.get(rowNum))-preNum){
								if(foldWidth < check.originalWidth){
									foldWidth = check.originalWidth;
								}
								check.originalWidth = 0;
								rowNum--;
								if(rowNum < 0)
									break;
								matchNum=0;
							}
							
						}

						
						//もしfoldが指定されていたらその長さをmaxHeightにする///////////////////
						//ここをコメントアウトすれば、最大幅が採用される////////////////////////
						if(check.fold != 0)
							foldWidth = check.fold;
						
						tmpWidth = Float.parseFloat((String)maxOriginalWidth.get(label));
						if(tmpWidth < foldWidth)
							maxOriginalWidth.put(label, Float.toString(foldWidth));
					}
					
					else if(check.type.equals("G2")){
						
						if(check.columnNum == 0){
							tmpWidth = Float.parseFloat((String)maxOriginalWidth.get(((PDFValue)check.inList.get(0)).labelOH));
							check.originalWidth = tmpWidth;
						}
						
						else{
							int local;
							String labelPrefix;
							String labelSuffix;
							String tmpLevel;
							
							if(label.equals("0"))
								labelPrefix = Integer.toString(Integer.parseInt(label) + 1);
							else{
								labelPrefix = label.substring(0, label.indexOf("-"));
								labelSuffix = label.substring(label.indexOf("-"));
								labelPrefix = Integer.toString(Integer.parseInt(labelPrefix) + 1);
								labelPrefix = labelPrefix + labelSuffix;
							}
							
							Pattern pattern = Pattern.compile(labelPrefix+"-"+"[0-9]*");
							Matcher matcher = pattern.matcher("");
							boolean match;
							
							check.originalWidth = 0;
							for(local=0; local<pdf_env.labelListOH.size(); local++){
								tmpLevel = (String)pdf_env.labelListOH.get(local);
								matcher = pattern.matcher(tmpLevel);
								match = matcher.matches();
								if(match){
									tmpWidth = Float.parseFloat((String)maxOriginalWidth.get(tmpLevel));
									check.originalWidth += tmpWidth;
								}
							}
						}
						
						tmpWidth = Float.parseFloat((String)maxOriginalWidth.get(label));
						if(tmpWidth < check.originalWidth)
							maxOriginalWidth.put(label, Float.toString(check.originalWidth));
					}
					
					else if(check.type.equals("C1")){
						
						int local;
						PDFValue[] test = new PDFValue[check.inList.size()];
						for(local=0; local<check.inList.size(); local++){
							test[local] = (PDFValue)check.inList.get(local);
						}
						check.originalWidth = 0;
						for(local=0; local<check.inList.size(); local++){
							tmpWidth = Float.parseFloat((String)maxOriginalWidth.get(test[local].labelOH));
							check.originalWidth += tmpWidth;	
						}
						tmpWidth = Float.parseFloat((String)maxOriginalWidth.get(label));
						if(tmpWidth < check.originalWidth)
							maxOriginalWidth.put(label, Float.toString(check.originalWidth));
					}
					
					else if(check.type.equals("C2")){
						
						int local;
						
						check.originalWidth = 0;
						for(local=0; local<check.inList.size(); local++){
							check.originalWidth = Float.parseFloat((String)maxOriginalWidth.get(((PDFValue)check.inList.get(local)).labelOH));
							tmpWidth = Float.parseFloat((String)maxOriginalWidth.get(label));
							if(tmpWidth < check.originalWidth)
								maxOriginalWidth.put(label, Float.toString(check.originalWidth));
						}
						
					}
					
				}
			}
		}
	}
	
	
	public void adjustOriginalHeight(){
		String label;
		float tmpHeight;
		
		
		for(i=pdf_env.labelListOV.size()-1; i>-1; i--){		//逆から走査
			label = (String)pdf_env.labelListOV.get(i);
			
			for(j=flatList.size()-1; j>-1; j--){		//処理と逆順に取り出す
				PDFValue check = (PDFValue)flatList.get(j);
				
				if( check.labelOV.equals(label) ){
					
					if(check.type.equals("Att")||check.type.equals("Func")){
						
						tmpHeight = Float.parseFloat((String)maxOriginalHeight.get(label));
						if( tmpHeight < check.originalHeight)
							maxOriginalHeight.put(label, Float.toString(check.originalHeight));
						
					}
					
					else if(check.type.equals("G1")){
						
						if(check.rowNum == 0){
							tmpHeight = Float.parseFloat((String)maxOriginalHeight.get(((PDFValue)check.inList.get(0)).labelOV));
							check.originalHeight = tmpHeight;
						}
						
						else{
							int local;
							String labelPrefix;
							String labelSuffix;
							String tmpLabel;
							
							if(label.equals("0"))
								labelPrefix = Integer.toString(Integer.parseInt(label) + 1);
							else{
								labelPrefix = label.substring(0, label.indexOf("-"));
								labelSuffix = label.substring(label.indexOf("-"));
								labelPrefix = Integer.toString(Integer.parseInt(labelPrefix) + 1);	
								labelPrefix = labelPrefix + labelSuffix;
							}
							
							Pattern pattern = Pattern.compile(labelPrefix+"-"+"[0-9]*");
							Matcher matcher = pattern.matcher("");
							boolean match;
							
							check.originalHeight = 0;
							for(local=0; local<pdf_env.labelListOV.size(); local++){
								tmpLabel = (String)pdf_env.labelListOV.get(local);
								matcher = pattern.matcher(tmpLabel);
								match = matcher.matches();
								if(match){
									tmpHeight = Float.parseFloat((String)maxOriginalHeight.get(tmpLabel));
									check.originalHeight += tmpHeight;
								}
							}
						}
						
						tmpHeight = Float.parseFloat((String)maxOriginalHeight.get(label));
						if(tmpHeight < check.originalHeight)
							maxOriginalHeight.put(label, Float.toString(check.originalHeight));
					}
					
					else if(check.type.equals("G2")){

						int local;
						String labelPrefix;
						String labelSuffix;
						String tmpLabel;

						int columnNum = check.columnNum;			
						float foldHeight = 0;
						int matchNum = 0;
						int preNum = 0;
						
						if(label.equals("0"))
							labelPrefix = Integer.toString(Integer.parseInt(label) + 1);
						else{
							labelPrefix = label.substring(0, label.indexOf("-"));
							labelSuffix = label.substring(label.indexOf("-"));
							labelPrefix = Integer.toString(Integer.parseInt(labelPrefix) + 1);
							labelPrefix = labelPrefix + labelSuffix;
						}
						
						Pattern pattern = Pattern.compile(labelPrefix+"-"+"[0-9]*");
						Matcher matcher = pattern.matcher("");
						boolean match;
						
						check.originalHeight = 0;
						for(local=pdf_env.labelListOV.size()-1; local>-1; local--){	//逆に走査
							tmpLabel = (String)pdf_env.labelListOV.get(local);
							matcher = pattern.matcher(tmpLabel);
							match = matcher.matches();
							if(match){
								if(!matchListV.contains(tmpLabel)){
									matchListV.add(tmpLabel);
									tmpHeight = Float.parseFloat((String)maxOriginalHeight.get(tmpLabel));
									check.originalHeight += tmpHeight;
									matchNum++;
								}
							}
							if(columnNum!=0)
								preNum = Integer.parseInt((String)check.columns.get(columnNum-1));
							if(matchNum == Integer.parseInt((String)check.columns.get(columnNum))-preNum){
								if(foldHeight < check.originalHeight)
									foldHeight = check.originalHeight;
								check.originalHeight = 0;
								columnNum--;
								if(columnNum < 0)
									break;
							}
							
						}
							
						//もしfoldが指定されていたらその長さをmaxHeightにする//////////////////////
						//ここをコメントアウトすれば、最大高さが採用される/////////////////////////
						if(check.fold != 0)
							foldHeight = check.fold;
							
						tmpHeight = Float.parseFloat((String)maxOriginalHeight.get(label));
						if(tmpHeight < foldHeight)
							maxHeight.put(label, Float.toString(foldHeight));
					}
					
					else if(check.type.equals("C1")){

						int local;
						
						check.originalHeight = 0;
						for(local=0; local<check.inList.size(); local++){
							check.originalHeight = Float.parseFloat((String)maxOriginalHeight.get(((PDFValue)check.inList.get(local)).labelOV));
							tmpHeight = Float.parseFloat((String)maxOriginalHeight.get(label));
							if(tmpHeight < check.originalHeight)
								maxHeight.put(label, Float.toString(check.originalHeight));
						}

					}
						
					else if(check.type.equals("C2")){

						int local;
						PDFValue[] test = new PDFValue[check.inList.size()];
						for(local=0; local<check.inList.size(); local++){
							test[local] = (PDFValue)check.inList.get(local);
						}
						check.originalHeight = 0;
						for(local=0; local<check.inList.size(); local++){
							tmpHeight = Float.parseFloat((String)maxOriginalHeight.get(test[local].labelOV));
							check.originalHeight += tmpHeight;	
						}
						tmpHeight = Float.parseFloat((String)maxOriginalHeight.get(label));
						if(tmpHeight < check.originalHeight)
							maxHeight.put(label, Float.toString(check.originalHeight));
					}
					
				}
			}
		}
	}
	

	public void set_maxWidth(PDFValue result){
		PDFValue instance;
		int local;
		
		//if(result.widthFLAG)
		result.box_width = Float.parseFloat((String)maxWidth.get(result.labelH));
		
		for(local=0; local<result.inList.size(); local++){

			instance = (PDFValue)result.inList.get(local);
			set_maxWidth(instance);
			
		}
		
	}

	
	//追加11.10
	public void set_maxHeight(PDFValue result){
		PDFValue instance;
		int local;
		
		//if(result.heightFLAG)
		result.box_height = Float.parseFloat((String)maxHeight.get(result.labelV));
		
		for(local=0; local<result.inList.size(); local++){

			instance = (PDFValue)result.inList.get(local);
			set_maxHeight(instance);
			
		}
		
	}
	
	
	public void set_maxOriginalWidth(PDFValue result){
		PDFValue instance;
		int local;
		
		result.originalWidth = Float.parseFloat((String)maxOriginalWidth.get(result.labelOH));
		
		for(local=0; local<result.inList.size(); local++){

			instance = (PDFValue)result.inList.get(local);
			set_maxOriginalWidth(instance);
			
		}
		
	}
	
	
	public void set_maxOriginalHeight(PDFValue result){
		PDFValue instance;
		int local;
		
		result.originalHeight = Float.parseFloat((String)maxOriginalHeight.get(result.labelOV));
		
		for(local=0; local<result.inList.size(); local++){

			instance = (PDFValue)result.inList.get(local);
			set_maxOriginalHeight(instance);
			
		}
		
	}
	
	
/*	
	public boolean restoreFOLD(){
		int i;
		boolean restore = false;
		
		for(i=flatList.size()-1; i>-1; i--){
			PDFValue check = (PDFValue)flatList.get(i);
			if(check.foldFLAG_H){
				//restoreFold_H(check);
				((PDFTFE)tfe_info).restoreFOLD(check);
				restore = true;
			}
			else if(check.foldFLAG_V){
				//restoreFold_V(check);
				((PDFTFE)tfe_info).restoreFOLD(check);
				restore = true;
			}
		}
		
		return restore;
	}
*/

	
	public void restoreFOLD(String which){
		int i;

		if(which.equals("H")){
			for(i=flatList.size()-1; i>-1; i--){
				PDFValue check = (PDFValue)flatList.get(i);
				if(check.foldFLAG_H){
					((PDFTFE)tfe_info).restoreFOLD(check);
				}
				else if(check.foldFLAG_V){
					check.foldFLAG_V = false;
				}
			}
		}
		if(which.equals("V")){
			for(i=flatList.size()-1; i>-1; i--){
				PDFValue check = (PDFValue)flatList.get(i);
				if(check.foldFLAG_H){
					check.foldFLAG_H = false;
				}
				else if(check.foldFLAG_V){
					((PDFTFE)tfe_info).restoreFOLD(check);
				}
			}
		}
	}
	
	
	public void set_tfeInfo(ITFE tfe_info){
		this.tfe_info = tfe_info;
	}
	
	
	public void setResultList(ExtList AllPage){
		this.resultList = AllPage;
	}
	
	
	public ExtList getOutputResult(){
		return resultList;
	}
	
	
	public void debug(String info){
		if(info.equals("Before Sort Label")){
			System.out.println();
			System.out.println("=== Before Sort LabelH ===");
			for(i=0; i<pdf_env.labelListH.size(); i++)
				System.out.println(pdf_env.labelListH.get(i));
			System.out.println("=== Before Sort LabelV ===");
			for(i=0; i<pdf_env.labelListV.size(); i++)
				System.out.println(pdf_env.labelListV.get(i));	
		}
		else if(info.equals("After Sort Label")){
			System.out.println();
			System.out.println("=== After Sort LabelH ===");
		    for(i=0; i<pdf_env.labelListH.size(); i++)
		    	System.out.println(pdf_env.labelListH.get(i));
			System.out.println("=== After Sort LabelH ===");
		    for(i=0; i<pdf_env.labelListV.size(); i++)
				System.out.println(pdf_env.labelListV.get(i));
		}
	}
	
}