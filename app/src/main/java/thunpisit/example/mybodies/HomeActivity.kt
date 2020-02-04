package thunpisit.example.mybodies

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val fragmentStart = HomeFragment()
        loadFragment(fragmentStart)

        val bottomNav : BottomNavigationView = findViewById(R.id.bottom_navigation)
        bottomNav.setItemIconTintList(null)
        bottomNav.setOnNavigationItemSelectedListener(BottomNavigationView.OnNavigationItemSelectedListener {
            when(it.itemId){
                R.id.nav_home -> {
                    val fragmentHome = HomeFragment()
                    loadFragment(fragmentHome)
                }
                R.id.nav_bmi_bmr -> {
                    val fragmentBmiBmr = BmiBmrFragment()
                    loadFragment(fragmentBmiBmr)
                }
                R.id.nav_body_fat -> {
                    val fragmentBodyFat = BodyFatFragment()
                    loadFragment(fragmentBodyFat)
                }
                R.id.nav_history -> {
                    val fragmentHistory = HistoryFragment()
                    //it.setIcon(R.drawable.history_pressed)
                    loadFragment(fragmentHistory)
                }
            }
            return@OnNavigationItemSelectedListener true
        })
    }

    override fun onBackPressed() {
        //super.onBackPressed()
    }

    private fun loadFragment(fragment: Fragment){
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragment_container,fragment)
        fragmentTransaction.commit()
    }
}
