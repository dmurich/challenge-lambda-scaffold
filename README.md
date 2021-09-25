# Challenge Lambda Scaffold

This project can be used as starting point for the implementation of the validation and scoring lambdas. It abstracts the usage of Code Masters' `tamtamy-judge-lambda` library and provides a clean structure for the implementation and testing of the lambdas.

## Table of Contents

1. [Architecture](#architecture)
2. [Local Tests](#local-tests)
3. [Sample Problem](#sample-problem)
4. [Installation & Compilation](#installation-compilation)
5. [Uploading to AWS](#uploading-to-aws)
6. [IDEs](#ides)

## Architecture

During the challenge, along with the Problem Statement, several scenarios are distributed to the participants in the form of text files (let's call them "inputs"). The participants should write a program which processes the data contained in the input files and produces text files as output, containing a solution for the problem (let's call them "solutions").

The validation and scoring lambdas are java functions loaded and executed inside the AWS Lambda service every time the Challenge Platform requests them. Every time a lambda function is called, one input file and the corresponding solution file are passed in as parameters. The validation lambda should verify the correctness of the solution, while the scoring lambda should compute the numerical score of the solution based on the Problem Statement.

The main classes are `src/main/java/lambda/ValidatorLambda.java` and `src/main/java/lambda/ScoringLambda.java`, which contain the entry points of the validation and scoring procedures (`String validationFunction(...)` and `long scoringFunction(...)` respectively). These functions accept an `OutputFile` parameter, which contains both the current input file and the corresponding solution file.

**Please keep in mind that performance matters, because both the validation and scoring functions can be called thousand of times per hour during a live challenge, and a fast response time is crucial for the responsiveness of the challenge platform itself.**

**Furthermore, consider that lambda functions run on a limited memory footprint, and this should be taken into account too while parsing the input and solution data.**

### ValidatorLambda

The function `private static String validationFunction(OutputFile file)` should implement the validation logic by reading and parsing the input file and the solution file, then checking if the solution file format and data are correct and respect the constraints of the Problem Statement.

If the solution fails to be validated, a `ValidationException` should be raised, specifying the validation `Category` and a `message`.
The `Category` parameter is an enum which specifies the generic error message displayed to the challenge participants upon failed validation. The `message` parameter will be only printed in the lambda logs and should be a detailed string identifying what actually failed in the validation, to help Code Masters troubleshoot problems during the challenge.

`ValidationException.Category` has four values: `ENCODING`, `CONSTRAINTS`, `SOLUTION_FORMAT`, `GENERIC`.
* `ENCODING` exceptions should be raised if the file encoding is wrong (for example, a binary solution file is provided instead of a text file)
* `CONSTRAINTS` exceptions should be raised if the solution data doesn't respect the constraints listed in the Problem Statement (for example, in the solution file two items are placed in the same coordinate in a 2D space)
* `SOLUTION_FORMAT` exceptions should be raised if the solution file is not structured as requested in the Problem Statement (for example, if it's requested that each line of the solution file must contain two integers, and the actual solution file has blank lines, or lines with more than two integers, or lines with string intead of integers, then this exception category should be used)
* `GENERIC` exceptions should be raised if the validation error does not belong to one of the above categories. There really should not be a use case for `GENERIC` exception; consider adding a new `Category` value if there is a case not covered by the existing ones.

### ScoringLambda

The function `private static long scoringFunction(OutputFile file)` should implement the scoring logic by reading and parsing the input file and the solution file, then computing the score based on the rules specified in the Problem Statement. When the scoring lambda is called, the solution file has been already verified by the validation lambda, therefore it's correct and should not be validated again. The function returns a `long` value which is the actual score which will be displayed to the challenge participants.

## Local Tests

The file `src/test/java/model/TestCases.java` can be used to create test cases using the JUnit library. Custom input files and solution files can be created and put in the directory `src/main/resources/`. They can then be read in the test cases and used as test data.

To add a new test case, just add a new function inside the `TestCases` class with the `@Test` annotation.

Extensive tests for validation and scoring should be developed in order to be sure of the correctness of the lambda functions.

Tests are executed as part of the building process. If a test fails, the building process fails too.

## Sample Problem

A sample problem is provided in this repository as an example for the validation and scoring lambdas implementation.

**Note that the sample problem is not representative of the complexity of real challenge problems. It is only provided as an example of how the lambda functions should be implemented.**

Sample problem definition: an ASCII text input file is provided. The first line contains the number of elements (N), and each of the following N lines contains two space-separated integers. All the integers are positive numbers. The solution file should contain N lines, each line should contain one integer number which is the result of the multiplication of the two integers in the corresponding line in the input file.
The score is the sum of all the lines in the solution file.

The sample problem validation and scoring functions are implemented in the file `src/main/java/lambda/SolutionChecker.java`. You can use this file structure as a basis for your implementation.

## Installation & Compilation

The project is packaged using the `maven` build tool. On Windows, you may need to set the environment variables `JAVA_HOME` and `MAVEN_HOME` pointing to the installation directories of Java and Maven respectively.

In a terminal, from the root directory, type `mvn clean package`. The first time the command is run, it will download all the dependencies. Then, after the compilation, it will execute the tests and produce an artifact in the `target/` directory called `challenge-lambda-scaffold-1.0.0-aws.zip`. This is the zip file that needs to be uploaded to the AWS lambda. Run `mvn clean package` or `mvn package` every time you need to rebuild the project.

**A `.jar` file is created alongside the zip file. This file should NOT be loaded to the AWS lambda. The correct file is the `.zip` file which filename ends in `-aws.zip`**

Change the `artifactId` string in the `pom.xml` file to produce a zip file with a custom name.

The command `mvn test` can be used to execute the tests without building the final artifact.

## Uploading to AWS

In the AWS dashboard, create a new lambda function and assign it a name.

Under the `Code Source` section, upload the zip file produced by the build process.

Under the `Runtime Settings` select `Java 8` as runtime and put the validation or scoring class name and package as the `Handler` (`lambda.ValidatorLambda` for the validator lambda and `lambda.ScoringLambda` for the scoring lambda)

## IDEs

If you prefer using an IDE, follow these instructions.

### Eclipse

Import the project as `Existing Maven Project`.

To build the project, right click on the file `pom.xml`, then select `Run As -> Maven build`. A dialog called `Edit Configuration` will appear. In the field `Goals`, type `clean package`, then click on the `Run` button in the bottom right corner. Eclipse will trigger the build. The next time you'll select `Run As -> Maven build`, Eclipse will remember your choice.

### IntelliJ IDEA

Import the project. A `Maven` tab should appear on the right of the screen. Here, under the `Lifecycle` section, you'll find the various possible actions. Click on `clean`, `package`, or `test` to trigger the corresponding action.
