package mierkiewicz.natalia.pantry.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import mierkiewicz.natalia.pantry.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.my_toolbar))
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, ProductListFragment.newInstance())
            .commit()
    }

}