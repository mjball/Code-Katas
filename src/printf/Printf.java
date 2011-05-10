package printf;

public class Printf
{
    private static char[] table = new char[] {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};

    public static void putChar(char c)
    {
        System.out.print(c);
    }

    public static int putString(String s)
    {
        int length = s.length();
        for (int i=0; i<length; i++)
        {
            putChar(s.charAt(i));
        }
        return length;
    }

    public static int putInt(int num)
    {
        char[] reversed = new char[11];
        boolean negative = false;
        
        int i;
        for(i=0; ; i++)
        {
            int m = num%10;
            if (m < 0)
            {
                m *= -1;
                negative = true;
            }
            char c = table[m];
            reversed[i] = c;
            num /= 10;
            if (num == 0) break;
        }
        
        int length = i;
        // print non-reversed
        if (negative)
        {
            putChar('-');
            length++;
        }
        
        for (; i>=0; i--)
        {
            putChar(reversed[i]);
        }
        return length;
    }

    public static int printf(String s, Object[] args)
    {
        int numPrinted = 0;
        int currentArg = 0;
        
        for (int i=0; i<s.length(); i++)
        {
            char c = s.charAt(i);
            if (c == '%')
            {
                char next = s.charAt(i+1);
                if (next == 's')
                {
                    String sub = (String) args[currentArg];
                    numPrinted += putString(sub);
                    i++;
                    currentArg++;
                }
                else if (next == 'd')
                {
                    int j = (Integer) args[currentArg];
                    numPrinted += putInt(j);
                    i++;
                    currentArg++;
                }
                else if (next == '%')
                {
                    putChar(next);
                    i++;
                    numPrinted++;
                }
            }
            else
            {
                putChar(c);
                numPrinted++;
            }
        }
        
        return numPrinted;
    }
}