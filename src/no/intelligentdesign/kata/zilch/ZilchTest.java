package no.intelligentdesign.kata.zilch;

import java.util.ArrayList;

import org.junit.Test;
import junit.framework.TestCase;

public class ZilchTest extends TestCase {

	@Test
	public void testZeroPointsAtStartOfGame() {
		ZilchGame game = new ZilchGame();
		game.add(new Player("Akilles"));
		assertEquals(0, game.getCurrentPlayer().getScore());
	}

	@Test
	public void testAddPoints() {
		ZilchGame game = new ZilchGame();
		Player p = new Player("Paris");
		game.add(p);
		assertEquals(0, game.getCurrentPlayer().getScore());
		p.addPoints(200);
		assertEquals(200, game.getCurrentPlayer().getScore());
	}

	@Test
	public void testGetDice() {
		ZilchGame game = new ZilchGame();
		assertEquals(6, game.getDice().size());
	}

	@Test
	public void testThrowHasDice() {
		DiceThrow diceThrow = new DiceThrow();
		assertEquals(6, diceThrow.getDice().size());
	}

	@Test
	public void testAOneIsWorthAHundo() {
		assertEquals(100, ZilchGame.getPointsWorth(new int[] { 1 }));
	}

	@Test
	public void testAFiveAintFilthy() {
		assertEquals(50, ZilchGame.getPointsWorth(new int[] { 5 }));
	}

	@Test
	public void testRollDiceNotOverSix() {
		Die die = new Die();
		assertTrue(7 > die.getValue());
	}

	@Test
	public void testDicePositiveNumber() {
		Die die = new Die();
		assertTrue(die.getValue() > 0);
	}

	@Test
	public void testRollDiceIsRandom() {
		RandomMock r = new RandomMock();
		Die d = new Die(r);
		r.setFace(2);
		d.roll();
		assertEquals(2, d.getValue());
		r.setFace(3);
		d.roll();
		assertEquals(3, d.getValue());
	}

	@Test
	public void testGetPlayerNames() {
		ZilchGame game = new ZilchGame();
		Player ruben = new Player("Ruben");
		Player oddmund = new Player("Oddmund");
		game.add(ruben);
		game.add(oddmund);
		assertEquals("Ruben", game.getCurrentPlayer().getName());
		game.endTurn();
		assertEquals("Oddmund", game.getCurrentPlayer().getName());
		game.endTurn();
		assertEquals("Ruben", game.getCurrentPlayer().getName());

		assertTrue(game.getAllPlayers().contains(ruben));
		assertTrue(game.getAllPlayers().contains(oddmund));
	}

	@Test
	public void testPlayerThrowing() {
		Player p = new Player("Janus");
		assertTrue(0 > p.throwThem());
	}

	@Test
	public void test_dice_can_be_marked() {
		Die die = new Die();
		assertFalse(die.isMarked());
		die.mark();
		assertTrue(die.isMarked());
	}

	@Test
	public void test_() {
		ZilchGame game = new ZilchGame();
		game.add(new Player("José"));
		assertNotNull(game.getCurrentPlayer().getDiceThrow());
	}

	@Test
	public void test_scoring_bits() {
		ZilchGame z = new ZilchGame();
		Player p;
		z.add(p = new Player("Fernando"));
		z.add(new Player("Julio"));
		assertNull(z.getWinner());
		p.addPoints(ZilchGame.MAX_SCORE + 1);
		assertEquals(p, z.getWinner());
		assertEquals(10001, z.getScore(p));	
	}
	
	@Test
	public void test_Jamaal_plays_zilch(){
		ZilchGame z = new ZilchGame();
		Player p, j;
		z.add(p = new Player("Jamaal"));
		z.add(j = new Player("Hussein"));
		
		p.addPoints(0);
		p.addPoints(0);
		p.addPoints(0);
		
		j.addPoints(0);
		j.addPoints(0);
		j.addPoints(0);
		
		j.addPoints(0);
		j.addPoints(0);
		j.addPoints(0);
		
		assertEquals((-1000), j.getScore());
		
		ArrayList<Die> dice = z.getDice();
		
		dice.get(0).setFace(1);
		
		Player pablo = z.getCurrentPlayer();
		
		int score = z.getScore(pablo);
		
		pablo.play(new boolean[]{true, false, false, false, false, false});
		
		assertEquals(5, z.getDice().size());
		
		dice = z.getDice();
		
		dice.get(0).setFace(3);
		pablo.play(new boolean[]{true, false, false, false, false});
		System.out.println(z.getScore(pablo));
		System.out.println(score);
		System.out.println(pablo.getName());
		
		assertTrue(z.getScore(pablo)+1000==score);
	}
	
	public void test_Jamaal_wins(){
		ZilchGame z = new ZilchGame();
		
		z.add(/*p = */new Player(z.toString()));
		setAndPlayFirstDice(z, 1); //100
		setAndPlayFirstDice(z, 1); //200
		setAndPlayFirstDice(z, 1); //300
		setAndPlayFirstDice(z, 1); //400
		setAndPlayFirstDice(z, 1); //500
		z.getCurrentPlayer().bank();
		assertEquals(500, z.getScore(z.getCurrentPlayer()));		
		
	}

	private void setAndPlayFirstDice(ZilchGame z, int i) {
		System.out.println(z.getDice().size());
		z.getDice().get(0).setFace(i);
		z.getCurrentPlayer().play(new boolean[]{true});
	}
	
	
	
}