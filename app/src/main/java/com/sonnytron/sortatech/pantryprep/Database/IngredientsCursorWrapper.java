package com.sonnytron.sortatech.pantryprep.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.Database.IngredientSchema.IngredientsTable;

import java.util.UUID;

/**
 * Created by sonnyrodriguez on 8/18/16.
 */
public class IngredientsCursorWrapper extends CursorWrapper {
    public IngredientsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public Ingredient getIngredient() {
        String uuidString = getString(getColumnIndex(IngredientsTable.Cols.UUID));
        String title = getString(getColumnIndex(IngredientsTable.Cols.TITLE));

        Ingredient ingredient = new Ingredient(UUID.fromString(uuidString));
        ingredient.setTitle(title);
        return ingredient;
    }
}
