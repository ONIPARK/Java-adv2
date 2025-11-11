package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain1 {

    private static final Charset EUC_JP = Charset.forName("EUC-JP");
    private static final Charset MS_949 = Charset.forName("MS949");

    public static void main(String[] args) {
        System.out.println("==ASCII 英文処理==");
        encoding("A", US_ASCII);
        encoding("A", ISO_8859_1);
        encoding("A", EUC_JP);
        encoding("A", MS_949);
        encoding("A", UTF_8);
        encoding("A", UTF_16BE);

        System.out.println("==日本語 支援==");
        encoding("あ", EUC_JP);
        encoding("あ", MS_949);
        encoding("あ", UTF_8);
        encoding("あ", UTF_16BE);
    }

    private static void encoding(String text, Charset charset) {
        byte[] bytes = text.getBytes(charset);
        System.out.printf("%s → [%s] インコーディング → %s %sbyte\n", text, charset, Arrays.toString(bytes), bytes.length);
    }
}
