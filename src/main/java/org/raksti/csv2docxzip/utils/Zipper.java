package org.raksti.csv2docxzip.utils;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zipper implements AutoCloseable {
    private final ZipOutputStream zipOutputStream;

    public Zipper(@NotNull OutputStream outputStream) {
        this.zipOutputStream = new ZipOutputStream(new BufferedOutputStream(outputStream));
    }

    public void add(@NotNull String fileName, @NotNull byte[] buffer) throws IOException {
        zipOutputStream.putNextEntry(new ZipEntry(fileName));
        zipOutputStream.write(buffer);
        zipOutputStream.closeEntry();
    }

    @Override
    public void close() throws Exception {
        zipOutputStream.close();
    }
}
