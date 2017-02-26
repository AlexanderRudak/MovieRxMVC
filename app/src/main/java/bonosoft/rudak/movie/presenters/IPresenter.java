package bonosoft.rudak.movie.presenters;


import bonosoft.rudak.movie.views.IView;

public interface IPresenter {
    void bind(IView view);
    void unbind();
}
