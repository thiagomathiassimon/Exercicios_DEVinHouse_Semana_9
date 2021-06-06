package br.com.devinhose.dao;

public interface IDao<T> {

    public void inserir(T objeto);

    public void alterar(T objeto);

}
