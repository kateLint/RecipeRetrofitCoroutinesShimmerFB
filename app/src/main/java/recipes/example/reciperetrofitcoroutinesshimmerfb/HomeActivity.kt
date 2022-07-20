package recipes.example.reciperetrofitcoroutinesshimmerfb

import android.content.Intent
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager

import kotlinx.coroutines.launch
import recipes.example.reciperetrofitcoroutinesshimmerfb.adapter.MainCategoryAdapter
import recipes.example.reciperetrofitcoroutinesshimmerfb.adapter.SubCategoryAdapter
import recipes.example.reciperetrofitcoroutinesshimmerfb.database.RecipeDatabase
import recipes.example.reciperetrofitcoroutinesshimmerfb.databinding.ActivityHomeBinding
import recipes.example.reciperetrofitcoroutinesshimmerfb.entities.CategoryItems
import recipes.example.reciperetrofitcoroutinesshimmerfb.entities.MealsItems

class HomeActivity : recipes.example.reciperetrofitcoroutinesshimmerfb.BaseActivity() {
    private lateinit var binding: ActivityHomeBinding
    var arrMainCategory = ArrayList<CategoryItems>()
    var arrSubCategory = ArrayList<MealsItems>()

    var mainCategoryAdapter = MainCategoryAdapter()
    var subCategoryAdapter = SubCategoryAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)


        getDataFromDb()
        mainCategoryAdapter.setClickListener(onCLicked)
        subCategoryAdapter.setClickListener(onCLickedSubItem)

    }

    private val onCLicked  = object : MainCategoryAdapter.OnItemClickListener{
        override fun onClicked(categoryName: String) {
            getMealDataFromDb(categoryName)
        }
    }

    private val onCLickedSubItem  = object : SubCategoryAdapter.OnItemClickListener{
        override fun onClicked(id: String) {
            val intent = Intent(this@HomeActivity,
                recipes.example.reciperetrofitcoroutinesshimmerfb.DetailActivity::class.java)
            intent.putExtra("id",id)
            startActivity(intent)
        }
    }

    private fun getDataFromDb(){
        launch {
            this.let {
                val cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getAllCategory()
                arrMainCategory = cat as ArrayList<CategoryItems>
                arrMainCategory.reverse()
                if(arrMainCategory.isNotEmpty()) {
                    getMealDataFromDb(arrMainCategory[0].strcategory)
                    mainCategoryAdapter.setData(arrMainCategory)
                }
                binding.rvMainCategory.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                binding.rvMainCategory.adapter = mainCategoryAdapter
            }


        }
    }

    private fun getMealDataFromDb(categoryName:String){
        binding.tvCategory.text = "$categoryName Category"
        launch {
            this.let {
                val cat = RecipeDatabase.getDatabase(this@HomeActivity).recipeDao().getSpecificMealList(categoryName)
                arrSubCategory = cat as ArrayList<MealsItems>
                subCategoryAdapter.setData(arrSubCategory)
                binding.rvSubCategory.layoutManager = LinearLayoutManager(this@HomeActivity,LinearLayoutManager.HORIZONTAL,false)
                binding.rvSubCategory.adapter = subCategoryAdapter
            }


        }
    }
}