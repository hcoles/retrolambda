// Copyright © 2013 Esko Luontola <www.orfjackal.net>
// This software is released under the Apache License 2.0.
// The license text is at http://www.apache.org/licenses/LICENSE-2.0

package net.orfjackal.retrolambda;

import org.objectweb.asm.Opcodes;

import java.nio.file.*;
import java.util.Properties;

public class Config {

    private static final String PREFIX = "retrolambda.";
    private static final String BYTECODE_VERSION = PREFIX + "bytecodeVersion";
    private static final String INPUT_DIR = PREFIX + "inputDir";
    private static final String OUTPUT_DIR = PREFIX + "outputDir";
    private static final String CLASSPATH = PREFIX + "classpath";

    private final Properties p;

    public Config(Properties p) {
        this.p = p;
    }

    public int getBytecodeVersion() {
        return Integer.parseInt(p.getProperty(BYTECODE_VERSION, "" + Opcodes.V1_7));
    }

    public Path getInputDir() {
        return Paths.get(getRequiredProperty(INPUT_DIR));
    }

    public Path getOutputDir() {
        String outputDir = p.getProperty(OUTPUT_DIR);
        if (outputDir == null) {
            return getInputDir();
        }
        return Paths.get(outputDir);
    }

    public String getClasspath() {
        return getRequiredProperty(CLASSPATH);
    }

    private String getRequiredProperty(String key) {
        String value = p.getProperty(key);
        if (value == null) {
            throw new IllegalArgumentException("Missing required property: " + key);
        }
        return value;
    }
}
