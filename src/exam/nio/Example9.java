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
public class Example9 {
    public static void main(String[] args) throws Exception {
        new Example9().run();
    }

    public void run() throws IOException {
        WatchService watch = FileSystems.getDefault().newWatchService();
        Path dir = Paths.get("/tmp");
        dir.register(watch, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);

        for (;;) {
            WatchKey key = null;

            try {
                key = watch.take();
            } catch (InterruptedException e) {
                break;
            }

            for (WatchEvent event : key.pollEvents()) {
                WatchEvent.Kind kind = event.kind();

                if (kind == StandardWatchEventKinds.OVERFLOW) {
                    continue;
                }

                WatchEvent<Path> pev = (WatchEvent<Path>)event;

                Path p = pev.context();
                String type = Files.probeContentType(dir.resolve(p));
                System.out.println(p + " " + type + " " + kind);
            }

            boolean valid = key.reset();
            if (!valid) {
                break;
            }
        }
    }
}
