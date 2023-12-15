package com.example;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class PasswordGenerator 
{
	//generates a password with default 16 characters
	public StringBuilder generatePassword()
	{
		String specialStr = "~`!@#$%^&*()_-+={[}]|\\:;\"'<,>.?/";
		String lowerStr = "abcdefghijklmnopqrstuvwxyz";
		String upperStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numberStr = "0123456789";
		int length = 32;
		boolean weak = true;
		boolean specialChar = false;
		boolean lowerChar = false;
		boolean upperChar = false;
		boolean numberChar = false;
		StringBuilder temp;
		int numWeak = 0;
		Scanner in = new Scanner(System.in);
		
		char[] specialArr = new char[specialStr.length()];
		char[] lowerArr = new char[lowerStr.length()];
		char[] upperArr = new char[upperStr.length()];
		char[] numberArr = new char[numberStr.length()];
		
		specialArr = strtoCharArr(specialStr);
		lowerArr = strtoCharArr(lowerStr);
		upperArr = strtoCharArr(upperStr);
		numberArr = strtoCharArr(numberStr);
		
		Arrays.sort(specialArr);
//		printArrays(specialArr, lowerArr, upperArr, numberArr);
		
		int[] charPos = new int[length];
		Random rand = new Random();
		
		//assign what type of character goes in each position, ensuring we have at least 1 of each type.
		do
		{
			temp = new StringBuilder();
			specialChar = false;
			lowerChar = false;
			upperChar = false;
			numberChar = false;
			
			for(int i = 0; i < charPos.length; i++)
			{
				charPos[i] = rand.nextInt(4) + 1;
				switch(charPos[i])
				{
					case 1:
					{
						specialChar = true;
						temp.append(specialArr[rand.nextInt(specialArr.length)]);
						break;
					}
					case 2:
					{
						temp.append(lowerArr[rand.nextInt(lowerArr.length)]);
						lowerChar = true;
						break;
					}
					case 3:
					{
						temp.append(upperArr[rand.nextInt(upperArr.length)]);
						upperChar = true;
						break;
					}
					case 4:
					{
						temp.append(numberArr[rand.nextInt(numberArr.length)]);
						numberChar = true;
						break;
					}
				
				}
				
				
			}
			//check if we have 1 of each type of character
			if(specialChar && lowerChar && upperChar && numberChar)
			{
				//password is not weak, break out of the loop
				weak = false;
			}else
			{
				numWeak++;
			}
				
		}while(weak);
		
		System.out.println("Number of weak passwords generated: " + numWeak);
		return temp;
	}
	
	
	public StringBuilder generatePassword(int length)
	{
		String specialStr = "~`!@#$%^&*()_-+={[}]|\\:;\"'<,>.?/";
		String lowerStr = "abcdefghijklmnopqrstuvwxyz";
		String upperStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numberStr = "0123456789";
		boolean weak = true;
		boolean specialChar = false;
		boolean lowerChar = false;
		boolean upperChar = false;
		boolean numberChar = false;
		StringBuilder temp;
		int numWeak = 0;
		Scanner in = new Scanner(System.in);
		
		char[] specialArr = new char[specialStr.length()];
		char[] lowerArr = new char[lowerStr.length()];
		char[] upperArr = new char[upperStr.length()];
		char[] numberArr = new char[numberStr.length()];
		
		specialArr = strtoCharArr(specialStr);
		lowerArr = strtoCharArr(lowerStr);
		upperArr = strtoCharArr(upperStr);
		numberArr = strtoCharArr(numberStr);
		
		Arrays.sort(specialArr);
//		printArrays(specialArr, lowerArr, upperArr, numberArr);
		
		int[] charPos = new int[length];
		Random rand = new Random();
		
		//assign what type of character goes in each position, ensuring we have at least 1 of each type.
		do
		{
			temp = new StringBuilder();
			specialChar = false;
			lowerChar = false;
			upperChar = false;
			numberChar = false;
			
			for(int i = 0; i < charPos.length; i++)
			{
				charPos[i] = rand.nextInt(4) + 1;
				switch(charPos[i])
				{
					case 1:
					{
						specialChar = true;
						temp.append(specialArr[rand.nextInt(specialArr.length)]);
						break;
					}
					case 2:
					{
						temp.append(lowerArr[rand.nextInt(lowerArr.length)]);
						lowerChar = true;
						break;
					}
					case 3:
					{
						temp.append(upperArr[rand.nextInt(upperArr.length)]);
						upperChar = true;
						break;
					}
					case 4:
					{
						temp.append(numberArr[rand.nextInt(numberArr.length)]);
						numberChar = true;
						break;
					}
				
				}
				
				
			}
			//check if we have 1 of each type of character
			if(specialChar && lowerChar && upperChar && numberChar)
			{
				//password is not weak, break out of the loop
				weak = false;
			}else
			{
				numWeak++;
			}
				
		}while(weak);
		
		System.out.println("Number of weak passwords generated: " + numWeak);
		return temp;
	}
	
	private char[] strtoCharArr(String str)
	{
		char[] tempArr = new char[str.length()];
		
		for(int i = 0; i < str.length(); i++)
		{
			tempArr[i] = (char) str.charAt(i);
		}
		
		return tempArr;
	}
	
	private void printArrays(char[] specialArr, char[] lowerArr, char[] upperArr, char[] numberArr)
	{
		System.out.println("***SPECIAL CHARACTERS***");
		printArray(specialArr);
		System.out.println("\n***LOWER CHARACTERS***");
		printArray(lowerArr);
		System.out.println("\n***UPPER CHARACTERS***");
		printArray(upperArr);
		System.out.println("\n***NUMBER CHARACTERS***");
		printArray(numberArr);
	}
	
	private void printArray(char[] array)
	{
		for(int i = 0; i < array.length; i++)
		{
			System.out.print(array[i]);
			if(i != array.length - 1)
			{
				System.out.print(", ");
			}
		}
	}
}
