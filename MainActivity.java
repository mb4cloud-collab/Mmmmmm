package com.mb4x.orbitalpredictor;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editM, editC, editH;
    private TextView textResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // تصميم الواجهة من الكود مباشرة
        android.widget.LinearLayout layout = new android.widget.LinearLayout(this);
        layout.setOrientation(android.widget.LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        TextView title = new TextView(this);
        title.setText("🚀 MB4X Orbital Predictor v2.5");
        title.setTextSize(20);
        title.setTypeface(null, android.graphics.Typeface.BOLD);
        layout.addView(title);

        editM = new EditText(this);
        editM.setHint("الكتلة M (kg) - افتراضي 77000");
        layout.addView(editM);

        editC = new EditText(this);
        editC.setHint("معامل السحب C - افتراضي 0.085");
        layout.addView(editC);

        editH = new EditText(this);
        editH.setHint("الارتفاع h (km) - افتراضي 435");
        layout.addView(editH);

        Button btnCalc = new Button(this);
        btnCalc.setText("حساب العمر المداري 📊");
        layout.addView(btnCalc);

        textResult = new TextView(this);
        textResult.setTextSize(16);
        textResult.setPadding(0, 30, 0, 0);
        layout.addView(textResult);

        setContentView(layout);

        btnCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculate();
            }
        });
    }

    private void calculate() {
        try {
            double M = editM.getText().toString().isEmpty() ? 77000 : Double.parseDouble(editM.getText().toString());
            double C = editC.getText().toString().isEmpty() ? 0.085 : Double.parseDouble(editC.getText().toString());
            double h = editH.getText().toString().isEmpty() ? 435 : Double.parseDouble(editH.getText().toString());

            // ثابت الجسم I الخبيث 😏
            double I = M * C;
            
            // معادلة تقريبية للعمر المداري
            double rho_earth = 1.225;
            double rho_h = rho_earth * Math.exp(-h / 7.5);
            double V = Math.sqrt(3.986e14 / ((6371 + h) * 1000));
            
            double years = (I * (rho_earth / rho_h)) / (V * V) * 0.005;

            textResult.setText("• ثابت الجسم (I = M × C): " + String.format("%.2f", I) +
                    "\n• العمر المداري المتوقع: " + String.format("%.2f", years) + " سنة");
        } catch (Exception e) {
            textResult.setText("يرجى إدخال أرقام صحيحة!");
        }
    }
}
