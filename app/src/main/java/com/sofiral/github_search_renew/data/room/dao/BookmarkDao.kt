package com.sofiral.github_search_renew.data.room.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.sofiral.github_search_renew.data.room.model.Bookmark
import io.reactivex.rxjava3.core.Completable
import io.reactivex.rxjava3.core.Single

@Dao
interface BookmarkDao {

    @Query("SELECT * FROM bookmark WHERE userId= :userId")
    fun getBookmarksOfUser(userId: String): Single<List<Bookmark>>

    @Insert
    fun insertBookmark(bookmark: Bookmark): Completable

    @Delete
    fun deleteBookmark(bookmarks: List<Bookmark>): Completable
}