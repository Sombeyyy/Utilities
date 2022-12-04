package de.sombeyyy.utilities.file;

import de.sombeyyy.utilities.string.StringUtils;

public final class FileNameUtils {

    private FileNameUtils() {
        throw new UnsupportedOperationException(getClass().getName() + " is not allowed to instantiate");
    }

    private static final char EXTENSION_SEPARATOR = '.';

    public static int indexOfExtension(final String filename) {
        if (filename == null) return -1;
        int index = filename.lastIndexOf(EXTENSION_SEPARATOR);
        return index == 0 ? -1 : index;
    }

    public static String getName(String path) {
        if (StringUtils.isNullOrEmpty(path)) return "";
        path = path.trim();
        int index = path.lastIndexOf('\\');
        if (index == -1) index = path.lastIndexOf('/');
        if (index == -1) {
            return path;
        } else if (index == path.length() - 1) {
            String chopped = StringUtils.chop(path);
            return getName(chopped);
        }
        return path.substring(index + 1);
    }

    public static String getBaseName(final String filename) {
        if(filename == null) return "";
        int index = indexOfExtension(filename);
        return index == -1 ? filename : filename.substring(0, index);
    }

    public static String getExtension(final String filename) {
        if(filename == null) return "";
        int index = indexOfExtension(filename);
        return index == -1 ? "" : filename.substring(index + 1);
    }

    public static String replaceUnallowables(final String filename) {
        return filename.replace('\\', '＼')
                .replace('/', '／')
                .replace(':', '：')
                .replace('*', '＊')
                .replace('?', '？')
                .replace('"', '＂')
                .replace('<', '＜')
                .replace('>', '＞')
                .replace('|', '｜')
                .replaceAll("\\.{2,}+$", "…")
                .replaceAll("\\.$", "．");
    }

}
