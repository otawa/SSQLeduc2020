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

	XmlDocument xmlDocument = new XmlDocument();
	xmlDocument.Load("sample2.xml");
	XmlElement elem = xmlDocument.DocumentElement;
	//print(elem.Name);

		if (elem.HasChildNodes == true) {
	        XmlNode childNode = elem.FirstChild;

	        while (childNode != null) {
	        	
	          	if (childNode.HasChildNodes == true) {
		            for (int i=0; i < childNode.ChildNodes.Count; i++) {
		              XmlNode dataNode= childNode.ChildNodes[i];
		              
		              	   for (int j=0; j < dataNode.ChildNodes.Count; j++) {
		                		XmlNode xmlAttr = dataNode.ChildNodes[j];
								array[j] = GameObject.Find(xmlAttr.InnerText);//オブジェクト一個一個の場所移動
								array[j].transform.position  = new Vector3 (0, 0, j);
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

								array[j].transform.localScale = new Vector3(nx/max, ny/max, nz/max);
							}

		                if (dataNode.HasChildNodes == true) {
		                XmlNode valueNode = dataNode.ChildNodes[0];
		            	}
		            }
	            }
	            childNode = childNode.NextSibling;
	        }	
	    }


	}
	
	// Update is called once per frame
	void Update () {
	
	}
}
