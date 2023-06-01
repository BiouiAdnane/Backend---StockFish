package com.example.aveiro_project.Web;

import com.example.aveiro_project.Entities.Palette;
import com.example.aveiro_project.Services.PaletteService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController @AllArgsConstructor
public class PaletteRestApi {
    private PaletteService service;
    @GetMapping("/palettes")
    public List<Palette> listPalettes(){
        return service.getPalettes();
    }
    @PutMapping("/palettes")
    public Palette updatePalettes(@RequestBody Palette palette){
        return service.updatePalette(palette) ;
    }
}
