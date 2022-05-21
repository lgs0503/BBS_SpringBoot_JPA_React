package lgs.bbs.comm;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.nio.charset.Charset;

public class HttpHeaderJsonType {

    private static HttpHeaders headers;

    public HttpHeaderJsonType(){
        headers = new HttpHeaders();
        headers.setContentType(new MediaType("application", "json", Charset.forName("UTF-8")));
    }

    public static HttpHeaders getHeader(){
        return headers;
    }

}
