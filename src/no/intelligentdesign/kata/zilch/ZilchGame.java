package no.intelligentdesign.kata.zilch;

import java.util.*;

public class ZilchGame {

	public static final int MAX_SCORE = 10000;
	private Stack<Player> players = new Stack<Player>();
	private Player currentPlayer;
	private ArrayList<Die> dice;
	private int numberOfDice = 6;
	private HashMap<Player, Integer> scores = new HashMap<Player, Integer>();
	private int pouch = 0;
	private HashMap<Player, Integer> zilxhes = new HashMap<Player, Integer>();

	public ZilchGame() {
		dice = (ArrayList<Die>) createDice();
	}

	private Collection<Die> createDice() {
		Collection<Die> omg = new ArrayList<Die>();

		for (int i = 0; i < numberOfDice; i++)
			omg.add(new Die());

		return omg;

	}

	public void add(Player player) {
		if(currentPlayer==null)
			currentPlayer=player;
		player.z = this;
		this.players.add(player);
		scores.put(player, 0);
	}

	public Player getCurrentPlayer() {
		players.remove(currentPlayer);
		return currentPlayer;
	}

	public void endTurn() {
		players.add(currentPlayer);
		currentPlayer = players.firstElement();
		players.remove(currentPlayer);
	}

	public ArrayList<Die> getDice() {
		return dice;
	}

	public static int getPointsWorth(int[] is) {
		int p = 0;
		for (Integer i : is) {
			if (i == 1)
				p += 100;
			if (i == 5)
				p += 50;
		}
		return p;
	}

	public Collection<Player> getAllPlayers() {
		players.add(currentPlayer);
		return players;
	}

	public Player getWinner() {
		Player winner = null;
		for (Player p : scores.keySet()) {
			if (winner == null)
				winner = p;
			else if (p.getScore() > winner.getScore())
				winner = p;
		}
		if(winner.getScore()<10000)
			return null;
		return winner;
	}

	public int getScore(Player p) {
		return scores.get(p);
	}

	public void addPoints(Player player, int i) {
		if (!player.equals(currentPlayer)) {
			zilch(player);
			return;
		}
		if (scores.get(player) != null) {
			i += scores.get(player);
			scores.remove(player);
		}
		scores.put(player, i);
	}

	private void zilch(Player player) {
		int i = 1;
		if (zilxhes.get(player) != null)
			i += zilxhes.get(player);
		if (i == 3) {
			fuckPoints(player);
			zilxhes.put(player, 0);
			return;
		}
		zilxhes.put(player, i);
	}

	private void fuckPoints(Player player) {
		scores.put(player, scores.get(player)-500);		
	}

	public void setMarkers(boolean[] bs) {
		// TODO Auto-generated method stub
		int i = 0;
		ArrayList<Die> neger = new ArrayList<Die>();
		for (Die d : dice) {
			if (bs.length > i && bs[i++] == true){
				pouch(d);
			}
			else
				neger.add(d);
		}
		dice = neger;
	}

	private void pouch(Die d) {
		// dice.remove(d);
		int i = ZilchGame.getPointsWorth(new int[] { d.getValue() });
		pouch += (i == 0) ? -1000 : i;
		if (i == 0)
			currentPlayer.addPoints(-1000);
	}

	public void bank() {
		scores.put(currentPlayer, scores.get(currentPlayer) +pouch);		
	}
}