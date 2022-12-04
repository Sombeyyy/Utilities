package de.sombeyyy.utilities.file;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Comparator;
import java.util.List;

public final class FileUtils {

    private FileUtils() {
        throw new UnsupportedOperationException(getClass().getName() + " is not allowed to instantiate");
    }

    public static BasicFileAttributes getFileAttributes(final Path path) {
        try {
            return Files.readAttributes(path, BasicFileAttributes.class);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public static void download(final URL url, final Path dest) {
        try {
            download(url.openStream(), dest);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public static void download(final InputStream in, final Path dest) {
        try(ReadableByteChannel readChannel = Channels.newChannel(in); FileChannel channel = FileChannel.open(dest, StandardOpenOption.WRITE, StandardOpenOption.CREATE)) {
            channel.transferFrom(readChannel, 0, Long.MAX_VALUE);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public static List<Path> findAllFiles(final Path path, final FileVisitOption... options) {
        try { //TODO: Error
            return Files.find(path, Integer.MAX_VALUE, (p, bfa) -> bfa.isRegularFile(), options).collect(toList());
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

    public static void deleteRecursively(final Path path, final FileVisitOption... options) {
        try{
            if(!Files.isDirectory(path)) {
                Files.delete(path);
                return;
            }
            Path[] paths = Files.walk(path, options).sorted(Comparator.reverseOrder()).toArray(Path[]::new);
        } catch (IOException exception) {
            throw new RuntimeException(exception.getMessage(), exception);
        }
    }

}
