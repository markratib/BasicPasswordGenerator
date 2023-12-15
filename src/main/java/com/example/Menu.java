package com.example;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.example.models.Password;

public class Menu 
{
	public int getChoiceGen(List<Password> passList, String askMsg)
	{
		boolean badNum;
		int choice = 0;
		final int MAX_CHOICE = passList.size();
		final int MIN_CHOICE = 1;
		Scanner in = new Scanner(System.in);
		
		
		if(MAX_CHOICE != 0)
		{
			do
			{
				badNum = true;
				System.out.println("*******************************");
				for(int i = 0; i < passList.size(); i++)
				{
					System.out.println((i+1) + ". " + passList.get(i).getuserNote());
				}
				System.out.println("*******************************");
				System.out.print(askMsg);
				try
				{
					choice = in.nextInt();
				}catch(InputMismatchException e)
				{
					System.out.println("Invalid Input...");
					
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					in.nextLine();
					continue;
				}
				
				in.nextLine();
				//check for good input
				if(choice <= MAX_CHOICE && choice >= MIN_CHOICE)
				{
					badNum = false;
				}else//not a desired number, print message and wait
				{
					System.out.println("Choice not in range...");
					try 
					{
						Thread.sleep(1000);
						System.out.println("\n\n");
					} catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}while(badNum);
			
			
		}else
		{
			System.out.println("The list is empty...");
		}
		
		
		return choice;
	}
	
	public int getChoiceMain()
	{
		boolean badNum;
		int choice = 0;
		final int MAXCHOICE = 4;
		final int MINCHOICE = 0;
		Scanner in = new Scanner(System.in);
		do
		{
			badNum = true;
			printMainMenu();
			System.out.print("Pick a number: ");
			try
			{
				choice = in.nextInt();
			}catch(InputMismatchException e)
			{
				System.out.println("Invalid Input...");
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				in.nextLine();
				continue;
			}
			
			in.nextLine();
			//check for good input
			if(choice <= MAXCHOICE && choice >= MINCHOICE)
			{
				badNum = false;
			}else//not a desired number, print message and wait
			{
				System.out.println("Choice not in range...");
				try 
				{
					Thread.sleep(1000);
				} catch (InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		}while(badNum);
		
		return choice;
	}
	
	private void cls()
	{
		char[] arr = new char[20];
		
		for(int i = 0; i < arr.length; i++)
		{
			arr[i] = '\n';
		}
		
		System.out.println(arr);
	}
	
	private void printMainMenu()
	{
		
		String menuStr =  "\n"
						+ "\n*******************************"  
						+ "\n**********MAIN MENU************"
						+ "\n*******************************"
						+ "\n*1. Generate Password         *"
						+ "\n*2. Save Encrypted Password   *"
						+ "\n*3. Decrypt Saved/Inuse pass  *"
						+ "\n*4. Nothing                   *"
						+ "\n*0. Exit                      *"
						+ "\n*******************************";
//		cls();
		System.out.println(menuStr);
	}
}
