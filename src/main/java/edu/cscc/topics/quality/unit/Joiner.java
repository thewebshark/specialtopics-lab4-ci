package edu.cscc.topics.quality.unit;

public interface Joiner {

    /**
     * Joins a series of strings using #separator between them
     * @param separator
     * @param base
     * @param parts
     * @return
     */
    public String join(String separator, String base, String... parts);
}
