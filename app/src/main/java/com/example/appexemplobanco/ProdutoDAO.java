package com.example.appexemplobanco;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO extends SQLiteOpenHelper {
    private static final String DATABASE = "AppBanco";
    private static final int VERSION = 1;
    private static final String TABELA_PRODUTO = "produto";

    public ProdutoDAO(@Nullable Context context) {
        super(context, DATABASE, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String ddl = "CREATE TABLE IF NOT EXISTS "+ TABELA_PRODUTO +
                "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, " +
                "nome TEXT, " +
                "preco REAL , " +
                "quantidade INTEGER);";
        db.execSQL(ddl);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void inserirProduto(Produto produto){
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("preco", produto.getPreco());
        values.put("quantidade", produto.getQuantidade());
        getWritableDatabase().insert(TABELA_PRODUTO, null, values);
    }

    public void apagarProduto(Produto produto){
        String args[] = {Integer.toString(produto.getId())};
        getWritableDatabase().delete(TABELA_PRODUTO, "id=?", args);
    }

    public void alterarProduto(Produto produto){
        ContentValues values = new ContentValues();
        values.put("nome", produto.getNome());
        values.put("preco", produto.getPreco());
        values.put("quantidade", produto.getQuantidade());
        String args[] = {Integer.toString(produto.getId())};
        getWritableDatabase().update(TABELA_PRODUTO, values, "id=?", args);
    }

    public Cursor getCursorListaProduto(){
        List<Produto> lista = new ArrayList<Produto>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA_PRODUTO + ";", null);
        return cursor;
    }

    public List<Produto> getListaProduto(){
        List<Produto> lista = new ArrayList<Produto>();
        Cursor cursor = getReadableDatabase().rawQuery("SELECT * FROM " + TABELA_PRODUTO + ";", null);
        cursor.moveToFirst();
        while (cursor.moveToNext()){
            Produto produto = new Produto();
            produto.setId(cursor.getInt(cursor.getColumnIndex("id")));
            produto.setNome(cursor.getString(cursor.getColumnIndex("nome")));
            produto.setPreco(cursor.getFloat(cursor.getColumnIndex("preco")));
            produto.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
            lista.add(produto);
        }
        cursor.close();
        return lista;
    }

    public Produto getProdutoByID(int id){
        Cursor cursor = getReadableDatabase().query(TABELA_PRODUTO, new String[]{"id","nome","preco","quantidade"}, "id=" + id, null,null, null, null);
        cursor.moveToFirst();
        Produto produto = new Produto();
        produto.setId(cursor.getInt(cursor.getColumnIndex("id")));
        produto.setNome(cursor.getString(cursor.getColumnIndex("nome")));
        produto.setPreco(cursor.getFloat(cursor.getColumnIndex("preco")));
        produto.setQuantidade(cursor.getInt(cursor.getColumnIndex("quantidade")));
        cursor.close();
        return produto;
    }



}
