package sopt.hyeran.a3rdseminar

import android.content.Intent
import android.graphics.Canvas
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.view.TouchDelegate
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.RelativeLayout
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var kakaoItems : ArrayList<KaKaoItem>  // lateinit: 초기화의 시점을 늦춰주는 키워드
    private lateinit var kakaoAdapter : KaKaoAdapter
    lateinit var swipeController : SwipeController
    lateinit var itemTouchListener: ItemTouchHelper

    var isDisplayButtons : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        kakaoItems = ArrayList()
        kakaoItems.add(KaKaoItem(R.drawable.pika1, "배달의민족", "(배달안내) 고객님이 주문하신 음식이 90분 내에 도착할 예정입니다.", "오후 9:50"))
        kakaoItems.add(KaKaoItem(R.drawable.pika2, "♥4총사♥", "한강 언제가???????", "오후 9:20"))
        kakaoItems.add(KaKaoItem(R.drawable.pika3, "친한척대마왕", "안감동에 사는 고란희씨", "오후 9:07"))
        kakaoItems.add(KaKaoItem(R.drawable.pika4, "SalNY", "나요미와 혜라니", "오후 7:20"))
        kakaoItems.add(KaKaoItem(R.drawable.pika5, "길혜리", "고기사줘", "오후 6:00"))
        kakaoItems.add(KaKaoItem(R.drawable.pika6, "울엄마", "우리딸 뭐해?", "오후 4:55"))
        kakaoItems.add(KaKaoItem(R.drawable.pika7, "꿀뙈지", "내일 같이 밥묵자~~!!!!", "오후 4:10"))
        kakaoItems.add(KaKaoItem(R.drawable.pika8, "예민하늬", "돈 갚아!!!!!!!", "오후 3:22"))
        kakaoItems.add(KaKaoItem(R.drawable.pika9, "도히언니", "까띾띾ㄹ깔깔깔깔까띾ㄹ", "오후 3:04"))
        kakaoItems.add(KaKaoItem(R.drawable.pika10, "우렁니", "나 눈에 모기물림;;;ㅠㅠ", "오후 2:41"))

        kakaoAdapter = KaKaoAdapter(kakaoItems, this)
        kakaoAdapter.setOnItemClickListener(this)

        main_rv.layoutManager = LinearLayoutManager(this)   // layoutManager: item들의 배치방식 결정
        main_rv.adapter = kakaoAdapter

        swipeController = SwipeController(object : SwipeControllerActions() {
            override fun onRightClicked(position: Int) {
                kakaoAdapter.notifyItemRemoved(position)
                kakaoAdapter.notifyItemRangeChanged(position, kakaoAdapter.itemCount)
            }
        })

        itemTouchListener = ItemTouchHelper(swipeController)
        itemTouchListener.attachToRecyclerView(main_rv)

        main_rv.addItemDecoration(object : RecyclerView.ItemDecoration(){
            override fun onDraw(c: Canvas?, parent: RecyclerView?, state: RecyclerView.State?) {
                swipeController.onDraw(c!!)
            }
        })
        main_float_add.setOnClickListener {
            clickFloat()
        }

        main_float_c1.setOnClickListener {
            Toast.makeText(this, "1번 버튼", Toast.LENGTH_SHORT).show()
        }

        main_float_c2.setOnClickListener {
            Toast.makeText(this, "2번 버튼", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onClick(v: View?) {
        val idx : Int = main_rv.getChildAdapterPosition(v)
        val name : String = kakaoItems[idx].name
        val profile : Int = kakaoItems[idx].profile

        val intent = Intent(applicationContext, ChatActivity::class.java)
        intent.putExtra("name", name)
        intent.putExtra("profile", profile)
        startActivity(intent)
    }

    fun clickFloat(){
        if (!isDisplayButtons) {
            isDisplayButtons = true
            main_wrapper_layout.visibility = View.VISIBLE
            main_wrapper_layout.setOnClickListener{ clickFloat() }
            val animation = AnimationUtils.loadAnimation(this, R.anim.float_main_show)
            main_float_add.setBackgroundResource(R.drawable.btn_cancel)
            main_float_add.startAnimation(animation)

            val layoutParams1 = main_float_c1.layoutParams as RelativeLayout.LayoutParams
            layoutParams1.bottomMargin += (main_float_c1.height * 1.2).toInt()
            val showC1 = AnimationUtils.loadAnimation(this, R.anim.float_button1_show)
            main_float_c1.layoutParams = layoutParams1
            main_float_c1.startAnimation(showC1)
            main_float_c1.isClickable = true

            val layoutParam2 = main_float_c2.layoutParams as RelativeLayout.LayoutParams
            layoutParam2.bottomMargin += (main_float_c2.height * 2.4).toInt()
            val showC2 = AnimationUtils.loadAnimation(this, R.anim.float_button2_show)
            main_float_c2.layoutParams = layoutParam2
            main_float_c2.startAnimation(showC2)
            main_float_c2.isClickable = true

        } else {
            isDisplayButtons = false
            main_wrapper_layout.visibility = View.INVISIBLE
            val animation = AnimationUtils.loadAnimation(this, R.anim.float_main_hide)
            main_float_add.setBackgroundResource(R.drawable.btn_add)
            main_float_add.startAnimation(animation)

            val layoutParams1 = main_float_c1.layoutParams as RelativeLayout.LayoutParams
            layoutParams1.bottomMargin -= (main_float_c1.height * 1.2).toInt()
            val hideC1 = AnimationUtils.loadAnimation(this, R.anim.float_button1_hide)
            main_float_c1.layoutParams = layoutParams1
            main_float_c1.startAnimation(hideC1)
            main_float_c1.isClickable = false

            val layoutParams2 = main_float_c2.layoutParams as RelativeLayout.LayoutParams
            layoutParams2.bottomMargin -= (main_float_c2.height * 2.4).toInt()
            val hideC2 = AnimationUtils.loadAnimation(this, R.anim.float_button2_hide)
            main_float_c2.layoutParams = layoutParams2
            main_float_c2.startAnimation(hideC2)
            main_float_c2.isClickable = false
        }
    }
}
