package manager;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import exceptions.TreeException;
import referenceBasedTreeImplementation.BSTReferencedBased;
import utilities.Word;

/**
 * Description: Word tracker manager that sorts words read from a file into a BST and 
 * can print the words alphabetically sorted to the screen or a file of the users choosing.
 *
 * @Author: GroupAuron
 */
public class WordTrackerManager
{
	private String FILE_PATH = ""; // store the relative path of the file to be sorted
	private static String FILE_PATHOUTPUT = ""; // store the relative path of the file to be sorted
	private static final String BINARY_FILE = "res/repository.ser";
	private static BSTReferencedBased<Word> bst;

	/**
	 * Initializes the newly created WordTrackerManager
	 * 
	 * @throws IOException when I/O operations fail
	 * @throws TreeException Tree is empty
	 * @throws ClassNotFoundException when class is not found
	 */
	public WordTrackerManager(String[] args) throws ClassNotFoundException, TreeException, IOException
	{
		String printCommand = "";
		String fileOutPut = "";
		int fileOutputIndex = 0;
		
		// running through the whole user arguement to pull out file name,
		// sorting command and file to write to if given
		for (String s : args)
		{
			if (s.contains(".txt"))
			{
				if (FILE_PATH == "")
				{
					FILE_PATH = s;
				}

			}

			if (s.contains("-pf"))
			{
				printCommand = "-pf";
			}

			if (s.contains("-pl"))
			{
				printCommand = "-pl";
			}

			if (s.contains("-po"))
			{
				printCommand = "-po";
			}

			if (s.contains("-f"))
			{
				int index = 0;
				for (String e : args)
				{
					if (e.equals("-f"))
					{
						fileOutputIndex = index;
					}
					index++;
				}
				fileOutPut = printCommand + "f";
				try
				{
					FILE_PATHOUTPUT = "res/output/" + args[fileOutputIndex + 1];
				} catch (IndexOutOfBoundsException e)
				{
					throw new IndexOutOfBoundsException();
				}
			}

		}

		bst = null;
		
		// switch to sort or which sorting view the user wants to see
		switch (printCommand)
		{
			case "-pf":
				readFile();
				outputToRepository();

				if (fileOutPut.endsWith("f") && (printCommand != "" || printCommand != null))
				{
					printLinesCommand("pff");
				} else
				{
					printLinesCommand("pf");
				}
				break;
			case "-pl":
				readFile();
				outputToRepository();
				if (fileOutPut.endsWith("f") && (printCommand != "" || printCommand != null))
				{
					printLinesCommand("plf");
				} else
				{
					printLinesCommand("pl");
				}
				break;
			case "-po":
				readFile();
				outputToRepository();
				if (fileOutPut.endsWith("f") && (printCommand != "" || printCommand != null))
				{
					printLinesCommand("pof");
				} else
				{
					printLinesCommand("po");
				}
				break;
		}

	}

	/**
	 * reading specified text file
	 * 
	 * @throws IOException when I/O operations fail
	 * @throws TreeException Tree is empty
	 * @throws ClassNotFoundException when class is not found
	 * @throws FileNotFoundException when file specified to be read does not exist
	 */
	private void readFile() throws TreeException, IOException, ClassNotFoundException
	{
		int lineNumber = 1;
		String lineRead = " ";

		Path p = Paths.get(FILE_PATH);
		String fileName = p.getFileName().toString();

		bst = new BSTReferencedBased<Word>();

		File checkRepo = new File(BINARY_FILE);
		
		// if repository.ser exists from a previous file read, populate the bst with repositorys.ser
		if (checkRepo.exists())
		{
			readRepository();
		}

		FileReader fr;
		BufferedReader br;
		// reading the file line by line and adding the words as Word class into a BST
		try
		{
			fr = new FileReader(FILE_PATH);
			br = new BufferedReader(fr);

			while ((lineRead = br.readLine()) != null)
			{
				String[] wholeLine = lineRead.split("[\\W]");

				for (int i = 0; i < wholeLine.length; i++)
				{

					if (!wholeLine[i].equals(""))
					{

						Word word = new Word(fileName, lineNumber, wholeLine[i]);

						bst.add(word);

					}
				}
				lineNumber++;
				br.readLine();
				lineNumber++;
			}

		} catch (IOException e)
		{
			e.printStackTrace();
		}

	}

	/**
	 * reading binary file
	 * 
	 * @throws IOException when I/O operations fail
	 * @throws ClassNotFoundException when class is not found
	 */
	private void readRepository() throws IOException, ClassNotFoundException
	{
		FileInputStream savedFile = new FileInputStream(BINARY_FILE);
		ObjectInputStream readBST = new ObjectInputStream(savedFile);
		boolean canRead = true;

		try
		{
			while (canRead)
			{
				BSTReferencedBased<Word> temp = new BSTReferencedBased<Word>();
				temp = (BSTReferencedBased<Word>) readBST.readObject();
				while (temp.inorderIterator().hasNext())
				{
					bst.add(temp.inorderIterator().next());
				}
			}
		} catch (EOFException e)
		{
			canRead = false;
		}
		savedFile.close();
		readBST.close();
	}

	/**
	 * outputting to binary file
	 * 
	 * @throws IOException when I/O operations fail
	 * @throws TreeException Tree is empty
	 * @throws NoSuchElementException
	 */
	private static void outputToRepository() throws IOException, NoSuchElementException, TreeException
	{
		FileOutputStream outFile = new FileOutputStream(BINARY_FILE);
		ObjectOutputStream out = new ObjectOutputStream(outFile);

		out.writeObject(bst);

		out.close();
		outFile.close();

	}

	/**
	 * 
	 * return a list of words that are the same as searchWord param
	 * 
	 * @param wordsList
	 *            list of of word objects to search from
	 * @param searchWord
	 *            word to search for in the list of word objects
	 * @return a list of words that match param searchWord
	 */
	private List<Word> getWord(List<Word> wordsList, String searchWord, String fileName)
	{

		return wordsList.stream()
				.filter(word -> word.getElement().equals(searchWord) && word.getFileName().equals(fileName))
				.collect(Collectors.toList());

	}

	/**
	 * print based on what the user wants to see
	 * 
	 * @throws NoSuchElementException
	 * @throws IOException when I/O operations fail
	 * @throws TreeException Tree is empty
	 * @throws ClassNotFoundException when class is not found
	 */
	private void printLinesCommand(String command)
			throws IOException, NoSuchElementException, TreeException, ClassNotFoundException
	{
		// adding Word class nodes into the arraylist through an iterator
		ArrayList<Word> wordList = new ArrayList<Word>();
		while (bst.inorderIterator().hasNext())
		{
			Word word = bst.inorderIterator().next();
			wordList.add(word);
		}

		// reset the iterator
		readRepository();

		
		ArrayList<ArrayList<Word>> aList = new ArrayList<ArrayList<Word>>();
		
		// add list of unique words returned for the getWord() method to aList above
		while (bst.inorderIterator().hasNext())
		{
			ArrayList<Word> uniqueWord = new ArrayList<Word>();
			Word temp = bst.inorderIterator().next();
			uniqueWord = (ArrayList<Word>) getWord(wordList, temp.getElement(), temp.getFileName());

			if (!aList.contains(uniqueWord))
				aList.add(uniqueWord);

		}
		
		
		FileWriter outPutFile = null;
        try {
            outPutFile = new FileWriter(FILE_PATHOUTPUT);
        } catch (FileNotFoundException e) {
        }
        
		for (ArrayList<Word> e : aList)
		{
			String element = "";
			String lineNumber = "";
			String fileName = "";
			for (Word f : e)
			{
				lineNumber += f.getLineNumber() + " ";
				element = f.getElement();
				fileName = f.getFileName();
			}

			switch (command)
			{
				case "pf":
					System.out.println("Word: " + element + "\nFile Name: " + fileName);
					System.out.println(" ");
					break;
				case "pl":
					System.out
							.println("Word: " + element + "\nLine Number : " + lineNumber + "\nFile Name: " + fileName);
					System.out.println(" ");
					break;
				case "po":
					System.out.println("Word: " + element + "\nFrequency: " + e.size() + "\nLine Number : " + lineNumber
							+ "\nFile Name: " + fileName);
					System.out.println(" ");
					break;
				case "pff":
					outPutFile.write("Word: " + element + "\nFile Name: " + fileName);
					outPutFile.write("\n \n");
					break;
				case "plf":
					outPutFile.write("Word: " + element + "\nLine Number : " + lineNumber + "\nFile Name: " + fileName);
					outPutFile.write("\n \n");
					break;
				case "pof":
					outPutFile.write("Word: " + element + "\nFrequency: " + e.size() + "\nLine Number : " + lineNumber
							+ "\nFile Name: " + fileName);
					outPutFile.write("\n \n");
					break;
			}

		}
		
		if( command == "pff" || command == "plf" || command == "pof" ) System.out.println("Report created. Please check the output.txt file under res/output.");
		
		 if (outPutFile !=null) outPutFile.close();

	}
}
