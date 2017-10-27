package database;

import static java.lang.Integer.parseInt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class QuestionProvider {
	static private final String filePath = "AgileGame/data/Questions.xml";
	static private boolean loaded = false;
	static private ArrayList<IQuestion> questions = new ArrayList<IQuestion>();

	static private void loadQuestion() throws ParserConfigurationException, SAXException, IOException {
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
	}

	static public ArrayList<IQuestion> getQuestions(int num) {
		if(loaded == false) {
			try {
				loadQuestion();
			} catch (ParserConfigurationException | SAXException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		ArrayList<IQuestion> ret = new ArrayList<IQuestion>();
		if(questions != null) {
			Random rnd = ThreadLocalRandom.current();
			for(int i = 0; i < questions.size() && num > 0; i++) {
				int rn = rnd.nextInt(questions.size()-i);
				if(rn < num) {
					num--;
					ret.add(questions.get(i));
				}
			}

			for (int i = ret.size() - 1; i > 0; i--) {
		      int idx = rnd.nextInt(i + 1);
		      Collections.swap(ret, i, idx);
		    }
		}
		return ret;
	}
}
