package com.example.testpopup


import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.ImageView
import android.widget.ProgressBar
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_image.*

/**
 * A simple [Fragment] subclass.
 */
class ImageFragment : Fragment() {
    companion object {
        fun newInstance(url: String): ImageFragment {

            val f = ImageFragment()

            val bdl = Bundle(1)

            bdl.putString("link", url)

            f.arguments = bdl

            return f

        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View? = inflater.inflate(R.layout.fragment_image, container, false)
        // Inflate the layout for this fragment
        val url = arguments!!.getString("link")
        val imgView : ImageView = view!!.findViewById(R.id.imgViewFrag)
        val progressBar :ProgressBar = view!!.findViewById(R.id.loadingdata_progress)
        Picasso.get().load(url).into(imgView)
        progressBar.max=100

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        imgViewFrag.setOnClickListener {
            this.hideSystemUI()

            val intent = Intent(activity,FullSize::class.java)
//            intent.putExtra("link",url)
//            activity?.startActivity(intent)
        }
        imgViewFrag.setOnLongClickListener {
            this.showSystemUI()
            return@setOnLongClickListener true
        }
    }
    private fun hideSystemUI() {
        activity?.window?.decorView?.systemUiVisibility   = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_FULLSCREEN
                or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
    }

    private fun showSystemUI() {
       activity?.window?.decorView?.systemUiVisibility  = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }
}
