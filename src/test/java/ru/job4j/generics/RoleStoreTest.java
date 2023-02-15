package ru.job4j.generics;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

class RoleStoreTest {

    @Test
    void whenAddAndFindThenNameIsArtist() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Artist"));
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("Artist");
    }

    @Test
    void whenAddAndFindThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Artist"));
        Role result = store.findById("10");
        assertThat(result).isNull();
    }

    @Test
    void whenAddDuplicateAndFindNameIsArtist() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Artist"));
        store.add(new Role("1", "Painter"));
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("Artist");
    }

    @Test
    void whenReplaceThenNameIsPainter() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Artist"));
        store.replace("1", new Role("1", "Painter"));
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("Painter");
    }

    @Test
    void whenNoReplaceRoleThenNoChangeName() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Artist"));
        store.replace("10", new Role("10", "Painter"));
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("Artist");
    }

    @Test
    void whenDeleteRoleThenRoleIsNull() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Artist"));
        store.delete("1");
        Role result = store.findById("1");
        assertThat(result).isNull();
    }

    @Test
    void whenNoDeleteRoleThenNameIsArtist() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Artist"));
        store.delete("10");
        Role result = store.findById("1");
        assertThat(result.getName()).isEqualTo("Artist");
    }

    @Test
    void whenReplaceOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Artist"));
        boolean rsl = store.replace("1", new Role("1", "Painter"));
        assertThat(rsl).isTrue();
    }

    @Test
    void whenReplaceNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Artist"));
        boolean rsl = store.replace("10", new Role("10", "Painter"));
        assertThat(rsl).isFalse();
    }

    @Test
    void whenDeleteOkThenTrue() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Artist"));
        boolean rsl = store.delete("1");
        assertThat(rsl).isTrue();
    }

    @Test
    void whenDeleteNotOkThenFalse() {
        RoleStore store = new RoleStore();
        store.add(new Role("1", "Artist"));
        boolean rsl = store.delete("2");
        assertThat(rsl).isFalse();
    }
}