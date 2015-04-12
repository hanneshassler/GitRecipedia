/*
 * Copyright (C) 2015  Hannes Hassler
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package rezeptsuperpos;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import org.jdom2.*;
import org.jdom2.input.*;

/**
 *
 * @author Hannes Hassler
 */
public class JDom {
    
		
	public Document readXML(String filename) throws JDOMException, IOException {
		 
		  SAXBuilder builder = new SAXBuilder();
		  File xmlFile = new File(filename);
		  Document document = null;	 		  
		  document = (Document) builder.build(xmlFile);	
		  return document;
		}
	
	
	public Element getElement(Document document, String[] pathFromRoot) {
		Element nextElement = document.getRootElement();		
		for (String elementName : pathFromRoot) {
			nextElement = nextElement.getChild(elementName);
		}
		return nextElement;
	}
	
	public void listElements(Element topElement, String listName, String[] listElemName) {
		
		List<Element> list = topElement.getChildren(listName);		 
		for (int i = 0; i < list.size(); i++) { 
		   System.out.println("SubElement "+i);
		   Element node = (Element) list.get(i); 
		   for (String nodeChildName : listElemName) {
			   System.out.println("nodeChildName : " + node.getChildText(nodeChildName));
		   }		   
		}
		
	}
	
	public void saveElements2File(@SuppressWarnings("rawtypes") ArrayList list, String path, String filename) {		
		PrintWriter writer=null;
		PrintWriter writerCSV=null;
		try {
			writer = new PrintWriter(path+"/"+filename, "UTF-8");
			writerCSV = new PrintWriter(path+"/"+filename+".csv", "UTF-8");
			
		} catch (FileNotFoundException e) {			
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {			
			e.printStackTrace();
		}
		writer.println("<?xml version=\"1.0\"?>");
		writer.println("<root>");
		//writerCSV.println(Film.csvHeader());
		for (int itemIdx=0; itemIdx<list.size(); itemIdx++) {
			writer.println(list.get(itemIdx));
			//writerCSV.println(((Film)list.get(itemIdx)).toCSV());
		}
		writer.println("</root>");
		writer.close();
		writerCSV.close();   
    }
    
}
