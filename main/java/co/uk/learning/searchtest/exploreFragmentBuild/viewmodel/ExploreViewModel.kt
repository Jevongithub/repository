package co.uk.learning.searchtest.exploreFragmentBuild.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import co.uk.learning.searchtest.exploreFragmentBuild.datasource.ExploreDataSource
import co.uk.learning.searchtest.exploreFragmentBuild.datasource.ExploreDataSourceFactory
import co.uk.learning.searchtest.model.Broadcasts

class ExploreViewModel : ViewModel() {

    val userPagedList : LiveData<PagedList<Broadcasts>>

    private val liveDataSource : LiveData<ExploreDataSource>

    init {
        val itemDataSourceFactory = ExploreDataSourceFactory()

        liveDataSource = itemDataSourceFactory.userLiveDataSource

        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(false)
            .setPageSize(ExploreDataSource.PAGE_SIZE)
            .build()

        userPagedList = LivePagedListBuilder(itemDataSourceFactory,config)
            .build()


    }

}