package swing2;

import java.awt.Polygon;

public class CollisionDetector
{
	public static boolean isIntersected(Polygon p1, Polygon p2)
	{
		for (int i = 0; i < p1.npoints; i++)
		{
			if (p2.contains(p1.xpoints[i], p1.ypoints[i]))
			{
				return true;
			}
		}
		for (int i = 0; i < p2.npoints; i++)
		{
			if (p1.contains(p2.xpoints[i], p2.ypoints[i]))
			{
				return true;
			}
		}
		return false;
	}
}
