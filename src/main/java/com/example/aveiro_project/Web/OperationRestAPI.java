package com.example.aveiro_project.Web;

import com.example.aveiro_project.DTOS.OperationDTO;
import com.example.aveiro_project.Enums.TypeOp;
import com.example.aveiro_project.Exceptions.BlockUsed;
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
    public OperationDTO saveOperation(@RequestBody OperationDTO operationDTO) throws QuantiteInsufficient, DepotMax, BlockUsed {
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
    public  OperationDTO updateOperation(@PathVariable int operationId, @RequestBody OperationDTO operationDTO) throws QuantiteInsufficient, DepotMax {
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
}
