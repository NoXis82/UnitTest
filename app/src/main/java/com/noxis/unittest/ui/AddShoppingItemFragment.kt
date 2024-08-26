package com.noxis.unittest.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.RequestManager
import com.google.android.material.textfield.TextInputEditText
import com.noxis.unittest.R
import com.noxis.unittest.other.Status
import javax.inject.Inject


class AddShoppingItemFragment @Inject constructor(
    val glide: RequestManager
) : Fragment(R.layout.fragment_add_shopping_item) {

    lateinit var viewModel: ShoppingViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]
        subscribeToObserver(view)

        view.findViewById<Button>(R.id.btnAddShoppingItem).setOnClickListener {
            viewModel.insertShoppingItem(
                view.findViewById<TextInputEditText>(R.id.etShoppingItemName).text.toString(),
                view.findViewById<TextInputEditText>(R.id.etShoppingItemAmount).text.toString(),
                view.findViewById<TextInputEditText>(R.id.etShoppingItemPrice).text.toString(),
            )
        }


        view.findViewById<ImageView>(R.id.ivShoppingImage).setOnClickListener {
            findNavController().navigate(
                AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment()
            )
        }

        val callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                viewModel.setCurImageUrl("")
                findNavController().popBackStack()
            }
        }

        requireActivity().onBackPressedDispatcher.addCallback(callback)
    }

    private fun subscribeToObserver(view: View) {
        viewModel.curImageUrl.observe(viewLifecycleOwner, Observer {
            glide.load(it).into(view.findViewById(R.id.ivShoppingImage))
        })

        viewModel.insertShoppingItemStatus.observe(viewLifecycleOwner, Observer {
            it.getContentIfNotHandled()?.let { result ->
                when (result.status) {
                    Status.SUCCESS -> {
                        Log.d("TAG", "subscribeToObserver: SUCCESS")
                    }

                    Status.ERROR -> {
                        Log.d("TAG", "subscribeToObserver: ERROR")
                    }

                    Status.LOADING -> {
                        Log.d("TAG", "subscribeToObserver: LOADING")
                    }
                }
            }
        })
    }
}