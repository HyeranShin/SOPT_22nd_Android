package sopt.hyeran.a3rdseminar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile)

        profile_friend_image.setImageResource(intent.getIntExtra("profile",0))
        profile_friend_name.text = intent.getStringExtra("name")
    }
}
