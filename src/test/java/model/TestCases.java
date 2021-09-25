package model;

import org.junit.Test;
import org.junit.Assert;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.io.input.BOMInputStream;

import lambda.SolutionChecker;

public class TestCases {

	@Test
	public void scoringTest() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		try (
				Reader inputReader = new BufferedReader(new InputStreamReader(new BOMInputStream(classLoader.getResourceAsStream("input-01.txt")), StandardCharsets.UTF_8));
				Reader solutionReader = new BufferedReader(new InputStreamReader(new BOMInputStream(classLoader.getResourceAsStream("solution-02.txt")), StandardCharsets.UTF_8));
		) {
			InputMapping inputMapping = InputMapping.read(inputReader);

			long score = SolutionChecker.score(inputMapping, solutionReader);
			System.out.println(score);
			Assert.assertEquals(300, score);
		}
	}

	@Test
	public void validationTest() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		try (
				Reader inputReader = new BufferedReader(new InputStreamReader(new BOMInputStream(classLoader.getResourceAsStream("input-01.txt")), StandardCharsets.UTF_8));
				Reader solutionReader = new BufferedReader(new InputStreamReader(new BOMInputStream(classLoader.getResourceAsStream("solution-00.txt")), StandardCharsets.UTF_8));
		) {
			InputMapping inputMapping = InputMapping.read(inputReader);
			SolutionChecker.validate(inputMapping,solutionReader);

//			long score = SolutionChecker.score(pairContainer, solutionReader);
//			Assert.assertEquals(86833L, score);
		}
	}

	@Test
	public void scoringTest3() throws Exception {
		ClassLoader classLoader = getClass().getClassLoader();
		try (
				Reader inputReader = new BufferedReader(new InputStreamReader(new BOMInputStream(classLoader.getResourceAsStream("input-03.txt")), StandardCharsets.UTF_8));
				Reader solutionReader = new BufferedReader(new InputStreamReader(new BOMInputStream(classLoader.getResourceAsStream("solution-03.txt")), StandardCharsets.UTF_8));
		) {
			InputMapping inputMapping = InputMapping.read(inputReader);

			long score = SolutionChecker.score(inputMapping, solutionReader);
			System.out.println(score);
			//Assert.assertEquals(-3, score);
		}
	}

	@Test
	public void testSet() throws Exception {
		Set<Point> offices = new HashSet<>();
		offices.add(new Point(1,1));
		offices.add(new Point(1,1));
		offices.add(new Point(100000,100000));
		offices.add(new Point(100000,100000));
		System.out.println(offices);
	}
}
