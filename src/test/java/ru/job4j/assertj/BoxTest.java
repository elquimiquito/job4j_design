package ru.job4j.assertj;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class BoxTest {
    @Test
    void isThisSphere() {
        Box box = new Box(0, 10);
        String name = box.whatsThis();
        assertThat(name).isEqualTo("Sphere");
    }

    @Test
    void isThisTetrahedron() {
        Box box = new Box(4, 8);
        String name = box.whatsThis();
        assertThat(name).isNotEmpty().isEqualTo("Tetrahedron");
    }

    @Test
    void whenFourVertices() {
        Box box = new Box(4, 11);
        int result = box.getNumberOfVertices();
        assertThat(result).isGreaterThan(2).isEqualTo(4);
    }

    @Test
    void whenNotFourVertices() {
        Box box = new Box(6, 11);
        int result = box.getNumberOfVertices();
        assertThat(result).isLessThan(0).isEqualTo(-1);
    }

    @Test
    void whenExist() {
        Box box = new Box(0, 15);
        boolean result = box.isExist();
        assertThat(result).isTrue();
    }

    @Test
    void whenDoesNotExist() {
        Box box = new Box(3, 15);
        boolean result = box.isExist();
        assertThat(result).isFalse();
    }

    @Test
    void whenAreaIsCalculated() {
        Box box = new Box(8, 5);
        double result = box.getArea();
        assertThat(result).isGreaterThan(0).isEqualTo(150);
    }

    @Test
    void whenAreaIsNotCalculated() {
        Box box = new Box(9, 5);
        double result = box.getArea();
        assertThat(result).isEqualTo(0);
    }
}