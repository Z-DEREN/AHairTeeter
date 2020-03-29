package com.zdr.ahairteeter.demo.zTest.cangku;

import org.apache.commons.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

public class cangkuapp {


    /*
链接: https://pan.baidu.com/s/1iE2VPhXYxHwc7MLz4JcNCA 提取码: 94fw

    https://pan.baidu.com/s/1KmdxIC4tCQhL3aUYIJgLbw
    * 6c67dd036b8b8c8eb14a2ff6c692848b66eb02c161e5e581a3350730419ac235R164rgaQaWwrltZVfHmhstplwk7DIo69UrfFBo77I+Y=
    * 69b5fc3c907196289002454552d71fda8efcf922be450c666aef6195695d7639oeak17WpM24IWykpKrJwJJWZ+mleNQ2ncVHUPlFq9gY="
    *
    *
    *
    *
    * */

    public static void main(String[] args) {
        cangkuapp cangkuapp = new cangkuapp();
        cangkuapp.Decode();
    }
    public void  Decode()
    {
        try {
            //解密的Key
            String strKey = "230074";
            //已加密的Base64字串
            String strEncrypted = "a36acad398792a8536fc32756290ce8f5507c91f3a5ab149595eb3ecacc30ed0AwQEz24cSjdlnSP6LhDI0KUFxp7q1pOnQoAtxZj31zI=";

            //先將Base64字串轉碼為byte[]
            Base64 objBase64 = new Base64();
            byte[] bysDecoded = objBase64.decode(strEncrypted.getBytes());

            //建立解密所需的Key. 因為加密時的key是用ASCII轉換, 所以這邊也用ASCII做
            DESKeySpec objDesKeySpec = new DESKeySpec(strKey.getBytes("ASCII"));
            SecretKeyFactory objKeyFactory = SecretKeyFactory.getInstance("DES");
            SecretKey objSecretKey = objKeyFactory.generateSecret(objDesKeySpec);

            //設定一個DES/ECB/NoPadding的Cipher
            //ECB對應到.Net的CipherMode.ECB
            //NoPadding對應到.Net的PaddingMode.None
            Cipher objCipher = Cipher.getInstance("DES/ECB/NoPadding");
            //設定為解密模式, 並設定解密的key
            objCipher.init(Cipher.DECRYPT_MODE, objSecretKey);

            //輸出解密後的字串. 因為加密是用UTF-8將字串轉為byte[], 所以這邊要用UTF-8轉回去
            //注意後面會多一些空字元. 因為加密前有因為資料長度的關係補上一些空的bytes
            //這邊用String的trim()將這些空字元刪掉
            String strDecrypted = new String(objCipher.doFinal(bysDecoded), "utf-8").trim();
            System.out.println("[" + strDecrypted + "]");
            //輸出:[我是一個PaddingMode.None的測試字串！]
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }



    }
}
