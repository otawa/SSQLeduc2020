using System;
//using System.Collections.Generic;
using System.ComponentModel;
//using System.Data;
//using System.Drawing;
using System.Linq;
using System.Text;
//using System.Windows.Forms;
using System.Xml;
using UnityEngine;
using System.Collections;

public class NewBehaviourScript : MonoBehaviour {

	// Use this for initialization
	void Start () {
	GameObject[] array = new GameObject[100];
	String[] sarray = new String[100];///////////////////テキスト生成


	//座標の値をあらかじめ入れておく
	int[] xarray = new int[100];
	int[] xxarray = new int[100];/////////////G1 change
	int[] zarray = new int[100];
	int r;
	float objhigh = 3.0f;
	float standhigh = 1.25f;
	int museumcount = 0;




	for(int n=10,k=0; n >= -10; n = n-5,k++){///////////////ここら辺G1 change
		zarray[k] = n;
	}

	for(int m=20, l=0; m >= -20; m = m-5,l++){	
		xxarray[l] = m;
	}
	for(int p=0, q=0; p<50; p++,q++){    	
		if(q == 9){
			q=0;
		}
		xarray[p] = xxarray[q];	
	}   

	 	

	XmlDocument xmlDocument = new XmlDocument();
	xmlDocument.Load("a4.xml");
	XmlElement elem = xmlDocument.DocumentElement; //elem.Nameはdoc
	

		if (elem.HasChildNodes == true) {
	        XmlNode childNode = elem.FirstChild;

	        while (childNode != null) { //childNode.Nameはcategory
	        	museumcount++;
	        	
	          	if (childNode.HasChildNodes == true) {
		            for (int i=0; i < childNode.ChildNodes.Count; i++) {
		              	XmlNode dataNode= childNode.ChildNodes[i]; //dataNode.NameはShape

				            for (int j=0; j < dataNode.ChildNodes.Count; j++) {      	   
			                	XmlNode xmlAttr = dataNode.ChildNodes[j]; //xmlAttrはkindCubekind
								array[j] = GameObject.Find(xmlAttr.InnerText);//オブジェクト一個一個の場所移動
								sarray[j] = xmlAttr.InnerText;//オブジェクトのテキスト生成のため
								r = j/9;//////////////G1 change
								if(j == 0){ //xmAttr.Nameはkind, xml.InnerTextはCubeとか
									r = 0;
								}
								
								array[j].transform.position  = new Vector3 (xarray[j], objhigh, zarray[r]);////////////G1 change
								//stand生成
								GameObject stand = Instantiate(Resources.Load("Prefab/Stand")) as GameObject;
								stand.transform.position= new Vector3(xarray[j], standhigh, zarray[r]); /////////////G1 change
								
								//オブジェクトのテキスト生成
								GameObject  messageText = Instantiate(Resources.Load("Prefab/TextPrefab")) as GameObject;
								messageText.GetComponent<TextMesh>().text = sarray[j].ToString();
								messageText.transform.Rotate(0,180,0);
								messageText.transform.position= new Vector3(xarray[j]+1.0f, standhigh+0.9f, zarray[r]); 
								messageText.transform.localScale = new Vector3(0.5f, 0.5f, 0.5f);
								//print(array[j]);
								//print(xarray[r]);
								//print(zarray[j]);

									//オブジェクトを縮小or拡大、展示用
								float nx = array[j].transform.lossyScale.x;
								float ny = array[j].transform.lossyScale.y;
								float nz = array[j].transform.lossyScale.z;

								float max = nx;
								if(max < ny){
									max = ny;
								}

								if(max < nz){
									max = nz;
								}
								max = max*1.5f;
								array[j].transform.localScale = new Vector3(nx/max, ny/max, nz/max);
							}
						
		                if (dataNode.HasChildNodes == true) {
		                XmlNode valueNode = dataNode.ChildNodes[0];
		            	}
		            }
	            }
	            childNode = childNode.NextSibling;
	            objhigh += 20.0f;
	            standhigh += 20.0f;
	        }	
	    }
	    //museum生成
		for(int i=0; i<museumcount; i++){	
			GameObject museum= Instantiate(Resources.Load("Prefab/Museum")) as GameObject;
			museum.transform.position= new Vector3(0, i*20, 0);  
		}
	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
