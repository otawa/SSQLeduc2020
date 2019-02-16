package supersql.codegenerator.Compiler;

import java.util.ArrayList;
import java.util.Arrays;

import supersql.common.Log;

public class Compiler_Dynamic {

	public Compiler_Dynamic() {

	}
	
//    public static void main(String args[]) {
//		Log.e(createNestWhile(null));
//	}


	public static String createNestWhile(ArrayList<Integer> dynamicAttributes_NestLevels) {
		String s = "";
		ArrayList<Integer> a = dynamicAttributes_NestLevels;
//		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(0, 0, 0));
//		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(0, 1, 1));
//		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(0, 1, 1, 2));
//		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(0, 1, 1, 0));
//		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(0, 1, 0, 1));
//		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(0, 1, 1, 0, 1));
//		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(0, 2, 1, 0, 1));		//TODO 1 -> OK
//		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(0, 2, 2, 1, 0, 1));	//TODO 2 -> OK
//		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(0, 1, 2, 2, 1, 2, 2, 0, 0));	//TODO 3	//TODO d2
//		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(0, 1, 2, 2, 1, 0, 1, 2, 0));	//TODO 4?
//		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(0, 1, 2, 2, 1, 2, 2, 0, 1, 2, 0));
//		ArrayList<Integer> a = new ArrayList<>(Arrays.asList(0, 1, 2, 2, 1, 2, 2, 0, 1, 2, 1, 1, 0));
		//Log.e(a);
		
		int old = -1;
		ArrayList<Integer> n = new ArrayList<>();
		ArrayList<ArrayList<String>> key = new ArrayList<>();
		int y = 0;
		for(int i=0; i<a.size(); i++){
			int x = a.get(i);
			if(x>old){
				try {
					y = n.get(x)+1;
					n.set(x, y);
					
					String b = key.get(x).get(y-1);
//					Log.e("b1="+b);
					//b += ((!b.isEmpty())? ", " : "")+"$row1["+i+"]";
					b += ", $row1["+i+"]";
					key.get(x).set(y-1, b);
					
				}catch(Exception e){
					y = 1;
					for(int k=x-1; k>=0; k--){	//for case: 0, 2, 1, 0
						try{
							n.get(k);
							break;
						}catch(Exception e2){
							n.add(k, 0);
						}
					}
					n.add(x, y);
					
					//Log.e(x+1+"_"+y+" "+i);
					try {
						ArrayList<String> l = new ArrayList<>();
						l.addAll(y-1, key.get(x)); 
						l.add(y-1+1, "$row1["+i+"]");
						key.set(x, l);
						
					} catch (Exception e2) {
						String b = "";
						b += "$row1["+i+"]";
						for(int k=x-1; k>=0; k--){	//for case: 0, 2, 1, 0
							try{
								key.get(k);
								break;
							}catch(Exception e3){
								n.add(k, 0);
								key.add(k, new ArrayList<String>(Arrays.asList("")));
							}
						}
						key.add(x, new ArrayList<String>(Arrays.asList(b)));
					}
				}
				//s += "$array"+(x+1)+"_"+y+" = array();\n";	//$arrayX_Y = array();
				
//			}else if(x<=old){
			}else if(x<old){
				String b = key.get(x).get(y-1);
//				Log.e("b3="+b);
				if (b.isEmpty()) {	//for case: 0, 2, 1, 0	//TODO -> OK?
					ArrayList<String> l = new ArrayList<>();
					l.add(y-1, "$row1["+i+"]");
					key.set(x, l);
					
					y = 1;
					n.add(x, y);
					
					l = new ArrayList<>();
					l.addAll(y-1, key.get(x));
					key.set(x, l);
					
				}else{
					//b += ((!b.isEmpty())? ", " : "")+"$row1["+i+"]";
					b += ", $row1["+i+"]";
					key.get(x).set(y-1, b);
				}
				
//				b += ((!b.isEmpty())? ", " : "")+"$row1["+i+"]";
//				key.get(x).set(y-1, b);
			}else if(x==old){
				String b = key.get(x).get(y-1);
//				Log.e("b2="+b);
				//b += ((!b.isEmpty())? ", " : "")+"$row1["+i+"]";
				b += ", $row1["+i+"]";
				key.get(x).set(y-1, b);
			}
			old = x;
		}
		
//		//☆
//		for(int i=0; i<key.size(); i++){
////			System.err.print(i);
////			Log.e(key.get(i).size());
//			for(int j=0; j<key.get(i).size(); j++){
//				System.err.print((i+1)+"_"+(j+1)+" : ");
//				System.err.print(key.get(i).get(j)+" ");
//			}
//			System.err.println("");
//		}
		
		
		String s0 = "$array1_1 = array();\n";
		s += "while($row1 = pg_fetch_row($result1)){\n";
		if(key.size()==1){
			//TODO ok?
			s += "	array_push($array1_1, array("+key.get(0).get(0)+"));\n";
//			s += "	$array1_1 += array(array(array("+key.get(0).get(0)+")));\n";
//			s += "	$array1_1 += array(array("+key.get(0).get(0)+"));\n";
//			s += "	$array1_1 += array("+key.get(0).get(0)+");\n";
		} else {
			try {
				for(int i=0; i<key.size()-1; i++){
					String k = key.get(i).get(0).replace(", ", ".'_'.");
					if(i>0){
						String k0 = "";
						for(int j=0; j<i; j++){
							k0 += "$key"+(j+2)+".'_'.";
						}
						k = k0+k;
					}
					s += "	$key"+(i+2)+" =  "+k+";\n" +
						 "	if(array_key_exists($key"+(i+2)+", $array"+(i+2)+"_1)){\n";
					
					for(int j=0; j<key.get(i+1).size(); j++){
						String ak = "$array"+(i+2)+"_"+(j+1)+"[$key"+(i+2)+"]";
						String av = "array("+key.get(i+1).get(j)+")";
						s += "		if(!in_array("+av+", "+ak+")){\n";
						s += "			array_push("+ak+", "+av+");\n";
						s += "		}\n";
					}
					
					s += "	}else{\n" +
						 ((i==0)? "		array_push($array1_1, array("+key.get(i).get(0)+"));\n" : "");
					
					for(int j=0; j<key.get(i+1).size(); j++){
						s += "		$array"+(i+2)+"_"+(j+1)+" += array($key"+(i+2)+" => array(array("+key.get(i+1).get(j)+")));\n";
						s0 += "$array"+(i+2)+"_"+(j+1)+" = array();\n";
					}
					
					s += "	}\n";
				}
			} catch (Exception e) {
				//e.printStackTrace();
			}	
		}
		s += "}\n\n";

		s = s0+s;
		
		
		
		
		
//		ArrayList<ArrayList<String>> key = new ArrayList<>();
//		for(int i=0; i<a.size(); i++){
//			int x = a.get(i);
//			try {
//				key.set(x, key.get(x)+", $row1["+i+"]");
//			}catch(Exception e){
//				key.add(x, "$row1["+i+"]");
//			}
//		}
		
		
//		Log.e(s);
		
//	    /*	[ d.name,  [e.id, e.name]! ,   d.name,   [e.name]!   ]! */
//             0          1	   1              0          1   
//	    $array1_1 = array();
//	    $array2_1 = array();
//	    $array2_2 = array();
//	    /*	[ d.name,  [e.id, [a, b]! , e.name]! ,   d.name,   [e.name, [x]!]! ,  y   ]! */
//             0          1	   2  2       1              0         1     2        0
//	    $array1_1 = array();
//	    $array2_1 = array();
//	    $array3_1 = array();
//	    $array2_2 = array();
//	    $array3_2 = array();
//	    /*	[ d.name,  [e.id, [a, b]! , e.name, [a, b]!]! ,   d.name,   [e.name, [x]!]!,  [x]! , [x]! ,  y   ]! */
//             0          1	   2  2       1      2  2           0         1       2        1      1      0
//	    $array1_1 = array();
//	    $array2_1 = array();
//	    $array3_1 = array();
//	    $array3_2 = array();
//	    $array2_2 = array();
//	    $array3_3 = array();
		
		
		
//	    /*	[ d.name,  [e.id, e.name]!    ]! */
//		/*      0         1      1           */ 
//	    
//	    $array1_1 = array();
//	    $array2_1 = array();
		
//	    while($row1 = pg_fetch_row($result1)){
//	    	$key2 = $row1[0];
//	    	if(array_key_exists($key2, $array2_1)){
//	    		array_push($array2_1[$key2], array($row1[1], $row1[2]));
//	    	}else{
//	    		array_push($array1_1, array($row1[0]));
//	    	    $array2_1 += array($key2 => array(array($row1[1], $row1[2])));
//	    	}
//	    }
		
		
		
//		ArrayList<Integer> a = new ArrayList<>();
//		a.add(1);
//		a.add(1);
//		ArrayList<ArrayList<Integer>> b = new ArrayList<>();
//		b.add(new ArrayList<Integer>(Arrays.asList(0)));
//		b.add(new ArrayList<Integer>(Arrays.asList(1, 2)));
//		
//		String s = "";
//		
//		for(int i=0; i<a.size(); i++){
//			int x = a.get(i);
//			for(int j=0; j<x; j++){
//				s += "$array"+(i+1)+"_"+(j+1)+" = array();\n";
//			}
//		}
//		s += "while($row1 = pg_fetch_row($result1)){\n";
//		
//		String key[] = new String[b.size()];
//		String array_push[] = new String[b.size()];
//		Arrays.fill(key, "");
//		Arrays.fill(array_push, "");
//		for(int i=0; i<b.size(); i++){
//			ArrayList<Integer> x = b.get(i);
//			for(int j=0; j<x.size(); j++){
//				key[i] += "$row1["+x.get(j)+"], ";
//			}
//			array_push[i] += "		array_push($array"+(i+1)+"_"+(j+1)+" = array();\n";
//			key[i] = key[i].substring(0, key[i].lastIndexOf(", "));
//		}
//		
//		int nl = 2;
//		s += "	$key"+nl+" = "+key[0]+"\n";
//		s += "	if(array_key_exists($key"+nl+", $array"+nl+"_1)){\n";
//		for(int i=0; i<a.size(); i++){
//			int x = a.get(i);
//			for(int j=0; j<x; j++){
//				s += "		array_push($array"+(i+1)+"_"+(j+1)+" = array();\n";
//			}
//		}
		
		
//		//0, 1, 1, 0, 1
//		
//		ArrayList<String> key = new ArrayList<>();
//		for(int i=0; i<a.size(); i++){
//			int x = a.get(i);
//			try {
//				key.set(x, key.get(x)+", $row1["+i+"]");
//			}catch(Exception e){
//				key.add(x, "$row1["+i+"]");
//			}
//		}
//		for(String x:key){
//			Log.e(x);
//		}
		

		
		
//	    /*	[ d.name,  [e.id, e.name, [f.id]!]!    ]! */
//		/*      0         1      1      2      */ 
//		        a         b1     c1     d1
//		        a         b1     c1     d2
//		        a         b2     c2     d3
//		        b         b1     c1     d4
//		        b         b1     c1     d5
//		        b         b2     c2     d6
//	    
//	    $array1_1 = array();
//	    $array2_1 = array();
//	    $array3_1 = array();
//	    while($row1 = pg_fetch_row($result1)){
//	    	$key2 = $row1[0];
//	    	if(array_key_exists($key2, $array2_1)){
//	    		array_push($array2_1[$key2], array($row1[1], $row1[2]));
//	    	}else{
//	    		array_push($array1_1, array($key2));
//	    	    $array2_1 += array($key2 => array(array($row1[1], $row1[2])));
//	    	}
//	    	$key3 = $key2.'_'.$row1[1].'_'.$row1[2];
//	    	if(array_key_exists($key3, $array3_1)){
//	    		array_push($array3_1[$key3], array($row1[3]));
//	    	}else{
//	    	    $array3_1 += array($key3 => array(array($row1[3])));
//	    	}
//	    }

		
		
//	    /*	[ d.name,  [e.id, e.name]! ,   d.name,   [e.name]!   ]! */
//	    
//	    $array1 = array();
//	    $array2_1 = array();
//	    $array2_2 = array();
//	    while($row1 = pg_fetch_row($result1)){
//	    	$key2 = $row1[0].'_'.$row1[3];
//	    	if(array_key_exists($key2, $array2_1)){
//	    		array_push($array2_1[$key2], array($row1[1], $row1[2]));
//	    		array_push($array2_2[$key2], array($row1[4]));
//	    	}else{
//	    		array_push($array1, array($row1[0], $row1[3]));
//	    	    $array2_1 += array($key2 => array(array($row1[1], $row1[2])));
//	    		$array2_2 += array($key2 => array(array($row1[4])));
//	    	}
//	    }
		return s;
	}
}
