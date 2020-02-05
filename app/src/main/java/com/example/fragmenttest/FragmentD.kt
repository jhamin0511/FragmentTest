package com.example.fragmenttest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_a.*
import kotlinx.android.synthetic.main.fragment_a.finish
import kotlinx.android.synthetic.main.fragment_c.*
import kotlinx.android.synthetic.main.fragment_d.*

class FragmentD : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_d, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        add_d_to_a.setOnClickListener {
            addFragment(FragmentA(), FragmentA::class.java.simpleName)
        }

        add_d_to_b.setOnClickListener {
            addFragment(FragmentB(), FragmentB::class.java.simpleName)
        }

        add_d_to_c.setOnClickListener {
            addFragment(FragmentC(), FragmentC::class.java.simpleName)
        }

        replace_d_to_a.setOnClickListener {
            replaceFragment(FragmentA(), FragmentA::class.java.simpleName)
        }

        replace_d_to_b.setOnClickListener {
            replaceFragment(FragmentB(), FragmentB::class.java.simpleName)
        }

        replace_d_to_c.setOnClickListener {
            replaceFragment(FragmentC(), FragmentC::class.java.simpleName)
        }

        finish.setOnClickListener {
//            requireActivity().finish()
            backStack(FragmentA::class.java.simpleName, 0)
        }

    }

}
