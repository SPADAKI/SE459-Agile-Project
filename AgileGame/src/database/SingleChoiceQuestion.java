package database;

import database.IQuestion;

public class SingleChoiceQuestion implements IQuestion {
	private final int idx;
	private final String question;
	private final String[] options;
	private final IQuestion.Option answer;

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
	}
}
