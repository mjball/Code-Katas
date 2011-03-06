package com.pillartechnology.ballkata;

import static org.junit.Assert.*;
import org.junit.Test;

public class AccountNumberChecksumTest
{
	@Test
	public void VerifiesValidAccountNumbers()
	{
		assertTrue(AccountNumberChecksum.isValid(0));
		assertTrue(AccountNumberChecksum.isValid(345882865));
	}
	
	@Test
	public void DoesNotVerifyInvalidAccountNumbers()
	{
		assertFalse(AccountNumberChecksum.isValid(1));
		assertFalse(AccountNumberChecksum.isValid(345782865));
	}
}