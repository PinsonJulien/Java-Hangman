package com.jpinson.pendujfx.framework.presenter;

import com.jpinson.pendujfx.interfaces.InitResetInterface;
import com.jpinson.pendujfx.framework.view.View;

// Presenters are the brain of the application, they communicate with both view and models.
public abstract class Presenter<V extends View<?,?>> implements InitResetInterface {
    private V view;

    public Presenter (V view) {
        this.view = view;
    }

    // Getters / Setters
    public V getView() {
        return this.view;
    }

    // Methods
}
