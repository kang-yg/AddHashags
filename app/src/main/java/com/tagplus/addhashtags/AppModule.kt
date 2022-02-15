package com.tagplus.addhashtags

import com.tagplus.addhashtags.view.FragmentMine
import com.tagplus.addhashtags.view.FragmentMineAddTag
import com.tagplus.addhashtags.view.FragmentMineTagList
import com.tagplus.addhashtags.view.FragmentPopTags
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Singleton
    @Provides
    fun getFragmentPopTags() = FragmentPopTags()

    @Singleton
    @Provides
    fun getFragmentMine() = FragmentMine()

    @Singleton
    @Provides
    fun getFragmentMineTagList() = FragmentMineTagList()

    @Singleton
    @Provides
    fun getFragmentMineAddTag() = FragmentMineAddTag()
}