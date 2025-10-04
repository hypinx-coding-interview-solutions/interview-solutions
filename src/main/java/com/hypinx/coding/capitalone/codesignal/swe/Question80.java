package com.hypinx.coding.capitalone.codesignal.swe;

import com.hypinx.coding.misc.TestCaseValidator;

import java.util.Arrays;

public class Question80 {

    public static void main(String[] args) {
        String[] ingredients = new String[]{"flour", "sugar", "eggs"};
        String[] recipes = new String[]{"floursugar", "random", "flour", "sugarflour", "sugareggs"};
        boolean[] expected = new boolean[]{true, false, true, false, false};
        boolean[] result = solution(ingredients, recipes);

        TestCaseValidator.validateTestCase("1", Arrays.equals(expected, result));
    }

    public static boolean[] solution(String[] ingredients, String[] recipes) {
        boolean[] result = new boolean[recipes.length];

        for (int i = 0; i < recipes.length; i++) {
            String recipe = recipes[i];
            boolean possible = true;
            for (int j = 0; j < ingredients.length; j++) {
                String current = ingredients[j];
                if (!recipe.startsWith(current)) {
                    possible = false;
                    break;
                } else {
                    recipe = recipe.substring(current.length());
                }

                if (recipe.length() == 0) break;
            }
            result[i] = recipe.length() == 0 && possible ? true : false;
        }

        return result;
    }
}
