package sopt.hyeran.a3rdseminar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var kakaoItems : ArrayList<KaKaoItem>  // lateinit: 초기화의 시점을 늦춰주는 키워드
    lateinit var kakaoAdapter : KaKaoAdapter
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

        kakaoAdapter = KaKaoAdapter(kakaoItems)
        main_rv.layoutManager = LinearLayoutManager(this)   // layoutManager: item들의 배치방식 결정
        main_rv.adapter = kakaoAdapter
    }
}
