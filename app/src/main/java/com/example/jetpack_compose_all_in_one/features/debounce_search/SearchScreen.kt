package com.example.jetpack_compose_all_in_one.features.debounce_search

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import io.reactivex.disposables.CompositeDisposable

@Composable
fun SearchScreen() {
    val viewModel: SearchViewModel by viewModel()
    var searchQuery by remember { mutableStateOf("") }
    val compositeDisposable = remember { CompositeDisposable() }

    Column(modifier = Modifier.padding(16.dp)) {
        TextField(
            value = searchQuery,
            onValueChange = {
                searchQuery = it
                compositeDisposable.clear() // Clear previous disposables
                if (it.isNotEmpty()) {
                    val disposable = viewModel.search(it)
                        .subscribe { _ ->
                            // Process the debounced search result
                            // e.g., update UI or make network request
                        }
                    compositeDisposable.add(disposable)
                }
            },
            label = { Text("Search") }
        )
    }
}
