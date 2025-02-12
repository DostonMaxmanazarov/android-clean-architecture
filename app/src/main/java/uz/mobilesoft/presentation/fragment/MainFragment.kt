package uz.mobilesoft.presentation.fragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import uz.mobilesoft.cleanarchitecture.R
import uz.mobilesoft.cleanarchitecture.databinding.FragmentMainBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        _binding = FragmentMainBinding.bind(view)
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}