package API.nhyira.apivitalis.utils;

import java.util.Comparator;
import java.util.List;

public class ListaUtils<T> {

    private List<T> lista;

    public ListaUtils(List<T> lista) {
        this.lista = lista;
    }

    public void ordenarPorAtributo(Comparator<? super T> comparator) {
        lista.sort(comparator);
    }
}