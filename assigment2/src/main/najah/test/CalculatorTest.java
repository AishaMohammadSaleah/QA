package main.najah.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.Timeout;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvFileSources;

import main.najah.code.Calculator;

@DisplayName("Calculator Tests")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Execution(ExecutionMode.CONCURRENT)
public class CalculatorTest {
	
    Calculator calc;
    @BeforeAll
    static void setupAll() {
    	System.out.println("Starting calculator test");
    }
	@BeforeEach
	void setUp() throws Exception {
		System.out.println("Executing Test");
		calc =new Calculator();
	}
	

	
	@ParameterizedTest
	@DisplayName("Test addition of small and large positive numbers")
	@Order(1)
	@CsvFileSource(resources = "../../../data/calculatorTest/numbersToTestpositiveNumbers.csv",numLinesToSkip = 1)
	void testAddPositiveNumbers(int num1,int num2,int expected) {
		assertEquals(expected, calc.add(num1,num2));
	}//the programs gives an error with the large numbers 
	//2147483647,1,2147483648 tried this and give me an error 
	@ParameterizedTest
	@DisplayName("Test addition with zero and negative numbers")
	@Order(2)
	@CsvFileSource(resources = "../../../data/calculatorTest/bothZeroAndNegativeNumbers.csv",numLinesToSkip = 1)	
	void testAddZeroAndNegativeNumbers( int num1,int num2,int expected) {
		assertEquals(expected, calc.add(num1,num2));
	}
	
	@Test 
	@DisplayName("Test division by zero")
	@Order(3)
	void testDivideByZero() {
		assertThrows(ArithmeticException.class,()->calc.divide(20, 0),"Divide by zero should throw an ArithmeticException");
	}
	
	@ParameterizedTest
	@DisplayName("Test valid division")
	@Order(4)
	@CsvFileSource(resources = "../../../data/calculatorTest/numbersToTestDivideMethod.csv",numLinesToSkip = 1)	
	void testDivide( int num1,int num2,int expected) {
		assertEquals(expected, calc.divide(num1,num2));
	}//if the output was double and that is ok the code gives an error and can not support doubles 
	
	@ParameterizedTest
	@DisplayName("Factorial Parameterized Test")
	@Order(5)
	@CsvFileSource(resources = "../../../data/calculatorTest/numbersToTestFactorial.csv",numLinesToSkip = 1)
	void testFactorial(int input, int expected) {
		assertEquals(expected, calc.factorial(input));
	}
	
	@ParameterizedTest
	@DisplayName("Factorial Parameterized Test")
	@Order(6)
	@CsvFileSource(resources = "../../../data/calculatorTest/numbersToTestFactorial.csv",numLinesToSkip = 1)
	void testFactorialNegativeNumbers() {
		assertThrows(IllegalArgumentException.class,() ->calc.factorial(-1));
	}
	 @Test
	 @DisplayName("Test factorial timeout")
	 @Timeout(1) 
	 @Order(7) 
	 void testFactorial_Timeout() {
	      assertTimeoutPreemptively(java.time.Duration.ofSeconds(1), () -> calc.factorial(5));
	  }
	 
	 
	 
	 @Test
	 @DisplayName("Intentional Failing Test - Needs Fix")
	 @Disabled("This test is currently falling. Need to review the add method")
	 @Order(8)
	 void testAddFailing() {
		 assertEquals(6, calc.add(2,2));
	 }
	 
	 
		@AfterAll
		static void allDone() {
			System.out.println("Calculator Tests Finished");
		}
		
		@AfterEach
		void setupDone() {
			System.out.println("End Executing Test");
		}
		
}
