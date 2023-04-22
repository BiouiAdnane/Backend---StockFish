package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Ingredient;
import com.example.aveiro_project.Entities.Nature;

import java.util.List;

public interface NatureService {
    public List<Nature> listerNatures();
    public Nature saveNature(Nature nature);
    public Nature updateNature(Nature nature);
    public void deleteNature(int id);
    List<Nature> searchNature(String keyword);

}
