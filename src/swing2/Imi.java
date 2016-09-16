package swing2;

import java.awt.image.BufferedImage;

import util.AnimGifHandler;

public class Imi extends Fighter
{
	BufferedImage[] standard = AnimGifHandler.getFrames(Imi.class.getResourceAsStream("/images/imi/alapallas.gif"));
	BufferedImage[] block = AnimGifHandler.getFrames(Imi.class.getResourceAsStream("/images/imi/vedekezes.gif"));
	BufferedImage[] punch_up = AnimGifHandler.getFrames(Imi.class.getResourceAsStream("/images/imi/felutes.gif"));
	BufferedImage[] crouch_and_block = AnimGifHandler.getFrames(Imi.class.getResourceAsStream("/images/imi/guggolasvedekezes.gif"));
	BufferedImage[] win = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/imi/gyozelem.gif"));
	BufferedImage[] turn_back = AnimGifHandler.getFrames(Imi.class.getResourceAsStream("/images/imi/megfordul1.gif"));
	BufferedImage[] walk = AnimGifHandler.getFrames(Imi.class.getResourceAsStream("/images/imi/menes.gif"));
	BufferedImage[] got_punch = AnimGifHandler.getFrames(Imi.class.getResourceAsStream("/images/imi/hatrahokol.gif"));
	BufferedImage[] kick = AnimGifHandler.getFrames(Imi.class.getResourceAsStream("/images/imi/rugas.gif"));
	BufferedImage[] punch = AnimGifHandler.getFrames(Imi.class.getResourceAsStream("/images/imi/utes.gif"));
	BufferedImage[] jump = AnimGifHandler.getFrames(Imi.class.getResourceAsStream("/images/imi/ugras.gif"));
	BufferedImage[] crouch = AnimGifHandler.getFrames(Imi.class.getResourceAsStream("/images/imi/guggolasvedekezes.gif"));

	public Imi()
	{
		mMoves.put(Move.STANDARD, new Sprite("IMI_STANDARD", standard));
		mMoves.put(Move.BLOCK, new Sprite("IMI_BLOCK", block));
		mMoves.put(Move.PUNCH_UP, new Sprite("IMI_PUNCH_UP", punch_up));
		mMoves.put(Move.CROUCH_AND_BLOCK, new Sprite("IMI_CROUCH_AND_BLOCK", crouch_and_block));
		mMoves.put(Move.WIN, new Sprite("IMI_WIN", win));
		mMoves.put(Move.TURN_BACK, new Sprite("IMI_TURN_BACK", turn_back));
		mMoves.put(Move.WALK_LEFT, new Sprite("IMI_WALK", walk));
		mMoves.put(Move.WALK_RIGHT, new Sprite("IMI_WALK", walk));
		mMoves.put(Move.GOT_PUNCH, new Sprite("IMI_GOT_PUNCH", got_punch));
		mMoves.put(Move.KICK, new Sprite("IMI_KICK", kick));
		mMoves.put(Move.PUNCH, new Sprite("IMI_PUNCH", punch));
		mMoves.put(Move.JUMP, new Sprite("IMI_JUMP", jump));
		mMoves.put(Move.CROUCH, new Sprite("IMI_CROUCH", crouch));
	}

	@Override
	public void moveStandard()
	{
		actualMove = Move.STANDARD;
	}

	@Override
	public void moveLeft()
	{
		setHorizontalOffset(getHorizontalOffset() - getMovement());
		actualMove = Move.WALK_LEFT;
	}

	@Override
	public void moveRight()
	{
		setHorizontalOffset(getHorizontalOffset() + getMovement());
		actualMove = Move.WALK_RIGHT;
	}

	@Override
	public void moveJump()
	{
//		setVerticalOffset(getVerticalOffset() - getMovement());
		actualMove = Move.JUMP;
	}

	@Override
	public void moveCrouch()
	{
//		setVerticalOffset(getVerticalOffset() + getMovement());
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
