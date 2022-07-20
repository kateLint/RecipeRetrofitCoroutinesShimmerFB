package recipes.example.reciperetrofitcoroutinesshimmerfb.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import recipes.example.reciperetrofitcoroutinesshimmerfb.databinding.ItemRvMainCategoryBinding
import recipes.example.reciperetrofitcoroutinesshimmerfb.entities.CategoryItems


class MainCategoryAdapter: RecyclerView.Adapter<MainCategoryAdapter.RecipeViewHolder>() {

    var listener: OnItemClickListener? = null
    var ctx: Context? = null
    var arrMainCategory = ArrayList<CategoryItems>()

    inner class RecipeViewHolder(view: View): RecyclerView.ViewHolder(view){

        private val binding = ItemRvMainCategoryBinding.bind(view)

        fun bindRecipe(recipies: CategoryItems){
            with(binding){
                tvDishName.text =  recipies.strcategory
                Glide.with(ctx!!).load(recipies.strcategorythumb).into(imgDish)
                binding.root.setOnClickListener {
                    listener?.onClicked(recipies.strcategory)
                }
            }
        }
    }

    fun setData(arrData : List<CategoryItems>){
        arrMainCategory = arrData as ArrayList<CategoryItems>
    }


    fun setClickListener(listener1: OnItemClickListener){
        listener = listener1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        ctx = parent.context
        val viewBinding = ItemRvMainCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return RecipeViewHolder(viewBinding.root)
    }

    override fun getItemCount(): Int {
        return arrMainCategory.size
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        holder.bindRecipe(arrMainCategory[position])
    }

    interface OnItemClickListener{
        fun onClicked(categoryName:String)
    }
}