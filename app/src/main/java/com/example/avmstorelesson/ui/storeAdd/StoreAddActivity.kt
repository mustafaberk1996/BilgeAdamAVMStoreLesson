package com.example.avmstorelesson.ui.storeAdd

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.avmstorelesson.R
import com.example.avmstorelesson.data.state.AdapterState
import com.example.avmstorelesson.data.state.PhotoListState
import com.example.avmstorelesson.data.state.StoreAddState
import com.example.avmstorelesson.databinding.ActivityStoreAddBinding
import com.example.avmstorelesson.databinding.ActivityStoreDetailBinding
import com.example.avmstorelesson.ui.adapter.PhotoAdapter
import com.example.avmstorelesson.ui.main.MainActivity.Companion.ADDED_STORE_INDEX_KEY
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StoreAddActivity : AppCompatActivity() {

    private lateinit var binding:ActivityStoreAddBinding
    private val viewModel:StoreAddViewModel by viewModels()

    private var adapter:PhotoAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreAddBinding.inflate(layoutInflater)
        setContentView(binding.root)


        observePhotoListState()
        observeAdapterState()
        observeStoreAddState()


        binding.btnSave.setOnClickListener {

            viewModel.saveStore(
                binding.etName.text.toString(),
                binding.etFloor.text.toString(),
                binding.etBlock.text.toString(),
                binding.etDescription.text.toString()
                )


        }

    }

    private fun observeStoreAddState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.storeAddState.collect{
                    when(it){
                        is StoreAddState.Idle->{}
                        is StoreAddState.Added->{
                            val intent = Intent()
                            intent.putExtra(ADDED_STORE_INDEX_KEY, it.index)
                            setResult(RESULT_OK,intent)
                            finish()
                        }
                    }
                }
            }
        }
    }

    private fun observeAdapterState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.adapterState.collect{
                    when(it){
                        is AdapterState.Idle->{}
                        is AdapterState.Changed -> {
                            adapter?.notifyItemChanged(it.index)
                        }
                        else->{}
                    }
                }
            }
        }
    }

    private fun observePhotoListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.photoListState.collect{
                    when(it){
                        is PhotoListState.Idle->{}
                        is PhotoListState.Result -> {
                            adapter =
                                PhotoAdapter(this@StoreAddActivity, it.photos) { clickedPhoto ->
                                    viewModel.photoSelected(clickedPhoto)
                                }
                            binding.rvPhotos.adapter = adapter
                        }
                    }
                }
            }
        }
    }
}