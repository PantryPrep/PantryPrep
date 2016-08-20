package com.sonnytron.sortatech.pantryprep.Database;

/**
 * Created by sonnyrodriguez on 8/18/16.
 */
public class IngredientSchema {
    public static final class IngredientsTable {
        public static final String NAME = "ingredientsItems";

        public static final class Cols {
            public static final String UUID = "uuid";
            public static final String TITLE = "title";
            public static final String TYPE = "type";
            public static final String PHOTO = "photo";
            public static final String EXP = "date";
        }
    }
}
