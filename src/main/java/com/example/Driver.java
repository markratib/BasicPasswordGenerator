package com.example;

import java.io.FileOutputStream;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Driver {

	public static void main(String[] args) 
	{
		String specialStr = "~`!@#$%^&*()_-+={[}]|\\:;\"'<,>.?/";
		String lowerStr = "abcdefghijklmnopqrstuvwxyz";
		String upperStr = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String numberStr = "0123456789";
		StringBuilder password;
		Scanner in = new Scanner(System.in);
		char choice;
		char[] specialArr = new char[specialStr.length()];
		char[] lowerArr = new char[lowerStr.length()];
		char[] upperArr = new char[upperStr.length()];
		char[] numberArr = new char[numberStr.length()];
		
		specialArr = strtoCharArr(specialStr);
		lowerArr = strtoCharArr(lowerStr);
		upperArr = strtoCharArr(upperStr);
		numberArr = strtoCharArr(numberStr);
		
		Arrays.sort(specialArr);
		
		
		System.out.println("***SPECIAL CHARACTERS***");
		printArray(specialArr);
		System.out.println("\n***LOWER CHARACTERS***");
		printArray(lowerArr);
		System.out.println("\n***UPPER CHARACTERS***");
		printArray(upperArr);
		System.out.println("\n***NUMBER CHARACTERS***");
		printArray(numberArr);
		
		password = generatePassword(specialArr, lowerArr, upperArr, numberArr);
		
		System.out.println("Password generated: " + password);
		
		System.out.print("\nStore password encrypted in a text file? (y/n) ");
		choice = in.next().charAt(0);
		
		if(choice == 'y' || choice == 'Y')
		{
			saveEncryptedPassword(password);
		}
		
	}
	
	private static char[] strtoCharArr(String str)
	{
		char[] tempArr = new char[str.length()];
		
		for(int i = 0; i < str.length(); i++)
		{
			tempArr[i] = (char) str.charAt(i);
		}
		
		return tempArr;
	}
	
	private static void printArray(char[] array)
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
	
	private static StringBuilder generatePassword(char[] special, char[] lower, char[] upper, char[] number)
	{
		boolean weak = true;
		boolean retry = true;
		boolean specialChar = false;
		boolean lowerChar = false;
		boolean upperChar = false;
		boolean numberChar = false;
		StringBuilder temp;
		int length;
		int numWeak = 0;
		char check;
		Scanner in = new Scanner(System.in);
		
		do
		{
			System.out.println("What is your desired length of password? ");
			length = in.nextInt();
			in.nextLine();
			System.out.println("Password length will be set to: " + length + "\nIs this correct? (y/n) ");
			check = in.next().charAt(0);
			if(check == 'y' || check == 'Y')
			{
				retry = false;
			}
			
		}while(retry);
		
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
						temp.append(special[rand.nextInt(special.length)]);
						break;
					}
					case 2:
					{
						temp.append(lower[rand.nextInt(lower.length)]);
						lowerChar = true;
						break;
					}
					case 3:
					{
						temp.append(upper[rand.nextInt(upper.length)]);
						upperChar = true;
						break;
					}
					case 4:
					{
						temp.append(number[rand.nextInt(number.length)]);
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
	
	private static void saveEncryptedPassword(StringBuilder password)
	{
		String str = password.toString();
		
		try {
			
			String fileSaveLocation = ".\\src\\main\\resources\\passwords.txt";
			FileOutputStream fout = new FileOutputStream(fileSaveLocation);
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			SecretKey AESKey = keyGenerator.generateKey();
			Cipher AESCipher = Cipher.getInstance("AES");
			byte[] byteArr = str.getBytes();
			System.out.println("Unencrypted passowrd: " + byteArr);
			
			AESCipher.init(Cipher.ENCRYPT_MODE, AESKey);
			byte[] encrypted = AESCipher.doFinal(byteArr);
			
			System.out.println("Encrypted password: " + encrypted);
			
			AESCipher.init(Cipher.DECRYPT_MODE, AESKey);
			byte[] decrypted = AESCipher.doFinal(encrypted);
			
			System.out.println("Decrypted passowrd: " + decrypted);
			String decryptedString = new String(decrypted);
			if(decryptedString.compareTo(str) == 0)
			{
				System.out.println("Decrypted byte array is equivalent to original password.");
			}else
			{
				System.out.println("Something went wrong, strings are not the same.");
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
