package prova_nivell.Ex_skin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import prova_nivell.Ex_skin.entity.Skin;

@Repository
public interface SkinRepository extends JpaRepository<Skin,Long> {
}
