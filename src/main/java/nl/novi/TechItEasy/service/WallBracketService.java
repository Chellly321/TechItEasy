package nl.novi.TechItEasy.service;

import nl.novi.TechItEasy.exceptions.RecordNotFoundException;
import nl.novi.TechItEasy.models.WallBracket;
import nl.novi.TechItEasy.repositories.WallBracketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WallBracketService {
    private WallBracketRepository wallBracketRepository;

    @Autowired
    public WallBracketService(WallBracketRepository wallBracketRepository){
        this.wallBracketRepository = wallBracketRepository;
    }
    public List<WallBracket> getAllWallBrackets() {
        return wallBracketRepository.findAll();
    }
    public WallBracket getWallBracket(Long id){
        Optional<WallBracket> wallBracket = wallBracketRepository.findById(id);
        if (wallBracket.isPresent()){
            return wallBracket.get();
        }else {
            throw new RecordNotFoundException("No wallbracket found");
        }
    }
    public WallBracket addWallBracket(WallBracket wallBracket){
        return wallBracketRepository.save(wallBracket);
    }
    public void deleteWallBracket(Long id){
        wallBracketRepository.deleteById(id);
    }
    public void updateWallBracket(Long id, WallBracket wallBracket){
        if (!wallBracketRepository.existsById(id)){
            throw new RecordNotFoundException("No wallbracket found");
        }
        WallBracket storedWallBracket = wallBracketRepository.findById(id).orElse(null);
        storedWallBracket.setId(wallBracket.getId());
        storedWallBracket.setPrice(wallBracket.getPrice());
        storedWallBracket.setName(wallBracket.getName());
        storedWallBracket.setAjustable(wallBracket.getAjustable());
        storedWallBracket.setSize(wallBracket.getSize());
        wallBracketRepository.save(storedWallBracket);
    }
}
