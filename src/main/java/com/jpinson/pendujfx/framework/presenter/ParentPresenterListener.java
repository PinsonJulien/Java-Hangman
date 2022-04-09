package com.jpinson.pendujfx.framework.presenter;

public interface ParentPresenterListener<E extends Enum<E>> {
    void selectPresenter(E alias);
}
