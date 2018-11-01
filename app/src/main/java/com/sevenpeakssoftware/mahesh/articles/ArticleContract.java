package com.sevenpeakssoftware.mahesh.articles;

import com.sevenpeakssoftware.common.BasePresenter;
import com.sevenpeakssoftware.common.BaseView;
import com.sevenpeakssoftware.model.Car;

import java.util.ArrayList;

public interface ArticleContract {

    interface View extends BaseView {

        void onArticleFound(ArrayList<Car> articleList);

        void showNoArticleView();

        void hideNoArticleView();

        void onArticleNotFound(Throwable throwable);
    }

    interface Presenter extends BasePresenter {

        void fetchArticleList();

        void onDestroy();

    }

    /**
     * Intractors are classes built for fetching data from your database, web services, or any other data source.
     **/
    interface GetCarIntractor {

        interface OnFinishedListener {
            void onFinished(ArrayList<Car> carArrayList);
            void onResponseFailure();
            void onApiFailure(Throwable t);
        }

        void getCarArrayList(OnFinishedListener onFinishedListener);
    }
}
