package util;

public class Colors
{
	public static boolean isAlmostEqual(int value1, int value2, int tolerance)
	{
		return Math.abs(value1 - value2) < tolerance;
	}

	public static int rgbToInt(int red, int green, int blue)
	{
		return red << 16 | green << 8 | blue;
	}

	public static int rgbToInt(int alpha, int red, int green, int blue)
	{
		return alpha << 24 | red << 16 | green << 8 | blue;
	}

	public static int getLuminance(int rgb)
	{
		return (getRed(rgb) + getGreen(rgb) + getBlue(rgb)) / 3;
	}

	public static boolean areSimilarColorsByRGB(int rgb1, int rgb2, int tolerance)
	{
		return isAlmostEqual(getRed(rgb1), getRed(rgb2), tolerance) && isAlmostEqual(getGreen(rgb1), getGreen(rgb2), tolerance) && isAlmostEqual(getBlue(rgb1), getBlue(rgb2), tolerance);
	}

	public static boolean areSimilarColorsByHSB(int rgb1, int rgb2, int tolerance)
	{
		return isAlmostEqual(getHue(rgb1), getHue(rgb2), tolerance) && isAlmostEqual(getSaturation(rgb1), getSaturation(rgb2), tolerance) && isAlmostEqual(getLuminance(rgb1), getLuminance(rgb2), tolerance);
	}

	public static int morph(int value1, int value2, double progress)
	{
		return (int)(value1 + (value2 - value1) * progress);
	}

	public static int getAlpha(int rgb)
	{
		return (rgb & 0xff000000) >>> 24;
	}

	public static int getRed(int rgb)
	{
		return (rgb & 0xff0000) >>> 16;
	}

	public static int getGreen(int rgb)
	{
		return (rgb & 0xff00) >>> 8;
	}

	public static int getBlue(int rgb)
	{
		return rgb & 0xff;
	}

	public static int getHue(int rgb)
	{
		float hue;
		float red = getRed(rgb);
		float green = getGreen(rgb);
		float blue = getBlue(rgb);
		float cMin = getMinComponent(rgb);
		float cMax = getMaxComponent(rgb);
		if (cMax != 0)
		{
			if (red == cMax)
			{
				hue = (green - blue) / (cMax - cMin);
			}
			else if (green == cMax)
			{
				hue = 2.0f + (blue - red) / (cMax - cMin);
			}
			else
			{
				hue = 4.0f + (red - green) / (cMax - cMin);
			}
			hue = hue / 6.0f;
			if (hue < 0)
			{
				hue += 1.0f;
			}
			return (int)(255 * hue);
		}
		return 0;
	}

	public static int getSaturation(int rgb)
	{
		int cMin = getMinComponent(rgb);
		int cMax = getMaxComponent(rgb);
		if (cMax == 0)
		{
			return 0;
		}
		return 255 * (cMax - cMin) / cMax;
	}

	private static int getMinComponent(int rgb)
	{
		return Math.min(Math.min(getRed(rgb), getGreen(rgb)), getBlue(rgb));
	}

	private static int getMaxComponent(int rgb)
	{
		return Math.max(Math.max(getRed(rgb), getGreen(rgb)), getBlue(rgb));
	}

	public static int rgbToLuminance(int rgb, int targetLuminance)
	{
		int red = getRed(rgb);
		int green = getGreen(rgb);
		int blue = getBlue(rgb);
		int originalLuminance = Colors.getLuminance(rgb);
		int rgbMax = getMaxComponent(rgb);
		int rgbMin = getMinComponent(rgb);
		int originalDistanceMin = originalLuminance - rgbMin;
		int originalDistanceMax = rgbMax - originalLuminance;
		if (originalDistanceMin > 0)
		{
			int newDistanceMin = targetLuminance - Math.max(rgbMin + targetLuminance - originalLuminance, 0);
			int newDistanceMax = Math.min(rgbMax + targetLuminance - originalLuminance, 255) - targetLuminance;
			double rateMin = (double)newDistanceMin / (double)originalDistanceMin;
			double rateMax = (double)newDistanceMax / (double)originalDistanceMax;
			double rate = Math.min(rateMin, rateMax);
			red = (int)((red - originalLuminance) * rate + targetLuminance);
			green = (int)((green - originalLuminance) * rate + targetLuminance);
			blue = (int)((blue - originalLuminance) * rate + targetLuminance);
			return Colors.rgbToInt(red, green, blue);
		}
		return Colors.rgbToInt(targetLuminance, targetLuminance, targetLuminance);
	}

	public static void main(String[] args)
	{
		int red1 = 0xaa;
		int green1 = 0xbb;
		int blue1 = 0xcc;
		int rgb1 = rgbToInt(red1, green1, blue1);
		int rgb2 = rgbToLuminance(rgb1, 0x0);
		int red2 = getRed(rgb2);
		int green2 = getGreen(rgb2);
		int blue2 = getBlue(rgb2);
		System.out.println(Integer.toHexString(rgb2) + "\t" + Integer.toHexString(getLuminance(rgb2)));
		System.out.println(red1 - red2);
		System.out.println(green1 - green2);
		System.out.println(blue1 - blue2);
	}
}
