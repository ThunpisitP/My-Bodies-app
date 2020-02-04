package thunpisit.example.mybodies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

class HomeFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?, SavedInstanceState: Bundle?): View? {
        val view : View = inflater.inflate(R.layout.fragment_home,container,false)
        return view
    }


}