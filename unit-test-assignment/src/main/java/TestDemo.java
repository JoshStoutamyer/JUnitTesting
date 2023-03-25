import java.util.Random;

public class TestDemo {

	// method that only accepts positive numbers and returns their sum
	public int addPostitive(int a, int b) {
		if(a > 0 && b > 0) {
			return a + b;
		} else {
			throw new IllegalArgumentException("Both parameters must be positive!");
		}
	}
	
	// method to square random values obtained from getRandomInt()
	int randomNumberSquared() {
		int intToBeSquared = getRandomInt();
		return intToBeSquared * intToBeSquared;
	}

	// method to obtain random numbers
	int getRandomInt() {
		Random random = new Random();
		return random.nextInt(10) + 1;
	}
}
