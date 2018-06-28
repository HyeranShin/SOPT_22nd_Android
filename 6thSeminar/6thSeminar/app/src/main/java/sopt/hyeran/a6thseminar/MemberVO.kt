package sopt.hyeran.a6thseminar

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

// VO: Value Object
open class MemberVO : RealmObject(){
    @PrimaryKey
    var id : String = ""
    var pwd : String = ""
}