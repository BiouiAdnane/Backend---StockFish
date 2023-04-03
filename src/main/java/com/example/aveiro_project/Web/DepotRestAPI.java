package com.example.aveiro_project.Web;
import com.example.aveiro_project.Entities.Depot;
import com.example.aveiro_project.Exceptions.DepotNotFoundException;
import com.example.aveiro_project.Services.DepotService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class DepotRestAPI {
    private DepotService depotService;
    @GetMapping("/depots")
    public List<Depot> depots(){
        return depotService.depots();
    }
    @GetMapping("/depots/{id}")
    public Depot getDepot(@PathVariable int id) throws DepotNotFoundException {
        return depotService.findDepot(id);
    }
    @PostMapping("/depots")
    public Depot saveDepot(@RequestBody Depot depot){
        return depotService.saveDepot(depot);
    }
    @PutMapping("/depots/{id}")
    public Depot updateDepot(@PathVariable int id,@RequestBody Depot depot) throws DepotNotFoundException {
        depot.setCode_Depot(id);
        return depotService.updateDepot(depot);
    }
    @DeleteMapping("/depots/{id}")
    public void deleteDepot(@PathVariable int id){
        depotService.deleteDepot(id);
    }
}
