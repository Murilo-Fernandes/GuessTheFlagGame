package com.example.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class TelaFinal extends AppCompatActivity {

    private TextView txtUsuario, txtTotalAcertos;
    private Button btnAgain,btnMainPage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tela_final);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtUsuario = findViewById(R.id.txtUsuario);
        txtTotalAcertos = findViewById(R.id.txtTotalAcertos);
        btnAgain = findViewById(R.id.btnAgain);
        btnMainPage = findViewById(R.id.btnMainPage);

        Intent intent = getIntent();
        String userName = intent.getStringExtra("NOME");
        int acertos = intent.getIntExtra("acertos", 0);

        txtUsuario.setText("Usuário: " + userName);
        txtTotalAcertos.setText(String.valueOf(acertos)); // Corrigido para converter int em String

        btnAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaFinal.this, TelaQuiz1.class);
                intent.putExtra("NOME", userName);
                startActivity(intent);
                finish();
            }
        });

        btnMainPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TelaFinal.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}

