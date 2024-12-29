# TestNG Test Suite

This project contains test cases for validating the Zomato Cart Offer functionality. The test execution is configured through the `testng.xml` file.

## Prerequisites

1. **Java Development Kit (JDK)**:
   - Ensure JDK 8 or higher is installed.
   - Verify by running:
     ```bash
     java -version
     ```

2. **Apache Maven**:
   - Ensure Maven is installed and added to your system's PATH.
   - Verify by running:
     ```bash
     mvn -version
     ```

3. **TestNG Plugin**:
   - If you are using an IDE (e.g., IntelliJ IDEA or Eclipse), ensure the TestNG plugin is installed.

## Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/chandresh91/Lucidity_SDET_Assignment.git
   ```

2. Navigate to the project directory:
   ```bash
   cd Lucidity_SDET_Assignment
   ```

3. Install dependencies:
   ```bash
   mvn clean install
   ```

## Test Execution

### Using TestNG XML

1. Open the `testng.xml` file to review the suite and test configuration.

   Example `testng.xml`:
   ```xml
   <?xml version="1.0" encoding="UTF-8"?>
   <!DOCTYPE suite SYSTEM "https://testng.org/testng-1.0.dtd">
   <suite name="Suite">
	<listeners>
		<listener class-name="api.utility.ExtentReportManager"></listener>
	</listeners>
	<test name="Test1">
		<classes>
			<class name="api.test.Cart_Tests" />
		</classes>
	</test>
</suite> 
   

2. Run the tests using Maven:
   ```bash
   mvn test
   ```

3. Alternatively, run the tests directly from the IDE:
   - Right-click on the `testng.xml` file.
   - Select **Run 'testng.xml'**.

### Generating Reports

- TestNG generates default reports in the `test-output` directory.
- Open `test-output/index.html` in a browser to view the test execution report.
- Also I have added Extent Report for the reference, you can find the report of test cases under reports folder.
- For extent report ,extent report dependency is required. 



## Troubleshooting

1. **Tests not executing**:
   - Ensure the `testng.xml` file is correctly configured and located in the project root.

2. **Dependency issues**:
   - Run:
     ```bash
     mvn dependency:resolve
     ```

3. **Report not generated**:
   - Ensure the `test-output` folder exists after running tests. If not, check the logs for errors.



---

### Author
Chandrashekhar Patil
