package com.deathmotion.anticheatbase.loader.common.utils;

import com.deathmotion.anticheatbase.loader.common.ACPlatformLoader;

import java.io.*;
import java.nio.file.Files;
import java.security.SecureClassLoader;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public final class ByteClassLoader extends SecureClassLoader {

    private final byte[] jarBytes;
    private final Set<String> names;

    public ByteClassLoader(byte[] jarBytes) throws IOException {
        super(ACPlatformLoader.class.getClassLoader());
        this.jarBytes = jarBytes;
        this.names = loadNames(jarBytes);
    }

    public ByteClassLoader(File jarFile) throws IOException {
        this(Files.readAllBytes(jarFile.toPath()));
    }

    public ByteClassLoader(InputStream jarInputStream) throws IOException {
        this(toByteArray(jarInputStream));
    }

    private static byte[] toByteArray(InputStream inputStream) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        int read;
        byte[] buffer = new byte[8192];
        while ((read = inputStream.read(buffer)) != -1) {
            baos.write(buffer, 0, read);
        }
        return baos.toByteArray();
    }

    private static Set<String> loadNames(byte[] jarBytes) throws IOException {
        Set<String> set = new HashSet<>();
        try (ZipInputStream jis =
                     new ZipInputStream(new ByteArrayInputStream(jarBytes))) {
            ZipEntry entry;
            while ((entry = jis.getNextEntry()) != null) {
                set.add(entry.getName());
            }
        }
        return Collections.unmodifiableSet(set);
    }

    @Override
    public InputStream getResourceAsStream(String name) {
        if (!names.contains(name)) {
            return null;
        }
        boolean found = false;
        ZipInputStream jis = null;
        try {
            jis = new ZipInputStream(new ByteArrayInputStream(jarBytes));
            ZipEntry entry;
            while ((entry = jis.getNextEntry()) != null) {
                if (entry.getName().equals(name)) {
                    found = true;
                    return jis;
                }
            }
        } catch (IOException exception) {
            exception.printStackTrace(System.err);
        } finally {
            if (jis != null && !found) {
                try {
                    jis.close();
                } catch (IOException exception) {
                    exception.printStackTrace(System.err);
                }
            }
        }
        return null;
    }

    @Override
    public Class<?> loadClass(String name, boolean resolve) throws ClassNotFoundException {
        Class<?> clazz = findLoadedClass(name);
        if (clazz == null) {
            try (final InputStream inputStream = getResourceAsStream(name.replace('.', '/') + ".class")) {
                assert inputStream != null;
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                int i = 0;
                while ((i = inputStream.read()) >= 0) {
                    byteArrayOutputStream.write(i);
                }
                byte[] bytes = byteArrayOutputStream.toByteArray();
                clazz = defineClass(name, bytes, 0, bytes.length);
                if (resolve) {
                    resolveClass(clazz);
                }
            } catch (Exception exception) {
                clazz = super.loadClass(name, resolve);
            }
        }
        return clazz;
    }
}
