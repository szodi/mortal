package swing2;

import java.awt.Dimension;
import java.awt.Toolkit;

//from w w w . j  av a 2 s  .c o  m
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main
{
	public static void main(String[] args)
	{
		final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				JFrame frame = new JFrame();
				GamePanel ip = new GamePanel();
				frame.add(ip);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(SCREEN_SIZE);
				frame.setUndecorated(true);
				frame.addKeyListener(ip);
				frame.setVisible(true);
				ip.start();
			}
		});
	}
}
