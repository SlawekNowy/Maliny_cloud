package pl.gmail.slawekwaga.maliny_cloud.config.security.internal;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MalinyUserRepo extends CrudRepository<MalinyUser,String> {

    public MalinyUser getUserByUsername(String username);
}
