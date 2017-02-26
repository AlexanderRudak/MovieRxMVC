package bonosoft.rudak.movie.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import bonosoft.rudak.movie.presenters.IPresenter;
import bonosoft.rudak.movie.presenters.Presenters;


public abstract class MvpAppCompatActivity extends AppCompatActivity implements IView{
    private boolean onSaveInstanceCalled;
    protected IPresenter presenter;
    protected int viewId = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setViewId();

        presenter = Presenters.getPresenter(getViewId());
        if (presenter == null)
            presenter = NewPresenter();

        presenter.bind(this);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        onSaveInstanceCalled = true;
    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        if (onSaveInstanceCalled)
            Presenters.savePresenter(getViewId(),presenter);
        else{
            Presenters.removePresenter(getViewId());
        }
        super.onDestroy();
    }

    @Override
    public int getViewId() {
        return viewId;
    }

    protected void setViewId(){
        viewId = 1;
    }

    protected IPresenter NewPresenter(){
        return null;
    }
}

