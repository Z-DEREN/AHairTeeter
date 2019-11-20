package com.AHairTeeter.Tool;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.binary.Hex;

public class codec {

	public static Key DEFAULT_KEY = null;

	public static final String DEFAULT_SECRET_KEY1 = "z";
	public static final String DEFAULT_SECRET_KEY2 = "s";
	public static final String DEFAULT_SECRET_KEY3 = "s";
	public static final String DEFAULT_SECRET_KEY4 = "z";
	public static final String DEFAULT_SECRET_KEY5 = "s";
	public static final String DEFAULT_SECRET_KEY6 = "z";
	public static final String DEFAULT_SECRET_KEY = DEFAULT_SECRET_KEY1;

	public static final String DES = "DES";

	public static final Base32 base32 = new Base32();

	static {
		DEFAULT_KEY = obtainKey(DEFAULT_SECRET_KEY);
	}

	/**
	 * ���key
	 **/
	public static Key obtainKey(String key) {
		if (key == null) {
			return DEFAULT_KEY;
		}
		KeyGenerator generator = null;
		try {
			generator = KeyGenerator.getInstance(DES);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		generator.init(new SecureRandom(key.getBytes()));
		Key key1 = generator.generateKey();
		generator = null;
		return key1;
	}

	/**
	 * ����<br>
	 * String��������,String�������
	 */
	public static String encode(String str) {
		return encode64(null, str);
	}

	/**
	 * ����<br>
	 * String��������,String�������
	 */
	public static String encode64(String key, String str) {
		return Base64.encodeBase64URLSafeString(obtainEncode(key, str.getBytes()));
	}

	/**
	 * ����<br>
	 * String��������,String�������
	 */
	public static String encode32(String key, String str) {
		return base32.encodeAsString(obtainEncode(key, str.getBytes())).replaceAll("=", "");
	}

	/**
	 * ����<br>
	 * String��������,String�������
	 */
	public static String encode16(String key, String str) {
		return Hex.encodeHexString(obtainEncode(key, str.getBytes()));
	}

	/**
	 * ����<br>
	 * ��String��������,String�������
	 */
	public static String decode(String str) {
		return decode64(null, str);
	}

	/**
	 * ����<br>
	 * ��String��������,String�������
	 */
	public static String decode64(String key, String str) {
		return new String(obtainDecode(key, Base64.decodeBase64(str)));
	}

	/**
	 * ����<br>
	 * ��String��������,String�������
	 */
	public static String decode32(String key, String str) {
		return new String(obtainDecode(key, base32.decode(str)));
	}

	/**
	 * ����<br>
	 * ��String��������,String�������
	 */
	public static String decode16(String key, String str) {
		try {
			return new String(obtainDecode(key, Hex.decodeHex(str.toCharArray())));
		} catch (DecoderException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * ����<br>
	 * ��byte[]��������,byte[]�������
	 */
	private static byte[] obtainEncode(String key, byte[] str) {
		byte[] byteFina = null;
		Cipher cipher;
		try {
			Key key1 = obtainKey(key);
			cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.ENCRYPT_MODE, key1);
			byteFina = cipher.doFinal(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * ����<br>
	 * ��byte[]��������,��byte[]�������
	 */
	private static byte[] obtainDecode(String key, byte[] str) {
		Cipher cipher;
		byte[] byteFina = null;
		try {
			Key key1 = obtainKey(key);
			cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.DECRYPT_MODE, key1);
			byteFina = cipher.doFinal(str);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cipher = null;
		}
		return byteFina;
	}

	/**
	 * ����
	 * 
	 * @param val
	 * @return
	 */
	public static String encrypt(String val) {
		String m = encode64(DEFAULT_SECRET_KEY2, val);
		String m1 = encode32(DEFAULT_SECRET_KEY3, m);
		return m1;
	}

	/**
	 * ����
	 * 
	 * @param val
	 * @return
	 */
	public static String decrypt(String val) {
		String n = decode32(DEFAULT_SECRET_KEY3, val);
		String n1 = decode64(DEFAULT_SECRET_KEY2, n);
		return n1;
	}

	
	
	
	
	
	/**
	 * ����
	 * 
	 * @param val
	 * @return
	 */
	public static String newencrypt(String val) {
		String m = encode64(DEFAULT_SECRET_KEY1, val);
		 m = encode32(DEFAULT_SECRET_KEY2, m);
		 m = encode64(DEFAULT_SECRET_KEY3, m);
		 m = encode32(DEFAULT_SECRET_KEY4, m);
		 m = encode64(DEFAULT_SECRET_KEY5, m);
		return m;
	}
	
	/**
	 * ����
	 * 
	 * @param val
	 * @return
	 */
	public static String newdecrypt(String val) {
		String m = decode64(DEFAULT_SECRET_KEY5, val);
		 m = decode32(DEFAULT_SECRET_KEY4, m);
		 m = decode64(DEFAULT_SECRET_KEY3, m);
		 m = decode32(DEFAULT_SECRET_KEY2, m);
		 m = decode64(DEFAULT_SECRET_KEY1, m);
		return m;
	}
	
	public static void main(String[] args) {
		// ����
		String m = newencrypt("123456");
		System.out.println("���ܽ����" + m);
		// ����
		String n = newdecrypt(m);
		System.out.println("���ܽ����" + n);

	}
	

	/**
	 * ���ݼ��ܷ���
	 * @param EPKEY ��Կ
	 * @param switchs �㷨
	 * @return
	 */
	public String puzzEncrypt( String EPKEY ,String pass, String switchs) {
		if(switchs.equals("1")) {
			pass = encode32(EPKEY, pass);
		}else {
			pass = encode64(EPKEY, pass);
		}
		return pass;
	}
	
	/**
	 * ���ݷ���
	 * @param EPKEY ��Կ
	 * @param switchs �㷨
	 * @return
	 */
	public String puzzdecrypt( String EPKEY ,String pass, String switchs) {
		if(switchs.equals("1")) {
			pass = decode32(EPKEY, pass);
		}else {
			pass = decode64(EPKEY, pass);
		}
		return pass;
	}


}
