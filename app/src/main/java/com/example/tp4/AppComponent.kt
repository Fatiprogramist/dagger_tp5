package com.example.tp4



import android.content.Context
import com.example.tp4.model.post.DatabaseModule
import com.example.tp4.model.post.retrofit.NetworkModule
import com.example.tp4.model.post.view.PostsViewModelFactory
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, DatabaseModule::class])
interface AppComponent {

    fun inject(activity: MainActivity)
    fun inject(activity: ViewPostsActivity)
    fun providePostsViewModelFactory(): PostsViewModelFactory

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder
        fun build(): AppComponent
    }
}
