package com.jpinson.pendujfx.framework.presenter;

import com.jpinson.pendujfx.framework.view.View;

import java.util.EnumMap;

// A ParentPresenter holds several child presenters inside a list
// Childs can call the parent to insert swap views.
public abstract class ParentPresenter
<
    V extends View<?, ?>,
    E extends Enum<E>,
    P extends ChildPresenter<?, ?>
>
    extends Presenter<V>
    implements ParentPresenterListener<E>
{
    private final EnumMap<E, P> childrenPresenterList;

    public ParentPresenter(
        V view,
        EnumMap<E, P> childrenPresenterList
    ) {
        super(view);
        this.childrenPresenterList = childrenPresenterList;
    }

    // Listeners
    @Override
    public void selectPresenter(E alias) {
        this.changeView(alias);
    }

    // Methods
    public void changeView(E alias) {
        this.removeCurrentPresenter();
        P presenter = this.getChildPresenter(alias);
        presenter.reset();
        this.getView().insertNode(presenter.getView().getPane());
    }

    public void removeCurrentPresenter() {
        this.getView().removeNodes();
    }

    public P getChildPresenter(E id) {
        return this.childrenPresenterList.get(id);
    }

    public void addChildPresenter(E id, P childPresenter) {
        this.childrenPresenterList.put(id, childPresenter);
    }
}
