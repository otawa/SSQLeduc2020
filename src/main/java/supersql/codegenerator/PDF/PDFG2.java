package supersql.codegenerator.PDF;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;
import supersql.extendclass.ExtList;

public class PDFG2 extends Grouper implements PDFTFE {

	private PDFEnv pdf_env;
	
	//�ɲ�10.17
	private PDFValue value;
	
	private ExtList columns;		//�ƥ��󥹥��󥹤γ�������ǿ�  �����饹�ѿ��Ǥ�̵��
	
	//��ư10.28  ������work�᥽�å���
	private float tmp_width;
	private float tmp_height;
	private float box_widthSUM;		//�ޤ���ߤ��ѿ������ι��
	private float box_heightMAX;	//�ޤ���ߤ��ѿ����⤵�κ�����
	private float fold;				//�ޤ���ߤ��ѿ�����cm�����Ĥ��ޤ���फ���Ǽ			
	private int topID;				//width��re_set�����inList�β����ܤ��餫

	
	private ExtList maxWidths;			//����G2�γ��������
	
	private int columnNum;				//�ƥ��󥹥��󥹤��ޤ��������
	private ExtList columnWidths;		//�ƥ��󥹥��󥹤γ��������

	//----- setLevel�᥽�åɤ���Ĵ���Suffix�� -----//
	int repeatNum = 0;
	
	
	//�쥤�������Ѵ���
	TFE newLE;
	boolean change = false;
	
	
	//���󥹥ȥ饯��
	public PDFG2(Manager manager, PDFEnv pdf_env) {
		this.pdf_env = pdf_env;
		//�ɲ�10.30  �ޤ������
		maxWidths = new ExtList();
	}

	@Override
	public String work(ExtList data_info) {
		//�ɲ�10.14 �᥽�å���˰�ư10.24
		float box_width = 0;
		float box_height = 0;
//		int level = pdf_env.level;
		
//		int fold_num;

		box_widthSUM = 0;
		box_heightMAX = 0;
		fold = 0;
		
		columnNum = 0;
		
		
		
		System.out.println("");
		System.out.println("------- G2 -------");
//		System.out.println("[PDFG2:work]tfe_info = " + makele0());
		System.out.println("[PDFG2:work]data_info = " + data_info);

		System.out.println("++++ G2��value��new���ޤ�");
		//�ɲ�10.17
		value = new PDFValue("G2");
		
		columns = new ExtList();
		columnWidths = new ExtList();

		int i;

		setDataList(data_info);
		setDecoration();

		///////////////////////////////
		//////////////////(G2 1)�ΤȤ�
		///////////////////////////////

		if (tfe instanceof Attribute) {
			System.out.println("[PDFG2:work]tfe is Attribute");
			System.out.println("number of data = " + data_info.size());


			for (i = 0; i < data_info.size(); i++) {
				//�ɲ�10.27�����������Τߤ˿����ʤ�ʤ���¤�η����Ƚ�̤���ݥ����
//				pdf_env.level_max = level;

				
				//�ɲ�10.24 �ɲ�10.28�ޤ�����б��������Ʊ���������꿶�����
//				pdf_env.level++;
////				pdf_env.level += columnNum;
//				if(pdf_env.level > pdf_env.level_max ){
//					pdf_env.level_max = pdf_env.level;
//				}

				worknextItem();
				
				//�ɲ�10.24 �����Ʊ���������꿶��������ѹ�10.27
				//pdf_env.level--;
//				pdf_env.level = level;
				
				System.out.println("gggggggg "+pdf_env.tmp_height);
				
				System.out.println("tttttt box_height = " +box_height);
				
				//�ɲ�10.28���ޤ����Ƚ��
				if(fold != 0){
					if(Fold_or_Not(box_width, box_height, fold)){
						System.out.println("foldfoldfoldfoldfold");
						box_width = 0;
						box_height = 0;
					}
				}
				
				
				//�ɲ�10.17
				//������tmp_width,height��­������Ǥ���
				tmp_width = pdf_env.tmp_width;
				if (box_width < tmp_width)
					box_width = tmp_width;
				box_height += pdf_env.tmp_height;
				
				
				//�ɲ�10.30
				//checkMaxWidth(pdf_env.tmp_width);
				checkMaxWidth(box_width);
				
				
				System.out.println("++++ G2��Att��set���ޤ�");
				//�ɲ�10.17 value�Υ��å�
				value.inList.add(((PDFTFE)tfe).getInstance());
				
				//��ư

			}

			
		}

		//////////////////////////////////////////
		//////////////////(G2 (C1 1 2))�Ȥ��ΤȤ�
		//////////////////////////////////////////
		else {
			System.out.println("[PDFG2:work]tfe is Operator");


			//table���ͤ���������������򤹤�
			for (i = 0; i < data_info.size(); i++) {
				//�ɲ�10.27�����������Τߤ˿����ʤ�ʤ���¤�η����Ƚ�̤���ݥ����
//				pdf_env.level_max = level;


				//�ɲ�10.24 �ɲ�10.28�ޤ�����б��� �����Ʊ���������꿶�����
//				pdf_env.level++;
////				pdf_env.level += columnNum;
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
				tmp_width = pdf_env.tmp_width;
				if (box_width < tmp_width)
					box_width = tmp_width;
				box_height += pdf_env.tmp_height;

				
				//�ɲ�10.30
				//checkMaxWidth(pdf_env.tmp_width);
				checkMaxWidth(box_width);
				
				
				System.out.println("++++ G2�ǥꥹ�Ȥ�set���ޤ�");
				//�ɲ�10.17 value�Υ��å�
				value.inList.add(((PDFTFE)tfe).getInstance());
				
				
				//��ư

			}

			
		}

		///////////////////////////////

		//�ɲ�10.24  �ѹ�10.28  �᥽�åɲ������ޤ���ߤȶ�ͭ
		//for(i=0; i<value.inList.size(); i++){
		//	PDFValue re_set = (PDFValue)value.inList.get(i);
		//	re_set.box_width = box_width;
		//}
		re_setWidth(topID, value.inList.size(), box_width);

		
		//�ɲ�10.28  �ޤ���߻���box_width,height��Ĵ��
		if(fold != 0){
			box_width += box_widthSUM;//���Τ��Υ��󥹥��󥹤��������Ǥ�������
			if(box_height < box_heightMAX){
				box_height = box_heightMAX;
			}
		}

		
		//�����餯���Τ褦�ˤ����ɲä�������Ǥ���
//		if(columns.size() == 0)
//			columns.add(Integer.toString(value.inList.size()));
		//�ɲ�11.2  ���
		columns.add(Integer.toString(value.inList.size()));

		
		//---------- ���Υ��󥹥��󥹤������� ------------//
		float columnWidth = 0;
		
		for(i=0; i<columnWidths.size(); i++)
			columnWidth += Float.parseFloat((String)columnWidths.get(i));
		box_width = columnWidth;
		//------------------------------------------------//
		
		//�ѹ���11.29
		value.box_width = box_width;
		value.box_height = box_height;
		
		value.originalWidth = box_width;	//C2�Υ쥤�������Ѵ��˻���
		value.originalHeight = box_height;	//C1�Υ쥤�������Ѵ��˻���
		
		
		value.columnNum = columnNum;
		value.columns = columns;
		value.columnWidths = columnWidths;
		
		value.fold = fold;
		value.tfeID = getId();
		
		pdf_env.tmp_width = box_width;
		pdf_env.tmp_height = box_height;
		
		
		
		//-------- ����G2�γ�����������˴ƻ� ----------//
		float maxWidth;
		
		if( maxWidths.size() < columns.size() ){
			int diff = columns.size() - maxWidths.size();
			for(i=0; i<diff; i++)
				maxWidths.add("0");
		}
		for(i=0; i<columnWidths.size(); i++){
			maxWidth = Float.parseFloat((String)maxWidths.get(i));
			columnWidth = Float.parseFloat((String)columnWidths.get(i));
			if(maxWidth < columnWidth){
				System.out.println("yyyyyyyyyyyyyy re-set");
				maxWidths.set(i, Float.toString(columnWidth));
			}
		}
		//------------------------------------------------//
		return null;
		
	}
	
	
	
	
	//�ɲ�10.28���ޤ����Ƚ��
	public boolean Fold_or_Not(float box_width, float box_height, float fold){
		boolean fold_or_not = false;
		tmp_height = box_height;
		tmp_height += pdf_env.tmp_height;
		if(tmp_height>fold){
			Fold(box_width, box_height);
			columns.add(Integer.toString(value.inList.size()));
			//1���������ǡ��ޤ��֤�ľ�������Ǥ˾��������������
			columnNum++;
			fold_or_not = true;
		}
		return fold_or_not;
	}
	
	//�ɲ�10.28  �ޤ����
	public void Fold(float box_width, float box_height){	
		box_widthSUM += box_width;//¾���羮��Ӥ�ɬ�ס�checkMaxWidth()�Ǥ����Τ���
		tmp_height = box_height;
		if(tmp_height > box_heightMAX){
			box_heightMAX = tmp_height;
		}
		//���λ�inList��set����Ƥ���value�ˤ�box_width��re-set
		re_setWidth(topID, value.inList.size(), box_width);
		topID = value.inList.size();
	}
	
	//�ɲ�10.30
	public void checkMaxWidth(float box_width){
		float columnTmpWidth;
		
		if(columnNum >= columnWidths.size())
			columnWidths.add("0");
		columnTmpWidth = Float.parseFloat((String)columnWidths.get(columnNum));
		if(columnTmpWidth < box_width)
			columnWidths.set(columnNum, Float.toString(box_width));
	}
	
	public void re_setWidth(int start, int end, float set_width){
		int i;
		for(i=start; i<end; i++){
			PDFValue re_set = (PDFValue)value.inList.get(i);
			re_set.box_width = set_width;
		}
	}
	
	public void setDecoration(){
		//�ޤ����
		if(decos.containsKey("fold")){
			fold = Float.parseFloat(decos.get("fold").toString());
		}

	}
	
	public PDFValue getInstance(){
		System.out.println("++++ G2��set���ޤ���");
		return value;
	}

	
	public void setLabel(PDFValue result) {

		int i;
		PDFValue instance;
		
		//---- rowNum��repeatNum�Ȥ��ƥ��饹�ѿ����ѹ� ----//
		//int rowNum = 0;				//ȿ�����Ϥ����������٥��������ֲ����ܡ�
		int columnNum = 0;				//�ޤ�����б�����٥��������ֲ����ܡ�
		
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
		
		
		//��C2ľ����G2ľ����C2������·���褦�Ȥ���ˤϡ�
		//    �����ǿƤ�C2���ä���max�򥫥���ȥ��åפ��ʤ���Ф����Ϥ�
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
			

			
			//------------- �ޤ�������SuffixH�ο��������䤹 ----------------//
			if( i == Integer.parseInt((String)result.columns.get(columnNum)) ){
				columnNum++;
			}
			String columnStr = Integer.toString(columnNum);
			if(labelSuffixH.equals("null"))
				pdf_env.labelSuffixH = "-" + columnStr;
			else
				pdf_env.labelSuffixH = labelSuffixH + "-" + columnStr;
			//----------------------------------------------------------------//
			
			
			//------------- ���󥹥������SuffixV�ο��������䤹 ------------//
			repeatNum++;
			//rowNum = i;		//¿ʬ���ä���OK
			String rowStr = Integer.toString(repeatNum);
			if(labelSuffixV.equals("null"))
				pdf_env.labelSuffixV = "-" + rowStr;
			else
				pdf_env.labelSuffixV = labelSuffixV + "-" + rowStr;
			//----------------------------------------------------------------//
			
	
			
			instance = (PDFValue)result.inList.get(i);
			((PDFTFE)tfe).setLabel(instance);
			
				
			//����ʤ��äݤ�
			//pdf_env.labelH = labelH;
			
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
			
			float heightSUM = 0;
			float maxWidth = 0;
			int columnNum = 0;
			
			this.columns.clear();
			this.columnWidths.clear();
			
			for(i=0; i<check.inList.size(); i++){
				PDFValue inBox = (PDFValue)check.inList.get(i);
			
				if(inBox.box_height > check.fold){
					System.out.println("ERROR: one Box's height is over the folding height");
					System.exit(0);
				}
				
				heightSUM += inBox.box_height;
				
				if(heightSUM > check.fold){
					this.columns.add(Integer.toString(i));
					this.columnWidths.add(Float.toString(maxWidth));
					
					maxWidth = 0;
					heightSUM = inBox.box_height;
					columnNum++;
				}
				
				if(maxWidth < inBox.box_width)
					maxWidth = inBox.box_width;				
			}
		
			this.columns.add(Integer.toString(i));
			this.columnWidths.add(Float.toString(maxWidth));
			
			this.columnNum = columnNum;		
			
			check.columns = this.columns;
			check.columnWidths = this.columnWidths;
			check.columnNum = this.columnNum;
		}
		
	}
	
	
	public boolean optimizeW(float Dexcess, PDFValue box){
		boolean flex = false;
		int i;
		
		PDFValue inBox = (PDFValue)box.inList.get(0);
		
		//------------ �Ǿ�̤��ޤ����Ǥ������inBox��Dexcess���� ------------//
		if( box.labelH.equals("0") && box.fold!=0 ){
			float tmpWidth;
			float maxColumnWidth = 0;
			for(i=0; i<box.columnNum+1; i++){
				tmpWidth = Float.parseFloat((String)box.columnWidths.get(i));
				if(maxColumnWidth < tmpWidth){
					maxColumnWidth = tmpWidth;
					inBox = (PDFValue)box.inList.get(Integer.parseInt((String)columns.get(i))-1);
				}
			}
			Dexcess = maxColumnWidth - pdf_env.widthPaper + pdf_env.paddingPaper_H*2;
		}
		//----------------------------------------------------------------------------//

		flex = ((PDFTFE)tfe).optimizeW(Dexcess, inBox);
		//ľ���λҤ��Ѥ�äƤ��뤫Ĵ�٤ơ��Ѥ�äƤ�����ƻ����Ǥ�getNewChild�Ǽ�äƤ��ƥ��å�
		if( ((PDFTFE)tfe).changeORnot() ){	
			//newLE = new PDFG2(manager, pdf_env);
			//((PDFG2)newLE).tfe = ((PDFTFE)tfe).getNewChild();	//����Ǥ�TFE���ǥ��åȤ���롣����ǽ���������뤫��
			this.tfe = ((PDFTFE)tfe).getNewChild();
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
		return "G2";
	}

}