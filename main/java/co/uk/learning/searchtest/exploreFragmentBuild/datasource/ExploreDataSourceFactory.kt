package co.uk.learning.searchtest.exploreFragmentBuild.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import co.uk.learning.searchtest.model.Broadcasts


class ExploreDataSourceFactory: DataSource.Factory<Int, Broadcasts>() {

    val userLiveDataSource = MutableLiveData<ExploreDataSource>()

    override fun create(): DataSource<Int, Broadcasts> {
        val userDataSource = ExploreDataSource()
        userLiveDataSource.postValue(userDataSource)

        return userDataSource

    }

}