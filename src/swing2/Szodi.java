package swing2;

import util.AnimGifHandler;

public class Szodi extends Fighter
{
	public Szodi()
	{
		String playerName = "Szodi";
		for (Move move : Move.values())
		{
			mMovesNonMirrored.put(move, new Sprite(playerName.toUpperCase() + "_" + move.toString(), AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/" + playerName.toLowerCase() + "/" + move.toString() + ".gif"))));
			mMovesMirrored.put(move, new Sprite(playerName.toUpperCase() + "_F_" + move.toString(), AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/" + playerName.toLowerCase() + "/" + move.toString() + "_F.gif"))));
		}
	}
}
