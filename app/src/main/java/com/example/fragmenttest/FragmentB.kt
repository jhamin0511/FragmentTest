package com.example.fragmenttest

import android.app.Activity.RESULT_OK
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_a.finish
import kotlinx.android.synthetic.main.fragment_b.*

private const val requestCodeB = 2000

class FragmentB : BaseFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_b, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        replace_b_to_c.setOnClickListener {
            startFragmentForResult(FragmentC(), this, requestCodeB)
        }

        popback_b_to_a.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("MESSAGE", "POPBACK B TO A")
            setResult(RESULT_OK, bundle)
            finish()
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
            requestCodeB -> {
                data?.let {
                    resultCodeA(data)
                }
            }
        }
    }

    private fun resultCodeA(data: Bundle) {
        Toast.makeText(requireContext(), data.getString("MESSAGE"), Toast.LENGTH_SHORT).show()
    }

}
