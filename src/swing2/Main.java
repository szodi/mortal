package swing2;

import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class Main
{
	public static void main(String[] args)
	{
		System.out.println(args[0]);
		if(args.length > 0)
		{
			Settings.APP_ROOT = args[0] + "/";
			System.out.println("Settings.APP_ROOT has been set to : " + Settings.APP_ROOT );
		}
//		final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();

		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				JFrame frame = new JFrame();
				GamePanel ip = new GamePanel();
				frame.add(ip);

				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setSize(new Dimension(1280, 1024));
//				frame.setUndecorated(true);
				frame.addKeyListener(ip);
				frame.setVisible(true);
				ip.start();
			}
		});
	}
}
