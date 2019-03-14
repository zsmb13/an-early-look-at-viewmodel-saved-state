package co.zsmb.example.viewmodelsavedstate.demo1

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import co.zsmb.example.viewmodelsavedstate.R
import kotlinx.android.synthetic.main.activity_main.*

class MyViewModel(val handle: SavedStateHandle) : ViewModel()

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = SavedStateVMFactory(this)
        viewModel = ViewModelProviders
            .of(this, factory)
            .get(MyViewModel::class.java)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            viewModel.handle.set("name", name)
            nameText.text = name
            nameInput.setText("")
        }

        nameText.text = viewModel.handle.get<String>("name")
    }

}
