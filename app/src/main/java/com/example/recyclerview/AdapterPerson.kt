package com.example.recyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.recyclerview.databinding.AdapterRvBinding
import com.example.recyclerview.model.Person

class AdapterPerson(
    val context: Context,
    val listOfPerson: List<Person>,
    val userClickListener: UserClickListener
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private lateinit var binding: AdapterRvBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(context)

        binding = AdapterRvBinding.inflate(layoutInflater, parent, false)

        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val viewHolder = holder as MyViewHolder
        viewHolder.initContent(listOfPerson[position])
    }

    override fun getItemCount(): Int = listOfPerson.size

    inner class MyViewHolder(var binding: AdapterRvBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun initContent(person: Person) {
            if (person.imageRes != null) {
                binding.avatar.setImageResource(person.imageRes!!)
            }
            binding.tvTitle.text = person.name
            binding.tvSubtitle.text = person.age.toString()
            binding.root.setOnClickListener {
                userClickListener.userClick()
            }

            binding.apply {
                avatar.setOnClickListener {
                    Glide
                        .with(context)
                        .load("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRe0O0260hzKyKursZUTtZAxECP0gSVJ2JXwQ&usqp=CAU")
                        .into(this.avatar)
                }
            }
        }
    }

    interface UserClickListener {
        fun userClick()

        fun onAvatarClick(view: View)
    }
}