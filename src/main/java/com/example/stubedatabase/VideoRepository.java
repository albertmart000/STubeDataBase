package com.example.stubedatabase;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface VideoRepository extends CrudRepository <Video, String> {
    @Transactional
    List<Video> deleteAllByUser (User user);
}
