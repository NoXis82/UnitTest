package com.noxis.unittest.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentFactory
import com.bumptech.glide.RequestManager
import com.noxis.unittest.adapters.ImageAdapter
import com.noxis.unittest.adapters.ShoppingAdapter
import com.noxis.unittest.repositories.FakeShoppingRepositoryAndroidTest
import javax.inject.Inject

class TestShoppingFragmentFactory @Inject constructor(
    private val imageAdapter: ImageAdapter,
    private val glide: RequestManager,
    private val shoppingAdapter: ShoppingAdapter
) : FragmentFactory() {

    override fun instantiate(classLoader: ClassLoader, className: String): Fragment {
        return when (className) {
            ImagePickFragment::class.java.name -> ImagePickFragment(imageAdapter)
            AddShoppingItemFragment::class.java.name -> AddShoppingItemFragment(glide)
            ShoppingFragment::class.java.name -> ShoppingFragment(
                shoppingAdapter,
                ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
            )

            else -> super.instantiate(classLoader, className)
        }
    }
}