package com.example.appsqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper db;

    EditText txtEmail, txtSenha, txtConfirmaSenha, txtNome, txtCPF, txtTelefone, txtRG;
    Button btnRegistar, btnLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        txtNome = findViewById(R.id.idNome);
        txtTelefone = findViewById(R.id.idTelefone);
        txtCPF = findViewById(R.id.idCPF);
        txtEmail = findViewById(R.id.idEmail);
        txtSenha = findViewById(R.id.idSenha);
        txtRG = findViewById(R.id.idRG);
        txtConfirmaSenha = findViewById(R.id.idConfirmaSenha);

        btnLogin = findViewById(R.id.idBtnLogin);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

        btnRegistar = findViewById(R.id.idBtnRegistrar);

        btnRegistar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, senha, confirmaSenha, nome, cpf, telefone, rg;


                email = txtEmail.getText().toString();
                senha = txtSenha.getText().toString();
                confirmaSenha = txtConfirmaSenha.getText().toString();
                nome = txtNome.getText().toString();
                cpf = txtCPF.getText().toString();
                telefone = txtTelefone.getText().toString();
                rg = txtRG.getText().toString();

                if (email.equals("") || senha.equals("") || confirmaSenha.equals("") || nome.equals("") || cpf.equals("") || telefone.equals("") || rg.equals("")) {
                    Toast.makeText(getApplicationContext(), "Favor inserir valores!!", Toast.LENGTH_SHORT).show();
                } else {
                    if (senha.equals(confirmaSenha)) {
                        Boolean checharEmail = db.validarEmail(email);
                        if (checharEmail == true) {
                            Boolean inserir = db.insert(email, senha, nome, cpf, rg, telefone);
                            if (inserir == true) {
                                Toast.makeText(getApplicationContext(), "Registro inserido com sucesso!!!", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            Toast.makeText(getApplicationContext(), "Email inserido já existe!!", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(getApplicationContext(), "Senha não confere!!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });
    }

}
