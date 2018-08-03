package net.piotrturski.waldo.imagetemplate;

import org.junit.Test;

import java.nio.file.Paths;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class TemplateMatcherTest {


    @Test
    public void should_detect_embedded_template() {

        NamedMatchResult result = TemplateMatcher.match(
                resolve("koala1.jpg"), resolve("koala2.jpg"))
                .get();

         assertThat(result.name).endsWith("koala1.jpg");
         assertThat(result.matchResult.x).isEqualTo(80);
         assertThat(result.matchResult.y).isEqualTo(17);
         assertThat(result.matchResult.threshold).isGreaterThan(0.9);
    }


    @Test
    public void should_detect_template_when_files_are_reversed() {
        NamedMatchResult result = TemplateMatcher.match(
                resolve("koala2.jpg"), resolve("koala1.jpg"))
                .get();

        assertThat(result.name).endsWith("koala1.jpg");
        assertThat(result.matchResult.x).isEqualTo(80);
        assertThat(result.matchResult.y).isEqualTo(17);
        assertThat(result.matchResult.threshold).isGreaterThan(0.9);
    }

    @Test
    public void should_reject_match_below_threshold() {
        Optional<NamedMatchResult> result = TemplateMatcher.match(
                resolve("not-koala.jpg"), resolve("koala1.jpg"));

        assertThat(result).isEmpty();
    }

    private String resolve(String filename) {
        return Paths.get("src/test/resources").resolve(filename).toString();
    }

}