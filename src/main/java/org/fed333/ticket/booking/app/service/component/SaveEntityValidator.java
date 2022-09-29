package org.fed333.ticket.booking.app.service.component;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.fed333.ticket.booking.app.model.Identifiable;
import org.fed333.ticket.booking.app.repository.CrudRepository;
import org.springframework.data.jpa.repository.JpaRepository;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;


@Slf4j
public class SaveEntityValidator<E extends Identifiable<ID>, ID> {

    @Setter
    private JpaRepository<E, ID> repository;

    /**
     * Validates if the entity object can be inserted.
     * @param entity object to be inserted
     * @throws RuntimeException when the object has id and there is another one with same id
     * */
    public void validateCreate(E entity) {
        if (nonNull(entity.getId()) && repository.existsById(entity.getId())){
            throw new RuntimeException("The " + entity.getClass().getName() + " object with id " + entity.getId() + " already exists");
        }
        log.info("{} passed create validation.", entity);
    }

    /**
     * Validates if the entity object can be updated.
     * @param entity object to be inserted
     * @throws RuntimeException when the entity has no id, or is missing
     * */
    public void validateUpdate(E entity) {
        if (isNull(entity.getId())){
            throw new RuntimeException("The " + entity.getClass().getName() + " object must have an id.");
        }
        if (!repository.existsById(entity.getId())) {
            throw new RuntimeException("The " + entity.getClass().getName() + " object with id " + entity.getId() + "is missing.");
        }
        log.info(entity.getClass().getName() + " {} passed update validation.", entity);
    }

}
