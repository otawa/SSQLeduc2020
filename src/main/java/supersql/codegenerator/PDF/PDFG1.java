package supersql.codegenerator.PDF;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;
import supersql.extendclass.ExtList;

public class PDFG1 extends Grouper implements PDFTFE {

	private PDFManager manager;

	private PDFEnv pdf_env;
	
	//�ɲ�10.17
	private PDFValue value;
	
	private ExtList rows;		//�ƥ��󥹥��󥹤γ�������ǿ�  �����饹�ѿ��Ǥ�̵��
	
	//��ư10.28  ������work�᥽�å���
	private float tmp_width;
	private float tmp_height;
	private float box_widthMAX;		//�ޤ���ߤ��ѿ������κ�����
	private float box_heightSUM;	//�ޤ���ߤ��ѿ����⤵�ι��
	private float fold;				//�ޤ���ߤ��ѿ�����cm�����Ĥ��ޤ���फ���Ǽ			
	private int topID;				//width��re_set�����inList�β����ܤ��餫

//	boolean fold_or_not = false;
	
	ExtList maxHeights;			//����G1�γ������⤵
	
	int rowNum;					//�ƥ��󥹥��󥹤��ޤ��������
	ExtList rowHeights;			//�ƥ��󥹥��󥹤γ������⤵

//	float rowMaxHeight;			//�᥽�å���˰�ư

	
	//----- setLevel�᥽�åɤ���Ĵ���Suffix�� -----//
	int repeatNum = 0;
	
	
	//�쥤�������Ѵ���
	TFE newLE;
	boolean change = false;
	
	
	//���󥹥ȥ饯��
	public PDFG1(Manager manager, PDFEnv pdf_env) {
		this.manager = (PDFManager) manager;
		this.pdf_env = pdf_env;
		//�ɲ�10.30  �ޤ������
		maxHeights = new ExtList();
	}

	@Override
	public String work(ExtList data_info) {
		//�ɲ�10.14 �᥽�å���˰�ư10.24
		float box_width = 0;
		float box_height = 0;
//		int level = pdf_env.level;
		
//		int fold_num;

		box_widthMAX = 0;
		box_heightSUM = 0;

		fold = 0;
		
		rowNum = 0;
		
		
		
		System.out.println("");
		System.out.println("------- G1 -------");
		System.out.println("[PDFG1:work]tfe_info = " + makele0());
		System.out.println("[PDFG1:work]data_info = " + data_info);

		System.out.println("++++ G1��value��new���ޤ�");
		//�ɲ�10.17
		value = new PDFValue("G1");
		
		rows = new ExtList();
		rowHeights = new ExtList();
//		rowHeights.add("0");

		int i;

		setDataList(data_info);
		setDecoration();

		if (tfe instanceof Attribute) {
			System.out.println("[PDFG1:work]tfe is Attribute");
			System.out.println("number of data = " + data_info.size());


			for (i = 0; i < data_info.size(); i++) {
				//�ɲ�10.27�����������Τߤ˿����ʤ�ʤ���¤�η����Ƚ�̤���ݥ����
//				pdf_env.level_max = level;

				
				//�ɲ�10.24 �ɲ�10.28�ޤ�����б��������Ʊ���������꿶�����
//				pdf_env.level++;
////				pdf_env.level += rowNum;
//				if(pdf_env.level > pdf_env.level_max ){
//					pdf_env.level_max = pdf_env.level;
//				}

				worknextItem();
				
				//�ɲ�10.24 �����Ʊ���������꿶��������ѹ�10.27
				//pdf_env.level--;
//				pdf_env.level = level;
				
				
				//�ɲ�10.28���ޤ����Ƚ��
				if(fold != 0){
					if(Fold_or_Not(box_width, box_height, fold)){
						box_width = 0;
						box_height = 0;
					}
				}
				
				
				//�ɲ�10.17
				//������tmp_width,height��­������Ǥ���
				box_width += pdf_env.tmp_width;
				tmp_height = pdf_env.tmp_height;
				if (box_height < tmp_height)
					box_height = tmp_height;
				
				
				//�ɲ�10.30
				//checkMaxHeight(pdf_env.tmp_width);
				checkMaxHeight(box_height);
				
				
				System.out.println("++++ G1��Att��set���ޤ�");
				//�ɲ�10.17 value�Υ��å�
				value.inList.add(((PDFTFE)tfe).getInstance());
				
				//��ư

			}

			
		}

		//////////////////////////////////////////
		//////////////////(G2 (C1 1 2))�Ȥ��ΤȤ�
		//////////////////////////////////////////
		else {
			System.out.println("[PDFG1:work]tfe is Operator");


			//table���ͤ���������������򤹤�
			for (i = 0; i < data_info.size(); i++) {
				//�ɲ�10.27�����������Τߤ˿����ʤ�ʤ���¤�η����Ƚ�̤���ݥ����
//				pdf_env.level_max = level;


				//�ɲ�10.24 �ɲ�10.28�ޤ�����б��� �����Ʊ���������꿶�����
//				pdf_env.level++;
////				pdf_env.level += rowNum;
//				if(pdf_env.level > pdf_env.level_max ){
//					pdf_env.level_max = pdf_env.level;
//				}
				
				worknextItem();
				
				//�ɲ�10.24 �����Ʊ���������꿶��������ѹ�10.27
				//pdf_env.level--;
//				pdf_env.level = level;

				
				
				//�ɲ�10.28���ޤ����Ƚ��
				if(fold != 0){
					if(Fold_or_Not(box_width, box_height, fold)){
						box_width = 0;
						box_height = 0;
					}
				}
				
				
				//�ɲ�10.11,14
				//������tmp_width,height��­������Ǥ���
				//G1�ξ�硡height��width�������ؤ���
				box_width += pdf_env.tmp_width;
				tmp_height = pdf_env.tmp_height;
				if (box_height < tmp_height)
					box_height = tmp_height;

				
				//�ɲ�10.30
				//checkMaxHeight(pdf_env.tmp_height);
				checkMaxHeight(box_height);
				
				
				System.out.println("++++ G1�ǥꥹ�Ȥ�set���ޤ�");
				//�ɲ�10.17 value�Υ��å�
				value.inList.add(((PDFTFE)tfe).getInstance());
				
				
				//��ư

			}

			
		}

		///////////////////////////////

		//�ɲ�10.24  �ѹ�10.28  �᥽�åɲ������ޤ���ߤȶ�ͭ
		//for(i=0; i<value.inList.size(); i++){
		//	PDFValue re_set = (PDFValue)value.inList.get(i);
		//	re_set.box_height = box_height;
		//}
		re_setHeight(topID, value.inList.size(), box_height);

		
		//�ɲ�10.28  �ޤ���߻���box_width,height��Ĵ��
		if(fold != 0){
			if(box_width < box_widthMAX){
				box_width = box_widthMAX;
			}
			box_height += box_heightSUM;//���Τ��Υ��󥹥��󥹤ι⤵�����Ǥ�������
		}

		
		//�����餯���Τ褦�ˤ����ɲä�������Ǥ���
//		if(columns.size() == 0)
//			columns.add(Integer.toString(value.inList.size()));
		//�ɲ�11.2  ���
		rows.add(Integer.toString(value.inList.size()));

		
		//---------- ���Υ��󥹥��󥹤ι⤵���� ------------//
		float rowHeight = 0;
		
		for(i=0; i<rowHeights.size(); i++)
			rowHeight += Float.parseFloat((String)rowHeights.get(i));
		box_height = rowHeight;
		//------------------------------------------------//
		
		//�ɲ�10.17
		value.box_width = box_width;
		value.box_height = box_height;
		
		value.originalWidth = box_width;		//C2�Υ쥤�������Ѵ��˻���
		value.originalHeight = box_height;	//C1�Υ쥤�������Ѵ��˻���
		
		
		value.rowNum = rowNum;
		value.rows = rows;
		value.rowHeights = rowHeights;
		
		value.fold = fold;
		value.tfeID = getId();
		
		pdf_env.tmp_width = box_width;
		pdf_env.tmp_height = box_height;

		
		
		//-------- ����G1�γ������⤵���˴ƻ� ----------//
		float maxHeight;
		
		if( maxHeights.size() < rows.size() ){
			int diff = rows.size() - maxHeights.size();
			for(i=0; i<diff; i++)
				maxHeights.add("0");
		}
		for(i=0; i<rowHeights.size(); i++){
			maxHeight = Float.parseFloat((String)maxHeights.get(i));
			rowHeight = Float.parseFloat((String)rowHeights.get(i));
			if(maxHeight < rowHeight)
				maxHeights.set(i, Float.toString(rowHeight));
		}
		//------------------------------------------------//
		
		
		System.out.println("�ޤ����������"+rowNum);
		int local;
		for(local=0;local<rows.size();local++){
			System.out.println("dore"+rows.get(local));
		}
		return null;
	}
	
	
	
	
	//�ɲ�10.28���ޤ����Ƚ��
	public boolean Fold_or_Not(float box_width, float box_height, float fold){
		boolean fold_or_not = false;
//		fold_or_not = false;
		tmp_width = box_width;
		tmp_width += pdf_env.tmp_width;
		if(tmp_width>fold){
			Fold(box_width, box_height);
			rows.add(Integer.toString(value.inList.size()));
			System.out.println("eeeeeeeeeeeeeee"+value.inList.size());
			//1���������ǡ��ޤ��֤�ľ�������Ǥ˾��������������
			rowNum++;																//���줬�ᤤ
			fold_or_not = true;
		}
		return fold_or_not;
	}
	
	//�ɲ�10.28  �ޤ����
	public void Fold(float box_width, float box_height){	
		tmp_width = box_width;
		if(tmp_width > box_widthMAX){
			box_widthMAX = tmp_width;
		}
		box_heightSUM += box_height;//¾���羮��Ӥ�ɬ�ס�checkMaxHeight()�Ǥ����Τ���
		//���λ�inList��set����Ƥ���value�ˤ�box_height��re-set
		re_setHeight(topID, value.inList.size(), box_height);
		topID = value.inList.size();
	}
	
	//�ɲ�10.30
	public void checkMaxHeight(float box_height){
		float rowTmpHeight;
		
		if(rowNum >= rowHeights.size())
			rowHeights.add("0");
		rowTmpHeight = Float.parseFloat((String)rowHeights.get(rowNum));
		if(rowTmpHeight < box_height)
			rowHeights.set(rowNum, Float.toString(box_height));
		
//		if(fold_or_not)
//			rowNum++;
	}
	
	public void re_setHeight(int start, int end, float set_height){
		int i;
		for(i=start; i<end; i++){
			PDFValue re_set = (PDFValue)value.inList.get(i);
			re_set.box_height = set_height;
		}
	}
	
	public void setDecoration(){
		//�ޤ����
		if(decos.containsKey("fold")){
			fold = Float.parseFloat(decos.get("fold").toString());
		}
	}
	
	public PDFValue getInstance(){
		System.out.println("++++ G1��set���ޤ���");
		return value;
	}

	
	public void setLabel(PDFValue result) {

		int i;
		PDFValue instance;
		
		//---- columnNum��repeatNum�Ȥ��ƥ��饹�ѿ����ѹ� ----//
		int rowNum = 0;					//�ޤ�����б�����٥��������ֲ����ܡ�
		//int columnNum = 0;			//ȿ�����Ϥ����������٥��������ֲ����ܡ�
		
		int labelH = pdf_env.labelH;					//��ʬ������٥�����������
		int labelV = pdf_env.labelV;					//��ʬ�ι⤵��٥�����������
		String labelSuffixH = pdf_env.labelSuffixH;		//��ʬ������٥�Suffix�����
		String labelSuffixV = pdf_env.labelSuffixV;		//��ʬ�ι⤵��٥�Suffix�����
		
		
		//----------------------------------------------
		int labelO = pdf_env.labelO;
		
		
		//--------- label��Suffix���碌�ơ���ʬ�Υ�٥������ -----------//
		if( pdf_env.labelSuffixH.equals("null") )
			result.labelH = Integer.toString(labelH);
		else
			result.labelH = Integer.toString(labelH) + labelSuffixH;
		
		if( pdf_env.labelSuffixV.equals("null") )
			result.labelV = Integer.toString(labelV);
		else
			result.labelV = Integer.toString(labelV) + labelSuffixV;
		//-----------------------------------------------------------------//
		if( pdf_env.labelSuffixH.equals("null") )
			result.labelOH = Integer.toString(labelO);
		else
			result.labelOH = Integer.toString(labelO) + labelSuffixH;
		
		if( pdf_env.labelSuffixV.equals("null") )
			result.labelOV = Integer.toString(labelO);
		else
			result.labelOV = Integer.toString(labelO) + labelSuffixV;
		
		
		//--------- �ƥ�٥뤬���Ƥ��ͤʤ�Х�٥�ꥹ�Ȥ��ɲá�---------//
		if( !pdf_env.labelListH.contains(result.labelH) )
			pdf_env.labelListH.add(result.labelH);
		if( !pdf_env.labelListV.contains(result.labelV) )
			pdf_env.labelListV.add(result.labelV);
		//-----------------------------------------------------------------//
		if( !pdf_env.labelListOH.contains(result.labelOH) )
			pdf_env.labelListOH.add(result.labelOH);
		if( !pdf_env.labelListOV.contains(result.labelOV) )
			pdf_env.labelListOV.add(result.labelOV);
		
		
		//��C1ľ����G1ľ����C1�ǹ⤵��·���褦�Ȥ���ˤϡ�
		//    �����ǿƤ�C1���ä���max�򥫥���ȥ��åפ��ʤ���Ф����Ϥ�
		//    ������������ˤϿƤ��ʤ�����狼��ɬ�פ�����
		//    �����·���褦�Ȥ��Ƥ�ºݤ��ޤ��ü�˥��쥤�ˤϤʤ�ʤ��Ϥ�
		
		
		int maxHtmp;				//�����Ǥ��Ϥ�max�����򤷤Ƥ����ѿ�
		int maxVtmp;				//�����Ǥ��Ϥ�max�����򤷤Ƥ����ѿ�
		
		pdf_env.labelmaxH++;
		maxHtmp = pdf_env.labelmaxH;			//max������
		pdf_env.labelmaxV++;
		maxVtmp = pdf_env.labelmaxV;			//max������
		
		
		int labelHtmp;				//�����Ǥ��Ϥ�labelH�����򤷤Ƥ����ѿ�
		int labelVtmp;				//�����Ǥ��Ϥ�labelV�����򤷤Ƥ����ѿ�
		
		pdf_env.labelH = pdf_env.labelmaxH;
		labelHtmp = pdf_env.labelH;				//labelH������		
		pdf_env.labelV = pdf_env.labelmaxV;
		labelVtmp = pdf_env.labelV;				//labelV������
		
		//----------------------------------
		int maxOtmp;
		pdf_env.labelmaxO++;
		maxOtmp = pdf_env.labelmaxO;
		int labelOtmp;
		pdf_env.labelO = pdf_env.labelmaxO;
		labelOtmp = pdf_env.labelO;
		
		
		for (i = 0; i < result.inList.size(); i++) {		
			
			pdf_env.labelH = labelHtmp;
			pdf_env.labelmaxH = maxHtmp;
			
			pdf_env.labelV = labelVtmp;
			pdf_env.labelmaxV = maxVtmp;

			
			//-----------------------------------
			pdf_env.labelO = labelOtmp;
			pdf_env.labelmaxO = maxOtmp;
			
			
			
			//------------- ���󥹥������SuffixH�ο��������䤹 ------------//
			repeatNum++;
			//columnNum = i;		//¿ʬ���ä���OK
			String columnStr = Integer.toString(repeatNum);
			if(labelSuffixH.equals("null"))
				pdf_env.labelSuffixH = "-" + columnStr;
			else
				pdf_env.labelSuffixH = labelSuffixH + "-" + columnStr;
			//----------------------------------------------------------------//
			

			//------------- �ޤ�������SuffixV�ο��������䤹 ----------------//
			if( i == Integer.parseInt((String)result.rows.get(rowNum)) ){
				rowNum++;
			}
			String rowStr = Integer.toString(rowNum);
			if(labelSuffixV.equals("null"))
				pdf_env.labelSuffixV = "-" + rowStr;
			else
				pdf_env.labelSuffixV = labelSuffixV + "-" + rowStr;
			//----------------------------------------------------------------//
			
	
			
			instance = (PDFValue)result.inList.get(i);
			((PDFTFE)tfe).setLabel(instance);
			
				
			//����ʤ��äݤ�
			//pdf_env.labelV = labelV;
			
			//------------ forʸ�Ƿ����֤�����Suffix�򸵤��᤹ ---------------//
			pdf_env.labelSuffixH = labelSuffixH;
			pdf_env.labelSuffixV = labelSuffixV;
			//----------------------------------------------------------------//
		}			


	}
	
	
	public void restoreFOLD(PDFValue check){

		if( check.tfeID != getId() ){
			((PDFTFE)tfe).restoreFOLD(check);
		}
		else{			
			int i;
			
			float widthSUM = 0;
			float maxHeight = 0;
			int rowNum = 0;
			
			this.rows.clear();
			this.rowHeights.clear();
			
			for(i=0; i<check.inList.size(); i++){
				PDFValue inBox = (PDFValue)check.inList.get(i);
			
				if(inBox.box_width > check.fold){
					System.out.println("ERROR: one Box's width is over the folding width");
					System.exit(0);
				}
				
				widthSUM += inBox.box_width;
				
				if(widthSUM > check.fold){
					this.rows.add(Integer.toString(i));
					this.rowHeights.add(Float.toString(maxHeight));
					
					maxHeight = 0;
					widthSUM = inBox.box_width;
					rowNum++;
				}
				
				if(maxHeight < inBox.box_height)
					maxHeight = inBox.box_height;				
			}
		
			this.rows.add(Integer.toString(i));
			this.rowHeights.add(Float.toString(maxHeight));
			
			this.rowNum = rowNum;	
			
			check.rows = this.rows;
			check.rowHeights = this.rowHeights;
			check.rowNum = this.rowNum;
		}
		
	}
	
	
	public boolean optimizeW(float Dexcess, PDFValue box){
		boolean flex = false;
		
		int local;
		
		float tmpDexcess;
		float tmpWidth = 0;
		float tmpHeight = 0;
		PDFValue keyBox = box;		//�Ȥꤢ�����ν����
		
		float sumCutWidth = 0;
	
		TFE originalTFE = this.tfe;		//����
		
		for (local=box.inList.size()-1; local>-1; local--) {	//�դ�������
			PDFValue inBox = (PDFValue)box.inList.get(local);
			
			//------------ ����Att����Image�λ��Τߡ��׻� -------------------//
			if(inBox.type.equals("Att") || inBox.type.equals("Func")){
				flex = false;
				break;
			}
			//---------------------------------------------------------------//
			
			tmpDexcess = (inBox.box_width / box.box_width) * Dexcess;	//�����ϥ��󥹥��󥹤�originalWidth�ǤϤʤ���max
			flex = ((PDFTFE)tfe).optimizeW(tmpDexcess, inBox);
			if(!flex)
				break;		//ȿ�����Ǥ��Ѵ����褭�ʤ����Ǥ����ä���⤦���ʤ��������Ϥ�äȺ٤�����ꤿ��
			else{
				sumCutWidth += pdf_env.cutWidth;
			}
			
			//��C1�Ȱ�ä���ȿ�����Ǥ�Ʊ���Ѵ��򤷤��� <-- �� �Ȥ������Բ�ǽ
			//���ΤޤޤǤϳ����Ǥǰ㤦�Ѵ��򤹤��ǽ��������
			// --> ���tfe_info�ϣ��ĤʤΤǺǸ�����Ǥ��Ѵ������Ѥ����
			//����Ǥ����꤭�뤫�ɤ����狼��ʤ� <-- �Ǹ�����Ǥ��Ѵ���tmpDexcess�ʾ帺��Ȥϸ¤�ʤ�
		}
		if(flex){
			//�����Ѵ��Ǥ����顢ľ���λҤ��Ѥ�äƤ��뤫Ĵ�٤ơ��Ѥ�äƤ���������Ǥ�getNewChild�Ǽ�äƤ��ƥ��å�
			if( ((PDFTFE)tfe).changeORnot() ){
				this.tfe = ((PDFTFE)tfe).getNewChild();
				//flex = true;
				pdf_env.cutWidth = sumCutWidth;
			}
		}
		
		//�ޤ���ߥȥ饤
		if(!flex){
			float newFold;
			
			newFold = box.box_width - Dexcess;
			tmpHeight = box.box_height;			//���Σ���ʬ
			for(local=0; local<box.inList.size(); local++){
				PDFValue inBox = (PDFValue)box.inList.get(local);
				if(tmpWidth > newFold){
					tmpWidth = 0;
					tmpHeight += inBox.box_height;
				}
				tmpWidth += inBox.box_width;
			}
			if( !(pdf_env.flexTH < (tmpHeight - box.box_height) / Dexcess) ){
				decos.put("fold", Float.toString(newFold));
				System.out.println("G1 is folding!!");
				flex = true;
				pdf_env.cutWidth = Dexcess;
			}
		}
		
		if(!flex){
			tmpWidth = 0;
			tmpHeight = 0;
			
			//--------- G2�ˤ������ˤ������Ȥʤ�inBox��õ�� -----------//
			for(local=0; local<box.inList.size(); local++){
				PDFValue inBox = (PDFValue)box.inList.get(local);
				if(tmpWidth < inBox.box_width){			//originalWidth�ǤϤʤ�max
					keyBox = inBox;
					tmpWidth = inBox.box_width;
				}
				tmpHeight += inBox.box_height;
			}
			//---------------------------------------------------------//
			if(box.box_width - keyBox.box_width >= Dexcess){		//������keyBox��originalWidth�ǤϤʤ��Ȼפ�
				//�Ǥ��Υ����å�
				if( !(pdf_env.flexTH < (tmpHeight - box.box_height) / Dexcess) ){
					newLE = new PDFG2(manager, pdf_env);
					((PDFG2)newLE).tfe = originalTFE;//this.tfe;
					((PDFG2)newLE).decos = this.decos;
					this.decos.put("fold", "0");
					//maxWidth���ѹ� <-- ���ʤ�����
					System.out.println("G1 change to G2");
					change = true;
					flex = true;
					pdf_env.cutWidth = box.box_width - keyBox.box_width;
				}
				else						//����elseʸ����ʤ���������
					flex = false;
			}
		}
		
		return flex;
	}
	
	
	public boolean optimizeH(float Dexcess, PDFValue box){
		boolean flex = false;
		
		return flex;
	}

	
	public TFE getNewChild(){
		return newLE;
	}
	
	
	public boolean changeORnot(){
		return change;
	}
	
	
	public void redoChange(){
		
	}
	
	
	@Override
	public String getSymbol() {
		return "G1";
	}

}