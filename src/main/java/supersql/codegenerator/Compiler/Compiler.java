package supersql.codegenerator.Compiler;

import supersql.codegenerator.Compiler.JSP.JSP;
import supersql.codegenerator.Compiler.PHP.PHP;
import supersql.codegenerator.Compiler.Rails.Rails;
import supersql.extendclass.ExtList;

public class Compiler {

	public static boolean isCompiler = false;
	
	public Compiler() {

	}

	//getExtension
	public static String getExtension() {
		String e = ".html";
		if(PHP.isPHP) 			e = ".php";
		else if(Rails.isRails) 	e = ".php";
		else if(JSP.isJSP)	 	e = ".jsp";
		return e;
	}
	
	//Added by goto 20161113  for Compiler:[ ] -> [ ]@{dynamic}
	//If Compiler && grouper then add @{dynamic}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static boolean addDynamicModifier(ExtList tfe_tree) {
		if(isCompiler){
		//if(isCompiler && ((ExtList)tfe.get(0)).get(0).toString().equals("grouper")){
			final String DYNAMIC_MODIFIER = "dynamic";
			ExtList tfe = ((ExtList)tfe_tree.get(1));
			int index = tfe.size()-1;
			String deco = tfe.get(index).toString();
			
			if(!(tfe.get(index) instanceof ExtList)){
				if(deco.contains("@{") && deco.contains("}")){
					deco = deco.substring(0, deco.lastIndexOf("}")) + "," + DYNAMIC_MODIFIER + "}";
				}else{
					deco = "@{"+DYNAMIC_MODIFIER+"}";
				}
			}else{
				deco = "@{"+DYNAMIC_MODIFIER+"}";
			}
			tfe.set(index, deco);
			return true;
			
		}else
			return false;
	}
	
}
