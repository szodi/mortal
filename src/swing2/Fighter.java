package swing2;

import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Map;

public abstract class Fighter
{
	Map<Move, Sprite> mMoves = new HashMap<>();

	int horizontalOffset = 0;
	int verticalOffset = 0;
	int movement = 8;

	BufferedImage biShownImage;
	Polygon pShownPolygon;

	int shownImageIndex;

	Move actualMove = Move.STANDARD;

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

	public void setshownPolygon(Polygon shownPolygon)
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
