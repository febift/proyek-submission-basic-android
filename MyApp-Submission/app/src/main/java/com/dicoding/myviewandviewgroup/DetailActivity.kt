package com.dicoding.myviewandviewgroup

import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.myviewandviewgroup.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    companion object {
        const val EXTRA_ARTIST = "extra_artist"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val artist = if (Build.VERSION.SDK_INT >= 33) {
            intent.getParcelableExtra<Artist>(EXTRA_ARTIST, Artist::class.java)
        } else {
            @Suppress("DEPRECATION")
            intent.getParcelableExtra<Artist>(EXTRA_ARTIST)
        }

        if (artist != null) {
            binding.tvItemName.text = artist.name
            binding.imgProfile.setImageResource(artist.photo)
            binding.tvItemDescription.text = artist.description
        }
    }
}