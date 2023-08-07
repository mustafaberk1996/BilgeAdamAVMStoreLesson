package com.example.avmstorelesson.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.avmstorelesson.R
import com.example.avmstorelesson.data.model.Store
import com.example.avmstorelesson.data.state.AdapterState
import com.example.avmstorelesson.data.state.StoreListState
import com.example.avmstorelesson.databinding.ActivityMainBinding
import com.example.avmstorelesson.showAlert
import com.example.avmstorelesson.ui.adapter.StoreAdapter
import com.example.avmstorelesson.ui.storeAdd.StoreAddActivity
import com.example.avmstorelesson.ui.storeDetail.StoreDetailActivity
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {


    private lateinit var binding:ActivityMainBinding
    private val viewModel:MainViewModel by viewModels()

    var adapter: StoreAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        observeStoreListState()
        observeAdapterState()

        binding.btnAddStore.setOnClickListener {
            val intent = Intent(this, StoreAddActivity::class.java)
            startActivityForResult(intent, STORE_ADD_REQUEST_CODE)
        }


    }

    private fun observeAdapterState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.adapterState.collect{
                    when(it){
                        is AdapterState.Removed->{
                            adapter?.notifyItemRemoved(it.index)
                        }
                        is AdapterState.Added->{
                            adapter?.notifyItemInserted(it.index)
                        }
                        else-> {

                        }
                    }
                }
            }
        }
    }

    private fun observeStoreListState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.storeListState.collect{
                    drawStoreListState(it)
                }
            }
        }
    }

    companion object{
        const val STORE_KEY = "store"
        const val STORE_INDEX_KEY = "store_index"
        const val ADDED_STORE_INDEX_KEY = "added_store_index_key"
        const val STORE_DELETE_REQUEST_CODE = 1
        const val STORE_ADD_REQUEST_CODE = 2
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == STORE_DELETE_REQUEST_CODE && resultCode == RESULT_OK){
            val index = data?.getIntExtra(STORE_INDEX_KEY, -1)
            data?.getParcelableExtra<Store>(STORE_KEY)?.let {
                Snackbar.make(binding.rvStores,"${it.name} magazasi silindi!",Snackbar.LENGTH_LONG).show()
            }
            if (index == -1){
                showAlert("Uyari","magaza bulunamadi")
            }else{
                viewModel.deleteStore(index!!)
            }
        }

        if (requestCode == STORE_ADD_REQUEST_CODE && resultCode == RESULT_OK){
            data?.getIntExtra(ADDED_STORE_INDEX_KEY,-1)?.let {
                if (it!=-1){
                    viewModel.storeAdded(it)
                }
            }

        }
    }

    private fun drawStoreListState(storeListState: StoreListState) {
        when(storeListState){
            is StoreListState.Idle->{}
            is StoreListState.Loading->{
                binding.progressBar.isVisible = true
                binding.rvStores.isVisible = false
            }
            is StoreListState.Result->{
                binding.progressBar.isVisible = false
                binding.rvStores.isVisible = true

                adapter = StoreAdapter(this, storeListState.stores){
                    val intent = Intent(this, StoreDetailActivity::class.java)
                    intent.putExtra(STORE_KEY, it)
                    startActivityForResult(intent, STORE_DELETE_REQUEST_CODE)
                }
                binding.rvStores.adapter = adapter

            }
            is StoreListState.Error->{
                binding.progressBar.isVisible = false
                binding.rvStores.isVisible = false

                showAlert("Hata","Bir sorun olustu")
            }
        }
    }


}