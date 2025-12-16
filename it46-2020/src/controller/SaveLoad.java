package controller;

import java.io.File;

public interface SaveLoad<T> {

	void save (T data,File file)throws Exception;
	T load(File file) throws Exception;
}
