package com.jpinson.pendujfx.utils;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.scene.layout.Region;
import junit.framework.TestCase;

public class CssClassTest extends TestCase {
    final String cls1 = "test";
    final String cls2 = "test2";
    final String cls3 = "test3";

    static {
        // Allow test to work.
        Platform.startup(() -> {});
    }

    public void testAdd () {
        Region element = new Region();
        ObservableList<String> clsList = element.getStyleClass();
        // Add a css class to the element.
        CssClass.add(element, cls1);
        assertTrue(clsList.contains(cls1));

        // Cannot add the same class more than once.
        CssClass.add(element, cls1);
        assertEquals(1, clsList.size());
    }

    public void testRemove () {
        Region element = new Region();
        ObservableList<String> clsList = element.getStyleClass();
        clsList.addAll(cls1, cls2);

        // Removes the right css class
        CssClass.remove(element, cls1);
        assertEquals(1, clsList.size());

        // Doesn't remove more if the class doesn't exist.
        CssClass.remove(element, cls1);
        assertEquals(1, clsList.size());
    }

    public void testSwap() {
        Region element = new Region();
        ObservableList<String> clsList = element.getStyleClass();
        clsList.addAll(cls1, cls2);

        // Swap properly two classes
        CssClass.swap(element, cls1, cls3);
        assertEquals(2, clsList.size());
        assertTrue(
            clsList.contains(cls2)
            && clsList.contains(cls3)
            && !clsList.contains(cls1)
        );
    }

    public void testContains() {
        Region element = new Region();
        ObservableList<String> clsList = element.getStyleClass();
        clsList.addAll(cls1);

        assertTrue(CssClass.contains(element, cls1));
        assertFalse(CssClass.contains(element, cls2));
    }
}
