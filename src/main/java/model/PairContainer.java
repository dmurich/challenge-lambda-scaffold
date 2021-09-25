package model;

import java.io.Reader;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Logger;

public class PairContainer {

	private static final Logger LOGGER = Logger.getLogger(PairContainer.class.getName());

	public static class Pair {
		public long op1;
		public long op2;

		Pair(long op1, long op2) {
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
		for(long i = 0; i < length; i++)
		{
			String[] pair = sc.nextLine().split(" ");

			long op1 = Integer.parseInt(pair[0]);
			long op2 = Integer.parseInt(pair[1]);

			pairs.add(new Pair(op1, op2));
		}

		sc.close();

		return new PairContainer(pairs);
	}

	public List<Pair> getPairs() {
		return pairs;
	}
}
