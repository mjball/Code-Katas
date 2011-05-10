package printf;

import static printf.Printf.*;

public class PrintfTest
{
    public static void main(String[] args)
    {
        putString("foo");   // expect "foo"
        putChar('\n');
        
        putString("");      // expect ""
        putChar('\n');
        
        putInt(234);        // expect "234"
        putChar('\n');
        
        putInt(-54323409);  // expect "-54323409"
        putChar('\n');
        
        putInt(0);          // expect "0"
        putChar('\n');

        printf("123-%s-%d", new Object[]{ "abc", 456 });    // expect "123-abc-456"
        putChar('\n');

        printf("110%%", new Object[0]);     // expect "110%"
        putChar('\n');
        
        printf("%d%%", new Object[]{110});  // expect "110%"
        putChar('\n');
    }
}