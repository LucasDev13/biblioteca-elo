package br.com.elotech.biblioteca_elo.domain.entities;

import java.util.List;

public record VolumeInfo(
        String title,
        List<String> authors
) {
}
