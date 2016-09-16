package swing2;

import java.awt.Polygon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import util.Image2Polygon;

public class Sprite
{
	public static final boolean FORCE_GENERATION = false;

	String id;
	BufferedImage[] images;
	private Polygon[] polygons;

	public Sprite(String id, BufferedImage[] images)
	{
		this.id = id;
		this.images = images;
		generatePolygons();
	}

	public void generatePolygons()
	{
		polygons = new Polygon[images.length];
		for (int i = 0; i < images.length; i++)
		{
			generatePolygon(i);
		}
	}

	private void generateAndSavePolygon(int index)
	{
		polygons[index] = Image2Polygon.getContourPolygon(images[index], 0x00ffffff);
		try
		{
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(Settings.APP_ROOT + "generated_polygon" + "/" + id + "_" + index + ".ply")));
			oos.writeObject(polygons[index]);
			oos.flush();
			oos.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	public void generatePolygon(int index)
	{
		if (!FORCE_GENERATION)
		{
			try
			{
				ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(Settings.APP_ROOT + "generated_polygon" + "/" + id + "_" + index + ".ply")));
				polygons[index] = (Polygon)ois.readObject();
				ois.close();
			}
			catch (Exception e)
			{
				generateAndSavePolygon(index);
			}
		}
		else
		{
			generateAndSavePolygon(index);
		}
		System.out.println(polygons[index].npoints + "\t" + id);
	}

	public Polygon[] getPolygons()
	{
		return polygons;
	}

	public BufferedImage[] getImages()
	{
		return images;
	}
}
