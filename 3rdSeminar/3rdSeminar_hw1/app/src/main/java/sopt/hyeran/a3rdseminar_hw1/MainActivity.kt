package sopt.hyeran.a3rdseminar_hw1

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var Items : ArrayList<Item>
    lateinit var Adapter: Adapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Items = ArrayList()
        // Int형으로 컬러 코드 넘겨줄때 Color.parseColor("")
        Items.add(Item(Color.parseColor("#00ff00"), Color.parseColor("#00ff00"), "5515", "[서울]지선버스"))
        Items.add(Item(Color.parseColor("#0000ff"), Color.parseColor("#0000ff"), "720", "[서울]간선버스"))
        Items.add(Item(Color.parseColor("#ff0000"), Color.parseColor("#ff0000"), "3100", "[서울]광역버스"))
        Items.add(Item(Color.parseColor("#ff0000"), Color.parseColor("#ff0000"), "3600", "[서울]광역버스"))
        Items.add(Item(Color.parseColor("#0000ff"), Color.parseColor("#0000ff"), "172", "[서울]간선버스"))
        Items.add(Item(Color.parseColor("#00ff00"), Color.parseColor("#00ff00"), "1221", "[서울]지선버스"))
        Items.add(Item(Color.parseColor("#00ff00"), Color.parseColor("#00ff00"), "1224", "[서울]지선버스"))
        Items.add(Item(Color.parseColor("#00ff00"), Color.parseColor("#00ff00"), "1131", "[서울]지선버스"))
        Items.add(Item(Color.parseColor("#00ff00"), Color.parseColor("#00ff00"), "1141", "[서울]지선버스"))

        Adapter = Adapter(Items)
        main_rv.layoutManager = LinearLayoutManager(this)
        main_rv.adapter = Adapter
    }
}
