package com.jpinson.pendujfx.utils;

import javafx.scene.Node;

public abstract class CssClass {

    public static void remove (Node node, String cls) {
        if (!contains(node, cls)) return;
        node.getStyleClass().remove(cls);
    }

    public static void remove (Node node, String... classes) {
        for (String cls : classes ) {
            remove(node, cls);
        }
    }

    public static void add (Node node, String cls) {
        if (contains(node, cls)) return;
        node.getStyleClass().add(cls);
    }

    public static void add (Node node, String... classes) {
        for (String cls : classes ) {
            add(node, cls);
        }
    }

    public static void swap (Node node, String beforeClass, String afterClass) {
        remove(node, beforeClass);
        add(node, afterClass);
    }

    public static boolean contains (Node node, String cls) {
        return node.getStyleClass().contains(cls);
    }
}
