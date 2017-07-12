package com.example.royal.touchdema;

import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.StringBuilderPrinter;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private TextView tvMessage;
    int total=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();

    }
    private void findViews(){
        tvMessage=(TextView)findViewById(R.id.tvMessage);
        ConstraintLayout constraintLayout = (ConstraintLayout)findViewById(R.id.layout);

        //註冊onTouchListener監聽者
        constraintLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                StringBuilder sb=new StringBuilder();
                //呼叫沒帶參數的 getX(),getY()會取得第一個觸擊點的座標
                sb.append(String.format(Locale.getDefault(),"first pointer: (%.1f,%.1f), ",event.getX(),event.getY()));
                sb.append("touch state: ");
                /*呼叫getAction()可以取得使用者觸擊方式:
                               ACTION_DOWN 代表剛觸碰到的元件;
                               ACTION_MOVE 代表不僅觸碰到還持續滑動，也就是持續改變觸擊點位置;
                               ACTION_UP 代表觸擊結束*/

                switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                        sb.append("Action_down\n");
                        total++;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        sb.append("Action_move\n");
                        break;
                    case MotionEvent.ACTION_UP:
                        sb.append("Action_up\n");
                        break;
                    default:
                        sb.append("\n");
                        break;
                }

                //呼叫 getPointerCount()取得觸擊點總數
                int pointerCount=event.getPointerCount();
                sb.append(String.format(Locale.getDefault(),"pointer count: %d %n",pointerCount));
                //觸擊點可能有多個，使用迴圈搭配索引將各點的ID、座標一一取得。一般而言索引值越小代表越早的觸擊點。
                for(int i = 0;i<pointerCount;i++){
                    sb.append(String.format(Locale.getDefault(),"pointer %d: (%.1f,%.1f) %n",event.getPointerId(i)+1,event.getX(i),event.getY(i)));
                }
                sb.append("共點了:"+total+"次\n");

                tvMessage.setText(sb);
                return true;
            }
        });
    }
}
