package app;

import java.io.IOException;
import exceptions.TreeException;
import manager.WordTrackerManager;

/**
 * A program that tracks the position of words.
 * This class is an entry point that can output the file location,
 * line location, and occurrence time of words
 * @author Group Auron
 */
public class WordTracker
{
	public static void main(String[] args) throws ClassNotFoundException, TreeException, IOException
	{
		WordTrackerManager wt = new WordTrackerManager(args);
		
	}

}
