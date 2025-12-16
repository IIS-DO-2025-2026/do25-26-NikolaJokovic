package controller;

public interface Command {

	void execute();
	void unexecute();
	String toLog();
}
