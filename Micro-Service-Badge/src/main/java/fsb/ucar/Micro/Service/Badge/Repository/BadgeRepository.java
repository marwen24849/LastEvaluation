package fsb.ucar.Micro.Service.Badge.Repository;

import fsb.ucar.Micro.Service.Badge.entity.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BadgeRepository extends JpaRepository<Badge, Long> {
}
