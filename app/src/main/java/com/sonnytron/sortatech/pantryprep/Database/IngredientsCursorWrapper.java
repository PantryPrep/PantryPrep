package com.sonnytron.sortatech.pantryprep.Database;

import android.database.Cursor;
import android.database.CursorWrapper;

import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.Database.IngredientSchema.IngredientsTable;

import java.util.List;
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
        String photo = getString(getColumnIndex(IngredientsTable.Cols.PHOTO));
        String type = getString(getColumnIndex(IngredientsTable.Cols.TYPE));
        long exp = getLong(getColumnIndex(IngredientsTable.Cols.EXP));

        Ingredient ingredient = new Ingredient(UUID.fromString(uuidString));
        ingredient.setTitle(title);
        ingredient.setStockPhoto(photo);
        ingredient.setDateLong(exp);
        ingredient.setType(type);
        return ingredient;
    }
}
