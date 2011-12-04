package exam.nio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.GroupPrincipal;
import java.nio.file.attribute.UserPrincipal;

/**
 * Created by IntelliJ IDEA.
 * User: ehp
 * Date: 26.11.11
 * Time: 23:56
 * To change this template use File | Settings | File Templates.
 */
public class Example3 {
    public static void main(String[] args) throws Exception {
        new Example3().run();
    }

    private void run() throws IOException {

        for (FileStore store : FileSystems.getDefault().getFileStores()) {
            showStoreInfo(store);
        }

        for (String s : FileSystems.getDefault().supportedFileAttributeViews()) {
            System.out.println("View: " + s);
        }
        for (Path p : FileSystems.getDefault().getRootDirectories()) {
            System.out.println("Root: " + p);
        }
    }

    private void showStoreInfo(FileStore store) throws IOException {
        System.out.println(store);
        System.out.println("Total space: " + store.getTotalSpace());
        System.out.println("Unallocated space: " + store.getUnallocatedSpace());
        System.out.println("Usable space: " + store.getUsableSpace());
        System.out.println("Type: " + store.type());
        System.out.println("Name: " + store.name());
        System.out.println("Read only: " + store.isReadOnly());
    }

}
