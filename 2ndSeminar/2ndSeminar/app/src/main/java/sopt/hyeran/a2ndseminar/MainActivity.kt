package sopt.hyeran.a2ndseminar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onClick(v: View?) {
        when(v){
            main_btn_home -> {
                clearSelected()
                main_btn_home.isSelected = true
                replaceFragment(HomeTab())
            }
            main_btn_mine -> {
                clearSelected()
                main_btn_mine.isSelected = true
                replaceFragment(MineTab())
            }
            main_btn_add -> {
                // intent: 액티비티 등의 전환이 일어날 때 호출이나 메세지를 전달하는 매개체
                val intent = Intent(applicationContext, AddActivity::class.java)
                intent.putExtra("add_image", R.drawable.add_image)  // 인텐트로 데이터 전달
                startActivity(intent)   // 전환될 액티비티로 넘어갈때
            }
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addFragment(HomeTab())
        main_btn_home.isSelected = true
        main_btn_home.setOnClickListener(this)
        main_btn_mine.setOnClickListener(this)
        main_btn_add.setOnClickListener(this)
    }

    // fragment: Activity를 구성하는 작은 모듈
    fun addFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        // transaction: Activity에 행하는 변화들
        val transaction = fm.beginTransaction()
        transaction.add(R.id.main_frame, fragment)
        transaction.commit()    // 동시에 실행
    }

    fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val transaction = fm.beginTransaction()
        transaction.replace(R.id.main_frame, fragment)
        transaction.addToBackStack(null)    // 이전 상태를 백스택에 추가하여 사용자가 백버튼을 눌렀을때에 대한 호환성 추가
        transaction.commit()
    }

    fun clearSelected(){
        main_btn_home.isSelected = false
        main_btn_mine.isSelected = false
    }
}
