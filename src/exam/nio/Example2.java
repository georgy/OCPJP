package exam.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.UserPrincipal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ehp
 * Date: 26.11.11
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */
public class Example2 {
    public static void main(String[] args) throws Exception {
        new Example2().run();
    }

    private void run() throws IOException {
        Charset utf8 = Charset.forName("UTF-8");
        Charset win = Charset.forName("windows-1250");
        Path source = Paths.get("/home/ehp/tex/cj.tex");
        Path dest = Paths.get("/tmp/cj.win");

        try (BufferedReader input = Files.newBufferedReader(source, utf8);
                BufferedWriter output = Files.newBufferedWriter(dest, win)) {
            String line = null;
            while ((line = input.readLine()) != null) {
                output.write(line);
                output.newLine();
            }
        }

        Files.copy(source, dest.resolveSibling("cj.orig"), StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.REPLACE_EXISTING);
        Files.move(dest.resolveSibling("cj.orig"), dest, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);

        UserPrincipal user = dest.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByName("root");
        GroupPrincipal group = dest.getFileSystem().getUserPrincipalLookupService().lookupPrincipalByGroupName("users");

        System.out.println(user + ":" + group);


    }

}
