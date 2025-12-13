package ru.uni.collections;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CollectionTests {
    @Test
    void arrayTests(){
        var array = new String[]{"a","b","c"};
        Assertions.assertEquals("a",array[0]);

        array[0]= "d";
        Assertions.assertEquals("d",array[0]);
    }

    @Test
    void  litsTests() {
        //var list = List.of("a","b","c");
        var list = new ArrayList<>(List.of("a","b","c"));
        Assertions.assertEquals(3, list.size());
        Assertions.assertEquals("a", list.get(0));

        list.set(0, "d");
        Assertions.assertEquals("d", list.get(0));
    }

    @Test
    void setTest()
    {
        var set = Set.copyOf(List.of("a","b","c","c"));
        Assertions.assertEquals(3, set.size());
        var element = set.stream().findAny().get();
    }
}
