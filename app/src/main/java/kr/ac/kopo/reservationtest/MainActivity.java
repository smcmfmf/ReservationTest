package kr.ac.kopo.reservationtest;

import android.graphics.Color;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.Chronometer;
import android.widget.DatePicker;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    Chronometer chrono;
    RadioGroup rg;
    RadioButton rbDate, rbTime;
    // CalendarView calendar;
    DatePicker calendar;
    TimePicker timePicker;

    TextView textResult;
//    Button btnStart, btnDone;
    int selectedYear, selectedMonth, selectedDay;
    int selectedHour, selectedMinutel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        chrono = findViewById(R.id.chronometer);
        // btnStart = findViewById(R.id.btn_start);
        rg = findViewById(R.id.rg);
        rbDate = findViewById(R.id.rb_date);
        rbTime = findViewById(R.id.rb_time);
        calendar = findViewById(R.id.calendar);
        timePicker = findViewById(R.id.time_pick);
        textResult = findViewById(R.id.text_result);
//        btnDone = findViewById(R.id.btn_done);

        calendar.setVisibility(View.INVISIBLE);
        timePicker.setVisibility(View.INVISIBLE);
        
        rbDate.setOnClickListener(rbListener);
        rbTime.setOnClickListener(rbListener);

//        btnStart.setOnClickListener(btnListener);
//        btnDone.setOnClickListener(btnListener);
        chrono.setOnClickListener(chronoListener);
        textResult.setOnLongClickListener(textListener);

//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
//                selectedYear = year;
//                selectedMonth = month + 1; // 자바에선 월을 0 ~ 11으로 사용함
//                selectedDay = dayOfMonth;
//            }
//        });
    } // End OnCreate

    View.OnClickListener chronoListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            chrono.setBase(SystemClock.elapsedRealtime()); // 시스템의 실시간 정보로 사용함
            chrono.start();
            chrono.setTextColor(Color.CYAN);
            rg.setVisibility(View.VISIBLE);
        }
    };

    View.OnLongClickListener textListener = new View.OnLongClickListener() {
        @Override
        public boolean onLongClick(View v) {
            chrono.stop();
            chrono.setTextColor(Color.BLUE);
            selectedYear = calendar.getYear();
            selectedMonth = calendar.getMonth() + 1; // 자바에선 월을 0 ~ 11으로 사용함
            selectedDay = calendar.getDayOfMonth();
            selectedHour = timePicker.getHour();
            selectedMinutel = timePicker.getMinute();
            textResult.setText(selectedYear + "년 " + selectedMonth + "월 " + selectedDay + "일 " + selectedHour + "시 " + selectedMinutel + "분으로 예약 완료됨!");
            rg.setVisibility(View.INVISIBLE);
            calendar.setVisibility(View.INVISIBLE);
            timePicker.setVisibility(View.INVISIBLE);
            return true;
        }
    };

    View.OnClickListener rbListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            calendar.setVisibility(View.INVISIBLE);
            timePicker.setVisibility(View.INVISIBLE);
            RadioButton rbEvent = (RadioButton) v; // 선택한 버튼 속성 값을 가져옴
            if (rbEvent == rbDate)
            {
                calendar.setVisibility(View.VISIBLE);
            }
            else
            {
                timePicker.setVisibility(View.VISIBLE);
            }
        }
    };

//    View.OnClickListener btnListener = new View.OnClickListener() {
//        @Override
//        public void onClick(View v) {
//            Button btnEvent = (Button) v;
//            if(btnEvent == btnStart)
//            {
//                chrono.setBase(SystemClock.elapsedRealtime()); // 시스템의 실시간 정보로 사용함
//                chrono.start();
//                chrono.setTextColor(Color.CYAN);
//            }
//            else
//            {
//                chrono.stop();
//                chrono.setTextColor(Color.BLUE);
//                selectedHour = timePicker.getHour();
//                selectedMinutel = timePicker.getMinute();
//                textResult.setText(selectedYear + "년 " + selectedMonth + "월 " + selectedDay + "일 " + selectedHour + "시 " + selectedMinutel + "분으로 예약 완료됨!");
//            }
//        }
//    };


} // End MainAtivity