package com.jpinson.pendujfx.framework.presenter;

import com.jpinson.pendujfx.framework.view.View;

// Child presenters are sub-presenters of their parent.
// Each child presenter may have completely different usage.

public abstract class ChildPresenter <
    L extends ParentPresenterListener<?>,
    V extends View<?, ?>
>
    extends Presenter<V>
{
    protected final L parentListener;

    public ChildPresenter(V view, L parentListener) {
        super(view);
        this.parentListener = parentListener;
    }
}
