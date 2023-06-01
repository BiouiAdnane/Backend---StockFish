package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Palette;
import com.example.aveiro_project.Repository.PaletteRepo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class PaletteServiceImpl implements PaletteService {
    private PaletteRepo repo;
    @Override
    public List<Palette> getPalettes() {

        return repo.findAll();
    }

    @Override
    public Palette updatePalette(Palette palette) {
        return repo.save(palette) ;
    }
}
