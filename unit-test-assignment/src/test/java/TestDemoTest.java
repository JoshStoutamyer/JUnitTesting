import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

import java.util.stream.Stream;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class TestDemoTest {
	private TestDemo testDemo;
	
	@BeforeEach
	void setUp() throws Exception {
		testDemo = new TestDemo();
	}
	/*
	 * A test method to test whether the method addPositive() only accepts positive integers and returns 
	 * positive sums.
	 */
	@ParameterizedTest
	@MethodSource("TestDemoTest#argumentsForAddPostive")
	void assertThatTwoPositiveNumbersAreAddedCorrectly(int a, int b, int expected, boolean expectException) {
		// Given: two positive numbers
		if (!expectException) {
			/*
			 * reads like: if a TestDemo object calls addPositive() with values of 'a' & 'b',
			 * then it should only receive positive integers. For the test these values are supplied by
			 * argumentsForAddPostive()
			 * The exception handles when 0's or negatives get passed in.
			 */
		// When: The method addPositive is called
		// Then: The values passed in are added together properly.
			assertThat(testDemo.addPostitive(a, b)).isEqualTo(expected);
			
		} else {
			// When: the method is called
			// Then: an exception is thrown
			assertThatThrownBy(() -> testDemo.addPostitive(a, b)).isInstanceOf(IllegalArgumentException.class);
		}
	}
	
	// As the name implied, the method supplies arguments for assertThatTwoPositiveNumbersAreAddedCorrectly()
	static Stream<Arguments> argumentsForAddPostive() {
		// @formatter:off
		return Stream.of(
				// Liminal tests: testing the very least necessary for pass/fails
				/*
				 * Since the addPositive() can only receive positive values to add together. 1 is the smallest
				 * value a or b can accept while not triggering an exception.
				 */
				arguments(1, 1, 2, false),
				/*
				 * Testing the 0 and negative cases, both should trigger an exception, thus they return true
				 * on the boolean.
				 */
				arguments(0, 0, 0, true),
				arguments(-1, -1, -2, true)
				);
		// @formatter:on
	}

	// Mocked test testing randomNumberSquared()
	@Test
	void assertThatNumberSquaredIsCorrect() {
		// Given: a number 
		TestDemo mockDemo = spy(testDemo);
		
		/*
		 * Since the number is, by design, random, we need something we know that we can test,
		 * and hold that value in mockDemo, which we then use to call randomNumberSquared() and hold that
		 * value in fiveSquared.
		 */
		doReturn(5).when(mockDemo).getRandomInt();
		// When: the method to square random numbers is called
		int fiveSquared = mockDemo.randomNumberSquared();
		
		// Since the expected value is known to us, all we need to know is if randomNumberSquared() produced 25.
		// Then: the number is squared properly
		assertThat(fiveSquared).isEqualTo(25);
	}
}
