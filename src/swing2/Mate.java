package swing2;

import java.awt.image.BufferedImage;

import util.AnimGifHandler;

public class Mate extends Fighter
{
	BufferedImage[] standard = AnimGifHandler.getFrames(Mate.class.getResourceAsStream("/images/mate/STANDARD.gif"));
	BufferedImage[] block = AnimGifHandler.getFrames(Mate.class.getResourceAsStream("/images/mate/BLOCK.gif"));
	BufferedImage[] punch_up = AnimGifHandler.getFrames(Mate.class.getResourceAsStream("/images/mate/PUNCH_UP.gif"));
	BufferedImage[] crouch_and_block = AnimGifHandler.getFrames(Mate.class.getResourceAsStream("/images/mate/guggolvedekeyik.gif"));
	BufferedImage[] win = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/mate/WIN.gif"));
	BufferedImage[] turn_back = AnimGifHandler.getFrames(Mate.class.getResourceAsStream("/images/mate/TURN_BACK.gif"));
	BufferedImage[] walk = AnimGifHandler.getFrames(Mate.class.getResourceAsStream("/images/mate/WALK.gif"));
	BufferedImage[] got_punch = AnimGifHandler.getFrames(Mate.class.getResourceAsStream("/images/mate/GOT_PUNCH.gif"));
	BufferedImage[] kick = AnimGifHandler.getFrames(Mate.class.getResourceAsStream("/images/mate/KICK.gif"));
	BufferedImage[] punch = AnimGifHandler.getFrames(Mate.class.getResourceAsStream("/images/mate/PUNCH.gif"));
	BufferedImage[] jump = AnimGifHandler.getFrames(Mate.class.getResourceAsStream("/images/mate/JUMP.gif"));
	BufferedImage[] crouch = AnimGifHandler.getFrames(Mate.class.getResourceAsStream("/images/mate/guggolvedekeyik.gif"));

	public Mate()
	{
		mMoves.put(Move.STANDARD, new Sprite("MATE_STANDARD", standard));
		mMoves.put(Move.BLOCK, new Sprite("MATE_BLOCK", block));
		mMoves.put(Move.PUNCH_UP, new Sprite("MATE_PUNCH_UP", punch_up));
		mMoves.put(Move.CROUCH_AND_BLOCK, new Sprite("MATE_CROUCH_AND_BLOCK", crouch_and_block));
		mMoves.put(Move.WIN, new Sprite("MATE_WIN", win));
		mMoves.put(Move.TURN_BACK, new Sprite("MATE_TURN_BACK", turn_back));
		mMoves.put(Move.WALK_LEFT, new Sprite("MATE_WALK", walk));
		mMoves.put(Move.WALK_RIGHT, new Sprite("MATE_WALK", walk));
		mMoves.put(Move.GOT_PUNCH, new Sprite("MATE_GOT_PUNCH", got_punch));
		mMoves.put(Move.KICK, new Sprite("MATE_KICK", kick));
		mMoves.put(Move.PUNCH, new Sprite("MATE_PUNCH", punch));
		mMoves.put(Move.JUMP, new Sprite("MATE_JUMP", jump));
		mMoves.put(Move.CROUCH, new Sprite("MATE_CROUCH", crouch));
	}
}
