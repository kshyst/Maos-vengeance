package com.mygdx.game.Model.Opps;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.MaosVengeance;

import java.util.ArrayList;

public class OppMissleController {
    public static ArrayList<OppMissle> oppMissles = new ArrayList<OppMissle>();

    public static void update(float delta , SpriteBatch batch){
        for (int i = 0; i < oppMissles.size(); i++) {
            oppMissles.get(i).update(delta);
            oppMissles.get(i).render(batch);
            if (oppMissles.get(i).isDestroyed){
                oppMissles.remove(i);
            }
        }
    }
}
