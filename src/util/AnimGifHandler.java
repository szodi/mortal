package util;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.stream.ImageOutputStream;

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

	private static BufferedImage[] resizeImages(BufferedImage[] images, int newWidth, int newHeight)
	{
		BufferedImage[] resizedImages = new BufferedImage[images.length];
		for (int i = 0; i < resizedImages.length; i++)
		{
			BufferedImage resized = new BufferedImage(newWidth, newHeight, images[i].getType());
			Graphics g = resized.getGraphics();
			g.drawImage(images[i], 0, 0, newWidth, newHeight, null);
			resizedImages[i] = resized;
		}
		return resizedImages;
	}

	private static int getGifDelayTime(File gifFile)
	{
		try
		{
			ImageReader reader = ImageIO.getImageReadersBySuffix("gif").next();
			reader.setInput(ImageIO.createImageInputStream(gifFile));
			// int i = reader.getMinIndex();
			// int numImages = reader.getNumImages(true);

			IIOMetadata imageMetaData = reader.getImageMetadata(0);
			String metaFormatName = imageMetaData.getNativeMetadataFormatName();

			IIOMetadataNode root = (IIOMetadataNode)imageMetaData.getAsTree(metaFormatName);

			IIOMetadataNode graphicsControlExtensionNode = getNode(root, "GraphicControlExtension");

			String strDelayTime = graphicsControlExtensionNode.getAttribute("delayTime");

			if (strDelayTime != null)
			{
				return Integer.parseInt(strDelayTime);
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return -1;
	}

	public static void resizeGif(File inFile, File outFile, int newWidth, int newHeight)
	{
		BufferedImage[] frames = getFrames(inFile);
		frames = resizeImages(frames, newWidth, newHeight);
		try
		{
			ImageOutputStream output = new FileImageOutputStream(outFile);
			int delay = getGifDelayTime(inFile);
			System.out.println(delay);
			GifSequenceWriter writer = new GifSequenceWriter(output, frames[0].getType(), delay, false);
			writer.writeToSequence(frames[0]);
			for (int i = 1; i < frames.length; i++)
			{
				writer.writeToSequence(frames[i]);
			}

			writer.close();
			output.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	private static IIOMetadataNode getNode(IIOMetadataNode rootNode, String nodeName)
	{
		int nNodes = rootNode.getLength();
		for (int i = 0; i < nNodes; i++)
		{
			if (rootNode.item(i).getNodeName().compareToIgnoreCase(nodeName) == 0)
			{
				return ((IIOMetadataNode)rootNode.item(i));
			}
		}
		IIOMetadataNode node = new IIOMetadataNode(nodeName);
		rootNode.appendChild(node);
		return (node);
	}

	public static void dumpGifFrames(File file, File toDir)
	{
		try
		{
			BufferedImage[] images = getFrames(file);
			for (int i = 0; i < images.length; i++)
			{
				BufferedImage bufferedImage = images[i];
				File outFile = new File(toDir.getAbsolutePath() + "\\" + file.getName() + "_" + i + ".png");
				ImageIO.write(bufferedImage, "PNG", outFile);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}
