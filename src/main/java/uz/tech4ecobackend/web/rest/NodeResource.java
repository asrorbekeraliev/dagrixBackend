package uz.tech4ecobackend.web.rest;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.tech4ecobackend.entity.Node;
import uz.tech4ecobackend.service.NodeService;

//@CrossOrigin(origins = "http://dottore.uz/")
@RestController
@RequestMapping("/api/node")
public class NodeResource {
    private final NodeService nodeService;


    public NodeResource(NodeService nodeService) {
        this.nodeService = nodeService;
    }

    @PostMapping("/register")
    public ResponseEntity nodeRegister(@RequestBody Node node){
            return ResponseEntity.ok(nodeService.create(node));
    }

    @GetMapping("/get")
    public ResponseEntity<?> getAll(Pageable pageable){
        Page<Node> result = nodeService.findAll(pageable);
        return ResponseEntity.ok(result);
    }


    @GetMapping("/get/NodeNumbers")
    public ResponseEntity getNodeNumbers(){
        return ResponseEntity.ok(nodeService.findAllNodeIDs());
    }


    @GetMapping("/{nodeNumber}")
    public ResponseEntity findOne(@PathVariable Long nodeNumber){
        Node node = nodeService.findOne(nodeNumber);
        return ResponseEntity.ok(node);
    }


    @DeleteMapping("/{nodeNumber}")
    public ResponseEntity delete(@PathVariable int nodeNumber){
        nodeService.delete(nodeNumber);
        return ResponseEntity.ok("Node " + nodeNumber + " Deleted successfully");
    }
}
