package com.gemalto.scgrunglometer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.room.Room
import com.gemalto.scgrunglometer.database.AppDatabase
import com.gemalto.scgrunglometer.repository.RecipeRepository
import com.gemalto.scgrunglometer.ui.theme.ScrunglometerTheme
import com.gemalto.scgrunglometer.viewmodel.IngredientViewModel
import com.gemalto.scgrunglometer.viewmodel.RecipeViewModel
import com.gemalto.scgrunglometer.viewmodel.SymptomViewModel

class MainActivity : ComponentActivity() {

    private val mainViewModel: RecipeViewModel by viewModels()
    private val ingredientViewModel: IngredientViewModel by viewModels()
    private val symptomViewModel: SymptomViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "scrunglometer-db"
        ).build()
        val repo = RecipeRepository(database)
        mainViewModel.initialize(repo)
        ingredientViewModel.initialize(repo)
        symptomViewModel.initialize(repo)

        enableEdgeToEdge()
        setContent {
            ScrunglometerTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainApp(
                        mainViewModel,
                        ingredientViewModel,
                        symptomViewModel,
                        Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}