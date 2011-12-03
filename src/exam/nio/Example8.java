package exam.nio;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Created by IntelliJ IDEA.
 * User: ehp
 * Date: 2.12.11
 * Time: 8:24
 * To change this template use File | Settings | File Templates.
 */
public class Example8 {
    public static void main(String[] args) throws Exception {
        new Example8().run();
    }

    public void run() throws IOException {
        Files.walkFileTree(Paths.get("/etc"),new VisitorImpl());
    }

    public static class VisitorImpl extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.println("previsit dir: " +dir);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println("visit file: " +file);
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.out.println("visit file failed: " +file);
            exc.printStackTrace();
            return FileVisitResult.CONTINUE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            System.out.println("postvisit dir: " +dir);
            return FileVisitResult.CONTINUE;
        }
    }
}
