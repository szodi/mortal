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

	Image image = Toolkit.getDefaultToolkit().createImage(this.getClass().getResource("/images/ccbg_fix.jpg"));

	Fighter[] fighters;

	boolean showPolyon = true;
	boolean showImage = true;

	int player1ImageIndex = 0;
	int player2ImageIndex = 0;
	
	boolean pressed1 = false;
	boolean pressed2 = false;

	public GamePanel()
	{
		fighters = new Fighter[2];
	}

	public void start()
	{
		fighters[0] = new Misi();
		fighters[0].setVerticalOffset(450);
		fighters[0].setHorizontalOffset(600);
		fighters[1] = new Mate();
		fighters[1].setVerticalOffset(450);
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
		if (Fighter.mKeyMoves1.containsKey(e.getKeyCode()) && Fighter.mKeyMoves1.get(e.getKeyCode()) != fighters[0].getActualMove())
		{
			Move move = Fighter.mKeyMoves1.get(e.getKeyCode());
			fighters[0].doMove(move);
			pressed1 = true;
			player1ImageIndex = 0;
		}
		else if (Fighter.mKeyMoves2.containsKey(e.getKeyCode()) && Fighter.mKeyMoves2.get(e.getKeyCode()) != fighters[1].getActualMove())
		{
			Move move = Fighter.mKeyMoves2.get(e.getKeyCode());
			fighters[1].doMove(move);
			pressed2 = true;
			player2ImageIndex = 0;
		}
		if (e.getKeyCode() == KeyEvent.VK_P)
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
		if (Fighter.mKeyMoves1.containsKey(e.getKeyCode()))
		{
			pressed1 = false;
		}
		else if (Fighter.mKeyMoves2.containsKey(e.getKeyCode()))
		{
			pressed2 = false;
		}
	}

	@Override
	public void run()
	{
		while (true)
		{
			player1ImageIndex = fighters[0].move(pressed1, player1ImageIndex);
			player2ImageIndex = fighters[1].move(pressed2, player2ImageIndex);
			showImage = !CollisionDetector.isIntersected(fighters[0].getShownPolygon(), fighters[1].getShownPolygon());
			try
			{
				Thread.sleep(20);
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			repaint();
		}
	}
}
