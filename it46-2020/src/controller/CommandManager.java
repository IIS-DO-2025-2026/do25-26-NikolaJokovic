package controller;
import java.util.*;


public class CommandManager {
	
	private final Stack<Command> undoStack = new Stack<>();
	private final Stack<Command> redoStack = new Stack<>();
	private final List<String> log = new ArrayList<>();
	

	public void executeCommand (Command c) {
		c.execute();
		undoStack.push(c);
		redoStack.clear();
		
		String line = c.toLog();
	    if (line != null && !line.isBlank()) {
	        log.add(line);
	    }
	}
	
	public void undo() {
		if(undoStack.isEmpty())return;
		
		Command c = undoStack.pop();
		c.unexecute();
		redoStack.push(c);
	}	
	
	public void redo() {
		if(redoStack.isEmpty())return;
		
		Command c = redoStack.pop();
		c.execute();
		undoStack.push(c);
	}
	
	public void clearAll() {
	    undoStack.clear();
	    redoStack.clear();
	    log.clear();
	}
	
	public boolean canUndo() { return !undoStack.isEmpty(); }
    public boolean canRedo() { return !redoStack.isEmpty(); }
    public List<String> getLog() { return log; }


	
}
