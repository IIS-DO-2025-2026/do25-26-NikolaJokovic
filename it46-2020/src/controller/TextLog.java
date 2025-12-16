package controller;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.List;

public class TextLog implements SaveLoad<List<String>> {
	
	@Override
	public void save(List<String> data, File file) throws Exception {
        Files.write(file.toPath(), data, StandardCharsets.UTF_8);
    }
    @Override
    public List<String> load(File file) throws Exception {
        return Files.readAllLines(file.toPath(), StandardCharsets.UTF_8);
    }

}
