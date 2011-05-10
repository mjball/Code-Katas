package kataBankOCR;

public class AccountNumberChecksum
{
	private AccountNumberChecksum(){}
	
	public static boolean isValid(int accountNumber)
	{
		return checksum(accountNumber) == 0;
	}
	
	private static int checksum(int accountNumber)
	{
		int sum = 0;
		// account number:  3  4  5  8  8  2  8  6  5
		// position names:  d9 d8 d7 d6 d5 d4 d3 d2 d1
		
		// checksum calculation:
		// (d1+2*d2+3*d3 +..+9*d9) mod 11 = 0
		
		for (int digit=1; digit<10; digit++)
		{
			sum += digitAt(accountNumber, digit-1) * digit;
		}
		
		return sum % 11;
	}
	
	private static int digitAt(int number, int position)
	{
		return (int) ((number/Math.pow(10, position)) % 10);
	}
}
