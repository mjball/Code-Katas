package kataBankOCR;

import java.io.InputStream;
import java.util.*;

public class OCRFileParser
{
	static final int GRID_SIZE = 3;
	static final char SPACE = ' ';
	
	private final Map<String, Integer> symbolTable;
	
	public OCRFileParser()
	{
		Map<String, Integer> tempTable = new HashMap<String, Integer>();
		for (Symbol s : Symbol.values())
		{
			tempTable.put(s.string, s.value);
		}
		symbolTable = Collections.unmodifiableMap(tempTable);
	}
	
	public List<Integer> parse(InputStream input)
	{
		List<Integer> toReturn = new ArrayList<Integer>();
		
		if (input == null) return toReturn;
		
		Scanner scanner = new Scanner(input);
		while (scanner.hasNext())
		{
			String entry = readNextEntry(scanner);
			
			if (!entry.isEmpty())
			{
				toReturn.add(this.parseEntry(entry));
			}
			
			if (scanner.hasNext())
			{
				// skip the blank line in between entries
				scanner.nextLine();
			}
		}
		
		return toReturn;
	}
	
	public int parseEntry(String entry)
	{
		int toReturn = 0;
		
		List<String> lines = Arrays.asList(entry.split("\n"));

		int numDigits = computeNumDigits(lines);
		
		for (int currentDigit=0; currentDigit<numDigits; currentDigit++)
		{
			String symbol = "";
			for (int i=0; i<GRID_SIZE; i++)
			{
				String line = lines.get(i);
				int start = currentDigit*GRID_SIZE;
				int end = start + GRID_SIZE;
				
				while (line.length() < end)
				{
					line += ' ';
				}
				
				symbol += line.substring(start, end);
			}
			
			toReturn = (toReturn*10) + parseSymbol(symbol);
		}
		
		return toReturn;
	}
	
	private String readNextEntry(Scanner scanner)
	{
		String toReturn = "";
		for (int i=0; i<GRID_SIZE && scanner.hasNext(); i++)
		{
			toReturn += scanner.nextLine() + "\n";
		}
		return toReturn;
	}
	
	// find the longest line in the entry and use its length
	// to determine the number of digits in the the entry
	private int computeNumDigits(Iterable<String> lines)
	{
		List<Integer> lineLengths = new ArrayList<Integer>();
		for (String line : lines)
		{
			lineLengths.add(line.length());
		}
		return Collections.max(lineLengths)/GRID_SIZE;
	}

	private int parseSymbol(String symbol)
	{
		return symbolTable.get(symbol);
	}
}