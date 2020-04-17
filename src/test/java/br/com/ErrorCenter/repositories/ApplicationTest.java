package br.com.ErrorCenter.repositories;

import br.com.ErrorCenter.entities.ApplicationEntity;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.ConstraintViolationException;

import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ApplicationTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ApplicationRepository applicationRepository;

    private ApplicationEntity app1 = new ApplicationEntity.Builder()
            .withName("conta azul")
            .withEmail("conta@conta.com")
            .withPassword("12345678")
            .build();
    private ApplicationEntity app2 = new ApplicationEntity.Builder()
            .withName("conta verde")
            .withEmail("conta@conta.com")
            .withPassword("12345678")
            .build();
    private ApplicationEntity app3 = new ApplicationEntity.Builder()
            .withName("conta vermelha")
            .withEmail("conta@conta.com")
            .withPassword("12345678")
            .build();
    private ApplicationEntity app4 = new ApplicationEntity.Builder()
            .withName("conta preta")
            .withEmail("conta@conta.com")
            .withPassword("12345678")
            .build();

    @Before
    public void init() {
        testEntityManager.persist(app1);
        testEntityManager.persist(app2);
        testEntityManager.persist(app3);
        testEntityManager.persist(app4);
        testEntityManager.flush();
    }

    @Test
    public void saveValid() {
        ApplicationEntity appSaved = applicationRepository.save(app1);

        assertThat(appSaved).isEqualTo(app1);
        assertThat(appSaved.getCreatedAt()).isNotNull();
    }

    @Test
    public void saveInvalid() {
        ApplicationEntity app = new ApplicationEntity.Builder()
                .withName("teste para falhar")
                .build();

        assertThatThrownBy(() -> {
            applicationRepository.saveAndFlush(app);
        }).isInstanceOf(ConstraintViolationException.class);
    }

    @Test
    public void findByIdValid() {
        assertThat(applicationRepository.findById(app1.getId()).get()).isEqualTo(app1);
    }

    @Test
    public void findByIdInvalid() {
        assertThatThrownBy(() -> {
            applicationRepository.findById(10000L).get();
        }).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void findAll() {
        assertThat(applicationRepository.findAll()).hasSize(4);
    }

}
