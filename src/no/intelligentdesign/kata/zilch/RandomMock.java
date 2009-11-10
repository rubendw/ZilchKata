package no.intelligentdesign.kata.zilch;

import java.util.Random;

@SuppressWarnings("serial")
public class RandomMock extends Random {
	int next;
	
	public void setFace(int i) {
		next = i;
	}
	
	@Override
	public int nextInt(int i) {
		return next - 1;		
	}

}
