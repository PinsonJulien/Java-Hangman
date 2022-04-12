package com.jpinson.pendujfx.framework.presenter;

import com.jpinson.pendujfx.framework.view.View;

public abstract class ChildPresenter <
    L extends ParentPresenterListener<?>,
    V extends View<?, ?>
>
    extends Presenter<V>
{
    private final L parentListener;

    public ChildPresenter(V view, L parentListener) {
        super(view);
        this.parentListener = parentListener;
    }

    // getters / setters
    public L getParentListener() {
        return this.parentListener;
    }
}
