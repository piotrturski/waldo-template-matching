package net.piotrturski.waldo.imagetemplate;

import io.vavr.control.Option;

import java.util.Optional;

public class ConsoleDisplay {

    public static void display(Optional<NamedMatchResult> result) {

        Option.ofOptional(result)
                .map(match -> String.format("match found at (%s, %s) of %s",
                        match.matchResult.x, match.matchResult.y, match.name))

                .orElse(Option.of("no match found"))
                .peek(System.out::println);
    }
}
