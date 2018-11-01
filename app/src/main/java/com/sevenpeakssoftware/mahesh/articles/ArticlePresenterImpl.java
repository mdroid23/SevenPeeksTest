package com.sevenpeakssoftware.mahesh.articles;

import com.sevenpeakssoftware.model.Car;

import java.util.ArrayList;

public class ArticlePresenterImpl implements ArticleContract.Presenter,ArticleContract.GetCarIntractor.OnFinishedListener {

    private ArticleContract.View mainView;
    private ArticleContract.GetCarIntractor carIntractor;

    public ArticlePresenterImpl(ArticleContract.View view, ArticleContract.GetCarIntractor carIntractor) {
        this.mainView = view;
        this.carIntractor = carIntractor;
    }

    @Override
    public void fetchArticleList() {
        carIntractor.getCarArrayList(this);
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void onFinished(ArrayList<Car> carArrayList) {
        if(mainView != null){
            mainView.onArticleFound(carArrayList);
            mainView.dismissProgressDialog();
        }
    }

    @Override
    public void onResponseFailure() {
        if(mainView != null){
            mainView.showNoArticleView();
            mainView.dismissProgressDialog();
        }
    }

    @Override
    public void onApiFailure(Throwable t) {
        if(mainView != null){
            mainView.onArticleNotFound(t);
            mainView.dismissProgressDialog();
        }
    }


}
