package com.chndr.movieapp.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.chndr.movieapp.R
import com.chndr.movieapp.home.dashboard.DashboardFragment
import com.chndr.movieapp.home.setting.SettingFragment
import com.chndr.movieapp.home.tiket.TiketFragment
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentHome = DashboardFragment()
        val fragmentTiket = TiketFragment()
        val fragmentSetting = SettingFragment()

        setFragment(fragmentHome)

        iv_menu1.setOnClickListener {
            setFragment(fragmentHome)
            changeIcon(iv_menu1,R.drawable.ic_home_active)
            changeIcon(iv_menu2,R.drawable.ic_tiket)
            changeIcon(iv_menu3,R.drawable.ic_profile)
        }
        iv_menu2.setOnClickListener {
            setFragment(fragmentTiket)
            changeIcon(iv_menu1,R.drawable.ic_home)
            changeIcon(iv_menu2,R.drawable.ic_tiket_active)
            changeIcon(iv_menu3,R.drawable.ic_profile)
        }
        iv_menu3.setOnClickListener {
            setFragment(fragmentSetting)
            changeIcon(iv_menu1,R.drawable.ic_home)
            changeIcon(iv_menu2,R.drawable.ic_tiket)
            changeIcon(iv_menu3,R.drawable.ic_profile_active)
        }
    }

        private fun setFragment(fragment: Fragment){
            val fragmentManager = supportFragmentManager
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.layout_frame, fragment)
            fragmentTransaction.commit()
        }


        private fun changeIcon(imageView: ImageView, int: Int){
            imageView.setImageResource(int)
        }

}