package com.example;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

public class Driver {

	public static void main(String[] args) 
	{
		PasswordGenerator pGen = new PasswordGenerator();
		PasswordEncryptor pEnc = new PasswordEncryptor();
		Menu men = new Menu();
		
		StringBuilder password = null;
		String encPassword = null;
		String decPassword = null;
		Scanner in = new Scanner(System.in);
		int choice;
		char charChoice;
		
//		int length = getLength();
//		password = pGen.generatePassword(length);
		
		do
		{
			choice = men.getChoiceMain();
			
			switch(choice)
			{
				//password generation
				case 1:
				{
					//if no password generated
					if(password == null)
					{
						password = pGen.generatePassword();
						System.out.println("Password generated: " + password);
						
					}else//there is already a password generated
					{
						//get user input to keep current or generate a new one
						System.out.print("There is already a password generated, keep old password? (y/n) ");
						charChoice = in.next().charAt(0);
						//if user says no, gen a new password
						if(charChoice == 'n')
						{
							password = pGen.generatePassword();
						}
					}
					
					break;
				}
				//save encrypt password
				case 2:
				{
					//if no password is generated, generate one
					if(password == null)
					{
						System.out.println("No password has been generated, generating a new password...");
						password = pGen.generatePassword();
						System.out.println("Password generated: " + password);
					}
					encPassword = pEnc.encryptPassword(password);
					System.out.println("Encrypted password is: " + encPassword);
					System.out.println("Saving password to file.");
					savePassword(encPassword);
					
					break;
				}
				//save encrypted password
				case 3:
				{
					decPassword = pEnc.decryptPassword(encPassword);
					System.out.println("Decrypted password: " + decPassword);
					
					break;
				}
				//TBD
				case 4:
				{
					
					break;
				}
				default:
				{
					System.out.println("Exiting....");
					choice = 0;
				}
			}
		}while(choice != 0);
		
		
	}
	
	private static int getLength()
	{
		Scanner in = new Scanner(System.in);
		boolean retry = true;
		char check;
		int length;
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
		
		return length;
	}
	
	private static void savePassword(String encPassword)
	{
		String filePath = ".\\src\\main\\resources\\passwords.txt";
		FileOutputStream fout = null;
		
		try
		{
			fout = new FileOutputStream(filePath);
			fout.write(encPassword.getBytes());
			fout.close();
			System.out.println("Encrypted password saved to \"passwords.txt\"");
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
}
