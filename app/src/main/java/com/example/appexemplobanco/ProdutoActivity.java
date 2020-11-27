package com.example.appexemplobanco;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appexemplobanco.Produto;
import com.example.appexemplobanco.ProdutoDAO;
import com.example.appexemplobanco.R;

public class ProdutoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_produto);
        if (getIntent().getExtras()!=null){
            EditText edtNome = (EditText) findViewById(R.id.edtNome);
            EditText edtPreco = (EditText ) findViewById(R.id.edtPreco);
            EditText edtQuantidade = (EditText) findViewById(R.id.edtQuantidade);
            edtNome.setText(getIntent().getExtras().getString("nome"));
            edtPreco.setText(Float.toString(getIntent().getExtras().getFloat("preco")));
            edtQuantidade.setText(Integer.toString(getIntent().getExtras().getInt("quantidade")));
            if(getIntent().getExtras().getInt("id")==-1) {
                Button bt = (Button) findViewById(R.id.btnSalvar);
                edtNome.setEnabled(false);
                edtPreco.setEnabled(false);
                edtQuantidade.setEnabled(false);
                bt.setText("Voltar");
            }
        }
    }

    public void onClick(View view) {
        try{
            Button bt = (Button) findViewById(R.id.btnSalvar);
            if(!bt.getText().toString().equals("Voltar")) {
                ProdutoDAO produtoHelper = new ProdutoDAO(this);
                EditText edtNome = (EditText) findViewById(R.id.edtNome);
                EditText edtPreco = (EditText) findViewById(R.id.edtPreco);
                EditText edtQuantidade = (EditText) findViewById(R.id.edtQuantidade);
                Produto produto = new Produto();
                produto.setNome(edtNome.getText().toString());
                produto.setPreco(Float.parseFloat(edtPreco.getText().toString()));
                produto.setQuantidade(Integer.parseInt(edtQuantidade.getText().toString()));
                if(getIntent().getExtras().getInt("id")!=-1) {
                    produto.setId(getIntent().getExtras().getInt("id"));
                    produtoHelper.alterarProduto(produto);
                }else{
                    produtoHelper.inserirProduto(produto);
                }

                Toast.makeText(this, "Cadastrado com sucesso!", Toast.LENGTH_SHORT).show();
            }
            this.finish();
        }catch (Exception e){
            e.getStackTrace();
        }
    }
}