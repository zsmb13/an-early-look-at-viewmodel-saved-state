package co.zsmb.example.viewmodelsavedstate.demo4

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import co.zsmb.example.viewmodelsavedstate.R
import kotlinx.android.synthetic.main.activity_main.*

class MyViewModel(val handle: SavedStateHandle) : ViewModel()

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = SavedStateVMFactory(this)
        viewModel = ViewModelProviders.of(this, factory).get(MyViewModel::class.java)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            viewModel.handle.set("name", name)
            nameInput.setText("")
        }

        viewModel.handle.getLiveData<String>("name").observe(this, Observer { name ->
            nameText.text = name
        })
    }

}
