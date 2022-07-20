package com.example.reciperetrofitcoroutinesshimmerfb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.codingwithme.recipeapp.entities.MealsItems
import com.example.reciperetrofitcoroutinesshimmerfb.databinding.ItemRvSubCategoryBinding


class SubCategoryAdapter: RecyclerView.Adapter<SubCategoryAdapter.RecipeSubViewHolder>() {

    var listener: OnItemClickListener? = null
    var ctx :Context? = null
    var arrSubCategory = ArrayList<MealsItems>()


    inner class RecipeSubViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = ItemRvSubCategoryBinding.bind(view)

        fun bindMeal(mealsItems: MealsItems){
            with(binding){
            tvDishName.text = mealsItems.strMeal

            Glide.with(ctx!!).load(mealsItems.strMealThumb).into(binding.imgDish)

                binding.root.setOnClickListener {
                listener?.onClicked(mealsItems.idMeal)
            }
                }}
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeSubViewHolder {
        ctx = parent.context
        val viewBinding = ItemRvSubCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return RecipeSubViewHolder(viewBinding.root)
    }

    override fun onBindViewHolder(holder: RecipeSubViewHolder, position: Int) {
        holder.bindMeal(arrSubCategory.get(position))
    }

    override fun getItemCount(): Int {
        return arrSubCategory.size
    }

    fun setData(arrData : List<MealsItems>){
        arrSubCategory = arrData as ArrayList<MealsItems>
    }

    fun setClickListener(listener1: OnItemClickListener){
        listener = listener1
    }


    interface OnItemClickListener{
        fun onClicked(id:String)
    }
}


