package com.example;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.SecureRandom;
import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class PasswordEncryptor 
{
	private static final int KEY_LENGTH = 256;
	private static final int ITERATION_COUNT = 65536;
	
	//encrypt password
	public String encryptPassword(String password)
	{
		StringBuilder encPassword = null;
		String str = password.toString();
		String secretKey = getSecretKey();
		String salt = getSalt();
		
		try
		{
			//do lots of stuff
			SecureRandom SR = new SecureRandom();
			//initialization vector
			byte[] iv = new byte[16];
			SR.nextBytes(iv);
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
	        byte[] cipherText = cipher.doFinal(str.getBytes("UTF-8"));
	        byte[] encryptedData = new byte[iv.length + cipherText.length];
	        System.arraycopy(iv, 0, encryptedData, 0, iv.length);
	        System.arraycopy(cipherText, 0, encryptedData, iv.length, cipherText.length);

	        return Base64.getEncoder().encodeToString(encryptedData);
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	//decrypt password
	public String decryptPassword(String encPassword)
	{
		
		StringBuilder decPassword = null;
		String secretKey = getSecretKey();
		String salt = getSalt();
		
		try
		{
			//do lots of stuff
			byte[] encData =  Base64.getDecoder().decode(encPassword);
			byte[] iv = new byte[16];
			System.arraycopy(encData, 0, iv, 0, iv.length);
			IvParameterSpec ivSpec = new IvParameterSpec(iv);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
			KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), ITERATION_COUNT, KEY_LENGTH);
			SecretKey tmp = factory.generateSecret(spec);
			SecretKeySpec secretKeySpec = new SecretKeySpec(tmp.getEncoded(), "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
	        byte[] cipherText = new byte[encData.length - 16];
	        byte[] encryptedData = new byte[iv.length + cipherText.length];
	        System.arraycopy(encData, 16, cipherText, 0, cipherText.length);
	        byte[] decryptedText = cipher.doFinal(cipherText);

	        return new String(decryptedText, "UTF-8");
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	//encrypt and save password
	private void saveEncryptedPassword(StringBuilder password)
	{
		String str = password.toString();
		
		try 
		{
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
			
			System.out.println("Decrypted password: " + new String(decrypted));
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

	private String getSecretKey()
	{
		StringBuilder secretKey = new StringBuilder();
		String keyLoc = ".\\src\\main\\resources\\secretkey";
		FileInputStream fin;
		int c;
		
		try
		{
			fin = new FileInputStream(keyLoc);
			while((c = fin.read()) != -1)
			{
				secretKey.append((char) c);
			}
			
			fin.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return secretKey.toString();
	}
	
	private String getSalt()
	{
		StringBuilder salt = new StringBuilder();
		String keyLoc = ".\\src\\main\\resources\\salt";
		FileInputStream fin;
		int c;
		
		try
		{
			fin = new FileInputStream(keyLoc);
			while((c = fin.read()) != -1)
			{
				salt.append((char) c);
			}
			
			fin.close();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		
		return salt.toString();
	}
}
