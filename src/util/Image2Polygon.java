package util;

import static util.Filler.DIRECTIONS_5;
import static util.Filler.DIRECTIONS_8;
import static util.Filler.DIRECTIONS_9;
import static util.Filler.E;
import static util.Filler.N;
import static util.Filler.NE;
import static util.Filler.NW;
import static util.Filler.O;
import static util.Filler.S;
import static util.Filler.SE;
import static util.Filler.SW;
import static util.Filler.W;
import static util.Filler.fill;
import static util.Filler.findFirst;
import static util.Filler.getPixelArrayObj;
import static util.Filler.invert;
import static util.Filler.keepContour;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import swing2.Settings;

public class Image2Polygon
{
	static Map<Point, List<Point>> mOppositeMask = new HashMap<>();

	static
	{
		mOppositeMask.put(NW, Arrays.asList(NE, E, SW, S, SE));
		mOppositeMask.put(N, Arrays.asList(SW, S, SE));
		mOppositeMask.put(NE, Arrays.asList(NW, W, SW, S, SE));
		mOppositeMask.put(W, Arrays.asList(NE, E, SE));
		mOppositeMask.put(E, Arrays.asList(NW, W, SW));
		mOppositeMask.put(SW, Arrays.asList(SE, E, NW, N, NE));
		mOppositeMask.put(S, Arrays.asList(NW, N, NE));
		mOppositeMask.put(SE, Arrays.asList(SW, W, NW, N, NE));
		mOppositeMask.put(O, Arrays.asList(DIRECTIONS_8));
	}

	public static Polygon getContourPolygon(BufferedImage image, int backgroundRGB)
	{
		int refRGB = image.getRGB(0, 0) & 0x00ffffff;
		BufferedImage biBordered = createBorder(image, refRGB);
		BufferedImage biContour = getMaskedImage(biBordered, refRGB);
		while (cleanMaskedImage(biContour) > 0)
			;
		// biContour = getMaskedImage(biContour, biContour.getRGB(0, 0) &
		// 0x00ffffff);

		Point startPoint = findFirst(biContour, rgb -> Colors.areSimilarColorsByRGB(rgb, backgroundRGB, 1));
		Integer[][] aPixels = getPixelArrayObj(biContour);
		List<Point> path = new ArrayList<>();
		fill(aPixels, startPoint.x, startPoint.y, DIRECTIONS_9, (rgb1, rgb2) -> Colors.areSimilarColorsByRGB(rgb1, rgb2, 1), path);
		return reduceLines(pointList2Polygon(path));
	}

	private static BufferedImage getMaskedImage(BufferedImage image, int backgroundRGB)
	{
		Integer[][] aPixels = getPixelArrayObj(image);
		Point xy = findFirst(image, rgb -> (rgb & 0x00ffffff) == backgroundRGB);
		boolean[][] marked = fill(aPixels, xy.x, xy.y, DIRECTIONS_5, (rgb1, rgb2) -> Colors.areSimilarColorsByRGB(rgb1, rgb2, 35), null);
		invert(marked);
		marked = keepContour(marked);
		Graphics g = image.getGraphics();
		for (int mx = 0; mx < marked.length; mx++)
		{
			for (int my = 0; my < marked[mx].length; my++)
			{
				g.setColor(marked[mx][my] ? Color.white : Color.black);
				g.drawLine(mx, my, mx, my);
			}
		}
		return image;
	}

	private static BufferedImage createBorder(BufferedImage image, int backgroundRGB)
	{
		BufferedImage biBordered = new BufferedImage(image.getWidth() + 2, image.getHeight() + 2, BufferedImage.TYPE_3BYTE_BGR);
		Graphics g = biBordered.getGraphics();
		g.setColor(new Color(backgroundRGB));
		g.fillRect(0, 0, biBordered.getWidth(), biBordered.getHeight());
		biBordered.setRGB(0, 0, backgroundRGB);
		g.drawImage(image, 1, 1, null);
		return biBordered;
	}

	private static int cleanMaskedImage(BufferedImage image)
	{
		Graphics g = image.getGraphics();
		g.setColor(Color.black);
		int erasedCount = 0;
		for (int y = 0; y < image.getHeight(); y++)
		{
			for (int x = 0; x < image.getWidth(); x++)
			{
				if (isErasablePixel(image, x, y))
				{
					erasedCount++;
					g.drawLine(x, y, x, y);
				}
			}
		}
		return erasedCount;
	}

	private static boolean isErasablePixel(BufferedImage image, int x, int y)
	{
		if ((image.getRGB(x, y) & 0x00ffffff) != 0x00ffffff)
		{
			return false;
		}
		for (Point point : DIRECTIONS_9)
		{
			int dx = x + point.x;
			int dy = y + point.y;
			int rgb = image.getRGB(dx, dy) & 0x00ffffff;
			if (rgb == 0x00ffffff)
			{
				boolean found = false;
				List<Point> lOpposites = mOppositeMask.get(point);
				for (Point opposite : lOpposites)
				{
					int ox = x + opposite.x;
					int oy = y + opposite.y;
					int rgbOpposite = image.getRGB(ox, oy) & 0x00ffffff;
					if (rgbOpposite == 0x00ffffff)
					{
						found = true;
						break;
					}
				}
				if (!found)
				{
					return true;
				}
			}
		}
		return false;
	}

	private static Polygon pointList2Polygon(List<Point> points)
	{
		int n = points.size();
		int[] xpoints = new int[n];
		int[] ypoints = new int[n];
		for (int i = 0; i < n; i++)
		{
			Point point = points.get(i);
			xpoints[i] = point.x;
			ypoints[i] = point.y;
		}
		return new Polygon(xpoints, ypoints, n);
	}

	private static Polygon reduceLines(Polygon polygon)
	{
		int[] xpoints = polygon.xpoints;
		int[] ypoints = polygon.ypoints;
		int npoints = polygon.npoints;
		List<Integer> xCoords = new ArrayList<>();
		List<Integer> yCoords = new ArrayList<>();
		for (int i = 0; i < npoints; i++)
		{
			int previousIndex = (i - 1 + npoints) % npoints;
			int nextIndex = (i + 1) % npoints;
			Point pPrevious = new Point(xpoints[previousIndex], ypoints[previousIndex]);
			Point pNext = new Point(xpoints[nextIndex], ypoints[nextIndex]);
			Point pActual = new Point(xpoints[i], ypoints[i]);
			if (!isOneLine(pPrevious, pActual, pNext))
			{
				xCoords.add(xpoints[i]);
				yCoords.add(ypoints[i]);
			}
		}
		int n = xCoords.size();
		xpoints = new int[n];
		ypoints = new int[n];
		for (int i = 0; i < n; i++)
		{
			xpoints[i] = xCoords.get(i);
			ypoints[i] = yCoords.get(i);
		}
		return new Polygon(xpoints, ypoints, n);
	}

	private static boolean isOneLine(Point p1, Point p2, Point p3)
	{
		return (p3.x - p1.x) * (p3.y - p2.y) == (p3.y - p1.y) * (p3.x - p2.x);
	}

	private static BufferedImage polygon2Image(Polygon polygon, BufferedImage image)
	{
		BufferedImage result = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
		Graphics g = result.getGraphics();
		g.setColor(Color.white);
		g.fillPolygon(polygon);
		return result;
	}

	//
	// private static BufferedImage pointSet2Image(Set<Point> pointSet,
	// BufferedImage image)
	// {
	// BufferedImage result = new BufferedImage(image.getWidth(),
	// image.getHeight(), image.getType());
	// Graphics g = result.getGraphics();
	// g.setColor(Color.white);
	// for (Point point : pointSet)
	// {
	// g.drawLine(point.x, point.y, point.x, point.y);
	// }
	// return result;
	// }
	//
	public static final void main(String[] args)
	{
		try
		{
			File file = new File(Settings.APP_ROOT + "images\\scorpion\\got_punch.gif");
			BufferedImage[] images = AnimGifHandler.getFrames(file);
			Polygon[] polys = new Polygon[images.length];
			for (int i = 0; i < images.length; i++)
			{
				polys[i] = Image2Polygon.getContourPolygon(images[i], 0x00ffffff);
				BufferedImage biPoly = polygon2Image(polys[i], images[i]);
				System.out.println(polys[i].npoints);
				ImageIO.write(biPoly, "png", new File(Settings.APP_ROOT + "frames\\poly_" + i + ".png"));
				// ImageIO.write(biContour, "png", new
				// File("C:\\workspace\\HackatonCC\\Game\\src\\frames\\contour_"
				// + i + ".png"));
				// ImageIO.write(biCleaned, "png", new
				// File("C:\\workspace\\HackatonCC\\Game\\src\\frames\\contour_"
				// + i + "_.png"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
