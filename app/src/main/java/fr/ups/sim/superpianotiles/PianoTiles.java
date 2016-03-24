package fr.ups.sim.superpianotiles;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Random;
import java.util.Set;

/**
 * Created by mathieukostiuk on 22/03/2016.
 */
public class PianoTiles {

    private Set<Tiles> tilesList = new LinkedHashSet<Tiles>();
    private int score;
    private Tiles nextTile;
    protected Random rand;

    public PianoTiles() {
        this.score = 0;
        this.rand = new Random();
        this.nextTile = null;
    }

    public void newTile() {

        boolean ajoute = false;


        while(!ajoute) {

            int top = this.rand.nextInt(3+1);
            int left = this.rand.nextInt(4+1);
            Integer num = this.tilesList.size();

            ajoute = this.tilesList.add(new Tiles(num.toString(),top,left));
            this.nextTile = this.tilesList.iterator().next();

        }
    }

    public boolean isCorrectTileTouched(float x, float y, float bottom, float width) {

        if (this.nextTile == null)
            return false;
        else{
            int[] tab = this.nextTile.getPos();

            float left = width*tab[0]/5;
            float top = bottom* tab[1]/4;
            float right = width-width *tab[2]/5;
            float bot = bottom - bottom*tab[3]/4;

            System.err.println("x: "+x+" y: "+y+"left: "+left+" right: "+right+
            " top : "+top+" bot: "+bot);

            this.removeNextTile();
            if (!this.tilesList.isEmpty())
                this.nextTile = this.tilesList.iterator().next();
            else
                this.nextTile = null;


            //Coucou ma pute
            return (x >= (left) &&
                    x <= (right) &&
                    y >= (top) &&
                    y <= (bot));

           // return true;
        }
    }

    public Set<Tiles> getTiles() {
        return this.tilesList;
    }

    private void removeNextTile() {
        if (this.nextTile != null)
            this.tilesList.remove(this.nextTile);
    }

    public int getScore() {
        return this.score;
    }

    public void incrementeScore() {
        this.score++;
    }
}
