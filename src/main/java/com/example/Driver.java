package com.example;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.example.models.Password;

public class Driver {

	public static void main(String[] args) 
	{
		PasswordGenerator pGen = new PasswordGenerator();
		PasswordEncryptor pEnc = new PasswordEncryptor();
		Menu men = new Menu();
		int doNothingCount = 0;
		Password currentPassword = null;
		/* Not used anymore
		StringBuilder password = null; 
		String encPassword = null;
		String decPassword = null;
		String userNote;
		*/
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
					if(currentPassword == null)
					{
						currentPassword = new Password();
						currentPassword.setDecryptedPassword(pGen.generatePassword().toString());
						System.out.println("Password generated: " + currentPassword.getDecryptedPassword());
						
					}else//there is already a password generated
					{
						//get user input to keep current or generate a new one
						System.out.print("There is already a password generated, keep old password? (y/n) ");
						charChoice = in.next().charAt(0);
						//if user says no, gen a new password
						if(charChoice == 'n')
						{
							currentPassword.setDecryptedPassword(pGen.generatePassword().toString());
						}
						System.out.println("Password generated: " + currentPassword.getDecryptedPassword());
					}
					
					break;
				}
				//save encrypt password
				case 2:
				{
					//if no password is generated, generate one
					if(currentPassword == null)
					{
						System.out.println("No password has been generated, generating a new password...");
						currentPassword = new Password();
						currentPassword.setDecryptedPassword(pGen.generatePassword().toString());
						System.out.println("Password generated: " + currentPassword.getDecryptedPassword());
					}
					System.out.println("Give this entry a note to describe what it's for? (press enter to skip)");
					currentPassword.setuserNote(in.nextLine());
					
					currentPassword.setEncryptedPassword(pEnc.encryptPassword(currentPassword.getDecryptedPassword()));
					System.out.println("Encrypted password is: " + currentPassword.getEncryptedPassword());
					System.out.println("Saving password to file.");
					savePassword(currentPassword);
					
					break;
				}
				//save encrypted password
				case 3:
				{
					//get a list of current saved passwords
					//ask user which they want to decrypt
					//decrypt passowrd
					//done?
					/*old functionality, not used*/
					if(currentPassword != null)
					{
						currentPassword.setDecryptedPassword(pEnc.decryptPassword(currentPassword.getEncryptedPassword()));
						System.out.println("Decrypted password: " + currentPassword.getDecryptedPassword());
					}else
					{
						System.out.println("No password currently in use....");
					}
					
					
					break;
				}
				//TBD
				case 4:
				{
					System.out.println("I have done nothing " + ++doNothingCount + " times.");
					if(doNothingCount > 10 && doNothingCount < 100)
					{
						System.out.println("Please sir, give me work.");
					}else if(doNothingCount > 100 && doNothingCount < 1000)
					{
						System.out.println("Wow you're bored.");
					}else if(doNothingCount > 1000 && doNothingCount < 10000)
					{
						System.out.println("I hope you're using a macro to do this...");
					}else if(doNothingCount > 10000)
					{
						System.out.println("o7");
					}
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
	
	private static void savePassword(Password pass)
	{
		String filePath = ".\\src\\main\\resources\\passwords.txt";
		FileOutputStream fout = null;
		
		try
		{
			//open the file in append mode
			fout = new FileOutputStream(new File(filePath), true);
			String writeStr = "\n" + pass.getEncryptedPassword() + ", " + pass.getuserNote();
			fout.write(writeStr.getBytes());
			fout.close();
			System.out.println("Encrypted password saved to \"passwords.txt\"");
		}catch(IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static List<String> getNotes()
	{
		
		return null;
	}
	private static List<String> getPasswords()
	{
		String filePath = ".\\src\\main\\resources\\passwords.txt";
		List<String> passList = new ArrayList<String>();
		StringBuilder temp = new StringBuilder();
		int c;
		//bool for when we're at the , in the password to signal we're at the comment part
		boolean skipover = false;
		
		try
		{
			FileInputStream in = new FileInputStream(filePath);
			while((c = in.read()) != -1)
			{
				//we're still getting the password
				if(!skipover)
				{
					//if we're at the end of the password part
					if((char) c == ',')
					{
						//set flag
						skipover = true;
						//add the password to the list
						passList.add(temp.toString());
						//reset our placeholder
						temp = new StringBuilder();
						
					}else//continue appending characters
					{
						temp.append((char) c);
					}
				}else//check for '\n' to begin getting the next password
				{
					if((char) c == '\n')
					{
						skipover = false;
					}
				}
			}
			
			
			
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return passList;
	}
}
