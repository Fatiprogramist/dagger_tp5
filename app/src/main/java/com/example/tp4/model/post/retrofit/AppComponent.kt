package com.example.tp4.model.post.retrofit

import com.example.tp4.MainActivity
import com.example.tp4.di.NetworkModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}
