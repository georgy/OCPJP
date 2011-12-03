package exam.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.util.EnumSet;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ehp
 * Date: 2.12.11
 * Time: 8:24
 * To change this template use File | Settings | File Templates.
 */
public class Example4 {
    public static void main(String[] args) throws Exception {
        new Example4().runSmall();
        new Example4().runStandard();
        new Example4().runChannel();
    }

    public void runSmall() throws IOException {
        Path file = Paths.get("/etc/passwd");
        List<String> lines = Files.readAllLines(file, Charset.forName("UTF-8"));

        System.out.println(lines);

        Files.write(Paths.get("/tmp/write.out"), lines, Charset.forName("UTF-8"), StandardOpenOption.CREATE_NEW, StandardOpenOption.DELETE_ON_CLOSE, StandardOpenOption.WRITE);

        file = Paths.get("/etc/ld.so.cache");
        byte[] data = Files.readAllBytes(file);

        System.out.println(data.length);

        Files.write(Paths.get("/tmp/write.out"), data, StandardOpenOption.CREATE_NEW, StandardOpenOption.DELETE_ON_CLOSE, StandardOpenOption.WRITE);
    }

    public void runStandard() throws IOException {
        try (BufferedReader br = Files.newBufferedReader(Paths.get("/etc/passwd"), Charset.forName("UTF-8"))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }

        try (InputStream is = Files.newInputStream(Paths.get("/etc/login.defs"));
                OutputStream os = Files.newOutputStream(Paths.get("/tmp/write1.out"), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING)) {
            byte[] buf = new byte[4096];

            int size = 0;
            while ((size = is.read(buf)) > 0) {
                os.write(buf, 0, size);
            }
        }

    }

    public void runChannel() throws IOException {
        try (ByteChannel ich = Files.newByteChannel(Paths.get("/etc/login.defs"), StandardOpenOption.READ);
                ByteChannel och = Files.newByteChannel(Paths.get("/tmp/write2.out"), EnumSet.of(StandardOpenOption.CREATE, StandardOpenOption.APPEND))) {
            ByteBuffer bb = ByteBuffer.allocate(4096);

            while (ich.read(bb) != -1) {
                bb.flip();
                och.write(bb);
                bb.compact();
            }

            bb.flip();
            while (bb.hasRemaining()) {
                och.write(bb);
            }
        }
    }
}
