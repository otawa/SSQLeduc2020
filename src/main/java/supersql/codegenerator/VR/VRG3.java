package supersql.codegenerator.VR;

import supersql.codegenerator.Grouper;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class VRG3 extends Grouper {

	private VREnv vrEnv;
	private VREnv vr_env2;
	boolean retFlag = false;	// 20140611_masato pagenationフラグ

	public VRG3(Manager manager, VREnv henv, VREnv henv2) {
		this.vrEnv = henv;
		this.vr_env2 = henv2;
	}


	@Override
	public String getSymbol() {
		return "VRG3";
	}

	@Override
	public String work(ExtList data_info) {
		Log.out("------- G3 -------");
		if(vrEnv.gLevel == 0){
			vrEnv.currentNode = vrEnv.currentNode.appendChild(vrEnv.xml.createElement("group"));
		}
		
		int i = 0;			// 20140526_masato
		int j = 0;			// 20140526_masato
		int k = 0;	
		if (decos.containsKey("vr_x")) {
			i = Integer.parseInt(decos.getStr("vr_x"));
			retFlag = true;
			if(!VRAttribute.componexflag){
				VRAttribute.compx[VRAttribute.cgcount] = i;
				VRAttribute.compflag[VRAttribute.cgcount] = 3;
			}
			VRAttribute.componexflag = true;
		}
		if (decos.containsKey("vr_y")) {///column->row_x, row->vr_y
			j = Integer.parseInt(decos.getStr("vr_y"));
			retFlag = true;
			if(!VRAttribute.componeyflag){
				VRAttribute.compy[VRAttribute.cgcount] = j;
				VRAttribute.compflag[VRAttribute.cgcount] = 3;
			}
			VRAttribute.componeyflag = true;
		}		
		if (decos.containsKey("vr_z")) {
			k = Integer.parseInt(decos.getStr("vr_z"));
			retFlag = true;
			if(!VRAttribute.componezflag){
				VRAttribute.compz[VRAttribute.cgcount] = k;
				VRAttribute.compflag[VRAttribute.cgcount] = 3;
			}
			VRAttribute.componezflag = true;
		}

		this.setDataList(data_info);

		if(vrEnv.gLevel == 0) {
			VRAttribute.floorarray.add(3);
		} else if(vrEnv.gLevel == 1) {
			VRAttribute.exharray.add(3);
		}

		VRAttribute.gjudge++;

		while (this.hasMoreItems()==true) {
			//////////////////////////G22//////////////////////////
			VRAttribute.genre = "";

			VRAttribute.elearraySeq = 0;//n2 kotani

			try {
				int l=VRManager.gindex.get(vrEnv.gLevel);
				VRManager.gindex.set(vrEnv.gLevel,l+1);//gindex[]++
			} catch (Exception e) {
				VRManager.gindex.add(1);	//gindex[]=1
			}

			vrEnv.gLevel++;
			Log.out("selectFlg" + VREnv.getSelectFlg());
			Log.out("selectRepeatFlg" + VREnv.getSelectRepeat());
			Log.out("formItemFlg" + VREnv.getFormItemFlg());

			String classid = VREnv.getClassID(tfe);

			//TODO: check this has nothing to do with vr
			if (GlobalEnv.isOpt() && !VREnv.getSelectRepeat()) {
				vr_env2.code.append("<tfe type=\"repeat\" dimension=\"2\"");
				vr_env2.code.append(" border=\"" + vrEnv.tableBorder
						+ "\"");

				if (decos.containsKey("tablealign"))
					vr_env2.code.append(" align=\""
							+ decos.getStr("tablealign") + "\"");
				if (decos.containsKey("tablevalign"))
					vr_env2.code.append(" valign=\""
							+ decos.getStr("tablevalign") + "\"");

				if (decos.containsKey("class")) {
					// class=menu�Ȃǂ̎w�肪��������t��
					vr_env2.code.append(" class=\"");
					vr_env2.code.append(decos.getStr("class") + " ");
				}
				if (vrEnv.writtenClassId.contains(VREnv.getClassID(this))) {
					// TFE10000�Ȃǂ̎w�肪��������t��
					if (decos.containsKey("class")) {
						vr_env2.code.append(VREnv.getClassID(this) + "\"");
					} else {
						vr_env2.code.append(" class=\""
								+ VREnv.getClassID(this) + "\"");
					}
				} else if (decos.containsKey("class")) {
					vr_env2.code.append("\"");
				}

				if (decos.containsKey("tabletype")) {
					vr_env2.code.append(" tabletype=\""
							+ decos.getStr("tabletype") + "\"");
					if (decos.containsKey("cellspacing")) {
						vr_env2.code.append(" cellspacing=\""
								+ decos.getStr("cellspacing") + "\"");
					}
					if (decos.containsKey("cellpadding")) {
						vr_env2.code.append(" cellpadding=\""
								+ decos.getStr("cellpadding") + "\"");
					}
				}
				vr_env2.code.append(">");
			}

			this.worknextItem();

			//TODO: check what this does
			if (vrEnv.notWrittenClassId.contains(classid)
					&& vrEnv.code.indexOf(classid) >= 0) {
				vrEnv.code.delete(vrEnv.code.indexOf(classid),
						vrEnv.code.indexOf(classid) + classid.length() + 1);
			}

			vrEnv.gLevel--;	
		}
		VRManager.gindex.set(vrEnv.gLevel, 0);
		if(vrEnv.gLevel == 0){
			VRManager.nest1count++;
		}

		for(int l=0; l<VRAttribute.elearrayXML.size();l++){///n2 kotani		
			vrEnv.currentNode.appendChild(VRAttribute.elearrayXML.get(l));
		}
		VRAttribute.elearrayXML.clear();//初期化
		VRAttribute.elearraySeq = 0;//初期化

		if(VRAttribute.gjudge==1){
			VRAttribute.billnum++;
		}
		VRAttribute.gjudge--;

		/////////////////////////G22end//////////////////////

		if(vrEnv.gLevel == 0){
			VRAttribute.componexflag = false;
			VRAttribute.componeyflag = false;
			VRAttribute.componezflag = false;
			VRAttribute.cgcount++;

			vrEnv.currentNode = vrEnv.currentNode.getParentNode();
			VRAttribute.grouptag++;
			VRAttribute.genrearray22.add(VRAttribute.genrecount);
		}

		return null;

	}
}
