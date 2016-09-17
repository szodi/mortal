package swing2;

import util.AnimGifHandler;

public class Mate extends Fighter
{
	public Mate()
	{
		String playerName = "Mate";
		for (Move move : Move.values())
		{
			mMovesNonMirrored.put(move, new Sprite(playerName.toUpperCase() + "_" + move.toString(), AnimGifHandler.getFrames(Mate.class.getResourceAsStream("/images/" + playerName.toLowerCase() + "/" + move.toString() + ".gif"))));
			mMovesMirrored.put(move, new Sprite(playerName.toUpperCase() + "_F_" + move.toString(), AnimGifHandler.getFrames(Mate.class.getResourceAsStream("/images/" + playerName.toLowerCase() + "/" + move.toString() + "_F.gif"))));
		}
	}
}
