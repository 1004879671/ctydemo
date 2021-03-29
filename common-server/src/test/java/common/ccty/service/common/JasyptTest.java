package common.ccty.service.common;

import com.ulisesbocchio.jasyptspringboot.encryptor.DefaultLazyEncryptor;
import org.jasypt.encryption.StringEncryptor;
import org.junit.Test;
import org.springframework.core.env.StandardEnvironment;

public class JasyptTest {

    @Test
    public void test() {
        // 对应配置文件中配置的加密密钥
        System.setProperty("jasypt.encryptor.password", "caitianyu");
        StringEncryptor stringEncryptor = new DefaultLazyEncryptor(new StandardEnvironment());
        System.out.println("加密： " + stringEncryptor.encrypt("etsrfk"));
        System.out.println("解密： " + stringEncryptor.decrypt("Lmvo/IB0FFXLKgZ8r6xYIg=="));
    }
}
