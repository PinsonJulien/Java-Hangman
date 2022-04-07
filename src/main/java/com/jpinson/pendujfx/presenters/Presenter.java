package com.jpinson.pendujfx.presenters;

import com.jpinson.pendujfx.views.View;

public abstract class Presenter<V extends View<?,?>> {
    private V view;

    public Presenter (V view) {
        this.setView(view);
    }

    public V getView() {
        return this.view;
    }

    public void setView(V view) {
        this.view = view;
    }
}
