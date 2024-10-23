package com.example.tp4.model.post.view

import com.example.tp4.model.post.room.PostRepository
import dagger.Module
import dagger.Provides

@Module
class ViewModule {
    @Provides
    fun providePostsViewModelFactory(repository: PostRepository): PostsViewModelFactory {
        return PostsViewModelFactory(repository)
    }
}
