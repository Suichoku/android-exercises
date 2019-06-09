package com.example.roomshoppinglist

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import kotlinx.android.synthetic.main.recyclerview_item.view.*

class ShoppingListAdapter (var shoppingList: MutableList<ShoppingListItem>): RecyclerView.Adapter<ShoppingListAdapter.ViewHolder>(){

    // Create UI View Holder from XML layout (is called always when a new shopping list item will be displayed)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.recyclerview_item, parent, false)
        return ViewHolder(view)
    }

    // return shopping list size
    override fun getItemCount(): Int = shoppingList.size

    // bind data to UI View Holder (holder = inner class ViewHolder)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // item to bind UI
        val item: ShoppingListItem = shoppingList[position] // get item at current position
        // update holder's data with item data
        holder.nameTextView.text = item.name
        holder.countTextView.text = item.count.toString()
        holder.priceTextView.text = item.price.toString()
    }

    // View Holder class to hold UI views (recyclerview_item.xml)
    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val nameTextView: TextView = view.nameTextView
        val countTextView: TextView = view.countTextView
        val priceTextView: TextView = view.priceTextView
    }

    // Updates data in adapter
    fun update(newList: MutableList<ShoppingListItem>) {
        shoppingList = newList
        notifyDataSetChanged()
    }
}