package exam.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ehp
 * Date: 2.12.11
 * Time: 8:24
 * To change this template use File | Settings | File Templates.
 */
public class Example5 {
    public static void main(String[] args) throws Exception {
        new Example5().run();
    }

    public void run() throws IOException {
        Path file = Paths.get("/tmp/test.txt");
        file = Files.createFile(file);
        System.out.println(file);
        Files.deleteIfExists(file);

        Path temp = Files.createTempFile(null,".tmp");
        System.out.println(temp);
        Files.deleteIfExists(temp);
    }
}
