package no.intelligentdesign.kata.zilch;

public class Player {
	ZilchGame z;
	String name;
		
	public Player(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public int getScore() {
		return z.getScore(this);
	}

	public void addPoints(int i) {
		z.addPoints(this, i);
		
	}

	public int throwThem() {
		// TODO Auto-generated method stub
		return -1;
	}

	public DiceThrow getDiceThrow() {
			
		return new DiceThrow();
	}

	public void play(boolean[] bs) {
		z.setMarkers(bs);
	}

	public void bank() {
		// TODO Auto-generated method stub
			z.bank();
		}
}
