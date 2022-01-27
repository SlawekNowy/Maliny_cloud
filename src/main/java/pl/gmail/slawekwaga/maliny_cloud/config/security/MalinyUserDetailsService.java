package pl.gmail.slawekwaga.maliny_cloud.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.gmail.slawekwaga.maliny_cloud.config.security.internal.MalinyUser;
import pl.gmail.slawekwaga.maliny_cloud.config.security.internal.MalinyUserDetails;
import pl.gmail.slawekwaga.maliny_cloud.config.security.internal.MalinyUserRepo;

@Service
public class MalinyUserDetailsService implements UserDetailsService {

    private MalinyUserRepo malinyUserRepo;

    @Autowired
    public void setMalinyUserRepo(MalinyUserRepo malinyUserRepo) {
        this.malinyUserRepo = malinyUserRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MalinyUser user = malinyUserRepo.getUserByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("Nie znaleziono użytkownika");
        }

        return new MalinyUserDetails(user);
    }
}
