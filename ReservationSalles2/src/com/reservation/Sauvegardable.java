package com.reservation;

import java.util.List;

// Interface générique → Binôme 2
public interface Sauvegardable<T> {
    void sauvegarder(List<T> elements, String chemin) throws Exception;
    List<T> charger(String chemin) throws Exception;
}
