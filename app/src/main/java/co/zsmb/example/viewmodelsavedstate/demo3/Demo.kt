package co.zsmb.example.viewmodelsavedstate.demo3

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateVMFactory
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import co.zsmb.example.viewmodelsavedstate.R
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class HandleDelegate<T>(
    private val handle: SavedStateHandle,
    private val key: String
) : ReadWriteProperty<Any, T?> {

    override fun getValue(thisRef: Any, property: KProperty<*>): T? {
        return handle[key]
    }

    override fun setValue(thisRef: Any, property: KProperty<*>, value: T?) {
        handle[key] = value
    }

}

class MyViewModel(handle: SavedStateHandle) : ViewModel() {
    var name: String? by HandleDelegate(handle, "name")
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
            viewModel.name = name
            nameText.text = name
            nameInput.setText("")
        }

        nameText.text = viewModel.name
    }

}
