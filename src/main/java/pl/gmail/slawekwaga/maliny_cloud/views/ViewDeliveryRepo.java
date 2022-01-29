package pl.gmail.slawekwaga.maliny_cloud.views;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViewDeliveryRepo  extends PagingAndSortingRepository<ViewDelivery,Integer> {
}
