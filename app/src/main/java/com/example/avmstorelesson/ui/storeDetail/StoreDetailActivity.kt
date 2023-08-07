package com.example.avmstorelesson.ui.storeDetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import coil.load
import com.example.avmstorelesson.R
import com.example.avmstorelesson.data.model.Store
import com.example.avmstorelesson.data.state.StoreDeleteState
import com.example.avmstorelesson.databinding.ActivityStoreDetailBinding
import com.example.avmstorelesson.showToast
import com.example.avmstorelesson.ui.main.MainActivity
import com.example.avmstorelesson.ui.main.MainActivity.Companion.STORE_INDEX_KEY
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StoreDetailActivity : AppCompatActivity() {

    private lateinit var binding:ActivityStoreDetailBinding
    private val viewModel:StoreDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStoreDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeStoreDeleteState()

        intent.getParcelableExtra<Store>(MainActivity.STORE_KEY)?.let {

            with(it){
                binding.ivStore.load(image)
                binding.tvFloor.text = floor
                binding.tvTitle.text = name
                binding.tvDescription.text = info
            }


        }?: kotlin.run {
            finish()
        }


        binding.ivDeleteStore.setOnClickListener {
            intent.getParcelableExtra<Store>(MainActivity.STORE_KEY)?.let {
                viewModel.deleteStore(it)
            }

        }





    }

    private fun observeStoreDeleteState() {
        lifecycleScope.launch{
            repeatOnLifecycle(Lifecycle.State.CREATED){
                viewModel.deleteStoreState.collect{
                    drawDeleteStoreState(it)
                }
            }
        }
    }

    private fun drawDeleteStoreState(storeDeleteState: StoreDeleteState) {
       when(storeDeleteState){
           is StoreDeleteState.Idle->{}
           is StoreDeleteState.Success->{
                val intent = Intent()
               intent.putExtra(MainActivity.STORE_KEY,storeDeleteState.deletedStore)
               intent.putExtra(STORE_INDEX_KEY, storeDeleteState.index)
               setResult(RESULT_OK, intent)
               finish()
           }
           is StoreDeleteState.Failed->{
                showToast("veri silinemedi, bir sorun olustu.")
                finish()
           }
       }
    }
}