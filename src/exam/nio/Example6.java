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
public class Example6 {
    public static void main(String[] args) throws Exception {
        new Example6().run();
    }

    public void run() throws IOException {
        Iterable<Path> roots = FileSystems.getDefault().getRootDirectories();

        for (Path r : roots) {
            System.out.println(r);
            try (DirectoryStream<Path> dir = Files.newDirectoryStream(r, new DirectoryStream.Filter<Path>() {
                @Override
                public boolean accept(Path entry) throws IOException {
                    return Files.isDirectory(entry);
                }
            })) {
                for (Path p : dir) {
                    System.out.println(p.getFileName());
                }
            }

        }

    }
}
