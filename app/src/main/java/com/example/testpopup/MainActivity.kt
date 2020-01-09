package com.example.testpopup

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.PopupMenu
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import com.androidnetworking.AndroidNetworking
import com.androidnetworking.common.Priority
import com.androidnetworking.error.ANError
import com.androidnetworking.interfaces.ParsedRequestListener
import com.example.testpopup.Adapter.PhotoAdapter
import com.example.testpopup.Adapter.ViewPagerAdapter
import com.example.testpopup.model.Photo
import com.example.testpopup.model.PhotoRespone
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList

@Suppress("NAME_SHADOWING")
class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    var photoList = ArrayList<Photo>()
    //lateinit var photoAdapter: PhotoAdapter
    lateinit var photoAdapter: ViewPagerAdapter
    private lateinit var viewPager: ViewPager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager = findViewById(R.id.viewPager)
        val toolbar = findViewById<Toolbar>(R.id.toolBar)
        setSupportActionBar(toolbar)
        toolbar?.title = "Photos"
        toolbar?.subtitle = "Sub"
        toolbar?.navigationIcon = ContextCompat.getDrawable(this, R.drawable.ic_menu_black_24dp)
        toolbar?.setNavigationOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.menu_open_website -> {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://resocoder.com"))
                        startActivity(intent)
                        true
                    }
                    R.id.menu_show_toast -> {
                        Toast.makeText(this, "Showing Toast!", Toast.LENGTH_LONG).show()
                        true
                    }
                    else -> false
                }
            }
            popupMenu.inflate(R.menu.menu_list)
            try {
                val fieldMPopup = PopupMenu::class.java.getDeclaredField("mPopup")
                fieldMPopup.isAccessible = true
                val mPopup = fieldMPopup.get(popupMenu)
                mPopup.javaClass
                    .getDeclaredMethod("setForceShowIcon", Boolean::class.java)
                    .invoke(mPopup, true)
            } catch (e: Exception) {
                Log.e("Main", "Error showing menu icons.", e)
            } finally {
                popupMenu.show()
            }
        }
            photoList = ArrayList()
            loadLink()
             photoAdapter = ViewPagerAdapter(supportFragmentManager,photoList)
//            rv_list.adapter = photoAdapter
//            rv_list.layoutManager= LinearLayoutManager(this,
//                LinearLayoutManager.VERTICAL,false)
        viewPager.adapter = photoAdapter

        }

   private fun loadLink() {
        AndroidNetworking.get("http://www.splashbase.co/api/v1/images/search?query=l")
            .setTag("test")
            .setPriority(Priority.HIGH)
            .build()
            .getAsObject(PhotoRespone::class.java, object : ParsedRequestListener<PhotoRespone>{
                override fun onResponse(response: PhotoRespone?) {
                    val photos : List<Photo> = response!!.getList
                    photoList.addAll(photos)
                    photoAdapter.notifyDataSetChanged()
//
                    loadingdata_progress.visibility = View.GONE
                }
                override fun onError(anError: ANError?) {
                }


            })
    }

}
