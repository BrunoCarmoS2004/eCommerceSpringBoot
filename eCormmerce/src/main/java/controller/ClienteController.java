package controller;

import java.util.List;

import javax.xml.crypto.dsig.keyinfo.RetrievalMethod;

import com.br.eCormmerce.models.Cliente;
import com.br.eCormmerce.service.ClienteService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/clientes")
public class ClienteController {
    
    @Autowired
    ClienteService clienteService;

    @GetMapping
    public List<Cliente> listarClientes(){
        return clienteService.listarClientes();
    }

    @PostMapping
    public Cliente criarCliente(@Valid @RequestBody Cliente cliente){
        return clienteService.criarCliente(cliente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizarCliente(@PathVariable Long id, @RequestBody Cliente cliente){
        if(clienteService.atualizarCliente(id, cliente) == null){

            String mensagem = "O id informado não existe";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
        }
        return ResponseEntity.ok(cliente);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletarCliente(@PathVariable Long id){
        if(clienteService.deletarCliente(id)){
            String mensagem = "Deleção realizada com sucesso";
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(mensagem);
        }
        String mensagem = "Esse id não existe";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(mensagem);
    }

    @GetMapping("/qnt-cliente")
    public int qntCliente(){
        return clienteService.qntCliente();
    }
}