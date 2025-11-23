package model;

public class Libro {
    private int id;
    private String titulo;
    private int ISBN;

    public Libro() {}
    public Libro(int id, String titulo,  int ISBN) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }

    public void setId(int id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getISBN() { return ISBN; }
    public void setISBN(int ISBN) { this.ISBN = ISBN; }

    @Override
    public String toString() {
        return "ID=" + id + ", Título='" + titulo + "'" +  ", ISBN='" + ISBN + "'";
    }
}
