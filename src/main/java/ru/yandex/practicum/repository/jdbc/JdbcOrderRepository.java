package ru.yandex.practicum.repository.jdbc;

import org.springframework.asm.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.yandex.practicum.model.Ingredient;
import ru.yandex.practicum.model.IngredientRef;
import ru.yandex.practicum.model.Taco;
import ru.yandex.practicum.model.TacoOrder;
import ru.yandex.practicum.repository.OrderRepository;

import java.beans.Transient;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class JdbcOrderRepository implements OrderRepository {

    private final String SAVE_QUERY =
            """
            INSERT INTO taco_order (delivery_name, delivery_street, delivery_city, delivery_state, delivery_zip,
            cc_number, cc_expiration, cc_cvv, placed_at) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)
            """;
    private final String SAVE_TACO_QUERY =
            """
            INSERT INTO taco (name, created_at, taco_order, taco_order_key) VALUES (?, ?, ?, ?)
            """;
    private final String SAVE_INGREDIENT_REF =
            """
            INSERT INTO ingredient_ref (ingredient, taco, taco_key) VALUES (?, ?, ?)
            """;

    private JdbcOperations jdbcOperations;

    public JdbcOrderRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    @Transactional
    public TacoOrder save(TacoOrder order) {
        PreparedStatementCreatorFactory pscf =
                new PreparedStatementCreatorFactory(SAVE_QUERY,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.VARCHAR,
                Types.VARCHAR, Types.VARCHAR, Types.TIMESTAMP
        );
        pscf.setReturnGeneratedKeys(true);

        order.setPlacedAt(new Date());
        PreparedStatementCreator psc =
                pscf.newPreparedStatementCreator(
                        Arrays.asList(
                                order.getDeliveryName(),
                                order.getDeliveryStreet(),
                                order.getDeliveryCity(),
                                order.getDeliveryState(),
                                order.getDeliveryZip(),
                                order.getCcNumber(),
                                order.getCcExpiration(),
                                order.getCcCVV(),
                                order.getPlacedAt()));

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long orderId = keyHolder.getKey().longValue();
        order.setId(orderId);

        List<Taco> tacos = order.getTacos();
        int i = 0;
        for (Taco taco : tacos) {
            saveTaco(orderId, i++, taco);
        }
        return order;
    }

    private long saveTaco(long orderId, int orderKey, Taco taco) {
        taco.setCreatedAt(new Date());
        PreparedStatementCreatorFactory pscf = new PreparedStatementCreatorFactory(SAVE_TACO_QUERY,
                Types.VARCHAR, Types.TIMESTAMP, Type.LONG, Type.LONG);
        pscf.setReturnGeneratedKeys(true);

        PreparedStatementCreator psc = pscf.newPreparedStatementCreator(
                Arrays.asList(
                        taco.getName(),
                        taco.getCreatedAt(),
                        orderId,
                        orderKey));
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcOperations.update(psc, keyHolder);
        long tacoId = keyHolder.getKey().longValue();
        taco.setId(tacoId);
        saveIngredientRefs(tacoId, taco.getIngredients());
        return tacoId;
    }

    private void saveIngredientRefs(long tacoId, List<IngredientRef> ingredientRefs) {
        int key = 0;
        for(IngredientRef ingredientRef : ingredientRefs) {
            jdbcOperations.update(SAVE_INGREDIENT_REF, ingredientRef.getIngredients(), tacoId, key++);
        }
    }
}
