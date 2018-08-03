package net.piotrturski.waldo.imagetemplate;

import one.util.streamex.EntryStream;
import org.bytedeco.javacpp.DoublePointer;
import org.bytedeco.javacpp.opencv_core.Mat;
import org.bytedeco.javacpp.opencv_core.Point;

import java.util.Optional;

import static org.bytedeco.javacpp.opencv_core.CV_32FC1;
import static org.bytedeco.javacpp.opencv_core.minMaxLoc;
import static org.bytedeco.javacpp.opencv_imgproc.TM_CCOEFF_NORMED;
import static org.bytedeco.javacpp.opencv_imgproc.matchTemplate;

public class TemplateMatcher {

    public static Optional<NamedMatchResult> match(String img, String temp) {
        return match(new NamedInput(img), new NamedInput(temp), 0.8);
    }

    public static Optional<NamedMatchResult> match(NamedInput img1, NamedInput img2, double threshold) {

        return EntryStream.of(img1, img2,
                            img2, img1)
            .mapKeyValue((image, template) ->

                match(image.mat, template.mat)
                        .filter(match -> match.threshold > threshold)
                        .map(match -> new NamedMatchResult(image.name, match))
            )
            .findFirst(Optional::isPresent)
            .map(Optional::get)
            ;
    }

    static Optional<MatchResult> match(Mat image, Mat template) {

        int resultRows = image.rows() - template.rows() + 1;
        int resultCols = image.cols() - template.cols() + 1;

        if (resultCols <= 0 || resultRows <= 0) {
            return Optional.empty();
        }

        Mat result = new Mat(resultRows, resultCols, CV_32FC1);

        matchTemplate(image, template, result, TM_CCOEFF_NORMED);

        DoublePointer maxVal = new DoublePointer(1);
        Point maxLoc = new Point();

        minMaxLoc(result, null, maxVal, null, maxLoc, null);

        MatchResult matchResult = new MatchResult(maxLoc.x(), maxLoc.y(), maxVal.get());
        return Optional.of(matchResult);
    }

}

