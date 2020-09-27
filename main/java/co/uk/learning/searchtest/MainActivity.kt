package co.uk.learning.searchtest

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import co.uk.learning.searchtest.exploreFragmentBuild.ExploreFragment


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val sV = searchItem?.actionView as SearchView


        sV.setSearchableInfo(manager.getSearchableInfo(componentName))

        sV.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            override fun onQueryTextSubmit(query: String?): Boolean {
                sV.clearFocus()
                sV.setQuery("", false)
                searchItem.collapseActionView()
                Toast.makeText(this@MainActivity, "Searching For $query", Toast.LENGTH_LONG).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                //TODO("Not yet implemented")
                val intent = Intent("STRING_ID_FOR_BRODCAST")

                intent.putExtra("searching", newText)
                sendBroadcast(intent)

                return false
            }


        })

        return true


    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.search -> supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, ExploreFragment())
                .commit()
        }

        return super.onOptionsItemSelected(item)

    }
}