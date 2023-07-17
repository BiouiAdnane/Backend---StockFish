package com.example.aveiro_project.Web;

import com.example.aveiro_project.DTOS.EntreeDTO;
import com.example.aveiro_project.DTOS.OperationDTO;
import com.example.aveiro_project.Entities.OperationResponse;
import com.example.aveiro_project.Enums.TypeOp;
import com.example.aveiro_project.Exceptions.BlockUsed;
import com.example.aveiro_project.Exceptions.DepotMax;
import com.example.aveiro_project.Exceptions.QuantiteInsufficient;
import com.example.aveiro_project.Services.OperationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin("*")
public class OperationRestAPI {
    private OperationService operationService;
    public OperationRestAPI(OperationService operationService){
        this.operationService=operationService;
    }

    @PostMapping("/operations")
    public OperationDTO saveOperation(@RequestBody OperationDTO operationDTO) throws QuantiteInsufficient, BlockUsed {


        return operationService.saveOperation(operationDTO);
    }

    @GetMapping("/operations")
    public List<OperationDTO> getOperation(){
        return (List<OperationDTO>) operationService.getOperation();
    }

    @GetMapping("/operations/{operationId}")
    public OperationDTO getOperation(@PathVariable int operationId)  {
        return operationService.getOperationId(operationId);
        }

    @PutMapping("/operations/{operationId}")
    public  OperationDTO updateOperation(@PathVariable int operationId, @RequestBody OperationDTO operationDTO) throws QuantiteInsufficient, DepotMax, BlockUsed {
        operationDTO.setIdOperation(operationId);
        return operationService.updateOperation(operationDTO);
    }

    @DeleteMapping("/operations/{operationId}")
    public void deletePersonne(@PathVariable int operationId){
        operationService.deleteOperation(operationId);
    }

    @GetMapping("operations/user/{userId}")
    public List<OperationDTO> operationListByUserId(@PathVariable int userId){
        return operationService.listerOpByUserId(userId);
    }
    @GetMapping("/operations/day/{date}")
    public List<OperationDTO> listerOpDate(@PathVariable("date")
                                            @DateTimeFormat(pattern = "dd.MM.yyyy") Date date){
        return operationService.listerOpDate(date);
    }
    @GetMapping("/operations/emballage/entree")
    public List<OperationDTO> listOpEmbE(){
        return operationService.listerOpEmb(TypeOp.E);
    }
    @GetMapping("/operations/emballage/sortie")
    public List<OperationDTO> listOpEmbS(){
        return operationService.listerOpEmb(TypeOp.S);
    }
    @GetMapping("/operations/prfini/entree")
    public List<OperationDTO> listOpPrfE(){
        return operationService.listerOpPrf(TypeOp.E);
    }
    @GetMapping("/operations/prfini/sortie")
    public List<OperationDTO> listOpPrfS(){
        return operationService.listerOpPrf(TypeOp.S);
    }
    @GetMapping("operations/depots/articles/emb")
    public List<Object[]> articleDepotEmb(){
        return operationService.articleDepotEmb();
    }
    @GetMapping("operations/depots/articles/prf")
    public List<Object[]> articleDepotPrF(){
        return operationService.articleDepotPrf();
    }
    @GetMapping("/operations/allees/{code_Depot}")
    public List<Integer> alleesDispo(@PathVariable int code_Depot){
        return operationService.getAvailableAllee(code_Depot);
    }
    @GetMapping("/operations/allees/rangees/{code_Depot}/{allee}")
    public List<Integer> rangeesDispo(@PathVariable int code_Depot,@PathVariable int allee) {
        return operationService.getAvailableRangee(code_Depot, allee);
    }
    @GetMapping("/operations/allees/rangees/niveaux/{code_Depot}/{allee}/{rangee}")
    public List<Integer> niveauxDispo(@PathVariable int code_Depot,@PathVariable int allee,@PathVariable int rangee ){
        return operationService.getAvailableNiveau(code_Depot, allee, rangee);
    }
    @GetMapping("/operations/depots/{id}")
    public List<OperationDTO> OpDepots(@PathVariable int id){
        return operationService.listerOpDepot(id);
    }
    @GetMapping("/operations/depots/{code_Article}/{qte}")
    public EntreeDTO NbOp(@PathVariable int code_Article,
                                @PathVariable int qte){
        return operationService.nombreDop(code_Article, qte);
    }

    @GetMapping("/operations/depots/{code_Depot}/{month}/{year}")
    public Map<String, List<?>> getOperationsByDepotAndMonthAndYear(
            @PathVariable("code_Depot") int code_Depot,
            @PathVariable("month") int month,
            @PathVariable("year") int year
    ) {
        Map<String, Map<String, Integer>> operationsByDay = operationService.getTotalOperationsByDay(code_Depot, month, year);

        List<DayData> dayDataList = new ArrayList<>();

        for (Map.Entry<String, Map<String, Integer>> entry : operationsByDay.entrySet()) {
            String date = entry.getKey();
            Map<String, Integer> dayOperations = entry.getValue();

            int entries = dayOperations.getOrDefault("entrees", 0);
            int outputs = dayOperations.getOrDefault("sorties", 0);

            DayData dayData = new DayData(date, entries, outputs);
            dayDataList.add(dayData);
        }

        // Triez la liste des objets DayData en fonction des dates
        Collections.sort(dayDataList);

        List<String> dates = new ArrayList<>();
        List<Integer> entrees = new ArrayList<>();
        List<Integer> sorties = new ArrayList<>();

        for (DayData dayData : dayDataList) {
            dates.add(dayData.getDate());
            entrees.add(dayData.getEntries());
            sorties.add(dayData.getOutputs());
        }

        Map<String, List<?>> response = new HashMap<>();
        response.put("dates", dates);
        response.put("entrees", entrees);
        response.put("sorties", sorties);

        return response;
    }

    // Classe personnalisée pour représenter les données d'une journée
    class DayData implements Comparable<DayData> {
        private String date;
        private int entries;
        private int outputs;

        public DayData(String date, int entries, int outputs) {
            this.date = date;
            this.entries = entries;
            this.outputs = outputs;
        }

        public String getDate() {
            return date;
        }

        public int getEntries() {
            return entries;
        }

        public int getOutputs() {
            return outputs;
        }

        @Override
        public int compareTo(DayData other) {
            // Compare les dates pour le tri croissant
            return this.date.compareTo(other.date);
        }
    }


    @GetMapping("/operations/articles/{code_Depot}/{month}/{year}")
    public OperationResponse getArticleOperations(
            @PathVariable("code_Depot") String codeDepot,
            @PathVariable("month") int month,
            @PathVariable("year") int year) {

        return operationService.getArticleOperations(codeDepot, month, year);
    }



}
