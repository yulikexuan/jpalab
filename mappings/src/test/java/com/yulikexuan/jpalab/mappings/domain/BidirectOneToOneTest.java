//: com.yulikexuan.jpalab.mappings.domain.BidirectOneToOneTest.java


package com.yulikexuan.jpalab.mappings.domain;


import com.yulikexuan.japlab.AbstractTestCase;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.*;

import javax.persistence.EntityTransaction;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;


@Slf4j
@DisplayName("Test Bidirectional One to One Association Mapping - ")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
public class BidirectOneToOneTest extends AbstractTestCase  {

    private static final String DESTINATION = "Yul";
    private static final String POST_TITLE = "Learning Java Language";

    private static UUID currentPostId;
    private static UUID currentPostDetailsId;

    @Test
    @Order(1)
    void test_One_To_One_Association() {

        // Given & When
        this.createEntities(POST_TITLE, DESTINATION);

        // Then
        assertThat(currentPostId).isEqualTo(currentPostDetailsId);
    }
    // Only ONE SELECT statement should be executed
    @Test
    @Order(2)
    void test_Fetch_Lazy_Does_Work_On_Child() {

        // Given
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        // When
        log.info(">>>>>>> Reload the Child: ");
        PostDetails postDetails = this.entityManager.find(PostDetails.class,
                currentPostDetailsId);

        // Then
        assertThat(postDetails.getDestination()).isEqualTo(DESTINATION);
        transaction.commit();
    }

    private void createEntities(String title, String destination) {

        log.info(">>>>>>> Create Entities: ");
        EntityTransaction transaction = this.entityManager.getTransaction();
        transaction.begin();

        Post post = new Post();
        post.setTitle(title);

        PostDetails postDetails = new PostDetails();
        postDetails.setDestination(destination);

        postDetails.setPost(post);

        this.entityManager.persist(post);
        this.entityManager.persist(postDetails);

        transaction.commit();

        currentPostId = post.getId();
        currentPostDetailsId = postDetails.getId();
    }

}///:~