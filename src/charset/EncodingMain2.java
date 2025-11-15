package charset;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.*;

public class EncodingMain2 {

    private static final Charset EUC_JP = Charset.forName("EUC-JP");
    private static final Charset MS_949 = Charset.forName("MS949");

    private static final Charset EUC_KR = Charset.forName("EUC-KR");

    public static void main(String[] args) {
        System.out.println("==ASCII 英文処理==");
        test("A", US_ASCII, US_ASCII);
        test("A", US_ASCII, ISO_8859_1); // ASCII 拡張(LATIN-1)
        test("A", US_ASCII, EUC_JP); // ASCII 含め
        test("A", US_ASCII, MS_949); // ASCII 含め
        test("A", US_ASCII, UTF_8); // ASCII 含め
        test("A", US_ASCII, UTF_16BE); // UTF_16 ディコーディング失敗

        System.out.println("== 日本語 インコーディング - 基本 ==");
        test("あ", US_ASCII, EUC_JP); // X
        test("あ", ISO_8859_1, ISO_8859_1); // X
        test("あ", EUC_JP, EUC_JP);
        test("あ", MS_949, MS_949);
        test("あ", UTF_8, UTF_8);
        test("あ", UTF_16BE, UTF_16BE);

        System.out.println("== ハングルインコーディング - 複雑な文字 ==");
        test("뷁", EUC_KR, EUC_KR);
        test("뷁", MS_949, MS_949);
        test("뷁", UTF_8, UTF_8);
        test("뷁", UTF_16BE, UTF_16BE);

        System.out.println("== 日本語インコーディング - ディコーディングが異なる場合 ==");
        test("あ", EUC_JP, MS_949);
        test("あ", MS_949, EUC_JP); //
        test("あ", EUC_JP, UTF_8); // X
        test("あ", MS_949, UTF_8); // X
        test("あ", UTF_8, MS_949); // X

        System.out.println("== 英文インコーディング - ディコーディングが異なる場合 ==");
        test("A", EUC_JP, UTF_8);
        test("A", MS_949, UTF_8);
        test("A", UTF_8, MS_949);
        test("A", UTF_8, UTF_16BE); // X

    }

    private static void test(String text, Charset encodingCharset, Charset decodingCharset) {
        byte[] encoded = text.getBytes(encodingCharset);
        String decoded = new String(encoded, decodingCharset);
        System.out.printf("%s → [%s] インコーディング → %s %sbyte -> [%s] ディコーディング -> %s\n", text, encodingCharset, Arrays.toString(encoded), encoded.length, decodingCharset, decoded);
    }
}
