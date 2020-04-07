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

    LocalDateTime dateTimeTest;

    private ApplicationEntity app1 = new ApplicationEntity("conta azul", "conta@conta.com", "123456789");

    private EventEntity event1 = new EventEntity("evento 1 fsdfs", "sfs dfs informacaofdfd", LevelEnum.INFO, app1);
    private EventEntity event2 = new EventEntity("evento 2 sfsfs", "acesso sfsdf ", LevelEnum.WARNING, app1);
    private EventEntity event3 = new EventEntity("evento 3 sfsdfs", "sfs dfs informacao", LevelEnum.INFO, app1);
    private EventEntity event4 = new EventEntity("evento 4 sfsdfsd", "sistema sf sdf", LevelEnum.ERROR, app1);

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

        assertThat(eventSaved).isEqualTo(event1);
        assertThat(eventSaved.getCreatedAt()).isNotNull();
        assertThat(eventSaved.getLevel()).isEqualTo(LevelEnum.INFO);
        assertThat(eventSaved.getApplication()).isEqualTo(app1);
        assertThat(eventSaved.getQuantity()).isEqualTo(1);
    }

    @Test
    public void saveInvalid() {
        EventEntity event = new EventEntity();

        assertThatThrownBy(() -> {
            eventRepository.saveAndFlush(event);
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
        assertThat(eventRepository.findByLogContaining("sfs dfs informacao")).hasSize(2);
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
        assertThat(eventRepository.findByQuantity(1)).hasSize(4);
    }

}
