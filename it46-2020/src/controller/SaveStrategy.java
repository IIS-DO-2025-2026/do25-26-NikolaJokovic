package controller;

import java.io.File;
import java.util.List;
import geometrija.Shape;

public interface SaveStrategy {

	void save(File file,List<Shape> shapes) throws Exception;
	List<Shape> load(File file) throws Exception;
}
