package Model;

import java.util.Date;

/**
 * Representa uma reserva no sistema de reservas.
 * Uma reserva contém informações sobre a pessoa, origem, destino e data da viagem.
 */
public class Reserva {


    private int idReserva;
    private int idCliente;
    private String origem;
    private String destino;
    private Date dataViagem;


    public Reserva(int idCliente, String origem, int idReserva, String destino, Date dataViagem) {

        this.idCliente = idCliente;
        this.origem = origem;
        this.idReserva = idReserva;
        this.destino = destino;
        this.dataViagem = dataViagem;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }


    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public String getOrigem() {
        return origem;
    }


    public void setOrigem(String origem) {
        this.origem = origem;
    }


    public String getDestino() {
        return destino;
    }


    public void setDestino(String destino) {
        this.destino = destino;
    }


    public java.sql.Date getDataViagem() {
        return (java.sql.Date) dataViagem;
    }


    public void setDataViagem(Date dataViagem) {
        this.dataViagem = dataViagem;
    }

    @Override
    public String toString() {
        System.out.print("\nReserva:  ");
        return "\nId = " + idReserva + "\nOrigem: " + origem + "\nDestino: " + destino + "\nData de viagem: " + dataViagem;
    }
}