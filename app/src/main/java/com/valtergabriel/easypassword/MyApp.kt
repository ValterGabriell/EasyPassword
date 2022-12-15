package com.valtergabriel.easypassword

import android.app.Application
import com.valtergabriel.easypassword.src.data.db.DatabaseManager
import com.valtergabriel.easypassword.src.data.repository.MyRepositoryImpl
import com.valtergabriel.easypassword.src.data.repository.PasswordRepository
import com.valtergabriel.easypassword.src.ui.viewmodel.AddPasswordViewModel
import com.valtergabriel.easypassword.src.ui.viewmodel.MainViewModel
import com.valtergabriel.easypassword.src.ui.viewmodel.PasswordViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            // Log Koin into Android logger
            androidLogger()
            // Reference Android context
            androidContext(this@MyApp)
            // Load modules
            modules(modules)
        }
    }
}

val modules = module {
    single { MyRepositoryImpl(get()) }
    viewModel { AddPasswordViewModel(get()) }
    viewModel {MainViewModel(get())}
    viewModel {PasswordViewModel(get())}
}