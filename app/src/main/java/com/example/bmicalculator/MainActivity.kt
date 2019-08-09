package com.example.bmicalculator

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //마지막에 저장한 값을 불러옴
        loadData()
        
        resultButton.setOnClickListener { //id가  resultButton이라는 것이 클릭 되면
            //마지막에 입력한 내용 저장
            saveData(heightEditText.text.toString().toInt(), weightEditText.text.toString().toInt())
            
            //다음 화면으로 이동
            startActivity<ResultActivity>( // resultAcrivity 코틀린 파일로 이동, 앙코 라이브러리 기능
                "weight" to weightEditText.text.toString(),
                "height" to heightEditText.text.toString()
            )
        }
    }

    //데이터를 저장하는 메서드
    private fun saveData(height: Int, weight: Int){
        val pref = PreferenceManager.getDefaultSharedPreferences(this) //프리퍼런스 매니저를 사용해 프리퍼런스 객체를 얻음
        val editor = pref.edit() // 프리퍼런스의 에디터를 editor에 담음

        editor.putInt("KEY_HEIGHT", height)  // editor를 사용에  height 값을 KEY_HEIGHT 키에 담음  , 해시맵
            .putInt("KEY_WEIGHT", weight)
            .apply() // 반영(신청)
    }

    //데이터를 불러오는 메서드
    private fun loadData(){
        val pref = PreferenceManager.getDefaultSharedPreferences(this) // 프리퍼런스 매니저를 사용해 프리퍼런스 객체를 얻음
        val height = pref.getInt("KEY_HEIGHT", 0) //KEY_HEIGHT 키에 해당하는 값을 가져옴, 아무것도 없다면  0을 가져옴
        val weight = pref.getInt("KEY_WEIGHT", 0)

        if(height != 0 && weight != 0){ // weight와 height가 둘다 0 이 아니라면
            heightEditText.setText(height.toString())//id가 heightEditText 인것에 Text를 입력(문자열로 변환해서)
            weightEditText.setText(weight.toString())
        }
    }
}
