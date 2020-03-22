package com.nikhilpanju.fabfilter.main

import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.crystal.crystalrangeseekbar.widgets.CrystalSeekbar
import com.google.android.material.appbar.AppBarLayout
import com.nikhilpanju.fabfilter.R
import com.nikhilpanju.fabfilter.filter.FiltersLayout
import com.nikhilpanju.fabfilter.utils.bindView


var animationPlaybackSpeed: Double = 0.8

/**
 * https://dribbble.com/shots/2940944--5-Filters
 */
class MainActivity : AppCompatActivity() {

    private val recyclerView: RecyclerView by bindView(R.id.ui_fabfilter_recycler_view)
    private val appbar: AppBarLayout by bindView(R.id.ui_fabfilter_appbar)
    private val drawerIcon: View by bindView(R.id.ui_fabfilter_drawer_icon)

    // layout/nav_drawer views
    private val drawerLayout: DrawerLayout by bindView(R.id.ui_fabfilter_drawer_layout)
    private val animationSpeedSeekbar: CrystalSeekbar by bindView(R.id.ui_fabfilter_animation_speed_seekbar)
    private val animationSpeedText: TextView by bindView(R.id.ui_fabfilter_animation_speed_text)
    private val githubCodeLink: TextView by bindView(R.id.ui_fabfilter_github_code_link)
    private val githubMeLink: TextView by bindView(R.id.ui_fabfilter_github_me_link)


    private lateinit var mainListAdapter: MainListAdapter

    /**
     * Used by FiltersLayout since we don't want to expose mainListAdapter (why?)
     * (Option: Combine everything into one activity if & when necessary)
     */
    var isAdapterFiltered: Boolean
        get() = mainListAdapter.isFiltered
        set(value) {
            mainListAdapter.isFiltered = value
        }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.ui_fabfilter_activity_main)

        // Appbar behavior init
        (appbar.layoutParams as CoordinatorLayout.LayoutParams).behavior = ToolbarBehavior()

        // RecyclerView Init
        mainListAdapter = MainListAdapter(this)
        recyclerView.adapter = mainListAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.setHasFixedSize(true)
        updateRecyclerViewAnimDuration()

        // Nav Drawer Init
        animationSpeedSeekbar.setOnSeekbarChangeListener { value ->
            animationPlaybackSpeed = value as Double
            animationSpeedText.text = "${"%.1f".format(animationPlaybackSpeed)}x"
            updateRecyclerViewAnimDuration()
        }
        drawerIcon.setOnClickListener { drawerLayout.openDrawer(GravityCompat.START) }
        githubCodeLink.setOnClickListener { openBrowser(R.string.ui_fabfilter_github_link_code) }
        githubMeLink.setOnClickListener { openBrowser(R.string.ui_fabfilter_github_link_me) }
    }

    private fun updateRecyclerViewAnimDuration() {
        recyclerView.itemAnimator?.removeDuration = FiltersLayout.closeIconRotationDuration * 60 / 100
        recyclerView.itemAnimator?.addDuration = FiltersLayout.closeIconRotationDuration
    }

    private fun openBrowser(resId: Int) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(getString(resId))))
    }

    /**
     * Called from FiltersLayout to get adapter scale down animator
     */
    fun getAdapterScaleDownAnimator(isScaledDown: Boolean): ValueAnimator =
            mainListAdapter.getScaleDownAnimator(isScaledDown)
}