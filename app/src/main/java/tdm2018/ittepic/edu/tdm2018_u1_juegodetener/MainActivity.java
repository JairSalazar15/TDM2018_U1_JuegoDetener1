package tdm2018.ittepic.edu.tdm2018_u1_juegodetener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {

    Button b1;
    TextView cont;
    private EditText num1;
    Thread thread, thread1;
    boolean ejecutar,pausado;
    double con2=1;
    double con = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b1=findViewById(R.id.button);
        num1 = (EditText) findViewById(R.id.editText);
        cont = findViewById(R.id.textView2);
        ejecutar = true;
        pausado = false;


        cont.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                juego();

            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //pararJuego();
                pausado=true;
                cont.setText("Iniciar");
                num1.setText("");
                finish();
            }
        });
    }

    public void juego(){
        int n=num1.getText().toString().length();
        ejecutar = true;
        if (con2 >=3  && con2 <3.1 ) {
            Toast.makeText(MainActivity.this, "Ganaste!", Toast.LENGTH_SHORT).show();
            ejecutar = false;

        } else {
            if (con2 > 1) {
                Toast.makeText(MainActivity.this, "fallaste!", Toast.LENGTH_SHORT).show();
                ejecutar = true;
            }

            if (pausado == false) {

                try {
                    thread1 = new Thread() {
                        public void run() {
                            //ejecuta en 2do plano
                            while (ejecutar) {
                                runOnUiThread(new Runnable() {
                                    //Ejecutar hilo en interfaz de usuario
                                    @Override
                                    public void run() {
                                        DecimalFormat df = new DecimalFormat();
                                        String twoDigitNum = df.format(con2);
                                        cont.setText(twoDigitNum);
                                    }
                                });
                                try {
                                    sleep(250);
                                } catch (InterruptedException e) {
                                }
                                con2 += 0.10;
                                if (con2 >= 5) {
                                    con2 = 1.00;
                                }
                            }
                        }
                    };
                    thread1.start();
                } catch (Exception e) {
                    Toast.makeText(MainActivity.this, "Error ya se ejecuto el hilo", Toast.LENGTH_SHORT).show();
                }
                pausado = true;
            }

        }


    }
    public void pararJuego(){
        thread.stop();
    }
}
