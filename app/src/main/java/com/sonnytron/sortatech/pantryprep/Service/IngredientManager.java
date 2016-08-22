package com.sonnytron.sortatech.pantryprep.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.sonnytron.sortatech.pantryprep.Database.IngredientsBaseHelper;
import com.sonnytron.sortatech.pantryprep.Database.IngredientsCursorWrapper;
import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.Database.IngredientSchema.IngredientsTable;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by sonnyrodriguez on 8/18/16.
 */
public class IngredientManager {
    private static IngredientManager sIngredientManager;
    private Context mContext;
    private SQLiteDatabase mDatabase;

    public static IngredientManager get(Context context) {
        if (sIngredientManager == null) {
            sIngredientManager = new IngredientManager(context);
        }
        return sIngredientManager;
    }

    public void addIngredient(Ingredient ingredient) {
        ContentValues values = getContentValues(ingredient);
        mDatabase.insert(IngredientsTable.NAME, null, values);
    }

    public List<Ingredient> getIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        IngredientsCursorWrapper cursor = queryIngredients(null, null);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ingredients.add(cursor.getIngredient());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return ingredients;
    }

    public Ingredient getIngredient(UUID id) {
        IngredientsCursorWrapper cursor = queryIngredients(
                IngredientsTable.Cols.UUID + " = ?",
                new String[] { id.toString() }
        );
        try {
            if (cursor.getCount() == 0) {
                return null;
            }
            cursor.moveToFirst();
            return cursor.getIngredient();
        } finally {
            cursor.close();
        }
    }

    public void deleteIngredient(Ingredient ingredient) {
        String uuidString = ingredient.getId().toString();
        mDatabase.delete(IngredientsTable.NAME, IngredientsTable.Cols.UUID + " = ?", new String[] { uuidString } );
    }

    private IngredientManager(Context context) {
        mContext = context.getApplicationContext();
        mDatabase = new IngredientsBaseHelper(mContext).getWritableDatabase();
    }

    private static ContentValues getContentValues(Ingredient ingredient) {
        ContentValues values = new ContentValues();
        values.put(IngredientsTable.Cols.UUID, ingredient.getId().toString());
        values.put(IngredientsTable.Cols.TITLE, ingredient.getTitle());
        values.put(IngredientsTable.Cols.TYPE, ingredient.getType());
        values.put(IngredientsTable.Cols.PHOTO, ingredient.getStockPhoto());
        values.put(IngredientsTable.Cols.EXP, ingredient.getExpLong());
        return values;
    }

    private IngredientsCursorWrapper queryIngredients(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(IngredientsTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null);
        return new IngredientsCursorWrapper(cursor);
    }
}
