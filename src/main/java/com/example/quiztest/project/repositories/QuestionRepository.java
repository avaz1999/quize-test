package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuestionRepository extends BaseRepository<Question> {
    Integer countAllByCategoryIdAndDeletedFalse(Long categoryId);
    @Query(value = """

            select q
                  from Question q
                  join Category c on q.category.id = c.id
                  where (
                          :categoryName is null
                           or lower(c.name) like lower(concat('%', :categoryName, '%'))
                                       )
                           and q.deleted = false
                           and c.deleted = false""")
    Page<Question> findAllByFilter(@Param("categoryName") String categoryName, Pageable pageable);


@Query(nativeQuery = true,value = """
        select q
                from question q
                join category  c on q.category_id = c.id
                where (:category is null
                        or lower(c.name) like lower(concat('%',:category,'%')))
                        and q.deleted = false and c.deleted = false
                        order by random() limit :pageSize""")
    List<Question> getForQuiz(@Param("category") String category,@Param("pageSize") Short pageSize);
}
