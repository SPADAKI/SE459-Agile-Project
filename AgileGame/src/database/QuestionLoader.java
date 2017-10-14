package database;

import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.IOException;

import static java.lang.Integer.parseInt;

public class QuestionLoader {
	static private final String filePath = "data/Questions.xml";
	static private boolean loaded = false;
	static private ArrayList<IQuestion> questions = new ArrayList<IQuestion>();

	static public ArrayList<IQuestion> loadQuestion() throws ParserConfigurationException, SAXException, IOException {
		if(loaded == false) {
			File file =  new File(filePath);
	        if (file.exists()) {
	        	DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	        	// Create the parser
		        DocumentBuilder db = dbf.newDocumentBuilder();
		        //Create a Document from a file
		        Document xml = db.parse(file);
		        xml.getDocumentElement().normalize();
		        //parse data
	            Element root = xml.getDocumentElement();
	            if (root.getNodeName().equals("Questions")) {
	            	NodeList questionsList = root.getElementsByTagName("Question");
	            	for (int i = 0; i < questionsList.getLength(); i++) {
	                    Element eleQue = (Element) questionsList.item(i);
	                    int qid = parseInt(eleQue.getAttribute("Id"));
	                    String text = eleQue.getElementsByTagName("Text").item(0).getTextContent();
	                    int ans = parseInt(eleQue.getElementsByTagName("Answer").item(0).getTextContent());
	                    NodeList optionsList = eleQue.getElementsByTagName("Option");
	                    String[] opts = new String[optionsList.getLength()];
	                    for(int j = 0; j < optionsList.getLength(); j++) {
	                    	Element eleOpt = (Element) optionsList.item(j);
	                    	int optIdx = parseInt(eleOpt.getAttribute("Idx"));
	                    	opts[optIdx] = eleOpt.getTextContent();
	                    }
	                    SingleChoiceQuestion que = new SingleChoiceQuestion(qid, text, opts, ans);
	                    questions.add(que);
	                }
	            }
	        }
	        loaded = true;
		}
		return questions;
	}
}
