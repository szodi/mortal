package swing2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Polygon;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;

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
		fighters[0] = new Imi();
		fighters[0].setVerticalOffset(450);
		fighters[0].setHorizontalOffset(600);
		fighters[1] = new Mate();
		fighters[1].setVerticalOffset(450);
		fighters[1].setHorizontalOffset(400);
		MusicPlayer.start(new File(Settings.APP_ROOT + "sounds/06-lee-groves-carmageddon-ost-desert.wav"));
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
		g.setColor(Color.red);
		g.drawString(fighters[0].getActualMove().toString(), 50, 50);
		g.setColor(Color.green);
		g.drawString(fighters[1].getActualMove().toString(), 250, 50);
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
			if(move == Move.PUNCH || move == Move.KICK)
			{
				MusicPlayer.start(new File(Settings.APP_ROOT + "sounds/mk1-00213.wav"));
			}
			if (fighters[0].getActualMove() == Move.CROUCH)
			{
				if (move == Move.PUNCH)
				{
					move = Move.PUNCH_UP;
				}
				else if (move == Move.BLOCK)
				{
					move = Move.CROUCH_AND_BLOCK;
				}
			}
			else if (fighters[0].getActualMove() == Move.PUNCH_UP)
			{
				if (move == Move.PUNCH)
				{
					move = Move.PUNCH_UP;
					return;
				}
			}
			else if (fighters[0].getActualMove() == Move.CROUCH_AND_BLOCK)
			{
				if (move == Move.BLOCK)
				{
					move = Move.CROUCH_AND_BLOCK;
					return;
				}
			}
			fighters[0].doMove(move);
			pressed1 = true;
			player1ImageIndex = 0;
		}
		else if (Fighter.mKeyMoves2.containsKey(e.getKeyCode()) && Fighter.mKeyMoves2.get(e.getKeyCode()) != fighters[1].getActualMove())
		{
			Move move = Fighter.mKeyMoves2.get(e.getKeyCode());
			if(move == Move.PUNCH || move == Move.KICK)
			{
				MusicPlayer.start(new File(Settings.APP_ROOT + "sounds/mk1-00213.wav"));
			}			
			if (fighters[1].getActualMove() == Move.CROUCH)
			{
				if (move == Move.PUNCH)
				{
					move = Move.PUNCH_UP;
				}
				else if (move == Move.BLOCK)
				{
					move = Move.CROUCH_AND_BLOCK;
				}
			}
			else if (fighters[1].getActualMove() == Move.PUNCH_UP)
			{
				if (move == Move.PUNCH)
				{
					move = Move.PUNCH_UP;
					return;
				}
			}
			else if (fighters[1].getActualMove() == Move.CROUCH_AND_BLOCK)
			{
				if (move == Move.BLOCK)
				{
					move = Move.CROUCH_AND_BLOCK;
					return;
				}
			}
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
			if (fighters[0].getActualMove() == Move.CROUCH_AND_BLOCK && Fighter.mKeyMoves1.get(e.getKeyCode()) == Move.BLOCK)
			{
				fighters[0].doMove(Move.CROUCH);
			}
			else
			{
				pressed1 = false;
			}
		}
		else if (Fighter.mKeyMoves2.containsKey(e.getKeyCode()))
		{
			if (fighters[1].getActualMove() == Move.CROUCH_AND_BLOCK && Fighter.mKeyMoves2.get(e.getKeyCode()) == Move.BLOCK)
			{
				fighters[1].doMove(Move.CROUCH);
			}
			else
			{
				pressed2 = false;
			}
		}
	}

	@Override
	public void run()
	{
		while (true)
		{
			player1ImageIndex = fighters[0].getImageIndex(pressed1, player1ImageIndex);
			player2ImageIndex = fighters[1].getImageIndex(pressed2, player2ImageIndex);
			if (CollisionDetector.isIntersected(fighters[0].getShownPolygon(), fighters[1].getShownPolygon()))
			{
				if (fighters[0].getActualMove() == Move.KICK || fighters[0].getActualMove() == Move.PUNCH || fighters[0].getActualMove() == Move.PUNCH_UP)
				{
					fighters[1].setActualMove(Move.GOT_PUNCH);
				}
				if (fighters[1].getActualMove() == Move.KICK || fighters[1].getActualMove() == Move.PUNCH || fighters[1].getActualMove() == Move.PUNCH_UP)
				{
					fighters[0].setActualMove(Move.GOT_PUNCH);
				}
			}
			// showImage =
			// !CollisionDetector.isIntersected(fighters[0].getShownPolygon(),
			// fighters[1].getShownPolygon());
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
