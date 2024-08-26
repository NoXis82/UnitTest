package com.noxis.unittest.ui

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.filters.MediumTest
import com.google.common.truth.Truth.assertThat
import com.noxis.unittest.R
import com.noxis.unittest.data.local.ShoppingItem
import com.noxis.unittest.getOrAwaitValue
import com.noxis.unittest.launchFragmentInHiltContainer
import com.noxis.unittest.repositories.FakeShoppingRepositoryAndroidTest
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import javax.inject.Inject

@MediumTest
@HiltAndroidTest
@ExperimentalCoroutinesApi
class AddShoppingItemFragmentTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @get:Rule
    var hiltAndroidRule = HiltAndroidRule(this)

    @Inject
    lateinit var fragmentFactory: ShoppingFragmentFactory

    @Before
    fun setUp() {
        hiltAndroidRule.inject()
    }

    @Test
    fun clickInsertIntoDb_shoppingItemInsertedIntoDb() {
        val testViewmodel = ShoppingViewModel(FakeShoppingRepositoryAndroidTest())
        launchFragmentInHiltContainer<AddShoppingItemFragment>(fragmentFactory = fragmentFactory) {
            viewModel = testViewmodel
        }

        onView(withId(R.id.etShoppingItemName)).perform(replaceText("shopping item"))
        onView(withId(R.id.etShoppingItemAmount)).perform(replaceText("5"))
        onView(withId(R.id.etShoppingItemPrice)).perform(replaceText("3.4"))

        onView(withId(R.id.btnAddShoppingItem)).perform(click())

        assertThat(testViewmodel.shoppingItems.getOrAwaitValue()).contains(
            ShoppingItem(
                name = "shopping item",
                amount = 5,
                price = 3.4f,
                imageUrl = ""
            )
        )
    }


    @Test
    fun clickAddShoppingItemButton_navigateToAddShoppingItemFragment() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<AddShoppingItemFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }
        onView(withId(R.id.ivShoppingImage)).perform(click())
        verify(navController).navigate(
            AddShoppingItemFragmentDirections.actionAddShoppingItemFragmentToImagePickFragment()
        )
    }

    @Test
    fun pressBackButton_popBackStack() {
        val navController = mock(NavController::class.java)
        launchFragmentInHiltContainer<AddShoppingItemFragment> {
            Navigation.setViewNavController(requireView(), navController)
        }

        pressBack()

        verify(navController).popBackStack()
    }

}