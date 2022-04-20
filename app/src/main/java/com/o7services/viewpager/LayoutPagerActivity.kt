package com.o7services.viewpager

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback

class LayoutPagerActivity : AppCompatActivity() {
    lateinit var viewPager : ViewPager2
    lateinit var layoutViewPagerAdapter: ViewsSliderAdapter
    var layoutArray = arrayOf(R.layout.layout1,R.layout.layout2, R.layout.layout3)
    private  val TAG = "LayoutPagerActivity"
    lateinit var btnPrev : Button
    lateinit var btnNext : Button
    lateinit var recyclerView: RecyclerView
    lateinit var dotsRecycler : DotsRecycler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layout_pager)
        viewPager = findViewById(R.id.viewPager)
        btnPrev = findViewById(R.id.btnPrev)
        btnNext = findViewById(R.id.btnNext)
        recyclerView = findViewById(R.id.rvDots)
        dotsRecycler = DotsRecycler(this,layoutArray.size, 0)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = dotsRecycler

        layoutViewPagerAdapter = ViewsSliderAdapter(layoutArray)
        viewPager.setAdapter(layoutViewPagerAdapter)

        viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                Log.e(TAG, position.toString())
                if(position == 0){
                    btnPrev.visibility = View.GONE
                }else{
                    btnPrev.visibility = View.VISIBLE
                }
                if(position == (layoutArray.size-1)){
                    btnNext.visibility = View.GONE
                }else{
                    btnNext.visibility = View.VISIBLE
                }

                dotsRecycler.updatePosition(position)
            }
        })

        btnPrev.setOnClickListener {
            if(viewPager.currentItem < layoutArray.size && viewPager.currentItem != 0){
                viewPager.currentItem = viewPager.currentItem -1
            }
        }

        btnNext.setOnClickListener {
            if(viewPager.currentItem < layoutArray.size){
                viewPager.currentItem = viewPager.currentItem +1
            }
        }
    }
}
class ViewsSliderAdapter(var layouts: Array<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(viewType, parent, false)
        return SliderViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {}
    override fun getItemViewType(position: Int): Int {
        return layouts[position]
    }

    override fun getItemCount(): Int {
        return layouts.size
    }

    inner class SliderViewHolder(view: View?) : RecyclerView.ViewHolder(view!!) {

    }
}