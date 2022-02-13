package utilities;

import java.io.Serializable;

/**
 Assignment Description:
 *
 @Author: YunZe (David) Wei - 861349
 */
public class Word implements Serializable, Comparable<Word>
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 616069112350568535L;
	private String fileName;
	private int lineNumber;
	private String element;
	
	/**
	Initializes the newly created Word
	 */
	public Word()
	{
		
	}
	
	/**
	 * 
	Initializes the newly created Word
	@param fileName
	@param lineNumber
	 */
	public Word(String fileName, int lineNumber, String element)
	{
		this.fileName = fileName;
		this.lineNumber = lineNumber;
		this.element = element;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getFileName()
	{
		return fileName;
	}
	
	/**
	 * 
	 * @param fileName
	 */
	public void setFileName(String fileName)
	{
		this.fileName = fileName;
	}
	
	/**
	 * 
	 * @return
	 */
	public int getLineNumber()
	{
		return lineNumber;
	}
	
	/**
	 * 
	 * @param lineNumber
	 */
	public void setLineNumber(int lineNumber)
	{
		this.lineNumber = lineNumber;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString()
	{
		return "Word [fileName=" + fileName + ", lineNumber=" + lineNumber + ", element=" + element + "]";
	}

	public String getElement()
	{
		return element;
	}

	public void setElement(String element)
	{
		this.element = element;
	}

	/**
	*
	*/
	@Override
	public int compareTo(Word o)
	{
	return this.getElement().compareTo(o.getElement());
	}
	
	
}
