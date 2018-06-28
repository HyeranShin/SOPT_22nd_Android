package sopt.hyeran.a6thseminar

import android.content.Context

// 어느 액티비티에서든지 불러내서 사용할 수 있는 클래스
// object 클래스는 별도의 선언없이 바로 접근해서 사용 가능

object SharedPreferenceController {
    private val USER = "user"
    private val ID = "id"
    private val PWD = "pwd"

    // SharedPreference에 저장
    fun setID(context : Context, id : String) {
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(ID, id)
        editor.commit()
    }

    // SharedPreference에서 가져오기
    fun getID(context : Context) : String? {
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        return pref.getString(ID, "")
    }

    // SharedPreference에 저장
    fun setPWD(context : Context, pwd : String) {
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(PWD, pwd)
        editor.commit()
    }

    // SharedPreference에서 가져오기
    fun getPWD(context : Context) : String? {
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        return pref.getString(PWD, "")
    }

    fun clearSPC(context : Context) {
        val pref = context.getSharedPreferences(USER, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.clear()
        editor.commit()
    }
}