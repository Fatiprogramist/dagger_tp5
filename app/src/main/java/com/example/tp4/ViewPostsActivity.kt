package com.example.tp4

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tp4.adapter.PostsAdapter
import com.example.tp4.model.post.view.PostsViewModel
import com.example.tp4.model.post.view.PostsViewModelFactory
import javax.inject.Inject

class ViewPostsActivity : AppCompatActivity() {

    @Inject
    lateinit var postsViewModelFactory: PostsViewModelFactory

    private val postsViewModel: PostsViewModel by viewModels { postsViewModelFactory }
    private lateinit var postsAdapter: PostsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_posts)

        // Dagger injection
        (application as App).appComponent.inject(this)

        // Initialize the RecyclerView
        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        postsAdapter = PostsAdapter(emptyList())
        recyclerView.adapter = postsAdapter

        postsViewModel.allPosts.observe(this) { posts ->
            postsAdapter.setPosts(posts)
        }

    }
}
