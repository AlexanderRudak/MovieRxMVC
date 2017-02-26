package bonosoft.rudak.movie.presenters;


import bonosoft.rudak.movie.views.IView;

public abstract class BasePresenter implements IPresenter{

    IView view;

    @Override
    public void bind(IView view){
        this.view = view;
    }

    @Override
    public void unbind(){
        this.view = null;
    }
}
