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
		return options[opt.getVal()];
	}

	public Option getAnswer() {
		return answer;
	}
}