package com.example.demo.Service;

import com.example.demo.Model.Client;
import com.example.demo.Model.ClientDTO;
import com.example.demo.Repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    private final ClientRepository clientRepository;

    public ClientService(ClientRepository clientRepository) {
        this.clientRepository = clientRepository;
    }

    public ClientDTO getClientById(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            return mapToDTO(client);
        }
        return null;
    }

    public ClientDTO createClient(ClientDTO clientDTO) {
        Client client = mapToEntity(clientDTO);
        Client createdClient = clientRepository.save(client);
        return mapToDTO(createdClient);
    }

    public ClientDTO updateClient(Long id, ClientDTO clientDTO) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            Client client = clientOptional.get();
            client.setNombre(clientDTO.getNombre());
            client.setApellido(clientDTO.getApellido());
            client.setNumeroDocumento(clientDTO.getNumeroDocumento());
            Client updatedClient = clientRepository.save(client);
            return mapToDTO(updatedClient);
        }
        return null;
    }

    public boolean deleteClient(Long id) {
        Optional<Client> clientOptional = clientRepository.findById(id);
        if (clientOptional.isPresent()) {
            clientRepository.delete(clientOptional.get());
            return true;
        }
        return false;
    }

    private ClientDTO mapToDTO(Client client) {
        return new ClientDTO(client.getId(), client.getNombre(), client.getApellido(), client.getNumeroDocumento());
    }

    private Client mapToEntity(ClientDTO clientDTO) {
        return new Client(clientDTO.getNombre(), clientDTO.getApellido(), clientDTO.getNumeroDocumento());
    }
}

