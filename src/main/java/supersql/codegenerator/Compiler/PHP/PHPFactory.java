package supersql.codegenerator.Compiler.PHP;

import java.io.Serializable;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Connector;
import supersql.codegenerator.Factory;
import supersql.codegenerator.Function;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.IfCondition;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;
import supersql.codegenerator.HTML.HTMLAttribute;
import supersql.codegenerator.HTML.HTMLC0;
import supersql.codegenerator.HTML.HTMLC1;
import supersql.codegenerator.HTML.HTMLC2;
import supersql.codegenerator.HTML.HTMLC3;
import supersql.codegenerator.HTML.HTMLEnv;
import supersql.codegenerator.HTML.HTMLFunction;
import supersql.codegenerator.HTML.HTMLG1;
import supersql.codegenerator.HTML.HTMLG2;
import supersql.codegenerator.HTML.HTMLG3;
import supersql.codegenerator.HTML.HTMLIfCondition;
import supersql.codegenerator.HTML.HTMLManager;

public class PHPFactory extends Factory implements Serializable{

	private static final long serialVersionUID = 1L;
	public static HTMLEnv htmlEnv;
	public static HTMLEnv htmlEnv2;

	@Override
	public Attribute createAttribute(Manager manager) {
		return new HTMLAttribute(manager, htmlEnv, htmlEnv2);
	}

	@Override
	public Connector createC0(Manager manager) {
		return new HTMLC0(manager, htmlEnv, htmlEnv2);
	}

	@Override
	public Connector createC1(Manager manager) {
		return new HTMLC1(manager, htmlEnv, htmlEnv2);
	}

	@Override
	public Connector createC2(Manager manager) {
		return new HTMLC2(manager, htmlEnv, htmlEnv2);
	}

	@Override
	public Connector createC3(Manager manager) {
		// return new HTMLC1(manager, html_env);
		return new HTMLC3(manager, htmlEnv, htmlEnv2);
	}

	@Override
	public Connector createC4(Manager manager) {
		return new HTMLC1(manager, htmlEnv, htmlEnv2);
		// return new HTMLC4(manager);
	}

	@Override
	public Attribute createConditionalAttribute(Manager manager) {
		return new HTMLAttribute(manager, htmlEnv, htmlEnv2, true);
	}

	@Override
	public Function createFunction(Manager manager) {
		return new HTMLFunction(manager, htmlEnv, htmlEnv2);
	}

	@Override
	public Grouper createG0(Manager manager) {
		// return new HTMLG0(manager, html_env);
		return new HTMLG1(manager, htmlEnv, htmlEnv2);
	}

	@Override
	public Grouper createG1(Manager manager) {
		return new HTMLG1(manager, htmlEnv, htmlEnv2);
	}

	@Override
	public Grouper createG2(Manager manager) {
		return new HTMLG2(manager, htmlEnv, htmlEnv2);
	}

	@Override
	public Grouper createG3(Manager manager) {
		return new HTMLG3(manager, htmlEnv, htmlEnv2);

	}

	@Override
	public Grouper createG4(Manager manager) {
		return new HTMLG1(manager, htmlEnv, htmlEnv2);
		// return new HTMLG4(manager, html_env);
	}

	@Override
	public IfCondition createIfCondition(Manager manager,
			supersql.codegenerator.Attribute condition, TFE thenTfe, TFE elseTfe) {
		return new HTMLIfCondition(manager, htmlEnv, htmlEnv2, condition,
				thenTfe, elseTfe);
	}

	@Override
	public void createLocalEnv() {
		htmlEnv = new HTMLEnv();
		htmlEnv2 = new HTMLEnv();
	}

	@Override
	public Manager createManager() {
		return new HTMLManager(htmlEnv, htmlEnv2);
	}

}
