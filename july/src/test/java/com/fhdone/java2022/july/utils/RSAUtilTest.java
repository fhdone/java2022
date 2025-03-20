package com.fhdone.java2022.july.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class RSAUtilTest {

    private String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCGWLpoLtMXBRcZ4lx3J+OGme68qSn/VJh19++EkJs3uSdTRwiUa1EeFF6CfX9QovxveRTHmVgObbvazVROm8CYIsKdFkAcH0tUfOK+NsLd/V2M/yKJB02zJYubaQBGOA7RmXkWs4O4RMzjWKdkl9WqObK90D99nc7Jz8WlbpsVwQIDAQAB";
    private String priKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAIZYumgu0xcFFxniXHcn44aZ7rypKf9UmHX374SQmze5J1NHCJRrUR4UXoJ9f1Ci/G95FMeZWA5tu9rNVE6bwJgiwp0WQBwfS1R84r42wt39XYz/IokHTbMli5tpAEY4DtGZeRazg7hEzONYp2SX1ao5sr3QP32dzsnPxaVumxXBAgMBAAECgYA5fDzi+Go9luGAI2IyYECUp0hGtNGyHfYQwFLeM+wex1yi3ym2LEds6gVYq69vBxah8jrof1506hJwONzCuBWLxL9k/zRhqSMswkQfI4VXM8g9YX0dbjo2TXANE8q5Z7sXlSasyu7toYSKV4aslISxeqGEaVG271ytXS21PebQiwJBALLMILChwRE2KHunJrUQXhUJgbp4DCp3wNqsOfQub5H+BmHcKvLPHjKdsxjnSwYOIRGMPBXxjjkAtJYEi0/DgCcCQQDAWxusna1C8O0XxNC1KFpeMqq6Gfn1cjroYZG5CIv5UB+W/YwdyY1kLxuLOWjd49XwGlsMtyBUPnV3AAdqBgPXAkA2TUigXIAGeixRYbKfyxjHyttbhporS7OGtkfjoTbBYCUQE433sG+7b6m1JonijOf+LFkTUQmgxlYSOc8VqoqBAkBZx+yR1AKztPok1vNHKu+K+gEf3dICUu/V2PyILcoPcutbsTo+CP4anbYwdHpS3u3TJKoMtEi9qW1wPq43OVl/AkB9vLCcHgEJa8/3Su7nphZXNfjKLmHvb6dy0eZb0rnDMGB2SNltQmw6M4eHglmKi+OngUybQO6aO/faFb0lh7UZ";

    @Test
    public void genKeyPair() throws Exception {
        RSAUtil.genKeyPair();
    }

    @Test
    public void encrypt() throws Exception {

        String enc = RSAUtil.encrypt("123456", pubKey);
        log.info(enc);

        String dec = RSAUtil.decrypt(enc, priKey);
        log.info(dec);

    }

}