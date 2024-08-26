package com.noxis.unittest.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textview.MaterialTextView
import com.noxis.unittest.R
import com.noxis.unittest.adapters.ShoppingAdapter
import javax.inject.Inject


class ShoppingFragment @Inject constructor(
    val shoppingAdapter: ShoppingAdapter,
    var viewModel: ShoppingViewModel? = null
) : Fragment(R.layout.fragment_shopping) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = viewModel ?: ViewModelProvider(requireActivity())[ShoppingViewModel::class.java]
        subscribeToObservers(view)
        setupRecyclerView(view)

        view.findViewById<FloatingActionButton>(R.id.fabAddShoppingItem).setOnClickListener {
            findNavController().navigate(
                ShoppingFragmentDirections.actionShoppingFragmentToAddShoppingItemFragment()
            )
        }
    }

    private val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(
        0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
    ) {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean = true

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            val pos = viewHolder.layoutPosition
            val item = shoppingAdapter.shoppingItems[pos]
            viewModel?.deleteShoppingItem(item)
//            Snackbar.make(requireView(), "Successfully deleted item", Snackbar.LENGTH_LONG).apply {
//                setAction("Undo") {
//                    viewModel?.insertShoppingItemIntoDb(item)
//                }
//                show()
//            }

        }

    }

    private fun subscribeToObservers(view: View) {
        viewModel?.shoppingItems?.observe(viewLifecycleOwner, Observer {
            shoppingAdapter.shoppingItems = it
        })
        viewModel?.totalPrice?.observe(viewLifecycleOwner, Observer {
            val price = it ?: 0f
            val priceText = "Total Price: $price€"
            view.findViewById<MaterialTextView>(R.id.tvShoppingItemPrice).text = priceText
        })
    }

    private fun setupRecyclerView(view: View) {
        view.findViewById<RecyclerView>(R.id.rvShoppingItems).apply {
            adapter = shoppingAdapter
            layoutManager = LinearLayoutManager(requireContext())
            ItemTouchHelper(itemTouchCallback).attachToRecyclerView(this)
        }
    }
}
