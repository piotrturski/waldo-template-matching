package net.piotrturski.waldo.imagetemplate;

import com.google.common.base.Preconditions;

public class Application {

    public static void main(String[] args) {
        Preconditions.checkArgument(args.length == 2,
                "expected 2 parameters (file paths)");
        ConsoleDisplay.display(TemplateMatcher.match(args[0], args[1]));
    }

}

