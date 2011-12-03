package exam.nio;

import java.io.IOException;
import java.nio.file.*;

/**
 * Created by IntelliJ IDEA.
 * User: ehp
 * Date: 2.12.11
 * Time: 8:24
 * To change this template use File | Settings | File Templates.
 */
public class Example7 {
    public static void main(String[] args) throws Exception {
        new Example7().run();
    }

    public void run() throws IOException {
        Path file1 = Files.createTempFile("sym",".link");
        Path sym = Paths.get("/tmp/symlink.link");
        Path hard = Paths.get("/tmp/hardlink.link");
        Files.createSymbolicLink(sym, file1);
        Files.createLink(hard, file1);

    }
}
