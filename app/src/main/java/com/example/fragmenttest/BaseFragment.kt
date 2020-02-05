package com.example.fragmenttest

import android.app.Activity.RESULT_CANCELED
import android.content.Intent
import android.os.Bundle
import androidx.annotation.GuardedBy
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    private fun getMainActivity() = requireActivity() as MainActivity

    fun addFragment(fragment: Fragment, tag: String) {
        getMainActivity().addFragment(fragment, tag)
    }

    fun replaceFragment(fragment: Fragment, tag: String) {
        getMainActivity().replaceFragment(fragment, tag)
    }

    fun backStack(tag: String, flags: Int = 0) {
        getMainActivity().back(tag, flags)
    }

    private fun getBaseActivity(): MainActivity {
        return requireActivity() as MainActivity
    }

    protected fun startFragment(fragment: BaseFragment) {
        getBaseActivity().startFragment(fragment)
    }

    protected fun startFragmentForResult(
        fragment: BaseFragment,
        target: Fragment,
        requestCode: Int
    ) {
        getBaseActivity().startFragmentForResult(fragment, target, requestCode)
    }

    protected fun setFragmentResult(resultCode: Int, data: Intent) {
        val targetFragment = targetFragment
        if (targetFragment is BaseFragment) {
            val requestCode = targetRequestCode

            targetFragment.onActivityResult(requestCode, resultCode, data)
        }
    }

    protected fun popBackFragment() {
        getBaseActivity().popBackFragment()
    }

    protected fun popBackFragment(tag: String, flags: Int) {
        getBaseActivity().popBackFragment(tag, flags)
    }

    fun finish() {
        val targetFragment = targetFragment
        if (targetFragment is BaseFragment) {
            val requestCode = targetRequestCode

            targetFragment.onFragmentResult(requestCode, mResultCode, mResultData)
        }
        getBaseActivity().popBackFragment()
    }

    fun finish(tag: String) {
        val targetFragment = getBaseActivity().getFragment(tag)

        if (targetFragment is BaseFragment) {
            val requestCode = targetRequestCode

            targetFragment.onFragmentResult(requestCode, mResultCode, mResultData)
        }
        getBaseActivity().popBackFragment(tag, 0)
    }

    @GuardedBy("this")
    internal var mResultCode = RESULT_CANCELED
    @GuardedBy("this")
    internal var mResultData: Bundle? = null

    protected fun setResult(resultCode: Int, data: Bundle) {
        synchronized(this) {
            mResultCode = resultCode
            mResultData = data
        }
    }

    open fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {

    }

}
