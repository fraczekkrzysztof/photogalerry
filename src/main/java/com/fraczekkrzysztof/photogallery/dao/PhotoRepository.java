package com.fraczekkrzysztof.photogallery.dao;

import com.fraczekkrzysztof.photogallery.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface PhotoRepository extends JpaRepository<Photo,Long> {

    @Query("select p from Photo p join p.gallery g where g.username = :username")
    List<Photo> findPhotosByUserName(@Param("username") String username);
}
