package no.intelligentdesign.kata.zilch;

import java.util.Random;

public class Die {
	Random random;
	int value;
	boolean marked = false;
	
	public Die(Random r) {
		random = r;
		roll();
	}

	public Die() {
		random = new Random();
		roll();
	}

	public int getValue() {
		return value;
	}

	public void roll() {
		value = random.nextInt(5) + 1;
	}

	public boolean isMarked() {
		return marked;
	}

	public void mark() {
			marked = true;
	}

	public void setFace(int i) {
			value = i;
	}

}
