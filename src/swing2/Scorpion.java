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
		mMoves.put(Move.WALK_LEFT, new Sprite("SCORPION_STANDARD", standard));
		mMoves.put(Move.WALK_RIGHT, new Sprite("SCORPION_STANDARD", standard));
		mMoves.put(Move.GOT_PUNCH, new Sprite("SCORPION_GOTPUNCH", gotPunch));
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
		verticalOffset -= movement;
	}

	@Override
	public void moveCrouch()
	{
		verticalOffset += movement;
	}

	@Override
	public void hitPunch()
	{
	}

	@Override
	public void hitKick()
	{
	}
}
