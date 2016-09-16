package swing2;

import util.AnimGifHandler;

public class Misi extends Fighter
{
	public Misi()
	{
		String playerName = "Misi";
		for (Move move : Move.values())
		{
			mMoves.put(move, new Sprite(playerName.toUpperCase() + "_" + move.toString(), AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/" + playerName.toLowerCase() + "/" + move.toString() + ".gif"))));
		}
	}
}
