package supersql.codegenerator.PDF;

import supersql.codegenerator.TFE;

public abstract interface PDFTFE {
	
	public PDFValue getInstance();
	public void setLabel(PDFValue result);
	
	public boolean optimizeW(float Dexcess, PDFValue result);
	public boolean optimizeH(float Dexcess, PDFValue result);
	public TFE getNewChild();
	public boolean changeORnot();
	public void redoChange();

	public void restoreFOLD(PDFValue check);
}

