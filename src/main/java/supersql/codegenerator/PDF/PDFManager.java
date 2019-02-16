package supersql.codegenerator.PDF;

import supersql.codegenerator.ITFE;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.extendclass.ExtList;

public class PDFManager extends Manager {
	
	private PDFEnv pdf_env;
	private PDFWriter writer;
	private PDFOptimizer optimizer;

	//�ǉ�10.14
	private ITFE tfe_info;
	private ExtList data_info;

	//�R���X�g���N�^
	public PDFManager(PDFEnv pdf_env) {
		this.pdf_env = pdf_env;
		writer = new PDFWriter(pdf_env);
		optimizer = new PDFOptimizer(pdf_env);
	}

	@Override
	public void generateCode(ITFE tfe_info, ExtList data_info) {
		System.out.println("[PDFManager:generateCode]tfe_info = "
				+ tfe_info.makele0());
		System.out.println("[PDFManager:generateCode]data_info = " + data_info);

		//�ǉ�10.14
		this.tfe_info = tfe_info;
		this.data_info = data_info;
		
		//�ǉ�12.13
		optimizer.set_tfeInfo(tfe_info);
		
		
		String filename = GlobalEnv.getoutfilename();
		
		if (filename == null) {
			filename = GlobalEnv.getfilename();
        	if (filename.indexOf(".sql")>0) {
        		filename = filename.substring(0, filename.indexOf(".sql")) + ".pdf";
        	} else if (filename.indexOf(".ssql")>0) {
        		filename = filename.substring(0, filename.indexOf(".ssql")) + ".pdf";
        	}
        }
		
		System.out.println(GlobalEnv.getoutfilename());
		pdf_env.createPDFfile(filename);


		pageReady();//�y�[�W��p�ӂ��ď�?��?

/*		
		tfe_info.work(data_info);
		
		
		boolean layoutChange;
		layoutChange = boxAdjust();
		
		//?�C�A�E�g����?���Ă��������?�x�I�@��?����?�񂾂�
		//boxAdjust��optimizeSTART��?����?�͖̂�肩?
		if(layoutChange){
			tfe_info.work(data_info);
			boxAdjust();
		}
*/
		int layoutChange = 0;
		
		while(layoutChange != -1){
			switch(layoutChange){
				case 1:
					layoutChange = boxAdjust();
					break;
				case 2:
					tfe_info.work(data_info);
					layoutChange = boxAdjust();
					break;
				default:		//���߂͂����̏�?
					tfe_info.work(data_info);
					layoutChange = boxAdjust();
					break;
			}
		}		
		
		pagePrint();
	}
	

	
	//�t�@�C?��p�ӂ�?���\�b�h���Ă�
	public void pageReady(){
		writer.page_ready();
	}

	
	public int boxAdjust(){
		int layoutChange;
		
		optimizer.initializeLabel();	
		
		System.out.println();
		PDFValue result = ((PDFTFE)tfe_info).getInstance();
		
		System.out.println();	
		System.out.println("====================");
		System.out.println("      Set Label     ");	
		System.out.println("====================");
		
		((PDFTFE)tfe_info).setLabel(result);		

		
		optimizer.initializeAdjust();
		
		System.out.println();	
		System.out.println("====================");
		System.out.println("    Adjusting Box   ");	
		System.out.println("====================");
		
		//��?��݂Ń��x?��\��Ȃ���??1���Ԃ��Ă���
		//�\��Ȃ����K�v���Ȃ�??0���Ԃ��Ă�?
		layoutChange = optimizer.adjust(result);
		
		
		if(layoutChange == 0){

			System.out.println();	
			System.out.println("====================");
			System.out.println("  Start Optimizing  ");	
			System.out.println("====================");
			
			//?�C�A�E�g�̕ύX��������??2���Ԃ��Ă��Ă���?�xAdjust
			//�ύX���Ȃ�?����-1���Ԃ��Ă��ďo�͂�
			layoutChange = optimizer.optimizeSTART(data_info, result);
			//layoutChange = optimizer.optimizeSTART(tfe_info, data_info, result);
		
		}
		
		return layoutChange;
	}
	
	//PDF�t�@�C?�Ƀe�L�X�gBOX��BOX�������o�����\�b�h���Ă�
	public void pagePrint(){
		
		System.out.println();	
		PDFValue result = ((PDFTFE)tfe_info).getInstance();
		
		//result�̕����@???�͒����ς݁@��?�q�����C���X�^���X�̐�?�ڂŃy�[�W�ς�
		//AllPage�ɑ�?
		optimizer.divideResult(result);		
		//�����ł�?���N�Ȃǂ�?�C�A�E�g�ύX���������烁�\�b�h�̖�?�l�Ƃ��Đ^��Ԃ�

		
		ExtList outputResult = optimizer.getOutputResult();
		
		
		System.out.println();	
		System.out.println("====================");
		System.out.println("   Start Printing   ");	
		System.out.println("====================");

		writer.pagePrint(outputResult);
	}

	//�t�@�C?���?���\�b�h���Ă�
	@Override
	public void finish(){

		//System.out.println("[PDFManager:finish]table_le2 = " + table_le2);

		//generateCode���Ɉړ�
		//page_print();
		
		//writer����?�������C����?
		//writer.closePDFfile();
		pdf_env.closePDFfile();

		/*
		 * System.out.println(filename);
		 * 
		 * try{ //�o�͐�f�B?�N�g?�w? filename =
		 * "/home/toyama/shinpei/ssql_java/package/bin/" + filename; /-test-*
		 * PrintWriter writer = new PrintWriter(new BufferedWriter(new
		 * FileWriter(filename))); writer.write(value.pdf); writer.close();
		 * -test-/ } catch(Exception e){ System.out.println("Exception: " + e); }
		 *  
		 */

	}
}