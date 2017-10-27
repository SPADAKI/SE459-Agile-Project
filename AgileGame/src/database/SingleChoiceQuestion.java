package database;

import database.IQuestion;

public class SingleChoiceQuestion implements IQuestion {
	private final int idx; //question number
	private final String question; //question content
	private final String[] options; //answers array 
	private final IQuestion.Option answer; //correct answer index 

	public SingleChoiceQuestion(int i, String q, String[] opts, int ans) {
		idx = i;
		question = q;
		options = opts;
		answer = IQuestion.Option.fromInt(ans);
	}

	public int getId() {
		return idx;
	}

	public String getQuestion() {
		return question;
	}

	public String getOption(Option opt) {
		int idx = opt.getVal();
		if(idx < options.length) return options[opt.getVal()];
		else return "";
	}

	public Option getAnswer() {
		return answer;
	}

	public void dumpQuestion() {
		System.out.println("Question "+idx+": "+question);
		for(int i = 0; i < options.length; i++) {
			System.out.println("  "+Option.fromInt(i)+". "+options[i]);
		}
		System.out.println("Answer: "+answer);
		
//		String[] array = {"element1","element2","element3"};
//        SingleChoiceQuestion q = new SingleChoiceQuestion(1, "sample1", array, 1);
//        q.dumpQuestion();
		
//		print sample
//		Question 1: sample1
//		  A. element1
//		  B. element2
//		  C. element3
//		Answer: B
	}
}
