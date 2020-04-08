package br.com.ErrorCenter.repositories;

import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.entities.EventEntity;
import br.com.ErrorCenter.enums.LevelEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import java.time.LocalDateTime;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EventTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private EventRepository eventRepository;

    private LocalDateTime dateTimeTest;

    private ApplicationEntity app1 = new ApplicationEntity.Builder()
            .withName("conta azul")
            .withEmail("conta@conta.com")
            .withPassword("12345678")
            .build();

    private EventEntity event1 = new EventEntity.Builder()
            .withDescription("descricao evento 1")
            .withLog("log do evento 1")
            .withLevel(LevelEnum.INFO)
            .withOrigin(app1)
            .build();

    private EventEntity event2 = new EventEntity.Builder()
            .withDescription("descricao evento 2")
            .withLog("log do evento 2")
            .withLevel(LevelEnum.ERROR)
            .withOrigin(app1)
            .build();

    private EventEntity event3 = new EventEntity.Builder()
            .withDescription("descricao evento 3")
            .withLog("log do evento 3")
            .withLevel(LevelEnum.INFO)
            .withOrigin(app1)
            .build();

    private EventEntity event4 = new EventEntity.Builder()
            .withDescription("descricao evento 4")
            .withLog("log do evento 4")
            .withLevel(LevelEnum.WARNING)
            .withOrigin(app1)
            .withQuantity(10)
            .build();

    @Before
    public void init() {
        testEntityManager.persist(app1);
        dateTimeTest = testEntityManager.persist(event1).getCreatedAt();
        testEntityManager.persist(event2);
        testEntityManager.persist(event3);
        testEntityManager.persist(event4);
        testEntityManager.flush();
    }

    @Test
    public void saveValid() {
        EventEntity eventSaved = eventRepository.save(event1);
        EventEntity eventSaved2 = eventRepository.save(event4);

        assertThat(eventSaved).isEqualTo(event1);
        assertThat(eventSaved.getCreatedAt()).isNotNull();
        assertThat(eventSaved.getLevel()).isEqualTo(LevelEnum.INFO);
        assertThat(eventSaved.getApplication()).isEqualTo(app1);
        assertThat(eventSaved.getQuantity()).isEqualTo(1);
        assertThat(eventSaved2.getQuantity()).isEqualTo(10);
    }

    @Test
    public void saveInvalid() {
        EventEntity event1 = new EventEntity.Builder()
                .withDescription("kfsmdkfmskfms")
                .withLog("fsn")
                .withLevel(LevelEnum.WARNING)
                .withOrigin(app1)
                .build();

        EventEntity event2 = new EventEntity.Builder()
                .withDescription("")
                .withLog("fsnfdjnfssdfs")
                .withLevel(LevelEnum.WARNING)
                .withOrigin(app1)
                .build();

        assertThatThrownBy(() -> {
            eventRepository.saveAndFlush(event1);
        }).isInstanceOf(ConstraintViolationException.class);

        assertThatThrownBy(() -> {
            eventRepository.saveAndFlush(event2);
        }).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void findByIdValid() {
        assertThat(eventRepository.findById(event1.getId()).get()).isEqualTo(event1);
    }

    @Test
    public void findByIdInvalid() {
        assertThatThrownBy(() -> {
            eventRepository.findById(10000L).get();
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void findAll() {
        assertThat(eventRepository.findAll()).hasSize(4);
    }

    @Test
    public void findByLevel() {
        assertThat(eventRepository.findByLevel(LevelEnum.INFO)).hasSize(2);
    }

    @Test
    public void findByDescription() {
        assertThat(eventRepository.findByDescriptionContaining("evento")).hasSize(4);
    }

    @Test
    public void findByLog() {
        assertThat(eventRepository.findByLogContaining("evento 1")).hasSize(1);
    }

    @Test
    public void findByApplicationId() {
        assertThat(eventRepository.findByApplicationId(app1.getId())).hasSize(4);
    }

    @Test
    public void findByCreatedAt() {
        assertThat(eventRepository.findByCreatedAt(dateTimeTest)).hasSize(1);
    }

    @Test
    public void findByQuantity() {
        assertThat(eventRepository.findByQuantity(1)).hasSize(3);
    }

}
