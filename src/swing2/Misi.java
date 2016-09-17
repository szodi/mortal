package swing2;

import util.AnimGifHandler;

public class Misi extends Fighter
{
	public Misi()
	{
		String playerName = "Misi";
		for (Move move : Move.values())
		{
			mMovesNonMirrored.put(move, new Sprite(playerName.toUpperCase() + "_" + move.toString(), AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/" + playerName.toLowerCase() + "/" + move.toString() + ".gif"))));
			mMovesMirrored.put(move, new Sprite(playerName.toUpperCase() + "_F_" + move.toString(), AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/" + playerName.toLowerCase() + "/" + move.toString() + "_F.gif"))));
		}
	}
}
