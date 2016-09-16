package swing2;

import java.awt.image.BufferedImage;

import util.AnimGifHandler;

public class Misi extends Fighter
{
	BufferedImage[] standard = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/alapallas.gif"));
	BufferedImage[] block = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/vedekezes.gif"));
	BufferedImage[] punch_up = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/felutes.gif"));
	BufferedImage[] crouch_and_block = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/gugolases-vedekezes.gif"));
//	BufferedImage[] win = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/gyozelem.gif"));
	BufferedImage[] turn_back = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/megfordulas.gif"));
	BufferedImage[] walk = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/seta.gif"));
	BufferedImage[] got_punch = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/hokoles.gif"));
	BufferedImage[] kick = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/rugas.gif"));
	BufferedImage[] punch = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/utes.gif"));
	BufferedImage[] jump = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/ugras_fixed.gif"));
	BufferedImage[] crouch = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/gugolas.gif"));

	public Misi()
	{
		mMoves.put(Move.STANDARD, new Sprite("MISI_STANDARD", standard));
		mMoves.put(Move.BLOCK, new Sprite("MISI_BLOCK", block));
		mMoves.put(Move.PUNCH_UP, new Sprite("MISI_PUNCH_UP", punch_up));
		mMoves.put(Move.CROUCH_AND_BLOCK, new Sprite("MISI_CROUCH_AND_BLOCK", crouch_and_block));
//		mMoves.put(Move.WIN, new Sprite("MISI_WIN", win));
		mMoves.put(Move.TURN_BACK, new Sprite("MISI_TURN_BACK", turn_back));
		mMoves.put(Move.WALK, new Sprite("MISI_WALK", walk));
		mMoves.put(Move.GOT_PUNCH, new Sprite("MISI_GOT_PUNCH", got_punch));
		mMoves.put(Move.KICK, new Sprite("MISI_KICK", kick));
		mMoves.put(Move.PUNCH, new Sprite("MISI_PUNCH", punch));
		mMoves.put(Move.JUMP, new Sprite("MISI_JUMP", jump));
		mMoves.put(Move.CROUCH, new Sprite("MISI_CROUCH", crouch));
	}

	@Override
	public void moveStandard()
	{
		actualMove = Move.STANDARD;
	}

	@Override
	public void moveLeft()
	{
		horizontalOffset -= movement;
		actualMove = Move.WALK;
	}

	@Override
	public void moveRight()
	{
		horizontalOffset += movement;
		actualMove = Move.WALK;
	}

	@Override
	public void moveJump()
	{
		verticalOffset -= movement;
		actualMove = Move.JUMP;
	}

	@Override
	public void moveCrouch()
	{
		verticalOffset += movement;
		actualMove = Move.CROUCH;
	}

	@Override
	public void hitPunch()
	{
		setActualMove(Move.PUNCH);
	}

	@Override
	public void hitKick()
	{
		setActualMove(Move.KICK);
	}
}
