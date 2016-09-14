package swing2;

import java.awt.image.BufferedImage;

import util.AnimGifHandler;

public class Scorpion extends Fighter
{
	BufferedImage[] standard = AnimGifHandler.getFrames(Scorpion.class.getResourceAsStream("/images/scorpion/standard.gif"));
	BufferedImage[] walk = AnimGifHandler.getFrames(Scorpion.class.getResourceAsStream("/images/scorpion/walk.gif"));
	BufferedImage[] gotPunch = AnimGifHandler.getFrames(Scorpion.class.getResourceAsStream("/images/scorpion/got_punch.gif"));

	public Scorpion()
	{
		mMoves.put(Move.STANDARD, new Sprite("SCORPION_STANDARD", standard));
		mMoves.put(Move.WALK, new Sprite("SCORPION_STANDARD", standard));
		mMoves.put(Move.GOT_PUNCH, new Sprite("SCORPION_GOTPUNCH", gotPunch));
	}
}