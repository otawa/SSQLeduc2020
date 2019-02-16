package supersql.codegenerator.VR;

import supersql.codegenerator.Function;
import supersql.codegenerator.Manager;
import supersql.common.Log;
import supersql.extendclass.ExtList;

import org.w3c.dom.Element;

public class VRFunction extends Function {
	
	
	
//	public static Element color;
	private final int COLOR = 0;
	private final int PULSE = 1;
	private final int HOP = 2;
	private final int ROTATE = 3;
	protected static String updateFile;
	private static Element[] options = new Element[4];

	
	public static String opt(String s) {
		if (s.contains("\"")) {
			s = s.replaceAll("\"", "");
			System.out.println("s");
		}
		if (s.startsWith("./")) {
			s = s.substring(2, s.length());
		}
		if (s.startsWith("/")) {
			s = s.substring(1, s.length());
		}
		return s;
	}

	private VREnv vrEnv;
	private VREnv vrEnv2;

	public VRFunction() {

	}

	public VRFunction(Manager manager, VREnv henv, VREnv henv2) {
		super();
		this.vrEnv = henv;
		this.vrEnv2 = henv2;
	}

	protected void Func_null() {
		return;
	}

	protected String className() { // added 20130703
		if (decos.containsKey("class"))
			return " class=\"" + decos.getStr("class") + "\" ";
		return "";
	}

	// Function��work�᥽�å�
	@Override
	public String work(ExtList data_info) {
		//2017/09/21 tatsu AddFunction
		this.setDataList(data_info);
		
		String FuncName = this.getFuncName();
		
		System.out.print("data info:"+data_info);
		switch (FuncName) {
		case "cube":
			if (VRAttribute.elearrayXML.size() > VRAttribute.elearraySeq) {
				Element n2 = VRAttribute.elearrayXML.get(VRAttribute.elearraySeq);
				Element cube = vrEnv.xml.createElement("cube");
				cube.setAttribute("size", getArg(0).getStr());
				addOptions(cube);
				n2.appendChild(cube);
			} else {
				Element n2 = vrEnv.xml.createElement("n2");
				n2.setAttribute("seq", Integer.toString(VRAttribute.elearraySeq));
				Element cube = vrEnv.xml.createElement("cube");
				cube.setAttribute("size", getArg(0).getStr());
				n2.appendChild(cube);
				addOptions(cube);
				VRAttribute.elearrayXML.add(VRAttribute.elearraySeq,n2);
			}
			VRAttribute.elearraySeq++;
			break;
			
		case "torus":
			if (VRAttribute.elearrayXML.size() > VRAttribute.elearraySeq) {
				Element n2 = VRAttribute.elearrayXML.get(VRAttribute.elearraySeq);
				Element torus = vrEnv.xml.createElement("torus");
				torus.setAttribute("r1", getArg(0).getStr());
				torus.setAttribute("r2", getArg(1).getStr());
				addOptions(torus);
				n2.appendChild(torus);
			} else {
				Element n2 = vrEnv.xml.createElement("n2");
				n2.setAttribute("seq", Integer.toString(VRAttribute.elearraySeq));
				Element torus = vrEnv.xml.createElement("torus");
				torus.setAttribute("r1", getArg(0).getStr());
				torus.setAttribute("r2", getArg(1).getStr());
				n2.appendChild(torus);
				addOptions(torus);
				VRAttribute.elearrayXML.add(VRAttribute.elearraySeq,n2);
			}
			VRAttribute.elearraySeq++;
			break;
			
		case "pyramid":
			if (VRAttribute.elearrayXML.size() > VRAttribute.elearraySeq) {
				Element n2 = VRAttribute.elearrayXML.get(VRAttribute.elearraySeq);
				Element pyramid = vrEnv.xml.createElement("pyramid");
				pyramid.setAttribute("size", getArg(0).getStr());
				pyramid.setAttribute("height", getArg(1).getStr());
				addOptions(pyramid);
				n2.appendChild(pyramid);
			} else {
				Element n2 = vrEnv.xml.createElement("n2");
				n2.setAttribute("seq", Integer.toString(VRAttribute.elearraySeq));
				Element pyramid = vrEnv.xml.createElement("pyramid");
				pyramid.setAttribute("size", getArg(0).getStr());
				pyramid.setAttribute("height", getArg(1).getStr());
				n2.appendChild(pyramid);
				addOptions(pyramid);
				VRAttribute.elearrayXML.add(VRAttribute.elearraySeq,n2);
			}
			VRAttribute.elearraySeq++;
			break;
			//object内に装飾はしない
		case "cuboid":
			if (VRAttribute.elearrayXML.size() > VRAttribute.elearraySeq) {
				Element n2 = VRAttribute.elearrayXML.get(VRAttribute.elearraySeq);
				Element cuboid = vrEnv.xml.createElement("cuboid");
				cuboid.setAttribute("lSize", getArg(0).getStr());
				cuboid.setAttribute("wSize", getArg(1).getStr());
				cuboid.setAttribute("dSize", getArg(2).getStr());
				addOptions(cuboid);
				n2.appendChild(cuboid);
			} else {
				Element n2 = vrEnv.xml.createElement("n2");
				n2.setAttribute("seq", Integer.toString(VRAttribute.elearraySeq));
				Element cuboid = vrEnv.xml.createElement("cuboid");
				cuboid.setAttribute("lSize", getArg(0).getStr());
				cuboid.setAttribute("wSize", getArg(1).getStr());
				cuboid.setAttribute("dSize", getArg(2).getStr());
				n2.appendChild(cuboid);
				addOptions(cuboid);
				VRAttribute.elearrayXML.add(VRAttribute.elearraySeq,n2);
			}
			VRAttribute.elearraySeq++;
			break;
			
		case "sphere":
			if (VRAttribute.elearrayXML.size() > VRAttribute.elearraySeq) {
				Element n2 = VRAttribute.elearrayXML.get(VRAttribute.elearraySeq);
				Element sphere = vrEnv.xml.createElement("sphere");
				sphere.setAttribute("size", getArg(0).getStr());
				addOptions(sphere);
				n2.appendChild(sphere);
			} else {
				Element n2 = vrEnv.xml.createElement("n2");
				n2.setAttribute("seq", Integer.toString(VRAttribute.elearraySeq));
				Element sphere = vrEnv.xml.createElement("sphere");
				sphere.setAttribute("size", getArg(0).getStr());
				n2.appendChild(sphere);
				addOptions(sphere);
				VRAttribute.elearrayXML.add(VRAttribute.elearraySeq,n2);
			}
			VRAttribute.elearraySeq++;
			break;
			
		case "obj":
			for (int i = 0; i < this.sizeArg(); i++)
			getArg(i).workAtt();
			break;
			
		case "color":
			Element oldColor = options[COLOR];    //color(color()) block
			String colorname; 
			colorname = getArg(sizeArg()-1).getStr();
			Element color = vrEnv.xml.createElement("color");
			color.setTextContent(colorname);
			options[COLOR] = color;
			getArg(0).workAtt();
			options[COLOR] = oldColor;
			break;
			
		case "pulse":
			Element oldpulse = options[PULSE];
			String pScale = getArg(sizeArg()-2).getStr();
			String pSpeed = getArg(sizeArg()-1).getStr();
			System.out.print("pscale:"+ pScale);
			Element pulse = vrEnv.xml.createElement("pulse");
			pulse.setAttribute("speed",pScale);
			pulse.setAttribute("scale", pSpeed);
			options[PULSE] = pulse;
			getArg(0).workAtt();
			options[PULSE] = oldpulse;
			
			break;
			
		case "hop":			
			Element oldhop = options[HOP];
			String hSpeed = getArg(sizeArg()-3).getStr();
			String hTop = getArg(sizeArg()-2).getStr();
			String hAxis = getArg(sizeArg()-1).getStr();
			Element hop = vrEnv.xml.createElement("hop");
			hop.setAttribute("speed", hSpeed);
			hop.setAttribute("top", hTop);
			hop.setAttribute("axis", hAxis);
			options[HOP] = hop;
			getArg(0).workAtt();
			options[HOP] = oldhop;
			
			break;
			
		case "rotate":
			Element oldrotate = options[ROTATE];
			String rX = getArg(sizeArg()-3).getStr();
			String rY = getArg(sizeArg()-2).getStr();
			String rZ = getArg(sizeArg()-1).getStr();
			Element rotate = vrEnv.xml.createElement("rotate");
			rotate.setAttribute("x", rX);
			rotate.setAttribute("y", rY);
			rotate.setAttribute("z", rZ);
			options[ROTATE] = rotate;
			getArg(0).workAtt();
			options[ROTATE] = oldrotate;
			break;
			
			
		}
		//No functions supported in VR yet
		Log.out("TFEId = " + VREnv.getClassID(this));
//		htmlEnv.append_css_def_td(VREnv.getClassID(this), this.decos);
		return null;
	}
	
	private void addOptions(Element object){
		for (int i=0; i < options.length; i++){
		if (options[i]!= null) {
			object.appendChild(options[i]);
		}
		}
	}
}
