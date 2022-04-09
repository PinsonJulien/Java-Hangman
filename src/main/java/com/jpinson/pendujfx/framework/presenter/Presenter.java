package com.jpinson.pendujfx.framework.presenter;

import com.jpinson.pendujfx.interfaces.InitResetInterface;
import com.jpinson.pendujfx.framework.view.View;

public abstract class Presenter<V extends View<?,?>> implements InitResetInterface {
    private V view;

    public Presenter (V view) {
        this.setView(view);
    }

    // Getters / Setters
    public V getView() {
        return this.view;
    }
    public void setView(V view) {
        this.view = view;
    }

    // Methods
}
