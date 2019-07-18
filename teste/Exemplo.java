package teste;

import java.io.File;
import java.io.IOException;

import java.io.StringReader;

 

import javax.xml.parsers.DocumentBuilder;

import javax.xml.parsers.DocumentBuilderFactory;

 

import org.w3c.dom.Document;

import org.w3c.dom.Element;

import org.w3c.dom.Node;

import org.w3c.dom.NodeList;

import org.xml.sax.InputSource;

 

import com.sun.org.apache.xml.internal.serialize.OutputFormat;

import com.sun.org.apache.xml.internal.serialize.XMLSerializer;

import com.sun.org.apache.xpath.internal.XPathAPI;

 

public class Exemplo {

 

      public static void main(String[] args) throws Exception {
            DocumentBuilderFactory dbf = 
                                DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = dbf.newDocumentBuilder();
            Document doc = docBuilder.parse(new File("arquivo.xml"));
            
            // mudando o valor de 'title'
            Element htmlTag = doc.getDocumentElement();
            Element headTag = 
                       (Element) htmlTag.getElementsByTagName("head").item(0);
            Element titleTag = 
                       (Element) headTag.getElementsByTagName("title").item(0);
            titleTag.setTextContent("Novo título");

            // adicionando mais parágrafos em 'body'
            Element p1Tag = doc.createElement("p");
            p1Tag.setAttribute("class", "black");
            p1Tag.setTextContent("Um novo parágrafo... ;)");
            
            Element p2Tag = doc.createElement("p");
            p2Tag.setAttribute("class", "white");
            p2Tag.setTextContent("Outro parágrafo..");
       
            Element p3Tag = doc.createElement("p");
            p3Tag.setAttribute("class", "black");
            p3Tag.setTextContent("Fim !");
          
            Element bodyTag = 
                        (Element) htmlTag.getElementsByTagName("body").item(0);
            bodyTag.appendChild(p1Tag);
            bodyTag.appendChild(p2Tag);
            bodyTag.appendChild(p3Tag);
           
            // removendo atributo 'onload'
            bodyTag.removeAttribute("onload");
           
            // removendo o primeiro parágrafo de 'body'
            NodeList pTags = bodyTag.getElementsByTagName("p");
            Node p1 = pTags.item(0);
            bodyTag.removeChild(p1);
        
            // fazendo uma consulta XPath
            NodeList resultado = XPathAPI.selectNodeList(bodyTag, 
                                                         "p[@class = 'black']");
            for (int i = 0; i < resultado.getLength(); i++) {
                  System.out.println("?: " + 
                                     resultado.item(i).getTextContent());
            }
     }
}

