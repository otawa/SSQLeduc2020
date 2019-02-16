package supersql.codegenerator.X3D;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import supersql.codegenerator.ITFE;
import supersql.codegenerator.Manager;
import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class X3DManager extends Manager{

    X3DEnv x3d_env;

    public X3DManager(X3DEnv lenv) {
        this.x3d_env = lenv;
    }

    @Override
	public void generateCode(ITFE tfe_info, ExtList data_info) {

        x3d_env.countfile = 0;
        x3d_env.code = new StringBuffer();
        x3d_env.css = new StringBuffer();
        x3d_env.header = new StringBuffer();
        x3d_env.footer = new StringBuffer();
        getOutfilename();

        Log.out("[X3DManager:generateCode]");

        if (tfe_info instanceof X3DG3) {
            tfe_info.work(data_info);
            return;
        }

        x3d_env.filename = x3d_env.outfile + ".x3dv";
        
        tfe_info.work(data_info);

        x3d_env.getHeader();
        x3d_env.getFooter();
        try {
            PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(x3d_env.filename)));
            pw.println(x3d_env.header);
            pw.println(x3d_env.code);
            pw.println(x3d_env.footer);
            pw.close();
        } catch (FileNotFoundException fe) {
            System.err.println("Error: specified outdirectory \""
                    + x3d_env.outdir + "\" is not found");
            System.exit(-1);
        } catch (IOException e) {
            System.err.println("Error[X3DManager]: File IO Error in X3DManager");
            e.printStackTrace();
            System.exit(-1);
        }

    }

    private void getOutfilename() {
        String file = GlobalEnv.getfilename();
        String outdir = GlobalEnv.getoutdirectory();
        String outfile = GlobalEnv.getoutfilename();
        x3d_env.outdir = outdir;

        if (outfile == null) {
        	if (file.indexOf(".sql")>0) {
        		x3d_env.outfile = file.substring(0, file.indexOf(".sql"));
        	} else if (file.indexOf(".ssql")>0) {
        		x3d_env.outfile = file.substring(0, file.indexOf(".ssql"));
        	}
        } else {
            x3d_env.outfile = getOutfile(outfile);
        }

        if (x3d_env.outfile.indexOf("/") > 0) {
            x3d_env.linkoutfile = x3d_env.outfile.substring(x3d_env.outfile
                    .lastIndexOf("/") + 1);
        } else {
            x3d_env.linkoutfile = x3d_env.outfile;
        }

        if (outdir != null) {
            connectOutdir(outdir, outfile);
        }
    }

    private String getOutfile(String outfile) {
        String out = new String();
        if (outfile.indexOf(".x3dv") > 0) {
            out = outfile.substring(0, outfile.indexOf(".x3dv"));
        } else {
            out = outfile;
        }
        return out;
    }

    private void connectOutdir(String outdir, String outfile) {
        String tmpqueryfile = new String();
        if (x3d_env.outfile.indexOf("/") > 0) {
            if (outfile != null) {
                if (x3d_env.outfile.startsWith(".")
                        || x3d_env.outfile.startsWith("/")) {
                    tmpqueryfile = x3d_env.outfile.substring(x3d_env.outfile
                            .indexOf("/") + 1);
                }
            } else {
                tmpqueryfile = x3d_env.outfile.substring(x3d_env.outfile
                        .lastIndexOf("/") + 1);
            }
        } else {
            tmpqueryfile = x3d_env.outfile;
        }
        if (!outdir.endsWith("/")) {
            outdir = outdir.concat("/");
        }
        x3d_env.outfile = outdir.concat(tmpqueryfile);
    }

    @Override
	public void finish() {
    }
}
