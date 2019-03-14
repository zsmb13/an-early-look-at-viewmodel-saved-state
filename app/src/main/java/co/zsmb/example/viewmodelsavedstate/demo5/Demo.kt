package co.zsmb.example.viewmodelsavedstate.demo5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import co.zsmb.example.viewmodelsavedstate.R
import kotlinx.android.synthetic.main.activity_main.*

class MyViewModel(private val handle: SavedStateHandle) : ViewModel() {
    private val _name: MutableLiveData<String> =
        handle.getLiveData<String>("name")

    val name: LiveData<String> = _name

    fun setName(name: String) {
        if (name.isNotBlank()) {
            _name.value = name
        }
    }
}

class MainActivity : AppCompatActivity() {

    lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val factory = SavedStateVMFactory(this)
        viewModel = ViewModelProviders.of(this, factory).get(MyViewModel::class.java)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString()
            viewModel.setName(name)
            nameInput.setText("")
        }

        viewModel.name.observe(this, Observer { name ->
            nameText.text = name
        })
    }

}
