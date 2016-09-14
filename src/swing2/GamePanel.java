package swing2;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable, KeyListener
{
	private static final long serialVersionUID = -729852966251240571L;

	Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/images/P1080098.JPG"));

	Fighter[] fighters;

	boolean showPolyon = true;
	boolean showImage = true;

	public GamePanel()
	{
		fighters = new Fighter[2];
	}

	public void start()
	{
		fighters[0] = new Scorpion();
		fighters[1] = new Misi();
		fighters[1].setHorizontalOffset(400);
		new Thread(this).start();
	}

	@Override
	public void setVisible(boolean aFlag)
	{
		super.setVisible(aFlag);
		start();
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		if (image != null)
		{
			g.drawImage(image, 0, 0, this);
		}
		for (Fighter fighter : fighters)
		{
			if (fighter != null)
			{
				if (showImage)
				{
					BufferedImage biShownImage = fighter.getShownImage();
					if (biShownImage != null)
					{
						g.drawImage(biShownImage, fighter.getHorizontalOffset(), fighter.getVerticalOffset(), this);
					}
				}
				if (showPolyon)
				{
					Polygon pShownPolygon = fighter.getShownPolygon();
					if (pShownPolygon != null)
					{
						g.drawPolygon(pShownPolygon);
					}
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT && fighters[0].getHorizontalOffset() > 0)
		{
			fighters[0].setHorizontalOffset(fighters[0].getHorizontalOffset() - fighters[0].getMovement());
			fighters[0].setActualMove(Move.GOT_PUNCH);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT && fighters[0].getHorizontalOffset() < 1000)
		{
			fighters[0].setHorizontalOffset(fighters[0].getHorizontalOffset() + fighters[0].getMovement());
			fighters[0].setActualMove(Move.WALK);
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP && fighters[0].getVerticalOffset() > 0)
		{
			fighters[0].setVerticalOffset(fighters[0].getVerticalOffset() - fighters[0].getMovement());
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN && fighters[0].getVerticalOffset() < 1000)
		{
			fighters[0].setVerticalOffset(fighters[0].getVerticalOffset() + fighters[0].getMovement());
		}
		else if (e.getKeyCode() == KeyEvent.VK_A && fighters[0].getHorizontalOffset() > 0)
		{
			fighters[1].setHorizontalOffset(fighters[1].getHorizontalOffset() - fighters[1].getMovement());
			fighters[1].setActualMove(Move.WALK);
		}
		else if (e.getKeyCode() == KeyEvent.VK_D && fighters[1].getHorizontalOffset() < 1000)
		{
			fighters[1].setHorizontalOffset(fighters[1].getHorizontalOffset() + fighters[1].getMovement());
			fighters[1].setActualMove(Move.WALK);
		}
		else if (e.getKeyCode() == KeyEvent.VK_W && fighters[1].getVerticalOffset() > 0)
		{
			fighters[1].setVerticalOffset(fighters[1].getVerticalOffset() - fighters[1].getMovement());
		}
		else if (e.getKeyCode() == KeyEvent.VK_S && fighters[1].getVerticalOffset() < 1000)
		{
			fighters[1].setVerticalOffset(fighters[1].getVerticalOffset() + fighters[1].getMovement());
		}
		else if (e.getKeyCode() == KeyEvent.VK_1)
		{
			fighters[1].setActualMove(Move.KICK);
		}
		else if (e.getKeyCode() == KeyEvent.VK_2)
		{
			fighters[1].setActualMove(Move.PUNCH);
		}
		else if (e.getKeyCode() == KeyEvent.VK_P)
		{
			showPolyon = !showPolyon;
		}
		else if (e.getKeyCode() == KeyEvent.VK_I)
		{
			showImage = !showImage;
		}

	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
		{
			fighters[0].setActualMove(Move.STANDARD);
		}
		else if (e.getKeyCode() == KeyEvent.VK_RIGHT)
		{
			fighters[0].setActualMove(Move.STANDARD);
		}
		else if (e.getKeyCode() == KeyEvent.VK_UP)
		{
			fighters[0].setActualMove(Move.STANDARD);
		}
		else if (e.getKeyCode() == KeyEvent.VK_DOWN)
		{
			fighters[0].setActualMove(Move.STANDARD);
		}
		else if (e.getKeyCode() == KeyEvent.VK_A)
		{
			fighters[1].setActualMove(Move.STANDARD);
		}
		else if (e.getKeyCode() == KeyEvent.VK_D)
		{
			fighters[1].setActualMove(Move.STANDARD);
		}
		else if (e.getKeyCode() == KeyEvent.VK_W)
		{
			fighters[1].setActualMove(Move.STANDARD);
		}
		else if (e.getKeyCode() == KeyEvent.VK_S)
		{
			fighters[1].setActualMove(Move.STANDARD);
		}
	}

	@Override
	public void run()
	{
		int player1ImageIndex = 0;
		int player2ImageIndex = 0;
		while (true)
		{
			BufferedImage[] images1 = fighters[0].getSprite(fighters[0].getActualMove()).getImages();
			Polygon[] polygons1 = fighters[0].getSprite(fighters[0].getActualMove()).getPolygons();
			player1ImageIndex = (player1ImageIndex + 1) % images1.length;
			fighters[0].setShownImage(images1[player1ImageIndex]);
			fighters[0].setshownPolygon(polygons1[player1ImageIndex]);
			BufferedImage[] images2 = fighters[1].getSprite(fighters[1].getActualMove()).getImages();
			Polygon[] polygons2 = fighters[1].getSprite(fighters[1].getActualMove()).getPolygons();
			player2ImageIndex = (player2ImageIndex + 1) % images2.length;
			fighters[1].setShownImage(images2[player2ImageIndex]);
			fighters[1].setshownPolygon(polygons2[player2ImageIndex]);
			showImage = !CollisionDetector.isIntersected(fighters[0].getShownPolygon(), fighters[1].getShownPolygon());
			try
			{
				Thread.sleep(50);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			repaint();
		}
	}
}
