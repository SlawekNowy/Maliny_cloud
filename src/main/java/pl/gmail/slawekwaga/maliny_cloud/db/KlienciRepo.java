package pl.gmail.slawekwaga.maliny_cloud.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface KlienciRepo extends CrudRepository<Klienci,Integer> {
}
