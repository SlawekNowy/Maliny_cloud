package pl.gmail.slawekwaga.maliny_cloud.db;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZamowieniaRepo extends PagingAndSortingRepository<Zamowienia,Integer> {
}
