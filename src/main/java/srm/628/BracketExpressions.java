import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * Recognize the grammar <br/>
 * BE := EMPTY-STRING | ( BE RC BE | [ BE SC BE | { BE CC BE |
 * X BE JC BE 
 * 
 * CC := } | X
 * 
 * RC := ) | X
 * 
 * SC := ] | X
 * 
 * JC := ) | ] | } | X
 */
/** The elements of the grammar */
enum Tokens {
	BRACKET_EXPRESSION, CURLY_CLOSE, ROUND_CLOSE, SQUARED_CLOSE, JOLLY_CLOSE;
}

public class BracketExpressions {

	Stack<ParsingState> fullState = new Stack<ParsingState>();

	public String ifPossible(String expression) {

		pushState(new ParsingState(expression), 0, Tokens.BRACKET_EXPRESSION);

		while (!fullState.isEmpty()) {
			ParsingState state = fullState.pop();

			if (state.isGoalReached())
				return POSSIBLE;
			else
				parse(state);

		}

		return IMPOSSIBLE;

	}

	private boolean parse(ParsingState state) {

		if (state.parsingComplete())
			return false;

		Tokens pattern = state.patterns.remove(0);

		if (pattern.equals(Tokens.BRACKET_EXPRESSION)) {
			return matchBracketExpression(state);
		}

		return matchClose(state, pattern);

	}

	boolean matchBracketExpression(ParsingState state) {

		if (state.text.equals("")) {
			// only possible match is empty string
			fullState.push(state);
			return true;
		}

		String firstChar = state.text.substring(0, 1);

		if (firstChar.equals("X")) {
			// First possible match is an opening
			pushState(state, 1, Tokens.BRACKET_EXPRESSION, Tokens.JOLLY_CLOSE,
					Tokens.BRACKET_EXPRESSION);

			// Second possible match is empty string
			pushState(state, 0);

			return true;
		}

		if (firstChar.equals("(")) {
			// Only possible match is an opening
			pushState(state, 1, Tokens.BRACKET_EXPRESSION, Tokens.ROUND_CLOSE,
					Tokens.BRACKET_EXPRESSION);
			return true;
		}

		if (firstChar.equals("[")) {
			// Only possible match is an opening
			pushState(state, 1, Tokens.BRACKET_EXPRESSION,
					Tokens.SQUARED_CLOSE, Tokens.BRACKET_EXPRESSION);
			return true;
		}

		if (firstChar.equals("{")) {
			// Only possible match is an opening
			pushState(state, 1, Tokens.BRACKET_EXPRESSION, Tokens.CURLY_CLOSE,
					Tokens.BRACKET_EXPRESSION);
			return true;
		}

		// only possible match is empty string
		fullState.push(state);
		return true;
	}

	private void pushState(ParsingState fromState, int popChars,
			Tokens... patternsToMatch) {

		List<Tokens> patterns = new ArrayList<Tokens>(patternsToMatch.length
				+ fromState.patterns.size());

		patterns.addAll(Arrays.asList(patternsToMatch));
		patterns.addAll(fromState.patterns);

		ParsingState newState = new ParsingState(patterns,
				fromState.text.substring(popChars));
		fullState.push(newState);

	}

	private boolean matchClose(ParsingState state, Tokens closePattern) {

		if (state.text.equals(""))
			return false;

		switch (closePattern) {
		case ROUND_CLOSE:
			if (state.text.startsWith(")") || state.text.startsWith("X")) {
				pushState(state, 1);
				return true;
			} else
				return false;

		case SQUARED_CLOSE:
			if (state.text.startsWith("]") || state.text.startsWith("X")) {
				pushState(state, 1);
				return true;
			} else
				return false;

		case CURLY_CLOSE:
			if (state.text.startsWith("}") || state.text.startsWith("X")) {
				pushState(state, 1);
				return true;
			} else
				return false;
		case JOLLY_CLOSE:

			if (state.text.startsWith("}") || state.text.startsWith("]")
					|| state.text.startsWith(")") || state.text.startsWith("X")) {
				pushState(state, 1);
				return true;
			} else
				return false;

		default:
			throw new RuntimeException("Invalid close pattern");
		}
	}

	class ParsingState {
		List<Tokens> patterns;
		String text;

		public ParsingState(List<Tokens> patterns, String text) {

			this.patterns = patterns;
			this.text = text;
		}

		public ParsingState(String text) {
			this(new ArrayList<Tokens>(), text);
		}

		boolean isGoalReached() {
			if (patterns.isEmpty() && text.equals(""))
				return true;
			else
				return false;
		}

		boolean parsingComplete() {
			return patterns.isEmpty();
		}

	}

	public static final String POSSIBLE = "possible";

	public static final String IMPOSSIBLE = "impossible";
}