package com.example.recyclerview

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.recyclerview.databinding.ActivityMainBinding
import com.example.recyclerview.model.Person
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val recyclerView = binding.recyclerview
        recyclerView.layoutManager = LinearLayoutManager(this)

        val listOfPerson = mutableListOf<Person>()
        listOfPerson.add(Person(name = "Shyngys", age = 21))
        listOfPerson.add(Person(imageRes = R.drawable.cat, name = "Valera", age = 23))
        listOfPerson.add(Person(name = "Dimash", age = 16))
        listOfPerson.add(Person(name = "AAA", age = 21))
        listOfPerson.add(Person(name = "kljklj", age = 21))

        val userClickListener = object : AdapterPerson.UserClickListener {
            override fun userClick() {
                Toast.makeText(this@MainActivity, "Item was clicked", Toast.LENGTH_SHORT).show()
            }

            override fun onAvatarClick(view: View) {
                loadImageFromUrl(
                    "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe0O0260hzKyKursZUTtZAxECP0gSVJ2JXwQ&usqp=CAU",
                    view as CircleImageView
                )
            }

        }

        val adapter = AdapterPerson(this, listOfPerson, userClickListener)
        recyclerView.adapter = adapter
    }

    fun loadImageFromUrl(url: String, view: CircleImageView) {
        Glide
            .with(this)
            .load(url)
            .centerCrop()
            .into(view)
    }
}

