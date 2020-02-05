package com.example.fragmenttest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        replaceFragment(FragmentA(), FragmentA::class.java.simpleName)
    }

    fun addFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    fun back(tag: String, flags: Int = 0) {
        supportFragmentManager.popBackStack(tag, flags)
    }

    fun startFragment(fragment: BaseFragment) {
        val tag = fragment.javaClass.simpleName

        supportFragmentManager.beginTransaction()
            .replace(R.id.container, fragment, tag)
            .addToBackStack(tag)
            .commit()
    }

    fun startFragmentForResult(
        fragment: BaseFragment,
        target: Fragment,
        requestCode: Int
    ) {
        fragment.setTargetFragment(target, requestCode)
        startFragment(fragment)
    }

    fun popBackFragment() {
        supportFragmentManager.popBackStack()
    }

    fun popBackFragment(tag: String, flags: Int) {
        supportFragmentManager.popBackStack(tag, flags)
    }

    fun getFragment(tag: String) =
        supportFragmentManager.findFragmentByTag(tag)

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount <= 1) {
            finish()
        } else {
            super.onBackPressed()
        }
    }

}
