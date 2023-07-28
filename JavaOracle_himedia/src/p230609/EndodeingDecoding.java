package p230609;

import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class EndodeingDecoding {
	public static SecretKey getKey() throws Exception {
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
		keyGenerator.init(128);
		SecretKey secretKey = keyGenerator.generateKey();
		return secretKey;
	}

	public static IvParameterSpec getIv() {
		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		return new IvParameterSpec(iv);
	}

	public static String encrypt(String specName, SecretKey key, IvParameterSpec iv, String plainText)
			throws Exception {
		Cipher cipher = Cipher.getInstance(specName);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		byte[] encrypted = cipher.doFinal(plainText.getBytes(StandardCharsets.UTF_8));
		return new String(Base64.getEncoder().encode(encrypted));
	}

	public static String decrypt(String specName, SecretKey key, IvParameterSpec iv, String cipherText)
			throws Exception {
		Cipher cipher = Cipher.getInstance(specName);
		cipher.init(Cipher.DECRYPT_MODE, key, iv); // 모드가 다르다.
		byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(cipherText));
		return new String(decrypted, StandardCharsets.UTF_8);
	}

	public static void main(String[] args) {
		String putTxt = "비밀번호";
		try {
			SecretKey key = EndodeingDecoding.getKey();
			IvParameterSpec ivParameterSpec = EndodeingDecoding.getIv();
			String specName = "AES/CBC/PKCS5Padding";

			String encryptedText = EndodeingDecoding.encrypt(specName, key, ivParameterSpec, putTxt);
			String decryptedText = EndodeingDecoding.decrypt(specName, key, ivParameterSpec, encryptedText);
			System.out.println("cipherText: " + encryptedText);
			System.out.println("plainText: " + decryptedText);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
