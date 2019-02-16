package supersql.codegenerator.PDF;

import java.util.Vector;

import supersql.extendclass.ExtList;

//文字列１つ１つに関する情報に関する値をまとめたクラス
public class PDFValue {
	
	//追加11.29
	protected float originalWidth, originalHeight;
	//追加10.17
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
	
	//追加10.31 Grouper
	protected ExtList columns;
	protected ExtList columnWidths;
	protected int columnNum;
	//11.09
	protected ExtList rows;
	protected ExtList rowHeights;
	protected int rowNum;
	
	//追加11.25 折り畳む時に指定した幅でボックスサイズをつくるため
	protected float fold;
	
	//変更11.3 Stringへ 11.10 ２つに変更
	protected String labelH;
	protected String labelV;
	
	protected String labelOH;
	protected String labelOV;
	
	//追加12.13
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

	//追加10.17
	//コンストラクタ
	public PDFValue(String type){
		this.type = type;
		this.inList = new ExtList();
	}

}