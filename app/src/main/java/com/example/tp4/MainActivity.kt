package com.example.tp4

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.tp4.model.post.Post
import com.example.tp4.model.post.view.PostsViewModel
import com.example.tp4.model.post.view.PostsViewModelFactory
import com.example.tp4.model.post.retrofit.ApiService
import com.example.tp4.model.post.room.PostDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var apiService: ApiService

    @Inject
    lateinit var postsViewModelFactory: PostsViewModelFactory

    @Inject
    lateinit var postDao: PostDao

    private val postsViewModel: PostsViewModel by viewModels { postsViewModelFactory }

    private lateinit var textView: TextView
    private lateinit var btnFetchData: Button
    private lateinit var btnViewData: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        (application as App).appComponent.inject(this)

        textView = findViewById(R.id.textView)
        btnFetchData = findViewById(R.id.btnFetchData)
        btnViewData = findViewById(R.id.btnViewData)

        // Fetch posts and observe LiveData when the button is clicked
        btnFetchData.setOnClickListener {
            fetchPosts()
        }

        // Start ViewPostsActivity when button is clicked
        btnViewData.setOnClickListener {
            val intent = Intent(this, ViewPostsActivity::class.java)
            startActivity(intent)
        }

        // Observe posts from the ViewModel
      //  observePosts()
    }

    private fun fetchPosts() {
        apiService.getPosts().enqueue(object : Callback<List<Post>> {
            override fun onResponse(call: Call<List<Post>>, response: Response<List<Post>>) {
                if (response.isSuccessful) {
                    val posts = response.body()
                    if (posts != null) {
                        savePostsToDatabase(posts)
                        displayPosts(posts)
                    }
                }
            }

            override fun onFailure(call: Call<List<Post>>, t: Throwable) {
                textView.text = "Error: ${t.message}"
            }
        })
    }

    private fun savePostsToDatabase(posts: List<Post>) {
        CoroutineScope(Dispatchers.IO).launch {
            postDao.deleteAllPosts()
            postDao.insertPosts(posts)

        }
    }

    private fun displayPosts(posts: List<Post>) {
        val postData = StringBuilder()
        posts.forEach { post ->
            postData.append("ID: ${post.id}\n")
            postData.append("Title: ${post.title}\n")
            postData.append("Body: ${post.body}\n\n")
        }
        textView.text = postData.toString()
    }
}
