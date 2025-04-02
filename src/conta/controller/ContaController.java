package conta.controller;

import java.util.ArrayList;

import conta.model.Conta;
import conta.repository.ContaRepository;

public class ContaController implements ContaRepository {

    private ArrayList<Conta> listaContas = new ArrayList<Conta>();
    int numero = 0;

    @Override
    public void procurarPorNumero(int numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'procurarPorNumero'");
    }

    @Override
    public void listarTodas() {
        for (var conta : listaContas) {
            conta.visualizar();
        }
    }

    @Override
    public void cadastrar(Conta conta) {
        listaContas.add(conta);
        System.out.println("\nA conta número " + conta.getNumero() + " foi criada com sucesso!");
    }

    @Override
    public void atualizar(Conta conta) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'atualizar'");
    }

    @Override
    public void deletar(int numero) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deletar'");
    }

    @Override
    public void sacar(int numero, float valor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'sacar'");
    }

    @Override
    public void depositar(int numero, float valor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'depositar'");
    }

    @Override
    public void transferir(int numeroOrigem, int numeroDestino, float valor) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'transferir'");
    }

    public int gerarNumero() {
        return ++ numero;
    }
}
