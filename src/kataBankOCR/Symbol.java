package kataBankOCR;

public enum Symbol
{
	ZERO(0,
		" _ " +
		"| |" +
		"|_|" ),

	ONE(1,
		"   " +
		"  |" +
		"  |" ),

	TWO(2,
		" _ " +
		" _|" +
		"|_ " ),

	THREE(3,
		" _ " +
		" _|" +
		" _|" ),

	FOUR(4,
		"   " +
	    "|_|" +
		"  |"),

	FIVE(5,
		" _ " +
		"|_ " +
		" _|" ),

	SIX(6,
		" _ " +
		"|_ " +
		"|_|" ),

	SEVEN(7,
		" _ " +
		"  |" +
		"  |" ),

	EIGHT(8,
		" _ " +
		"|_|" +
		"|_|" ),

	NINE(9,
		" _ " +
		"|_|" +
		" _|" );

	private Symbol(int v, String s)
	{
		value = v;
		string = s;
	}

	final int value;
	final String string;
}
