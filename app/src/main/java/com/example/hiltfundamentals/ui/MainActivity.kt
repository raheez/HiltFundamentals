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
        println(mSomething.performActionTwo())

    }

    private fun observeCryptoCurrency() {
        viewModel.cryptocurrency.observe(this) {
            cryptocurrencyList.adapter = CryptocurrencyAdapter(it)
        }
    }
}

@ActivityScoped
class SomethingTwo @Inject constructor(val someInterfaceImpl: TestInterface)  {
    fun performActionTwo(): String {
        return "hello "+ someInterfaceImpl.performFunctions()
    }
}

class SomeInterfaceImpl @Inject constructor () : TestInterface{
    override fun performFunctions(): String {
        return "interface implemented"
    }
}

interface TestInterface{
    fun performFunctions() :String
}







@Module
@InstallIn(ActivityComponent::class)
 class MyModule{

     @Provides
     @ActivityScoped
     fun getInterfaceDetails():TestInterface  = SomeInterfaceImpl()
}