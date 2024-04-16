package util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaSigner;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
public class RSATest {

    public static  String PUBLIC_KEY= "";

    public static  String PRIVATE_KEY= "";


    public static final String USER_JSON = "{\"clientId\":\"edse-f-card\",\"user_name\":\"190447\",\"userName\":\"王雄\",\"type\":\"user\",\"userId\":\"190447\",\"client_id\":\"edse-f-card\",\"loginName\":\"190447\",\"exp\":2147483647,\"jti\":\"d3aa64df-0e48-4043-9a0d-aeec87370451\",\"platformCode\":\"bwjt\"}";

    public static String ENCODE_STR = "";

    //    加密算法
    private static final String KEY_ALGORITHM = "RSA";

    @BeforeClass
    public static void generateKey() throws Exception {
        KeyPairGenerator instance = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        KeyPair keyPair = instance.generateKeyPair();
        PrivateKey privateKey = keyPair.getPrivate();
        PublicKey publicKey = keyPair.getPublic();
        //Base64 编码
        byte[] privateKeyEncoded = privateKey.getEncoded();
        String privateKeyStr = Base64.encodeBase64String(privateKeyEncoded);
        byte[] publicKeyEncoded = publicKey.getEncoded();
        String publicKeyStr=Base64.encodeBase64String(publicKeyEncoded);
        PUBLIC_KEY = publicKeyStr;
        PRIVATE_KEY = privateKeyStr;
        log.info("PUBLIC_KEY: {}", PUBLIC_KEY);
        log.info("PRIVATE_KEY: {}", PRIVATE_KEY);
    }

    @Test
    @Order(1)
    public void encrypt() throws Exception {
        log.info(USER_JSON);
        RSAPrivateKey privateKey=getPrivateKey(PRIVATE_KEY);
        ENCODE_STR = JwtHelper.encode(USER_JSON, new RsaSigner(privateKey)).getEncoded();
        log.info("ENCODE_STR:{}", ENCODE_STR);

    }

    @Test
    @Order(2)
    public void decrypt() throws Exception {
        RSAPublicKey rsaPublicKey=getPublicKey(PUBLIC_KEY);
        Jwt jwt=JwtHelper.decodeAndVerify(ENCODE_STR, new RsaVerifier(rsaPublicKey) );
        String claims= jwt.getClaims();
        log.info(claims);

    }


    public  RSAPublicKey getPublicKey(String publicKey) throws Exception {
        // 通过X509编码的Key指令获得公钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(Base64.decodeBase64(publicKey));
        RSAPublicKey key = (RSAPublicKey) keyFactory.generatePublic(x509KeySpec);
        return key;
    }

    public RSAPrivateKey getPrivateKey(String privateKey)
            throws Exception {
        // 通过PKCS#8编码的Key指令获得私钥对象
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(Base64.decodeBase64(privateKey));
        RSAPrivateKey key = (RSAPrivateKey) keyFactory.generatePrivate(pkcs8KeySpec);
        return key;
    }



}
