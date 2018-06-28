package sopt.hyeran.a6thseminar

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    lateinit var memberRealm : Realm

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()

        login_check_btn.setOnClickListener {
            val id = login_id_edit.text.toString()
            val pwd = login_pw_edit.text.toString()

            if(getMemberList(id).isEmpty()){
                Toast.makeText(this, "존재하는 아이디가 없습니다.", Toast.LENGTH_SHORT).show()
            }else {
                // 자동로그인이 체크될 경우 SharedPreference 작동
                if(login_auto_check.isChecked) {
                    SharedPreferenceController.setID(this, id)
                    SharedPreferenceController.setPWD(this, pwd)
                }
                // 데이터를 찾고 로그인을 정상적으로 완료
                val intent = Intent(this, MainActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
                finish()
            }
        }

        login_sign_btn.setOnClickListener{
            startActivity(Intent(this, SignActivity::class.java))
        }
    }

    // Realm 초기화
    fun init() {
        Realm.init(this)
        memberRealm = Realm.getDefaultInstance()
    }

    // 데이터 가져오기(조회)
    fun getMemberList(id : String) : RealmResults<MemberVO> {
        return memberRealm.where(MemberVO::class.java).equalTo("id", id).findAll()
    }

}
