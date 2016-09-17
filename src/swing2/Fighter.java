package swing2;

import java.awt.Polygon;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public abstract class Fighter
{
	public static Map<Integer, Move> mKeyMoves1 = new HashMap<>();
	public static Map<Integer, Move> mKeyMoves2 = new HashMap<>();

	static 
	{
		mKeyMoves1.put(KeyEvent.VK_W, Move.JUMP);
		mKeyMoves1.put(KeyEvent.VK_S, Move.CROUCH);
		mKeyMoves1.put(KeyEvent.VK_A, Move.WALK_LEFT);
		mKeyMoves1.put(KeyEvent.VK_D, Move.WALK_RIGHT);
		mKeyMoves1.put(KeyEvent.VK_1, Move.PUNCH);
		mKeyMoves1.put(KeyEvent.VK_2, Move.KICK);
		mKeyMoves1.put(KeyEvent.VK_3, Move.BLOCK);

		mKeyMoves2.put(KeyEvent.VK_UP, Move.JUMP);
		mKeyMoves2.put(KeyEvent.VK_DOWN, Move.CROUCH);
		mKeyMoves2.put(KeyEvent.VK_LEFT, Move.WALK_LEFT);
		mKeyMoves2.put(KeyEvent.VK_RIGHT, Move.WALK_RIGHT);
		mKeyMoves2.put(KeyEvent.VK_8, Move.PUNCH);
		mKeyMoves2.put(KeyEvent.VK_9, Move.KICK);
		mKeyMoves2.put(KeyEvent.VK_7, Move.BLOCK);
	}
	
	Map<Move, Sprite> mMoves = new HashMap<>();

	int horizontalOffset = 0;
	int verticalOffset = 0;
	int movement = 8;

	BufferedImage biShownImage;
	Polygon pShownPolygon;

	int shownImageIndex;

	Move actualMove = Move.STANDARD;
	
	boolean isPlayer1 = true;
	boolean isOnLeft = false;

	public void doMove(Move move)
	{
		if(actualMove == Move.CROUCH)
		{
			if(move == Move.PUNCH)
			{
				move = Move.PUNCH_UP;
			}
			else if ( move == Move.BLOCK)
			{
				move = Move.CROUCH_AND_BLOCK;
			}
		}
		actualMove = move;
		if (move == Move.WALK_LEFT)
		{
			setHorizontalOffset(getHorizontalOffset() - getMovement());
		}
		else if (move == Move.WALK_RIGHT)
		{
			setHorizontalOffset(getHorizontalOffset() + getMovement());
		}
	}
	
	public Sprite getSprite(Move move)
	{
		return mMoves.get(move);
	}

	public int getHorizontalOffset()
	{
		return horizontalOffset;
	}

	public void setHorizontalOffset(int horizontalOffset)
	{
		if (this.horizontalOffset != horizontalOffset)
		{
			translatePolygons(horizontalOffset - this.horizontalOffset, 0);
		}
		this.horizontalOffset = horizontalOffset;
	}

	public int getVerticalOffset()
	{
		return verticalOffset;
	}

	public void setVerticalOffset(int verticalOffset)
	{
		if (this.verticalOffset != verticalOffset)
		{
			translatePolygons(0, verticalOffset - this.verticalOffset);
		}
		this.verticalOffset = verticalOffset;
	}

	private void translatePolygons(int dx, int dy)
	{
		for (Sprite sprite : mMoves.values())
		{
			for (Polygon polygon : sprite.getPolygons())
			{
				polygon.translate(dx, dy);
			}
		}
	}

	public int getImageIndex(boolean buttonPressed, int playerImageIndex)
	{
		BufferedImage[] images = mMoves.get(actualMove).getImages();
		Polygon[] polygons = mMoves.get(actualMove).getPolygons();
		if (actualMove == Move.WALK_LEFT)
		{
			if(buttonPressed)
			{
				playerImageIndex = (playerImageIndex + images.length - 1) % images.length;
			}
			else
			{
				playerImageIndex = 0;
				actualMove = Move.STANDARD;
			}
		}
		else if (actualMove == Move.WALK_RIGHT)
		{
			if(buttonPressed)
			{
				playerImageIndex = (playerImageIndex + 1) % images.length;
			}
			else
			{
				playerImageIndex = 0;
				actualMove = Move.STANDARD;
			}
		}
		else if (actualMove == Move.GOT_PUNCH)
		{
			playerImageIndex = (playerImageIndex + 1) % images.length;
		}
		else if (actualMove != Move.STANDARD)
		{
			if(buttonPressed && playerImageIndex < images.length - 1)
			{
				playerImageIndex++;
			}
			else if(!buttonPressed && playerImageIndex > 0)
			{
				playerImageIndex--;
			}
			if (playerImageIndex == 0)
			{
				actualMove = Move.STANDARD;
			}
		}
		else
		{
			playerImageIndex = (playerImageIndex + 1) % images.length;
		}
		biShownImage = images[playerImageIndex];
		pShownPolygon = polygons[playerImageIndex];
		return playerImageIndex;
	}
	
	public int getMovement()
	{
		return movement;
	}

	public void setMovement(int movement)
	{
		this.movement = movement;
	}

	public BufferedImage getShownImage()
	{
		return biShownImage;
	}

	public void setShownImage(BufferedImage shownImage)
	{
		this.biShownImage = shownImage;
	}

	public Polygon getShownPolygon()
	{
		return pShownPolygon;
	}

	public void setShownPolygon(Polygon shownPolygon)
	{
		this.pShownPolygon = shownPolygon;
	}

	public Move getActualMove()
	{
		return actualMove;
	}

	public void setActualMove(Move actualMove)
	{
		this.actualMove = actualMove;
	}

	public int getShownImageIndex()
	{
		return shownImageIndex;
	}

	public void setShownImageIndex(int shownImageIndex)
	{
		this.shownImageIndex = shownImageIndex;
	}
}
