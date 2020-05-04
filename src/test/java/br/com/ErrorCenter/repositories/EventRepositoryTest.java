package br.com.ErrorCenter.repositories;

import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.entities.EventEntity;
import br.com.ErrorCenter.enums.LevelEnum;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class EventRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EventRepository eventRepository;

    ApplicationEntity app = new ApplicationEntity.Builder()
            .withEmail("email@email.com")
            .withName("qualquer nome")
            .withPassword("qualquer senha")
            .build();

    EventEntity event = new EventEntity.Builder()
            .withDescription("qualquer descricao1")
            .withLevel(LevelEnum.INFO)
            .withLog("qualquer log1")
            .withOrigin(app)
            .build();

    EventEntity event2 = new EventEntity.Builder()
            .withDescription("qualquer descricao2")
            .withLevel(LevelEnum.ERROR)
            .withLog("qualquer log2")
            .withOrigin(app)
            .build();

    EventEntity event3 = new EventEntity.Builder()
            .withDescription("qualquer descricao3")
            .withLevel(LevelEnum.ERROR)
            .withLog("qualquer log3")
            .withOrigin(app)
            .build();

    EventEntity event4 = new EventEntity.Builder()
            .withDescription("zzzzzzzzzzzzz")
            .withLevel(LevelEnum.WARNING)
            .withLog("zzzzzzzzzzzzzzzz")
            .withOrigin(app)
            .build();

    @Before
    public void setUp() {
        // create application
        entityManager.persistAndFlush(this.app);

        // create event
        entityManager.persistAndFlush(this.event);
        entityManager.persistAndFlush(this.event2);
        entityManager.persistAndFlush(this.event3);
        entityManager.persistAndFlush(this.event4);
    }

    @Test
    public void findAll() {
        //test sem nenhum filtro, retorna todos com um limite de x por pagina, onde x = default page size
        assertThat(
                eventRepository.findByAny(
                        null,
                        null,
                        null,
                        null,
                        null,
                        null,
                        null
                ).getSize()
        ).isEqualTo(4);
    }

    @Test
    public void filterByLevel() {
        EventEntity event = eventRepository.findByAny(
                this.event.getLevel(),
                null,
                null,
                null,
                null,
                null,
                null
                                                    ).get().findFirst().get();
        //o evento retornado no filter é igual ao único evento com essa característica
        assertThat(event).isEqualTo(this.event);

        Integer numberOfElements = eventRepository.findByAny(
                this.event2.getLevel(),
                null,
                null,
                null,
                null,
                null,
                null
                                                    ).getSize();
        //retorna todos os eventos com características iguais
        assertThat(numberOfElements).isEqualTo(2);
    }

    @Test
    public void findByDescription() {
        EventEntity event = eventRepository.findByAny(
                null,
                this.event.getDescription(),
                null,
                null,
                null,
                null,
                null
                                                    ).get().findFirst().get();
        //o evento retornado no filter é igual ao único evento com essa característica
        assertThat(event).isEqualTo(event);

        Integer numberOfElements = eventRepository.findByAny(
                null,
                "qualquer",
                null,
                null,
                null,
                null,
                null
                                                            ).getSize();
        //retorna todos os eventos com características iguais
        assertThat(numberOfElements).isEqualTo(3);
    }

    @Test
    public void findByLog() {
        EventEntity event = eventRepository.findByAny(
                null,
                null,
                this.event.getLog(),
                null,
                null,
                null,
                null
                                                    ).get().findFirst().get();
        //o evento retornado no filter é igual ao único evento com essa característica
        assertThat(event).isEqualTo(event);

        Integer numberOfElements = eventRepository.findByAny(
                null,
                null,
                "qualquer",
                null,
                null,
                null,
                null
                                                            ).getSize();
        //retorna todos os eventos com características iguais
        assertThat(numberOfElements).isEqualTo(3);
    }

    @Test
    public void findByApplicationId() {
        EventEntity event = eventRepository.findByAny(
                null,
                null,
                null,
                this.event.getApplication().getId(),
                null,
                null,
                null
                                                    ).get().findFirst().get();
        //o evento retornado no filter é igual ao único evento com essa característica
        assertThat(event).isEqualTo(event);

        Integer numberOfElements = eventRepository.findByAny(
                null,
                null,
                null,
                this.app.getId(),
                null,
                null,
                null
                                                            ).getSize();
        //retorna todos os eventos com características iguais
        assertThat(numberOfElements).isEqualTo(4);
    }

    @Test
    public void findByDate() {
        EventEntity event = eventRepository.findByAny(
                null,
                null,
                null,
                null,
                this.event.getCreatedAt(),
                null,
                null
                                                    ).get().findFirst().get();
        //o evento retornado no filter é igual ao único evento com essa característica
        assertThat(event).isEqualTo(event);

        Integer numberOfElements = eventRepository.findByAny(
                null,
                null,
                null,
                null,
                this.event.getCreatedAt(),
                null,
                null
                                                        ).getSize();
        //retorna todos os eventos com características iguais
        assertThat(numberOfElements).isEqualTo(4);
    }

    @Test
    public void findByQuantity() {
        EventEntity event = eventRepository.findByAny(
                null,
                null,
                null,
                null,
                null,
                this.event.getQuantity(),
                null
                                                    ).get().findFirst().get();
        //o evento retornado no filter é igual ao único evento com essa característica
        assertThat(event).isEqualTo(event);

        Integer numberOfElements = eventRepository.findByAny(
                null,
                null,
                null,
                null,
                null,
                1,
                null
                                                            ).getSize();
        //retorna todos os eventos com características iguais
        assertThat(numberOfElements).isEqualTo(4);
    }

    @Test
    public void findByPageable() {
        int pageSize = 3;

        Integer numberOfElements = eventRepository.findByAny(
                null,
                null,
                null,
                null,
                null,
                null,
                PageRequest.of(0, pageSize)
                                                            ).getSize();
        //deve retornar no maximo {pageSize} elementos por pagina
        assertThat(numberOfElements).isEqualTo(pageSize);
    }

    @Test
    public void findByMoreThanOneParam() {
        EventEntity event3 = new EventEntity.Builder()
                .withDescription("qualquer descricao3")
                .withLevel(LevelEnum.ERROR)
                .withLog("qualquer log3")
                .withOrigin(app)
                .build();
        Integer numberOfElements = eventRepository.findByAny(
                LevelEnum.ERROR,
                "qualquer descricao",
                "qualquer log3",
                app.getId(),
                null,
                null,
                null).getSize();

        assertThat(numberOfElements).isEqualTo(1);
    }

}
