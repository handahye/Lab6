package org.androidtown.myapplication;

import android.app.Activity; import android.os.Bundle;
import android.os.Environment;
import android.view.View; import android.widget.Button; import android.widget.EditText; import android.widget.Toast; import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream; import java.io.InputStreamReader; import java.io.OutputStreamWriter;

public class MainActivity extends Activity {
    private final static String NOTES = "notes.txt";
    private EditText txtData;
    private Button button, button2, button3, button4;
    File file;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtData = (EditText) findViewById(R.id.txtData);
        button = (Button) findViewById(R.id.button);
        button2 = (Button) findViewById(R.id.button2);
        button3 = (Button) findViewById(R.id.button3);
        button4 = (Button) findViewById(R.id.button4);
        button.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                write();
            }
        });
        button2.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                clear();
            }
        });
        button3.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                read();

            }
        });
        button4.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                finishAPP();
            }
        });
        File sdCard = Environment.getExternalStorageDirectory();
        File directory = new File (sdCard.getAbsolutePath() + "/MyFiles");
        directory.mkdirs();
        file = new File(directory, "textfile.txt");


    }
    public void write(){
        try {
            FileOutputStream fOut = new FileOutputStream(file);
            OutputStreamWriter out = new OutputStreamWriter(fOut);//아웃풋 스트림 객체 생성
            out.write(txtData.getText().toString());//txtData를 텍스트로 얻어서 문자열로 변환하고 출력 스트림에 씀
            Toast.makeText(this, "WRITE", Toast.LENGTH_SHORT).show();
            out.close();
        } catch (Throwable t) {
        }
    }
    public void clear(){
        txtData.setText("");
        Toast.makeText(this, "CLEAR", Toast.LENGTH_SHORT).show();
    }
    public void read(){
        try {

            FileInputStream fIn = new FileInputStream(file);
            //InputStream in = openFileInput(NOTES);//파일이름
            if (fIn != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(fIn));//버퍼리더로 파일을 넣음
                String str = "";
                StringBuffer buf = new StringBuffer();

                while ((str = reader.readLine()) != null) {//라인씩 읽어오는데 만약 데이터가 널이 아니면
                    buf.append(str + "\n");
                }
                fIn.close();
                txtData.setText(buf.toString());//editText에 읽어온 스트링을 반환함
            }
            } catch (java.io.FileNotFoundException e) {

        } catch (Throwable t) {
            Toast.makeText(this, "READ" + t.toString(), Toast.LENGTH_SHORT).show(); }
    }
    public void finishAPP()//어플종료
    {
        Toast.makeText(this, "FINISH", Toast.LENGTH_SHORT).show();
        finish();
    }
}

