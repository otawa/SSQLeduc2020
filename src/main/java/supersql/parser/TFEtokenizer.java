package supersql.parser;


import java.io.Serializable;
import java.util.StringTokenizer;

import supersql.common.GlobalEnv;
import supersql.common.Log;
import supersql.extendclass.ExtList;

public class TFEtokenizer implements Serializable {

	private StringTokenizer st;

	private ExtList st_list;

	private int ind;

    public TFEtokenizer(String strs) {

        st = new StringTokenizer(strs, "\t[]{}()+?,!%#@'\\\"", true);

        st_list = new ExtList();

        String buffer = new String();

        try {
            while (st.hasMoreTokens()) {
                buffer = this.next(buffer);
                
            }
        } catch (IllegalStateException e) {
        	Log.err("Error[TFEtokenizer]: Syntax Error in TFE");
            //tk////////////////////////////////////////////////////
            GlobalEnv.addErr("Error[TFEparser]: Syntax Error in TFE");
 //           return ;
        	//System.exit(-1);
            //tk////////////////////////////////////////////////////

        }
        this.push(buffer);

        ind = 0;

    }

    public boolean hasMoreTokens(int i) {

        if (ind + i <= st_list.size()) {
            return true;
        } else {
            return false;
        }

    }

    public boolean hasMoreTokens() {

        return this.hasMoreTokens(1);

    }

    public int countTokens() {

        return st_list.size() - ind;

    }

    public String nextToken() {
        if (ind < st_list.size()) {
        	String ret = (String) (st_list.get(ind++));
//        	TFEmatcher.tokenCounter(ret, ind);	//halken TFEmatcher
            return ret;
        } else {
            ind++;
            return "";
        }
    }
//    public String nextToken() {
//    	if (ind < st_list.size()) {
//    		return (String) (st_list.get(ind++));
//    	} else {
//    		ind++;
//    		return "";
//    	}
//    }

    public String lookToken() {
        if (ind < st_list.size()) {
            return (String) (st_list.get(ind));
        } else {
            return "";
        }
    }

    public String prevToken() {
        if (ind == 0) {
            return "";
        } else {
            return (String) (st_list.get(--ind));
        }
    }

    private String next(String buffer) {

        if (!st.hasMoreTokens()) {
            this.push(buffer);
            return new String();
        }

        String delimitor = new String("[]{}()+?,!%#@\\");
        String ch = st.nextToken("\t[]{}()+?,!%#@\\'\"");

        //added by goto
        if(!ch.contains(" as "))
	        //(chie)
	        ch = ch.replaceAll(" ","");
        

        if (ch.equals(" ") || ch.equals("\t"))
            return buffer + ch;
        if (delimitor.indexOf(ch) != -1) {
            this.push(buffer);
            this.push(ch);
            return new String();
        }
        if (ch.equals("\\"))
            return buffer + ch + st.nextToken("\t[]{}()+?,!%#@\\'");
        if (ch.equals("'"))
            return buffer + this.quoted();
        if (ch.equals("\""))
            return buffer + this.dquoted();
        return buffer + ch;

    }

    private void push(String token) {
        String item = token.trim();
        if (!item.equals(""))
            st_list.add(item);
    }

    private String quoted() {

        String ret_token = "";

        while (st.hasMoreTokens()) {
            String ch = st.nextToken("\\'");
            if (ch.equals("'"))
                return "\'" + ret_token + "\'";
            if (ch.equals("\\"))
                ret_token = ret_token + ch + st.nextToken();
            
            ret_token = ret_token + ch;
        }
        Log.err("*** No corresponding quote \"'\" Found ***");
        throw (new IllegalStateException());
    }

    private String dquoted() {

        String ret_token = "";

        while (st.hasMoreTokens()) {
        	//tk //////////////////////////////////////////////
        	// to avoid like c:\SSQL\\demo\\test.sql
        	
            String ch = st.nextToken("\"");
//            String ch = st.nextToken("\\\"");
            //tk///////////////////////////////////////////////
            if (ch.equals("\""))
                return "\"" + ret_token + "\"";
            if (ch.equals("\\"))
                ret_token = ret_token + ch + st.nextToken();
            ret_token = ret_token + ch;
        }
        Log.err("*** No corresponding quote '\"' Found ***");
        throw (new IllegalStateException());
    }

    public String DebugTrace() {
        String str = "";
        for (int i = 0; i < st_list.size(); i++) {
            if (i == ind - 1) {
                str += " >>>> " + st_list.get(i) + " <<<< ";
            } else {
                str += st_list.get(i);
            }
        }
        return str;
    }

}
