package supersql.codegenerator.PDF;

import supersql.extendclass.ExtList;

//PDF�˴ؤ������Ū���ѿ��ȥ᥽�åɤ���ĥ��饹
public class PDFWriter {

	private PDFEnv pdf_env;

	private int i;
	
	
	private float default_posH;
	private float default_posV;
	
		
	private float adjust_align = 0;
	private float adjust_valign = 0;
	private float[] color;
	private float[] bordercolor;
	private float[] bgcolor;
	private float[] fontcolor;	
	
	private boolean line = true;

	
	
	//���󥹥ȥ饯��
	public PDFWriter(PDFEnv pdf_env) {
		this.pdf_env = pdf_env;
	}

	
	//�ڡ������Ѱ�
	public void page_ready() {
		pdf_env.page_ready();
	}

/*
	//����˥��ԡ����줿ʸ������ξ���򸵤˥ܥå���������Ϥ�,�ڡ������Ĥ���
	public void pagePrint(PDFValue result) {
		default_posH = pdf_env.paddingPaper_H;
		default_posV = pdf_env.heightPaper - pdf_env.paddingPaper_V;
		
//		for(i=0; i<result.inList.size(); i++){
//			PDFValue outputONEpage = (PDFValue)result.inList.get(i);
//			output(outputONEpage, default_posH, default_posV);
			
			output(result, default_posH, default_posV);
		
			pdf_env.end_page();
//		}
		
		System.out.println("end");

	}
*/	
	
	//����˥��ԡ����줿ʸ������ξ���򸵤˥ܥå���������Ϥ�,�ڡ������Ĥ���
	public void pagePrint(ExtList outputResult) {
		default_posH = pdf_env.paddingPaper_H;
		default_posV = pdf_env.heightPaper - pdf_env.paddingPaper_V;
		
		for(i=0; i<outputResult.size(); i++){
			PDFValue resultPage = (PDFValue)outputResult.get(i);
			output(resultPage, default_posH, default_posV);
//			output(result, default_posH, default_posV);
		
			pdf_env.end_page();
			
			if( i != outputResult.size()-1 )
				page_ready();
		}
		
		System.out.println("end");

	}
	
	
	//�ɲ�10.17 �Ƶ�Ū�˸ƤФ�����Ƥ����Ƥ���Ϥ���᥽�å�
	public void output(PDFValue result, float posH, float posV){
		
		PDFValue instance;
		String type = result.type;
		int local;//////////////�ݥ���ȡ��᥽�å����������ʤ�������
		
		//�ɲ�10.31���ޤ����
		float tmp_posH = posH;
		float tmp_posV = posV;
		int fold_num = 0;
		int tmp = 0;
		
		//���ˤ��줬C1�ʤΤ�G2�ʤΤ���������ؿ�position�ΰ����Ȥʤ�
		//String type = instance.type;

		//���Ϥ����ΤϤ���
//		if(result.type.equals("G1"))
		box_out(result, posH, posV);
		
		for(local=0; local<result.inList.size(); local++){
			
			instance = (PDFValue)result.inList.get(local);//�ޤ������
//			System.out.println("instance.data ="+instance.data);
			output(instance, posH, posV);//result�ǤϤʤ�instance���Ϥ�
			
			//�Ƥ�type�ȻҤ�����
			//position(type, instance);
			if(type.equals("C1")){
				posH += instance.box_width;
			}
			else if(type.equals("C2")){
				posV -= instance.box_height;
			}
			else if(type.equals("G1")){
				if(tmp == Integer.parseInt((String)result.rows.get(fold_num))-1){
					if(fold_num < result.rowNum)//���դ�-1���񤤤Ƥ��ä�
						fold_num++;
					posH = tmp_posH;
					posV -= instance.box_height;
				}
				else
					posH += instance.box_width;
				tmp++;			
			}
			else if(type.equals("G2")){
				if(tmp == Integer.parseInt((String)result.columns.get(fold_num))-1){
					if(fold_num < result.columnNum)//���դ�-1���񤤤Ƥ��ä�
						fold_num++;
					posV = tmp_posV;
					posH += instance.box_width;
				}
				else{
					posV -= instance.box_height;
				}
				tmp++;			
			}
			else
				System.out.println("%%%%%%%%%%%%%%%%%%%%%%");
		}
		
	}
	
	public void box_out(PDFValue result, float posH, float posV){
		
		//pdf_env.setlinewidth(linewidth);
		pdf_env.setlinewidth();
		pdf_env.defaultborder();
		if(!(result.bordercolor.equals("null"))){
			bordercolor = new float[3];
			bordercolor = getColor(result.bordercolor);
			pdf_env.setBorderColor(bordercolor[0], bordercolor[1], bordercolor[2]);
		}
		pdf_env.defaultbackground();//�ǥե�����طʿ�������
		if(!(result.bgcolor.equals("null"))){
			bgcolor = new float[3];
			bgcolor = getColor(result.bgcolor);
			pdf_env.setBGColor(bgcolor[0], bgcolor[1], bgcolor[2]);
		}else if(result.data.equals("null")){
			bgcolor = new float[3];
			if(result.type.equals("G1"))
				bgcolor = getColor("#0000ff");
			if(result.type.equals("G2"))
				bgcolor = getColor("#ffff00");
			if(result.type.equals("C1"))
				bgcolor = getColor("#ff0000");
			if(result.type.equals("C2"))
				bgcolor = getColor("#00ff00");
			pdf_env.setBGColor(bgcolor[0], bgcolor[1], bgcolor[2]);
		}
		pdf_env.save();
		
		//��ɸ�Ͽ޷��κ������ʤΤ�
		posV -= result.box_height;
		
		pdf_env.rect(posH, posV, result.box_width, result.box_height);
//		System.out.println("posH = "+posH);
//		System.out.println("posV = "+posV);
//		System.out.println("result.box_width = "+result.box_width);
//		System.out.println("result.data_width = "+result.data_width);
//		System.out.println("result.box_height = "+result.box_height);
//		System.out.println("result.data_height = "+result.data_height);
		
		////////////////////////////////////
//		pdf_env.stroke();//������
		if (line == true)
			pdf_env.fill_stroke();//����
		else
			pdf_env.fill();
		/////////////////////////////////////
		pdf_env.restore();
		
		
		if(result.type.equals("Att")){		
			textOut(result, posH, posV);
		}
		else{//���ϴؿ���image�ؿ������ʤ�����
			imageOut(result, posH, posV);
		}
	}

	
	//�ɲ�10.25 ���ط�
	public float[] getColor(String decorate){
		color = new float[3];
		//System.out.println("wwwww "+decorate);
		if( decorate.startsWith("#") ){
			String rgb = decorate.substring(1);//#�������
			//System.out.println("wwwww "+rgb);
			//ľ�ܥ��֥������Ȥ˳�RGB�ͤ�����255f��float���äƤ��� 
			color[0] = Integer.parseInt(rgb.substring(0,2),16)/255f;
			color[1] = Integer.parseInt(rgb.substring(2,4),16)/255f;
			color[2] = Integer.parseInt(rgb.substring(4,6),16)/255f;
			//System.out.println("wwwww "+color[0]+color[1]+color[2]);
		}
		//���ݥåȥ��顼�ν���
		return color;
	}
	
	//�᥽�åɲ�10.25
	public void textOut(PDFValue instance, float posH, float posV){
		
		if(instance.align.equals("left"))
			adjust_align = instance.padding;
		else if(instance.align.equals("center"))
			adjust_align = (instance.box_width - instance.data_width)/2;
		else if(instance.align.equals("right"))
			adjust_align = instance.box_width - instance.data_width - instance.padding;

		if(instance.valign.equals("top"))
			adjust_valign = instance.box_height - instance.data_height - instance.padding;
		else if(instance.valign.equals("center"))
			adjust_valign = (instance.box_height - instance.data_height)/2;
		else if(instance.valign.equals("bottom"))
			adjust_valign = instance.padding;
		
		//text_flow���Ȳ��Τ����줬��ǽ���ʤ�
		//pdf_env.defaultfont();
		
		fontcolor = new float[3];//text_flow�Ǥʤ����if����
		fontcolor[0] = 0;
		fontcolor[1] = 0;
		fontcolor[2] = 0;
		if(!(instance.fontcolor.equals("null"))){
			fontcolor = getColor(instance.fontcolor);
			//text_flow���Ȳ��Τ����줬��ǽ���ʤ�
			//pdf_env.setFontColor(fontcolor[0], fontcolor[1], fontcolor[2]);
		}
		pdf_env.save();
		
		pdf_env.textflow(instance, posH + adjust_align, posV + adjust_valign, fontcolor);
		
		//pdf_env.setfont(pdf_env.font, pdf_env.fontsize);
		//pdf_env.show_xy(instance.data, posH + adjust_align, posV + adjust_valign);
		//pdf_env.show_xy(Integer.toString(result.level), posH + adjust_align, posV + adjust_valign);
		
		pdf_env.restore();	
	}
	
	//�ɲ�10.25
	public void imageOut(PDFValue instance, float posH, float posV){
		
		if(instance.image_num!=-1){
			
			/*��ư10.27  PDFFunction��˰�ư
			/* ����ǤϤ��ޤ��Ԥ��ʤ� ������Ĥ�������֤���ξ���׻�����������̵��
			if( (instance.data_width > instance.box_width) && (instance.data_height > instance.box_height) ){
				
				instance.data_width = instance.box_width - instance.padding*2f;
				instance.data_height = instance.box_height - instance.padding*2f;
			}
			else 
			///////////////
			if(instance.data_width > instance.box_width){
				float original_width = instance.data_width;
				instance.data_width = instance.box_width - instance.padding*2f;
				float scale = instance.data_width / original_width;
				instance.data_height = instance.data_height * scale;
			}
			if(instance.data_height > instance.box_height){
				float original_height = instance.data_height;
				instance.data_height = instance.box_height - instance.padding*2f;
				float scale = instance.data_height / original_height;
				instance.data_width = instance.data_width * scale;
			}
			*/

//			pdf_env.fit_image_blind(instance.image_num, posH, posV, instance.box_width, instance.box_height);
		
			if(instance.align.equals("left"))
				adjust_align = instance.padding;
			else if(instance.align.equals("center"))
				adjust_align = (instance.box_width - instance.data_width)/2f;
			else if(instance.align.equals("right"))
				adjust_align = instance.box_width - instance.data_width - instance.padding;

			if(instance.valign.equals("top"))
				adjust_valign = instance.box_height - instance.data_height - instance.padding;
			else if(instance.valign.equals("center"))
				adjust_valign = (instance.box_height - instance.data_height)/2f;
			else if(instance.valign.equals("bottom"))
				adjust_valign = instance.padding;
			
			System.out.println("data_height "+instance.data_height+" box_height "+instance.box_height);
			
			System.out.println("adjust_valign = "+adjust_valign+" "+instance.data_height);
			pdf_env.fit_image(instance.image_num, posH+adjust_align, posV+adjust_valign, instance.box_width-instance.padding*2, instance.box_height-instance.padding*2);
			//pdf_env.fit_image(instance.image_num, posH+adjust_align, posV+adjust_valign, instance.data_width, instance.data_height);		
			pdf_env.close_image(instance.image_num);
		}
		
	}
	

	/*
	 * //PDF�ե�������Ĥ���᥽�å� public void closePDFfile() { pdf_env.closePDFfile(); }
	 */

}