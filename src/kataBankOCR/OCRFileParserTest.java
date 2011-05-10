package kataBankOCR;

import static org.junit.Assert.assertEquals;
import java.io.InputStream;
import java.util.List;
import java.util.Scanner;
import org.junit.Before;
import org.junit.Test;

public class OCRFileParserTest
{
	private OCRFileParser parser;

	@Before
	public void init()
	{
		this.parser = new OCRFileParser();
	}
	
	@Test
	public void EmptyStringParsesToZero()
	{
		assertEquals(0, parser.parseEntry(""));
	}
	
	@Test
	public void CanParseZero()
	{
		String text =
			" _ \n" +
			"| |\n" +
			"|_|\n";
		
		assertEquals(0, parser.parseEntry(text));
	}
	
	@Test
	public void CanParseOne()
	{
		String text =
			"   \n" +
			"  |\n" +
			"  |\n";
		
		assertEquals(1, parser.parseEntry(text));
	}
	
	@Test
	public void CanParseTwo()
	{
		String text = 
			" _ \n" +
			" _|\n" +
			"|_ \n";
		
		assertEquals(2, parser.parseEntry(text));
	}
	
	@Test
	public void CanParseZeros()
	{
		String text =
			" _  _  _  _  _  _  _  _  _ \n" +
			"| || || || || || || || || |\n" +
			"|_||_||_||_||_||_||_||_||_|\n";
		
		assertEquals(0, parser.parseEntry(text));
	}
	
	@Test
	public void CanParseZerosFromFile()
	{
		String text = new Scanner(getClass().getResourceAsStream("testfiles/usecase1/0s.txt"))
			.useDelimiter("\\Z").next();
		assertEquals(0, parser.parseEntry(text));
	}
	
	@Test
	public void CanParseSingleEntryHomogenousFiles()
	{
		for (int i=0, j=0; i<10; i++, j+=111111111)
		{
			String text = new Scanner(getClass().getResourceAsStream("testfiles/usecase1/"+i+"s.txt"))
				.useDelimiter("\\Z").next();
			assertEquals(j, parser.parseEntry(text));
		}
	}
	
	@Test
	public void CanParseSingleEntryNonHomogenousFile()
	{
		String text = new Scanner(getClass().getResourceAsStream("testfiles/usecase1/123456789.txt"))
			.useDelimiter("\\Z").next();
		assertEquals(123456789, parser.parseEntry(text));
	}
	
	@Test
	public void CanParseMultiEntryFile()
	{
		InputStream input = getClass().getResourceAsStream("testfiles/usecase1/multipleEntries.txt");
		List<Integer> results = parser.parse(input);
		
		for (int i=0, j=0; i<10; i++, j+=111111111)
		{
			assertEquals(j, results.get(i).intValue());
		}
		
		assertEquals(123456789, results.get(10).intValue());
	}
	
	@Test
	public void CanHandleNonexistentFile()
	{
		InputStream input = getClass().getResourceAsStream("does/not/exist.txt");
		List<Integer> results = parser.parse(input);
		
		assertEquals(0, results.size());
	}
}