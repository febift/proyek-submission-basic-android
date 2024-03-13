package com.dicoding.myviewandviewgroup

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.myviewandviewgroup.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val list = ArrayList<Artist>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvArtists.setHasFixedSize(true)

        list.addAll(getListArtist())
        showRecyclerList()
    }

    private fun getListArtist(): ArrayList<Artist> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDescription = resources.getStringArray(R.array.data_description)
        val dataPhoto = resources.obtainTypedArray(R.array.data_photo)
        val listArtist = ArrayList<Artist>()

        for (i in dataName.indices) {
            val artist = Artist(dataName[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
            listArtist.add(artist)
        }

        return listArtist
    }

    private fun showRecyclerList() {
        binding.rvArtists.layoutManager = LinearLayoutManager(this)
        val listArtistAdapter = ListArtistAdapter(list)
        binding.rvArtists.adapter = listArtistAdapter

        listArtistAdapter.setOnItemClickCallback(object : ListArtistAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Artist) {
                val moveIntent = Intent(this@MainActivity, DetailActivity::class.java)
                moveIntent.putExtra(DetailActivity.EXTRA_ARTIST, data)
                startActivity(moveIntent)
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.profile_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_profile -> {
                val moveIntent = Intent(this@MainActivity, ProfileActivity::class.java)
                startActivity(moveIntent)
            }
            else -> return  super.onOptionsItemSelected(item)
        }
        return true
    }
}