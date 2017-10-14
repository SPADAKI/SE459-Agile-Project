package database;

public interface IQuestion {
	public enum Option {
		A(0), B(1), C(2), D(3);

		private final int value;
		private Option(int v) {
			this.value = v;
		}

		public static Option fromInt(int v) {
			switch(v) {
			case 0: return A;
			case 1: return B;
			case 2: return C;
			case 3: return D;
			}
			return A;
		}

		public int getVal() {
			return this.value;
		}
	}

	public int getId();

	public String getQuestion();

	public String getOption(Option opt);

	public Option getAnswer();

	public void dumpQuestion();
}
