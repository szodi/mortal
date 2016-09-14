package swing2;

import java.awt.image.BufferedImage;

import util.AnimGifHandler;

public class Misi extends Fighter
{
	BufferedImage[] standard = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/alapallas.gif"));
	BufferedImage[] walk = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/seta.gif"));
	BufferedImage[] kick = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/rugas.gif"));
	BufferedImage[] punch = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/utes.gif"));

	public Misi()
	{
		mMoves.put(Move.STANDARD, new Sprite("MISI_STANDARD", standard));
		mMoves.put(Move.WALK, new Sprite("MISI_WALK", walk));
		mMoves.put(Move.KICK, new Sprite("MISI_KICK", kick));
		mMoves.put(Move.PUNCH, new Sprite("MISI_PUNCH", punch));
	}
}
