package com.example.util;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

public class Encrypter {
	
	/**
	 * 
	 * @param toEncrypt
	 * @param EncryptType AES,
	 * @param KeyLength
	 * @return ArrayList of Key and Encrypted String
	 */

	ArrayList<String> Encrypt(String toEncrypt, String EncryptType,int KeyLength) {
		KeyGenerator keyG;
		try {
			keyG = KeyGenerator.getInstance("EncryptType");
			// 設定key的長度
			keyG.init(256);
			// 產生SecretKey
			SecretKey secuK = keyG.generateKey();
			// 取得要用來加密的key(解密也需使用這把key)
			byte[] key = secuK.getEncoded();
			System.out.println("key：" + new String(key));
			SecretKeySpec spec = new SecretKeySpec(key, "AES");
			Cipher cipher = Cipher.getInstance("AES");
			// 設定為加密模式
			cipher.init(Cipher.ENCRYPT_MODE, spec);
			// 將字串加密，並取得加密後的資料
			byte[] encryptData = cipher.doFinal(toEncrypt.getBytes());
			System.out.println("加密後字串：" + new String(encryptData));

			// 使用剛剛用來加密的key進行解密
			spec = new SecretKeySpec(key, "AES");
			cipher = Cipher.getInstance("AES");
			// 設定為解密模式
			cipher.init(Cipher.DECRYPT_MODE, spec);
			byte[] original = cipher.doFinal(encryptData);
			System.out.println("解密後字串：" + new String(original));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalBlockSizeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BadPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
