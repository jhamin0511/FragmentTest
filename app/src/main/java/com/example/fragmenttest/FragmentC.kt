package com.example.fragmenttest

import android.app.Activity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.fragment_a.finish
import kotlinx.android.synthetic.main.fragment_b.*
import kotlinx.android.synthetic.main.fragment_c.*

private const val requestCodeD = 3000

class FragmentC : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_c, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        replace_c_to_b.setOnClickListener {
//            replaceFragment(FragmentB(), FragmentB::class.java.simpleName)
//        }

        popback_c_to_b.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("MESSAGE", "POPBACK C TO B")
            setResult(Activity.RESULT_OK, bundle)
            finish()
        }

        popback_c_to_a.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("MESSAGE", "POPBACK C TO A")
            finish(FragmentA::class.java.simpleName, bundle)
        }


        finish.setOnClickListener {
            requireActivity().finish()
        }
    }

}
