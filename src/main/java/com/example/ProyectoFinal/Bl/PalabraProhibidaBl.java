package com.example.ProyectoFinal.Bl;

import com.example.ProyectoFinal.Dao.PalabraProhibidaRepository;
import com.example.ProyectoFinal.Entity.PalabraProhibida;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.List;
import java.util.regex.Pattern;

@Service
public class PalabraProhibidaBl {

    @Autowired
    private PalabraProhibidaRepository repo;

    public void validarComentario(String comentario) {
        if (comentario == null) return;

        String normalized = normalizar(comentario);

        List<String> malas = repo.findByStatusTrue().stream()
                .map(p -> normalizar(p.getPalabra()))
                .toList();

        for (String mala : malas) {
            if (mala.isBlank()) continue;

            // palabra completa (evita que "as" bloquee "casa")
            Pattern p = Pattern.compile("\\b" + Pattern.quote(mala) + "\\b", Pattern.CASE_INSENSITIVE);
            if (p.matcher(normalized).find()) {
                throw new RuntimeException("COMENTARIO_CONTIENE_LENGUAJE_NO_PERMITIDO");
            }
        }
    }

    private String normalizar(String s) {
        String t = s.toLowerCase();
        t = Normalizer.normalize(t, Normalizer.Form.NFD).replaceAll("\\p{M}", ""); // quita acentos
        t = t.replaceAll("[^a-z0-9\\s]", " "); // quita símbolos
        t = t.replaceAll("\\s+", " ").trim();
        return t;
    }
}
