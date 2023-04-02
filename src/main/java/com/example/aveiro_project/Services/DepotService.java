package com.example.aveiro_project.Services;

import com.example.aveiro_project.Entities.Article;
import com.example.aveiro_project.Entities.Depot;
import com.example.aveiro_project.Exceptions.ArticleNotFoundException;
import com.example.aveiro_project.Exceptions.DepotNotFoundException;

import java.util.List;

public interface DepotService {
    public Depot saveDepot (Depot depot);
    public List<Depot> depots();
    public Depot findDepot(int id);
    public void deleteDepot(int id);
    public Depot updateDepot(Depot depot) throws DepotNotFoundException;
}
