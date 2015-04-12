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

import java.util.ArrayList;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/**
 *
 * @author Hannes Hassler
 */
public class GenericArchive {
    public Document document;
    public ArrayList<Element> documentArr = new ArrayList<Element>(); 
    public String documentPathFile;
    public int currElementOfDocument;
    
    @Override
    public String toString() {
		Format format=Format.getPrettyFormat();
                XMLOutputter xmlOutputter = new XMLOutputter();
                xmlOutputter.setFormat(format);
        return xmlOutputter.outputString(document); 
     }
    
    public Element getCurrentElement() {
        Element returnElement=null;
        try {
           returnElement=documentArr.get(currElementOfDocument); 
        } catch (Exception ex) {
            //return null
        }
        return returnElement;
    }
}
