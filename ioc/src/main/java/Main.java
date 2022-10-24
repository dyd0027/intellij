import jdk.nashorn.api.scripting.URLReader;

public class Main {
    public static void main(String[] args){
        String url = "www.naver.com/books/it?page=10&size=20&name=spring-boot";

        //밑 에 두 방식은 DI를 사용하지 않은 경우
        //Base64 encoding
        IEncoder encoder = new Base64Encoder();
        String result = encoder.encode(url);
        System.out.println(result);

        //url encoding
        IEncoder urlEncoder = new UrlEncoder();
        result = urlEncoder.encode(url);
        System.out.println(result);

        //밑 두 방식은 DI를 사용한 경우
        Encoder baseEncoderDI = new Encoder(new Base64Encoder());
        Encoder urlEncoderDI = new Encoder(new UrlEncoder());
        System.out.println(baseEncoderDI.encode(url));
        System.out.println(urlEncoderDI.encode(url));
    }
}
