package top.abner.album

import android.graphics.Color
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.ViewSwitcher
import kotlinx.android.synthetic.main.activity_main.*
import android.os.CountDownTimer
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.FrameLayout


class MainActivity : AppCompatActivity(), ViewSwitcher.ViewFactory {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE)
        getWindow().setFlags(
            WindowManager.LayoutParams. FLAG_FULLSCREEN,
            WindowManager.LayoutParams. FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)
        initView()
        initData()
    }

    fun initView() {
        imageSwitcher.setFactory(this);
        imageSwitcher.setImageResource(R.drawable.image)
        imageSwitcher.setInAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_in_left));
        imageSwitcher.setOutAnimation(AnimationUtils.loadAnimation(this, android.R.anim.slide_out_right));
    }

    var list = ArrayList<Drawable>()

    private fun initData() {
        list.add(getResources().getDrawable(R.drawable.image));
        list.add(getResources().getDrawable(R.drawable.image2));
        list.add(getResources().getDrawable(R.drawable.image3));

        timer.start()
    }

    override fun makeView(): View {
        var imageView = ImageView(this);
        imageView.setBackgroundColor(Color.WHITE);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
        return imageView;
    }

    private val TOTAL_TIME = (60 * 1000).toLong()
    private val COUNT_DOWN_INTERVAL = (5 * 1000).toLong()
    var sum = 3
    var count = 0;

    private val timer = object : CountDownTimer(TOTAL_TIME, COUNT_DOWN_INTERVAL) {
        override fun onTick(millisUntilFinished: Long) {
            count = count % sum
            imageSwitcher.setImageDrawable(list.get(count))
            count++
        }

        override fun onFinish() {
            this.start()
        }
    }
}
