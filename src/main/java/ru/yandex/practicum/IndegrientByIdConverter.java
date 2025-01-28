package ru.yandex.practicum;


import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.model.Ingredient;
import ru.yandex.practicum.model.Ingredient.Type;

import ru.yandex.practicum.repository.IngredientRepository;


import java.util.HashMap;
import java.util.Map;

@Component
public class IndegrientByIdConverter implements Converter<String, Ingredient> {


    private IngredientRepository ingredientRepository;

    @Autowired
    public IndegrientByIdConverter(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    @Override
    public Ingredient convert(String id) {
        return ingredientRepository.findById(id).orElse(null);

    }
}
