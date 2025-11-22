package model;

public class Libro {
    private int id;
    private String titulo;
    private int ISBN;
    private String autor;

    public Libro() {}
    public Libro(int id, String titulo,  int ISBN, String autor) {
        this.id = id;
        this.titulo = titulo;
    }

    public int getId() { return id; }
    public String getTitulo() { return titulo; }

    public void setId(int id) { this.id = id; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public int getISBN() { return ISBN; }
    public void setISBN(int ISBN) { this.ISBN = ISBN; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }


    @Override
    public String toString() {
        return "ID=" + id + ", Título='" + titulo + "'" +  ", ISBN='" + ISBN + "'" + ", Autor='" + autor + "'";
    }
}
