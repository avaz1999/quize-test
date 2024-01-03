package com.example.quiztest.project.repositories;

import com.example.quiztest.project.entity.Question;
import com.example.quiztest.project.projection.GetQuesrionForQuiz;
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


    @Query(nativeQuery = true, value = """
        SELECT q.id AS id,
                       q.title AS title,
                       q.difficulty AS difficulty
                       FROM question q
                JOIN category c ON q.category_id = c.id
                WHERE (:category IS NULL OR LOWER(c.name) LIKE LOWER(CONCAT('%', :category, '%')))
                                                            AND q.deleted = FALSE
                                                            AND c.deleted = FALSE
                                                          ORDER BY RANDOM()
                                                          LIMIT :pageSize
""")
    List<GetQuesrionForQuiz> getForQuiz(@Param("category") String category, @Param("pageSize") Short pageSize);

}
