package com.example.fragmenttest

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_a.*

private const val requestCodeA = 1000

class FragmentA : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_a, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replace_a_to_b.setOnClickListener {
            startFragmentForResult(FragmentB(), this, requestCodeA)
        }

        finish.setOnClickListener {
            requireActivity().finish()
        }

    }

    override fun onFragmentResult(requestCode: Int, resultCode: Int, data: Bundle?) {
        super.onFragmentResult(requestCode, resultCode, data)

        if (resultCode == RESULT_OK) {
            resultRequest(requestCode, data)
        }
    }

    private fun resultRequest(requestCode: Int, data: Bundle?) {
        when (requestCode) {
            requestCodeA -> {
                data?.let {
                    resultCodeA(data)
                }
            }
        }
    }

    private fun resultCodeA(data: Bundle) {
        Toast.makeText(requireContext(), data.getString("MESSAGE"), Toast.LENGTH_SHORT).show()
    }

    override fun onBackFragment(data: Bundle?) {
        super.onBackFragment(data)

        data?.let {
            Toast.makeText(requireContext(), data.getString("MESSAGE"), Toast.LENGTH_SHORT).show()
        }
    }
}
