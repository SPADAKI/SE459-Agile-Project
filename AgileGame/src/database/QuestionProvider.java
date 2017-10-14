package database;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class QuestionProvider {
	static public ArrayList<IQuestion> getQuestions(int num) {
		ArrayList<IQuestion> questions = null;
		try {
			questions = QuestionLoader.loadQuestion();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
