package no.intelligentdesign.kata.zilch;

import java.util.ArrayList;
import java.util.Collection;

public class DiceThrow {

	public Collection<Die> getDice() {
		Collection<Die> balls = new ArrayList<Die>();
		for(int i=0;i<6;i++)
			balls.add(new Die());
		return balls;
	}

}
