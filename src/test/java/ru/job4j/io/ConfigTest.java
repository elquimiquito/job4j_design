package ru.job4j.io;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

class ConfigTest {

    @Test
    void whenPairWithoutComment() {
        String path = "./data/configTest1.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Petr Arsentev");
    }

    @Test
    void whenCommentsAndEmptyLines() {
        String path = "./data/configTest2.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("name")).isEqualTo("Igor Bielogolovski");
        assertThat(config.value("age")).isEqualTo("21");
    }

    @Test
    void whenIncorrectExpressions() {
        String path = "./data/configTest3.properties";
        Config config = new Config(path);
        assertThatThrownBy(config::load).isInstanceOf(IllegalArgumentException.class);
    }
}