package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.util.List;
import geometrija.Shape;

public class SaveDrawingStrategy implements SaveStrategy{
	
	@Override
	public void save(File file,List<Shape> shapes) throws Exception {
		
		ObjectOutput oos= new ObjectOutputStream(new FileOutputStream(file));
		
		oos.writeObject(shapes);
		
		oos.close();
	}
	@Override
    public List<Shape> load(File file) throws Exception {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        List<Shape> shapes = (List<Shape>) ois.readObject();
        ois.close();
        return shapes;
    }

}
