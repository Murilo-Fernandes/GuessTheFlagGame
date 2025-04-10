package com.example.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaQuiz1 extends AppCompatActivity {

    private Button btnAnswer;
    private RadioGroup rgOptions;
    private RadioButton rbRight, rbWrong1, rbWrong2, rbWrong3;
    private int acertos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_quiz1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        rgOptions = findViewById(R.id.rgOptions);
        btnAnswer = findViewById(R.id.btnAnswer);

        btnAnswer.setEnabled(false);

        Intent intent = getIntent();
        acertos = intent.getIntExtra("acertos", 0);

        rgOptions.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                btnAnswer.setEnabled(true);
            }
        });

        btnAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = rgOptions.getCheckedRadioButtonId();
                RadioButton radioButton = findViewById(selectedId);

                if(radioButton.getId() == R.id.rbRight) {
                    acertos++;
                    Toast.makeText(TelaQuiz1.this, "Resposta correta!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(TelaQuiz1.this, "Resposta errada!", Toast.LENGTH_SHORT).show();
                }
                // Passa para a próxima tela (ou finaliza o quiz)
                Intent it = new Intent(TelaQuiz1.this, TelaQuiz2.class);
                it.putExtra("NOME", getIntent().getStringExtra("NOME")); // Passa o nome do usuário
                it.putExtra("acertos", acertos); //Passa a pontuação
                startActivity(it);
                finish(); // Finaliza a atividade atual para impedir o retorno
            }
        });
    }
    @Override
    public boolean onSupportNavigateUp() {
        // Redireciona o usuário para a MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
        return true;
}
}