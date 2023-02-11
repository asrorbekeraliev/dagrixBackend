package uz.tech4ecobackend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uz.tech4ecobackend.entity.Node;
import uz.tech4ecobackend.repository.NodeRepository;
import uz.tech4ecobackend.repository.ParameterRepository;

import java.util.ArrayList;
import java.util.List;

@Service
public class NodeService {
    private final NodeRepository nodeRepository;
    private final ParameterRepository parameterRepository;

    public NodeService(NodeRepository nodeRepository, ParameterRepository parameterRepository) {
        this.nodeRepository = nodeRepository;
        this.parameterRepository = parameterRepository;
    }

    public Node create(Node node){
        Node result = nodeRepository.save(node);
        return result;
    }
    @Transactional(readOnly = true)
    public Page<Node> findAll(Pageable pageable){
        return nodeRepository.findAll(pageable);
    }
    @Transactional(readOnly = true)
    public List<Long> findAllNodeIDs(){
        List<Node> nodes = nodeRepository.findAll();
        List<Long> nodeNumbers = new ArrayList<>();
        for (int i=0; i<nodes.size(); i++){
            nodeNumbers.add(nodes.get(i).getId());
        }
        return nodeNumbers;
    }
    @Transactional(readOnly = true)
    public Node findOne(Long nodeNumber){
        Node result = nodeRepository.findById(nodeNumber).get();
        return result;
    }

    @Transactional(readOnly = true)
    public boolean nodeExist(Long nodeNumber){
        boolean result = nodeRepository.existsNodeById(nodeNumber);
        return result;
    }


    public String delete(int nodeNumber){
        String result = "";
        try{
            parameterRepository.deleteAllByNodeId(nodeNumber);
        } catch (Exception e){
            result = "catched";
        }
        nodeRepository.deleteById((long) nodeNumber);
        return result;
    }



}
