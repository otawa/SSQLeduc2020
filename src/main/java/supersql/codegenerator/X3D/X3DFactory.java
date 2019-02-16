package supersql.codegenerator.X3D;

import supersql.codegenerator.Attribute;
import supersql.codegenerator.Connector;
import supersql.codegenerator.Factory;
import supersql.codegenerator.Function;
import supersql.codegenerator.Grouper;
import supersql.codegenerator.IfCondition;
import supersql.codegenerator.Manager;
import supersql.codegenerator.TFE;

public class X3DFactory extends Factory {

	X3DEnv x3d_env;

	@Override
	public void createLocalEnv() {
		x3d_env = new X3DEnv();
	}

	@Override
	public Manager createManager() {
		return new X3DManager(x3d_env);
	}

	@Override
	public Connector createC0(Manager manager) {
		return new X3DC0(manager, x3d_env);
	}

	@Override
	public Connector createC1(Manager manager) {
		return new X3DC1(manager, x3d_env);
	}

	@Override
	public Connector createC2(Manager manager) {
		return new X3DC2(manager, x3d_env);
	}

	@Override
	public Connector createC3(Manager manager) {
		return new X3DC3(manager, x3d_env);
	}

	@Override
	public Connector createC4(Manager manager) {
		return new X3DC1(manager, x3d_env);
	}

	@Override
	public Grouper createG0(Manager manager) {
		return new X3DG1(manager, x3d_env);
	}

	@Override
	public Grouper createG1(Manager manager) {
		return new X3DG1(manager, x3d_env);
	}

	@Override
	public Grouper createG2(Manager manager) {
		return new X3DG2(manager, x3d_env);
	}

	@Override
	public Grouper createG3(Manager manager) {
		return new X3DG3(manager, x3d_env);

	}

	@Override
	public Grouper createG4(Manager manager) {
		return new X3DG1(manager, x3d_env);
	}

	@Override
	public Attribute createAttribute(Manager manager) {
		return new X3DAttribute(manager, x3d_env);
	}

	@Override
	public Function createFunction(Manager manager) {
		return new X3DFunction(manager, x3d_env);
	}

	@Override
	public Attribute createConditionalAttribute(Manager manager) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IfCondition createIfCondition(Manager manager, Attribute condition,
			TFE thenTfe, TFE elseTfe) {
		// TODO Auto-generated method stub
		return null;
	}
}

