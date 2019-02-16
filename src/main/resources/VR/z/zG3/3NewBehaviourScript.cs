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
	print(elem.Name); //doc

		if (elem.HasChildNodes == true) {
	        XmlNode childNode = elem.FirstChild;

	        while (childNode != null) {
	        	print(childNode.Name); //category
	        	print("aaa");
	        

	          	if (childNode.HasChildNodes == true) {
		            for (int i=0; i < childNode.ChildNodes.Count; i++) {
		              	XmlNode dataNode= childNode.ChildNodes[i];
		              	print(dataNode.Name); //Shape
		              	print("bbb");
		              	print(dataNode.ChildNodes.Count); //オブジェクトの数
		              	   for (int j=0; j < dataNode.ChildNodes.Count; j++) { //一個一個kindを見てる
			                	XmlNode xmlAttr = dataNode.ChildNodes[j]; //xmlAttrはkindCubekind
			                	print(xmlAttr.Name); //kind
			                	print(xmlAttr.InnerText); //Cubeとか
			                	print("ccc");
								array[j] = GameObject.Find(xmlAttr.InnerText);
								array[j].transform.position  = new Vector3 (0, 0, j);//移動してる
							}

		                if (dataNode.HasChildNodes == true) {
		                XmlNode valueNode = dataNode.ChildNodes[0];
		                //print(valueNode.Name);
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
