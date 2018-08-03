package net.piotrturski.waldo.imagetemplate;

import org.bytedeco.javacpp.opencv_core;

import static org.bytedeco.javacpp.opencv_imgcodecs.IMREAD_COLOR;
import static org.bytedeco.javacpp.opencv_imgcodecs.imread;

public class NamedInput {
    public final String name;
    public final opencv_core.Mat mat;

    public NamedInput(String name) {
        this.name = name;
        this.mat = imread(name, IMREAD_COLOR);
        if (mat.empty()) {
            throw new IllegalArgumentException("can't read " + name);
        }
    }
}
