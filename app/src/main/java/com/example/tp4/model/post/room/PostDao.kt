package com.example.tp4.model.post.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.tp4.model.post.Post

@Dao
interface PostDao {

    // Insert a single post
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPost(post: Post)

    // Insert multiple posts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertPosts(posts: List<Post>)

    // Return LiveData with PostEntity
    @Query("SELECT * FROM posts")
    fun getAllPosts(): LiveData<List<Post>>

    // Get a specific post by its ID
    @Query("SELECT * FROM posts WHERE id = :postId")
    fun getPostById(postId: Int): Post?

    // Update a post
    @Update
    fun updatePost(post: Post)

    // Delete a specific post
    @Delete
    fun deletePost(post: Post)

    // Delete all posts
    @Query("DELETE FROM posts")
    fun deleteAllPosts()
}
