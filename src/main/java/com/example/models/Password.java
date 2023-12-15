package com.example.models;

import java.util.Objects;

//This class will hold both encrypted and decrypted passwords. And their respective userNotes.
public class Password 
{
	String encryptedPassword;
	String decryptedPassword;
	String userNote;
	
	
	public Password(String encryptedPassword, String decryptedPassword, String userNote) {
		super();
		this.encryptedPassword = encryptedPassword;
		this.decryptedPassword = decryptedPassword;
		this.userNote = userNote;
	}	
	public Password() {
		super();
	}
	public String getEncryptedPassword() {
		return encryptedPassword;
	}
	public void setEncryptedPassword(String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}
	public String getDecryptedPassword() {
		return decryptedPassword;
	}
	public void setDecryptedPassword(String decryptedPassword) {
		this.decryptedPassword = decryptedPassword;
	}
	public String getuserNote() {
		return userNote;
	}
	public void setuserNote(String userNote) {
		this.userNote = userNote;
	}
	@Override
	public int hashCode() {
		return Objects.hash(decryptedPassword, encryptedPassword, userNote);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Password other = (Password) obj;
		return Objects.equals(decryptedPassword, other.decryptedPassword)
				&& Objects.equals(encryptedPassword, other.encryptedPassword) && Objects.equals(userNote, other.userNote);
	}
	@Override
	public String toString() {
		return "Password [encryptedPassword=" + encryptedPassword + ", decryptedPassword=" + decryptedPassword
				+ ", userNote=" + userNote + "]";
	}
	
	
}
