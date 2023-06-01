package com.example.aveiro_project.Repository;

import com.example.aveiro_project.Entities.Palette;
import com.example.aveiro_project.Enums.SizeArticle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaletteRepo extends JpaRepository<Palette,Integer> {
    Palette findPaletteBySizeArticle(SizeArticle sizeArticle);
}
