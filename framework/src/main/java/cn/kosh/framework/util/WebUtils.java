package cn.kosh.framework.util;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PushbackInputStream;

/**
 * Created by kosh on 2017/5/20.
 */
public class WebUtils {
    public static String getBody(HttpServletRequest request) throws IOException {
        InputStream inputStream = request.getInputStream();
        InputStream body;
        if (inputStream == null) {
            body = null;
        } else if (inputStream.markSupported()) {
            inputStream.mark(1);
            body = (inputStream.read() != -1 ? inputStream : null);
            inputStream.reset();
        } else {
            PushbackInputStream pushbackInputStream = new PushbackInputStream(inputStream);
            int b = pushbackInputStream.read();
            if (b == -1) {
                body = null;
            } else {
                body = pushbackInputStream;
                pushbackInputStream.unread(b);
            }
        }
        if (body == null) {
            return null;
        } else {
            StringBuilder sb = new StringBuilder();
            BufferedInputStream br = new BufferedInputStream(body);

            byte[] byteArray = new byte[1024];
            int length;
            while ((length = br.read(byteArray)) != -1) {
                sb.append(new String(byteArray, 0, length));
            }

            return sb.toString();
        }
    }
}
