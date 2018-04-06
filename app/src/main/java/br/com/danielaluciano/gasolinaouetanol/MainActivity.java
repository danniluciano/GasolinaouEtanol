package br.com.danielaluciano.gasolinaouetanol;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.text.NumberFormat;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private NumberFormat currencyFormat =
            NumberFormat.getCurrencyInstance();
    private double percent2 = 0.0;
    private double percent = 0.0;
    private TextView porcentagemGasolinaTextView;
    private TextView porcentagemEtanolTextView;
    private TextView resultadoTextView;
    private ImageView resultadoImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
                setContentView(R.layout.activity_main);
        porcentagemGasolinaTextView = (TextView)
                findViewById(R.id.porcentagemGasolinaTextView);
        porcentagemEtanolTextView = (TextView)
                findViewById(R.id.porcentagemEtanolTextView);
        resultadoTextView = (TextView)
                findViewById(R.id.resultadoTextView);
        porcentagemEtanolTextView.setText(currencyFormat.format(0));
        porcentagemGasolinaTextView.setText(currencyFormat.format(0));
        resultadoImageView = (ImageView)
                findViewById(R.id.resultadoImageView);
        SeekBar gasolinaSeekBar =
                (SeekBar) findViewById(R.id.gasolinaSeekBar);
        SeekBar etanolSeekBar =
                (SeekBar) findViewById(R.id.etanolSeekBar);
        gasolinaSeekBar.setOnSeekBarChangeListener(seekBarListener);
        etanolSeekBar.setOnSeekBarChangeListener(seekBarListener);

    }

    private SeekBar.OnSeekBarChangeListener seekBarListener =
            new SeekBar.OnSeekBarChangeListener() {
                @Override
                public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                    if (seekBar.getId() == R.id.etanolSeekBar) {
                        percent = progress;
                        percent = percent /10;
                        porcentagemEtanolTextView.setText(currencyFormat.format(percent));
                    }
                    else {
                        percent2 = progress;
                        percent2 = percent2 / 10;
                        porcentagemGasolinaTextView.setText(currencyFormat.format(percent2));
                    }
                    calculate ();
                }

                @Override
                public void onStartTrackingTouch(SeekBar seekBar) {

                }

                @Override
                public void onStopTrackingTouch(SeekBar seekBar) {

                }
            };

    private void calculate (){
        double resultado = percent/percent2;
        if (resultado >= 0.7) {
            resultadoTextView.setText(R.string.resultadoGasolina);
            resultadoImageView.setImageResource(R.drawable.gasolina);
        }
        else{
            resultadoTextView.setText(R.string.resultadoEtanol);
            resultadoImageView.setImageResource(R.drawable.etanol);
        }
    }
}
