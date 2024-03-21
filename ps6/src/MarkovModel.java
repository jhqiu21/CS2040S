import java.util.Random;
import java.util.HashMap;
/**
 * This is the main class for your Markov Model.
 *
 * Assume that the text will contain ASCII characters in the range [1,255].
 * ASCII character 0 (the NULL character) will be treated as a non-character.
 *
 * Any such NULL characters in the original text should be ignored.
 */
public class MarkovModel {

	// Use this to generate random numbers as needed
	private Random generator = new Random();
	private HashMap<String, int[]> hashmap;
	// This is a special symbol to indicate no character
	public static final char NOCHARACTER = (char) 0;
	int order;
	/**
	 * Constructor for MarkovModel class.
	 *
	 * @param order the number of characters to identify for the Markov Model sequence
	 * @param seed the seed used by the random number generator
	 */
	public MarkovModel(int order, long seed) {
		// Initialize your class here
		this.order = order;
		this.hashmap = new HashMap<>();
		// Initialize the random number generator
		generator.setSeed(seed);
	}

	/**
	 * Builds the Markov Model based on the specified text string.
	 */
	public void initializeText(String text) {
		// Build the Markov model here
		String kgram;
		char curr;
		for (int pos = 0; pos < text.length() - this.order; pos++) {
			kgram = text.substring(pos, pos + this.order);
			curr = text.charAt(pos + this.order);
			if (!this.hashmap.containsKey(kgram)) {
				int[] ascii = new int[256];
				this.hashmap.put(kgram, ascii);
			}
			int index = (int) curr;
			this.hashmap.get(kgram)[index]++;
		}
	}

	/**
	 * Returns the number of times the specified kgram appeared in the text.
	 */
	public int getFrequency(String kgram) {
		int[] asciiArr = this.hashmap.get(kgram);
		int feq = 0;
		for (int i = 0; i < 256; i++) {
			feq += asciiArr[i];
		}
		return feq;
	}

	/**
	 * Returns the number of times the character c appears immediately after the specified kgram.
	 */
	public int getFrequency(String kgram, char c) {
		int index = (int) c;
		return this.hashmap.get(kgram)[index];
	}

	/**
	 * Generates the next character from the Markov Model.
	 * Return NOCHARACTER if the kgram is not in the table, or if there is no
	 * valid character following the kgram.
	 */
	public char nextCharacter(String kgram) {
		// See the problem set description for details
		// on how to make the random selection.
		if (this.hashmap.containsKey(kgram)) {
			int[] ref = this.hashmap.get(kgram);
			int bound = getFrequency(kgram);
			int resIndex = this.generator.nextInt(bound);
			int accIndex = 0;
			for (int i = 0; i < 256; i++) {
				accIndex += ref[i];
				if (accIndex > resIndex) {
					return (char) i;
				}
			}
		}
		return NOCHARACTER;
	}
}
