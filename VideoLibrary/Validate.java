public class Validate
{
	public static int valid(String abc)
	{
		char c;
		int length = abc.length();
		for(int i=0; i < length;i++)
		{
			c = abc.charAt(i);
			if(!Character.isLetter(c) && !Character.isWhitespace(c) && c != '.')
			{
				return 1;
			}
		}
		return 0;
	}
}
