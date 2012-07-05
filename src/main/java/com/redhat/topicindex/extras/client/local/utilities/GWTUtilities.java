package com.redhat.topicindex.extras.client.local.utilities;

public class GWTUtilities
{
	/**
	 * Replacement for String.toByteArray()
	 * 
	 * @param string
	 *            The string to convert
	 * @param bytesPerChar
	 *            The number of bytes per character
	 * @return the same as the standard Java String.toByteArray() method
	 */
	public static byte[] getByteArray(final String string, final int bytesPerChar)
	{
		char[] chars = string.toCharArray();
		byte[] toReturn = new byte[chars.length * bytesPerChar];
		for (int i = 0; i < chars.length; i++)
		{
			for (int j = 0; j < bytesPerChar; j++)
				toReturn[i * bytesPerChar + j] = (byte) (chars[i] >>> (8 * (bytesPerChar - 1 - j)));
		}
		return toReturn;
	}
}
