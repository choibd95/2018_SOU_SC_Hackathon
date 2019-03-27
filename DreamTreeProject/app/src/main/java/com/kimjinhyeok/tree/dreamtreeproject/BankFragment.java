package com.kimjinhyeok.tree.dreamtreeproject;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import static android.content.Context.MODE_PRIVATE;
import static com.kimjinhyeok.tree.dreamtreeproject.R.id.htext;
import static com.kimjinhyeok.tree.dreamtreeproject.SignActivity.nav_header_id_text;

public class BankFragment extends Fragment {
    private String TAG = "LOG : BankActivity";
    private ListView mListView = null;
    private CustomListAdapter mAdapter = null;
    private Button btnAdd = null, btnDel = null, btnAllSelect = null;
    Button bteat;
    Context context;
    BankDialog bankDialog;
    OpenHelper openHelper;
    SQLiteDatabase conn;
    String moneyflag;
    TextView tvbank;
    TextView tvminus;
    TextView tvtotal;

    TextView daycount;
    TextView avg;
   // TextView mealTV;

    TextView naview;

     ArrayList<bankvo> volist;

    int sumInput = 0;//총 지급액
    int sumOutput = 0;//총 지출액
    int totalCash = 0; //카드 잔액
    int countOutput = 0;//지출 횟수
    int avgOutput = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d(TAG, "onCreate(Bundle)");
        super.onCreate(savedInstanceState);

        View view = inflater.inflate(R.layout.bank_fragment, container);

        mListView = (ListView) view.findViewById(R.id.listView);
        btnAdd = (Button)view.findViewById(R.id.btn_add);
        btnDel = (Button)view.findViewById(R.id.btn_delete);
        btnAllSelect = (Button)view.findViewById(R.id.btn_allSelect);

        bteat = (Button)view.findViewById(R.id.bteat);

        //view setting
        initViewData();

        //temp data
//        for(int i = 0 ; i < 10 ; i++)
//            mAdapter.addItem(R.drawable.anti_smoking, "테스트 : "+(i+1),false,vo);
        //  mAdapter.addItem(R.drawable.no,false,name,address);

        tvbank = (TextView) view.findViewById(R.id.tvbank);
        tvminus =  (TextView) view.findViewById(R.id.tvminus);
        tvtotal =  (TextView) view.findViewById(R.id.tvtotal);

        daycount =  (TextView) view.findViewById(R.id.daycount);
        avg =  (TextView) view.findViewById(R.id.avg);
        // mealTV =  (TextView) view.findViewById(R.id.meal);

        naview =  (TextView) view.findViewById(htext);

        dbselect();

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        openHelper = new OpenHelper(context);
    }


    /**
     * View setting
     */
    private void initViewData(){
        btnAdd.setOnClickListener(buttonEvent);
        btnDel.setOnClickListener(buttonEvent);
        btnAllSelect.setOnClickListener(buttonEvent);
        bteat.setOnClickListener(buttonEvent);

        mAdapter = new CustomListAdapter() ;

        mListView.setAdapter(mAdapter);
    }

    private View.OnClickListener buttonEvent = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch(v.getId()){
                case R.id.btn_add:
                    moneyflag = "수입";
                    Dialog();
                    break;
                case R.id.btn_delete:
                    moneyflag = "지출";
                    Dialog();
                    break;
                case R.id.btn_allSelect:
                    ArrayList<Integer> seqList = mAdapter.getItemList();
                    for (int ivo : seqList){
                        db_delete(ivo);
                    }
                    dbselect();
                    break;

                case  R.id.bteat :
                    eatdialog();
                    break;
            }
            //refresh adapter
            mAdapter.notifyDataSetChanged();
        }
    };

    public void Dialog(){
        bankDialog = new BankDialog(context, bankListener); // 왼쪽 버튼 이벤트
        // 오른쪽 버튼 이벤트

        //요청 이 다이어로그를 종료할 수 있게 지정함
        bankDialog.setCancelable(true);
        bankDialog.getWindow().setGravity(Gravity.CENTER);
        bankDialog.show();

        if(!moneyflag.equals("수입"))
        {

            bankDialog.dal1.setText("지출 기록");
            bankDialog.dal2.setText("지출 내역");
            bankDialog.dal3.setText("지출 금액");
            bankDialog.dal4.setText("지출 날짜");
        }
        time_now();


    }

    //다이얼로그 클릭이벤트
    private View.OnClickListener bankListener = new View.OnClickListener() {
        public void onClick(View v) {

            switch (v.getId()) {
                case R.id.bt_bank_no:
                    bankDialog.dismiss();
                    break;
                case R.id.bt_bank_ok:
                    if(bankDialog.incon.getText().toString().equals(""))
                    {
                        Toast.makeText(context, "사용한곳을 입력해주세요", Toast.LENGTH_LONG).show();
                    }else if(bankDialog.inmoney.getText().toString().equals(""))
                    {
                        Toast.makeText(context, "금액을 입력해주세요", Toast.LENGTH_LONG).show();
                    }else
                    {
                        db_plus_insert();
                        bankDialog.dismiss();
                    }
                    break;
                case R.id.bt_time :
                    timecall();
                    break;
                case  R.id.bt_date :
                    yearcall();
                    break;
            };
        }

    };
    void time_now()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH)+1;
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        String days = year+"년 "+month+"월 "+day+"일";
        String times = hour+"시 "+minute+"분";
        bankDialog.changedate(days);

        bankDialog.changtime(times);
    }

    TimePickerDialog.OnTimeSetListener timelistener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {

// 설정버튼 눌렀을 때

            String hour =Integer.toString(hourOfDay);
            String min =Integer.toString(minute);
            String time = hour+"시 "+min+"분";
            bankDialog.changtime(time);
        }
    };


    void timecall()
    {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog dialog = new TimePickerDialog(context, timelistener, hour, minute, false);
        dialog.show();
        Log.d("TEST","시=="+hour+"분==="+minute);
    }


    void  yearcall()
    {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog dialog = new DatePickerDialog(context, yearlistener, year, month, day);
        dialog.show();
    }

    private DatePickerDialog.OnDateSetListener yearlistener = new DatePickerDialog.OnDateSetListener() {

        @Override

        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            monthOfYear = monthOfYear+1;
            bankDialog.changedate(year + "년" + monthOfYear + "월" + dayOfMonth +"일");
        }

    };




    void dbselect()
    {
        mAdapter.items.clear();
        conn = openHelper.getReadableDatabase();

//        Cursor c = conn.rawQuery("select con,intime,money,banking,seq,division,minus from bank", null);
        Cursor c = conn.rawQuery("SELECT seq, money, div, con, intime from bank where del_bool ='N'", null);

        //   volist = new ArrayList<>();


        while (c.moveToNext()){
            bankvo vo = new bankvo();

            vo.setSeq(c.getInt(0));
            vo.setMoney(c.getInt(1));
            vo.setDiv(c.getString(2));
            vo.setCon(c.getString(3));
            vo.setIntime(c.getString(4));

            if(vo.getDiv().equals("수입"))
            {




                mAdapter.addItem(R.drawable.transaction,false,vo);



            }
            else if(vo.getDiv().equals("지출"))
            {
                mAdapter.addItem(R.drawable.restaurant,false,vo);



            }else{
                Toast.makeText(getContext(),"구분자 에러", Toast.LENGTH_SHORT);
            }

        }
        Cursor c2 = conn.rawQuery("SELECT SUM(money) from bank where div='지출' and del_bool='N'", null);
        while(c2.moveToNext()){
            sumOutput = c2.getInt(0);
        }
        Cursor c3 = conn.rawQuery("SELECT SUM(money) from bank where div='수입' and del_bool='N'", null);
        while(c3.moveToNext()){
            sumInput = c3.getInt(0);
        }
        Cursor c4 = conn.rawQuery("SELECT COUNT(*) from bank where div='지출' and del_bool='N'",null);
        while(c4.moveToNext()){
            countOutput= c4.getInt(0);
        }
        conn.close();
        totalCash = sumInput - sumOutput;
        if(countOutput==0) countOutput =1;
        avgOutput = totalCash / availCount();

        avgOutput=(int)Math.round(avgOutput/1000.0)*1000;
        String tvin = toNumFormat(sumInput);

        tvbank.setText(tvin);

        String minin = toNumFormat(sumOutput);
        tvminus.setText(minin);

                String total2 = toNumFormat(totalCash);
            tvtotal.setText(total2);

        SharedPreferences pref = context.getSharedPreferences("pref", MODE_PRIVATE);
        int meal = pref.getInt("eat", 2);





        SharedPreferences.Editor editor = pref.edit();
        editor.putString("totalcash", total2+"원");

        editor.commit();

        String cash = pref.getString("totalcash", "100,000원");


        mAdapter.notifyDataSetChanged();
        avg.setText(avgOutput+"");

        daycount.setText(availCount()+"");

        switch (meal){
            case 1:
                bteat.setText("끼니 설정@\n하루 한끼");
                break;
            case 2:
                bteat.setText("끼니 설정@\n하루 두끼");
                break;
            case 3:
                bteat.setText("끼니 설정@\n하루 세끼");
                break;
        }


    }

    void db_delete(int seq){
        conn = openHelper.getReadableDatabase();
        StringBuffer delsql = new StringBuffer();
        delsql.append("UPDATE bank SET del_bool='Y' where seq = ");
        delsql.append(seq);
        conn.execSQL(delsql.toString());
        conn.close();
        dbselect();
    }

    void db_plus_insert()
    {
        long time = System.currentTimeMillis();
        SimpleDateFormat dayTime = new SimpleDateFormat("yy-MM-dd\nhh:mm");
        String str = dayTime.format(new Date(time));
        String incon = String.valueOf(bankDialog .incon.getText());
        String moneys = String.valueOf(bankDialog.inmoney.getText());
        int money =Integer.parseInt(moneys);
        conn = openHelper.getReadableDatabase();

//        String insql = "INSERT INTO bank (division, con, intime, money, banking,minus) VALUES ('"+moneyflag+"','"+incon+"','"+str+"','"+money+"','"+bank+"','"+minusbank+"');";
//        String insql ="INSERT INTO BANK(div, con, intime, money) VALUES('수입','구청 지급','2017:11:01',100000)";
        StringBuffer insql = new StringBuffer();
        insql.append("INSERT INTO BANK(div, con, intime, money) VALUES('");
        insql.append(moneyflag);
        insql.append("','");
        insql.append(incon);
        insql.append("','");
        insql.append(str);
        insql.append("',");
        insql.append(money+"");
        insql.append(")");

        conn.execSQL(insql.toString());


        conn.close();

        dbselect();
    }

    public  String toNumFormat(int num) {
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(num);
    }

    public void eatdialog()
    {

        final CharSequence[] eat = {"하루 한끼","하루 두끼","하루 세끼"};
        AlertDialog.Builder alt_bld = new AlertDialog.Builder(getContext());
        alt_bld.setIcon(R.drawable.main_white);
        alt_bld.setTitle("끼니 선택");

        alt_bld.setSingleChoiceItems(eat, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {

//                String eatc = eat[item].toString().s(3);
                int eatnum =   item+1;
                SharedPreferences pref = context.getSharedPreferences("pref", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.putInt("eat", eatnum);
                editor.commit();
           //     Toast.makeText(context, eatnum+"", Toast.LENGTH_LONG).show();

                dialog.dismiss();

                bteat.setText("끼니 설정\n"+eat[item].toString());

                dbselect();
            }

        });
        AlertDialog alert = alt_bld.create();
        alert.show();
    }

    /**
     * 남을일수 * 끼니수 사용 가능 횟수
     * @return
     */
    public int availCount(){



        SharedPreferences pref = context.getSharedPreferences("pref", MODE_PRIVATE);
       int meal = pref.getInt("eat", 2);


        Calendar calendar = Calendar.getInstance();
        int availCount=0;
        int totalDay=0;
        int thisYear=calendar.get(Calendar.YEAR);
        switch (calendar.get(Calendar.MONTH)+1)
        {
            case 1: case 3: case 5: case 7: case 8: case 10: case 12:
            totalDay = 31; break;
            case 4: case 6: case 9: case 11:
            totalDay = 30; break;
            case 2:
                if (thisYear%4==0 && thisYear%100!=0 || thisYear%400==0) //윤년
                {
                    totalDay = 29;
                }else{
                    totalDay = 28;
                }
        }
        availCount = (totalDay-calendar.get(Calendar.DAY_OF_MONTH)+1)*meal;
        return availCount;

    }

}
