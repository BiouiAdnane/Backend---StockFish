package com.example.aveiro_project.Web;

import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Enums.TypeOp;
import com.example.aveiro_project.Exceptions.DepotMax;
import com.example.aveiro_project.Exceptions.QuantiteInsufficient;
import com.example.aveiro_project.Services.OperationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
@RestController
@CrossOrigin("*")
public class OperationRestAPI {
    private OperationService operationService;
    public OperationRestAPI(OperationService operationService){
        this.operationService=operationService;
    }

    @PostMapping("/operations")
    public Operation saveOperation(@RequestBody Operation operation) throws QuantiteInsufficient, DepotMax {
        return operationService.saveOperation(operation);
    }

    @GetMapping("/operations")
    public List<Operation> getOperation(){
        return (List<Operation>) operationService.getOperation();
    }

    @GetMapping("/operations/{operationId}")
    public Operation getOperation(@PathVariable int operationId)  {
        return operationService.getOperationId(operationId);
    }

    @PutMapping("/operations/{operationId}")
    public  Operation updateOperation(@PathVariable int operationId, @RequestBody Operation operation) throws QuantiteInsufficient, DepotMax {
        operation.setIdOperation(operationId);
        return operationService.updateOperation(operation);
    }

    @DeleteMapping("/operations/{operationId}")
    public void deletePersonne(@PathVariable int operationId){
        operationService.deleteOperation(operationId);
    }

    @GetMapping("operations/user/{userId}")
    public List<Operation> operationListByUserId(@PathVariable int userId){
        return operationService.listerOpByUserId(userId);
    }
    @GetMapping("/operations/day/{date}")
    public List<Operation> listerOpDate(@PathVariable("date")
                                            @DateTimeFormat(pattern = "dd.MM.yyyy") Date date){
        return operationService.listerOpDate(date);
    }
    @GetMapping("/operations/emballage/entree")
    public List<Operation> listOpEmbE(){
        return operationService.listerOpEmb(TypeOp.E);
    }
    @GetMapping("/operations/emballage/sortie")
    public List<Operation> listOpEmbS(){
        return operationService.listerOpEmb(TypeOp.S);
    }
    @GetMapping("/operations/prfini/entree")
    public List<Operation> listOpPrfE(){
        return operationService.listerOpPrf(TypeOp.E);
    }
    @GetMapping("/operations/prfini/sortie")
    public List<Operation> listOpPrfS(){
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
}
