package com.sylviane.banking.repository;

import com.sylviane.banking.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    //select * from users where firstname = ali;

    //List<User> findAllByFirstname(String firstname);//chercher la liste des utilisateurs sur leurs prenoms

    //select * from users where firstname like "%ali%";

    //List<User> findAllByAccountContaining(String firstname);

    //select * from users u inner join account ac on u.id_user=ac.id_user and ac.iban="FR0101225026";

    //List<User> findAllByAccountIban(String iban);

    //select * from users where firstname = "%ali%" and email = "t.tata@gmail.com";
    /*User findByFirstnameContainingIgnoreCaseAndEmail(String firstname, String email);

    @Query("from User where firstname = :firstname")//annotation pour implementer les methodes de recherche
    List<User> searchByFirstname(String firstname);*/
    //ou
    //List<User> searchByFirstname(@Param("firstname") String firstname);

    //select * from users where firstname like "%ali%";
    /*@Query("from User where firstname = '%:fn%'")//fn=firstname
    List<User> searchByFirstnameContaining(@Param("fn") String firstname);*/
    //@Query("from User u inner join Account ac on u.id_user=ac.user.id_user where ac.iban = :iban")

    /*@Query("from User u inner join Account ac on u.id=ac.user.id where ac.iban = :iban")
    List<User> searchByIban(String iban);*/

    //utilisation d'une requete native
    //@Query(value = "select * from users u inner join account ac on u.id_user=ac.id_user and ac.iban = :iban", nativeQuery = true)

    /*@Query(value = "select * from users u inner join account ac on u.id=ac.id and ac.iban = :iban", nativeQuery = true)
    List<User> searchByIbanNative(String iban);*/

    Optional<User> findByEmail(String email);
}
