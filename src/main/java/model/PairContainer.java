package model;

import java.io.Reader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PairContainer {

	private static final Logger LOGGER = Logger.getLogger(PairContainer.class.getName());

	public static class Pair {
		public int op1;
		public int op2;

		Pair(int op1, int op2) {
			this.op1 = op1;
			this.op2 = op2;
		}
	}

	private List<Pair> pairs;

	public PairContainer(List<Pair> pairs) {
		this.pairs = pairs;
	}

	public static PairContainer read(Reader reader) {
		Scanner sc = new Scanner(reader);

		// First line: array lenght
		int length = Integer.parseInt(sc.nextLine());

		List<Pair> pairs = new ArrayList<Pair>(length);

		// Next N lines: pairs
		for(int i = 0; i < length; i++)
		{
			String[] pair = sc.nextLine().split(" ");

			int op1 = Integer.parseInt(pair[0]);
			int op2 = Integer.parseInt(pair[1]);

			pairs.add(new Pair(op1, op2));
		}

		sc.close();

		return new PairContainer(pairs);
	}

	public List<Pair> getPairs() {
		return pairs;
	}
}
