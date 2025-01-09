/*
 * Copyright (C) 2018 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.paging3demo

import android.content.Context
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.savedstate.SavedStateRegistryOwner
import com.android.paging3demo.api.GithubService
import com.android.paging3demo.data.GithubRepository
import com.android.paging3demo.db.RepoDatabase

/**
 * Creates an instance of [GithubRepository] based on the [GithubService] and a
 * [GithubLocalCache]
 */
private fun Context.provideGithubRepository( ): GithubRepository {
    return GithubRepository(GithubService.create(), RepoDatabase.getInstance(this))
}

/**
 * Provides the [ViewModelProvider.Factory] that is then used to get a reference to
 * [ViewModel] objects.
 */
fun Context.provideViewModelFactory(owner: SavedStateRegistryOwner): ViewModelProvider.Factory {
    return ViewModelFactory(owner, provideGithubRepository())
}

/**
 * Factory for ViewModels
 */
class ViewModelFactory(
    owner: SavedStateRegistryOwner,
    private val repository: GithubRepository
) : AbstractSavedStateViewModelFactory(owner, null) {

    override fun <T : ViewModel> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        if (modelClass.isAssignableFrom(SearchRepositoriesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SearchRepositoriesViewModel(repository, handle) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}