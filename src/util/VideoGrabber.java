package util;

import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import org.jcodec.api.awt.FrameGrab;
import org.jcodec.common.FileChannelWrapper;
import org.jcodec.common.NIOUtils;

public class VideoGrabber
{

	public static void main(String[] args)
	{
		try
		{
			FileChannelWrapper ch = NIOUtils.readableFileChannel(new File("video.mp4"));
			FrameGrab grab = new FrameGrab(ch);

			for (int i = 30000; i < 31000; i++)
			{
				FrameGrab fg = (FrameGrab)grab.seekToFramePrecise(i);
				BufferedImage image = fg.getFrame();
				System.out.println(i);
				ImageIO.write(image, "png", new File("frame_" + i + ".png"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
