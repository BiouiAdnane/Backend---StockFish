package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Palette;

import java.util.List;

public interface PaletteService {
    List<Palette> getPalettes();
    Palette updatePalette(Palette palette);
}
