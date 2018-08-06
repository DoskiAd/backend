package io.shifu.doskiad.validators;

import io.shifu.doskiad.model.Item;
import io.shifu.doskiad.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ItemValidator {
    private final CategoryRepository categoryRepository;

    @Autowired
    public ItemValidator(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    public String validate(Item item) {
        if (!categoryRepository.findById(item.getCategory()).isPresent()) {
            return "Category error";
        }

        if (item.getTitle() == null || item.getDescription() == null || item.getPrice() == null || item.getLocation() == null) {
            return "All form fields are required for submission.";
        }

        if (item.getTitle().isEmpty() || item.getDescription().isEmpty() || item.getPrice() < 0 || item.getLocation().isEmpty()) {
            return "All form fields are required for submission.";
        }

        if (item.getTitle().length() < 4 || item.getTitle().length() > 50) {
            return "Please use between 4 and 50 characters for title.";
        }

        if (item.getDescription().length() < 8 || item.getDescription().length() > 1000) {
            return "Please use between 8 and 1000 characters for description.";
        }

        if (item.getLocation().length() < 4 || item.getLocation().length() > 20) {
            return "Please use between 4 and 20 characters for location.";
        }

        return null;
    }
}
