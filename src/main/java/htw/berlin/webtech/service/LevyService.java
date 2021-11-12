package htw.berlin.webtech.service;

import htw.berlin.webtech.persistence.LevyEntity;
import htw.berlin.webtech.persistence.LevyRepository;
import htw.berlin.webtech.web.api.Levy;
import htw.berlin.webtech.web.api.LevyManipulationRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LevyService {

    private final LevyRepository levyRepository;

    public LevyService(LevyRepository levyRepository) {
        this.levyRepository = levyRepository;
    }

    public List<Levy> findAll() {
        List<LevyEntity> levies = levyRepository.findAll();
        return levies.stream()
                .map(this::transformEntity)
                .collect(Collectors.toList());
    }

    public Levy findById(Long id) {
        var levyEntity = levyRepository.findById(id);
        return levyEntity.isPresent()? transformEntity(levyEntity.get()) : null;
    }

    public Levy create(LevyManipulationRequest request) {
        var levyEntity = new LevyEntity(
                request.getTitle(),
                request.getDiscription(),
                request.getModul(),
                request.getDeadline(),
                request.getImportance()
        );
        levyEntity = levyRepository.save(levyEntity);
        return transformEntity(levyEntity);
    }

    public Levy update(Long id, LevyManipulationRequest request) {
        var levyEntityOptional = levyRepository.findById(id);
        if(levyEntityOptional.isEmpty()) return null;

        var levyEntity = levyEntityOptional.get();
        levyEntity.setTitle(request.getTitle());
        levyEntity.setDiscription(request.getDiscription());
        levyEntity.setModul(request.getModul());
        levyEntity.setDeadline(request.getDeadline());
        levyEntity.setImportance(request.getImportance());
        levyEntity = levyRepository.save(levyEntity);

        return transformEntity(levyEntity);
    }

    private Levy transformEntity(LevyEntity levyEntity) {
        return new Levy(
                levyEntity.getId(),
                levyEntity.getTitle(),
                levyEntity.getDiscription(),
                levyEntity.getModul(),
                levyEntity.getDeadline(),
                levyEntity.getImportance()
        );
    }
}
