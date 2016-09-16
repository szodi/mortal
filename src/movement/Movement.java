package movement;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import swing2.Sprite;

public class Movement implements Runnable, KeyListener
{
	Sprite sprite;

	boolean pressed = false;
	
	BufferedImage image;
	
	int keyEvent;
	
	public Movement(Sprite sprite, int keyEvent)
	{
		this.sprite = sprite;
		this.keyEvent = keyEvent;
	}

	public void start()
	{
		new Thread(this).start();
	}
	
	public BufferedImage getImage()
	{
		return image;
	}
	
	@Override
	public void run()
	{
		int playerImageIndex = 0;
		while(true)
		{
			if(pressed && playerImageIndex < sprite.getImages().length - 1)
			{
				playerImageIndex++;
			}
			else if(!pressed && playerImageIndex > 0)
			{
				playerImageIndex--;
			}
			image = sprite.getImages()[playerImageIndex];
			try
			{
				Thread.sleep(50);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	
	@Override
	public void keyPressed(KeyEvent e)
	{
		if(e.getKeyCode() == keyEvent)
		{
			pressed = true;
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
		if(e.getKeyCode() == keyEvent)
		{
			pressed = false;
		}
	}
	
	@Override
	public void keyTyped(KeyEvent e)
	{
	}
}
