package exam.nio;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.text.DateFormat;
import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: ehp
 * Date: 26.11.11
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */
public class Example1 {
    public static void main(String[] args) throws Exception {
        new Example1().run();
    }

    private void run() throws IOException {
        Path user = Paths.get(System.getProperty("user.home"));
        Path path = user.resolve(Paths.get("java"));
        Path win = Paths.get("C:\\windows\\system\\temp");
        Path denorm = Paths.get("/home/./ehp/../../usr/./java/jdk");
        Path tojdk = Paths.get(System.getProperty("user.dir")).relativize(denorm);

        if (denorm.equals(tojdk)) {
            System.out.println("jdk eq jdk");
        }
        if (Files.isSameFile(denorm, tojdk)) {
            System.out.println("files jdk eq jdk");
        }
        if (path.startsWith(user)) {
            System.out.println("java is in home");
        }
        if (tojdk.endsWith(Paths.get("java/jdk"))) {
            System.out.println("jdk in java");
        }

        showPathInfo(user);
        showPathInfo(path);
        showPathInfo(denorm.normalize());
        showPathInfo(tojdk);
        showPathInfo(tojdk.normalize());
        //showPathInfo(win); //throws illegal argument exception on linux

        List<Path> all = new ArrayList<>();
        all.add(user);
        all.add(path);
        all.add(denorm);
        all.add(tojdk);
        Collections.sort(all);

        System.out.println(all);

        showFileInfo(Paths.get("hjfdsajh"));
        showFileInfo(tojdk);
        showFileInfo(user);
        showFileInfo(Paths.get("/usr"));
        showFileInfo(Paths.get("/etc/passwd"));
        showFileInfo(Paths.get("/etc/shadow"));
        showFileInfo(Paths.get("/bin/sh"));
        showFileInfo(Paths.get("/home/ehp/.m2"));

    }

    private void showPathInfo(Path path) throws IOException {
        System.out.format("toString: %s%n", path.toString());
        System.out.format("getFileName: %s%n", path.getFileName());
        System.out.format("getName(0): %s%n", path.getName(0));
        System.out.format("getNameCount: %d%n", path.getNameCount());
        if (path.getNameCount() >= 2) {
            System.out.format("subpath(0,2): %s%n", path.subpath(0, 2));
        }
        System.out.format("getParent: %s%n", path.getParent());
        System.out.format("getRoot: %s%n", path.getRoot());
        System.out.format("URI: %s%n", path.toUri());
        System.out.format("Absolute: %s%n", path.toAbsolutePath());
        try {
            System.out.format("Real no symlink: %s%n", path.toRealPath(LinkOption.NOFOLLOW_LINKS));
            System.out.format("Real: %s%n", path.toRealPath());
        } catch (NoSuchFileException e) {
            System.out.println("No such file");
        }
    }

    private void showFileInfo(Path path) throws IOException {
        if (Files.notExists(path)) {
            return;
        }

        System.out.println(path + " size: " + Files.size(path));
        System.out.println(path + " owner: " + Files.getOwner(path));
        System.out.println(path + " perms: " + Files.getPosixFilePermissions(path));
        System.out.println(path + " modify time: " + formatDate(Files.getLastModifiedTime(path)));

        PosixFileAttributeView pview = Files.getFileAttributeView(path, PosixFileAttributeView.class);
        System.out.println(path + " group: " + pview.readAttributes().group());

        BasicFileAttributeView bview = Files.getFileAttributeView(path, BasicFileAttributeView.class);
        System.out.println(path + " create time: " + formatDate(bview.readAttributes().creationTime()));
        System.out.println(path + " access time: " + formatDate(bview.readAttributes().lastAccessTime()));

        if (Files.isReadable(path)) {
            UserDefinedFileAttributeView uview = Files.getFileAttributeView(path, UserDefinedFileAttributeView.class);
            for (String name : uview.list()) {
                ByteBuffer buf = ByteBuffer.allocate(uview.size(name));
                uview.read(name, buf);
                buf.flip();
                System.out.println(path + " user attr " + name + ": " + buf.toString());
            }
        }


        if (Files.isDirectory(path)) {
            System.out.println(path + " is directory");
        }
        if (Files.isExecutable(path)) {
            System.out.println(path + " is executable");
        }
        if (Files.isHidden(path)) {
            System.out.println(path + " is hidden");
        }
        if (Files.isReadable(path)) {
            System.out.println(path + " is readable");
        }
        if (Files.isWritable(path)) {
            System.out.println(path + " is writable");
        }
        if (Files.isRegularFile(path)) {
            System.out.println(path + " is regular file");
        }
        if (Files.isSymbolicLink(path)) {
            System.out.println(path + " is symbolic link");
        }
    }

    private String formatDate(FileTime time) {
        DateFormat df = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM, new Locale("cs", "CZ"));
        return df.format(new Date(time.toMillis()));
    }
}
