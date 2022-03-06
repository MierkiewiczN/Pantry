package mierkiewicz.natalia.pantry.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.google.android.material.bottomnavigation.BottomNavigationView
import mierkiewicz.natalia.pantry.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))

        val productListFragment: ProductListFragment = ProductListFragment.newInstance()

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, productListFragment)
            .commit()
        val bottomNavigation: BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.recipes_menu_item -> replaceFragment(ProductListFragment.newInstance())
                R.id.products_menu_item -> replaceFragment(ProductListFragment.newInstance())
                R.id.categories_menu_item -> replaceFragment(CategoryListFragment())
            }
            true
        }

    }

    private fun replaceFragment(fragment: Fragment) = supportFragmentManager.commit {
        setReorderingAllowed(true)
        replace(R.id.frameLayout, fragment)
    }

}