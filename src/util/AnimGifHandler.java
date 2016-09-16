package util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

public class AnimGifHandler
{
	public static BufferedImage[] getFrames(Object input)
	{
		ImageReader is = ImageIO.getImageReadersBySuffix("GIF").next();
		ImageInputStream iis;
		try
		{
			iis = ImageIO.createImageInputStream(input);
			is.setInput(iis);
			int imagesCount = is.getNumImages(true);
			BufferedImage[] images = new BufferedImage[imagesCount];
			for (int i = 0; i < imagesCount; i++)
			{
				BufferedImage bi = is.read(i);
				images[i] = bi;
			}
			return images;
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	public static void dumpGifFrames(File file, File toDir)
	{
		try
		{
			BufferedImage[] images = getFrames(file);
			for (int i = 0; i < images.length; i++)
			{
				BufferedImage bufferedImage = images[i];
				File outFile = new File(toDir.getAbsolutePath() + "/" + file.getName() + "_" + i + ".png");
				ImageIO.write(bufferedImage, "PNG", outFile);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
