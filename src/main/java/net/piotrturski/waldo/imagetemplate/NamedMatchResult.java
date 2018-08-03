package net.piotrturski.waldo.imagetemplate;

public class NamedMatchResult {

    public final String name;
    public final MatchResult matchResult;

    NamedMatchResult(String name, MatchResult matchResult) {
        this.name = name;
        this.matchResult = matchResult;
    }
}

class MatchResult {

    public final int x;
    public final int y;
    public final double threshold;

    MatchResult(int x, int y, double threshold) {
        this.x = x;
        this.y = y;
        this.threshold = threshold;
    }

}
