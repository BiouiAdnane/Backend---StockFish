package com.example.aveiro_project.Services;

import com.example.aveiro_project.DTOS.OperationDTO;
import com.example.aveiro_project.Entities.Article;
import com.example.aveiro_project.Entities.Block;
import com.example.aveiro_project.Entities.Depot;
import com.example.aveiro_project.Entities.Operation;
import com.example.aveiro_project.Enums.TypeOp;
import com.example.aveiro_project.Exceptions.BlockUsed;
import com.example.aveiro_project.Exceptions.DepotMax;
import com.example.aveiro_project.Exceptions.QuantiteInsufficient;
import com.example.aveiro_project.Repository.ArticleRepository;
import com.example.aveiro_project.Repository.BlockRepo;
import com.example.aveiro_project.Repository.DepotRepository;
import com.example.aveiro_project.Repository.OperationRepository;
import com.example.aveiro_project.mappers.OperationMapperImpl;
import jakarta.persistence.PersistenceContext;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Transactional
@AllArgsConstructor
@Slf4j
@PersistenceContext

public class OperationServiceImpl implements OperationService{

    private DepotRepository depotRepository;
    private OperationRepository operationRepository;
    private ArticleRepository articleRepository;
    private OperationMapperImpl dto;
    private BlockRepo blockRepo;
    @Override
    public List<OperationDTO> getOperation() {
        List<Operation>operations= operationRepository.findAll();
        return operations.stream().map(operation -> dto.fromOperation(operation)).toList();

    }

    @Override
    public OperationDTO saveOperation(OperationDTO operationDTO) throws QuantiteInsufficient, DepotMax, BlockUsed {
        Operation operation=dto.fromOperationDTO(operationDTO);
        Block block = blockRepo.findBlockByAlleeAndRangeeAndNiveauAndDepot(operation.getAllee(), operation.getRangee(), operation.getNiveau(), operation.getDepot());
            log.info("Saving New Operation");
            operation.setN_Lot("ET"+ getNumberOfDaysSinceStartOfYear(LocalDate.now())+"C");
            operation.setDateOpertaion(new Date());
            Optional <Article>opArticle = articleRepository.findById(operation.getArticle().getCode_Article()) ;
            Optional <Depot> opDepo = depotRepository.findById(operation.getDepot().getCode_Depot());
            if(opArticle.isPresent() || opDepo.isPresent()){
                Article article = opArticle.get();
                Depot depot = opDepo.get();
                if(operation.getTypeOpr()==TypeOp.E){
                    if (operation.getQuantite()+depot.getQuantiteActuelle()>depot.getQauntiteMax())
                        throw new DepotMax("quantite max depasse");
                    if(block !=null){
                        throw new BlockUsed("cet emplacement est deja utilise");
                    }
                    Block block1=new Block();
                    block1.setAllee(operation.getAllee());
                    block1.setRangee(operation.getRangee());
                    block1.setNiveau(operation.getNiveau());
                    block1.setDepot(operation.getDepot());
                    block1.setQuantite(operation.getQuantite());
                    article.setQuantite_Article(article.getQuantite_Article()+operation.getQuantite());
                    depot.setQuantiteActuelle(depot.getQuantiteActuelle()+operation.getQuantite());
                    blockRepo.save(block1);
                } else if (operation.getTypeOpr()==TypeOp.S) {
                    if(article.getQuantite_Article()<operation.getQuantite() )
                        throw new QuantiteInsufficient("quantite inscufisante");
                    if (block==null){
                        throw new BlockUsed("impossiiiible");
                    }
                    if (block.getQuantite()==operation.getQuantite())
                        blockRepo.deleteById(block.getId_Block());
                    else if (block.getQuantite()>operation.getQuantite()){
                    block.setQuantite(block.getQuantite()-operation.getQuantite());
                    blockRepo.save(block);}
                    article.setQuantite_Article(article.getQuantite_Article()-operation.getQuantite());
                    depot.setQuantiteActuelle(depot.getQuantiteActuelle()-operation.getQuantite());
                }

            depotRepository.save(depot);
            articleRepository.save(article);
            operationRepository.save(operation);
            }
            return operationDTO;
    }
@Override
public OperationDTO updateOperation(OperationDTO operationDTO) throws QuantiteInsufficient, DepotMax, BlockUsed {
    Operation operation = dto.fromOperationDTO(operationDTO) ;
    Block block = blockRepo.findBlockByAlleeAndRangeeAndNiveauAndDepot(operation.getAllee(), operation.getRangee(), operation.getNiveau(), operation.getDepot());

    // Update operation properties
//        operation.setQuantite(operationDTO.getQuantite());
//        operation.setAllee(operationDTO.getAllee());
//        operation.setRangee(operationDTO.getRangee());
//        operation.setNiveau(operationDTO.getNiveau());
    operation.setDateOpertaion(new Date());

    // Update related entities if necessary
    if (operation.getArticle().getCode_Article() !=operationDTO.getCode_Article()) {
        log.info("Updating Operation");
        Optional<Article> opArticle = articleRepository.findById(operationDTO.getCode_Article());

                Article article = opArticle.get();

                // Update article quantities
                if (operation.getTypeOpr() == TypeOp.E) {
                    article.setQuantite_Article(article.getQuantite_Article() + operationDTO.getQuantite() - operation.getQuantite());
                } else if (operation.getTypeOpr() == TypeOp.S) {
                    if (article.getQuantite_Article() < operationDTO.getQuantite() - operation.getQuantite()) {
                        throw new QuantiteInsufficient("Quantite insuffisante");
                    }
                    article.setQuantite_Article(article.getQuantite_Article() - operationDTO.getQuantite() + operation.getQuantite());
                }
                articleRepository.save(article);
            }

        if (operation.getDepot().getCode_Depot()!=(operationDTO.getCode_Depot())) {
            Optional<Depot> opDepot = depotRepository.findById(operationDTO.getCode_Depot());

                Depot depot = opDepot.get();

                // Update depot quantities
                if (operation.getTypeOpr() == TypeOp.E) {
                    if (operationDTO.getQuantite() + depot.getQuantiteActuelle() > depot.getQauntiteMax()) {
                        throw new DepotMax("Quantite max depassee");
                    }
                    depot.setQuantiteActuelle(depot.getQuantiteActuelle() + operationDTO.getQuantite() - operation.getQuantite());
                } else if (operation.getTypeOpr() == TypeOp.S) {
                    depot.setQuantiteActuelle(depot.getQuantiteActuelle() - operationDTO.getQuantite() + operation.getQuantite());
                }
                depotRepository.save(depot);

        }

        operationRepository.save(operation);
        OperationDTO operationDTO1=dto.fromOperation(operation);
        return operationDTO1;


}

    @Override
    public OperationDTO getOperationId(int operationId) {
        Operation operation=operationRepository.findById(operationId).orElse(null);
        OperationDTO operationDTO=dto.fromOperation(operation);
        return operationDTO;
    }
/*
    @Override
    public OperationDTO updateOperation(OperationDTO operationDTO) throws QuantiteInsufficient, DepotMax {
        // baqi khassha tqad
        Operation operation=dto.fromOperationDTO(operationDTO);
        int idBefore=operationDTO.getIdOperation();
//        log.info(operation.toString());
        deleteOperation(operationDTO.getIdOperation());
        operation.setIdOperation(idBefore);
        Optional <Article>opArticle = articleRepository.findById(operation.getArticle().getCode_Article()) ;
        Optional <Depot> opDepo = depotRepository.findById(operation.getDepot().getCode_Depot());
        if(opArticle != null || opDepo != null){
            Article article = opArticle.get();
            Depot depot = opDepo.get();
            if(operation.getTypeOpr()==TypeOp.E){
                if (operation.getQuantite()+depot.getQuantiteActuelle()>depot.getQauntiteMax())
                    throw new DepotMax("quantite max depasse");
                article.setQuantite_Article(article.getQuantite_Article()+operation.getQuantite());
                depot.setQuantiteActuelle(depot.getQuantiteActuelle()+operation.getQuantite());
            } else if (operation.getTypeOpr()==TypeOp.S) {
                if(article.getQuantite_Article()<operation.getQuantite() )
                    throw new QuantiteInsufficient("quantite inscufisante");
                article.setQuantite_Article(article.getQuantite_Article()-operation.getQuantite());
                depot.setQuantiteActuelle(depot.getQuantiteActuelle()-operation.getQuantite());
            }
            operation.setDateOpertaion(new Date());
            articleRepository.save(article);
            depotRepository.save(depot);
        }
        operation.setN_Lot(operationDTO.getN_Lot());
//        operation.setIdOperation(idBefore);
        operationRepository.save(operation);
        OperationDTO operationDTO1=dto.fromOperation(operation);
        return operationDTO1;
    }
*/

    @Override
    public void deleteOperation(int operationId) {
        Operation operation=dto.fromOperationDTO(getOperationId(operationId));
        Article article = operation.getArticle();
        if (operation.getTypeOpr()==TypeOp.E)
            article.setQuantite_Article(article.getQuantite_Article()-operation.getQuantite());
        else if (operation.getTypeOpr()==TypeOp.S) {
            article.setQuantite_Article(article.getQuantite_Article()+operation.getQuantite());
        }
        articleRepository.save(article);
        operationRepository.deleteById(operationId);
        //khassha tjarab
    }

    @Override
    public List<OperationDTO> listerOpByUserId(int userId) {
        List<Operation> operations= operationRepository.findOperationsByPersonne_Matriculation(userId);
        return operations.stream().map(op->dto.fromOperation(op)).toList();
    }

    @Override
    public List<OperationDTO> listerOpDate(Date date) {
        List<Operation> operations= operationRepository.findOperationByDateOpertaionEquals(date) ;
        return operations.stream().map(op->dto.fromOperation(op)).toList();
    }

    @Override
    public List<OperationDTO> listerOpPeriode(Date dateDebut, Date dateFin) {
        List<Operation> operations=operationRepository.findOperationsByDateOpertaionIsBetween(dateDebut,dateFin) ;
        return operations.stream().map(op->dto.fromOperation(op)).toList();
    }

    @Override
    public List<OperationDTO> listerOpEmb(TypeOp typeOp) {
        Depot depot = depotRepository.getReferenceById(1);
        List<Operation>operations=operationRepository.findByTypeOprAndDepot(typeOp,depot);
        return operations.stream().map(op->dto.fromOperation(op)).toList();
    }

    @Override
    public List<OperationDTO> listerOpPrf(TypeOp typeOp) {
        Depot depot = depotRepository.getReferenceById(2);
        List<Operation> operations= operationRepository.findByTypeOprAndDepot(typeOp,depot);
        return operations.stream().map(op->dto.fromOperation(op)).toList() ;
    }

    @Override
    public List<Object[]> articleDepotEmb() {
        log.info("all articles");
        return operationRepository.finddipoemb() ;
    }

    @Override
    public List<Object[]> articleDepotPrf() {
        return operationRepository.finddipoprf();
    }


    public static long getNumberOfDaysSinceStartOfYear(LocalDate date) {

        LocalDate startOfYear = LocalDate.of(date.getYear(), 1, 1);


        return date.toEpochDay() - startOfYear.toEpochDay()+1;
    }
    public boolean disponibleBlock(int allee,int rangee,int niveau,Depot depot){
        List<Operation> operations = operationRepository.findAllByAlleeAndRangeeAndNiveauAndDepotEquals(allee, rangee, niveau,depot);
        return operations.isEmpty();
    }
    @Override
    public List<Integer> getAvailableAllee(int code_Depot) {
        Depot depot=depotRepository.findById(code_Depot).orElse(null) ;
        List<Integer> allees = IntStream.rangeClosed(1, depot.getNbrMaxAllee())
                .boxed()
                .collect(Collectors.toList());
        List<Operation> operations = depot.getOperations();

        for (Operation operation : operations) {
            int allee = operation.getAllee();
            if (allees.contains(allee)) {
                List<Integer> rangees = getAvailableRangee(code_Depot, allee);
                if (rangees.isEmpty()) {
                    allees.remove(Integer.valueOf(allee));
                }
            }
        }
        return allees;
    }
    @Override
    public List<Integer> getAvailableRangee(int code_Depot, int allee) {
        Depot depot=depotRepository.findById(code_Depot).orElse(null);
        List<Integer> rangees = IntStream.rangeClosed(1, depot.getNbrMaxRangee())
                .boxed()
                .collect(Collectors.toList());
        List<Operation> operations = depot.getOperations();

        for (Operation operation : operations) {
            int operationAllee = operation.getAllee();
            int operationRangee = operation.getRangee();
            if (operationAllee == allee && rangees.contains(operationRangee)) {
                List<Integer> niveaux = getAvailableNiveau(code_Depot, allee, operationRangee);
                if (niveaux.isEmpty()) {
                    rangees.remove(Integer.valueOf(operationRangee));
                }
            }
        }
        return rangees;
    }
    @Override
    public List<Integer> getAvailableNiveau(int code_Depot, int allee, int rangee) {
        Depot depot=depotRepository.findById(code_Depot).orElse(null) ;
        List<Integer> niveaux = IntStream.rangeClosed(1, depot.getNbrMaxNiveau())
                .boxed()
                .collect(Collectors.toList());
        List<Operation> operations = depot.getOperations();

        for (Operation operation : operations) {
            int operationAllee = operation.getAllee();
            int operationRangee = operation.getRangee();
            int operationNiveau = operation.getNiveau();
            if (operationAllee == allee && operationRangee == rangee && niveaux.contains(operationNiveau)) {
                niveaux.remove(Integer.valueOf(operationNiveau));
            }
        }
        return niveaux;
    }

    @Override
    public List<OperationDTO> listerOpDepot(int id) {
        Depot depot=depotRepository.findById(id).orElse(null) ;
        List<Operation> operations= operationRepository.findOperationsByDepot(depot);
        return operations.stream().map(op->dto.fromOperation(op)).toList();
    }

    public boolean blockExists(Block block) {
        Optional<Block> existingBlock = blockRepo.findById(block.getId_Block());
        return existingBlock.isPresent();
    }


}
