package com.sopt.hyera.a4thseminar.get

data class GetBoardResponse (
    var message : String,
    val data : ArrayList<GetBoardResponseData>
)