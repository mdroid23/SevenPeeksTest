package com.sevenpeakssoftware.mahesh

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.sevenpeakssoftware.adapter.CarsAdapter
import com.sevenpeakssoftware.common.BaseActivity
import com.sevenpeakssoftware.mahesh.articles.ArticleContract
import com.sevenpeakssoftware.mahesh.articles.ArticlePresenterImpl
import com.sevenpeakssoftware.mahesh.articles.GetCarInteractorImpl
import com.sevenpeakssoftware.model.Car
import com.sevenpeakssoftware.utils.LogUtil
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_no_connection.*
import kotlinx.android.synthetic.main.layout_no_more_item.*
import java.util.*


class ArticleListActivity : BaseActivity(), ArticleContract.View {

    private val TAG = javaClass.name
    private var mContext: Context? = null

    private lateinit var presenter: ArticleContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mContext = this

        val layoutManager = LinearLayoutManager(this)
        layoutManager.orientation = LinearLayoutManager.VERTICAL
        recyclerView.setLayoutManager(layoutManager)
        setListener()
    }


    override fun onStart() {
        super.onStart()
        fetchDataFromServer()
    }

    private fun setListener() {

        buttonRetry.setOnClickListener(View.OnClickListener {
            fetchDataFromServer()
        })
    }

    private fun fetchDataFromServer() {

        if (hasConnectivity()) {
            layoutConnection.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            presenter = ArticlePresenterImpl(this, GetCarInteractorImpl())
            presenter.fetchArticleList()
            showProgressDialog(R.string.str_msg_fetching_articles)
        } else {
            layoutConnection.visibility = View.VISIBLE
            recyclerView.visibility = View.GONE
        }

    }

    override fun onArticleFound(articleList: ArrayList<Car>?) {
        LogUtil.info(TAG, "Number of cars : " + articleList?.size)
        if (articleList?.size == 0) {
            showNoArticleView()
        } else {
            hideNoArticleView()
            val adapter = CarsAdapter(mContext, articleList)
            recyclerView.setAdapter(adapter)
        }
    }

    override fun showNoArticleView() {
        layout_no_item.visibility = View.VISIBLE
        recyclerView.visibility = View.GONE
    }

    override fun hideNoArticleView() {
        layout_no_item.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
    }

    override fun onArticleNotFound(throwable: Throwable?) {
        dismissProgressDialog()
        showNoArticleView()
        showToast("Error:" + throwable?.message)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDestroy()
    }
}
