package movement;

import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import swing2.Misi;
import swing2.Sprite;
import util.AnimGifHandler;

public class Player
{
	BufferedImage[] standard = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/alapallas.gif"));
	BufferedImage[] block = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/vedekezes.gif"));
	BufferedImage[] punch_up = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/felutes.gif"));
	BufferedImage[] crouch_and_block = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/gugolases-vedekezes.gif"));
//	BufferedImage[] win = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/gyozelem.gif"));
	BufferedImage[] turn_back = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/megfordulas.gif"));
	BufferedImage[] walk = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/seta.gif"));
	BufferedImage[] got_punch = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/hokoles.gif"));
	BufferedImage[] kick = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/rugas.gif"));
	BufferedImage[] punch = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/utes.gif"));
	BufferedImage[] jump = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/ugras_fixed.gif"));
	BufferedImage[] crouch = AnimGifHandler.getFrames(Misi.class.getResourceAsStream("/images/misi/gugolas.gif"));
	
	Movement[] movements;
	
	public Player()
	{
		Movement mvJump = new Movement(new Sprite("MISI_CROUCH", crouch), KeyEvent.VK_W);
		Movement mvCrouch = new Movement(new Sprite("MISI_WALK_BLOCK", walk), KeyEvent.VK_S);
		Movement mvLeft = new Movement(new Sprite("MISI_WALK_LEFT", walk), KeyEvent.VK_A);
		Movement mvRight = new Movement(new Sprite("MISI_WALK_RIGHT", walk), KeyEvent.VK_D);
		Movement mvBlock = new Movement(new Sprite("MISI_WALK_BLOCK", walk), KeyEvent.VK_0);
		Movement mvPunch = new Movement(new Sprite("MISI_WALK_BLOCK", walk), KeyEvent.VK_1);
		Movement mvKick = new Movement(new Sprite("MISI_WALK_BLOCK", walk), KeyEvent.VK_2);
	}
}
