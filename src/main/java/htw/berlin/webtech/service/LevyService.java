package htw.berlin.webtech.service;

import htw.berlin.webtech.persistence.LevyEntity;
import htw.berlin.webtech.persistence.LevyRepository;
import htw.berlin.webtech.web.api.Levy;
import htw.berlin.webtech.web.api.LevyCreateRequest;
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

    public Levy create(LevyCreateRequest request) {
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
