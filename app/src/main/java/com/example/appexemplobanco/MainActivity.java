package com.example.appexemplobanco;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ProdutoDAO produtoHelper;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Button btNovo = (Button) findViewById(R.id.btCadastrar);
        btNovo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ProdutoActivity.class));
            }
        });*/
    }

    @Override
    protected void onResume(){
        super.onResume();
        carregarListView();
    }

    public void carregarListView(){
        produtoHelper = new ProdutoDAO(this);
        listView = (ListView) findViewById(R.id.listProdutos);
        List<Produto> produtos = produtoHelper.getListaProduto();

        ArrayAdapter<Produto> adaptador = new ArrayAdapter<Produto>(this, android.R.layout.simple_list_item_checked, produtos);
        listView.setAdapter(adaptador);

        if (produtos.size() == 0){
            Toast.makeText(this, "Nenhum registro encontrado", Toast.LENGTH_SHORT).show();
        }
    }

    public void btExcluir(View view){
        String selecionados = "";
        //Cria um array com os itens selecionados no listview
        SparseBooleanArray checked = listView.getCheckedItemPositions();
        for (int i = 0; i < checked.size(); i++){
            Produto produto = (Produto) listView.getItemAtPosition(checked.keyAt(i));
            //pega os itens marcados
            produtoHelper.apagarProduto(produto);
        }
        Toast.makeText(this, "Produto Apagado com Sucesso!", Toast.LENGTH_LONG).show();
        carregarListView();
    }

    public void onCLickNovo(View view) {
        startActivity(new Intent(this, ProdutoActivity.class));
    }
}