package com.jpinson.pendujfx.views;

import java.util.ArrayList;

public class ListenableView<L> extends View {
    private final ArrayList<L> listeners;

    public ListenableView () {
        this.listeners = new ArrayList<>();
    }

    public final ArrayList<L> getListeners() {
        return this.listeners;
    }

    public void addListener(L listener) {
        this.listeners.add(listener);
    }

}
