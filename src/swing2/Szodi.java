package swing2;

import java.awt.image.BufferedImage;

import util.AnimGifHandler;

public class Szodi extends Fighter
{
	BufferedImage[] standard = AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/szodi/alapallas.gif"));
	BufferedImage[] block = AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/szodi/alapvedekezes.gif"));
	BufferedImage[] punch_up = AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/szodi/felutes.gif"));
	BufferedImage[] crouch_and_block = AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/szodi/guggolasvedekezessel.gif"));
	BufferedImage[] win = AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/szodi/gyozelem.gif"));
	BufferedImage[] turn_back = AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/szodi/megfordulas.gif"));
	BufferedImage[] walk = AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/szodi/menes.gif"));
	BufferedImage[] got_punch = AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/szodi/visszahokoles.gif"));
	BufferedImage[] kick = AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/szodi/rugas.gif"));
	BufferedImage[] punch = AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/szodi/utes.gif"));
	BufferedImage[] jump = AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/szodi/ugras.gif"));
	BufferedImage[] crouch = AnimGifHandler.getFrames(Szodi.class.getResourceAsStream("/images/szodi/guggolasvedekezessel.gif"));

	public Szodi()
	{
		mMoves.put(Move.STANDARD, new Sprite("SZODI_STANDARD", standard));
		mMoves.put(Move.BLOCK, new Sprite("SZODI_BLOCK", block));
		mMoves.put(Move.PUNCH_UP, new Sprite("SZODI_PUNCH_UP", punch_up));
		mMoves.put(Move.CROUCH_AND_BLOCK, new Sprite("SZODI_CROUCH_AND_BLOCK", crouch_and_block));
		mMoves.put(Move.WIN, new Sprite("SZODI_WIN", win));
		mMoves.put(Move.TURN_BACK, new Sprite("SZODI_TURN_BACK", turn_back));
		mMoves.put(Move.WALK_LEFT, new Sprite("SZODI_WALK", walk));
		mMoves.put(Move.WALK_RIGHT, new Sprite("SZODI_WALK", walk));
		mMoves.put(Move.GOT_PUNCH, new Sprite("SZODI_GOT_PUNCH", got_punch));
		mMoves.put(Move.KICK, new Sprite("SZODI_KICK", kick));
		mMoves.put(Move.PUNCH, new Sprite("SZODI_PUNCH", punch));
		mMoves.put(Move.JUMP, new Sprite("SZODI_JUMP", jump));
		mMoves.put(Move.CROUCH, new Sprite("SZODI_CROUCH", crouch));
	}
}
