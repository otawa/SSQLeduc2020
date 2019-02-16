package supersql.codegenerator.PDF;

import java.util.Vector;

import supersql.extendclass.ExtList;

//ʸ���󣱤ģ��Ĥ˴ؤ������˴ؤ����ͤ�ޤȤ᤿���饹
public class PDFValue {
	
	//�ɲ�11.29
	protected float originalWidth, originalHeight;
	//�ɲ�10.17
	protected String type;
	protected float box_width, box_height;
	protected ExtList inList;
	protected String data = "null";
	protected float data_width, data_height;
	protected float padding;
	protected String align = "left";
	protected String valign = "top";
	protected String bgcolor = "null";
	protected String fontcolor = "null";
	protected String fontstyle = "normal";
	protected String bordercolor = "null";
	
	//�ɲ�10.31 Grouper
	protected ExtList columns;
	protected ExtList columnWidths;
	protected int columnNum;
	//11.09
	protected ExtList rows;
	protected ExtList rowHeights;
	protected int rowNum;
	
	//�ɲ�11.25 �ޤ������˻��ꤷ�����ǥܥå�����������Ĥ��뤿��
	protected float fold;
	
	//�ѹ�11.3 String�� 11.10 ���Ĥ��ѹ�
	protected String labelH;
	protected String labelV;
	
	protected String labelOH;
	protected String labelOV;
	
	//�ɲ�12.13
	protected boolean foldFLAG_H = false;
	protected boolean foldFLAG_V = false;
	protected int tfeID;
	
	
	
	protected String s;

	protected float fontsize;

	protected int row = 1;

	protected int image_num = -1;

	protected Vector text_box = new Vector();

	protected String[] text_line;

	boolean set_width = false;

	protected float s_width;

	protected boolean bg_rgb = false;
	protected boolean font_rgb = false;

	//�ɲ�10.17
	//���󥹥ȥ饯��
	public PDFValue(String type){
		this.type = type;
		this.inList = new ExtList();
	}

}