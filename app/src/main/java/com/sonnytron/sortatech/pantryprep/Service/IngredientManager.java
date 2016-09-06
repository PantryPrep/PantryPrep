package com.sonnytron.sortatech.pantryprep.Service;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.util.Log;

import com.sonnytron.sortatech.pantryprep.Database.IngredientsBaseHelper;
import com.sonnytron.sortatech.pantryprep.Database.IngredientsCursorWrapper;
import com.sonnytron.sortatech.pantryprep.Models.Ingredient;
import com.sonnytron.sortatech.pantryprep.Database.IngredientSchema.IngredientsTable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Created by sonnyrodriguez on 8/18/16.
 */
public class IngredientManager {
    private static IngredientManager sIngredientManager;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private ArrayList<String> ingredientsFromYummly;
    private ArrayList<String> ingredientsLocal;

    public void whichDoWeHave() {
        for (String string: ingredientsFromYummly) {
            if (ingredientsLocal.indexOf(string) != -1) {
                // you have this ingredient
            }
        }
    }

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
        Collections.sort(ingredients);
        return ingredients;
    }



    public List<Ingredient> getIngredientsVeggies() {
        List<Ingredient> ingredients = new ArrayList<>();
        String whereClause = "type = ?";
        String[] whereArgs = {"veggies"};
        IngredientsCursorWrapper cursor = queryIngredients(whereClause, whereArgs);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ingredients.add(cursor.getIngredient());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Collections.sort(ingredients);
        return ingredients;
    }

    public List<Ingredient> getIngredientsProtein() {
        List<Ingredient> ingredients = new ArrayList<>();
        String whereClause = "type = ?";
        String[] whereArgs = {"protein"};
        IngredientsCursorWrapper cursor = queryIngredients(whereClause, whereArgs);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ingredients.add(cursor.getIngredient());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Collections.sort(ingredients);
        return ingredients;
    }

    public List<Ingredient> getIngredientsSpices() {
        List<Ingredient> ingredients = new ArrayList<>();
        String whereClause = "type = ?";
        String[] whereArgs = {"spices"};
        IngredientsCursorWrapper cursor = queryIngredients(whereClause, whereArgs);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ingredients.add(cursor.getIngredient());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Collections.sort(ingredients);
        return ingredients;
    }

    public List<Ingredient> getIngredientsFruits() {
        List<Ingredient> ingredients = new ArrayList<>();
        String whereClause = "type = ?";
        String[] whereArgs = {"fruit"};
        IngredientsCursorWrapper cursor = queryIngredients(whereClause, whereArgs);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ingredients.add(cursor.getIngredient());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Collections.sort(ingredients);
        return ingredients;
    }

    public List<Ingredient> getIngredientsDairy() {
        List<Ingredient> ingredients = new ArrayList<>();
        String whereClause = "type = ?";
        String[] whereArgs = {"dairy"};
        IngredientsCursorWrapper cursor = queryIngredients(whereClause, whereArgs);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ingredients.add(cursor.getIngredient());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Collections.sort(ingredients);
        return ingredients;
    }

    public List<Ingredient> getIngredientsNoSpice() {
        List<Ingredient> ingredients = new ArrayList<>();
        String whereClause = "type != ?";
        String[] whereArgs = {"spices"};
        IngredientsCursorWrapper cursor = queryIngredients(whereClause, whereArgs);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ingredients.add(cursor.getIngredient());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Collections.sort(ingredients);
        return ingredients;
    }

    public List<Ingredient> getIngredientsSpiceOnly() {
        List<Ingredient> ingredients = new ArrayList<>();
        String whereClause = "type = ?";
        String[] whereArgs = {"spices"};
        IngredientsCursorWrapper cursor = queryIngredients(whereClause, whereArgs);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ingredients.add(cursor.getIngredient());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Collections.sort(ingredients);
        return ingredients;
    }

    //get top 5 ingredients
    public List<Ingredient> getTopFiveIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();
        String whereClause = "type != ? and type != ? and type != ?";
        String[] whereArgs = {"spices", "fruits", "dairy"};

        IngredientsCursorWrapper cursor = queryTopFiveIngredients(whereClause, whereArgs);
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ingredients.add(cursor.getIngredient());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Collections.sort(ingredients);
        return ingredients;
    }

    //get expiring
    public List<Ingredient> getExpiringIngredients() {
        List<Ingredient> ingredients = new ArrayList<>();

        IngredientsCursorWrapper cursor = queryExpiringIngredients();
        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                ingredients.add(cursor.getIngredient());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        Collections.sort(ingredients);
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

    private IngredientsCursorWrapper queryTopFiveIngredients(String whereClause, String[] whereArgs) {
        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();

        String[] subQueries = new String[]{
                "SELECT * FROM (SELECT * FROM ingredientsItems where type = 'protein' order by date desc limit 1) as meat",
                "SELECT * FROM (SELECT * FROM ingredientsItems where type in ('veggies') order by date desc limit 4) as others"
        };

        String sql = qb.buildUnionQuery(subQueries,null,null);
        Cursor cursor = mDatabase.rawQuery(sql,null);

        return new IngredientsCursorWrapper(cursor);
    }

    //get expiring
    public IngredientsCursorWrapper queryExpiringIngredients() {
        String sql ="select strftime('%Y-%m-%d', date / 1000, 'unixepoch') AS EXPDATE, * from ingredientsItems where EXPDATE <= date('now', '5 days')";
        Cursor cursor = mDatabase.rawQuery(sql,null);

        return new IngredientsCursorWrapper(cursor);
    }
}
