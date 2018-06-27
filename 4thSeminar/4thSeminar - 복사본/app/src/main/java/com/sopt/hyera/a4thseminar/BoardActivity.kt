package com.sopt.hyera.a4thseminar

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.sopt.hyera.a4thseminar.ApplicationController
import com.sopt.hyera.a4thseminar.MainActivity
import com.sopt.hyera.a4thseminar.NetworkService
import com.sopt.hyera.a4thseminar.R
import com.sopt.hyera.a4thseminar.post.PostBoardResponse
import kotlinx.android.synthetic.main.activity_board.*
import okhttp3.Callback
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Response
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileNotFoundException
import java.io.InputStream

class BoardActivity : AppCompatActivity() {

    lateinit var networkService : NetworkService
    private val REQ_CODE_SELECT_IMAGE = 100
    lateinit var data : Uri
    private var image : MultipartBody.Part? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
        networkService = ApplicationController.instance.networkService
        write_image_btn.setOnClickListener {
            changeImage()
            //갤러리를 불러오는부분
        }
        write_post_btn.setOnClickListener {
            postBoard()
        }
    }

    fun changeImage(){
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = android.provider.MediaStore.Images.Media.CONTENT_TYPE
        intent.data = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
        startActivityForResult(intent, REQ_CODE_SELECT_IMAGE) //엑티비티를 켜서 응답값을 받아 작업ㅇ르 한다.(a에서 b엑티비티를 호출하고 b에서 생성된 값을 a가 받아온다)
    }

    fun postBoard(){
        val title = RequestBody.create(MediaType.parse("text/plain"), write_title_tv.text.toString())
        val content = RequestBody.create(MediaType.parse("text/plain"), write_content_tv.text.toString())
        val id = RequestBody.create(MediaType.parse("text/plain"), "nuri")
        val postBoardResponse = networkService.postBoard(image,title,content,id)
        postBoardResponse.enqueue(object : retrofit2.Callback<PostBoardResponse>{
            override fun onFailure(call: Call<PostBoardResponse>?, t: Throwable?) {

            }

            override fun onResponse(call: Call<PostBoardResponse>?, response: Response<PostBoardResponse>?) {
                if(response!!.isSuccessful){
                    startActivity(Intent(applicationContext, MainActivity::class.java))
                    finish()
                }
            }

        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //갤러리에 접근 가능하고 무엇인가를 동작했을때 이 곳에 오게된다.(request_code를 통해 내가 어디서 부른지 확인, 2. 응답코드를 보고 사진을 찍고 확인을 눌렀으면
        if (requestCode == REQ_CODE_SELECT_IMAGE) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    //if(ApplicationController.getInstance().is)
                    this.data = data!!.data//이미지의 uri를 가져올때
                    Log.v("이미지", this.data.toString())

                    val options = BitmapFactory.Options()

                    var input: InputStream? = null // here, you need to get your context.
                    try {
                        input = contentResolver.openInputStream(this.data)
                    } catch (e: FileNotFoundException) {
                        e.printStackTrace()
                    }

                    val bitmap = BitmapFactory.decodeStream(input, null, options) // InputStream 으로부터 Bitmap 을 만들어 준다.
                    val baos = ByteArrayOutputStream()//이미지 생성
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, baos)//jpeg형태로 적당히 압축
                    val photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray())
                    val photo = File(this.data.toString()) // 가져온 파일의 이름을 알아내려고 사용합니다

                    ///RequestBody photoBody = RequestBody.create(MediaType.parse("image/jpg"), baos.toByteArray());
                    // MultipartBody.Part 실제 파일의 이름을 보내기 위해 사용!!

                    image = MultipartBody.Part.createFormData("photo", photo.name, photoBody)
                    //포스트멘에서 보낸 키값이랑 name이랑 같아야 한다.

                    //body = MultipartBody.Part.createFormData("image", photo.getName(), profile_pic);

                    //만들었던 이미지 뷰에 이미지를 띄우기 위해서
                    Glide.with(this)
                            .load(data.data)
                            .centerCrop()
                            .into(write_image)

                } catch (e: Exception) {
                    e.printStackTrace()
                }

            }
        }
    }
}