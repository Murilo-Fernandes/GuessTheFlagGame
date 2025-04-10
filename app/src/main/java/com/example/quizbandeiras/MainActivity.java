package com.example.quizbandeiras;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    private EditText edtNome;
    private Button btnIniciar, btnOut;


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
        edtNome = findViewById(R.id.edtNome);
        btnIniciar = findViewById(R.id.btnIniciar);
        btnOut = findViewById(R.id.btnOut);

        // Desabilitar o botão "Iniciar Quiz" por padrão
        btnIniciar.setEnabled(false);

        // Habilitar o botão "Iniciar Quiz" apenas se o nome for digitado
        edtNome.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Habilitar o botão se o texto não estiver vazio
                btnIniciar.setEnabled(!s.toString().trim().isEmpty());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        // Botão "Iniciar Quiz"
        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nome = edtNome.getText().toString().trim();

                if (!nome.isEmpty()) {
                    // Iniciar o Quiz se o nome estiver preenchido
                    Intent intent = new Intent(MainActivity.this, TelaQuiz1.class);
                    intent.putExtra("NOME", nome);
                    startActivity(intent);
                } else {
                    // Fechar o aplicativo se o nome estiver vazio
                    finish();
                }
            }
        });

        // Botão "Sair"
        btnOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Fechar o aplicativo
            }
        });
    }
}
