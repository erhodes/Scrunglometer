package com.gemalto.scgrunglometer.viewmodel

import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import com.gemalto.scgrunglometer.model.Symptom
import com.gemalto.scgrunglometer.repository.RecipeRepository

class SymptomViewModel: ViewModel() {
    val symptoms: SnapshotStateList<Symptom>
        get() = repository.symptoms

    private lateinit var repository: RecipeRepository

    //todo replace this with dependency injection
    fun initialize(repo: RecipeRepository) {
        repository = repo
    }

    fun addSymptom(name: String) {
        repository.addSymptom(name)
    }
}