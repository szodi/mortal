package util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Stack;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import javax.imageio.ImageIO;

import swing2.Settings;

public class Filler
{
	public static final Point NW = new Point(-1, -1);
	public static final Point N = new Point(0, -1);
	public static final Point NE = new Point(1, -1);
	public static final Point W = new Point(-1, 0);
	public static final Point O = new Point(0, 0);
	public static final Point E = new Point(1, 0);
	public static final Point SW = new Point(-1, 1);
	public static final Point S = new Point(0, 1);
	public static final Point SE = new Point(1, 1);

	public static final Point[] DIRECTIONS_4 = new Point[]{N, W, E, S};
	public static final Point[] DIRECTIONS_5 = new Point[]{O, N, W, E, S};
	public static final Point[] DIRECTIONS_8 = new Point[]{N, W, E, S, NW, NE, SW, SE};
	public static final Point[] DIRECTIONS_9 = new Point[]{O, NW, N, NE, W, E, SW, S, SE};

	public static <E> boolean[][] fill(E[][] field, int i, int j, Point[] directions, BiPredicate<E, E> isAffected, List<Point> outPathPoints)
	{
		Stack<Point> stack = new Stack<Point>();
		stack.add(new Point(i, j));
		boolean[][] marked = createMarkedArray(field);
		while (stack.size() > 0)
		{
			Point point = stack.pop();
			for (Point direction : directions)
			{
				int di = point.x + direction.x;
				int dj = point.y + direction.y;
				if (di >= 0 && di <= (field.length - 1) && dj >= 0 && dj <= (field[di].length - 1) && !marked[di][dj])
				{
					if (isAffected.test(field[i][j], field[di][dj]))
					{
						marked[di][dj] = true;
						Point stackPoint = new Point(di, dj);
						stack.add(stackPoint);
						if (outPathPoints != null)
						{
							outPathPoints.add(stackPoint);
						}
					}
				}
			}
		}
		return marked;
	}

	private static <E> boolean[][] createMarkedArray(E[][] array)
	{
		boolean[][] marked = new boolean[array.length][];
		for (int k = 0; k < array.length; k++)
		{
			marked[k] = new boolean[array[k].length];
		}
		return marked;
	}

	public static int[][] getPixelArray(BufferedImage bi)
	{
		int[][] aPixels = new int[bi.getWidth()][bi.getHeight()];
		for (int j = 0; j < bi.getHeight(); j++)
		{
			for (int i = 0; i < bi.getWidth(); i++)
			{
				aPixels[i][j] = bi.getRGB(i, j);
			}
		}
		return aPixels;
	}

	public static Integer[][] getPixelArrayObj(BufferedImage bi)
	{
		Integer[][] aPixels = new Integer[bi.getWidth()][bi.getHeight()];
		for (int j = 0; j < bi.getHeight(); j++)
		{
			for (int i = 0; i < bi.getWidth(); i++)
			{
				aPixels[i][j] = bi.getRGB(i, j);
			}
		}
		return aPixels;
	}

	public static boolean[][] keepContour(boolean[][] marked)
	{
		boolean[][] contour = new boolean[marked.length][];
		for (int k = 0; k < marked.length; k++)
		{
			contour[k] = new boolean[marked[k].length];
		}
		for (int i = 0; i < contour.length; i++)
		{
			for (int j = 0; j < contour[i].length; j++)
			{
				boolean isContour = false;
				for (Point direction : DIRECTIONS_5)
				{
					int dx = i + direction.x;
					int dy = j + direction.y;
					if (dx >= 0 && dx <= (marked.length - 1) && dy >= 0 && dy <= (marked[dx].length - 1) && !marked[dx][dy] && marked[i][j])
					{
						isContour = true;
						break;
					}
				}
				contour[i][j] = isContour;
			}
		}
		return contour;
	}

	public static void invert(boolean[][] booleanArray)
	{
		for (int i = 0; i < booleanArray.length; i++)
		{
			for (int j = 0; j < booleanArray[i].length; j++)
			{
				booleanArray[i][j] = !booleanArray[i][j];
			}
		}
	}

	public static Point findFirst(BufferedImage image, Predicate<Integer> rgbCriteria)
	{
		for (int y = 0; y < image.getHeight(); y++)
		{
			for (int x = 0; x < image.getWidth(); x++)
			{
				int rgbActual = image.getRGB(x, y);
				if (rgbCriteria.test(rgbActual))
				{
					return new Point(x, y);
				}
			}
		}
		return null;
	}

	public static void main(String[] args)
	{
		try
		{
			BufferedImage image = ImageIO.read(new File(Settings.APP_ROOT + "frames\\scorpion-mortal-kombat-animated-gif-1.gif_5.png"));
			Integer[][] aPixels = getPixelArrayObj(image);
			Point xy = findFirst(image, rgb -> rgb == 0x00f600ff);
			boolean[][] marked = fill(aPixels, xy.x, xy.y, DIRECTIONS_5, (rgb1, rgb2) -> rgb2 == 0x00f600ff, null);
			invert(marked);
			boolean[][] contour = keepContour(marked);
			Graphics g = image.getGraphics();
			for (int mx = 0; mx < marked.length; mx++)
			{
				for (int my = 0; my < marked[mx].length; my++)
				{
					g.setColor(contour[mx][my] ? Color.white : Color.black);
					g.drawLine(mx, my, mx, my);
					// if (marked[mx][my])
					// {
					// g.setColor(contour[mx][my] ? Color.white : Color.blue);
					// g.drawLine(mx, my, mx, my);
					// }
				}
			}
			ImageIO.write(image, "png", new File("frames\\scorpion-mortal-kombat-animated-gif-1.gif_5_.png"));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
