package com.example.roomwordsample

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData

// https://codelabs.developers.google.com/codelabs/android-room-with-a-view-kotlin/#7
class WordRepository(private val wordDao: WordDao) {

    val allWords: LiveData<List<Word>> = wordDao.getAllWords() // happens on different thread, because of Room

    @WorkerThread // marks that this method needs to be called from non-UI thread
    suspend fun insert(word: Word) {
        wordDao.insert(word)
    }
}