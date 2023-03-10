package com.example.hiltfundamentals.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hiltfundamentals.R
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.android.scopes.ViewScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject
import javax.inject.Qualifier
import javax.inject.Singleton

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by viewModels()

    lateinit var cryptocurrencyList: RecyclerView
    lateinit var mAddButton: Button

    @Inject
    lateinit var mSomething: SomethingTwo

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cryptocurrencyList = findViewById<RecyclerView>(R.id.cryptocurrency_list)
        mAddButton = findViewById<Button>(R.id.button_add)
        cryptocurrencyList.layoutManager = LinearLayoutManager(this)


//        observeCryptoCurrency()
        println(mSomething.performActionOne())
        println(mSomething.performActionTwo())

    }

    private fun observeCryptoCurrency() {
        viewModel.cryptocurrency.observe(this) {
            cryptocurrencyList.adapter = CryptocurrencyAdapter(it)
        }
    }
}

@ActivityScoped
class SomethingTwo @Inject constructor(@Impl1 val one: TestInterface,@Impl2 val two: TestInterface) {
    fun performActionOne(): String {
        return "hello " + one.performFunctions()
    }

    fun performActionTwo(): String {
        return "hello " + two.performFunctions()
    }
}

class SomeInterfaceImpl1 @Inject constructor() : TestInterface {
    override fun performFunctions(): String {
        return "interface one  implemented"
    }
}

class SomeInterfaceImpl2 @Inject constructor() : TestInterface {
    override fun performFunctions(): String {
        return "interface two implemented"
    }
}

interface TestInterface {
    fun performFunctions(): String
}


@Module
@InstallIn(ActivityComponent::class)
class MyModule {

    @Impl1
    @Provides
    @ActivityScoped
    fun getInterfaceDetails(): TestInterface = SomeInterfaceImpl1()

    @Impl2
    @Provides
    @ActivityScoped
    fun getInterfaceDetailss(): TestInterface = SomeInterfaceImpl2()
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl1

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class Impl2

