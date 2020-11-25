package com.example.appexemplobanco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class ProdutoActivity extends AppCompatActivity {
    private ProdutoDAO produtoHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
    }

    public void onClick(View view) {
        try{
            produtoHelper = new ProdutoDAO(this);
            EditText edtNome = (EditText) findViewById(R.id.edtNome);
            EditText edtPreco = (EditText ) findViewById(R.id.edtPreco);
            EditText edtQuantidade = (EditText) findViewById(R.id.edtQuantidade);
            Produto produto = new Produto();
            produto.setNome(edtNome.getText().toString());
            produto.setPreco(Float.parseFloat(edtPreco.getText().toString()));
            produto.setQuantidade(Integer.parseInt(edtQuantidade.getText().toString()));
            produtoHelper.inserirProduto(produto);

            Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            this.finish();
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}