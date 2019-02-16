package supersql.codegenerator;

import supersql.extendclass.ExtList;

public class IfCondition extends Operator {

	protected Attribute condition;
	protected TFE thenTfe;
	protected TFE elseTfe;

	public IfCondition(Attribute condition, TFE thenTfe, TFE elseTfe) {
		super();
		this.condition = condition;
		this.thenTfe = thenTfe;
		this.elseTfe = elseTfe;
	}

	public void debugout(int count) {
		Debug dbgout = new Debug();
		dbgout.prt(count, "<IfCondition type=" + getSymbol() + " thenTfe="
				+ thenTfe + " elseTfe=" + elseTfe + " condition=" + condition.toString() + ">");

		dbgout.prt(count, "</IfCondition>");
	}

	private String getSymbol() {
		return "HTMLIfCondition";
	}

	public ExtList makesch() {
		ExtList outsch = new ExtList();
		outsch.addAll(condition.makesch());
		outsch.addAll(thenTfe.makesch());
		outsch.addAll(elseTfe.makesch());
		return outsch;
	}

	public ExtList makele0() {
		ExtList le0 = new ExtList();

		le0.add(this.getSymbol());
		le0.addAll(condition.makele0());
		le0.addAll(thenTfe.makele0());
		le0.addAll(elseTfe.makele0());

		return le0;
	}

	public String work(ExtList data_info) {
		return null;
		//return aggregate;
	}

	public int countconnectitem() {
		if(elseTfe != null)
			return 3;
		else
			return 2;
	}

	public ExtList makeschImage() {
		ExtList outsch = new ExtList();
		outsch.addAll(condition.makeschImage());
		outsch.addAll(thenTfe.makeschImage());
		outsch.addAll(elseTfe.makeschImage());
		return outsch;
	}

	public Attribute getCondition() {
		return condition;
	}

	public ITFE getThenTfe() {
		return thenTfe;
	}

	public ITFE getElseTfe() {
		return elseTfe;
	}

	@Override
	public String toString() {
		if(elseTfe == null)
			return "if " + condition + " then "+ thenTfe;
		else
			return "if " + condition + " then "+ thenTfe + " else "+elseTfe;
	}

	@Override
	public Object createNode(ExtList<ExtList<String>> data_info) {
		return null;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	
	
	static TFE IfCondition(ExtList if_then_else) {
		String token = "";
		ExtList firstTFE;
		ExtList secondTFE;
	
		if(if_then_else.get(0).equals("if")){
			token = CodeGenerator.exprtostring( (ExtList)((ExtList)if_then_else.get(2)).get(1) );
			Attribute condition = CodeGenerator.makeAttribute(token, true);
			int t_idx = 0;
			if( if_then_else.indexOf("then") != -1){
				t_idx = if_then_else.indexOf("then");
			}
			firstTFE = (ExtList)if_then_else.get(t_idx + 2);
	
			int e_idx = 0;
			if( if_then_else.indexOf("else") != -1){
				e_idx = if_then_else.indexOf("else");
			}
			secondTFE = (ExtList)if_then_else.get(e_idx + 2);
	
			TFE thenTfe = CodeGenerator.initialize(firstTFE);
			TFE elseTfe = CodeGenerator.initialize(secondTFE);
	
			IfCondition out_tfe = makeIfCondition(condition, thenTfe, elseTfe );
			return out_tfe;
		}else{
			token = CodeGenerator.exprtostring( (ExtList)((ExtList)if_then_else.get(1)).get(1) );
			Attribute condition = CodeGenerator.makeAttribute(token, true);
	
			int t_idx = 0;
			if(if_then_else.indexOf("?") != -1){
				t_idx = if_then_else.indexOf("?");
			}
			firstTFE = (ExtList)if_then_else.get(t_idx + 1);
	
			int e_idx = 0;
			if(if_then_else.indexOf("?") != -1){
				e_idx = if_then_else.indexOf(":");
			}
			secondTFE = (ExtList)if_then_else.get(e_idx + 1);
	
	
			TFE thenTfe = CodeGenerator.initialize(firstTFE);
			TFE elseTfe = CodeGenerator.initialize(secondTFE);
	
			IfCondition out_tfe = makeIfCondition(condition, thenTfe, elseTfe );
			return out_tfe;
		}
	
	}

	private static IfCondition makeIfCondition(Attribute condition, TFE thenTfe, TFE elseTfe) {
		return createIfCondition(condition, thenTfe, elseTfe);
	}

	private static IfCondition createIfCondition(Attribute condition, TFE thenTfe, TFE elseTfe){
		IfCondition ifCondition = CodeGenerator.factory.createIfCondition(CodeGenerator.manager, condition, thenTfe, elseTfe);
		ifCondition.setId(CodeGenerator.TFEid++);
		return ifCondition;
	}
	

}
